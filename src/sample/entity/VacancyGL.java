package sample.entity;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public abstract class VacancyGL {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    protected String v_id;
    protected String name;
    @Column(length = 1000)
    protected String key_skills;
    protected String experience_id;
    @Column(length = 500)
    protected String experience_name;
    protected Long salary_from;
    protected Long salary_to;
    protected String published_at;
    protected String employer_name;
    protected String specializations_id;
    @Column(length = 500)
    protected String specializations_name;
    protected String specializations_profarea_id;
    @Column(length = 500)
    protected String specializations_profarea_name;
    protected String url;
    protected LocalDate dateParse;

    public VacancyGL() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getV_id() {
        return v_id;
    }

    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey_skills() {
        return key_skills;
    }

    public void setKey_skills(String key_skills) {
        this.key_skills = key_skills;
    }

    public String getExperience_id() {
        return experience_id;
    }

    public void setExperience_id(String experience_id) {
        this.experience_id = experience_id;
    }

    public String getExperience_name() {
        return experience_name;
    }

    public void setExperience_name(String experience_name) {
        this.experience_name = experience_name;
    }

    public Long getSalary_from() {
        return salary_from;
    }

    public void setSalary_from(Long salary_from) {
        this.salary_from = salary_from;
    }

    public Long getSalary_to() {
        return salary_to;
    }

    public void setSalary_to(Long salary_to) {
        this.salary_to = salary_to;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getEmployer_name() {
        return employer_name;
    }

    public void setEmployer_name(String employer_name) {
        this.employer_name = employer_name;
    }

    public String getSpecializations_id() {
        return specializations_id;
    }

    public void setSpecializations_id(String specializations_id) {
        this.specializations_id = specializations_id;
    }

    public String getSpecializations_name() {
        return specializations_name;
    }

    public void setSpecializations_name(String specializations_name) {
        this.specializations_name = specializations_name;
    }

    public String getSpecializations_profarea_id() {
        return specializations_profarea_id;
    }

    public void setSpecializations_profarea_id(String specializations_profarea_id) {
        this.specializations_profarea_id = specializations_profarea_id;
    }

    public String getSpecializations_profarea_name() {
        return specializations_profarea_name;
    }

    public void setSpecializations_profarea_name(String specializations_profarea_name) {
        this.specializations_profarea_name = specializations_profarea_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getDateParse() {
        return dateParse;
    }

    public void setDateParse(LocalDate dateParse) {
        this.dateParse = dateParse;
    }
}
