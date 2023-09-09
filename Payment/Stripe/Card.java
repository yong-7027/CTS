package Stripe;
import Tools.CardInfoValidation;
import Tools.DateTime;
import Tools.StripeAPIKey;
import Payment.Payment;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodCreateParams;
import java.util.Scanner;

public class Card extends Payment {
    private String cardNo;
    private String expiredDate;
    private String cvc;
    private String email;

    public Card() {
        super();
    }

    public Card(String cardNo, String expiredDate, String cvc, String email) {
        this.cardNo = cardNo;
        this.expiredDate = expiredDate;
        this.cvc = cvc;
        this.email = email;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean pay(double amount, Card card) {
        // Set Secret Key
        StripeAPIKey.init();

        double stripeAmount = amount * 100;

        PaymentMethod paymentMethod;
        Customer customer;

        paymentMethod = createPaymentMethod(card);
        customer = createCustomer(card);

        if (paymentMethod == null || customer == null) {
            return false;
        }

        PaymentIntent paymentIntent = createPaymentIntent(stripeAmount, paymentMethod, customer);

        if (paymentIntent != null) {
            System.out.println("Payment successful!\n");
            recordPayment(amount);
            return true;
        } else {
            return false;
        }
    }

//    private CreditCard initializeCreditCard() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter card number: ");
//        String cardNumber = scanner.nextLine().trim();
//
//        String expiredDate;
//
//        do {
//            System.out.print("Enter expired date (mm/yy): ");
//            expiredDate = scanner.next().trim();
//
//        } while(!expiredDateFormatValidation(expiredDate));
//
//        System.out.print("Enter Cvc: ");
//        String cvc = scanner.next().trim();
//
//        System.out.print("Enter email: ");
//        String email = scanner.next().trim();
//        scanner.next();
//
//        return new CreditCard(cardNumber, expiredDate, cvc, email);
//    }

//    private boolean expiredDateFormatValidation(String expiredDate) {
//        String regex = "^\\d{2}/\\d{2}$";
//
//        if(expiredDate.matches(regex)) {
//            return true;
//        }
//
//        System.out.println("Invalid expired date format. Please follow the format.");
//
//        return false;
//    }
//
//    private int getExpiredMonth(String expiredDate){
//        // Date user entered
//        String[] monthYear = expiredDate.split("/");
//
//        return Integer.parseInt(monthYear[0]);
//    }
//
//    private int getExpiredYear(String expiredDate){
//        // Date user entered
//        String[] monthYear = expiredDate.split("/");
//
//        return Integer.parseInt(monthYear[1]);
//    }

    private PaymentMethod createPaymentMethod(Card card) {
        try {
            PaymentMethodCreateParams paymentMethodParams = PaymentMethodCreateParams.builder()
                    .setType(PaymentMethodCreateParams.Type.CARD)
                    .setCard(PaymentMethodCreateParams.CardDetails.builder()
                            .setNumber(card.getCardNo())
                            .setExpMonth((long) CardInfoValidation.getExpiredMonth(card.getExpiredDate()))
                            .setExpYear((long) CardInfoValidation.getExpiredYear(card.getExpiredDate()))
                            .setCvc(card.getCvc())
                            .build())
                    .build();

            return PaymentMethod.create(paymentMethodParams);

        } catch (StripeException e) {
            System.out.println("Payment Failed. Your card info is invalid.");
            return null;
        }
    }

    private Customer createCustomer(Card card) {
        try {
            CustomerCreateParams customerParams = CustomerCreateParams.builder()
                    .setEmail(card.getEmail())
                    .build();

            return Customer.create(customerParams);

        } catch (StripeException e) {
            System.out.println("Payment Failed. Your email is invalid.");
            return null;
        }
    }

    private PaymentIntent createPaymentIntent(double stripeAmount, PaymentMethod paymentMethod, Customer customer) {
        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount((long) stripeAmount)
                    .setCurrency("myr")
                    .setPaymentMethod(paymentMethod.getId())
                    .setCustomer(customer.getId())
                    .build();

            return PaymentIntent.create(params);

        } catch (StripeException e) {
            System.out.println("Error creating payment intent: " + e.getMessage());
            return null;
        }
    }

    private void recordPayment(double amount) {
        String insertPaymentMethod = "CREDIT/DEBIT CARD";
        String currency = "MYR";
        String paymentStatus = "PAID";

        setBookingId(4);
        setPaymentMethod(insertPaymentMethod);
        setPaymentAmount(amount);
        setCurrency(currency);
        setPaymentDate(DateTime.getCurrentDate());
        setPaymentTime(DateTime.getCurrentTime());
        setPaymentStatus(paymentStatus);

        insertPayment();
    }


//    @Override
//    public boolean pay(double amount) {
//        double stripeAmount = amount * 100;
//
//        CreditCard card = new CreditCard();
//        Scanner scanner = new Scanner(System.in);
//
//        // Set Secret Key
//        StripeAPIKey.init();
//
//        // Get Customer CardNo
//        System.out.print("Enter card number: ");
//        String cardNumber = scanner.nextLine();
//        card.setCardNo(cardNumber);
//
//        // Get Customer Card Expired Date
//        String[] expiredDate = expiredDateValidation(scanner, card);
//        int month = Integer.parseInt(expiredDate[0]);
//        int year = Integer.parseInt(expiredDate[1]);
//
//        // Get Customer Cvc
//        System.out.print("Enter Cvc: ");
//        String cvc = scanner.next();
//        card.setCvc(cvc);
//
//        // Get Customer Email
//        System.out.print("Enter email: ");
//        String customerEmail = scanner.next();
//        card.setEmail(customerEmail);
//
//        try {
//            // Set Payment Method Info
//            PaymentMethodCreateParams paymentMethodParams = PaymentMethodCreateParams.builder()
//                    .setType(PaymentMethodCreateParams.Type.CARD)  // Set Type of Card
//
//                    // Set Card Info
//                    .setCard(PaymentMethodCreateParams.CardDetails.builder()
//                            .setNumber(card.getCardNo())
//                            .setExpMonth((long) month)
//                            .setExpYear((long) year)
//                            .setCvc(card.getCvc())
//                            .build())
//                    .build();
//
//            // Create Payment Method
//            PaymentMethod paymentMethod = PaymentMethod.create(paymentMethodParams);
//
//            // Set Customer Info
//            CustomerCreateParams customerParams = CustomerCreateParams.builder()
//                    .setEmail(card.getEmail())  // Set Customer Email
//                    .build();
//
//            // Create Customer
//            Customer customer = Customer.create(customerParams);
//
//            // Set Payment Intent Info
//            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
//                    .setAmount((long) stripeAmount)   // Set the Payment Amount
//                    .setCurrency("myr") // Set the Payment Currency
//                    .setPaymentMethod(paymentMethod.getId())    // Set Payment Method
//                    .setCustomer(customer.getId())  // Set Customer Info
//                    .build();
//
//            // Create Payment Intent
//            PaymentIntent paymentIntent = PaymentIntent.create(params);
//
//            System.out.println("Payment successful. Payment Intent ID: " + paymentIntent.getId());
//
//            String insertPaymentMethod = "CREDIT/DEBIT CARD";
//            String currency = "MYR";
//            String paymentStatus = "PAID";
//
//            setBookingId(3);
//            setPaymentMethod(insertPaymentMethod);
//            setPaymentAmount(amount);
//            setCurrency(currency);
//            setPaymentDate(DateTime.getCurrentDate());
//            setPaymentTime(DateTime.getCurrentTime());
//            setPaymentStatus(paymentStatus);
//
//            insertPayment();
//
//            return true;
//
//        } catch (StripeException e) {
//            // Catch Exception
//            System.out.println("Payment failed. Error: " + e.getMessage());
//
//            return false;
//        }
//    }

//    public static boolean expiredDateValidation(String expiredDate){
//        // Date user entered
//        String[] monthYear = expiredDate.split("/");
//
//        // Current date
//        String[] currentMonthYear = DateTime.getCurrentDate().split("-");
//
//        if(monthYear.length == 2){
//            // Str to Int
//            int expiredMonth = Integer.parseInt(monthYear[0]);
//            int expiredYear = Integer.parseInt(monthYear[1]);
//
//            int currentMonth = Integer.parseInt(currentMonthYear[1]);
//            int currentYear = Integer.parseInt(currentMonthYear[0].substring(2,4));
//
//            // Valid date format
//            if(expiredYear < currentYear || expiredYear > 99) {
//                // Year validation
//                System.out.println("Invalid year. Please Enter Again.\n");
//                return false;
//            } else if(expiredMonth < currentMonth || expiredMonth > 12) {
//                // Month validation
//                System.out.println("Invalid month. Please Enter Again.\n");
//                return false;
//            } else {
//                // Valid
//                return true;
//            }
//
//        } else{
//            // Invalid date format
//            System.out.println("Invalid date format. Please follow mm/yy format.\n");
//
//            return false;
//        }
//    }
//
//    public boolean emailValidation(String email) {
//        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA_Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA_Z]{2,7}$";
//
//        return email.matches(regex);
//    }

}
