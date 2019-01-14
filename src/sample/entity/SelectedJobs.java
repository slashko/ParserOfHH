package sample.entity;

import javax.persistence.*;

@Entity
public class SelectedJobs extends VacancyGL{

    public SelectedJobs() {
    }

    public SelectedJobs(Vacancy vacancy){
        this.v_id = vacancy.getV_id();
        this.name = vacancy.getName();
        this.key_skills = vacancy.getKey_skills();
        this.experience_id = vacancy.getExperience_id();
        this.experience_name = vacancy.getExperience_name();
        this.salary_from = vacancy.getSalary_from();
        this.salary_to = vacancy.getSalary_to();
        this.published_at = vacancy.getPublished_at();
        this.employer_name = vacancy.getEmployer_name();
        this.specializations_id = vacancy.getSpecializations_id();
        this.specializations_name = vacancy.getSpecializations_name();
        this.specializations_profarea_id = vacancy.getSpecializations_profarea_id();
        this.specializations_profarea_name = vacancy.getSpecializations_profarea_name();
        this.url = vacancy.getUrl();
        this.dateParse = vacancy.getDateParse();
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
