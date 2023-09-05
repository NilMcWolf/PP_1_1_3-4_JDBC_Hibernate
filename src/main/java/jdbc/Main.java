package jdbc;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

//import com.mysql.cj.Session;
import jdbc.dao.UserDaoHibernateImpl;
import jdbc.model.User;
import jdbc.service.UserService;
import jdbc.service.UserServiceImpl;
import jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Max", "Ivanov", (byte)15);
        System.out.println("User Max добавлен");
        userService.saveUser("Max2", "Ivanov2", (byte)25);
        System.out.println("User Max2 добавлен");
        userService.saveUser("Max3", "Ivanov3", (byte)35);
        System.out.println("User Max добавлен");
        List<User> vivod = userService.getAllUsers();
        for (User user : vivod) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
