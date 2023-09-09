package Ewallet;

import Payment.Payment;
import Stripe.Card;
import Tools.DateTime;
import Tools.TNGInfoValidation;

import java.util.Scanner;

public class TNGPayment extends Payment {
    private final String PAYMENT_METHOD = "TNG";
    private final String CURRENCY = "MYR";
    private final String PAYMENT_STATUS = "PAID";

    private String name;
    private String myKadNo;
    private String pinCode;

    public TNGPayment() {
    }

    public TNGPayment(String name, String myKadNo, String pinCode) {
        this.name = name;
        this.myKadNo = myKadNo;
        this.pinCode = pinCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMyKadNo() {
        return myKadNo;
    }

    public void setMyKadNo(String myKadNo) {
        this.myKadNo = myKadNo;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public boolean pay(double amount, Card card) {
        setBookingId(2);
        setPaymentMethod(PAYMENT_METHOD);
        setPaymentAmount(amount);
        setCurrency(CURRENCY);
        setPaymentDate(DateTime.getCurrentDate());
        setPaymentTime(DateTime.getCurrentTime());
        setPaymentStatus(PAYMENT_STATUS);

        insertPayment();
        return true;

//        Scanner input = new Scanner(System.in);
//
//        while (true) {
//            System.out.print("Enter your name: ");
//            String name = input.nextLine().trim();
//
//            if (TNGInfoValidation.nameValidator(name)) {
//                // While name is valid, break
//                break;
//            }
//        }
//
//        while(true) {
//            System.out.print("Enter your MyKad Number (No need put desh): ");
//            String myKadNo = input.next().trim();
//
//            MyKad myKadFormat = new MyKad(myKadNo);
//
//            if(myKadFormat.myKadNoFormatVerification()) {
//                String birthDate = myKadFormat.getMyKadNo().substring(0, 6);
//                String birthStateCode = myKadFormat.getMyKadNo().substring(6, 8);
//                String systemCode = myKadFormat.getMyKadNo().substring(8, 12);
//
//                MyKad myKad = new MyKad(myKadFormat.getMyKadNo(), birthDate, birthStateCode, systemCode);
//
//                if(myKad.myKadNoVerification()) {
//                    break;
//                }
//            }
//        }
//
//        while (true) {
//            System.out.print("Enter your pin code: ");
//            String pinCode = input.next().trim();
//
//            if (TNGInfoValidation.pinCodeVerification(pinCode)) {
//                // While pin code is valid, break
//                break;
//            }
//        }
    }

//    private void nameVerification(String name) {
//        // 定义一个正则表达式，表示只包含字母和空格的名字
//        String regex = "^[a-zA-Z\\s]+$";
//
//        // 使用正则表达式进行匹配
//        if (name.matches(regex)) {
//            System.out.println("Name is valid.");
//        } else {
//            System.out.println("Name is invalid. It should only contain letters and spaces.");
//        }
//    }
//
//    private void pinCodeVerification(String pinCode) {
//        String regex = "^(\\d{6})$";
//
//        // 使用正则表达式进行匹配
//        if (pinCode.matches(regex)) {
//            System.out.println("Pin number is valid.");
//        } else {
//            System.out.println("Pin number is invalid. It should follow the format dddddd.");
//        }
//    }
}
