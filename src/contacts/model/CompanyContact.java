package contacts.model;

import contacts.model.Contact;
import contacts.utils.enums.EditedElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class CompanyContact extends Contact {
    private String organizationName;
    private String address;

    public CompanyContact(String organizationName, String address, String phoneNumber, LocalDateTime creationDate, LocalDateTime updatedDate) {
        super(phoneNumber, creationDate, updatedDate);
        this.organizationName = organizationName;
        this.address = address;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getAddress() {
        return address;
    }

    public String toString() {
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedCreationDate = this.creationDate != null ? this.creationDate.format(customFormat) : "null";
        String formattedUpdatedDate = this.updatedDate != null ? this.updatedDate.format(customFormat) : "null";

        return String.format("Organization name: %s\nAddress: %s\nNumber: %s\nTime created: %s\nTime last edit: %s", this.organizationName, this.address, this.phoneNumber, formattedCreationDate, formattedUpdatedDate);
    }

    @Override
    protected ArrayList<EditedElement> getUpdateableFields() {
        ArrayList<EditedElement> updateableFields = new ArrayList<>(Arrays.asList(EditedElement.NAME, EditedElement.ADDRESS, EditedElement.PHONE_NUMBER));

        return updateableFields;
    }

    @Override
    protected void updateSelectedField(EditedElement editedElement, String newValue) {
        switch(editedElement) {
            case NAME:
                this.organizationName = newValue;

            case ADDRESS:
                this.address = newValue;

            case PHONE_NUMBER:
                super.setPhoneNumber(newValue);

            default:
                break;
        }
    }

    @Override
    protected String getSelectedField(EditedElement editedElement) {
        switch (editedElement) {
            case NAME:
                return this.organizationName;

            case ADDRESS:
                return this.address;

            case PHONE_NUMBER:
                return this.phoneNumber;

            default:
                return null;
        }
    }
}
