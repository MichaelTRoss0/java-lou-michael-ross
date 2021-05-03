<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <title>ToDo Manager</title>
    </head>
    <body>
        <div class="container">
            <div class="row text-center">
                <h2> ToDo Manager</h2>
            </div>
            <div class="row">
                <div class="col-8">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>To-Do</th>
                                <th>Note</th>
                                <th>Finished</th>
                                <th>Toggle Finished</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="todo" items="${todos}">
                                <tr>
                                    <td><c:out value="${todo.id}"/></td>
                                    <td><c:out value="${todo.todo}"/></td>
                                    <td><c:out value="${todo.note}"/></td>
                                    <td><c:out value="${todo.finished}"/></td>
                                    <td><a href="toggleToDo?id=${todo.id}">Toggle Finished</a></td>
                                    <td><a href="deleteToDo?id=${todo.id}">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-4">
                    <form class="form" method="POST" action="addToDo">
                        <h3>Add To-Do</h3>
                        <div class="form-group row">
                            <label class="col-4" for="todo">To-Do</label>
                            <input class="col-8 form-control" type="text" id="todo" name="todo"/>
                        </div>
                        <div class="form-group row">
                            <label class="col-4" for="note">Note</label>
                            <input class="col-8 form-control" type="text" id="note" name="note"/>
                        </div>
                        <button type="submit">Add To-Do</button>
                    </form>
                </div>
            </div>
        </div>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
