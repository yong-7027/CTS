//package src.main.webapp;
//
////package PayPal;
////
////import java.io.IOException;
////import javax.servlet.ServletException;
////import javax.servlet.annotation.WebServlet;
////import javax.servlet.http.*;
////
////import com.paypal.api.payments.*;
////import com.paypal.base.rest.PayPalRESTException;
////
////@WebServlet("/Payment/review.jsp")
////public class ExecutePaymentServlet extends HttpServlet {
////    private static final long serialVersionUID = 1L;
////
////    public ExecutePaymentServlet() {
////    }
////
////    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        String paymentId = request.getParameter("paymentId");
////        String payerId = request.getParameter("PayerID");
////
////        try {
////            System.out.println("Payment successful!");
////            PaymentServices paymentServices = new PaymentServices();
////            Payment payment = paymentServices.executePayment(paymentId, payerId);
////
////            String paymentStatus = payment.getState(); // 获取支付状态
////
////            if ("approved".equalsIgnoreCase(paymentStatus)) {
////                System.out.println("Payment successful!");
////                // 在这里可以返回支付成功的信息给主程序
////            } else {
////                System.out.println("Payment failed.");
////                // 在这里可以返回支付失败的信息给主程序
////            }
////
////        } catch (PayPalRESTException ex) {
////            ex.printStackTrace();
////            System.out.println("Payment successful!");
////        }
////    }
////}
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//
//import Stripe.Insert;
//
//@WebServlet("receipt.jsp")
//public class ExecutePaymentServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    public ExecutePaymentServlet() {
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String paymentId = request.getParameter("paymentId");
//        String payerId = request.getParameter("PayerID");
//
//        if(paymentId != null && payerId != null){
//            Insert insert = new Insert();
//            insert.insert();
//        }
//
//        else{
//            Insert insert = new Insert();
//            insert.insert();
//        }
//    }
//
//
//}
//
