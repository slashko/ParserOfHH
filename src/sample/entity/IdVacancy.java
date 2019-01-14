package sample.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class IdVacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 200)
    private String idVacansy;
    private Integer LDT;
    private LocalDate date;

    public IdVacancy() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdVacansy() {
        return idVacansy;
    }

    public void setIdVacansy(String idVacansy) {
        this.idVacansy = idVacansy;
    }

    public Integer getLDT() {
        return LDT;
    }

    public void setLDT(Integer LDT) {
        this.LDT = LDT;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
