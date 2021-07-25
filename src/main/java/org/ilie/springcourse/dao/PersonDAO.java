package org.ilie.springcourse.dao;

import org.ilie.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Ilie"));
        people.add(new Person(++PEOPLE_COUNT, "Grigore"));
        people.add(new Person(++PEOPLE_COUNT, "Vitamin"));
        people.add(new Person(++PEOPLE_COUNT, "Curechi"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show (int id) {
        return people.stream().filter(person -> person.getId() == id).collect(Collectors.toList()).get(0);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
