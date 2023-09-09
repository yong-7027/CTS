import System.*;
//import PayPal.PaymentServices;
import com.paypal.base.rest.PayPalRESTException;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws PayPalRESTException, PayPalRESTException, PayPalRESTException, SQLException {
        Scanner input = new Scanner(System.in);
//        PaymentHistory paymentHistory = new PaymentHistory();
//
//        System.out.println("Enter your user id: ");
//        int userId = input.nextInt();
//        paymentHistory.setUserId(userId);
//
//        System.out.println("Payment history filtering: ");
//        System.out.println("1. Last 30 days");
//        System.out.println("2. All days");
//        System.out.println("3. Custom start date and end date");
//        System.out.print("Select your option: ");
//        int filterOption = input.nextInt();
//
//        while (true) {
//            if (filterOption == 1) {
//                paymentHistory.setSearchRequire(1);
//                break;
//            } else if (filterOption == 2) {
//                paymentHistory.setSearchRequire(2);
//                break;
//            } else if (filterOption == 3) {
//                // Custom start date and end date
//                paymentHistory.setSearchRequire(3);
//
//                String startDate;
//                String endDate;
//                String datePattern = "yyyy-mm-dd";
//
//                while (true) {
//                    System.out.println("Enter the start date (yyyy-mm-dd): ");
//                    startDate = input.next();
//
//                    if (DateTime.dateValidation(startDate, datePattern)) {
//                        paymentHistory.setStartDate(startDate);
//
//                        break;
//                    }
//                }
//
//                while (true) {
//                    System.out.println("Enter the end date (yyyy-mm-dd): ");
//                    endDate = input.next();
//
//                    if (DateTime.dateValidation(endDate, datePattern)) {
//                        paymentHistory.setEndDate(endDate);
//
//                        break;
//                    }
//                }
//
//                break;
//            } else {
//                System.out.println("Invalid option. Please retry.");
//            }
//        }
//
//        paymentHistory.searchRequirement();
// ---------------------------------------------------------------------------------------------------------------------
        SystemClass systemClass = new SystemClass();
        double amount = 25;

        System.out.printf("You need to pay RM %.2f\n", amount);
        System.out.print("\nDo you want to continue to make payment? (Y/N) : ");
        String ctnPayment = input.next().trim();

        if(ctnPayment.equals("Y") || ctnPayment.equals("y")) {
            systemClass.makePayment(input);
        }

        // 创建并启动加载动画线程
        LoadingAnimation loadingAnimation = new LoadingAnimation();
        loadingAnimation.start();

        // 模拟一些工作
        try {
            Thread.sleep(5000); // 休眠5秒，模拟加载过程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 停止加载动画线程
        loadingAnimation.stopLoading();

//        double amount = 25;
//
//        CustPromoCode custPromoCode = new CustPromoCode();
//        Scanner input = new Scanner(System.in);
//
//        System.out.print("Enter your user id: ");
//        int userId = input.nextInt();
//
//        //custPromoCode.getPromoCode(userId);
//
//        System.out.printf("You need to pay RM %.2f\n", amount);
//        System.out.print("\nDo you want to continue to make payment? (Y/N) : ");
//        char ctnPayment = input.next().charAt(0);
//
//        if(ctnPayment == 'Y' || ctnPayment == 'y') {
//            System.out.print("\nDo you want to apply promotion code? (Y/N) : ");
//            char applyPromoCode = input.next().charAt(0);
//
//            if(applyPromoCode == 'Y' || applyPromoCode == 'y') {
//                double discountValue = 0;
//                String code = null;
//
//                while(discountValue == 0) {
//                    System.out.print("\nEnter your promotion code (X - Exit) : ");
//                    code = input.next().trim();
//
//                    discountValue = custPromoCode.promoCodeVerification(userId, code);
//
//                    if(code.equals("X") || code.equals("x")) {
//                        break;
//                    }
//
//                    if(discountValue == 0) {
//                        // Invalid promotion code
//                        System.out.println("Invalid Promotion Code. Please Retry.");
//                    }
//                }
//
//                amount -= discountValue;
//
//                if(!(code.equals("X") || code.equals("x"))) {
//                    System.out.println("\nDiscount " + discountValue + ". Your current amount is: " + amount);
//                }
//
//                System.out.print("\nContinue to place order? (Y/N) : ");
//                char placeOrder = input.next().charAt(0);
//
//                if(placeOrder == 'Y' || placeOrder == 'y') {
//                    System.out.println("Select your payment method: ");
//                    System.out.println("1. Credit/Debit Card");
//                    System.out.println("2. Touch 'n Go");
//
//                    int paymentMethod = input.nextInt();
//
//                    Payment payment;
//
//                    while(true) {
//                        if(paymentMethod == 1) {
//                            // Process Credit/Debit Card Payment
//                            payment = new CreditCardVerification();
//
//                            break;
//                        } else if (paymentMethod == 2) {
//                            // Process TNG Payment
//                            payment = new TNGPayment();
//
//                            System.out.print("Enter your name: ");
//                            String name = input.nextLine().trim();
//
//                            break;
//                        } else {
//                            System.out.println("Invalid selection. Please retry.");
//                        }
//                    }
//
//                    while(true) {
//                        if(payment.pay(amount)) {
//                            break;
//                        }
//                    }
//
//                    if(discountValue > 0) {
//                        // User use promotion code, update promotion code status
//                        custPromoCode.updatePromoCodeStatus(userId, code);
//                    }
//                }
//            }
//        }
// ----------------------------------------------------------------------------------------------------------------------------
//
//        System.out.println("Enter your selection: ");
//        System.out.println("1. Create new promotion");
//        System.out.println("2. View promotion");
//
//        int option = input.nextInt();
//
//        if(option == 1){
//            AdminPromoCode adminPromoCode = new AdminPromoCode();
//
//            adminPromoCode.createPromotion();
//            adminPromoCode.insertPromotion();
//
//            CustPromoCode custPromoCode = new CustPromoCode();
//            custPromoCode.getPromoCode(userId);
//
//            CreditCardVerification creditCardVerification = new CreditCardVerification();
//            creditCardVerification.pay(30);
//
////            ArrayList<String> codes;
////
////            PromoCode promoCode = new PromoCode();
////
////            codes = promoCode.loadPromotionCode();
////
////            if(codes == null) {
////                String code = PromoCode.generatePromotionCode(15);
////                System.out.println(code);
////            }
////
////            else {
////                promoCode.checkPromoCode(codes);
////            }
//
//
////            PromoCode promoCode = new PromoCode();
////
////            promoCode.checkPromoCode();
//        }

//        OrderDetail amount = new OrderDetail();
//        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
//
//        orderDetails.add(new OrderDetail("Suzume", 16.00));
//        amount.setPrice(16.00);
//
//        CustPaymentMethod p1 = new Stripe.CreditCardVerification("Zheng Sheng", "zs@gmail.com", amount.getPrice());
//        p1.pay(amount.getPrice());
//
//        Scanner scanner = new Scanner(System.in);
//
//        OrderDetail orderDetail = new OrderDetail("Suzume", "16.00", "1", "0.50");
//
//        System.out.println("Select payment method:");
//        System.out.println("1. Credit Card");
//        System.out.println("2. PayPal");
//        int choice = scanner.nextInt();
//
//        if (choice == 1) {
//            // Implement credit card payment logic
//        }
//
//        else if (choice == 2) {
////            System.out.println(orderDetail.getSubTotal());
////            System.out.println(orderDetail.getTotal());
////            System.out.println(orderDetail.getPrice());
////            System.out.println(orderDetail.getFee());
////            System.out.println(orderDetail.getQuantity());
//
//            try {
//                PaymentServices paymentServices = new PaymentServices();
//                String approvalLink = paymentServices.authorizePayment(orderDetail);
//
//                System.out.println("Click the link to complete your payment: ");
//                System.out.println("Approval Link: " + approvalLink);
//
//                System.out.println("After completing payment, press Enter to continue...");
//                scanner.nextLine(); // Wait for user input
//                scanner.nextLine(); // Wait for user input
//
//
//                //checkPaymentStatus(payment);
//
//            } catch (PayPalRESTException e) {
//                e.printStackTrace();
//            }
//        }
//
//        else {
//            System.out.println("Invalid choice");
//        }

//        private static void checkPaymentStatus(Payment payment) {
//            System.out.println("Checking payment status...");
//
//            APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
//            Payment executedPayment = null;
//
//            try {
//                executedPayment = Payment.get(apiContext, payment.getId());
//            } catch (PayPalRESTException e) {
//                throw new RuntimeException(e);
//            }
//
//            System.out.println(executedPayment.getState());
//
//            if (executedPayment.getState().equals("approved")) {
//                System.out.println("Payment successful!");
//            } else {
//                System.out.println("Payment not successful.");
//            }
//        }
    }
}