package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "meters")
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meter_id")
    private int meterId;
    @Column(name = "meter_name", nullable = false)
    private String meterName;
    @Column(name = "last_day_reading", nullable = false)
    private int lastDayReading;
    @Column(name = "last_night_reading", nullable = false)
    private int lastNightReading;
    public Meter() {
    }

    public Meter(String meterName, int lastDayReading, int lastNightReading) {
        this.meterName = meterName;
        this.lastDayReading = lastDayReading;
        this.lastNightReading = lastNightReading;
    }

    public int getMeterId() {
        return meterId;
    }

    public void setMeterId(int meterId) {
        this.meterId = meterId;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }


    public int getLastDayReading() {
        return lastDayReading;
    }

    public void setLastDayReading(int lastDayReading) {
        this.lastDayReading = lastDayReading;
    }

    public int getLastNightReading() {
        return lastNightReading;
    }

    public void setLastNightReading(int lastNightReading) {
        this.lastNightReading = lastNightReading;
    }

    @Override
    public String toString() {
        return "Meter{" +
                "meterId='" + meterId + '\'' +
                ", lastDayReading=" + lastDayReading +
                ", lastNightReading=" + lastNightReading +
                '}';
    }
}
