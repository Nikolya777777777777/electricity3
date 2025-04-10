package org.example.service.impl;

import org.example.dao.MeterDao;
import org.example.dao.impl.MeterDaoImpl;
import org.example.model.Meter;
import org.example.service.MeterService;
import org.hibernate.SessionFactory;
import org.example.util.HibernateUtil;
import java.util.List;

public class MeterServiceImpl implements MeterService {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final MeterDao meterDao = new MeterDaoImpl(sessionFactory);
    @Override
    public Meter create(Meter entity) {
        return meterDao.create(entity);
    }

    @Override
    public Meter get(Long id) {
        return meterDao.get(id);
    }

    @Override
    public Meter getLastMeter() {
        return meterDao.getLastMeter();
    }
    @Override
    public List<String> getAllNames() {
        return meterDao.getAllNames();
    }

    @Override
    public Meter ShowLastResultsByName(String name) {
        return meterDao.ShowLastResultsByName(name);
    }

    @Override
    public List<Meter> getAllByName(String name) {
        return meterDao.getAllByName(name);
    }

    @Override
    public void remove(Meter entity) {
        meterDao.remove(entity);
    }
}
