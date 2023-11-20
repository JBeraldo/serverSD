package com.sd.server.DAO;

import com.sd.server.Models.Point;
import com.sd.server.Models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class PointDAO {

    private final SessionFactory sessionFactory;

    public PointDAO() {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void addPoint(Point point) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(point);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Point> getAllPoints() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Point ", Point.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deletePoint(Long point_id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Point WHERE id = :point_id");
            query.setParameter("point_id",point_id);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public Point getPointById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Point.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updatePoint(Point point) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(point);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
