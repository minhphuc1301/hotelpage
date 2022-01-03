<%@page import="java.util.List"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.text.DecimalFormat"%>

<%@page import="dtos.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="roomDAO" class="daos.RoomDAO"></jsp:useBean>
    <!DOCTYPE html>
    <html lang="en">

        <head>

            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <meta http-equiv="x-ua-compatible" content="ie=edge">
            <title>Booking Hotel</title>
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


        <body >

        <%

            UserDTO dto = (UserDTO) session.getAttribute("USER");


        %>

        <!-- Navbar -->
        <nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling-navbar">
            <div class="container">

                <!-- Brand -->
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


                        <li class="nav-item" style="margin-right:5px;">

                            <%                                if (dto == null) {
                            %>
                            <a  href="register.jsp"class="nav-link border border-light rounded waves-effect"
                                target="_self" >
                                <i class="fas fa-user-plus"></i>Register
                            </a>
                            <%}%>


                        </li>




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
                            <%} else {%>
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

        <!--Carousel Wrapper-->
        <div id="carousel-example-1z" class="carousel slide carousel-fade pt-4" data-ride="carousel">

            <!--Indicators-->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-1z" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-1z" data-slide-to="1"></li>
                <li data-target="#carousel-example-1z" data-slide-to="2"></li>
            </ol>
            <!--/.Indicators-->

            <!--Slides-->
            <div class="carousel-inner" role="listbox">

                <!--First slide-->
                <div class="carousel-item active">
                    <div class="view" style="background-image: url('https://pearlriverhotel.vn/wp-content/uploads/2019/07/pearl-river-hotel-home1.jpg'); background-repeat: no-repeat; background-size: cover;">
                        <!-- Mask & flexbox options-->
                        <div class="mask rgba-black-strong d-flex justify-content-center align-items-center">

                            <!-- Content -->
                            <div class="text-center white-text mx-5 wow fadeIn">
                                <h1 class="mb-4">
                                    <strong>Hi ${sessionScope.USER.getFullName()}, Welcome to Booking Hotel Page </strong>
                                </h1>                         
                                <a  href="#main-page" class="btn btn-outline-white btn-lg">Start
                                    booking hotel
                                    <i class="fas fa-shopping-cart"></i>
                                </a>
                            </div>
                            <!-- Content -->
                        </div>
                        <!-- Mask & flexbox options-->

                    </div>
                </div>
                <!--/First slide-->

                <!--Second slide-->
                <div class="carousel-item">
                <div class="view" style="background-image: url('https://cdn.vietnambiz.vn/2019/11/4/dd32d9b188d86d6d8dc40d1ff9a0ebf6-15728512315071030248829.jpg'); background-repeat: no-repeat; background-size: cover;">
                        <!-- Mask & flexbox options-->
                        <div class="mask rgba-black-strong d-flex justify-content-center align-items-center">

                            <!-- Content -->
                            <div class="text-center white-text mx-5 wow fadeIn">
                                <h1 class="mb-4">
                                    <strong>Hi ${sessionScope.USER.getFullName()}, Welcome to Booking Hotel Page </strong>
                                </h1>                         
                                <a  href="#main-page" class="btn btn-outline-white btn-lg">Start
                                    booking hotel
                                    <i class="fas fa-shopping-cart"></i>
                                </a>
                            </div>
                            <!-- Content -->
                        </div>
                        <!-- Mask & flexbox options-->

                    </div>
                </div>
                <!--/Second slide-->

                <!--Third slide-->
                <div class="carousel-item">
                    <div class="view" style="background-image: url('https://pix8.agoda.net/hotelImages/124/1246280/1246280_16061017110043391702.jpg?s=1024x768'); background-repeat: no-repeat; background-size: cover;">
                        <!-- Mask & flexbox options-->
                        <div class="mask rgba-black-strong d-flex justify-content-center align-items-center">

                            <!-- Content -->
                            <div class="text-center white-text mx-5 wow fadeIn">
                                <h1 class="mb-4">
                                    <strong>Hi ${sessionScope.USER.getFullName()}, Welcome to Booking Hotel Page </strong>
                                </h1>                         
                                <a  href="#main-page" class="btn btn-outline-white btn-lg">Start
                                    booking hotel
                                    <i class="fas fa-shopping-cart"></i>
                                </a>
                            </div>
                            <!-- Content -->
                        </div>
                        <!-- Mask & flexbox options-->

                    </div>
                </div>
                <!--/Third slide-->

            </div>
            <!--/.Slides-->

            <!--Controls-->
            <a class="carousel-control-prev" href="#carousel-example-1z" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carousel-example-1z" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
            <!--/.Controls-->

        </div>
        <!--/.Carousel Wrapper-->

        <!--Main layout-->
        <main id="main-page">
            <div class="container">

                <!--Navbar-->
                <nav class="navbar navbar-expand-lg navbar-dark mdb-color lighten-3 mt-3 mb-5">

                    <!-- Navbar brand -->


                    <!-- Collapse button -->
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#basicExampleNav"
                            aria-controls="basicExampleNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <!-- Collapsible content -->
                    <div class="collapse navbar-collapse" id="basicExampleNav">
                        <form action="MainController" class="form-inline">
                            <!-- Links -->
                            <ul class="navbar-nav mr-auto">

                                <li class="nav-item">
                                    <a class="nav-link">Checkin Date</a>
                                    <input  value="${requestScope.FROM!=null?requestScope.FROM:param.from}" type="date" required="" name="from">
                                    <c:set var="checkinDate" value="${param.from}"></c:set>
                                    </li>

                                    <li class="nav-item ">

                                        <a class="nav-link" >Checkout Date</a>
                                        <input value="${requestScope.TO!=null?requestScope.TO:param.to}" type="date" required="" name="to">
                                    <c:set var="checkoutDate" value="${param.to}"></c:set>
                                    </li>

                                </ul>
                                <!-- Links -->


                                <input  name="quantity"value="1"style="width:50px;"type="number" required="" min="1">Amount of Room</input>
                                <input value="${param.search3}"type="text" disabled style="display:none;;"id="search3"name="search3">

                            <input style="margin-left:5px;" onclick=" if (document.getElementById('search3').style.display == 'none') {
                                        document.getElementById('search3').style.display = 'block'
                                        document.getElementById('search3').style = 'block'
                                        document.getElementById('search3').disabled = '';
                                        document.getElementById('search3').style.width = '119px'
                                        s
                                    } else {
                                        document.getElementById('search3').disabled = 'disabled';
                                        document.getElementById('search3').style.display = 'none';
                                    }" type="checkbox"  value="${param.vehicle1}" value="Bike">
                            <label style="font-weight:bold;"for="vehicle1"> Search by hotel area</label><br>

                            <div class="md-form my-0">
                                <input value="${param.search}"name="search"class="form-control mr-sm-2" type="text" placeholder="Search by hotel name" aria-label="Search">
                                <input style="border:none;"class="fas fa-search" type="submit" name="action" value="Search" >


                            </div>

                        </form>
                    </div>
                    <!-- Collapsible content -->

                </nav>
                <!--/.Navbar-->

                <!--Section: Products v.3-->
                <section class="text-center mb-4">

                    <!--Grid row-->
                    <div class="row wow fadeIn">

                        <c:if test="${requestScope.LIST==null and requestScope.NOTFOUND==null}"> 
                            <c:forEach items="${roomDAO.list}" var="p">

                                <div style="display:flex;margin-bottom: 25px;">
                                    <!--Grid column-->
                                    <div class="col-6 col-sm-8">

                                        <!--Card-->
                                        <div style="flex-flow: row;"class="card">
                                            <img style="width:500px;height:350px;"src="img/${p.imageUrl}"
                                                 alt="">

                                            <!--Card content-->
                                            <div style="text-align:center;display:grid;" class="card-body ">
                                                <!--Category & Title-->  

                                                <h5>
                                                    <strong>
                                                        <a class="dark-grey-text">Room Name
                                                            <span class="badge badge-pill blue">${p.name}</span>
                                                        </a>
                                                    </strong>
                                                </h5>
                                                <h5>
                                                    <strong>
                                                        <a class="dark-grey-text">Room Type
                                                            <span class="badge badge-pill blue">${p.type}</span>
                                                        </a>
                                                    </strong>
                                                </h5>

                                                <h5>
                                                    <strong>
                                                        <a class="dark-grey-text">Unit In Stock 
                                                            <span class="badge badge-pill deep-orange">${p.unitInStock}</span>
                                                        </a>
                                                    </strong>
                                                </h5>

                                                <h5>
                                                    <strong>
                                                        <%
                                                            String from = (String) request.getAttribute("from");
                                                            String to = (String) request.getAttribute("to");
                                                        %>
                                                        <strong class="font-weight-bold blue-text">${p.price} $</strong>
                                                        <c:url var="add" value="MainController">
                                                            <c:param name="action" value="Add"></c:param>
                                                            <c:param name="checkinDate" value="${checkinDate}"></c:param>
                                                            <c:param name="checkinDate1" value="<%=from%>"></c:param>
                                                            <c:param name="checkoutDate" value="${checkoutDate}"></c:param>
                                                            <c:param name="checkoutDate1" value="<%=to%>"></c:param>
                                                            <c:param name="hotelName" value="${p.hotelName}"></c:param>
                                                            <c:param name="roomName" value="${p.name}"></c:param>
                                                            <c:param name="hotelID" value="${p.hotelID}"></c:param>
                                                            <c:param name="roomID" value="${p.roomID}"></c:param>
                                                            <c:param name="typeRoom" value="${p.type}"></c:param>
                                                            <c:param name="price" value="${p.price}"></c:param>
                                                            <c:param name="unitInStock" value="${p.unitInStock}"></c:param>
                                                            <c:param name="image" value="${p.imageUrl}"></c:param>

                                                        </c:url>

                                                        <a href="${pageScope.add}" class="dark-grey-text">
                                                            <span class="badge badge-pill primary-color-dark">Add To Cart</span>
                                                        </a>

                                                    </strong>
                                                </h5>




                                            </div>
                                            <!--Card content-->

                                        </div>
                                        <!--Card-->

                                    </div>

                                    <!--Grid column-->
                                    <div class="col-6 col-sm-4">

                                        <!--Card-->
                                        <div class="card">

                                            <!--Card content-->
                                            <div class="card-body text-center">
                                                <img style="width:300px;height:243px;"src="img/${p.hotelUrl}">
                                                <!--Category & Title-->
                                                <h5>
                                                    <strong>
                                                        <a class="dark-grey-text">Hotel Name:
                                                            <span class="badge badge-pill blue-gradient">${p.hotelName}</span>
                                                        </a>
                                                    </strong>
                                                </h5>

                                                <h5>
                                                    <strong>
                                                        <a class="dark-grey-text">Hotel Area   <i class="fas fa-map-marker-alt"></i> :

                                                            <span class="badge badge-pill deep-orange">${p.areaName}</span>
                                                        </a>
                                                    </strong>
                                                </h5>





                                            </div>
                                            <!--Card content-->

                                        </div>
                                        <!--Card-->

                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${requestScope.LIST!=null and not empty requestScope.LIST}"> 
                            <c:forEach items="${LIST}" var="p">

                                <div style="display:flex;margin-bottom: 25px;">
                                    <!--Grid column-->
                                    <div class="col-6 col-sm-8">

                                        <!--Card-->
                                        <div style="flex-flow: row;"class="card">
                                            <img style="width:500px;height:350px;"src="img/${p.imageUrl}"
                                                 alt="">

                                            <!--Card content-->
                                            <div style="text-align:center;display:grid;" class="card-body ">
                                                <!--Category & Title-->  

                                                <h5>
                                                    <strong>
                                                        <a class="dark-grey-text">Room Name
                                                            <span class="badge badge-pill blue">${p.name}</span>
                                                        </a>
                                                    </strong>
                                                </h5>
                                                <h5>
                                                    <strong>
                                                        <a class="dark-grey-text">Room Type
                                                            <span class="badge badge-pill blue">${p.type}</span>
                                                        </a>
                                                    </strong>
                                                </h5>


                                                <h5>
                                                    <strong>
                                                        <a class="dark-grey-text">Unit In Stock 
                                                            <span class="badge badge-pill deep-orange">${p.unitInStock}</span>
                                                        </a>
                                                    </strong>
                                                </h5>

                                                <h5>
                                                    <strong>
                                                        <%
                                                            String from1 = (String) request.getAttribute("from");
                                                            String to1 = (String) request.getAttribute("to");
                                                        %>
                                                        <strong class="font-weight-bold blue-text">${p.price} $</strong>
                                                        <c:url var="add" value="MainController">
                                                            <c:param name="action" value="Add"></c:param>
                                                            <c:param name="checkinDate" value="${checkinDate}"></c:param>
                                                            <c:param name="checkinDate1" value="<%=from1%>"></c:param>
                                                            <c:param name="checkoutDate" value="${checkoutDate}"></c:param>
                                                            <c:param name="checkoutDate1" value="<%=to1%>"></c:param>
                                                            <c:param name="hotelName" value="${p.hotelName}"></c:param>
                                                            <c:param name="hotelID" value="${p.hotelID}"></c:param>
                                                            <c:param name="typeRoom" value="${p.type}"></c:param>
                                                            <c:param name="roomID" value="${p.roomID}"></c:param>
                                                            <c:param name="roomName" value="${p.name}"></c:param>
                                                            <c:param name="price" value="${p.price}"></c:param>
                                                            <c:param name="unitInStock" value="${p.unitInStock}"></c:param>
                                                            <c:param name="image" value="${p.imageUrl}"></c:param>

                                                        </c:url>

                                                        <a href="${pageScope.add}" class="dark-grey-text">
                                                            <span class="badge badge-pill primary-color-dark">Add To Cart</span>
                                                        </a>
                                                    </strong>
                                                </h5>




                                            </div>
                                            <!--Card content-->

                                        </div>
                                        <!--Card-->

                                    </div>
                                    <!--Grid column-->
                                    <div class="col-6 col-sm-4">

                                        <!--Card-->
                                        <div class="card">

                                            <!--Card content-->
                                            <div class="card-body text-center">
                                                <img style="width:300px;height:243px;"src="img/${p.hotelUrl}">
                                                <!--Category & Title-->
                                                <h5>
                                                    <strong>
                                                        <a class="dark-grey-text">Hotel Name:
                                                            <span class="badge badge-pill blue-gradient">${p.hotelName}</span>
                                                        </a>
                                                    </strong>
                                                </h5>

                                                <h5>
                                                    <strong>
                                                        <a class="dark-grey-text">Hotel Area   <i class="fas fa-map-marker-alt"></i> :

                                                            <span class="badge badge-pill deep-orange">${p.areaName}</span>
                                                        </a>
                                                    </strong>
                                                </h5>





                                            </div>
                                            <!--Card content-->

                                        </div>
                                        <!--Card-->

                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>

                        <c:if test="${requestScope.NOTFOUND!=null }"> 
                            <h3>${requestScope.NOTFOUND}</h3>
                        </c:if>



                    </div>
                    <!--Grid row-->



                </section>
                <!--Section: Products v.3-->

                <!--Pagination-->
                <nav class="d-flex justify-content-center wow fadeIn">
                    <ul class="pagination pg-blue">

                        <!--Arrow left-->


                        <c:if test="${requestScope.save==null and requestScope.index!=null}">
                            <c:forEach begin="1" end="${moonDao.numberPageByStatus}" var="c">
                                <c:set var="index" value="${param.index}"></c:set>
                                    <li class="page-item active">
                                        <a id="${c}"class="page-link" href="PagingController?index=${c}&search=${param.search}&cate=${cate}">${c}

                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                        <c:if test="${requestScope.save==null and requestScope.index==null}">
                            <c:forEach begin="1" end="${moonDao.numberPageByStatus}" var="c">
                                <c:set var="index" value="${param.index}"></c:set>
                                    <li class="page-item active">
                                        <a id="${c}"class="page-link" href="PagingController?index=${c}&search=${param.search}&cate=${cate}">${c}

                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                        <c:if test="${requestScope.check1!=null}">
                            <c:forEach begin="1" end="${moonDao.getNumberPageByLessPrice(30)}" var="c">
                                <c:set var="index" value="${param.index}"></c:set>
                                    <li class="page-item active">
                                        <a id="${c}"class="page-link" href="PagingController?search3=${param.search3}&index=${c}&search=${param.search}&cate=${cate}">${c}

                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                        <c:if test="${requestScope.check2!=null}">
                            <c:forEach begin="1" end="${moonDao.getNumberPageByBetweenPrice(60)}" var="c">
                                <c:set var="index" value="${param.index}"></c:set>
                                    <li class="page-item active">
                                        <a id="${c}"class="page-link" href="PagingController?search3=${param.search3}&index=${c}&search=${param.search}&cate=${cate}">${c}

                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                        <c:if test="${requestScope.check3!=null}">
                            <c:forEach begin="1" end="${moonDao.getNumberPageByBiggerPrice(60)}" var="c">
                                <c:set var="index" value="${param.index}"></c:set>
                                    <li class="page-item active">
                                        <a id="${c}"class="page-link" href="PagingController?search3=${param.search3}&index=${c}&search=${param.search}&cate=${cate}">${c}

                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                        <c:if test="${requestScope.LIST2!=null }">

                            <%
                            %>
                            <c:set var="search1" value="${requestScope.save}"></c:set>
                            <c:forEach begin="1" end="${moonDao.getNumberPageByStatusAndCategoryIDAndSearch(search1,cate)}" var="c">
                                <c:set var="index" value="${param.index}"></c:set>
                                    <li class="page-item active">
                                        <a id="${c}"class="page-link" href="PagingController?index=${c}&search=${param.search}&cate=${cate}&cate2=${requestScope.cate2}">${c}

                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>


                    </ul>
                </nav>
                <!--Pagination-->

            </div>
        </main>
        <!--Main layout-->

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
        <script>  <%    String check = (String) request.getAttribute("CHECKDAY");
            if (check != null) {
            %>

                                alert("${requestScope.CHECKDAY}");
            <%}%>

            <%    String add = (String) request.getAttribute("ADD");
                if (add != null) {
            %>

                                alert("${requestScope.ADD}");
            <%}%>
            <%    String mess = (String) request.getAttribute("SUCCESS");
                if (mess != null) {
            %>

                                alert("${requestScope.SUCCESS}");
            <%}%>
        </script>
    </body>

</html>
