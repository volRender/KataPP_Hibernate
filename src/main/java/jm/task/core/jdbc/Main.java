package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //save 4 users for table data
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl();
        dao.saveUser("Daniil", "Litvishko", (byte) 23);
        dao.saveUser("Vladislav", "Varakin", (byte) 23);
        dao.saveUser("Nikita", "Vasiliev", (byte) 22);
        dao.saveUser("Lena", "Soroka", (byte) 22);

        // get all users info from the table
        List<User> userList = dao.getAllUsers();
        for (User u: userList) {
            System.out.println(u);
        }

        /*
        //create table Users
        service.createUsersTable();




        //clean all data from the table
        service.cleanUsersTable();

        //drop table Users
        service.dropUsersTable();
         */
    }
}
