/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.sql.Date;

import datamodel.Account;
import datamodel.Event;
import datamodel.Register;
import datamodel.Review;
import datamodel.TimeSlot;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
	static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory != null) {
			return sessionFactory;
		}
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static List<Account> listAccounts() {
		List<Account> resultList = new ArrayList<Account>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; // each process needs transaction and commit the changes in DB.

		try {
			tx = session.beginTransaction();
			List<?> accounts = session.createQuery("FROM Accounts").list();
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

	public static List<Account> listAccounts(String username) {
		List<Account> resultList = new ArrayList<Account>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			System.out.println((Account) session.get(Account.class, 1)); // use "get" to fetch data
			// Query q = session.createQuery("FROM Employee");
			List<?> accounts = session.createQuery("FROM Accounts").list();
			for (Iterator<?> iterator = accounts.iterator(); iterator.hasNext();) {
				Account account = (Account) iterator.next();
				if (account.getUsername().contentEquals(username)) {
					resultList.add(account);
				}
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

	public static void createAccount(String userName, String firstName, String lastName, String address, String city,
			String state, String zip, String email, String phone, String type, String password) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Account(userName, firstName, lastName, address, city, state, Integer.valueOf(zip), email,
					phone, type.charAt(0), password));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static List<Event> listEvents() {
		List<Event> resultList = new ArrayList<Event>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; // each process needs transaction and commit the changes in DB.

		try {
			tx = session.beginTransaction();
			List<?> events = session.createQuery("FROM Events").list();
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

	public static List<Event> listEvents(int eventId) {
		List<Event> resultList = new ArrayList<Event>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			System.out.println((Event) session.get(Event.class, 1)); // use "get" to fetch data
			// Query q = session.createQuery("FROM Employee");
			List<?> events = session.createQuery("FROM Events").list();
			for (Iterator<?> iterator = events.iterator(); iterator.hasNext();) {
				Event event = (Event) iterator.next();
				if (event.getEventId() == (eventId)) {
					resultList.add(event);
				}
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

	public static void createEvent(String username, String name, String description) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Event(username, name, description));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static List<TimeSlot> listTimeSlots() {
		List<TimeSlot> resultList = new ArrayList<TimeSlot>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; // each process needs transaction and commit the changes in DB.

		try {
			tx = session.beginTransaction();
			List<?> timeslots = session.createQuery("FROM Timeslots").list();
			for (Iterator<?> iterator = timeslots.iterator(); iterator.hasNext();) {
				TimeSlot timeslot = (TimeSlot) iterator.next();
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

	public static List<TimeSlot> listTimeSlots(int eventId, Date date, String time) {
		List<TimeSlot> resultList = new ArrayList<TimeSlot>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			System.out.println((TimeSlot) session.get(TimeSlot.class, 1)); // use "get" to fetch data
			// Query q = session.createQuery("FROM Employee");
			List<?> timeslots = session.createQuery("FROM Timeslots").list();
			for (Iterator<?> iterator = timeslots.iterator(); iterator.hasNext();) {
				TimeSlot timeslot = (TimeSlot) iterator.next();
				if (timeslot.getEventId() == (eventId) && timeslot.getDate().equals(date)
						&& timeslot.getTime().contentEquals(time)) {
					resultList.add(timeslot);
				}
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

	public static void createTimeSlot(String eventId, String date, String time, String occupancy) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new TimeSlot(Integer.valueOf(eventId), Date.valueOf(date), time, Integer.valueOf(occupancy)));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static List<Register> listRegisters() {
		List<Register> resultList = new ArrayList<Register>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; // each process needs transaction and commit the changes in DB.

		try {
			tx = session.beginTransaction();
			List<?> registers = session.createQuery("FROM Registers").list();
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

	public static List<Register> listRegisters(String username, int eventId, Date date, String time) {
		List<Register> resultList = new ArrayList<Register>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			System.out.println((Register) session.get(Register.class, 1)); // use "get" to fetch data
			// Query q = session.createQuery("FROM Employee");
			List<?> registers = session.createQuery("FROM Registers").list();
			for (Iterator<?> iterator = registers.iterator(); iterator.hasNext();) {
				Register register = (Register) iterator.next();
				if (register.getUsername().contentEquals(username) && register.getEventId() == (eventId)
						&& register.getDate().equals(date)) {
					resultList.add(register);
				}
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

	public static void createRegister(String username, String eventId, String date, String time) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Register(username, Integer.valueOf(eventId), Date.valueOf(date), time));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static List<Review> listReviews() {
		List<Review> resultList = new ArrayList<Review>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; // each process needs transaction and commit the changes in DB.

		try {
			tx = session.beginTransaction();
			List<?> reviews = session.createQuery("FROM Reviews").list();
			for (Iterator<?> iterator = reviews.iterator(); iterator.hasNext();) {
				Review review = (Review) iterator.next();
				resultList.add(review);
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

	public static List<Review> listReviews(int reviewId) {
		List<Review> resultList = new ArrayList<Review>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			System.out.println((Review) session.get(Review.class, 1)); // use "get" to fetch data
			// Query q = session.createQuery("FROM Employee");
			List<?> reviews = session.createQuery("FROM Reviews").list();
			for (Iterator<?> iterator = reviews.iterator(); iterator.hasNext();) {
				Review review = (Review) iterator.next();
				if (review.getReviewId() == reviewId) {
					resultList.add(review);
				}
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

	public static void createReview(String username, String stars, String comment) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Review(username, Integer.valueOf(stars), comment));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
