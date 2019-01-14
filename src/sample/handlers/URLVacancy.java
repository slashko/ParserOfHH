package sample.handlers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import sample.configuration.QueriesToDataBase;
import sample.entity.IdVacancy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class URLVacancy extends Thread {
    private Integer page = 0;
    private Integer countQ = 0;
    private Long found;
    private LocalDateTime LDTLust = null;
    private long t;
    public static QueriesToDataBase queriesBD = new QueriesToDataBase();

    public void run() {
        Parser parser = new Parser();
        while (true) {
            t = System.currentTimeMillis();
            LDTLust = LocalDateTime.now().minusMinutes(30);
            parser.setLDT(++countQ);
            System.out.println(LDTLust);
            getVacancyOfHH(LDTLust);
            parser.run();
            try {
                Thread.sleep(1799900 - (System.currentTimeMillis() - t));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void getVacancyOfHH(LocalDateTime LDT) {
        try {
            URL url = new URL("https://api.hh.ru/vacancies?&area=1&date_from=" + LDT + "&per_page=100&page=" + page);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            int code = connection.getResponseCode();
//            System.out.print("Полученный код запроса:" + code);
            if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    setIdVacancyInBD(line);
                }
                reader.close();
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (((page + 1) * 100) < found) {
            page++;
            getVacancyOfHH(LDT);
        } else {
            page = 0;
            return;
        }
    }

    private void setIdVacancyInBD(String line) {
        try {
            JSONObject org = (JSONObject) JSONValue.parseWithException(line);
            Long foundL = (Long) org.get("found");
            System.out.println((page + 1) + ":" + foundL);
            if (foundL == 0) {
                found = foundL;
                return;
            }
            found = foundL;
            JSONArray vacancies = (JSONArray) org.get("items");
            IdVacancy idVacancy = new IdVacancy();
            JSONObject jso;
            for (int i = 0; i < vacancies.size(); i++) {
                jso = (JSONObject) vacancies.get(i);
                idVacancy.setIdVacansy((String) jso.get("url"));
                idVacancy.setLDT(countQ);
                idVacancy.setDate(LocalDate.now());
                queriesBD.addEntity(idVacancy);
            }
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }
}
