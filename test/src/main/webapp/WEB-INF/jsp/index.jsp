<%--
  Created by IntelliJ IDEA.
  User: An2D2
  Date: 09.09.2020
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
<head>
    <!-- Подключаем bootstrap CSS,
        Spring boot автоматически замапит ресурс благодаря зависимости webjars в pom.xml -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link href="${jstlCss}" rel="stylesheet" />

	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />

    <c:url value="/css/main.css" var="jstlCss" />
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>
<body>
<script type="text/javascript">
    window.addEventListener("load",function(){
        var cat = document.querySelector("select").value;
        if (cat==="task2") {
            $("#task2").show();
            $("#task1").hide();
        }
        else {
            $("#task1").show();
            $("#task2").hide();
        }
    },false);
</script>
<div class="container">
    <h1 class="cover-heading row  justify-content-center">Test App</h1>
    <div class="card">

        <div class="row  justify-content-center align-items-center align-middle ">
            <div class="col">
                <form action="" method="POST" name="Submit" enctype="multipart/form-data">
                    <div class="form-group">
                        <div id="task2">b = <input type="text" class="form-control" id="2"  name="b" <c:if test="${operationModel.b!=0}">value="${operationModel.b}"</c:if> /></div>
                        <div class="form-row" id="task1">
                            <div class="col">
                                a1 = <input type="text"  class="form-control" name="a1" <c:if test="${operationModel.a1!=null}">value="${operationModel.toString(operationModel.a1)}"</c:if> />
                            </div>
                            <div class="col">
                                a2 = <input type="text" class="form-control" name="a2" <c:if test="${operationModel.a2!=null}">value="${operationModel.toString(operationModel.a2)}"</c:if> />
                            </div>
                        </div>
                        <br>
                        <p>
                            <select name="category" class="form-control" onchange="change()">>
                                        <option value="task1"  <c:if test="${'task1' eq operationModel.category}">selected="selected"</c:if>>Task 1</option>
                                        <option value="task2" <c:if test="${'task2' eq operationModel.category}">selected="selected"</c:if>>Task 2</option>
                            </select>
                        </p>

                        <p>
                            <input type="submit" class="btn btn-primary" name="submit"  value="Submit"/>
                        </p>
                        <h3>Result: ${result}</h3>
                        <p>

                            <input type="submit" class="btn btn-primary" name="save" value="Save"/>

                        </p>
                        <p>
                            <input type="file" class="form-control-file" name="file" name="Open" />
                            <input type="submit" class="btn btn-primary" name="open" value="Open"/>
                        </p>
                        <c:if test="${!error.isEmpty() }">
                            <div class="alert alert-danger" role="alert">
                                    ${error}
                            </div>
                        </c:if>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
<script> function change() {
    var cat = document.querySelector("select").value;
    if (cat==="task2") {
        $("#task2").show();
        $("#task1").hide();
    }
    else {
        $("#task1").show();
        $("#task2").hide();
    }
} </script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>