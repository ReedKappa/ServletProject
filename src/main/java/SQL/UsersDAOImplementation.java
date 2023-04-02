package SQL;

import model.UserProfile;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class UsersDAOImplementation implements UsersDAO {
    @Override
    public UserProfile get(long id) {
        return (UserProfile) HibernateSessionFactory.getSessionFactory().openSession().get(UserProfile.class, id);
    }

    @Override
    public List<UserProfile> getAll() {
        try {
            return HibernateSessionFactory.getSessionFactory().openSession().createCriteria(UserProfile.class).list();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void add(UserProfile dataSet) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tr1 = session.beginTransaction();
        session.save(dataSet);
        tr1.commit();
        session.close();
    }
}
