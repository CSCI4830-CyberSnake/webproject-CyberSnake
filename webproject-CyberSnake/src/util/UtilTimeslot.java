package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;
import datamodel.Timeslot;



public class UtilTimeslot extends UtilDB{
	
	//get all timeslots
	public static List<Timeslot> listTimeslots() {
		List<Timeslot> resultList = new ArrayList<Timeslot>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> timeslots = session.createQuery("FROM Timeslot").list();
			for (Iterator<?> iterator = timeslots.iterator(); iterator.hasNext();) {
				Timeslot timeslot = (Timeslot) iterator.next();
				resultList.add(timeslot);
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

	//get one specific timeslot based on timeslotId
	public static Timeslot getTimeslot(int timeslotId) {
		Timeslot timeslot = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			timeslot = (Timeslot) session.createCriteria(Timeslot.class).add(Restrictions.eq("timeslotId", timeslotId)).uniqueResult();
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return timeslot;
	}
	
	//get all timeslots associated with an eventId
	public static List<Timeslot> getTimeslotsByEvent(int eventId) {
		List<Timeslot> resultList = new ArrayList<Timeslot>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			List<?> timeslots = session.createCriteria(Timeslot.class).add(Restrictions.eq("eventId", eventId)).list();
			for (Iterator<?> iterator = timeslots.iterator(); iterator.hasNext();) {
				Timeslot timeslot = (Timeslot) iterator.next();
				resultList.add(timeslot);
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

	//create timeslot
	public static boolean createTimeslot(String eventId, String date, String time, String occupancy) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		boolean result = true;
		
		try {
			tx = session.beginTransaction();
			session.save(new Timeslot(Integer.valueOf(eventId), Date.valueOf(date), time, Integer.valueOf(occupancy)));
			tx.commit();
		} catch (HibernateException e) {
			result = false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}


}
