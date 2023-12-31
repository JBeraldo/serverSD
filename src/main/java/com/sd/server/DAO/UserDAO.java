package com.sd.server.DAO;

import com.sd.server.Exceptions.EmailAlreadyUsedException;
import com.sd.server.Exceptions.NotFoundException;
import com.sd.server.Models.User;
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
            throw e;
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
            throw e;
        }
    }

    public void deleteUser(Long user_id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User WHERE id = :user_id");
            query.setParameter("user_id",user_id);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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

    public void addUserIfNotExistByEmail(User user) throws EmailAlreadyUsedException {
        if (!isUserExistsByEmail(user.getEmail())) {
            addUser(user);
        }
        else {
            throw new EmailAlreadyUsedException();
        }
    }

    public void addFirstUser() {
        User user = new User("Admin","$2a$10$52ds0zBAVM7nMuGbdbz.SObIZDWre5I.Rg/vT2n/HpuVi3/Rh176K","admin@sd.com","admin");
        try {
            addUserIfNotExistByEmail(user);
        } catch (EmailAlreadyUsedException ignored) {

        }
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