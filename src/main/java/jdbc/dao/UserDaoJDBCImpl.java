package jdbc.dao;



import jdbc.model.User;
import jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    private final Connection con = getCon();
    public UserDaoJDBCImpl() {

    }

    //todo: Если отхватили исключение, то необходимо прологировать

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS USERI (\n" +
                "  ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                "  NAME VARCHAR(45) NULL,\n" +
                "  LASTNAME VARCHAR(45) NULL,\n" +
                "  AGE REAL NOT NULL);";
        try (Statement stm = con.createStatement()) {
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS USERI";
        try (Statement stm = con.createStatement()) {
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age)  {

        String sql = "INSERT INTO USERI (NAME, LASTNAME, AGE) VALUES(?, ?, ?)";
        try (PreparedStatement prs = con.prepareStatement(sql)){
            prs.setString(1, name);
            prs.setString(2, lastName);
            prs.setInt(3, age);
            prs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM USERI WHERE ID =?";
        try (PreparedStatement prs = con.prepareStatement(sql)){
            prs.setInt(1, (int)id);
            prs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> itog = new ArrayList<>();
        String sql = "SELECT * FROM USERI";
        try (Statement stm = con.createStatement()) {
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("ID"));
                user.setName(rs.getString("NAME"));
                user.setLastName(rs.getString("LASTNAME"));
                user.setAge(rs.getByte("AGE"));
                itog.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itog;
    }

        public void cleanUsersTable() {
        String sql = "DELETE FROM USERI";
        try (Statement stm = con.createStatement()) {
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
