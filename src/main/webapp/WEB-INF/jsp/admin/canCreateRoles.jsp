<%--
    Document   : canCreateRoles
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
                    <li><a href="#">Manage Role</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->
                    <div class="row">
                        <div class="col-md-12">
                            <form:form cssClass="form-horizontal" modelAttribute="canCreateRole" method="POST" name="form1" id="form1" action="../admin/canCreateRoles.htm">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Manage Role</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Role</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="role.roleId" cssClass="form-control" readonly="readonly"/>
                                                <span class="help-block">Role that you are managing</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Can Create Roles</label>
                                            <div class="col-md-6 col-xs-12">
                                                <select name="canCreateRoleList" id="canCreateRoleList" class="form-control select" multiple="multiple">
                                                    <c:forEach  items="${allRoleList}" var="r">
                                                        <c:set var="found" value="0"/>
                                                        <c:forEach items="${canCreateRole.canCreateRoles}" var="myRole">
                                                            <c:if test="${r.roleId==myRole.roleId}"><c:set var="found" value="1"/></c:if>
                                                        </c:forEach>
                                                        <c:choose>
                                                            <c:when test="${found==1}"><option  selected="selected" value="${r.roleId}">${r.roleName}</option></c:when>
                                                            <c:otherwise><option value="${r.roleId}">${r.roleName}</option></c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                                <span class="help-block">Select the Roles this Role can create Users for</span>
                                            </div>
                                        </div>
                                        <div class="col-md-12 col-xs-12">                                                                                
                                            <spring:hasBindErrors name="canCreateRole">
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
        
    </body>
</html>