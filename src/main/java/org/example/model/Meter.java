package org.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "meters")
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meter_id")
    private Long meterId;
    private String meterName;
    private LocalDateTime date;
    private float lastDayReading;
    private float lastNightReading;
    public Meter() {
    }

    public Meter(String meterName, int lastDayReading, int lastNightReading) {
        this.meterName = meterName;
        this.lastDayReading = lastDayReading;
        this.lastNightReading = lastNightReading;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }


    public float getLastDayReading() {
        return lastDayReading;
    }

    public void setLastDayReading(float lastDayReading) {
        this.lastDayReading = lastDayReading;
    }

    public float getLastNightReading() {
        return lastNightReading;
    }

    public void setLastNightReading(float lastNightReading) {
        this.lastNightReading = lastNightReading;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Meter{"
                + "meterId=" + meterId
                + ", meterName='" + meterName + '\''
                + ", date=" + date
                + ", lastDayReading=" + lastDayReading
                + ", lastNightReading=" + lastNightReading
                + '}';
    }
}
