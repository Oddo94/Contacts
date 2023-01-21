package contacts.commands;

import contacts.PhoneBook;

import java.util.Scanner;
import java.util.TreeMap;

public class ListCommandExtended extends CompositeCommand {
    private PhoneBook phoneBook;
    private Scanner scanner;
    private CompositeCommand menuCommand;

    public ListCommandExtended(TreeMap<String, Command> childCommands, CompositeCommand menuCommand,  PhoneBook phoneBook) {
        super(childCommands);
        this.phoneBook = phoneBook;
        this.scanner = new Scanner(System.in);
        this.menuCommand = menuCommand;
    }

    public void execute() {
        System.out.println("[list] Enter action ([number], back):");
        String userInput = scanner.nextLine();

        try {
            while(true) {
                int recordNumber = Integer.parseInt(userInput);
                int recordIndex = recordNumber - 1;

                phoneBook.displayContactByIndex(recordIndex, phoneBook.getContactsList());

            }

        } catch(NumberFormatException ex) {
            if("back".equals(userInput)) {
                menuCommand.execute();
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

}
