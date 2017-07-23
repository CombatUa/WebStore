package ua.alex.ua.alex.dao;

import org.eclipse.jetty.server.Authentication;

import java.time.LocalDate;
import java.util.List;

public interface UserDao {
    long createUser(String firstName, String lastName, double Salary, LocalDate dateOfBirth);

    boolean updateUser(long userId, String firstName, String lastName, double Salary, LocalDate dateOfBirth);

    boolean deleteUser(long userId);

    User selectUser(long userId);

    User findUser(String firstName, String lastName, double Salary, LocalDate dateOfBirth);

    List<User> getAllUsers();

}
