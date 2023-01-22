package contacts.commands;

import contacts.PhoneBook;

import java.util.Scanner;

public class AlterContactCommand  extends CompositeCommand {
    private PhoneBook phoneBook;
    private int recordNumber;
    private Scanner scanner;



    public AlterContactCommand(PhoneBook phoneBook, int recordNumber) {
        this.phoneBook = phoneBook;
        this.recordNumber = recordNumber;
        this.scanner = new Scanner(System.in);

        super.addChild("edit", new EditCommand(phoneBook));
        super.addChild("delete", new RemoveCommand(phoneBook));
        super.addChild("menu", null);
    }

    public void execute() {

    }

//    public void execute() {
//        Command currentCommand = null;
//        Invoker commandInvoker = new Invoker();
//        while (true) {
//            System.out.println("\n[record] Enter action (edit, delete, menu):");
//            String userInput = scanner.nextLine();
//
//            switch (userInput) {
//                case "remove":
////                    currentCommand = new RemoveCommand(this.phoneBook);
////                    commandInvoker.setCommand(currentCommand);
////                    commandInvoker.executeCommand();
//
//                    return;
//
//                case "edit":
//                    currentCommand = new EditCommand(this.phoneBook, recordNumber);
//                    commandInvoker.setCommand(currentCommand);
//                    commandInvoker.executeCommand();
//                    //Prints the edited record
//                    phoneBook.getContactByIndex(recordNumber - 1).toString();
//                    return;
//
//                case "menu":
//                    return;
//
//                default:
//                    System.out.println("Invalid input!");
//                    return;
//            }
//        }


//    private PhoneBook phoneBook;
//    private int recordNumber;
//    private Scanner scanner;
//
//    public AlterContactCommand(PhoneBook phoneBook, int recordNumber) {
//        this.phoneBook = phoneBook;
//        this.recordNumber = recordNumber;
//        this.scanner = new Scanner(System.in);
//    }
//
//    @Override
//    public void execute() {
//        Command currentCommand = null;
//        Invoker commandInvoker = new Invoker();
//        while (true) {
//            System.out.println("\n[record] Enter action (edit, delete, menu):");
//            String userInput = scanner.nextLine();
//
//            switch (userInput) {
//                case "remove":
//                    currentCommand = new RemoveCommand(this.phoneBook);
//                    commandInvoker.setCommand(currentCommand);
//                    commandInvoker.executeCommand();
//                    return;
//
//                case "edit":
//                    currentCommand = new EditCommand(this.phoneBook, recordNumber);
//                    commandInvoker.setCommand(currentCommand);
//                    commandInvoker.executeCommand();
//                    //Prints the edited record
//                    phoneBook.getContactByIndex(recordNumber - 1).toString();
//                    return;
//
//                case "menu":
//                    return;
//
//                default:
//                    System.out.println("Invalid input!");
//                    return;
//            }
//        }
//    }
}
