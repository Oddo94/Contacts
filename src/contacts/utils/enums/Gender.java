package contacts.utils.enums;

public enum Gender {
    MALE("M"),
    FEMALE("F"),
    UNDEFINED("[no data]");

    private String genderName;

    private Gender(String genderName) {
        this.genderName  = genderName;
    }

    public String getGenderName() {
        return this.genderName;
    }
}
