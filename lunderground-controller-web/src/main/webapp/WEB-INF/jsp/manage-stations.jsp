<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <title>Underground Controller - Manage Stations</title>
        <meta charset="UTF-8">
        <meta name="description" content="Controller to manage underground stations">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="resources/css/underground.css">
    </head>
    <body>
        <div id="wrapper">
            <%@ include file="common/header.jsp" %>

            <div id="main-content">
                <h2>This is where we manage underground stations</h2>
                <section id="manage-station">
                    <form method="post">
                        <div id="station-inputs">
                            <p>
                                <label class="col-label" for="stationName">Station Name:</label>
                                <input class="col-input" id="stationName" type="text" name="stationName">
                                <button type="submit" formaction="manage-stations/add">Add</button>
                            </p>
                            <p>
                                <label class="col-label" for="zoneNumber">Zone:</label>
                                <select class="col-input" id="zoneNumber">
                                    <option value="1">Zone 1</option>
                                    <option value="2">Zone 2</option>
                                    <option value="3">Zone 3</option>
                                    <option value="4">Zone 4</option>
                                    <option value="5">Zone 5</option>
                                    <option value="6">Zone 6</option>
                                </select>
                            </p>
                        </div>
                    </form>
                </section>

                <section id="list-stations">

                </section>
            </div>

            <%@ include file="common/footer.jsp" %>
        </div>
    </body>
</html>
