package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import datamodel.Event;


public class UtilEvent extends UtilDB {

	// get all events
	public static List<Event> listEvents() {
		List<Event> resultList = new ArrayList<Event>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> events = session.createQuery("FROM Event").list();
			for (Iterator<?> iterator = events.iterator(); iterator.hasNext();) {
				Event event = (Event) iterator.next();
				resultList.add(event);
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

	// get all events created by a specific user
	public static List<Event> listEvents(String username) {
		List<Event> resultList = new ArrayList<Event>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> events = session.createCriteria(Event.class).add(Restrictions.eq("username", username)).list();
			for (Iterator<?> iterator = events.iterator(); iterator.hasNext();) {
				Event event = (Event) iterator.next();
				resultList.add(event);
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

	// get event with specified eventId
	public static Event getEvent(int eventId) {
		Event event = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			event = (Event) session.createCriteria(Event.class).add(Restrictions.eq("eventId", eventId)).uniqueResult();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return event;
	}

	// create a new event
	public static boolean createEvent(String username, String name, String description) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		boolean result = true;
		try {
			tx = session.beginTransaction();
			session.save(new Event(username, name, description));
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
