package org.example.dao;

import org.example.model.Meter;
import java.util.List;

public interface MeterDao {
    Meter create(Meter entity);

    Meter get(Long id);

    List<Meter> getAllByName(String name);
    Meter getLastMeter();
    List<String> getAllNames();
    Meter ShowLastResultsByName(String name);
    void remove(Meter entity);
}
