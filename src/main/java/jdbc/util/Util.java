package jdbc.util;

import jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

//import javax.security.auth.login.Configuration;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/kata";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "roott";
    private Connection con;

    public Util() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            e.
                    printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }

    // отсюда для гипернета
    private static SessionFactory sf;
    public static SessionFactory getSf() {
        if (sf == null) {
            try {
                Configuration config = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/kata");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "roott");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "");

                config.setProperties(settings);

                config.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(config.getProperties()).build();

                sf = config.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        return sf;
        }
    }




