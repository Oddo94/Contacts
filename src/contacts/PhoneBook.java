package contacts;

import contacts.utils.enums.ContactType;
import contacts.utils.enums.EditedElement;

import java.time.LocalDateTime;
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
        Contact contact = contactsList.remove(contactIndex);

        if(contact != null) {
            return 0;
        }

        return -1;
    }


    public Contact getContactByIndex(int index) {
        return contactsList.get(index);
    }

    public int editRecord(int recordNumber, ContactType contactType, EditedElement editedElement, String newValue) {
        int executionResult = -1;
        int recordIndex = recordNumber - 1;

        if(contactType == ContactType.PERSON) {
            executionResult = editPersonRecord(recordNumber, contactType, editedElement, newValue);
        } else if(contactType == ContactType.COMPANY) {
            executionResult = editCompanyRecord(recordNumber, contactType, editedElement, newValue);
        }

        return executionResult;

    }

    private int editPersonRecord(int recordIndex, ContactType contactType, EditedElement editedElement, String newValue) {
        PersonContact personContact = (PersonContact) contactsList.get(recordIndex);

        switch(editedElement) {
            case NAME:
                personContact.setName(newValue);
                personContact.setUpdatedDate(LocalDateTime.now());
                break;

            case SURNAME:
                personContact.setSurname(newValue);
                personContact.setUpdatedDate(LocalDateTime.now());
                break;

            case PHONE_NUMBER:
                personContact.setPhoneNumber(newValue);
                personContact.setUpdatedDate(LocalDateTime.now());
                break;

            default:
                return -1;
        }

        return 0;
    }

    private int editCompanyRecord(int recordIndex, ContactType contactType, EditedElement editedElement, String newValue) {
        CompanyContact companyContact = (CompanyContact) contactsList.get(recordIndex);

        switch(editedElement) {
            case ADDRESS:
                companyContact.setAddress(newValue);
                companyContact.setUpdatedDate(LocalDateTime.now());
                break;

            case PHONE_NUMBER:
                companyContact.setPhoneNumber(newValue);
                companyContact.setUpdatedDate(LocalDateTime.now());
                break;

            default:
                return -1;
        }

        return 0;
    }

    public int getNumberOfContacts() {
        return contactsList.size();
    }

    public ArrayList<Contact> getContactsList() {
        return this.contactsList;
    }

}
