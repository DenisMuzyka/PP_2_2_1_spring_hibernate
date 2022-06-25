package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      userService.add(new User("User1", "Lastname1", "user1@mail.ru"), new Car("audi", 3));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"), new Car("bmw", 5));


      // пользователи с авто
      List<User> users = userService.listUsers();
      for (User user : users) {
         if (user.getCar() != null) {
            System.out.println("Id = "+user.getId());
            System.out.println("First Name = "+user.getFirstName());
            System.out.println("Last Name = "+user.getLastName());
            System.out.println("Email = "+user.getEmail());
            System.out.println("Car = "+user.getCar());
            System.out.println();
         }
      }

      // пользователь, выбранный по модели и серии машины
      userService.getUserFromParam("audi", 3);
      context.close();
   }
}
