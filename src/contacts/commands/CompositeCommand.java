package contacts.commands;

import java.util.Map;
import java.util.TreeMap;

public abstract class CompositeCommand implements Command {
    private TreeMap<String, Command> childCommands;

    public CompositeCommand() {
        this.childCommands = new TreeMap<String, Command>();
    }
    public CompositeCommand(TreeMap<String, Command> childCommands) {
        this.childCommands = childCommands;
    }

    protected void addChild(String commandName, Command command) {
        childCommands.put(commandName, command);
    }

    protected Command getChild(String commandName) {
        return childCommands.get(commandName);
    }

    protected void removeChild(String commandName) {
        childCommands.remove(commandName);
    }

    public void setChildCommands(TreeMap<String, Command> childCommands) {
        this.childCommands = childCommands;
    }

    public void execute() {
        for(Map.Entry<String, Command> currentEntry : childCommands.entrySet()) {
            if(currentEntry.getValue() != null) {
                currentEntry.getValue().execute();
            } else {
                return;
            }

        }
    }
}
