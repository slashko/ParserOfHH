package sample.entity;

import javax.persistence.*;

@Entity
public class Vacancy extends VacancyGL{

    public Vacancy() {
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "\nkey_skills='" + key_skills + '\'' +
                "\n experience_name='" + experience_name + '\'' +
                "\n employer_name='" + employer_name + '\'' +
                "\n specializations_name='" + specializations_name + '\'' +
                "\n specializations_profarea_name='" + specializations_profarea_name + '\'' +
                '}';
    }
}
