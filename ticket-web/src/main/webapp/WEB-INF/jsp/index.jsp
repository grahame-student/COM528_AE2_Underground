<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <label class="col-value">xxx-xxx-xxx-xxx-xxx</label>
                        <button>Generate new ID</button>
                    </p>
                    <p>
                        <label class="col-label" for="station-name">Station Name</label>
                        <select class="col-value" id="station-name">
                            <option value="Station 1">Station 1 - Zone 1</option>
                            <option value="Station 2">Station 2 - Zone 2</option>
                            <option value="Station 3">Station 3 - Zone 3</option>
                            <option value="Station 4">Station 4 - Zone 4</option>
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
