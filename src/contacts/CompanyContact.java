package contacts;

import java.time.LocalDateTime;

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
        return String.format("Organization name: %s\nAddress: %s\nNumber: %s\nTime created: %s\nTime last edit: %s", this.organizationName, this.address, this.phoneNumber, this.creationDate, this.updatedDate);
    }
}
