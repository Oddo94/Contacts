package contacts.commands;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeCommand implements Command {
    private List<Command> commandList;

    public CompositeCommand() {
        this.commandList = new ArrayList<>();
    }

    protected void addChild(Command command) {
        commandList.add(command);
    }

    protected Command getChild(int commandIndex) {
        return commandList.get(commandIndex);
    }

    protected void removeChild(Command command) {
        commandList.remove(command);
    }

    public void execute() {
        for(Command currentCommand : commandList) {
            currentCommand.execute();
        }
    }
}
