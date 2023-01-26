package contacts.commands;

import contacts.PhoneBook;

import java.util.Scanner;
import java.util.TreeMap;

public class MenuCommand extends CompositeCommand {
    private Scanner scanner;
    private PhoneBook phoneBook;

    public MenuCommand(PhoneBook phoneBook) {
        this.scanner = new Scanner(System.in);
        this.phoneBook = phoneBook;
    }

    public void setChildCommands(TreeMap<String, Command> childCommands) {
        super.setChildCommands(childCommands);
    }

    @Override
    public void execute() {
        Command currentCommand;

        while(true) {
            System.out.println("\n[menu] Enter action (add, list, search, count, exit):");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "add":
                    super.getChild("add").execute();
                    break;

                case "list":
                    super.getChild("list").execute();
                    break;

                case "search":
                    super.getChild("search").execute();
                    break;

                case "count":
                    super.getChild("count").execute();
                    break;

                case "exit":
                    super.getChild("exit").execute();
                    break;
            }
        }
    }
}
