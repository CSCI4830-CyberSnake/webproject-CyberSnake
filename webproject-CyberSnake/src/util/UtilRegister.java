package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.time.LocalDate;

import datamodel.Register;
import datamodel.Timeslot;


public class UtilRegister extends UtilDB{
	
	//get all entries from registers table
	public static List<Register> listRegisters() {
		List<Register> resultList = new ArrayList<Register>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; 

		try {
			tx = session.beginTransaction();
			List<?> registers = session.createQuery("FROM Register").list();
			for (Iterator<?> iterator = registers.iterator(); iterator.hasNext();) {
				Register register = (Register) iterator.next();
				resultList.add(register);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}

	//get all entries from registers table associated with specified username
	public static List<Register> listRegisters(String username) {
		List<Register> resultList = new ArrayList<Register>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			List<?> registers = session.createCriteria(Register.class).add(Restrictions.eq("username", username)).list();
			
			for (Iterator<?> iterator = registers.iterator(); iterator.hasNext();) {
				Register register = (Register) iterator.next();
				resultList.add(register);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}
	
	//get all entries from registers table associated with specified username that have not passed yet
	public static List<Register> listUpcoming(String username) {
		List<Register> resultList = new ArrayList<Register>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			List<?> registers = session.createCriteria(Register.class).add(Restrictions.eq("username", username)).list();
			
			for (Iterator<?> iterator = registers.iterator(); iterator.hasNext();) {
				Register register = (Register) iterator.next();
				Timeslot timeslot = UtilTimeslot.getTimeslot(register.getTimeslotId());
				String date = timeslot.getDate().toString();
				if(LocalDate.now().isBefore(LocalDate.parse(date)))
					resultList.add(register);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}	
	
	//get all entries from registers table associated with specified username that have not passed yet
	public static List<Register> listPassed(String username) {
		List<Register> resultList = new ArrayList<Register>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			List<?> registers = session.createCriteria(Register.class).add(Restrictions.eq("username", username)).list();
			
			for (Iterator<?> iterator = registers.iterator(); iterator.hasNext();) {
				Register register = (Register) iterator.next();
				Timeslot timeslot = UtilTimeslot.getTimeslot(register.getTimeslotId());
				String date = timeslot.getDate().toString();
				if(LocalDate.now().isAfter(LocalDate.parse(date)))
					resultList.add(register);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}	

	//create a new registration entry
	public static boolean createRegister(String username, String timeslotId) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		boolean result = true;
		
		try {
			tx = session.beginTransaction();
			session.save(new Register(username, Integer.valueOf(timeslotId)));
			tx.commit();
		} catch (HibernateException e) {
			result= false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
	
	//create a new registration entry
	public static boolean cancelRegister(String username, String timeslotId) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		boolean result = true;
		
		try {
			tx = session.beginTransaction();
			
			Register reg = (Register) session.createCriteria(Register.class).add(Restrictions.eq("username", username))
				.add(Restrictions.eq("timeslotId", Integer.parseInt(timeslotId))).uniqueResult();
			
			session.delete(reg);
			tx.commit();
		} catch (HibernateException e) {
			result= false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}


}
