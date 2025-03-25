package org.example.service;

import org.example.model.Meter;
import java.util.List;

public interface MeterHistoryService {
    Meter create(Meter entity);

    Meter get(Long id);

    List<Meter> getAll();

    void remove(Meter entity);
}
