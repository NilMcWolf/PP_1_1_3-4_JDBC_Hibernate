package jdbc.dao;



import jdbc.model.User;
import jdbc.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;


@Slf4j
public class UserDaoHibernateImpl extends Util implements UserDao {
    public UserDaoHibernateImpl() {

    }

    /**todo: Необходимо отлавливать ошибки в рантайме в случае, если при выполнение транзакций вылетят ошибки, чтобы откатить
     *  изменения и прологировать из-за чего это произошло
     */



    @Override
    public void createUsersTable() {
        Transaction transaction;
        try(Session session = Util.getSf().openSession()) {
            transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS useri " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            log.warn("Исключение " + ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction;
        try(Session session = Util.getSf().openSession()) {
            transaction = session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS useri";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            log.warn("Исключени " + ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction;
        try(Session session = Util.getSf().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception ex) {
            log.warn("Исключение " + ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction;
        try(Session session = Util.getSf().openSession()) {
            String hql = "DELETE User WHERE id = :lg";
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("lg", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            log.warn("Исключение " + ex);
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = Util.getSf().openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (Exception ex) {
            log.warn("Исключение " + ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction;
        try(Session session = Util.getSf().openSession()) {
            transaction = session.beginTransaction();
            List<User> buff = getAllUsers();
            for (User user : buff) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception ex) {
            log.warn("Исключение " + ex);
            ex.printStackTrace();
        }
    }
}
