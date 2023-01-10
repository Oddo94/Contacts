package contacts;

import contacts.io.FileManager;
import contacts.model.CompanyContact;
import contacts.model.Contact;
import contacts.model.PersonContact;
import contacts.utils.enums.Gender;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        InputManager inputManager = new InputManager();
//        inputManager.manageUserInput();

        CompanyContact companyContact= new CompanyContact("Tesla", "1 Tesla Road Austin", "0756488933", LocalDateTime.now(), LocalDateTime.now());
        PersonContact personContact = new PersonContact("Smith", "Jason", "0745388922", LocalDate.parse("1994-02-15"), Gender.MALE, LocalDateTime.now(), LocalDateTime.now());

        ArrayList<Contact> contactList = new ArrayList<>(Arrays.asList(companyContact, personContact));
        File file = new File("D:\\IAP\\JetBrains Academy projects\\Contacts\\Contacts\\task\\src\\phonebook.db");
        FileManager fileManager = new FileManager(file);

        fileManager.saveContactsToFile(contactList);

        ArrayList<Contact> outputList = fileManager.getContactsFromFile();
        outputList.stream()
                .map(x -> x.getClass() == PersonContact.class ? ((PersonContact) x).toString() : ((CompanyContact) x).toString())
                .forEach(System.out::println);



    }
}
