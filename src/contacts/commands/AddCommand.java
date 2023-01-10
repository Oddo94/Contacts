package contacts;

import contacts.utils.enums.ContactType;
import contacts.utils.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        Contact contact = null;
        if(contactType == ContactType.PERSON) {
            contact = getPersonContact();
        } else if (contactType == ContactType.COMPANY) {
            contact = getCompanyContact();
        } else {
            System.out.println("Bad contact type!");
            return;
        }

        int executionResult = phoneBook.addContact(contact);

        if (executionResult == -1) {
            System.out.println("Unable to add the specified record!");
        } else {
            System.out.println("The record added.");
        }

        System.out.println();
    }

    private PersonContact getPersonContact() {
        System.out.println("Enter the name: ");
        String name = scanner.nextLine();

        System.out.println("Enter the surname: ");
        String surname = scanner.nextLine();

        System.out.println("Enter the birth date:");
        LocalDate birthDay = null;
        try {
            birthDay =  getBirthday(scanner.nextLine());
        } catch(DateTimeParseException ex) {
            System.out.println("Bad birth date!");
            birthDay = null;
        }

        System.out.println("Enter the gender (M, F):");
        Gender gender = getGender(scanner.nextLine());

        if(gender == Gender.UNDEFINED) {
            System.out.println("Bad gender!");
        }

        System.out.println("Enter the number:");
        String phoneNumber = scanner.nextLine();

        PersonContact personContact = new PersonContact(name, surname, phoneNumber, birthDay, gender, LocalDateTime.now(), LocalDateTime.now());

        return personContact;

    }

    private CompanyContact getCompanyContact() {
        System.out.println("Enter the organization name:");
        String name = scanner.nextLine();

        System.out.println("Enter the address:");
        String address = scanner.nextLine();

        System.out.println("Enter the number:");
        String number = scanner.nextLine();

        CompanyContact companyContact = new CompanyContact(name, address, number, LocalDateTime.now(), LocalDateTime.now());

        return companyContact;
    }

    private ContactType getContactType(String input) {
        ContactType contactType;
        switch(input) {
            case "person":
                contactType = ContactType.PERSON;
                break;

            case "organization":
                contactType = ContactType.COMPANY;
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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDay = LocalDate.parse(input, dateTimeFormatter);

        return birthDay;
    }
}
