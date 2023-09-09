package PayPal;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.List;

public class PaymentServices {
    private static final String CLIENT_ID = "AfB6EwcVCjunhlsWwD8V9wc0LEIHsT8SxaX00EA0edi_ORJ9W_8ARaTU2-zAu-cuAWi7vpFK5TZp16w-";
    private static final String CLIENT_SECRET = "EAjmzv3nD5FlzJ2t9bU44THUskcasOC3lcooiz_Nr8Arco1RNONBXHnkvCnGoOErzxAj2EKAmQeWA1Al";
    private static final String MODE = "sandbox";

    public String authorizePayment(OrderDetail orderDetail) throws PayPalRESTException {
        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(orderDetail);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        Payment approvedPayment = requestPayment.create(apiContext);

        return getApprovalLink(approvedPayment);
    }

//    public APIContext getAPIContext() {
//        Map<String, String> configMap = new HashMap<>();
//        configMap.put("mode", PaymentServices.MODE);
//
//        List<String> scopes = new ArrayList<>();
//        scopes.add("openid");
//        scopes.add("email");
//        configMap.put("openid.connect.scope", String.join(" ", scopes));
//
//        OAuthTokenCredential tokenCredential = new OAuthTokenCredential(PaymentServices.CLIENT_ID, PaymentServices.CLIENT_SECRET, configMap);
//
//        APIContext apiContext = new APIContext(PaymentServices.CLIENT_ID, PaymentServices.CLIENT_SECRET, PaymentServices.MODE, configMap);
//        apiContext.setConfigurationMap(configMap);
//
//        return apiContext;
//    }

    private Payer getPayerInformation(){
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("Wilson")
                .setLastName("Peterson")
                .setEmail("sb-gmw47q27011293@personal.example.com");

        payer.setPayerInfo(payerInfo);

        return payer;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8888/paypal_cancel");
        redirectUrls.setReturnUrl("http://localhost:8888/Payment/review.jsp");

        return redirectUrls;
    }

    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }

    private List<Transaction> getTransactionInformation(OrderDetail orderDetail){
        Details details = new Details();
        details.setSubtotal(orderDetail.getSubTotal());
        details.setTax(orderDetail.getFee());
        details.setShipping("0.00");

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(orderDetail.getTotal());
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(orderDetail.getMovieName());

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        Item item = new Item();
        item.setCurrency("USD")
            .setName(orderDetail.getMovieName())
            .setPrice(orderDetail.getPrice())
            .setTax(orderDetail.getFee())
            .setQuantity(orderDetail.getQuantity());

        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);

        return listTransaction;
    }

    private String getApprovalLink(Payment approvedPayment){
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for(Links link:links){
            if(link.getRel().equalsIgnoreCase("approval_url")){
                approvalLink = link.getHref();
            }
        }

        return approvalLink;
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment payment = new Payment().setId(paymentId);

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        return payment.execute(apiContext, paymentExecution);
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

//    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
//        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
//        return Payment.get(apiContext, paymentId);
//    }
//
//    public Payment executePayment(String paymentId, String payerId)
//            throws PayPalRESTException {
//        PaymentExecution paymentExecution = new PaymentExecution();
//        paymentExecution.setPayerId(payerId);
//
//        Payment payment = new Payment().setId(paymentId);
//
//        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
//
//        return payment.execute(apiContext, paymentExecution);
//    }
}
