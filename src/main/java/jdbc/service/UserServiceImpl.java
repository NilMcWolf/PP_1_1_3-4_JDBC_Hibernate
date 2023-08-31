package jdbc.service;



import jdbc.dao.UserDaoJDBCImpl;
import jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl odin = new UserDaoJDBCImpl();
    public void createUsersTable() {
        odin.createUsersTable();
    }

    public void dropUsersTable() {
        odin.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        odin.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        odin.removeUserById(id);
    }

    public List<User> getAllUsers() {

        return odin.getAllUsers();
    }

    public void cleanUsersTable() {
        odin.cleanUsersTable();
    }
}
