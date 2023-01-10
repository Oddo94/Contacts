package contacts.commands;

import contacts.PhoneBook;
import contacts.commands.Command;

import java.util.Scanner;

public class InfoCommand implements Command {
    private PhoneBook phoneBook;
    private Scanner scanner;

    public InfoCommand(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        phoneBook.displayContacts(phoneBook.getContactsList(), false);

        System.out.println("Select a record:");
        int recordNumber = scanner.nextInt();

        int recordIndex = recordNumber - 1;
        phoneBook.displayContactByIndex(recordIndex, phoneBook.getContactsList());
    }
}
