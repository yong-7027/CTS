package Ewallet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyKad {
    private String myKadNo;
    private String birthDate;
    private String birthStateCode;
    private String systemCode;

    public MyKad() {
    }

    public MyKad(String myKadNo) {
        this.myKadNo = myKadNo;
    }

    public MyKad(String myKadNo, String birthDate, String birthStateCode, String systemCode) {
        this.myKadNo = myKadNo;
        this.birthDate = birthDate;
        this.birthStateCode = birthStateCode;
        this.systemCode = systemCode;
    }


    //    public MyKad(String myKadNumber) {
//        birthDate = myKadNumber.substring(0, 6);
//        birthStateCode = myKadNumber.substring(6, 8);
//        systemCode = myKadNumber.substring(8, 12);
//    }


    public String getMyKadNo() {
        return myKadNo;
    }

    public void setMyKadNo(String myKadNo) {
        this.myKadNo = myKadNo;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthStateCode() {
        return birthStateCode;
    }

    public void setBirthStateCode(String birthStateCode) {
        this.birthStateCode = birthStateCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public boolean myKadNoVerification() {

        if (myKadNoFormatVerification()) {

            if(birthDateVerification()) {
                if(birthStateCodeVerification()) {
                    return true;
                }

                else {
                    System.out.println("invalid birth state code.");

                    return false;
                }
            }

            else {
                return false;
            }
        }

        else {
            System.out.println("MyKad number is invalid. It should follow the format of MyKad Number.");

            return false;
        }
    }

    public boolean myKadNoFormatVerification() {
        // 验证身份证号码的正则表达式
        String regex = "^(\\d{6})(\\d{2})(\\d{4})$";

        // 使用正则表达式进行匹配
        if (myKadNo.matches(regex)) {
            return true;
        }

        System.out.println("MyKad number is invalid. It should follow the format of MyKad Number.");

        return false;
    }

    private boolean birthDateVerification() {
        // 尝试将生日部分解析为日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        dateFormat.setLenient(false); // 关闭宽松解析

        try {
            Date birthday = dateFormat.parse(birthDate);
            // 验证日期是否有效
            if (isValidDate(birthday)) {
                return true;
            }

        } catch (ParseException e) {
            System.out.println("MyKad number is invalid. Invalid date format.");
        }

        return false;
    }

    // 验证日期是否有效
    private boolean isValidDate(Date date) {
        // 获取当前日期
        Date currentDate = new Date();

        // 创建 Calendar 对象以进行更多的日期验证
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 获取年份、月份和日期
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // 月份从0开始，所以需要加1
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // 检查年份是否在合理范围内（示例中假设合理范围是1900年到当前年份）
        if (year < 1850 || year > calendar.get(Calendar.YEAR)) {
            return false; // 年份不在合理范围内，无效
        }

        // 检查月份是否在1到12之间
        if (month < 1 || month > 12) {
            System.out.println("Invalid MyKad Number. Your month is invalid.");

            return false; // 月份无效
        }

        // 检查日期是否在每个月内的有效范围内
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                if (day < 1 || day > 31) {
                    System.out.println("Invalid MyKad Number. Your day is invalid.");

                    return false; // 31天的月份
                }
                break;
            case 4: case 6: case 9: case 11:
                if (day < 1 || day > 30) {
                    System.out.println("Invalid MyKad Number. Your day is invalid.");

                    return false; // 30天的月份
                }
                break;
            case 2:
                // 判断闰年
                boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                if (isLeapYear) {
                    if (day < 1 || day > 29) {
                        System.out.println("Invalid MyKad Number. Your day is invalid.");

                        return false; // 闰年的2月
                    }
                } else {
                    if (day < 1 || day > 28) {
                        System.out.println("Invalid MyKad Number. Your day is invalid.");

                        return false; // 非闰年的2月
                    }
                }
                break;
        }

        // 将日期向前推18年
        calendar.add(Calendar.YEAR, 18);

        // 检查出生日期是否在18年前或在当日
        if (calendar.getTime().before(currentDate) || calendar.getTime().equals(currentDate)) {
            return true; // 年龄大于或等于18岁
        } else {
            System.out.println("Invalid MyKad Number. Your are under 18.");

            return false; // 年龄不足18岁
        }
    }

    private boolean birthStateCodeVerification() {
        // 定义一组允许的数字
        String[] allowedCodes = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "82"};

        // 遍历允许的数字数组
        for (String code : allowedCodes) {
            if (code.equals(birthStateCode)) {
                return true; // 如果匹配到任何一个允许的数字，返回true
            }
        }

        // 如果未匹配到任何一个允许的数字，返回false
        return false;
    }
}
