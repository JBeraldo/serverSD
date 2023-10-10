package com.sd.server.DAO;

import com.sd.server.exceptions.NotFoundException;
import com.sd.server.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Objects;


public class UserDAO {

    private final SessionFactory sessionFactory;

    public UserDAO() {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isUserAdmin(long id) {
        try (Session session = sessionFactory.openSession()) {
            return Objects.equals(this.getUserById(id).getType(), "admin");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email) throws NotFoundException {
            Session session = sessionFactory.openSession();
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            User user;
            query.setParameter("email", email);
            if((user= query.uniqueResult()) != null ){
                return user;
            }
            throw new NotFoundException("Usuário não encontrado");
    }

    public void addUserIfNotExistByEmail(User user) {
        if (!isUserExistsByEmail(user.getEmail())) {
            addUser(user);
        }
    }

    public void addFirstUser(){
        User user = new User("Admin","0192023A7BBD73250516F069DF18B500","admin@sd.com","admin");
        addUserIfNotExistByEmail(user);
    }

    public boolean isUserExistsByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            return query.uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}