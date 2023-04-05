package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.buildSessionFactory();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE users (id BIGINT AUTO_INCREMENT, " +
                    "name VARCHAR(15), lastname VARCHAR(25), age TINYINT, PRIMARY KEY (id));").executeUpdate();
            transaction.commit();
        } catch (Throwable e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("������� ��� ���������� ��� ��������� ���� ������ ��� ���������� �������");
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession())  {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE users").executeUpdate();
            session.getTransaction().commit();
        } catch (Throwable e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("������� �� ���������� ��� ��������� ���� ������ ��� ���������� �������");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            System.out.println("User � ������ " + new User(name, lastName, age).getName() + " �������� � ���� ������");
            session.getTransaction().commit();
        } catch (Throwable e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("������ ��� ���������� ������������");
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Throwable e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("������ ��� �������� ������������");
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<User> userList = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            return userList;
        } catch (Throwable e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("������ ��� ������ ���� ������ �� �������");
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE from User").executeUpdate();
            session.getTransaction().commit();
        } catch (Throwable e) {;
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("������ ��� ������� �������");
        }
    }
}
