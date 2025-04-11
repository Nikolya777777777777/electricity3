package org.example.service;

import org.example.model.Meter;
import java.util.List;
import java.util.Optional;

public interface MeterService {
    Meter create(Meter entity);
    Meter get(Long id);
    Meter getLastMeter();
    List<Meter> getAllByName(String name);
    List<String> getAllNames();
    Meter ShowLastResultsByName(String name);
    void remove(Meter entity);
}
