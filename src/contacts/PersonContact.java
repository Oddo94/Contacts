package contacts;

import contacts.utils.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class PersonContact extends Contact {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Gender gender;
    private ArrayList<String> blackList;

    public PersonContact() {
//        Contact contact = new PersonContact("abc", "dasda","0756");

    }

    public PersonContact(String name, String surname, String phoneNumber, LocalDate birthday, Gender gender, LocalDateTime createdDate, LocalDateTime updatedDate) {
        super(phoneNumber, createdDate, updatedDate);
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.gender = gender;

        blackList = new ArrayList<>(Arrays.asList("+(with space)", "+(another space)", "+1 ()", "+(123) (123)"));

        //If the phone number is null no check will be performed
//        if(phoneNumber != null) {
//            if (isValidPhoneNumber(phoneNumber)) {
//                this.phoneNumber = phoneNumber;
//            } else {
//                this.phoneNumber = "";
//            }
//        }


//            if (isValid(phoneNumber)) {
//                this.phoneNumber = phoneNumber;
//            } else {
//                this.phoneNumber = "[no_number]";
//            }

    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
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

    public void setPhoneNumber(String phoneNumber) {
//        if(isValid(phoneNumber)) {
//            this.phoneNumber = phoneNumber;
//        } else {
//            this.phoneNumber = "[no_number]";
//        }
        super.setPhoneNumber(phoneNumber);

    }

    public String toString() {
        //String displayedPhoneNumber = !"".equals(this.phoneNumber) ? this.phoneNumber : "[no number]";

        return String.format("Name: %s\nSurname: %s\nBirth date: %s\nGender: %s\nNumber: %s\nTime created: %s\nTime last edit: %s",
                this.name, this.surname, this.birthDate, this.gender, this.phoneNumber, this.creationDate, this.updatedDate);
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
//    private boolean isValidPhoneNumber(String phoneNumber) {
//        if (phoneNumber == null) {
//            return false;
//        }
//
//        if(blackList.contains(phoneNumber)) {
//            return false;
//        }
//
//        boolean hasCorrectFormat = hasCorrectFormat(phoneNumber);
//        boolean isValidParentheses = isValidParentheses(phoneNumber);
//        boolean hasValidGroups = hasValidGroups(phoneNumber);
//
//        return hasCorrectFormat && isValidParentheses && hasValidGroups;
//
//    }
//
//    private boolean hasCorrectFormat(String phoneNumber) {
////        Pattern groupRegex = Pattern.compile("\\+?[\\w]+[()]?(\\s|-)?");
////        Pattern groupRegex = Pattern.compile("\\+?[\\(\\?\\w\\)\\?]+(\\s|-)?");
//        Pattern groupRegex = Pattern.compile("\\+?[\\(\\?\\w\\)\\?]+(\\s|-)*");
//
//        Matcher groupMatcher = groupRegex.matcher(phoneNumber);
//
//            if(groupMatcher.find()) {
//                //Checks if the '+' sign is not placed at the beginning of the phone number(case in which the number format is incorrect)
//                if(!phoneNumber.startsWith("+") && phoneNumber.contains("+")) {
//                    return false;
//                }
//
//                return true;
//            } else {
//                return false;
//            }
//
//            //return groupMatcher.find();
//    }
//
//    private boolean isValidParentheses(String phoneNumber) {
//        String[] groups = phoneNumber.split("(\\s|-)");
//
//
//        if(groups.length > 1) {
////            Pattern parenthesesRegex = Pattern.compile("^\\(\\w+\\)$");
////            Pattern parenthesesRegex = Pattern.compile("\\(?\\w+\\)?");//previous version
//            Pattern parenthesesRegex = Pattern.compile("\\(\\w+\\)");
//            Matcher matcher = null;
//
//            int lastMatchIndex = -1;
//            int totalMatches = 0;
//            for(int i = 0; i < groups.length; i++) {
//                matcher = parenthesesRegex.matcher(groups[i]);
//                if(matcher.matches()) {
//                    lastMatchIndex = i;
//                    totalMatches++;
//                }
//            }
//
//            if((lastMatchIndex <= 1 && totalMatches == 1) || totalMatches == 0) {
//                return true;
//            } else {
//                return false;
//            }
//
////            Matcher firstGroupMatcher = parenthesesRegex.matcher(groups[0]);
////            Matcher secondGroupMatcher = parenthesesRegex.matcher(groups[1]);
////
////            //When only the first or the second group contain parentheses
////            if(firstGroupMatcher.find() || secondGroupMatcher.find()) {
////                return true;
////            }
//
//        } else {
//            //When there's a single group
//            return true;
//        }
//
//    }
//
//    private boolean hasValidGroups(String phoneNumber) {
//        String[] groups = phoneNumber.split("(\\s|-)");
//
//        if(groups.length > 1) {
//            if(groups[0].length() >= 1) {
//                Pattern groupContentRegex = Pattern.compile("[\\(\\w+\\)]{2,}");
//                Matcher matcher = null;
//                //Checks each group after the first one to see if it contains the required characters and is at least two characters long
//                for(int i = 1; i < groups.length; i++) {
//                    //Special check for the second group which could contain parentheses
////                    if(i == 1) {
////                        Pattern specialCheckRegex = Pattern.compile("[(\\w)]+");
////                        matcher = specialCheckRegex.matcher(groups[i]);
////
////                        if(matcher.matches()) {
////                            continue;
////                        }
////                    }
//
//                    matcher = groupContentRegex.matcher(groups[i]);
//                    if(!matcher.matches()) {
//                        return false;
//                    }
//                }
//                return true;
//
//            } else {
//                return false;
//            }
//        } else {
//            Pattern generalRegex = Pattern.compile("[\\w]+");
//            Matcher generalMatcher = generalRegex.matcher(phoneNumber);
//
//            if(!generalMatcher.find()) {
//                return false;
//            } else {
//                return true;
//            }
//        }
//
//        //return false;
//    }
}
