package contacts;

import java.util.Scanner;

public class AddCommand implements Command {
    private PhoneBook phoneBook;
    private Scanner scanner;

    public AddCommand(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
        this.scanner = new Scanner(System.in);
    }
    @Override
    public void execute() {
        System.out.println("Enter the name: ");
        String name = scanner.nextLine();

        System.out.println("Enter the surname: ");
        String surname = scanner.nextLine();

        System.out.println("Enter the number:");
        String phoneNumber = scanner.nextLine();

        Contact contact = new Contact(name, surname, phoneNumber);

        int executionResult = phoneBook.addContact(contact);

        if (executionResult == -1) {
            System.out.println("Unable to add the specified record!");
        } else {
            System.out.println("The record added.");
        }

    }
}
