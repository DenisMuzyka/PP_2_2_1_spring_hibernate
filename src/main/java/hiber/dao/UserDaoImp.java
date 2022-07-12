package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user, Car car) {
      user.setCar(car);
      sessionFactory.getCurrentSession().persist(user);
   }

   @Override
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession().createQuery("FROM User",User.class).getResultList();
   }

   @Override
   public User getUserFromParam(String model, int series) {
      return sessionFactory.getCurrentSession().createQuery("FROM User u where u.car.model = :paramModel and u.car.series = :paramSeries", User.class)
              .setParameter("paramModel", model)
              .setParameter("paramSeries", series)
              .getSingleResult();

   }

}
