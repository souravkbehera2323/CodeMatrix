<%--
    Document   : userAdd
    Created on : 18th Feb 2016, 23:07:50
    Author     : Akil Mahimwala
    Modified on : 
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- META SECTION -->
        <%@include file="../common/meta.jsp"%>
        <!-- END META SECTION -->

        <!-- CSS INCLUDE -->
        <%@include file="../common/css.jsp"%>
        <!-- EOF CSS INCLUDE -->
    </head>
    <body>
        <!-- START PAGE CONTAINER -->
        <div class="page-container page-navigation-toggled page-container-wide">
            <%@include file="../common/sidebar.jsp" %>

            <!-- PAGE CONTENT -->
            <div class="page-content">
                <%@include file="../common/topbar.jsp" %>

                <!-- START BREADCRUMB -->
                <ul class="breadcrumb">
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Admin</a></li>
                    <li><a href="#">User</a></li>
                    <li><a href="#">Add User</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->
                    <div class="row">
                        <div class="col-md-12">
                            <form:form cssClass="form-horizontal" modelAttribute="user" method="POST" name="form1" id="form1" enctype="multipart/form-data">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Add New User</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Name</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="name" maxlength="50" cssClass="form-control"/>
                                            </div>
                                        </div></br>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Email Id</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="emailId" maxlength="50" cssClass="form-control"/>
                                            </div>
                                        </div></br>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Username</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="username" maxlength="25" cssClass="form-control"/>
                                            </div>
                                        </div></br>

                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Password</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:password path="password" maxlength="50" cssClass="form-control"/>
                                            </div>
                                        </div></br>
                                       
                                        <div class="form-group">                                        
                                            <label class="req col-md-2 col-xs-12 control-label">Role</label>
                                            <div class="col-md-6 col-xs-12">                                                                                
                                                <form:select path="roles" cssClass="form-control select">
                                                    <form:option value="" label="-"/>
                                                    <form:options items="${roleList}" itemLabel="roleName" itemValue="roleId"/>
                                                </form:select>
                                            </div>
                                        </div></br>
                                    
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Status</label>
                                            <div class="col-md-6 col-xs-12">                                                                                
                                                &nbsp;&nbsp;&nbsp;    Active <form:radiobutton path="active" value="true" code="yes" checked="true"/>
                                                &nbsp;&nbsp;&nbsp;Inactive
                                                <form:radiobutton path="active" value="false" code="no" />
                                            </div>
                                        </div></br>

                                        <div class="col-md-12 col-xs-12">                                                                                
                                            <spring:hasBindErrors name="user">
                                                <span class="text-danger">
                                                    <form:errors path="*"/>
                                                </span>
                                            </spring:hasBindErrors>
                                        </div>
                                    </div>
                                    <div class="panel-footer">
                                        <div class="pull-right">
                                            <button type="submit" class="btn btn-success">Submit</button>
                                            <button type="submit" name="_cancel" formnovalidate="formnovalidate" class="btn btn-primary">Cancel</button>
                                        </div>
                                    </div>

                                </div>
                            </form:form>
                        </div>
                    </div> 
                </div>
                <!-- END PAGE CONTENT WRAPPER -->
            </div>
            <!-- END PAGE CONTENT -->
        </div>
        <!-- END PAGE CONTAINER -->

        <%@include file="../common/messagebox.jsp" %>

        <%@include file="../common/script.jsp" %>
        <!-- START THIS PAGE PLUGINS-->        
        <script type='text/javascript' src='../js/plugins/icheck/icheck.min.js'></script>        
        <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        <script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-select.js"></script>
        <script type='text/javascript' src='../js/plugins/jquery-validation/jquery.validate.js'></script>
        <!-- END THIS PAGE PLUGINS-->        

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>        
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->

        <script type="text/javascript">

            var jvalidate = $("#form1").validate({
                ignore: [],
                rules: {
                    username: {
                        required: true,
                        minlength: 4,
                        maxlength: 25
                    },
                    name: {
                        required: true,
                        maxlength: 50
                    },
                    emailId: {
                        maxlength: 50,
                        email:true
                    },
                    phoneNo: {
                        maxlength: 15
                    },
                    password: {
                        required: true
                    },
                    "roles": {
                        required: true
                    }
                },
                errorPlacement: function (error, element) {
                    if (element.hasClass('select')) {
                        error.insertAfter(element.next(".bootstrap-select"));
                        element.next("div").addClass("error");
                    } else {
                        error.insertAfter(element);
                    }
                }
            });

        </script>
    </body>
</html>