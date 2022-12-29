package contacts;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputManager {
    private Scanner scanner;
    private PhoneBook phoneBook;
    private Invoker commandInvoker;

    public InputManager() {
        scanner = new Scanner(System.in);
        //scanner.useDelimiter(System.lineSeparator());

        phoneBook = new PhoneBook();
        commandInvoker = new Invoker();
    }

    public Contact getContact() {
        System.out.println("Enter the name: ");
        String name = scanner.nextLine();

        System.out.println("Enter the surname: ");
        String surname = scanner.nextLine();

        System.out.println("Enter the number:");
        String phoneNumber = scanner.nextLine();

        Contact contact = new Contact(name, surname, phoneNumber);

        return contact;
    }

    public void manageUserInput() {
        int displayExecutionResult;
        int executionResult;

        while (true) {
            System.out.println("Enter action (add, remove, edit, count, list, exit):");
            String userInput = scanner.nextLine();

            switch(userInput) {
                case "add":
                    int result = executeAddCommand(this.phoneBook, this.commandInvoker);
                    if(result == 0) {
                        System.out.println("The record added.");
                    } else {
                        System.out.println("Unable to add the specified record!");
                    }
                    break;

                case "remove":
                   displayExecutionResult = executeListCommand(this.phoneBook, this.commandInvoker);

                    if(displayExecutionResult == -1) {
                        System.out.println("No records to remove!");
                    } else {
                        int removeExecutionResult = executeRemoveCommand(this.phoneBook, this.commandInvoker);
                        if (removeExecutionResult == 0) {
                            System.out.println("The record removed!");
                        }
                    }
                    break;

                case "edit" :
                    displayExecutionResult = executeListCommand(this.phoneBook, this.commandInvoker);

                    if(displayExecutionResult == -1) {
                        System.out.println("No records to edit!");
                    } else {
                        int editExecutionResult = executeEditCommand(this.phoneBook, this.commandInvoker);

                        if(editExecutionResult == 0) {
                            System.out.println("The record updated!");
                        }
                    }
                    break;


                case "count":
                    int numberOfRecords = executeCountCommand(this.phoneBook, this.commandInvoker);
                    System.out.println(String.format("The Phone Book has %d records.", numberOfRecords));
                    break;

                case "list":
                    executeListCommand(this.phoneBook, this.commandInvoker);
                    break;

                case "exit":
                    System.exit(0);

                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }

    }

    private void retrieveInputForCommand(CommandType commandType) {
        switch(commandType) {

        }
    }
    private int executeAddCommand(PhoneBook phoneBook, Invoker commandInvoker) {
//        System.out.println("Enter the name:");
//        String name = scanner.nextLine();
//
//        System.out.println("Enter the surname:");
//        String surname = scanner.nextLine();
//
//        System.out.println("Enter the phone number:");
//        String phoneNumber = scanner.nextLine();

        Contact contact = getContact();

        Command addCommand = new AddCommand(contact, phoneBook);
        commandInvoker.setCommand(addCommand);

        int executionResult = commandInvoker.executeCommand();

        return executionResult;
    }

    private int executeRemoveCommand(PhoneBook phoneBook, Invoker commandInvoker) {
        System.out.println("Select a record:");
        int recordNumber = scanner.nextInt();
        scanner.nextLine();

        Command removeCommand = new RemoveCommand(recordNumber, phoneBook);
        commandInvoker.setCommand(removeCommand);

        int executionResult = commandInvoker.executeCommand();

        return executionResult;
    }

    private int executeEditCommand(PhoneBook phoneBook, Invoker commandInvoker) {
        System.out.println("Select a record:");
        int recordNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Select a field (name, surname, number):");
        String fieldName = scanner.nextLine();

        String name = null;
        String surname = null;
        String phoneNumber = null;
        switch(fieldName) {
            case "name":
                System.out.println("Enter name:");
                name = scanner.nextLine();
                break;

            case "surname":
                System.out.println("Enter surname:");
                surname = scanner.nextLine();
                break;

            case "number":
                System.out.println("Enter number:");
                phoneNumber = scanner.nextLine();
                break;

            default:
                System.out.println("Invalid field name!");
                break;
        }

        Contact editedContact = new Contact(name, surname, phoneNumber);

        Command editCommand = new EditCommand(editedContact, recordNumber, phoneBook);
        commandInvoker.setCommand(editCommand);

        int executionResult = commandInvoker.executeCommand();

        return executionResult;
    }

    private int executeListCommand(PhoneBook phoneBook, Invoker commandInvoker) {
        Command listCommand = new ListCommand(phoneBook);
        commandInvoker.setCommand(listCommand);

        int executionResult = commandInvoker.executeCommand();

        return executionResult;
    }

    public int executeCountCommand(PhoneBook phoneBook, Invoker commandInvoker) {
        Command countCommand = new CountCommand(phoneBook);
        commandInvoker.setCommand(countCommand);

        int executionResult = commandInvoker.executeCommand();

        return executionResult;
    }




}
