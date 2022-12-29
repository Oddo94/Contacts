package contacts;

import java.util.List;

public class ListCommand implements Command {
    private PhoneBook phoneBook;

    public ListCommand(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }


    @Override
    public int execute() {
        List<Contact> contactList = phoneBook.getContactsList();

        if( contactList.size() > 0) {
            for(int i = 0; i < contactList.size(); i++) {
                Contact currentContact = contactList.get(i);
                int contactNumber = 1 + i;
                String contactToPrint = String.format("%d. %s", contactNumber, currentContact.toString());
                System.out.println(contactToPrint);
            }

            return 0;
        }

        return -1;
    }
}
