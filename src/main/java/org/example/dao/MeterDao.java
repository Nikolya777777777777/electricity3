package org.example.dao;

import org.example.model.Meter;
import java.util.List;
import java.util.Optional;

public interface MeterDao {
    Meter create(Meter entity);

    Optional<Meter> get(Long id);

    List<Meter> getAllByName(String name);
    Optional<Meter> getLastMeter();
    List<String> getAllNames();
    Optional<Meter> ShowLastResultsByName(String name);
    void remove(Meter entity);
}
