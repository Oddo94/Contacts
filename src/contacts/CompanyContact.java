package contacts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        String formattedCreationDate = this.creationDate.format(customFormat);
        String formattedUpdatedDate = this.updatedDate.format(customFormat);

        return String.format("Organization name: %s\nAddress: %s\nNumber: %s\nTime created: %s\nTime last edit: %s", this.organizationName, this.address, this.phoneNumber, formattedCreationDate, formattedUpdatedDate);
    }
}
