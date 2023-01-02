package contacts;

import java.util.Scanner;

public class InputManager {
    private Scanner scanner;
    private PhoneBook phoneBook;
    private Invoker commandInvoker;

    public InputManager() {
        scanner = new Scanner(System.in);

        phoneBook = new PhoneBook();
        commandInvoker = new Invoker();
    }

    public void manageUserInput() {
        Command currentCommand;

        while (true) {
            System.out.println("Enter action (add, remove, edit, count, info, exit):");
            String userInput = scanner.nextLine();

            switch(userInput) {
                case "add":
                    currentCommand = new AddCommand(this.phoneBook);
                    executeCurrentCommand(currentCommand, this.commandInvoker);
                    break;

                case "remove":
                    currentCommand = new RemoveCommand(this.phoneBook);
                    executeCurrentCommand(currentCommand, this.commandInvoker);
                    break;

                case "edit" :
                    currentCommand = new EditCommand(this.phoneBook);
                    executeCurrentCommand(currentCommand, this.commandInvoker);
                    break;

                case "count":
                    currentCommand = new CountCommand(this.phoneBook);
                    executeCurrentCommand(currentCommand, this.commandInvoker);
                    break;

                case "info":
                    currentCommand = new InfoCommand(this.phoneBook);
                    executeCurrentCommand(currentCommand, this.commandInvoker);
                    break;

                case "exit":
                    System.exit(0);

                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
    }

    private void executeCurrentCommand(Command command, Invoker commandInvoker) {
        commandInvoker.setCommand(command);
        commandInvoker.executeCommand();
    }
}
