package org.mwo.agh.edu.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Spreadsheet implements Serializable {

    private final Set<Person> persons;

    public Spreadsheet() {
        this.persons = new HashSet<>();
    }

    public void addPerson(Person person) {
        this.persons.add(person);
    }

    public Set<Person> getPersons() {
        return persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spreadsheet that = (Spreadsheet) o;
        return persons.equals(that.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons);
    }
}
