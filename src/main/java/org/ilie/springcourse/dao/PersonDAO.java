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
        return null;
//        return people.stream().filter(person -> person.getId() == id).collect(Collectors.toList()).get(0);
    }

    public void save(Person person) {

      try {
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO person VALUES ("+1+",'"+person.getName()+"','"+person.getAge() + "','"+person.getEmail()+"')";
        statement.executeUpdate(sql);

      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {

//        people.removeIf(p -> p.getId() == id);
    }
}
