package contacts.commands;

import contacts.PhoneBook;

public class CountCommand implements Command {
    private PhoneBook phoneBook;

    public CountCommand(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    @Override
    public void execute() {
        int totalContacts = phoneBook.getNumberOfContacts();

        System.out.println(String.format("The Phone Book has %d records.", totalContacts));

        System.out.println();
    }
}
