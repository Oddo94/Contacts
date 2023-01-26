package contacts.commands;

import contacts.PhoneBook;
import contacts.model.CompanyContact;
import contacts.model.Contact;
import contacts.model.PersonContact;

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
        List<Contact> contactList = phoneBook.getContactsList();
        List<Contact> searchResults = performRecordSearch(contactList);

        while(true) {
            System.out.println("\n[search] Enter action ([number], back, again):");
            String userInput = scanner.nextLine();

            try {
                int recordNumber = Integer.parseInt(userInput);

                int recordIndex = recordNumber - 1;
                if (searchResults.size() > 0 && (recordIndex >= 0 && recordIndex < contactList.size())) {
                    phoneBook.displayContactByIndex(recordIndex, contactList);
                } else {
                    System.out.println("Invalid record number!");
                    return;
                }
                //Retrieves the contact based on the user supplied record number
                Contact selectedContact = searchResults.get(recordIndex);

                //Retrieves the index of the record inside the original contact list
                int originalListRecordNumber = contactList.indexOf(selectedContact) + 1;
                //Sets the record number that needs to be updated in the edit command object
                ((EditCommand)childCommands.get("edit")).setRecordNumberToUpdate(originalListRecordNumber);

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
                    menuCommand.execute();
                } else if("again".equals(userInput)) {
                    //Moves to the next iteration of the loop and displays the search menu again
                    performRecordSearch(contactList);
                }
            }
        }
    }

    private List<Contact> performRecordSearch(List<Contact> contactList) {
        System.out.println("Enter search query:");
        String searchQuery = scanner.nextLine();

        List<Contact> searchResults = searchRecords(contactList, searchQuery);

        if(searchResults.size() > 0) {
            System.out.println(String.format("Found %d results:", searchResults.size()));

            //Prints the search results
            searchResults
                    .stream()
                    .map(contact -> String.format("%d. %s", searchResults.indexOf(contact) + 1, contact.getFullName()))
                    .forEach(System.out::println);
        } else {
            System.out.println("No results found!");
        }

        return searchResults;

    }

    private List<Contact> searchRecords(List<Contact> contactList, String searchQuery) {

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

        Pattern pattern = Pattern.compile(searchQuery, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(searchInput);

        return matcher.find();
    }
}
