package contacts;

import contacts.utils.enums.ContactType;
import contacts.utils.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddCommand implements Command {
    private PhoneBook phoneBook;
    private Scanner scanner;

    public AddCommand(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
        this.scanner = new Scanner(System.in);
    }
    @Override
    public void execute() {
        System.out.println("Enter the type (person, organization):");
        ContactType contactType = getContactType(scanner.nextLine());

        if(contactType == ContactType.UNDEFINED) {
            System.out.println("Bad contact type!");
        }

        System.out.println("Enter the gender (M, F):");
        Gender gender = getGender(scanner.nextLine());

        if(gender == Gender.UNDEFINED) {
            System.out.println("Bad gender!");
        }

        System.out.println("Enter the birth date(DD-MM-YYYY):");
        LocalDate birthDay = null;

        try {
            birthDay =  getBirthday(scanner.nextLine());
        } catch(DateTimeParseException ex) {
            System.out.println("Bad birth date!");
        }

        System.out.println();

        System.out.println("Enter the name: ");
        String name = scanner.nextLine();

        System.out.println("Enter the surname: ");
        String surname = scanner.nextLine();

        System.out.println("Enter the number:");
        String phoneNumber = scanner.nextLine();

        PersonContact contact = new PersonContact(name, surname, phoneNumber, birthDay, gender, LocalDateTime.now(), null);

        int executionResult = phoneBook.addContact(contact);

        if (executionResult == -1) {
            System.out.println("Unable to add the specified record!");
        } else {
            System.out.println("The record added.");
        }
    }

    private ContactType getContactType(String input) {
        ContactType contactType;
        switch(input) {
            case "person":
                contactType = ContactType.PERSON;
                break;

            case "organization":
                contactType = ContactType.ORGANIZATION;
                break;

            default:
                contactType = ContactType.UNDEFINED;

        }

        return contactType;
    }

    private Gender getGender(String input) {
        Gender gender;

        switch(input) {
            case "M":
                gender = Gender.MALE;
                break;

            case "F":
                gender = Gender.FEMALE;
                break;

            default:
                gender = Gender.UNDEFINED;
        }

        return gender;
    }

    private LocalDate getBirthday(String input) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDay = LocalDate.parse(input, dateTimeFormatter);

        return birthDay;
    }
}
