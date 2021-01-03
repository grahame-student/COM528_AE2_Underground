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


                <button type="submit" formaction="sales" formmethod="get"></button>
            </div>

            <%@ include file="common/footer.jsp" %>
        </div>
    </body>
</html>
