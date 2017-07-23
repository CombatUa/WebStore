package ua.alex.ua.alex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2DaoFactory extends DaoFactory {
    private static final String CREATE_QUERY =
            "CREATE TABLE USERS (id NUMBER , first_name VARCHAR(200), last_name VARCHAR(200), salary number, date_of_birth date)";


    public static Connection createConnection() {
        try {
            return DriverManager.getConnection("jdbc:h2:mem:store;DB_CLOSE_DELAY=-1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDao getUserDao() {
        return new H2UserDao();
    }

    @Override
    public void init() {


        Connection connection = H2DaoFactory.createConnection();
        try {
            connection.createStatement().execute(CREATE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
