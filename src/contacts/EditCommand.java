package contacts;

public class EditCommand implements Command {
    private Contact contact;
    private int contactNumber;
    private PhoneBook phoneBook;

    public EditCommand(Contact contact, int contactNumber, PhoneBook phoneBook) {
        this.contact = contact;
        this.contactNumber = contactNumber;
        this.phoneBook = phoneBook;
    }


    @Override
    public int execute() {
        int executionResult = phoneBook.editRecord(contactNumber, contact);

        return executionResult;
    }
}
