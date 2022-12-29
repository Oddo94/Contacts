package contacts;

import java.util.ArrayList;

public class PhoneBook {
    private ArrayList<Contact> contactsList;

    public PhoneBook() {
        this.contactsList = new ArrayList<>();
    }

    public int addContact(Contact contact) {
        boolean hasInsertedRecord = contactsList.add(contact);

        if(hasInsertedRecord) {
            return 0;
        }

        return -1;
    }

    public int removeContact(Contact contact) {
        boolean hasRemovedContact = contactsList.remove(contact);

        if(hasRemovedContact) {
            return 0;
        }

        return -1;
    }

    public int removeContact2(int contactIndex) {
        Contact contact = contactsList.remove(contactIndex);

        if(contact != null) {
            return 0;
        }

        return -1;
    }


    public int editRecord(int recordNumber, Contact editedContact) {
        int recordIndex = recordNumber - 1;

        Contact contactToEdit = contactsList.get(recordIndex);

        String newName = editedContact.getName() != null ? editedContact.getName() : contactToEdit.getName();
        String newSurname = editedContact.getSurname() != null ? editedContact.getSurname() : contactToEdit.getSurname();
        String newPhoneNumber = editedContact.getPhoneNumber() != null ? editedContact.getPhoneNumber() : contactToEdit.getPhoneNumber();




        contactToEdit.setName(newName);
        contactToEdit.setSurname(newSurname);
        contactToEdit.setPhoneNumber(newPhoneNumber);

        return 0;
    }

    public int getNumberOfContacts() {
        return contactsList.size();
    }

    public ArrayList<Contact> getContactsList() {
        return this.contactsList;
    }
}
