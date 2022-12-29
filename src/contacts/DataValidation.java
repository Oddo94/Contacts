package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {

    public boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }

        return hasCorrectFormat(phoneNumber) && isValidParentheses(phoneNumber) && hasValidGroups(phoneNumber);

    }

    private boolean hasCorrectFormat(String phoneNumber) {
        Pattern groupRegex = Pattern.compile("\\+?[\\w]+[()]?(\\s|-)?");
        Matcher groupMatcher = groupRegex.matcher(phoneNumber);

        return groupMatcher.matches();
    }

    private boolean isValidParentheses(String phoneNumber) {
        String[] groups = phoneNumber.split("(\\s|-)");

        if(groups.length > 1) {
            Pattern parenthesesRegex = Pattern.compile("^\\(\\w+\\)$");
            Matcher firstGroupMatcher = parenthesesRegex.matcher(groups[0]);
            Matcher secondGroupMatcher = parenthesesRegex.matcher(groups[1]);

            //When only the first or the second group contain parentheses
            if(firstGroupMatcher.matches() || secondGroupMatcher.matches()) {
                return true;
            }

        } else {
            //When there's a single group
            return true;
        }

        return false;
    }

    private boolean hasValidGroups(String phoneNumber) {
        String[] groups = phoneNumber.split("(\\s|-)");

        if(groups.length > 1) {
            if(groups[0].length() >= 1 && groups[0].length() <= 2) {
                Pattern groupContentRegex = Pattern.compile("[\\w+]{2,}");
                Matcher matcher = null;
                //Checks each group after the first ne to see if it contains the required characters and is at least two characters long
                for(int i = 1; i < groups.length; i++) {
                    matcher = groupContentRegex.matcher(groups[i]);

                    if(!matcher.matches()) {
                        return false;
                    }
                }
            }
        } else {
            Pattern generalRegex = Pattern.compile("[\\w+]");
            Matcher generalMatcher = generalRegex.matcher(phoneNumber);

            if(!generalMatcher.matches()) {
                return false;
            } else {
                return true;
            }
        }

        return false;
    }
}
