package contacts;

import contacts.io.FileManager;
import contacts.model.CompanyContact;
import contacts.model.Contact;
import contacts.model.PersonContact;
import contacts.utils.enums.ContactType;
import contacts.utils.enums.EditedElement;
import contacts.utils.enums.Gender;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private ArrayList<Contact> contactsList;
    private File storageFile;
    boolean saveContactsToFile;

    public PhoneBook(boolean saveContactsToFile, File storageFile) {
        this.saveContactsToFile = saveContactsToFile;
        this.storageFile = storageFile;

        //Loads the contacts from the storage file if the file exists, otherwise it just initializes the in-memory storage
        if(saveContactsToFile && storageFile != null) {
            this.contactsList = retrieveContactListFromFile();
        } else {
            this.contactsList = new ArrayList<>();
        }

    }

    public int addContact(Contact contact) {
        boolean hasInsertedRecord = contactsList.add(contact);

        if(hasInsertedRecord) {
            //Saves the contacts list to the storage file after the specified contact is removed
            saveContactListToFile(this.contactsList);

            return 0;
        }

        return -1;
    }

    public int removeContact(int contactIndex) {
        Contact contact = contactsList.remove(contactIndex);

        if(contact != null) {
            //Saves the contacts list to the storage file after the specified contact is removed
            saveContactListToFile(this.contactsList);

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

        if(executionResult != -1) {
            saveContactListToFile(this.contactsList);
        }

        return executionResult;

    }

    private int editPersonRecord(int recordIndex, ContactType contactType, EditedElement editedElement, String newValue) {
        PersonContact personContact = (PersonContact) contactsList.get(recordIndex);
        String fullName = null;

        switch(editedElement) {
            case NAME:
                personContact.setName(newValue);
                //Updates the full name field of the base class
                fullName = String.format("%s %s", newValue, personContact.getSurname());
                personContact.setFullName(fullName);
                personContact.setUpdatedDate(LocalDateTime.now());
                break;

            case SURNAME:
                personContact.setSurname(newValue);
                //Updates the full name field of the base class
                fullName = String.format("%s %s", personContact.getName(), newValue);
                personContact.setFullName(fullName);
                personContact.setUpdatedDate(LocalDateTime.now());
                break;

            case BIRTH_DATE:
                LocalDate birthDate = LocalDate.parse(newValue);
                personContact.setBirthDate(birthDate);
                personContact.setUpdatedDate(LocalDateTime.now());
                break;

            case GENDER:
                Gender gender = getGender(newValue);
                personContact.setGender(gender);
                personContact.setUpdatedDate(LocalDateTime.now());
                break;

            case PHONE_NUMBER:
                personContact.setPhoneNumber(newValue);
                personContact.setUpdatedDate(LocalDateTime.now());
                break;

            default:
                return -1;
        }

        //Saves the contacts list to the storage file after the specified contact is updated
        saveContactListToFile(this.contactsList);

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

        //Saves the contacts list to the storage file after the specified contact is updated
        saveContactListToFile(this.contactsList);

        return 0;
    }

    public int getNumberOfContacts() {
        //Retrieves the contacts list from the storage file if this exists
        if(saveContactsToFile && storageFile != null) {
            this.contactsList = retrieveContactListFromFile();
        }

        return contactsList.size();
    }

    public ArrayList<Contact> getContactsList() {
        //Retrieves the contacts list from the storage file if this exists
        if(saveContactsToFile && storageFile != null) {
            this.contactsList = retrieveContactListFromFile();
        }

        return this.contactsList;
    }

    public void displayContacts(List<Contact> contactList, boolean showDetails) {
        if( contactList.size() > 0) {
            for(int i = 0; i < contactList.size(); i++) {
                Contact currentContact = contactList.get(i);
                int contactNumber = 1 + i;
                //String contactToPrint = String.format("%d. %s", contactNumber, currentContact.toString());
                //System.out.println(contactToPrint);

                String contactListEntry = null;
                if(currentContact.getClass() == PersonContact.class) {
                    PersonContact personContact = (PersonContact) currentContact;
                    if(showDetails) {
                        System.out.println(personContact.toString());
                    } else {
                        contactListEntry = String.format("%d. %s %s", contactNumber, personContact.getName(), personContact.getSurname());
                        System.out.println(contactListEntry);
                    }

                } else if(currentContact.getClass() == CompanyContact.class) {
                    CompanyContact companyContact = (CompanyContact) currentContact;
                    if(showDetails) {
                        System.out.println(companyContact.toString());
                    } else {
                        contactListEntry = String.format("%d. %s", contactNumber, companyContact.getOrganizationName());
                        System.out.println(contactListEntry);
                    }
                }
            }
        }
    }

    public void displayContactByIndex(int index, List<Contact> contactList) {
        int lowerBound = 0;
        int upperBound = contactList.size() - 1;

        if (index < lowerBound || index > upperBound) {
            System.out.println("Invalid index for the specified contact!");
            return;
        }

        Contact searchedContact = contactList.get(index);

        if (searchedContact.getClass() == PersonContact.class) {
            PersonContact personContact = (PersonContact) searchedContact;
            System.out.println(personContact.toString());
        } else if (searchedContact.getClass() == CompanyContact.class) {
            CompanyContact companyContact = (CompanyContact) searchedContact;
            System.out.println(companyContact.toString());
        }

        System.out.println();
    }

    private Gender getGender(String userInput) {
        Gender gender = Gender.UNDEFINED;

        if ( "M".equals(userInput)) {
            gender = Gender.MALE;
        } else if ("F".equals(userInput)) {
            gender = Gender.FEMALE;
        }

        return gender;
    }

    private void saveContactListToFile(ArrayList<Contact> contactsList) {
        if(saveContactsToFile && storageFile != null) {
            FileManager fileManager = new FileManager(storageFile);
            fileManager.saveContactsToFile(contactsList);
        }
    }

    private ArrayList<Contact> retrieveContactListFromFile() {
        FileManager fileManager = new FileManager(storageFile);
        ArrayList<Contact> retrievedContacts = fileManager.getContactsFromFile();

        return retrievedContacts;
    }

}
