package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory factory;
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = Util.buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            System.out.println("User с именем " + user.getName() + " добавлен в базу данных");
            session.getTransaction().commit();
        } catch (Throwable e) {
            session.getTransaction().rollback();
        } finally {
            sessionFactory.close();
        }
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
