package contacts;

public class RemoveCommand implements Command {
    private int contactIndex;
    private PhoneBook phoneBook;

    public RemoveCommand(int contactIndex, PhoneBook phoneBook) {
        this.contactIndex = contactIndex;
        this.phoneBook = phoneBook;
    }

    @Override
    public int execute() {
        int lowerBoundIndex = 0;
        int realIndex = contactIndex - 1;
        int upperBoundIndex = phoneBook.getContactsList().size() - 1;

        //Checks if the index is out of bounds
        if(realIndex < lowerBoundIndex || realIndex > upperBoundIndex) {
            System.out.println("The provided index is out of bounds!");
            return -1;
        }

        int executionResult = phoneBook.removeContact2(realIndex);

        return executionResult;
    }
}
