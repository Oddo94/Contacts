package contacts.commands;

import contacts.PhoneBook;
import jdk.jshell.spi.ExecutionControl;

import java.util.Scanner;
import java.util.TreeMap;

public class SearchCommand extends CompositeCommand {
    private PhoneBook phoneBook;
    private Scanner scanner;
    private CompositeCommand menuCommand;

    public SearchCommand(TreeMap<String, Command> childCommands, CompositeCommand menuCommand, PhoneBook phoneBook) {
        super(childCommands);
        this.phoneBook = phoneBook;
        this.scanner = new Scanner(System.in);
        this.menuCommand = menuCommand;
    }

    public void execute() {
        System.out.println("Not implemented yet!");
    }
}
