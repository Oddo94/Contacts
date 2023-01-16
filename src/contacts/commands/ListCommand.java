package contacts.commands;

import contacts.PhoneBook;

import java.util.Scanner;

public class ListCommand implements Command {
    private PhoneBook phoneBook;
    private Scanner scanner;

    public ListCommand(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        phoneBook.displayContacts(phoneBook.getContactsList(), false);

        while(true) {
            System.out.println("[list] Enter action ([number], back):");
            String userInput = scanner.nextLine();

            try {
                while(true) {
                    int recordNumber = Integer.parseInt(userInput);
                    int recordIndex = recordNumber - 1;

                    phoneBook.displayContactByIndex(recordIndex, phoneBook.getContactsList());

//                    Command alterContactCommand = new AlterContactCommand(this.phoneBook, recordNumber);
//                    Invoker commandInvoker = new Invoker();
//                    commandInvoker.setCommand(alterContactCommand);
//                    commandInvoker.executeCommand();
                }

            } catch(NumberFormatException ex) {
                if("back".equals(userInput)) {
                    return;
                } else {
                    System.out.println("Invalid command!");
                }
            }

        }
    }
}
