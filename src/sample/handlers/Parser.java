package sample.handlers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import sample.entity.SelectedJobs;
import sample.entity.Vacancy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

public class Parser extends Thread {
    private LocalDate time;
    private Integer countQ;
    private List<String> idVacancys;
    private static HashSet<String> list_id = new HashSet<>();
    private static final List<String> listSpec = URLVacancy.queriesBD.getSpecialization();

    public void run() {
        idVacancys = URLVacancy.queriesBD.getIdVacancy(countQ);
        time = LocalDate.now();
        for (int i = 0; i < idVacancys.size(); i++) {
            try {
                URL url = new URL(idVacancys.get(i));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setUseCaches(false);
                int code = connection.getResponseCode();
                if (code == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream(), "utf8"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        setVacancyInBD(line, idVacancys.get(i));
                    }
                    reader.close();
                }
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        idVacancys = null;
    }

    private void setVacancyInBD(String str, String url) {
        Vacancy vacancy = new Vacancy();
        JSONObject jVacancy = null;
        try {
            jVacancy = (JSONObject) JSONValue.parseWithException(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONArray specializations;
        JSONObject specialization;

        specializations = (JSONArray) jVacancy.get("specializations");
        if (specializations != null) {
            String id = "";
            String name = "";
            String profarea_id = "";
            String profarea_name = "";
            for (int i = 0; i < specializations.size(); i++) {

                specialization = (JSONObject) specializations.get(i);
                if (i != specializations.size() - 1) {
                    id = id + specialization.get("id") + "; ";
                } else {
                    id = id + specialization.get("id");
                }
                if (i != specializations.size() - 1) {
                    name = name + specialization.get("name") + "; ";
                } else {
                    name = name + specialization.get("name");
                }
                if (i != specializations.size() - 1) {
                    profarea_id = profarea_id + specialization.get("profarea_id") + "; ";
                } else {
                    profarea_id = profarea_id + specialization.get("profarea_id");
                }
                if (i != specializations.size() - 1) {
                    profarea_name = profarea_name + specialization.get("profarea_name") + "; ";
                } else {
                    profarea_name = profarea_name + specialization.get("profarea_name");
                }
            }
            vacancy.setSpecializations_id(id);
            vacancy.setSpecializations_name(name);
            vacancy.setSpecializations_profarea_id(profarea_id);
            vacancy.setSpecializations_profarea_name(profarea_name);
        }

        JSONArray aKey_skills;
        JSONObject jKey_skills;
        JSONObject experience;
        JSONObject salary;
        JSONObject employer;
        String strKey_skills = "";

        vacancy.setV_id((String) jVacancy.get("id"));

        aKey_skills = (JSONArray) jVacancy.get("key_skills");
        if (strKey_skills != null) {
            for (int i = 0; i < aKey_skills.size(); i++) {
                jKey_skills = (JSONObject) aKey_skills.get(i);
                if (i != aKey_skills.size() - 1) {
                    strKey_skills = strKey_skills + jKey_skills.get("name") + "; ";
                } else {
                    strKey_skills = strKey_skills + jKey_skills.get("name");
                }
            }
            vacancy.setKey_skills(strKey_skills);
        }

        experience = (JSONObject) jVacancy.get("experience");
        if (experience != null) {
            vacancy.setExperience_id((String) experience.get("id"));
            vacancy.setExperience_name((String) experience.get("name"));
        }

        salary = (JSONObject) jVacancy.get("salary");
        if (salary != null) {
            vacancy.setSalary_from((Long) salary.get("from"));
            vacancy.setSalary_to((Long) salary.get("to"));
        }

        vacancy.setName((String) jVacancy.get("name"));

        vacancy.setPublished_at((String) jVacancy.get("published_at"));

        employer = (JSONObject) jVacancy.get("employer");
        if (employer != null) {
            vacancy.setEmployer_name((String) employer.get("name"));
        }

        vacancy.setUrl(url);

        vacancy.setDateParse(time);

        if (!list_id.contains(jVacancy.get("id"))) {
            if (checkSpecialization(specializations)) {
                SelectedJobs sJobs = new SelectedJobs(vacancy);
                URLVacancy.queriesBD.addEntity(sJobs);
                list_id.add((String) jVacancy.get("id"));
            }
        }
        URLVacancy.queriesBD.addEntity(vacancy);
    }

    private boolean checkSpecialization(JSONArray listSpecialization) {
        JSONObject spec;
        for (int i = 0; i < listSpecialization.size(); i++) {
            spec = (JSONObject) listSpecialization.get(i);
            if (listSpec.contains(spec.get("id"))) {
                return true;
            }
        }
        return false;
    }

    public void setLDT(Integer LDT) {
        this.countQ = LDT;
    }
}
