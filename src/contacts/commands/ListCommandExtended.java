package contacts.commands;

import contacts.PhoneBook;
import contacts.model.Contact;

import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class ListCommandExtended extends CompositeCommand {
    private PhoneBook phoneBook;
    private Scanner scanner;
    private TreeMap<String, Command> childCommands;
    private CompositeCommand menuCommand;

    public ListCommandExtended(TreeMap<String, Command> childCommands, CompositeCommand menuCommand,  PhoneBook phoneBook) {
        super(childCommands);
        this.phoneBook = phoneBook;
        this.scanner = new Scanner(System.in);
        this.childCommands = childCommands;
        this.menuCommand = menuCommand;
    }

    public void execute() {
        List<Contact> contactList = phoneBook.getContactsList();
        phoneBook.displayContacts(contactList, false);

        System.out.println("[list] Enter action ([number], back):");
        String userInput = scanner.nextLine();

        try {
            //while(true) {
                int recordNumber = Integer.parseInt(userInput);
                //Sets the record number that needs to be updated in the edit command object
                ((EditCommand)childCommands.get("edit")).setRecordNumberToUpdate(recordNumber);
                //int recordIndex = recordNumber - 1;

                int recordIndex = recordNumber - 1;
                if (recordIndex >= 0 && recordIndex < contactList.size()) {
                    phoneBook.displayContactByIndex(recordIndex, contactList);
                   // break;
                }
            //}
               while(true) {
                   System.out.println("\n[record] Enter action (edit, delete, menu):");
                   userInput = scanner.nextLine();

                   switch(userInput) {
                       case "edit":
                           childCommands.get("edit").execute();
                           break;

                       case "delete":
                           childCommands.get("delete").execute();
                           break;

                       case "menu":
                           menuCommand.execute();
                           break;

                       default:
                           System.out.println("Invalid command inside list menu!");

                   }

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
