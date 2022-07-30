<%--
    Document   : questionAdd
    Created on : 18th Oct 2019, 23:07:50
    Author     : Yogesh Bhagwat
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
                    <li><a href="#">Test Result</a></li>
                    <li><a href="#">Edit Taker</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->
                    <div class="row">
                        <div class="col-md-12">
                            <form:form cssClass="form-horizontal" modelAttribute="takerAttemptedQuestionsList" method="POST" name="form1" id="form1" enctype="multipart/form-data" action="../takers/editNoOfQuestionAttemptedByTaker.htm">
                                <form:input type="hidden" name="takerId" path="takerId" class="form-control" />
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Edit Taker</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Taker Name</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input type="text"  path="firstName" class="form-control" disabled="true"  />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">No Of Question Attempted</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input type="text" name="noOfQuestionAttempted" path="noOfQuestionAttempted"  class="form-control" disabled="true"  />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Email</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input type="text" name="Email" path="Email"  class="form-control" disabled="true"  />
                                            </div>
                                        </div>
                                        <div class="form-group">                                        
                                            <label class="req col-md-2 col-xs-12 control-label">Status</label>
                                            <div class="col-md-6 col-xs-12">                                                                                
                                                <form:select path="active" cssClass="form-control select">
                                                    <form:option value="1" selected="selected" label="Enable"/>
                                                    <form:option value="0" label="Disable"/>
                                                </form:select>
                                                <span class="help-block">Select "enable" if you want the taker to enable</span>
                                            </div>
                                        </div>
                                        <div class="col-md-12 col-xs-12">                                                                                
                                            <spring:hasBindErrors name="takerAttemptedQuestionsList">
                                                <span class="text-danger">
                                                    <form:errors path="*"/>
                                                </span>
                                            </spring:hasBindErrors>
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
        <script type='text/javascript' src='../js/plugins/jquery-validation/jquery.validate.js'></script>
        <script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-select.js"></script>
        <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        <!-- END THIS PAGE PLUGINS-->        

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>        
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->


    </body>
</html>