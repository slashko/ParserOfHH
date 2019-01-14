package sample.configuration;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QueriesToDataBase<T> {
    public void addEntity(T entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public List<String> getIdVacancy(Integer countQ) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT idVacansy FROM IdVacancy WHERE LDT =:countQ");
        query.setParameter("countQ", countQ);
        List<String> idVacancies = query.list();
        session.close();
        return idVacancies;
    }

    public List<String> getSpecialization() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT id FROM Specialization");
        List<String> specializations = query.list();
        session.close();
        return specializations;
    }

    public Long getCountVacancy() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT count(*) FROM Vacancy");
        Long count = (Long) query.getSingleResult();
        session.close();
        return count;
    }
}
