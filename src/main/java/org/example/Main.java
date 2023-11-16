package org.example;

import org.example.controller.PersonController;
import org.example.model.Person;
import org.example.model.Priority;
import org.example.view.PersonView;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PersonController controller = new PersonController();
        PersonView view = new PersonView();
        Scanner scanner = new Scanner(System.in);

        // INFORMAÇÕES FICTÍCIAS PARA O SISTEMA DE INSERÇÃO SE BASEAR
        controller.createPerson("TESTE_JOVEM_BAIXA", 30, Priority.LOW, "");
        controller.createPerson("TESTE_IDOSO_BAIXA", 60, Priority.LOW, "");
        controller.createPerson("TESTE_JOVEM_MEDIA", 30, Priority.MEDIUM, "");
        controller.createPerson("TESTE_IDOSO_MEDIA", 60, Priority.MEDIUM, "");
        controller.createPerson("TESTE_IDOSO_ALTA", 60, Priority.HIGH, "");
        controller.createPerson("TESTE_JOVEM_ALTA", 30, Priority.HIGH, "");

        // PESSOA DE TESTE
        controller.createPerson("SEU JAO", 10, Priority.MEDIUM, "Dor de cabeça");

        while (true) {
            view.displayMenu();
            System.out.println("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine().toUpperCase();

                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter new priority: (LOW, MEDIUM or HIGH) ");
                    String enterPriority = scanner.next().toUpperCase();
                    Priority priority = controller.checkPriority(enterPriority);
                    scanner.nextLine();

                    System.out.println("Add a description of the complain: ");
                    String description = scanner.nextLine();

                    controller.createPerson(name, age, priority, description);
                    System.out.println("Person created.");
                }
                case 2 -> {
                    boolean validation = true;
                    while (validation) {
                        System.out.println("Which field would you like to update?");
                        view.displayUpdateOptions();
                        int updateChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (updateChoice) {
                            case 1 -> {
                                System.out.println("Enter person id to update: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();

                                System.out.println("Enter the new name: ");
                                String newName = scanner.nextLine().toUpperCase();

                                controller.updatePersonName(id, newName);
                            }
                            case 2 -> {
                                System.out.println("Enter person id to update: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();

                                Integer newAge = null;
                                System.out.println("Enter new Age: ");
                                String ageToConvert = scanner.nextLine();
                                try {
                                    newAge = Integer.parseInt(ageToConvert);
                                    controller.updatePersonAge(id, newAge);
                                    System.out.println("The User/'s information/'s have been successfully updated.");
                                    validation = false;
                                } catch (NumberFormatException e) {
                                    System.out.println("Age not updated!");
                                }
                            }
                            case 3 -> {
                                System.out.println("Enter person id to update: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();

                                System.out.println("Priority is a required field! ");
                                System.out.println("Enter new Priority: ");
                                String enterPriority = scanner.next().toUpperCase();
                                Priority newPriority = controller.checkPriority(enterPriority);

                                controller.updatePersonPriority(id, newPriority);
                            }
                            case 4 -> {
                                System.out.println("Enter person id to update: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();

                                System.out.println("Type the new description: (The current description will be overwritten)");
                                String newDescription = scanner.nextLine();

                                controller.updatePersonDescription(id, newDescription);
                            }
                            case 5 -> {
                                System.out.println("Returning to Main Menu...");
                                validation = false;
                            }
                            default -> System.out.println("Invalid option. Try again.");
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Enter person id to be found: ");
                    int id = scanner.nextInt();
                    Person person = controller.readPersonById(id);
                    view.displayPerson(person);
                }
                case 4 -> {
                    System.out.println("Enter person id to delete: ");
                    int id = scanner.nextInt();
                    controller.deletePerson(id);
                }
                case 5 -> {
                    List<Person> queue = controller.getQueue();
                    view.displayQueue(queue);
                }
                case 6 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}