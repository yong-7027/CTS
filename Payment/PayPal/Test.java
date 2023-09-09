package PayPal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    private static final String CLIENT_ID = "AfB6EwcVCjunhlsWwD8V9wc0LEIHsT8SxaX00EA0edi_ORJ9W_8ARaTU2-zAu-cuAWi7vpFK5TZp16w-";
    private static final String CLIENT_SECRET = "EAjmzv3nD5FlzJ2t9bU44THUskcasOC3lcooiz_Nr8Arco1RNONBXHnkvCnGoOErzxAj2EKAmQeWA1Al";
    private static final String MODE = "sandbox";

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Select payment method:");
//        System.out.println("1. Credit Card");
//        System.out.println("2. PayPal");
//        int choice = scanner.nextInt();
//
//        if (choice == 1) {
//            // Implement credit card payment logic
//        } else if (choice == 2) {
//            try {
//                RedirectUrls redirectUrls = getRedirectURLs();
//
//                System.out.println(redirectUrls);
//
//                Payment payment = createPayPalPayment();
//                payment.setRedirectUrls(redirectUrls);
//
//                String approvalLink = getApprovalLink(payment);
//                System.out.println("Please follow this link to complete the payment:");
//                System.out.println(approvalLink);
//
//                System.out.println("After completing payment, press Enter to continue...");
//                scanner.nextLine(); // Wait for user input
//                scanner.nextLine(); // Wait for user input
//
//                checkPaymentStatus(payment);
//
//            } catch (PayPalRESTException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("Invalid choice");
//        }
//    }

    private static RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8888/paypal_cancel");
        redirectUrls.setReturnUrl("http://localhost:9000/Payment/");

        return redirectUrls;
    }

    private static void checkPaymentStatus(Payment payment) {
        System.out.println("Checking payment status...");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        Payment executedPayment = null;

        try {
            executedPayment = Payment.get(apiContext, payment.getId());
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }

        System.out.println(executedPayment.getState());

        if (executedPayment.getState().equals("approved")) {
            System.out.println("Payment successful!");
        } else {
            System.out.println("Payment not successful.");
        }
    }

    private static Payment createPayPalPayment() throws PayPalRESTException {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        RedirectUrls redirectUrls = getRedirectURLs();

        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction();

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal("10.00"); // Set your total amount here
        transaction.setAmount(amount);

        transactions.add(transaction);

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setRedirectUrls(redirectUrls);
        payment.setTransactions(transactions);

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return payment.create(apiContext);
    }

    private static String getApprovalLink(Payment payment) {
        for (Links link : payment.getLinks()) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                return link.getHref();
            }
        }
        return null;
    }
}

