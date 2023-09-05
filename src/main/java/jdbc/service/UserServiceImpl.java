package jdbc.service;



import jdbc.dao.UserDaoHibernateImpl;
import jdbc.dao.UserDaoJDBCImpl;
import jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl odin = new UserDaoJDBCImpl();
    UserDaoHibernateImpl dva = new UserDaoHibernateImpl();
    public void createUsersTable() {
        dva.createUsersTable();
    }

    public void dropUsersTable() {
        dva.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        dva.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        dva.removeUserById(id);
    }

    public List<User> getAllUsers() {

        return dva.getAllUsers();
    }

    public void cleanUsersTable() {
        dva.cleanUsersTable();
    }
}
