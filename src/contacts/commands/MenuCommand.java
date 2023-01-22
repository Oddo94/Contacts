package contacts.commands;

import contacts.PhoneBook;

import java.util.Scanner;
import java.util.TreeMap;

public class MenuCommand extends CompositeCommand {
    private Scanner scanner;
    private PhoneBook phoneBook;
    private TreeMap<String, Command> childCommands;

    public MenuCommand(PhoneBook phoneBook) {
        //super(childCommands);
        this.scanner = new Scanner(System.in);
//        this.childCommands.put("add", new AddCommand(phoneBook));
//        this.childCommands.put("delete", new RemoveCommand(phoneBook));
//        this.childCommands.put("list", new ListCommandExtended());
//
//        TreeMap<String, Command> listChildCommands = new TreeMap<>();
//        listChildCommands.put("edit", new EditCommand(phoneBook, 1));
//        listChildCommands.put("delete", new RemoveCommand(phoneBook));
//        listChildCommands.put("menu", this);



    }

    public void setChildCommands(TreeMap<String, Command> childCommands) {
        super.setChildCommands(childCommands);
        //this.childCommands = childCommands;
    }

    @Override
    public void execute() {
        Command currentCommand;

        System.out.println("[menu] Enter action (add, list, search, count, exit):");
        String userInput = scanner.nextLine();

        switch (userInput) {
            case "add":
                super.getChild("add").execute();
                //this.getChild("add").execute();
                //childCommands.get("add").execute();
                break;


            case "list":
                super.getChild("list").execute();
                //this.getChild("list").execute();
                //childCommands.get("list").execute();
                break;

            case "search":
                break;

            case "exit":
                super.getChild("exit").execute();
                break;
        }
    }
}
