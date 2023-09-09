package Tools;

public class CardInfoValidation {
    private static final String EXPIRED_DATE_REGEX = "^\\d{2}/\\d{2}$";

    public static boolean expiredDateFormatValidation(String expiredDate) {
        if(expiredDate.matches(EXPIRED_DATE_REGEX)) {
            return true;
        }

        System.out.println("Invalid expired date format. Please follow the format.\n");

        return false;
    }

    public static int getExpiredMonth(String expiredDate){
        // Date user entered
        String[] monthYear = expiredDate.split("/");

        return Integer.parseInt(monthYear[0]);
    }

    public static int getExpiredYear(String expiredDate){
        // Date user entered
        String[] monthYear = expiredDate.split("/");

        return Integer.parseInt(monthYear[1]);
    }
}
