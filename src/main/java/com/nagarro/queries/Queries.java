package com.nagarro.queries;

import com.nagarro.entity.images.Images;
import com.nagarro.entity.users.Users;
import com.nagarro.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.util.List;

// Class used to create queries
public class Queries {

    // Method to change the password of a user
    public static void changePassword(String userName, String newPassword) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Users SET password='" + newPassword + "' WHERE userName='" + userName + "' OR emailID='" + userName + "'";
        Query query = session.createQuery(hql);

        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    // Method to check the user name
    public static Users checkUser(String userName) {
        Session session = HibernateUtil.getSession();

        String hql = "FROM Users WHERE userName='" + userName + "' OR emailID='" + userName +  "'";

        Query query = session.createQuery(hql);
        List<Users> users = query.list();
        session.close();

        return users.get(0);
    }

    // Method to authenticate the user name and password
    public static Users getUser(String userName, String password) throws Exception {
        Session session = HibernateUtil.getSession();

        String hql = "FROM Users WHERE (userName='" + userName + "' OR emailID='" + userName +  "') AND password='" + password + "'";

        Query query = session.createQuery(hql);
        List<Users> users = query.list();
        session.close();

        return users.get(0);
    }

    public static void addImage(String name, int userID, File file) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        float size = file.length() / (1024);
        String fileName = file.getName();

        Images image = new Images(name, size, fileName);

        String hql = "FROM Users WHERE id=" + userID;
        Query query = session.createQuery(hql);

        List<Users> users = query.list();
        Users user = users.get(0);

        user.addImage(image);
        image.setUser(user);

        session.save(image);
        transaction.commit();
        session.close();

    }

    public static List<Images> getImages(int id) {

        Session session = HibernateUtil.getSession();

        String hql = "FROM Images WHERE userID=" + id;
        Query query = session.createQuery(hql);

        List<Images> images = query.list();

        return images;
    }

    // Method to delete the image
    public static void deleteImage(int id) {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "DELETE FROM Images WHERE id='" + id + "'";
        Query query = session.createQuery(hql);

        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    // Method to edit the image
    public static void editImage(int id, String name, File file) {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        float size = file.length() / 1024;
        String fileName = file.getName();
        String hql = "UPDATE Images SET name='" + name + "', fileName='" + fileName + "', size='" + size + "' WHERE id='" + id + "'";
        Query query = session.createQuery(hql);

        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public static void totalFileSize(int id, File file) {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Images WHERE userID='" + id + "'";
        Query query = session.createQuery(hql);

        List<Images> images = query.list();
        transaction.commit();
        session.close();

        float size = 0;
        for (Images image : images) {
            size += image.getSize();
        }

        size += file.length() / 1024;

        if (size > 1024*1024*10) {
            throw new RuntimeException("Total File Size Exceeded 10 MB");
        }
    }

}
