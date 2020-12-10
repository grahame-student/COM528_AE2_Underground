<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <title>Underground Controller - Manage Stations</title>
        <meta charset="UTF-8">
        <meta name="description" content="Controller to manage underground stations">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="resources/css/underground-core.css">
        <link rel="stylesheet" type="text/css" href="resources/css/underground-manage-stations.css">
    </head>
    <body>
        <div id="wrapper">
            <%@ include file="common/header.jsp" %>

            <div id="main-content">
                <section id="manage-station">
                    <form method="post">
                        <div id="station-inputs">
                            <p>
                                <label class="col-label" for="stationName">Station Name:</label>
                                <input class="col-input" id="stationName" type="text" name="stationName"
                                       required="true">
                                <button type="submit" formaction="manage-stations/add">Add</button>
                            </p>
                            <p>
                                <label class="col-label" for="zoneNumber">Zone:</label>
                                <select class="col-input" id="zoneNumber" name="zoneNumber">
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
                    <form method="post">
                        <table>
                            <tr>
                                <th>Station Name</th>
                                <th>Zone</th>
                                <th></th>
                                <th></th>
                            </tr>
                            <c:forEach items="${stations}" var="station" varStatus="tagStatus">
                                <tr>
                                    <td>${station.name}</td>
                                    <td>${station.zone}</td>
                                    <td>
                                        <button formaction="manage-stations/delete?id=${station.id}">Delete</button>
                                    </td>
                                    <td></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </form>
                </section>
            </div>

            <%@ include file="common/footer.jsp" %>
        </div>
    </body>
</html>
