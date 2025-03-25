package org.example.dao;

import org.example.model.Meter;
import java.util.List;

public interface MeterHistoryDao {
    Meter create(Meter entity);

    Meter get(Long id);

    List<Meter> getAll();

    void remove(Meter entity);
}
