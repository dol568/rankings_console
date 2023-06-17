package org.mwo.agh.edu.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Project implements Serializable {

    private final String name;
    private final Set<Activity> activities;

    public Project(String name) {
        this.name = name;
        this.activities = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return name.equals(project.name) && activities.equals(project.activities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, activities);
    }
}
