package contacts.io;

import contacts.model.Contact;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private File storageFile;

    public FileManager(File file) {
        this.storageFile = file;
    }

    public void saveContactsToFile(ArrayList<Contact> contactList) {
        if(contactList == null || storageFile == null) {
            return;
        }

        try (FileOutputStream fileOutStream = new FileOutputStream(storageFile);
             ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream)) {

            for (Contact contact : contactList) {
                objectOutStream.writeObject(contact);
            }
        } catch(FileNotFoundException ex) {
            System.out.println(String.format("Unable to find the specified file!Reason: %s", ex.getMessage()));
        } catch(IOException ex) {
            System.out.println(String.format("Error initializing stream!Reason: %s", ex.getMessage()));
        }

    }

    public ArrayList<Contact> getContactsFromFile() {
        if( storageFile == null) {
            return new ArrayList<Contact>();
        }

        ArrayList<Contact> contactList = new ArrayList<>();
        try (FileInputStream fileInStream = new FileInputStream(storageFile);
             ObjectInputStream objectInStream = new ObjectInputStream(fileInStream)) {

            while (true) {
                try {
                    Contact contact = (Contact) objectInStream.readObject();
                    contactList.add(contact);
                } catch(EOFException ex) {
                    break;
                }
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Error while deserializing the current object!");
        } catch(FileNotFoundException ex) {
            System.out.println(String.format("Unable to find the specified file!Reason: %s", ex.getMessage()));
        } catch(IOException ex) {
            System.out.println(String.format("Error initializing stream!Reason: %s", ex.getMessage()));
        }

        return contactList;
    }

    public void createStorageFile() {
        if(storageFile == null) {
            return;
        }

        try {
            storageFile.createNewFile();
        } catch (IOException ex) {
            System.err.println(String.format("Unable to create the storage file!Reason: %s", ex.getMessage()));
        }

    }
}
