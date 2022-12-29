package contacts;

import java.util.Scanner;

public class RemoveCommand implements Command {
    private PhoneBook phoneBook;
    private Scanner scanner;

    public RemoveCommand(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        if(phoneBook.getNumberOfContacts() == 0) {
            System.out.println("No records to remove!");
            return;
        }

        System.out.println("Select a record:");
        int recordNumber = scanner.nextInt();
        scanner.nextLine();

        int lowerBoundIndex = 0;
        int realIndex = recordNumber - 1;
        int upperBoundIndex = phoneBook.getContactsList().size() - 1;

        //Checks if the index is out of bounds
        if(realIndex < lowerBoundIndex || realIndex > upperBoundIndex) {
            System.out.println("The provided index is out of bounds!");
        }

        int executionResult = phoneBook.removeContact(realIndex);

        if(executionResult == -1) {
            System.out.println("Unable to remove the specified record!");
        } else {
            System.out.println("The record removed!");
        }

    }
}
