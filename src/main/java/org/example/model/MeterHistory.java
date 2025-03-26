package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "meter_history")
public class MeterHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "meter_id")
    private Meter meter;
    @Column(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date;
    @Column(name = "day_reading", nullable = false)
    private int dayReading;
    @Column(name = "night_reading", nullable = false)
    private int nightReading;

    @JoinColumn(name = "meter_name")
    private String meterName;

    public MeterHistory() {
    }

    public MeterHistory(Meter meter) {
        this.meterName = meter.getMeterName();
        this.dayReading = meter.getLastDayReading();
        this.nightReading = meter.getLastNightReading();
    }

    public MeterHistory(Long id, LocalDateTime date, int dayReading, int nightReading, Meter meter) {
        this.meter = meter;
        this.id = id;
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

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
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

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }
}
