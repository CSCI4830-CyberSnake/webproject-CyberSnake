package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;
import java.time.LocalDate;

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
	
	//get all timeslots associated with an eventId ordered by time and date
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
		Collections.sort(resultList, new SortByDateTime());
		return resultList;
	}	
	
	//get all active timeslots associated with an eventId
	public static List<Timeslot> getActiveTimeslotsByEvent(int eventId) {
		List<Timeslot> resultList = new ArrayList<Timeslot>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			List<?> timeslots = session.createCriteria(Timeslot.class).add(Restrictions.eq("eventId", eventId)).list();
			for (Iterator<?> iterator = timeslots.iterator(); iterator.hasNext();) {
				Timeslot timeslot = (Timeslot) iterator.next();
				if( LocalDate.now().isBefore(LocalDate.parse(timeslot.getDate().toString())) &&
						timeslot.getOccupancy() > 0 )
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
	
	//get all timeslots on the same date
	public static List<Timeslot> getTimeslotsByDate(Date date) {
		List<Timeslot> resultList = new ArrayList<Timeslot>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			List<?> timeslots = session.createCriteria(Timeslot.class).add(Restrictions.eq("date", date)).list();
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
	public static boolean createTimeslot(String eventId, String date, String startTime, String endTime, String occupancy) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		boolean result = true;
		
		try {
			tx = session.beginTransaction();
			session.save(new Timeslot(Integer.valueOf(eventId), Date.valueOf(date), startTime, endTime, Integer.valueOf(occupancy)));
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
	
	// increase occupancy by one
	public static void increaseOccupancy(int timeslotId){
	  Session session = getSessionFactory().openSession();
      Transaction tx = null;
      
      try{
         tx = session.beginTransaction();
         Timeslot timeslot = (Timeslot)session.get(Timeslot.class, timeslotId); 
         timeslot.setOccupancy( timeslot.getOccupancy() + 1 );
         session.update(timeslot); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   
	// decrease occupancy by one
   public static void decreaseOccupancy(int timeslotId){
	  Session session = getSessionFactory().openSession();
      Transaction tx = null;
      
      try{
         tx = session.beginTransaction();
         Timeslot timeslot = (Timeslot)session.get(Timeslot.class, timeslotId); 
         timeslot.setOccupancy( timeslot.getOccupancy() - 1 );
         session.update(timeslot); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   
   //get int formatted time: 11:30 to 1130
   public static int getIntTime(String time) {
		String[] array = time.split("\\s*:\\s*");
		return (Integer.parseInt(array[0]) * 100) + Integer.parseInt(array[1]);
	}
	
	//checks whether the given start and end time are available during the given date
	public static boolean available(Date date, String startTime, String endTime) {
		List<Timeslot> list = UtilTimeslot.getTimeslotsByDate(date);
		if( list.size() == 0)
			return true;
		
		int start = UtilTimeslot.getIntTime(startTime);
		int end = UtilTimeslot.getIntTime(endTime);
		
		//for each existing timeslot check whether there is any overlap in time with new time
		for(Timeslot timeslot: list) {
			if( ( start >= UtilTimeslot.getIntTime(timeslot.getStartTime()) && 
				start <= UtilTimeslot.getIntTime(timeslot.getEndTime()) ) || 
				( end >= UtilTimeslot.getIntTime(timeslot.getStartTime()) && 
				end <= UtilTimeslot.getIntTime(timeslot.getEndTime()) ))
				return false;
		}
		
		return true;
	}
	
	//transforms time from 24hr format to 12hr format. example: 13:45 to 1:45pm
	public static String getFormattedTime(String startTime, String endTime) {
		String result1 = "";
		String[] array1 = startTime.split("\\s*:\\s*");

		if(Integer.parseInt(array1[0]) < 12) {
			if( Integer.parseInt(array1[0]) == 0)
				result1 += "12:" + array1[1] + "am";
			else
				result1 += array1[0] + ":" + array1[1] + "am";
			
		}
		else {
			if( Integer.parseInt(array1[0]) == 12)
				result1 += array1[0] + ":" + array1[1] + "pm";
			else
				result1 += String.valueOf(Integer.parseInt(array1[0]) - 12) + ":" + array1[1] + "pm";
		}
		
		String result2 = "";
		String[] array2 = endTime.split("\\s*:\\s*");

		if(Integer.parseInt(array2[0]) < 12) {
			if( Integer.parseInt(array2[0]) == 0)
				result2 += "12:" + array2[1] + "am";
			else
				result2 += array2[0] + ":" + array2[1] + "am";
			
		}
		else {
			if( Integer.parseInt(array2[0]) == 12)
				result2 += array2[0] + ":" + array2[1] + "pm";
			else
				result2 += String.valueOf(Integer.parseInt(array2[0]) - 12) + ":" + array2[1] + "pm";
		}
		return result1 + " - " + result2;
	}


}
