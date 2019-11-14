package dao.hibernate;

import dao.hibernate.util.HibernateUtil;
import entities.Prescription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class HibernatePrescriptionDao
{
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Prescription> findByDate(Date date)
    {
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery("from Prescription as p where ?1 between p.startDate and p.endDate");
        query.setParameter(1,date);
        List<Prescription> prescriptionList = query.getResultList();
        currentSession.close();
        return prescriptionList;
    }
}
