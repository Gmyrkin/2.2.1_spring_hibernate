package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserCar(String model, int series) {
      Session session = sessionFactory.openSession();
      String table = "FROM User WHERE car.model = :model and car.series = :series";
      Query query = session.createQuery(table);
      query.setParameter("model", model);
      query.setParameter("series", series);
      User user = (User) query.getSingleResult();
      session.close();
      return user;
   }


}
