package com.sd.server.DAO;

import com.sd.server.models.JWTSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class JWTSessionDAO {

    private final SessionFactory sessionFactory;

    public JWTSessionDAO() {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void addJWTSession(JWTSession jwt_session) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(jwt_session);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteAllJWTSession() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createQuery("DELETE FROM JWTSession WHERE TRUE").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUserAllSessions(Integer user_id){
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query<JWTSession> query = session.createQuery("DELETE from JWTSession WHERE user.id = :user_id", JWTSession.class);
            query.setParameter("user_id",user_id);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public boolean hasUserActiveSession(int user_id) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("SELECT COUNT(*) from JWTSession where user.id = :user_id");
            query.setParameter("user_id",user_id);
            Long count = (Long)query.uniqueResult();
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}