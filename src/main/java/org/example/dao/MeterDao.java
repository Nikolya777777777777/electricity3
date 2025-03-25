package org.example.dao;

import org.example.model.Meter;

public interface MeterDao {
    Meter create(Meter entity);

    Meter get(Long id);
    void remove(Meter entity);
}
