package ua.alex.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.alex.ua.alex.dao.H2DaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


class H2DaoFactoryTest {
    private static final String CREATE_QUERY =
            "CREATE TABLE EXAMPLE (GREETING VARCHAR(6), TARGET VARCHAR(6))";
    private static final String DATA_QUERY =
            "INSERT INTO EXAMPLE VALUES('Hello','World')";

    private static Connection connection;

    @BeforeEach
    void setUp() {
        H2DaoFactory daoFactory = new H2DaoFactory();
        try {
            connection = H2DaoFactory.createConnection();
            connection.createStatement().execute(CREATE_QUERY);
            connection.createStatement().execute(DATA_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void selectTest() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from example");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(String.format("%s , %s", resultSet.getString(1), resultSet.getString("TARGET")));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}

