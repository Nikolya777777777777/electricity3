package org.example;

import org.example.model.Meter;
import org.example.model.MeterHistory;
import org.hibernate.SessionFactory;
import org.example.service.MeterHistoryService;
import org.example.service.impl.MeterHistoryServiceImpl;
import org.example.service.MeterService;
import org.example.service.impl.MeterServiceImpl;
import org.example.util.HibernateUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MeterApp {
    private static final int DEFAULT_KILO_PER_DAY = 100;
    private static final int DEFAULT_KILO_PER_NIGHT = 80;
    private static int amountOfTransaction = 1;
    private static final Map<Integer, Meter> history = new <Integer, Meter>HashMap();
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        MeterService meterService = new MeterServiceImpl();
        Meter meter = new Meter("123", 100, 100);
        MeterHistory meterHistory = new MeterHistory(meter);
        MeterHistoryService historyService = new MeterHistoryServiceImpl();
        meterService.create(meter);
        historyService.create(meter);
        history.put(amountOfTransaction, meter);
        amountOfTransaction++;
        boolean exit = false;
        int choice = 0;
        float tarifNaDzien = 0;
        float tarifNaNoch = 0;
        int usedPerDay = 0;
        int usedPerNight = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Вибери варіант");
            System.out.println("1.Ввести нові тарифи на день та ніч");
            System.out.println("2.Отримати актуальні показники лічильника");
            System.out.println("3.Отримати історію показників лічильника");
            System.out.println("4.Ввести зміни в показниках лічильника");
            System.out.println("5.Вийти з програми");
            choice = scanner.nextInt();
            if (choice == 1) {
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
            } else if (choice == 2) {
                System.out.println(meterService.get(Long.getLong(String.valueOf(amountOfTransaction))));
            } else if (choice == 3) {
                System.out.println(historyService.getAll());
            } else if (choice == 4) {
                System.out.println("Введіть зміни в показниках лічильника");
                try{
                    System.out.println("Спожито квт за день: ");
                    usedPerDay = scanner.nextInt();
                    System.out.println("Спожито квт за ніч: ");
                    usedPerNight = scanner.nextInt();
                    Meter newMeter = new Meter();
                    meter.setLastDayReading(usedPerDay);
                    meter.setLastNightReading(usedPerNight);
                    meterService.create(meter);
                    historyService.create(meter);
                    history.put(amountOfTransaction, meter);
                    amountOfTransaction++;
                } catch (RuntimeException e) {
                    System.out.println("Ви ввели некоректне значення, спробуйте знову");
                    choice = 4;
                }
            } else if (choice == 5) {
                exit = true;
            } else {
                System.out.println("Ви ввели некоректне значення спробуйте знову, введіть число від 1 до 5");
                exit = false;
            }
        } while (exit != true); {
            System.out.print("Введіть номер лічильника: ");
            String meterId = scanner.nextLine();

            System.out.print("Введіть денний тариф: ");
            int day = scanner.nextInt();

            System.out.print("Введіть нічний тариф: ");
            int night = scanner.nextInt();
            scanner.nextLine();
        }
    }
}