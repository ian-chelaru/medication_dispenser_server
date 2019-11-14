package dao.hibernate;

import dao.hibernate.util.HibernateUtil;
import entities.Medication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateMedicationDao
{
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Medication findById(int id)
    {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        Medication medication = currentSession.get(Medication.class,id);
        transaction.commit();
        currentSession.close();
        return medication;
    }
}
