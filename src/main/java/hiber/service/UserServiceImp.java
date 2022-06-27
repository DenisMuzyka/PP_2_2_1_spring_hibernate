package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Autowired
   private SessionFactory sessionFactory;

   @Transactional
   @Override
   public void add(User user, Car car) {
      userDao.add(user, car);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional
   public void getUserFromParam(String model, int series) {
      Car car = (Car) sessionFactory.getCurrentSession().createQuery("FROM Car c where c.model = :paramModel and c.series = :paramSeries")
              .setParameter("paramModel", model)
              .setParameter("paramSeries", series)
              .getSingleResult();
      System.out.println("Машина принадлежит юзеру с именем " + car.getUser().getFirstName());
   }

}
