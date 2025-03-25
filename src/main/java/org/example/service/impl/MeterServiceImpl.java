package org.example.service.impl;

import org.example.dao.MeterDao;
import org.example.dao.impl.MeterDaoImpl;
import org.example.model.Meter;
import org.example.service.MeterService;
import org.hibernate.SessionFactory;
import org.example.util.HibernateUtil;

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
    public void remove(Meter entity) {
        meterDao.remove(entity);
    }
}
