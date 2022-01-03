<%@page import="daos.OrderDAO"%>
<%@page import="dtos.CartDTO"%>

<%@page import="java.util.List"%>

<%@page import="dtos.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Moon Cake Shop</title>
        <!-- Font Awesome -->

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="css/mdb.min.css" rel="stylesheet">
        <!-- Your custom styles (optional) -->
        <link href="css/style.min.css" rel="stylesheet">
        <style type="text/css">
            html,
            body,
            header,
            .carousel {
                height: 60vh;
            }

            @media (max-width: 740px) {

                html,
                body,
                header,
                .carousel {
                    height: 100vh;
                }
            }

            @media (min-width: 800px) and (max-width: 850px) {

                html,
                body,
                header,
                .carousel {
                    height: 100vh;
                }
            }
            #btnLogin:hover{
                background-color: white;
            }
        </style>

    </head>


    <body >

        <%
//            response.setHeader("Cache-Control", "no-cache, no-store");
            UserDTO dto = (UserDTO) session.getAttribute("USER");
            CartDTO list = (CartDTO) session.getAttribute("LIST_CART");
            if (list == null || list.getCart().size() == 0) {
                String url = "home.jsp";
                response.sendRedirect(url);
                return;

            }


        %>
        <c:if test="${sessionScope.LIST_CART==null and  empty sessionScope.LIST_CART}">
            <c:redirect url="listCart.jsp"></c:redirect>
        </c:if>
        <!-- Navbar -->
        <nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling-navbar">
            <div class="container">

                <a class="navbar-brand waves-effect" href="home.jsp" target="_self">
                    <image src="img/booking.jpg" width="80px">
                    <strong class="red-text">Booking Hotel</strong>
                </a>


                <!-- Collapse -->
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <!-- Links -->
                <div class="collapse navbar-collapse" id="navbarSupportedContent">

                    <!-- Left -->
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link waves-effect" href="home.jsp#main-page">Home
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>

                    </ul>
                    <c:forEach var="cart" items="${sessionScope.LIST_CART.getCart().values()}" >

                        <c:set var="count" value="${count+1}"></c:set>
                    </c:forEach>
                    <!-- Right -->
                    <ul class="navbar-nav nav-flex-icons">
                        <li class="nav-item">
                            <a href="listCart.jsp"class="nav-link waves-effect">
                                <span class="badge red z-depth-1 mr-1"> ${count} </span>
                                <i class="fas fa-shopping-cart"></i>
                                <span class="clearfix d-none d-sm-inline-block"> Cart </span>
                            </a>
                        </li>

                        <%                                if (dto == null) {
                        %>
                        <li class="nav-item" style="margin-right:5px;">

                            <a  href="register.jsp"class="nav-link border border-light rounded waves-effect"
                                target="_self" >
                                <i class="fas fa-user-plus"></i>Register
                            </a>

                        </li>
                        <%}%>

                        <li class="nav-item">


                            <c:url var="logout" value="MainController">
                                <c:param name="action" value="Logout"></c:param>
                            </c:url>

                            <%                                if (dto == null) {
                            %>
                            <a  style="display:inline;text-decoration:none;color:black;" href="login.jsp"/>
                            <button id="btnLogin"class="nav-link border border-light rounded waves-effect"
                                    target="_blank">   
                                <i class="fas fa-sign-in-alt"></i>Login
                            </button>
                            </a>
                            <%   } else {%>   
                            <a href="${pageScope.logout}" class="nav-link border border-light rounded waves-effect"
                               target="_blank">
                                <i class="fas fa-sign-in-alt"></i>Logout
                            </a>
                            <%}%>
                        </li>

                    </ul>

                </div>

            </div>
        </nav>



        <!--Main layout-->
        <main style="margin-top:150px;margin-bottom: 229px">


            <div class="container">
                <a href="home.jsp">Back to shopping !</a>
                <div  style='display:flex;justify-content: space-evenly;'>

                    <div style="display: flex;
                         flex-wrap: wrap;
                         justify-content: center;">



                        <h3 style='text-align: center'>Your Information</h3>
                        <table class="table table-primary">

                            <tbody>
                                <tr>
                                    <th>Fullname</th>
                                    <td> <input type='text' value="${sessionScope.USER.getFullName()}"required=""></input></td>
                                </tr>
                                <tr>
                                    <th>Address</th>
                                    <td> <input type='text' name="address"value="${sessionScope.USER.getAddress()} " required=""></input></td>
                                </tr>

                                <tr>
                                    <th>Phone</th>
                                    <td> <input type='text' name="phone" value="${sessionScope.USER.getPhone()}" required=""></input>
                                        <p>${requestScope.ERROR1.phoneError}</p>
                                    </td>
                                    <c:set var="phone" value="${PHONE}"></c:set>

                                    </tr>
                                    <tr>
                                    <c:forEach var="cart" items="${sessionScope.LIST_CART.getCart().values()}" varStatus="counter">
                                        <c:set var="total1" value="${total1+cart.dto.price*cart.quantity}"></c:set>

                                        <c:url var="order" value="OrderController">


                                        </c:url>



                                <input type="hidden" name="total" value="${total1}"/>
                            </c:forEach>




                            <th>Gender</th>
                            <td> ${sessionScope.USER.getGender()}</td>
                            </tr>
                            <tr>
                                <th>Discount Code</th>
                            <form action="CheckcodeController">
                                <td> 
                                    <input value="${param.code}" name="code"type="text" required="" >
                                    <button>Check code</button>
                                    <p style="color:red;">${requestScope.CODE}</p>
                                    <p style="color:green;">${requestScope.CODE1}</p>
                                </td>
                            </form>
                            </tr>

                            </tbody>

                        </table>



                    </div>
                    <div>
                        <h3 style="text-align:center;">Your Order</h3>
                        <c:if test="${sessionScope.LIST_CART!=null and not empty sessionScope.LIST_CART}">

                            <c:if test="${not empty sessionScope.LIST_CART.getCart()}">

                                <form action="MainController" method="POST">


                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th>Hotel Name</th>
                                                <th>Room Type</th>
                                                <th>Checkin Date</th>
                                                <th>Checkout Date</th>
                                                <th>Image</th>
                                                <th>Quantity</th>
                                                <th>Price</th>
                                                <th>Total</th>

                                            </tr>
                                        </thead>
                                        <tbody>


                                            <c:forEach var="cart" items="${sessionScope.LIST_CART.getCart().values()}" varStatus="counter">
                                                <c:set var="total" value="${total+cart.dto.price*cart.quantity}"></c:set>

                                                <c:url var="order" value="OrderController">
                                                    <c:param name="productName" value="${cart.dto.hotelName}"></c:param>

                                                </c:url>
                                                <c:set var="count" value="${count+1}"></c:set>
                                                <c:set var="count1" value="${counter.count}"></c:set>
                                                <c:set var="roomID" value="${cart.dto.roomID}"></c:set>
                                                <%
                                                    OrderDAO dao = new OrderDAO();
                                                    String orderID = dao.generateOrderID();
                                                %>
                                                <tr>

                                                    <td>${counter.count}</td>
                                                    <td>${pageScope.cart.dto.hotelName}</td>
                                                    <td>${cart.dto.type}</td>
                                                    <td><input type="date" name="from"value="${cart.dto.checkinDate}" required=""  ${empty cart.dto.checkinDate ?"":"readonly=''"}</td>
                                                    <td><input type="date" name="to"value="${cart.dto.checkoutDate}" required="" ${empty cart.dto.checkinDate ?"":"readonly=''"}</td>
                                                    <td><img style="width:100px;"src='img/${cart.dto.imageUrl}'></td>
                                                    <td><input name="quantity"type="number" min="1" max="${cart.dto.unitInStock+cart.quantity}"value="${cart.quantity}" readonly=""></input></td>
                                            <input type="hidden" name="amount" value="${cart.dto.unitInStock+cart.quantity}">
                                            <input type="hidden" value="<%=orderID%>" name="orderID">

                                            <td>${cart.dto.price} $</td>
                                            <td>${cart.dto.price*cart.quantity} $</td>


                                            </tr>


                                        </c:forEach>
                                        </tbody>

                                    </table>
                                    <c:if test="${requestScope.DISCOUNT!=null or not empty requestScope.DISCOUNT}">
                                        <c:set var="total" value="${total-((total*requestScope.DISCOUNT)/100)}"></c:set>
                                    </c:if>



                                    <div style="
                                         display: flex;
                                         flex-wrap: nowrap;
                                         align-content: space-between;
                                         justify-content: flex-end;
                                         align-items: baseline;
                                         ">
                                        <a style="font-size: 17px;
                                           font-weight: bold;

                                           padding: 5px;
                                           border-radius: 10px">Total Price: <c:out value="$${total}"/></a>
                                        <input type="hidden" value="${total}" name="total">
                                    </div>
                                    <div style="text-align:center;">
                                        <input   class='btn btn-blue'type='submit' name='action' value='Order'></input>

                                    </div>
                                </c:if>
                            </form>
                        </c:if>
                    </div>

                </div>

                <h3>${requestScope.ERROR}</h3>







            </div>
        </main>
        <!--Main layout-->

    </form>
    <!--Footer-->
    <footer class="page-footer text-center font-small mt-4 wow fadeIn">

        <!--Call to action-->
        <div class="pt-4">
            <a class="btn btn-outline-white" href="https://mdbootstrap.com/docs/jquery/getting-started/download/" target="_blank"
               role="button">Download MDB
                <i class="fas fa-download ml-2"></i>
            </a>
            <a class="btn btn-outline-white" href="https://mdbootstrap.com/education/bootstrap/" target="_blank" role="button">Start
                free tutorial
                <i class="fas fa-graduation-cap ml-2"></i>
            </a>
        </div>
        <!--/.Call to action-->

        <hr class="my-4">

        <!-- Social icons -->
        <div class="pb-4">
            <a href="https://www.facebook.com/mdbootstrap" target="_blank">
                <i class="fab fa-facebook-f mr-3"></i>
            </a>

            <a href="https://twitter.com/MDBootstrap" target="_blank">
                <i class="fab fa-twitter mr-3"></i>
            </a>

            <a href="https://www.youtube.com/watch?v=7MUISDJ5ZZ4" target="_blank">
                <i class="fab fa-youtube mr-3"></i>
            </a>

            <a href="https://plus.google.com/u/0/b/107863090883699620484" target="_blank">
                <i class="fab fa-google-plus-g mr-3"></i>
            </a>

            <a href="https://dribbble.com/mdbootstrap" target="_blank">
                <i class="fab fa-dribbble mr-3"></i>
            </a>

            <a href="https://pinterest.com/mdbootstrap" target="_blank">
                <i class="fab fa-pinterest mr-3"></i>
            </a>

            <a href="https://github.com/mdbootstrap/bootstrap-material-design" target="_blank">
                <i class="fab fa-github mr-3"></i>
            </a>

            <a href="http://codepen.io/mdbootstrap/" target="_blank">
                <i class="fab fa-codepen mr-3"></i>
            </a>
        </div>
        <!-- Social icons -->

        <!--Copyright-->
        <div class="footer-copyright py-3">
            © 2021 Copyright:
            <a href="home.jsp" target="_blank"> Moon Cake Shop </a>
        </div>
        <!--/.Copyright-->

    </footer>
    <!--/.Footer-->

    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="js/mdb.min.js"></script>
    <!-- Initializations -->
    <script type="text/javascript">

        function deleteConf()
        {
            document.getElementById("confirm").style.display = "inline";
            document.getElementById("${count1}").style.display = "inline";
        }
        <%
            String mess = (String) request.getAttribute("MESSAGE1");
            if (mess != null) {
        %>
        alert("${requestScope.MESSAGE1}");
        <%}%>
        window.onload = function () {



            var bootstrapButton = $.fn.button.noConflict(); // return $.fn.button to previously assigned value
            $.fn.bootstrapBtn = bootstrapButton;
            $("#myBtn1").click(function () {
                $("#myModal").modal('hide');
                $("#myModal1").modal('show');

            });


        };

        <%
            String error1 = (String) session.getAttribute("ERROR");
            if (error1 != null) {
        %>
        $("#myModal").modal('show');




        <%}%>


        <%    String a = (String) request.getAttribute("index1");
            if (a != null) {
        %>
        document.getElementById('${index}').style.color = "black";
        <%
        } else {
        %>
        document.getElementById('1').style.color = "black";
        <%
            }
        %>
        // Animations initialization



        new WOW().init();

    </script>
</body>

</html>
