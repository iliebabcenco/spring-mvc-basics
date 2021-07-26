package org.ilie.springcourse.dao;

import org.ilie.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "456321";

    private static Connection connection;



    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
      try {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }



    public List<Person> index() {
      List<Person> people = new ArrayList<>();
      try {
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM person";
        statement.executeQuery(sql);
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
          Person person = new Person();
          person.setId(rs.getInt("id"));
          person.setName(rs.getString("name"));
          person.setId(rs.getInt("age"));
          person.setEmail(rs.getString("email"));
          people.add(person);
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
      return people;
    }

    public Person show (int id) {
      String sql = "SELECT * FROM Person WHERE id=?;";
      Person person = new Person();
      try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        person.setId(rs.getInt("id"));
        person.setName(rs.getString("name"));
        person.setAge(rs.getInt("age"));
        person.setEmail(rs.getString("email"));

      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
      return person;
    }

    public void save(Person person) {

      try {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO person VALUES (1, ?, ?, ?)");

        preparedStatement.setString(1, person.getName());
        preparedStatement.setInt(2, person.getAge());
        preparedStatement.setString(3, person.getEmail());
        preparedStatement.executeUpdate();

      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }

    public void update(int id, Person updatedPerson) {
      try {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE person SET name=?, age=?, email=? WHERE id=?");

        preparedStatement.setString(1, updatedPerson.getName());
        preparedStatement.setInt(2, updatedPerson.getAge());
        preparedStatement.setString(3, updatedPerson.getEmail());
        preparedStatement.setInt(4, id);
        preparedStatement.executeUpdate();

      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }

    public void delete(int id) {
      try {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM  person WHERE id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
}
