package org.mwo.agh.edu.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Person implements Serializable {

    private final String name;
    private final String surname;
    private final Set<Project> projects;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.projects = new HashSet<>();
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) && surname.equals(person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
