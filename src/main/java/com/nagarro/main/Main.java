package com.nagarro.main;

import com.nagarro.entity.images.Images;
import com.nagarro.hibernate.HibernateUtil;
import com.nagarro.entity.users.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;


public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        File file = new File("src/main/resources/images/download.jpg");
        String fileName = file.getName();
        String name = file.getName().replace(".jpg", "");
        float size = file.length() / (1024);

        Images image = new Images(name, size, fileName);
        Users user = new Users("Vaibhav Jain", "as1908", "vaibj1908@gmail.com", "raja1908");

        user.addImage(image);
        image.setUser(user);

        session.save(user);
        session.save(image);

        transaction.commit();
        System.out.println("Records inserted sucessessfully");
        session.close();

    }

}
