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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Honda",645)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru",new Car("Opel", 755)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Dog",777)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",new Car("VAZ",999)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }
      User caruser = userService.getUserCar("Honda",645);
      System.out.println(caruser);

      context.close();
   }
}
