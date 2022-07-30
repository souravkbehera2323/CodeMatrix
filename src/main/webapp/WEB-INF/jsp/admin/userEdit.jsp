<%--
    Document   : userEdit
    Created on : 18 Aug, 2014, 10:23:38 AM
    Author     : Akil Mahimwala
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
                    <li><a href="#">Edit User</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->
                    <div class="row">
                        <div class="col-md-12">
                            <form:form cssClass="form-horizontal" modelAttribute="user" method="POST" name="form1" id="form1" enctype="multipart/form-data" action="../admin/userEdit.htm">
                                <form:hidden path="userId"/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Edit User - User Id[${user.userId}]</h3>
                                    </div>
                                    <div class="panel-body">

                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Name</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="name" maxlength="50" cssClass="form-control"/>
                                                <span class="help-block">Please enter the Name of the user</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Email Id</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="emailId" maxlength="50" cssClass="form-control"/>
                                                <span class="help-block">Please enter the Email Id of the user</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Username</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="username" maxlength="25" cssClass="form-control"/>
                                                <span class="help-block">Please enter the Username that the User will use to login</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-2 col-xs-12 control-label">Password</label>
                                            <div class="col-md-6 col-xs-12">
                                                <input type="text" id="password" name="password" maxlength="25" Class="form-control"  />
                                                <span class="help-block">Please enter the Password that the User will use to login</span>
                                            </div>
                                        </div>
                                        <div class="form-group">                                        
                                            <label class="col-md-2 col-xs-12 control-label">Status</label>
                                            <div class="col-md-6 col-xs-12">                                                                                
                                                Active <form:radiobutton path="active" value="true"/>
                                                &nbsp;&nbsp;&nbsp;Inactive <form:radiobutton path="active" value="false"/>
                                                <span class="help-block">Select "active" if you want the user to active</span>
                                            </div>
                                        </div>
                                        <div class="form-group">                                        
                                            <label class="req col-md-2 col-xs-12 control-label">Role</label>
                                            <div class="col-md-6 col-xs-12">                                                                                
                                                <form:select path="roles" cssClass="form-control select" items="${roleList}" itemLabel="roleName" itemValue="roleId" multiple="true"/>
                                                <span class="help-block">Select a Role for this user, you can select more than one Role</span>
                                            </div>
                                        </div>
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
                "roles": {
                    required: true
                }
//                ,
//                password:{
//                    required :true
//                    
//                }
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