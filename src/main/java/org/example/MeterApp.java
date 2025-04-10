package org.example;

import org.example.dao.exception.DataProcessingException;
import org.example.model.Meter;
import org.example.service.MeterService;
import org.example.service.impl.MeterServiceImpl;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MeterApp {
    private static final int DEFAULT_KILO_PER_DAY = 100;
    private static final int DEFAULT_KILO_PER_NIGHT = 80;
    private static float tarifNaDzien = 3f;
    private static float tarifNaNoch = 1.5f;
    private static int amountOfTransaction = 1;
    private static boolean exit = false;
    private static float usedPerDay = 0;
    private static float usedPerNight = 0;

    private static int choice = 0;
    private static final Map<Integer, Meter> history = new <Integer, Meter>HashMap();
    private static final MeterService meterService = new MeterServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        do {
            System.out.println("Меню");
            System.out.println("1.Ввести нові тарифи на день та ніч");
            System.out.println("2.Отримати актуальні показники лічильника");
            System.out.println("3.Отримати історію показників лічильника");
            System.out.println("4.Ввести зміни в показниках лічильника");
            System.out.println("5.Розрахувати скільки потрібно заплатити згідно останніх змін лічильника");
            System.out.println("6.Створити новий лічильник");
            System.out.println("7.Вийти з програми");
            System.out.println("Вибери, номер який хочеш використати: ");
            choice = scanner.nextInt();
            if (choice == 1) {
                updateTariffs();
            } else if (choice == 2) {
                showLatestMeterReading();
            } else if (choice == 3) {
                showHistory();
            } else if (choice == 4) {
                updateMeterReadings();
            } else if (choice == 5) {
                calculateHowMuchToPay();
            } else if (choice == 6) {
                createNewMeter();
            } else if (choice == 7) {
                exit = true;
            }else {
                System.out.println("Ви ввели некоректне значення спробуйте знову, введіть число від 1 до 5");
                exit = false;
            }
        } while (!exit);
    }
    private static void updateTariffs() {
        System.out.println("Введіть нові тарифи на день та ніч");
        try {
            System.out.println("Новий тариф на день: ");
            tarifNaDzien = scanner.nextFloat();
            System.out.println("Новий тариф на ніч: ");
            tarifNaNoch = scanner.nextFloat();
        } catch (RuntimeException e) {
            System.out.println("Ви ввели некоректне значення, спробуйте знову");
            choice = 1;
        }
    }
    private static void showLatestMeterReading() {
        try {
            System.out.println("У тебе є такі лічильники: " + meterService.getAllNames() +
                    "\nВведіть ім'я лічильника, для якого хочете отримати актуальні показники: ");
            String name = scanner.next();
            System.out.println(meterService.ShowLastResultsByName(name));
        } catch (Exception e) {
            throw new DataProcessingException("Не можемо отримати всі імена з таблиці meters", e);
        }
    }
    private static void showHistory() {
        try {
            System.out.println("У тебе є такі лічильники: " + meterService.getAllNames() +
                    "\nВведіть ім'я лічильника, для якого хочете отримати історію всіх показників: ");
            String name = scanner.next();
            System.out.println(meterService.getAllByName(name));
        } catch (Exception e) {
            choice = 3;
            throw new DataProcessingException("Ви ввели некоректне значення таблиці, спробуйте ще раз", e);
        }
    }
    private static void updateMeterReadings() {
        System.out.println("Введіть зміни в показниках лічильника");
        try {
            LocalDateTime newTime = LocalDateTime.now();
            System.out.println("Спожито квт за день: ");
            usedPerDay = scanner.nextFloat();
            System.out.println("Спожито квт за ніч: ");
            usedPerNight = scanner.nextFloat();
            if (amountOfTransaction - 1 >= 0) {
                Meter newMeter = new Meter();
                newMeter.setLastDayReading(meterService.getLastMeter().getLastDayReading() + usedPerDay);
                newMeter.setLastNightReading(meterService.getLastMeter().getLastNightReading() + usedPerNight);
                newMeter.setDate(newTime);
                meterService.create(newMeter);
                history.put(amountOfTransaction, newMeter);
                amountOfTransaction++;
            } else {
                System.out.println("Спочатку створіть лічильник для оновлення даних у ньому.");
            }
        } catch (RuntimeException e) {
            System.out.println("Ви ввели некоректне значення, спробуйте знову.");
            choice = 4;
        }
    }
    private static void defaultMeter() {
        LocalDateTime now = LocalDateTime.now();
        Meter meter = new Meter("123", 100, 100);
        meter.setDate(now);
        meterService.create(meter);
        history.put(amountOfTransaction, meter);
        amountOfTransaction++;
    }

    private static void calculateHowMuchToPay() {
        float finalSum = 0;
        int firstId = amountOfTransaction - 1;
        int secondId = amountOfTransaction - 2;
        if (firstId < 0) {
            System.out.println("У тебе немає записів лічильника, добав спочатку записи, ти маєш заплатити: " + finalSum + " грн");
        }
        if (secondId == 0) {
            float firstDayKw = meterService.get(Long.parseLong(String.valueOf(firstId))).getLastDayReading();
            float firstNightKw = meterService.get(Long.parseLong(String.valueOf(firstId))).getLastNightReading();
            finalSum = firstDayKw * tarifNaDzien + firstNightKw * tarifNaNoch;
            System.out.println("Ти маєш заплатити: " + finalSum + " грн");
        }
        float firstDayKw = meterService.get(Long.parseLong(String.valueOf(firstId))).getLastDayReading();
        float firstNightKw = meterService.get(Long.parseLong(String.valueOf(firstId))).getLastNightReading();
        float secondDayKw = meterService.get(Long.parseLong(String.valueOf(secondId))).getLastDayReading();
        float secondNightKw = meterService.get(Long.parseLong(String.valueOf(secondId))).getLastNightReading();
        finalSum = ((secondDayKw - firstDayKw) * tarifNaDzien) + ((secondNightKw - firstNightKw) * tarifNaNoch);
        System.out.println("Ти маєш заплатити: " + finalSum + " грн");
    }

    private static void createNewMeter() {
        LocalDateTime now = LocalDateTime.now();
        try {
            Meter meter = new Meter();
            System.out.println("Введіть назву лічильника: ");
            String meterName = scanner.next();
            System.out.println("Введіть кількість кіловат спожитих за день: ");
            float dayKw = scanner.nextFloat();
            System.out.println("Введіть кількість кіловат спожитих за ніч: ");
            float nightKw = scanner.nextFloat();
            meter.setMeterName(meterName);
            meter.setDate(now);
            meter.setLastDayReading(dayKw);
            meter.setLastNightReading(nightKw);
            meterService.create(meter);
        } catch (RuntimeException e) {
            choice = 6;
            throw new RuntimeException("Ви ввели некоректне значення, спробуйте знову");
        }

    }
}