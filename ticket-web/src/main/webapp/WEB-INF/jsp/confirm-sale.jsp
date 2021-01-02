<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Ticket Machine</title>
        <meta charset="UTF-8">
        <meta name="description" content="Ticket Machine">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="resources/css/ticket-core.css">
    </head>
    <body>
        <div id="wrapper">
            <%@ include file="common/header.jsp" %>

            <div id="main-content">
                <h2>Ticket - Checkout</h2>
                <p id="start-station"  >From:       ${startStation} - ${startZone}</p>
                <p id="dest-station"   >To:         ${destStation} - ${destZone}</p>
                <p id="sales-timestamp">Valid From: ${timeStamp} - (${rateBand})</p>
                <p id="journey-price"  >Price:      ${}</p>


                <ul>
                    <li>Display travel summary</li>
                    <li>Display ticket price</li>
                    <li></li>
                    <li>Pay - valid card   - return to ticket-sales + display ticket</li>
                    <li>Pay - invalid card - return to ticket-sales + display payment declined</li>
                    <li>Cancel - return to ticket-sales</li>
                </ul>
            </div>

            <%@ include file="common/footer.jsp" %>
        </div>
    </body>
</html>
