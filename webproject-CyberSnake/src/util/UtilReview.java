package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import datamodel.Review;


public class UtilReview extends UtilDB{
	
	//get all reviews
	public static List<Review> listReviews() {
		
		List<Review> resultList = new ArrayList<Review>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> reviews = session.createQuery("FROM Review").list();
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

	//get all reviews associated with the specified eventId
	public static List<Review> listReviews(String eventId) {
		List<Review> resultList = new ArrayList<Review>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			List<?> reviews = session.createCriteria(Review.class).add(Restrictions.eq("eventId", Integer.valueOf(eventId))).list();
			
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

	//create a new review
	public static boolean createReview(String username, String eventId, String stars, String comment, String anon) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		boolean result = true;
		
		try {
			tx = session.beginTransaction();
			session.save(new Review(username, Integer.valueOf(eventId), Integer.valueOf(stars), comment, Boolean.parseBoolean(anon)));
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
