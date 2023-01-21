package contacts;

import contacts.commands.*;

import java.io.File;
import java.util.Map;
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
        Command editCommand = new EditCommand(phoneBook, 1);
        CompositeCommand menuCommand = new MenuCommand(phoneBook);

        TreeMap<String, Command> commonChildCommands = new TreeMap<>();
        commonChildCommands.put("edit", new EditCommand(phoneBook, 1));
        commonChildCommands.put("delete", new RemoveCommand(phoneBook));
        commonChildCommands.put("menu", menuCommand);

        TreeMap<String, Command> listChildCommands = commonChildCommands;
        TreeMap<String, Command> searchChildCommands = commonChildCommands;

        Command addCommand = new AddCommand(phoneBook);
        Command listCommand = new ListCommandExtended(listChildCommands, menuCommand, phoneBook);
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

//    public void manageUserInput() {
//        Command currentCommand;
//
//        while (true) {
//            System.out.println("[menu] Enter action (add, list, search, count, exit):");
//            String userInput = scanner.nextLine();
//
//            switch(userInput) {
//                case "add":
//                    currentCommand = new AddCommand(this.phoneBook);
//                    executeCurrentCommand(currentCommand, this.commandInvoker);
//                    break;
//
//
//                case "list":
//                    currentCommand = new ListCommand(this.phoneBook);
//                    executeCurrentCommand(currentCommand, this.commandInvoker);
//                    break;
//
//                case "search":
//                    break;
//
////                case "remove":
////                    currentCommand = new RemoveCommand(this.phoneBook);
////                    executeCurrentCommand(currentCommand, this.commandInvoker);
////                    break;
//
////                case "edit" :
////                    currentCommand = new EditCommand(this.phoneBook);
////                    executeCurrentCommand(currentCommand, this.commandInvoker);
////                    break;
//
//                case "count":
//                    currentCommand = new CountCommand(this.phoneBook);
//                    executeCurrentCommand(currentCommand, this.commandInvoker);
//                    break;
//
//
//                case "exit":
//                    System.exit(0);
//
//                default:
//                    System.out.println("Invalid command!");
//                    break;
//            }
//        }
//    }


//    private void executeCurrentCommand(Command command, Invoker commandInvoker) {
//        commandInvoker.setCommand(command);
//        commandInvoker.executeCommand();
//    }

}
