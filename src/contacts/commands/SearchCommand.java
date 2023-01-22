package contacts.commands;

import contacts.PhoneBook;
import contacts.model.CompanyContact;
import contacts.model.Contact;
import contacts.model.PersonContact;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchCommand extends CompositeCommand {
    private PhoneBook phoneBook;
    private Scanner scanner;
    private TreeMap<String, Command> childCommands;
    private CompositeCommand menuCommand;


    public SearchCommand(TreeMap<String, Command> childCommands, CompositeCommand menuCommand, PhoneBook phoneBook) {
        super(childCommands);
        this.phoneBook = phoneBook;
        this.scanner = new Scanner(System.in);
        this.childCommands = childCommands;
        this.menuCommand = menuCommand;
    }

    public void execute() {
        System.out.println("Enter search query:");
        String searchQuery = scanner.nextLine();

        while(true) {
            System.out.println("[search] Enter action ([number], back, again):");
            String userInput = scanner.nextLine();

            List<Contact> contactList = phoneBook.getContactsList();
            try {
                int recordNumber = Integer.parseInt(userInput);
                //Sets the record number that needs to be updated in the edit command object
                ((EditCommand)childCommands.get("edit")).setRecordNumberToUpdate(recordNumber);
                //int recordIndex = recordNumber - 1;

                int recordIndex = recordNumber - 1;
                if (recordIndex >= 0 && recordIndex < contactList.size()) {
                    phoneBook.displayContactByIndex(recordIndex, contactList);
                }

                while(true) {
                    System.out.println("\n[record] Enter action (edit, delete, menu):");
                    userInput = scanner.nextLine();

                    switch (userInput) {
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
                            System.out.println("Invalid command inside search menu!");

                    }
                }

            } catch (NumberFormatException ex) {
                if("back".equals(userInput)) {
                    return;
                } else if("again".equals(userInput)) {
                    //Moves to the next iteration of the loop and displays the search menu again
                    continue;
                }
            }
        }
    }

    private List<Contact> searchRecord(List<Contact> contactList, String searchQuery) {

        List<Contact> searchResults = contactList
                .stream()
                .map(contact -> contact.getClass() == PersonContact.class ? (PersonContact) contact : (CompanyContact) contact)
                .filter(contact -> containsSearchQuery(contact.toString(), searchQuery))
                .collect(Collectors.toList());

        return searchResults;
    }

    private boolean containsSearchQuery(String searchInput, String searchQuery) {
        if(searchQuery == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(searchQuery);
        Matcher matcher = pattern.matcher(searchInput);

        return matcher.find();
    }
}
