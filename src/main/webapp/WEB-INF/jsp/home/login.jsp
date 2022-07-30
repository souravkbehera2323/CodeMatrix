
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="body-full-height">
    <head><!-- META SECTION -->
        <%@include file="../common/meta.jsp"%>
        <!-- END META SECTION -->
        <!-- CSS INCLUDE -->        
        <%@include file="../common/css.jsp"%>
        <!-- EOF CSS INCLUDE -->                                    
        <style> 
            input {
                width: 100%;
            } 
        </style>
    </head>
    <body onload="$('#username').focus();">
        <div class="login-container" id="backgroundId">

            <div class="login-box animated fadeInDown">
                <!--<a href="https://www.altius.cc"><div class="login-logo"></div></a>-->

                <div class="login-body">

                    <div class="login-titleLo"><strong>Administration Panel</strong></div>

                    <!-- START LOGIN FORM HERE -->
                    <form action="../home/login.htm" class="form-horizontal" method="post" >
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <!--<label class=" control-label">Email Id</label>-->
                        <div class="form-group">
                            <div class="col-md-12">
                                Email Id <input type="text" class="form-control" style="background-color: #EEE; border-color:#196aaf;" placeholder="Email Id" name="username" id="username" value="${sessionScope[SPRING_SECURITY_LAST_USERNAME]}" />
                            </div>
                        </div>
                        <!--<label class="control-label">Password</label>-->
                        <div class="form-group">
                            <div class="col-md-12">
                                Password  <input type="password" id="password" class="form-control" style="background-color: #EEE; border-color:#196aaf;" placeholder="Password" name="password"/>
                            </div>
                        </div>
                        <div class="form-group">                            
                            <div class="col-md-12">
                                <button class="btn btn-info btn-block" id="loginBtn">Log In</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <c:if test="${param.error != null}">
                                    <span class="text-danger">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
                                </c:if>
                                <c:if test="${param.logout != null}">
                                    <div>
                                        <span class="text-danger">You have been logged out.</span>
                                    </div>
                                </c:if>
                                <div class="pull-right">
                                    <p class="text-builtBy">ver <spring:eval expression="@versionProperties.getProperty('major')" />.<spring:eval expression="@versionProperties.getProperty('minor')" /></p>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="login-footer">
                    <div class="pull-left">
                        <p><h6>©2019. All rights reserved.</h6>
                        <p><h6> C.O.D.E. Administration Panel</h6>
                        <p><h6>Running CODEkor Version 1.08 released on 12/07/2018</h6>
                        <p><a href="https://www.altius.cc">About</a> | <a href="https://www.altius.cc">Contact Us</a></p>
                    </div>

                </div>
                <!-- MESSAGE SECTION -->
                <%@include file="../common/message.jsp"%>
                <!-- END MESSAGE SECTION -->
                <%@include file="../common/script.jsp" %>
            </div>
        </div>
        <%--            <script>
                        <sec:authorize access="hasAnyRole('ROLE_TAKER')">
                         $("#backgroundId").css({"background": "#808080"});
                        </sec:authorize>
                    </script>--%>
    </body>
</html>