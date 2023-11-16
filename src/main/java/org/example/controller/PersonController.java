package org.example.controller;

import org.example.model.Person;
import org.example.model.Priority;

import java.util.*;

public class PersonController {

    private List<Person> queue = new ArrayList<>();

    public Person createPerson(String name, Integer age, Priority priority, String description) {
        Person newPerson = new Person(name, age, priority, description);
        sortQueue(newPerson);
        return newPerson;
    }

    public Person readPersonById(int id) {
        for (Person person : queue) {
            if (person.getId() == id)
                return person;
        }
        return null;
    }

    public List<Person> getQueue() {
        return queue;
    }

    public void updatePersonName(int id, String newName) {
        Person person = readPersonById(id);
        if (person != null) {
            person.setName(newName);
            System.out.println("Name updated.");
        }
        else
            System.out.println("Fail to update name!");
    }

    public void updatePersonAge(int id, Integer newAge) {
        Person person = readPersonById(id);
        if (person != null) {
            boolean validator = validateNewAge(person.getAge(), newAge);
            if (!validator)
                person.setAge(newAge);
            else {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the same priority or a new one: (LOW, MEDIUM or HIGH)");
                System.out.println("*Priority is a required field, even if it still the same, please insert*");
                String enterPriority = scanner.next().toUpperCase();
                Priority priority = checkPriority(enterPriority);

                int idToRecover = person.getId();
                String name = person.getName();
                String description = person.getDescription();
                deletePerson(id);
                Person personId = createPerson(name, newAge, priority, description);
                personId.setId(idToRecover);
            }
        } else
            System.out.println("Fail to update age!");
    }

    public void updatePersonPriority(int id, Priority newPriority) {
        Person person = readPersonById(id);
        if (person != null) {
            boolean validator = validateNewPriority(person.getPriority(), newPriority);
            if (!validator) {
                person.setPriority(newPriority);
                System.out.println("Priority updated.");
            }
            else {
                int idToRecover = person.getId();
                String name = person.getName();
                Integer age = person.getAge();
                String description = person.getDescription();
                deletePerson(id);
                Person personId = createPerson(name, age, newPriority, description);
                personId.setId(idToRecover);
                System.out.println("Priority updated.");
            }
        } else
            System.out.println("Fail to update priority!");
    }

    public void updatePersonDescription(int id, String newDescription) {
        Person person = readPersonById(id);
        if (person != null) {
            person.setDescription(newDescription);
            System.out.println("Description updated.");
        }
        else
            System.out.println("Fail to update description!");
    }

    public void deletePerson(int id) {
        Person personToRemove = null;
        for (Person person : queue) {
            if (person.getId() == id) {
                personToRemove = person;
                break;
            }
        }
        if (personToRemove != null) {
            queue.remove(personToRemove);
            System.out.println("Person removed.");
        } else
            System.out.println("Fail to delete!");
    }

    public Priority checkPriority(String priority) {
        if (Objects.equals(priority, "LOW"))
            return Priority.LOW;

        if (Objects.equals(priority, "MEDIUM"))
            return Priority.MEDIUM;

        if (Objects.equals(priority, "HIGH"))
            return Priority.HIGH;

        return null;
    }

    private void sortQueue(Person person) {
        if (queue.isEmpty())
            queue.add(person);
        else {
            if (person.getPriority().equals(Priority.HIGH))
                sortHighPriority(person);

            else if (person.getPriority().equals(Priority.MEDIUM))
                sortMediumPriority(person);

            else if (person.getPriority().equals(Priority.LOW))
                sortLowPriority(person);

            else
                System.out.println("Error to insert!");
        }
    }

    private void sortHighPriority(Person personToInsert) {
        ListIterator<Person> iterator = queue.listIterator();
        if (personToInsert.getAge() >= 60) {
            while (iterator.hasNext()) {
                Person currentPerson = iterator.next();
                if (currentPerson.getPriority().equals(Priority.MEDIUM)) {
                    iterator.previous();
                    iterator.add(personToInsert);
                    return;
                }
            }
        } else {
            while (iterator.hasNext()) {
                Person currentPerson = iterator.next();
                if (currentPerson.getPriority().equals(Priority.HIGH) && currentPerson.getAge() >= 60) {
                    iterator.previous();
                    iterator.add(personToInsert);
                    return;
                }
            }
        }
    }

    private void sortMediumPriority(Person personToInsert) {
        ListIterator<Person> iterator = queue.listIterator();
        if (personToInsert.getAge() < 60) {
            while (iterator.hasNext()) {
                Person currentPerson = iterator.next();
                if (currentPerson.getPriority().equals(Priority.LOW)) {
                    iterator.previous();
                    iterator.add(personToInsert);
                    return;
                }
            }
        } else {
            while (iterator.hasNext()) {
                Person currentPerson = iterator.next();
                if (currentPerson.getPriority().equals(Priority.MEDIUM) && currentPerson.getAge() < 60) {
                    iterator.previous();
                    iterator.add(personToInsert);
                    return;
                }
            }
        }
    }

    private void sortLowPriority(Person personToInsert) {
        ListIterator<Person> iterator = queue.listIterator();
        if (personToInsert.getAge() < 60) {
            queue.add(personToInsert);
        } else {
            while (iterator.hasNext()) {
                Person currentPerson = iterator.next();
                if (currentPerson.getPriority().equals(Priority.LOW) && currentPerson.getAge() < 60) {
                    iterator.previous();
                    iterator.add(personToInsert);
                    return;
                }
            }
        }
    }

    private boolean validateNewAge(int lastAge, int newAge) {
        return (lastAge >= 60 || newAge >= 60) && (lastAge < 60 || newAge < 60);
    }

    private boolean validateNewPriority(Priority lastPriority, Priority newPriority) {
        return !lastPriority.equals(newPriority);
    }
}

