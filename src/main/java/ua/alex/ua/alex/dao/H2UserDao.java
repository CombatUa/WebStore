package ua.alex.ua.alex.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class H2UserDao implements UserDao {
    private static final String SELECT_ALL_USERS = "SELECT ID  , FIRST_NAME , LAST_NAME , SALARY , DATE_OF_BIRTH  FROM USERS";
    private static final String INSERT_USER = "INSERT INTO USERS (ID, FIRST_NAME, LAST_NAME, SALARY, DATE_OF_BIRTH) VALUES (?,?, ?, ?, ?)";


    @Override
    public long createUser(String firstName, String lastName, double salary, LocalDate dateOfBirth) {
        long id = (new Random()).nextLong();
        try (PreparedStatement preparedStatement = H2DaoFactory.createConnection().prepareStatement(INSERT_USER);) {

            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setDouble(4, salary);
            preparedStatement.setDate(5, Date.valueOf(dateOfBirth));
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

    }

    @Override
    public boolean updateUser(long userId, String firstName, String lastName, double Salary, LocalDate dateOfBirth) {
        return false;
    }

    @Override
    public boolean deleteUser(long userId) {
        return false;
    }

    @Override
    public User selectUser(long userId) {
        return null;
    }

    @Override
    public User findUser(String firstName, String lastName, double Salary, LocalDate dateOfBirth) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = H2DaoFactory.createConnection().prepareStatement(SELECT_ALL_USERS)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDouble("salary"),
                        resultSet.getDate("date_of_birth").toLocalDate());
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
