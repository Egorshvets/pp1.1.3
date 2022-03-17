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
            System.out.println(user);         //autoCommit отключается в конструкторе userDaoJDBC
        }                                     //в Util только создается соединение
        userService.cleanUsersTable();        //commit вызывается в конце запроса в каждом из методов, работающих с БД
        userService.dropUsersTable();         //теперь UserServiceImpl extends UserDaoJDBC; UserDaoJDBC implements UserDao, UserService
        userService.closeConnection();
    }
}
