package org.example.view;

import org.example.model.Person;

import java.util.List;

public class PersonView {

    public void displayMenu() {
        System.out.println("\nMAIN MENU:");
        System.out.println("1. Add Person");
        System.out.println("2. Update Person");
        System.out.println("3. Read Person");
        System.out.println("4. Delete Person");
        System.out.println("5. Display Queue");
        System.out.println("6. Exit Program");
    }

    public void displayPerson(Person person) {
        if (person != null) {
            System.out.println("\nName: " + person.getName());
            System.out.println("Age: " + person.getAge());
            System.out.println("Priority: " + person.getPriority());
            System.out.println("Id: " + person.getId());
        } else {
            System.out.println("Person not found.");
        }
    }

    public void displayQueue(List<Person> queue) {
        if (queue.isEmpty()) {
            System.out.println("\nQueue is empty.");
        } else {
            System.out.println("\nFull Queue:");
            int position = 0;
            for (Person person : queue) {
                if (person.getId() > 0) {
                    System.out.println((position + 1) + "º -" + " Name: " + person.getName() + ", Age: " + person.getAge() + " Priority: " + person.getPriority() + ", ID: " + person.getId());
                    position++;
                }
            }
        }
    }

    public void displayUpdateOptions() {
        System.out.println("1. Update Name");
        System.out.println("2. Update Age");
        System.out.println("3. Update Priority");
        System.out.println("4. Return to main menu");
    }
}
