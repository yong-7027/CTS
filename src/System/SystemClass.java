package System;

import Cash.Employee;
import Ewallet.MyKad;
import Ewallet.TNGPayment;
import Payment.*;
import Discount.CustPromoCode;
import PaymentMethod.DefaultPaymentDAO;
import PaymentMethod.DefaultPaymentDAOImpl;
import PaymentMethod.DefaultPaymentMethod;
import Stripe.*;
import Tools.CardInfoValidation;
import Tools.TNGInfoValidation;
import com.stripe.model.Charge;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SystemClass {
    public int getUserId(Scanner input) {
        System.out.print("Enter your user id: ");

        int userId = input.nextInt();

        // Consume carriage returns
        input.nextLine();

        return userId;
    }

    public void makePayment(Scanner input) throws SQLException {
        double amount = 25;

        CustPromoCode custPromoCode = new CustPromoCode();

        int userId = getUserId(input);

        //custPromoCode.getPromoCode(userId);

        System.out.print("\nDo you want to apply promotion code? (Y/N) : ");
        String applyPromoCode = input.nextLine().trim();

        if(applyPromoCode.equals("Y") || applyPromoCode.equals("y")) {
            // Invoke applyPromotionCode method to let user apply promotion code
            Object[] result = applyPromotionCode(custPromoCode, input, userId);

            String code = (String) result[0];
            double discountValue = (double) result[1];

            // Deduct discount value
            amount -= discountValue;

            if(discountValue > 0) {
                System.out.println("\nDiscount " + discountValue + ". Your current amount is: " + amount);
            }

            System.out.print("\nContinue to place order? (Y/N) : ");
            String placeOrder = input.nextLine().trim();

            if(placeOrder.equals("Y") || placeOrder.equals("y")) {
                // No default payment method
                int option = 2;

                DefaultPaymentDAOImpl defaultPaymentDAO = new DefaultPaymentDAOImpl();
                DefaultPaymentMethod defaultPaymentMethod = new DefaultPaymentMethod();
                String activePaymentMethod = null;

                // Check the user has any default payment method or not
                // Each user has only one default payment method in status "ACTIVE", so if found, the payment method will be in the [0]
                if (defaultPaymentMethod.isDefaultPayment(defaultPaymentDAO.getAll(), userId, "ACTIVE") != null) {
                    activePaymentMethod = defaultPaymentMethod.isDefaultPayment(defaultPaymentDAO.getAll(), userId, "ACTIVE")[0];
                }

                if (activePaymentMethod != null) {
                    // User has default payment method
                    System.out.printf("Your default payment method is %s. Do you want to continue to complete your payment? \n", activePaymentMethod);
                    System.out.println("1. Complete payment");
                    System.out.println("2. Change payment method");
                    System.out.println("3. Back");

                    // Set option to default payment method
                    option = input.nextInt();
                    input.nextLine();
                }

                if (option == 1) {
                    // Directly insert payment
                    if (activePaymentMethod.equals("CREDIT/DEBIT CARD")) {
                        Payment payment = new Card();

                        payment.pay(amount, null);
                    } else {
                        Payment payment = new TNGPayment();

                        payment.pay(amount, null);
                    }
                } else if (option == 2) {
                    System.out.println("Payment Method");
                    System.out.println("1. Credit/Debit Card");
                    System.out.println("2. Touch 'n Go");
                    System.out.print("Select your payment method (X - Exit) : ");

                    int paymentMethod = input.nextInt();

                    // Consume carriage returns
                    input.nextLine();

                    Payment payment;

                    while (true) {
                        if (paymentMethod == 1) {
                            // Set default payment method
                            updateDefaultPayment(input, defaultPaymentMethod, defaultPaymentDAO, userId, "CREDIT/DEBIT CARD");

                            // Process Credit/Debit Card Payment
                            payment = new Card();

                            Card card;

                            do {
                                card  = cardPayment(payment, input);
                                System.out.print("\n");
                            } while (!payment.pay(amount, card));

                            break;
                        } else if (paymentMethod == 2) {
                            // Set default payment method
                            updateDefaultPayment(input, defaultPaymentMethod, defaultPaymentDAO, userId, "TNG");

                            // Process TNG Payment
                            payment = new TNGPayment();

                            if (tngPayment(payment, input)) {
                                payment.pay(amount, null);
                            }

                            break;
                        } else {
                            System.out.println("Invalid selection. Please retry.");
                        }
                    }
                }

                if(discountValue > 0) {
                    // User use promotion code, update promotion code status
                    custPromoCode.updatePromoCodeStatus(userId, code);
                }
            }
        }
    }

    public Object[] applyPromotionCode(CustPromoCode custPromoCode, Scanner input, int userId) {
        double discountValue = 0;
        String code = null;

        while(discountValue == 0) {
            System.out.print("\nEnter your promotion code (X - Exit) : ");
            code = input.nextLine().trim();

            discountValue = custPromoCode.promoCodeVerification(userId, code);

            if(code.equals("X") || code.equals("x")) {
                break;
            }

            if(discountValue == 0) {
                // Invalid promotion code
                System.out.println("Invalid Promotion Code. Please Retry.");
            }
        }

        return new Object[]{code, discountValue};
    }

    public boolean tngPayment(Payment payment, Scanner input) {
        if (payment instanceof TNGPayment) {
            TNGPayment tngPayment = (TNGPayment) payment;

            while (true) {
                System.out.print("Enter your name: ");
                String name = input.nextLine().trim();

                if (TNGInfoValidation.nameValidator(name)) {
                    // While name is valid, set name
                    tngPayment.setName(name);
                    break;
                } else if (name.equals("X") || name.equals("x")) {
                    return false;
                }
            }

            while(true) {
                System.out.print("Enter your MyKad Number (No need put '-'): ");
                String myKadNo = input.nextLine().trim();

                if(TNGInfoValidation.myKadNoFormatVerification(myKadNo)) {
                    String birthDate = myKadNo.substring(0, 6);
                    String birthStateCode = myKadNo.substring(6, 8);

                    if (TNGInfoValidation.birthDateVerification(birthDate) && TNGInfoValidation.birthStateCodeVerification(birthStateCode)) {
                        tngPayment.setMyKadNo(myKadNo);
                        break;
                    }
                } else if (myKadNo.equals("X") || myKadNo.equals("x")) {
                    return false;
                }
            }

            while (true) {
                System.out.print("Enter your pin code: ");
                String pinCode = input.nextLine().trim();

                if (TNGInfoValidation.pinCodeVerification(pinCode)) {
                    // While pin code is valid, set PIN
                    tngPayment.setPinCode(pinCode);
                    break;
                } else if (pinCode.equals("X") || pinCode.equals("x")) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public Card cardPayment(Payment payment, Scanner input) {
        if(payment instanceof Card) {
            Card card = (Card) payment;

            System.out.print("\nEnter card number: ");
            String cardNo = input.nextLine().trim();

            if (cardNo.equals("X") || cardNo.equals("x")) {
                return null;
            }

            String expiredDate;

            do {
                System.out.print("Enter expired date (mm/yy): ");
                expiredDate = input.nextLine().trim();

                if (expiredDate.equals("X") || expiredDate.equals("x")) {
                    return null;
                }
            } while(!CardInfoValidation.expiredDateFormatValidation(expiredDate));

            System.out.print("Enter Cvc: ");
            String cvc = input.nextLine().trim();

            if (cvc.equals("X") || cvc.equals("x")) {
                return null;
            }

            System.out.print("Enter email: ");
            String email = input.nextLine().trim();

            if (email.equals("X") || email.equals("x")) {
                return null;
            }

            return new Card(cardNo, expiredDate, cvc, email);
        }

        return null;
    }

    public void updateDefaultPayment(Scanner input, DefaultPaymentMethod defaultPaymentMethod, DefaultPaymentDAO defaultPaymentDAO, int userId, String paymentMethod) throws SQLException {
        // Set default payment method
        System.out.printf("Do you want to set the %s as your default payment method? (Y/N) : ", paymentMethod);
        String setDefaultPayment = input.nextLine().trim();

        // Default is first time to set default payment method
        int update = 0;

        if (setDefaultPayment.equals("Y") || setDefaultPayment.equals("y")) {
            // If user want to set default payment method
            if (paymentMethod.equals("CREDIT/DEBIT CARD")) {
                DefaultPaymentMethod inactiveDefaultPayment = new DefaultPaymentMethod(userId, "TNG", "INACTIVE");
                if (defaultPaymentDAO.delete(inactiveDefaultPayment) == 1) {
                    System.out.println("Delete successfully");
                }
            } else {
                DefaultPaymentMethod inactiveDefaultPayment = new DefaultPaymentMethod(userId, "CREDIT/DEBIT CARD", "INACTIVE");
                if (defaultPaymentDAO.delete(inactiveDefaultPayment) == 1) {
                    System.out.println("Delete successfully");
                }
            }

            String[] inactivePaymentMethod = null;

            if (defaultPaymentMethod.isDefaultPayment(defaultPaymentDAO.getAll(), userId, "INACTIVE") != null) {
                inactivePaymentMethod = defaultPaymentMethod.isDefaultPayment(defaultPaymentDAO.getAll(), userId, "INACTIVE");

                for (String userPaymentMethod : inactivePaymentMethod) {
                    if (userPaymentMethod != null && userPaymentMethod.equals(paymentMethod)) {
                        // If found, update status to "ACTIVE"
                        DefaultPaymentMethod updateDefaultPayment = new DefaultPaymentMethod(userId, userPaymentMethod, "ACTIVE");
                        update = defaultPaymentDAO.update(updateDefaultPayment);

                        if (update == 1) {
                            System.out.println("Set successfully.");
                        }

                        break;
                    }
                }
            }

            if (update == 0) {
                // First time to set this payment method as default payment method
                DefaultPaymentMethod insertDefaultPayment = new DefaultPaymentMethod(userId, paymentMethod, "ACTIVE");
                defaultPaymentDAO.insert(insertDefaultPayment);

                System.out.println("Set successfully.");
            }
        }
    }

//    public String defaultPaymentMethod(int userId) {
//        String status = "USED";
//
//        DefaultPaymentMethod defaultPaymentMethod = new DefaultPaymentMethod();
//
////        if (paymentMethod == 1) {
////            System.out.print("Do you want to set this card as your default payment method? (Y/N) : ");
////        } else if (paymentMethod == 2) {
////            System.out.print("Do you want to set Touch 's n Go as your default payment method? (Y/N) : ");
////        }
//
//        ArrayList<DefaultPaymentMethod> defaultPaymentMethods = defaultPaymentMethod.selectAllDefaultPaymentMethod();
//
//        defaultPaymentMethod.setUserId(userId);
//        defaultPaymentMethod.setStatus(status);
//        //defaultPaymentMethod.checkExistDefaultPaymentMethod();
//
//        return defaultPaymentMethod.getPaymentMethod();
//    }
}
