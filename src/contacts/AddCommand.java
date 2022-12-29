package contacts;

public class AddCommand implements Command {
    private Contact contact;
    private PhoneBook phoneBook;

    public AddCommand(Contact contact, PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
        this.contact = contact;
    }
    @Override
    public int execute() {
        int executionResult = phoneBook.addContact(contact);

        return executionResult;
    }
}
