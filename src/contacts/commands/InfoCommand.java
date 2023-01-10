package contacts;

import java.util.List;
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
