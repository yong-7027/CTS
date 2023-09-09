package Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TNGInfoValidation {
    private static final String NAME_REGEX = "^[a-zA-Z\\s]+$";
    private static final String PIN_CODE_REGEX = "^(\\d{6})$";
    private static final String MYKAD_REGEX = "^(\\d{6})(\\d{2})(\\d{4})$";
    public static boolean nameValidator(String name) {
        if (name.matches(NAME_REGEX)) {
            return true;
        }

        System.out.println("Name is invalid. It should only contain letters and spaces.");
        return false;
    }

    public static boolean myKadNoFormatVerification(String myKadNo) {
        // 使用正则表达式进行匹配
        if (myKadNo.matches(MYKAD_REGEX)) {
            return true;
        }

        System.out.println("MyKad number is invalid. You should follow the format of MyKad Number.");
        return false;
    }

    public static boolean birthDateVerification(String birthDate) {
        // 尝试将生日部分解析为日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        dateFormat.setLenient(false); // 关闭宽松解析

        try {
            Date validBirthDate = dateFormat.parse(birthDate);
            // 验证日期是否有效
            return isValidYear(validBirthDate);
        } catch (ParseException e) {
            System.out.println("MyKad number is invalid. Invalid date format.");
            return false;
        }
    }

    private static boolean isValidYear(Date date) {
        // 获取当前日期
        Date currentDate = new Date();

        // 创建 Calendar 对象以进行更多的日期验证
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 获取年份、月份和日期
        int year = calendar.get(Calendar.YEAR);

        // 检查年份是否在合理范围内（示例中假设合理范围是1900年到当前年份）
        if (year < 1900 || year > calendar.get(Calendar.YEAR)) {
            System.out.println("Invalid birthdate 's year.");
            return false; // 年份不在合理范围内，无效
        }

        // 将日期向前推18年
        calendar.add(Calendar.YEAR, 18);

        // 检查出生日期是否在18年前或在当日
        if (calendar.getTime().before(currentDate) || calendar.getTime().equals(currentDate)) {
            return true; // 年龄大于或等于18岁
        } else {
            System.out.println("Invalid TNG account, you are under 18.");
            return false; // 年龄不足18岁
        }
    }

    public static boolean birthStateCodeVerification(String birthStateCode) {
        // 定义一组允许的数字
        String[] allowedCodes = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "82"};

        // 遍历允许的数字数组
        for (String code : allowedCodes) {
            if (code.equals(birthStateCode)) {
                return true; // 如果匹配到任何一个允许的数字，返回true
            }
        }

        // 如果未匹配到任何一个允许的数字，返回false
        System.out.println("Invalid birth state code.");
        return false;
    }

    public static boolean pinCodeVerification(String pinCode) {
        // 使用正则表达式进行匹配
        if (pinCode.matches(PIN_CODE_REGEX)) {
            // Valid PIN
            return true;
        }

        System.out.println("Pin number is invalid. You should follow the 6 digits format.");
        return false;
    }
}
