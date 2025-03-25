package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "meter_history")
public class MeterHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @Column(name = "meter_id")
    private int meterId;

    @ManyToOne
    @Column(name = "meter_name", nullable = false)
    private String meterName;
    @Column(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date;
    @Column(name = "day_reading", nullable = false)
    private int dayReading;
    @Column(name = "night_reading", nullable = false)
    private int nightReading;

    public MeterHistory() {
    }

    public MeterHistory(Meter meter) {
        this.meterId = meter.getMeterId();
        this.dayReading = meter.getLastDayReading();
        this.nightReading = meter.getLastNightReading();
    }

    public MeterHistory(Long id, int meterId, LocalDateTime date, int dayReading, int nightReading, String meterName) {
        this.id = id;
        this.meterId = meterId;
        this.meterName = meterName;
        this.date = date;
        this.dayReading = dayReading;
        this.nightReading = nightReading;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setMeterName(String meterId) {
        this.meterName = meterName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getDayReading() {
        return dayReading;
    }

    public void setDayReading(int dayReading) {
        this.dayReading = dayReading;
    }

    public int getNightReading() {
        return nightReading;
    }

    public void setNightReading(int nightReading) {
        this.nightReading = nightReading;
    }
}
