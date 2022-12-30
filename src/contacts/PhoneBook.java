package contacts;

import contacts.utils.enums.EditedElement;

import java.util.ArrayList;

public class PhoneBook {
    private ArrayList<PersonContact> contactsList;

    public PhoneBook() {
        this.contactsList = new ArrayList<>();
    }

    public int addContact(PersonContact contact) {
        boolean hasInsertedRecord = contactsList.add(contact);

        if(hasInsertedRecord) {
            return 0;
        }

        return -1;
    }

//    public int removeContact(Contact contact) {
//        boolean hasRemovedContact = contactsList.remove(contact);
//
//        if(hasRemovedContact) {
//            return 0;
//        }
//
//        return -1;
//    }

    public int removeContact(int contactIndex) {
        PersonContact contact = contactsList.remove(contactIndex);

        if(contact != null) {
            return 0;
        }

        return -1;
    }


    public int editRecord(int recordNumber, EditedElement editedElement, String newValue) {
        int recordIndex = recordNumber - 1;

        switch(editedElement) {
            case NAME:
                contactsList.get(recordIndex).setName(newValue);
                break;

            case SURNAME:
                contactsList.get(recordIndex).setSurname(newValue);
                break;

            case PHONE_NUMBER:
                contactsList.get(recordIndex).setPhoneNumber(newValue);
                break;

            default:
                return -1;
        }

        return 0;

//        Contact contactToEdit = contactsList.get(recordIndex);
//
//        String newName = editedContact.getName() != null ? editedContact.getName() : contactToEdit.getName();
//        String newSurname = editedContact.getSurname() != null ? editedContact.getSurname() : contactToEdit.getSurname();
//        String newPhoneNumber = editedContact.getPhoneNumber() != null ? editedContact.getPhoneNumber() : contactToEdit.getPhoneNumber();
//
//
//
//
//        contactToEdit.setName(newName);
//        contactToEdit.setSurname(newSurname);
//        contactToEdit.setPhoneNumber(newPhoneNumber);
//
//        return 0;
    }

    public int getNumberOfContacts() {
        return contactsList.size();
    }

    public ArrayList<PersonContact> getContactsList() {
        return this.contactsList;
    }
}
