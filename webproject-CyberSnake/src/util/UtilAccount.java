package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import datamodel.Account;

public class UtilAccount extends UtilDB{
	
	//get all accounts
	public static List<Account> listAccounts() {
		List<Account> resultList = new ArrayList<Account>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; 

		try {
			tx = session.beginTransaction();
			List<?> accounts = session.createQuery("FROM Account").list();
			for (Iterator<?> iterator = accounts.iterator(); iterator.hasNext();) {
				Account account = (Account) iterator.next();
				resultList.add(account);
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

	//get account with specified username
	public static Account getAccount(String username) {
		Account account = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			account = (Account) session.createCriteria(Account.class).add(Restrictions.eq("username", username)).uniqueResult();
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return account;
	}

	//create a new account
	public static boolean createAccount(String userName, String firstName, String lastName, String address, String city,
			String state, String zip, String email, String phone, String password, Boolean admin) {
		Session session = getSessionFactory().openSession();
		boolean result = true;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Account(userName, firstName, lastName, address, city, state, Integer.valueOf(zip), email,
					phone, password, admin));
			tx.commit();
		} catch (HibernateException e) {
			result = false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch (java.lang.NumberFormatException e) {
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
