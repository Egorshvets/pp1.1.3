package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Potemkin", (byte) 20);
        userService.saveUser("Mariya", "Aleshina", (byte) 33);
        userService.saveUser("Arnold", "Shwarcneger", (byte) 70);
        userService.saveUser("Anfisa", "Aslanovna" , (byte) 25);
        List<User> users = userService.getAllUsers();
        for(User user : users) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
        userService.closeConnection();
    }
}
