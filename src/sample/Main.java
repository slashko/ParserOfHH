package sample;

import sample.handlers.URLVacancy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        System.out.println("Выгрзука вакансий из hh.ru!\nКоманды консоли:\n1 - зпустить выгрузку");
        System.console();
        URLVacancy URLVacancy = new URLVacancy();
        String str = new String();
        while (true) {
            try {
                str = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (str) {
                case "1":
                    System.out.println("Начало в: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
                    URLVacancy.start();
            }
        }
    }
}
