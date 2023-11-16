package org.example.model;

import java.time.LocalDateTime;

public class Person {

    private int id;
    private String name;
    private Integer age;
    private Priority priority;
    private String description;
    private LocalDateTime arrival;

    private static int nextId = -5;

    public Person(String name, Integer age, Priority priority, String description) {
        this.name = name;
        this.age = age;
        this.priority = priority;
        this.description = description;
        this.id = nextId++;
        this.arrival = LocalDateTime.now();
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
