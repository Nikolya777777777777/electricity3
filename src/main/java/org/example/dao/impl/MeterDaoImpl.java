package org.example.dao.impl;

import org.example.dao.MeterDao;
import org.example.model.Meter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class MeterDaoImpl extends AbstractDao implements MeterDao {
    private Meter lastMeter = new Meter();

    public MeterDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Meter create(Meter entity) {
        lastMeter = entity;
        lastMeter.setMeterId(entity.getMeterId());
        lastMeter.setMeterName(entity.getMeterName());
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can not create Meter: " + entity);
        } finally {
            if (session != null) {
                session.close();
            }
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
    public List<Meter> getAllByName(String name) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Meter m where m.name = :name", Meter.class)
                    .setParameter("name", name).list();
        } catch (RuntimeException e) {
            throw new RuntimeException("Can not get all Meter history");
        }
    }

    @Override
    public Meter getLastMeter() {
        return lastMeter;
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

    @Override
    public List<String> getAllNames() {
        try (Session session = factory.openSession()) {
            return session.createQuery("SELECT DISTINCT m.meterName FROM Meter m", String.class).list();
        } catch (RuntimeException e) {
            throw new RuntimeException("Can not get all meters names history");
        }
    }

    @Override
    public Meter ShowLastResultsByName(String name) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Meter m WHERE m.meterName = :name ORDER BY m.date DESC", Meter.class)
                    .setParameter("name", name)
                    .setMaxResults(1)
                    .uniqueResult();
        } catch (RuntimeException e) {
            throw new RuntimeException("Can not get last meter's result");
        }
    }
}
