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
        <link rel="stylesheet" type="text/css" href="resources/css/ticket-configure.css">
    </head>
    <body>
        <div id="wrapper">
            <%@ include file="common/header.jsp" %>

            <div id="main-content">
                <form method="post">
                    <p>
                        <label class="col-label">Ticket Machine ID</label>
                        <label class="col-value">${machineConfig.uuid}</label>
                        <button>Generate new ID</button>
                    </p>
                    <p>
                        <label class="col-label" for="station-name">Station Name</label>
                        <select class="col-value" id="station-name">
                            <c:forEach items="${machineConfig.stationList}" var="station" varStatus="tagStatus">
                                <option value="${station.name}">${station.name} - Zone ${station.zone}</option>
                            </c:forEach>
                        </select>
                    </p>
                    <p>
                        <button>Apply Changes</button>
                    </p>
                </form>
            </div>

            <%@ include file="common/footer.jsp" %>
        </div>
    </body>
</html>
