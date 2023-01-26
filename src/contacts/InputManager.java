package contacts;

import contacts.commands.*;

import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;

public class InputManager {
    private Scanner scanner;
    private PhoneBook phoneBook;
    private Invoker commandInvoker;

    public InputManager(boolean saveContactsToFile, File storageFile) {
        scanner = new Scanner(System.in);
        commandInvoker = new Invoker();

        phoneBook = new PhoneBook(saveContactsToFile, storageFile);
    }

    public void manageUserInput() {
        //Creates the command tree
        Command editCommand = new EditCommand(phoneBook);
        CompositeCommand menuCommand = new MenuCommand(phoneBook);

        TreeMap<String, Command> commonChildCommands = new TreeMap<>();
        commonChildCommands.put("edit", new EditCommand(phoneBook));
        commonChildCommands.put("delete", new DeleteCommand(phoneBook));
        commonChildCommands.put("menu", menuCommand);

        TreeMap<String, Command> listChildCommands = commonChildCommands;
        TreeMap<String, Command> searchChildCommands = commonChildCommands;

        Command addCommand = new AddCommand(phoneBook);
        Command listCommand = new ListCommand(listChildCommands, menuCommand, phoneBook);
        CompositeCommand searchCommand = new SearchCommand(searchChildCommands, menuCommand, phoneBook);
        Command countCommand = new CountCommand(phoneBook);
        Command exitCommand = new ExitCommand();

        TreeMap<String, Command> menuChildCommands = new TreeMap<>();
        menuChildCommands.put("add", addCommand);
        menuChildCommands.put("list", listCommand);
        menuChildCommands.put("search", searchCommand);
        menuChildCommands.put("count", countCommand);
        menuChildCommands.put("exit", exitCommand);

        menuCommand.setChildCommands(menuChildCommands);

        menuCommand.execute();

    }
}
