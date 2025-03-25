package org.example.dao.impl;

import org.example.dao.MeterDao;
import org.example.model.Meter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class MeterDaoImpl extends AbstractDao implements MeterDao {

    public MeterDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Meter create(Meter entity) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can not create Meter: " + entity);
        }
        return entity;
    }

    @Override
    public Meter get(Long id) {
        try (Session session = factory.openSession()) {
            return session.get(Meter.class, id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Can not get Meter with id: " + id);
        }
    }

    @Override
    public void remove(Meter entity) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can not remove Meter: " + entity);
        }
    }
}
