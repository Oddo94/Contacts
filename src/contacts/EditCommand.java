package contacts;

import java.util.Scanner;

public class EditCommand implements Command {
    private PhoneBook phoneBook;
    private Scanner scanner;

    public EditCommand(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
        this.scanner = new Scanner(System.in);
    }


    @Override
    public void execute() {
        int executionResult = -1;

        if(phoneBook.getNumberOfContacts() == 0) {
            System.out.println("No records to edit!");
            return;
        }

        System.out.println("Select a record:");
        int recordNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Select a field (name, surname, number):");
        String fieldName = scanner.nextLine();

        switch(fieldName) {
            case "name":
                System.out.println("Enter name:");
                String name = scanner.nextLine();
                executionResult = phoneBook.editRecord(recordNumber, EditedElement.NAME, name);
                break;

            case "surname":
                System.out.println("Enter surname:");
                String surname = scanner.nextLine();
                executionResult = phoneBook.editRecord(recordNumber, EditedElement.SURNAME, surname);
                break;

            case "number":
                System.out.println("Enter number:");
                String phoneNumber = scanner.nextLine();
                executionResult = phoneBook.editRecord(recordNumber, EditedElement.PHONE_NUMBER, phoneNumber);
                break;

            default:
                System.out.println("Invalid field name!");
                break;
        }

        if(executionResult == -1) {
            System.out.println("Unable to update the specified record!");
        } else {
            System.out.println("The record updated!");
        }


    }
}
