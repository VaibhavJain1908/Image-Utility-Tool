/**#################################################################
 * 
 **#################################################################*/
package com.nagarro.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**#################################################################
 * @author VAIBHAV JAIN
 * @Date: 27 APRIL 2021
 * @Description:
 *HibernateUtil
 *#################################################################*/

@SuppressWarnings("deprecation")
public class HibernateUtil {

	private static HibernateUtil instance=new HibernateUtil();
    private SessionFactory sessionFactory;
    
    public static HibernateUtil getInstance(){
            return instance;
    }
    
    private HibernateUtil(){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
                
        sessionFactory = configuration.buildSessionFactory();
    }
    
    public static Session getSession(){
        Session session =  getInstance().sessionFactory.openSession();
        
        return session;
    }
}
