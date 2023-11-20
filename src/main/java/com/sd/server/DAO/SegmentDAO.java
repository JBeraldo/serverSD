package com.sd.server.DAO;

import com.sd.server.Models.Segment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class SegmentDAO {

    private final SessionFactory sessionFactory;

    public SegmentDAO() {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void addSegment(Segment segment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(segment);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Segment> getAllSegments() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Segment ", Segment.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deleteSegment(Long segment_id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Segment WHERE id = :segment_id");
            query.setParameter("segment_id",segment_id);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public Segment getSegmentById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Segment.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateSegment(Segment segment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(segment);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
