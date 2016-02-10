package Hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		List<Object []> user = null;
		
		Session session = sessionFactory.openSession();
		
		try{
			session.beginTransaction();
			SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM user");
			sqlQuery.addEntity(User.class);
			user = sqlQuery.list();
			
			
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
			sessionFactory.close();
		}
		
		//User user2 = new User();
		
		for(Object [] us : user){
			System.out.println((us[0].toString()));
		}
		
		//System.out.println("Приветики!!!");
		/*for(Iterator iterator = user.iterator(); iterator.hasNext();){
			User users = (User) iterator.next();
			System.out.println(users.toString());
		}*/
	}
}