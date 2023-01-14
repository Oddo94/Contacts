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
        boolean saveContactsToFile = args[0] != null ? true : false;

        File file = null;
        FileManager fileManager= null;
        if(saveContactsToFile) {
            file = new File(String.format("D:\\IAP\\JetBrains Academy projects\\Contacts\\Contacts\\task\\src\\%s", args[0]));
            fileManager = new FileManager(file);

            if(!file.exists()) {
                fileManager.createStorageFile();
            }
        }

           InputManager inputManager = new InputManager(saveContactsToFile, file);
           inputManager.manageUserInput();

        //fileManager.createStorageFile();
//
//        CompanyContact companyContact= new CompanyContact("Tesla", "1 Tesla Road Austin", "0756488933", LocalDateTime.now(), null);
//        PersonContact personContact = new PersonContact("Smith", "Jason", "0745388922", LocalDate.parse("1994-02-15"), Gender.MALE, LocalDateTime.now(), null);
//
//        ArrayList<Contact> contactList = new ArrayList<>(Arrays.asList(companyContact, personContact));
//
//        fileManager.saveContactsToFile(contactList);
//
//        ArrayList<Contact> outputList = fileManager.getContactsFromFile();
//        outputList.stream()
//                .map(x -> x.getClass() == PersonContact.class ? ((PersonContact) x).toString() + "\n" : ((CompanyContact) x).toString() + "\n")
//                .forEach(System.out::println);

    }
}
