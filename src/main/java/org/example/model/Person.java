package org.example.model;

public class Person {

    private int id;
    private String name;
    private Integer age;
    private Priority priority;

    private static int nextId = -5;

    public Person(String name, Integer age, Priority priority) {
        this.name = name;
        this.age = age;
        this.priority = priority;
        this.id = nextId++;
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
