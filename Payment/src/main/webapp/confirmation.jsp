<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review</title>
<style type="text/css">
    table { border: 0; }
    table td { padding: 5px; }
</style>
</head>
<body>
<div align="center">
    <h1>Please Review Before Paying</h1>
    <form action="review.jsp" method="post">
    <table>
        <tr>
            <td colspan="2"><b>Transaction Details:</b></td>
            <td>
                <input type="hidden" name="paymentId" value="${param.paymentId}" />
                <input type="hidden" name="PayerID" value="${param.PayerID}" />
            </td>
        </tr>
        <tr>
            <td>Description:</td>
            <td>${transaction.description}</td>
        </tr>
        <tr>
            <td>Subtotal:</td>
            <td>${transaction.amount.details.subtotal} USD</td>
        </tr>
        <tr>
            <td>Shipping:</td>
            <td>${transaction.amount.details.shipping} USD</td>
        </tr>
        <tr>
            <td>Tax:</td>
            <td>${transaction.amount.details.tax} USD</td>
        </tr>
        <tr>
            <td>Total:</td>
            <td>${transaction.amount.total} USD</td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="button" value="Pay Now" id="payButton" />
            </td>
        </tr>
    </table>
    </form>
</div>
</body>

<script>
    document.getElementById("payButton").addEventListener("click", function() {
        // Replace 'approvalLinkHere' with the actual Approval Link from your code
        var approvalLink = 'approvalLinkHere';
        window.location.href = approvalLink;
    });
</script>
</html>

