package contacts;

import contacts.utils.enums.ContactType;
import contacts.utils.enums.EditedElement;

import java.util.Scanner;

public class EditCommand implements Command {
    private PhoneBook phoneBook;
    private Scanner scanner;

    public EditCommand(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
        this.scanner = new Scanner(System.in);
    }


    @Override
    public void execute() {
        int executionResult = -1;

        if(phoneBook.getNumberOfContacts() == 0) {
            System.out.println("No records to edit!");
            return;
        }

        System.out.println("Select a record:");
        int recordNumber = scanner.nextInt();
        scanner.nextLine();
        int actualIndex = recordNumber - 1;


        Class contactClass = phoneBook.getContactByIndex(recordNumber).getClass();

        if(contactClass == PersonContact.class) {
            executionResult = updatePersonContact(actualIndex);
        } else if (contactClass == CompanyContact.class) {
            executionResult = updateCompanyContact(actualIndex);
        }



        if(executionResult == -1) {
            System.out.println("Unable to update the specified record!");
        } else {
            System.out.println("The record updated!");
        }


    }

    public int updatePersonContact(int index) {
        int executionResult = -1;

        System.out.println("Select a field (name, surname, birth, gender, number):");
        String fieldName = scanner.nextLine();

        switch(fieldName) {
            case "name":
                System.out.println("Enter name:");
                String name = scanner.nextLine();
                executionResult = phoneBook.editRecord(index, ContactType.PERSON, EditedElement.NAME, name);
                break;

            case "surname":
                System.out.println("Enter surname:");
                String surname = scanner.nextLine();
                executionResult = phoneBook.editRecord(index, ContactType.PERSON, EditedElement.SURNAME, surname);
                break;

            case "birth":
                System.out.println("Enter birth date(DD-MM-YYYY):");
                String birthDate = scanner.nextLine();
                executionResult = phoneBook.editRecord(index, ContactType.PERSON, EditedElement.BIRTH_DATE, birthDate);
                break;

            case "gender":
                System.out.println("Enter gender(M/F):");
                String gender = scanner.nextLine();
                executionResult = phoneBook.editRecord(index, ContactType.PERSON, EditedElement.GENDER, gender);
                break;

            case "number":
                System.out.println("Enter number:");
                String phoneNumber = scanner.nextLine();
                executionResult = phoneBook.editRecord(index, ContactType.PERSON, EditedElement.PHONE_NUMBER, phoneNumber);
                break;

            default:
                System.out.println("Invalid field name!");
                break;
        }

        return executionResult;
    }

    public int updateCompanyContact(int index) {
        int executionResult = -1;

        System.out.println("Select a field (address, number):");
        String fieldName = scanner.nextLine();

        switch(fieldName) {
            case "address":
                System.out.println("Enter address:");
                String address = scanner.nextLine();
                executionResult = phoneBook.editRecord(index, ContactType.COMPANY, EditedElement.ADDRESS, address);
                break;

            case "number":
                System.out.println("Enter number:");
                String phoneNumber = scanner.nextLine();
                executionResult = phoneBook.editRecord(index, ContactType.COMPANY, EditedElement.PHONE_NUMBER, phoneNumber);
                break;

            default:
                System.out.println("Invalid field name!");
                break;
        }

        return executionResult;

    }
}
