<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="Rus">
<head>
    <meta charset="utf-8"/>
    <title>${title}</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/swiper.min.css"/>" rel="stylesheet">

    <script type="application/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <script type="application/javascript" src="<c:url value="/resources/js/swiper.min.js"/>"></script>
</head>

<body>
<div id="header-wrapper">
    <div id="header" class="container">
        <div id="logo">
            <h1><a href="#">Art-$hop</a></h1>
            <p>Artur pawn$hop</p>
        </div>
        <div id="menu">
            <ul>
                <li><a href="<c:url value="/index"/>"><spring:message code="Main"/></a></li>
                <li><a href="<c:url value="/register"/>"><spring:message code="Register"/></a></li>
                <li><a href="<c:url value="/login"/>"><spring:message code="Login"/></a></li>
                <li><a href="<c:url value="/about"/>"><spring:message code="about"/></a></li>

                    <%
                        String locale = "";
                        for (Cookie c : request.getCookies()) {
                            if (c.getName().equals("locale")) {
                                locale = c.getValue();
                                break;
                            }
                        }

                    %>





                    <form >
                        <select name="lang" onchange="this.form.submit()">
                            <c:forEach items=" ,Rus,Eng" var="i">
                                <%
                                    String i = (String) pageContext.getAttribute("i");

                                %>

                                <option value="${i}" <%if(locale.equals(i)){%>selected<%} else %>>${i}</option>
                            </c:forEach>
                        </select>
                    </form>


                </li>
            </ul>
        </div>
    </div>
    <div id="banner" class="container">
        <div class="image-style">


            <!-- Swiper -->
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <img src="<c:url value="/resources/images/1.jpg"/>" height="320" alt width=100%/>
                    </div>
                    <div class="swiper-slide">
                        <img src="<c:url value="/resources/images/2.jpg"/>" height="320" alt width=100%/>
                    </div>
                    <div class="swiper-slide">
                        <img src="<c:url value="/resources/images/3.jpg"/>" height="320" alt width=100%/>
                    </div>
                    <div class="swiper-slide">
                        <img src="<c:url value="/resources/images/4.jpg"/>" height="320" alt width=100%/>
                    </div>
                    <div class="swiper-slide">
                        <img src="<c:url value="/resources/images/5.jpg"/>" height="320" alt width=100%/>
                    </div>

                </div>
                <!-- Add Arrows -->
                <!-- <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div> -->
            </div>

            <!-- Initialize Swiper -->
            <script>
                var swiper = new Swiper('.swiper-container', {
                    speed: 200,
                    spaceBetween: 5000,
                    loop: true,
                    lazy: true,
                    autoplay: true,
                    navigation: {
                        nextEl: '.swiper-button-next',
                        prevEl: '.swiper-button-prev',
                    },
                });
            </script>

        </div>
    </div>
</div>



<div id="wrapper">
    <div id="splash" class="container"><span><spring:message code="splash_span"/> </span><spring:message code="splash"/>
    </div>
    <div id="page" class="container">
        <div id="content">

            <jsp:include page="${view}"/>

        </div>
        <!-- end #content -->
        <div id="sidebar">
            <jsp:include page="${menu}"/>


        </div>
        <!-- end #sidebar -->
        <div style="clear: both;">&nbsp;</div>
    </div>
    <!-- end #page -->
</div>
<div id="footer-content" class="container">
    <div id="footer-bg">
        <div id="column1">
            <h2>BEst repositoryes</h2>
            <ul class="list-style2">
                <li class="first"><a href="https://github.com/AiceFirst/spp">Artur Rep</a></li>
                <li><a href="#">Tima Rep</a></li>
                <li><a href="https://github.com/kiri110v/spp">Sanya Rep</a></li>
            </ul>
        </div>
        <div id="column2">

        </div>
        <div id="column3">

        </div>
    </div>
</div>
<div id="footer">
    <h1>Our best site</h1>
</div>
<!-- end #footer -->
</body>
</html>
