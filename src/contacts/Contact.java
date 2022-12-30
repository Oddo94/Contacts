package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Contact {
    protected String phoneNumber;
    protected LocalDateTime creationDate;
    protected LocalDateTime updatedDate;

    protected Contact() {

    }

    protected Contact(String phoneNumber, LocalDateTime creationDate, LocalDateTime updatedDate) {
        if(isValid(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "[no_number]";
        }
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
    }

    protected String getPhoneNumber() {
        return this.phoneNumber;
    }

    protected void setPhoneNumber(String phoneNumber) {
        if(isValid(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "[no_number]";
        }
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
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
}
