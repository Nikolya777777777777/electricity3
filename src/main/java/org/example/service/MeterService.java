package org.example.service;

import org.example.model.Meter;

public interface MeterService {
    Meter create(Meter entity);
    Meter get(Long id);
    void remove(Meter entity);
}
