package contacts.model;

import contacts.model.Contact;
import contacts.utils.enums.EditedElement;
import contacts.utils.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class PersonContact extends Contact {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Gender gender;

    public PersonContact() {}

    public PersonContact(String name, String surname, String phoneNumber, LocalDate birthDate, Gender gender, LocalDateTime createdDate, LocalDateTime updatedDate) {
        super(phoneNumber, createdDate, updatedDate);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        super.setPhoneNumber(phoneNumber);
    }

    public String toString() {
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedCreationDate = this.creationDate != null ? this.creationDate.format(customFormat) : "null";
        String formattedUpdatedDate = this.updatedDate != null ? this.updatedDate.format(customFormat) : "null";

        return String.format("Name: %s\nSurname: %s\nBirth date: %s\nGender: %s\nNumber: %s\nTime created: %s\nTime last edit: %s\n",
                this.name, this.surname, (this.birthDate != null ? this.birthDate : "[no data]"), this.gender.getGenderName(), this.phoneNumber, formattedCreationDate, formattedUpdatedDate);
    }


    private boolean isValid(String number) {

        // no group with brackets
        String regex1 = "[+]?[a-zA-Z0-9]?([\\s-]?[a-zA-Z0-9]{2,})*";

        // the first group has brackets
        String regex2 = "[+]?(\\([a-zA-Z0-9]+\\))([\\s-][a-zA-Z0-9]{2,})*";

        // the second group has brackets
        String regex3 = "[+]?[a-zA-Z0-9]{1,}[\\s-]\\([a-zA-Z0-9]{2,}\\)([\\s-][a-zA-Z0-9]{2,})*";

        if (number.matches(regex1) || number.matches(regex2) || number.matches(regex3)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected ArrayList<EditedElement> getUpdateableFields() {
        ArrayList<EditedElement> updateableFields = new ArrayList<>(Arrays.asList(EditedElement.NAME, EditedElement.SURNAME, EditedElement.BIRTH_DATE, EditedElement.GENDER, EditedElement.PHONE_NUMBER));

        return updateableFields;
    }

    @Override
    protected void updateSelectedField(EditedElement editedElement, String newValue) {
        switch(editedElement) {
            case NAME:
                this.name = newValue;

            case SURNAME:
                this.surname = newValue;

            case BIRTH_DATE:
                this.birthDate = LocalDate.parse(newValue);

            case GENDER:
                if("M".equals(newValue)) {
                    this.gender = Gender.MALE;
                } else if("F".equals(newValue)) {
                    this.gender = Gender.FEMALE;
                }

            case PHONE_NUMBER:
                super.setPhoneNumber(newValue);

            default:
                break;
        }
    }

    @Override
    protected String getSelectedField(EditedElement editedElement) {
        switch(editedElement) {
            case NAME:
                return this.name;

            case SURNAME:
               return this.surname;

            case BIRTH_DATE:
               return this.birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            case GENDER:
                return this.gender.getGenderName();

            case PHONE_NUMBER:
                return this.phoneNumber;

            default:
                return null;
        }
    }
}
