package src.main.webapp;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import Stripe.Insert;

@WebServlet("/review.jsp")
public class ReviewPaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReviewPaymentServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");

        if(paymentId != null && payerId != null){
            Insert insert = new Insert();
            insert.insert();
        }

        else{
            Insert insert = new Insert();
            insert.insert();
        }
    }

}
