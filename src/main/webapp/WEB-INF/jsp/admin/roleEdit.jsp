<%--
    Document   : roleEdit
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
                    <li><a href="#">Role</a></li>
                    <li><a href="#">Edit Role</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->
                    <div class="row">
                        <div class="col-md-12">
                            <form:form cssClass="form-horizontal" modelAttribute="role" method="POST" name="form1" id="form1" action="../admin/roleEdit.htm">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Edit Role</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Role Id</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="roleId" maxlength="25" cssClass="form-control" readonly="true" />
                                                <span class="help-block">Please enter the RoleId that you want to created</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Role name</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="roleName" maxlength="25" cssClass="form-control"/>
                                                <span class="help-block">Please enter the Name of the Role"</span>
                                            </div>
                                        </div>
                                        <div class="form-group">                                        
                                            <label class="req col-md-2 col-xs-12 control-label">Business Functions</label>
                                            <div class="col-md-6 col-xs-12">                                                                                
                                                <select id="businessFunctionList" name="businessFunctionList" class="form-control select" multiple="multiple">
                                                    <c:forEach  items="${availableBusinessFunctionList}" var="abf">
                                                        <c:set var="found" value="0"/>
                                                        <c:forEach items="${role.businessFunctions}" var="mbf">
                                                            <c:if test="${abf.businessFunctionId==mbf.businessFunctionId}"><c:set var="found" value="1"/></c:if>
                                                        </c:forEach>
                                                        <c:choose>
                                                            <c:when test="${found==1}"><option selected="selected" value="${abf.businessFunctionId}">${abf.businessFunctionDesc}</option></c:when>
                                                            <c:otherwise><option value="${abf.businessFunctionId}">${abf.businessFunctionDesc}</option></c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                                <span class="help-block">Select the Business Functions this Role has access to</span>
                                            </div>
                                        </div>
                                        <div class="col-md-12 col-xs-12">                                                                                
                                            <spring:hasBindErrors name="role">
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
                    roleId: {
                        required: true,
                        minlength: 4,
                        maxlength: 25
                    },
                    roleName: {
                        required: true,
                        maxlength: 50
                    },
                    "businessFunctionList": {
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