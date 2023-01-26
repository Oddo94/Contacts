package contacts;

import contacts.io.FileManager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        //boolean saveContactsToFile = args[0] != null ? true : false;
        boolean saveContactsToFile = false;

        File file = null;
        FileManager fileManager= null;
        if(saveContactsToFile) {
              String workingDirectoryPath = System. getProperty("user.dir");
              System.out.println("WORKING DIRECTORY:" + workingDirectoryPath);

            file = new File(String.format("%s\\Contacts\\task\\src\\contacts\\resources\\%s", workingDirectoryPath, "phonebook.db"));
            fileManager = new FileManager(file);

            if(!file.exists()) {
                fileManager.createStorageFile();
            }
        }

           InputManager inputManager = new InputManager(saveContactsToFile, file);
           inputManager.manageUserInput();
    }
}
