<%-- 
    Document   : listQuestionOptions
    Created on : 22 Aug, 2019, 12:41:29 PM
    Author     : altius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <li><a href="#">Question Options</a></li>
                    <li><a href="#">Create Question Options</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->
                    <div class="row">
                        <div class="col-md-12">
                            <form:form cssClass="form-horizontal" modelAttribute="questionOption" method="POST" name="form1" id="form1" enctype="multipart/form-data"  action="../questionOptions/addQuestionOptions.htm">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Add New Question Option</h3>
                                    </div>

                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Name</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="questionOptionName" maxlength="25" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        </br>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Question Value</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="questionValue" maxlength="50" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        </br>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Label Value</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="labelValue" maxlength="50" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        </br>
                                        <div class="form-group">                                        
                                            <label class="req col-md-2 col-xs-12 control-label">Sort Order</label>
                                            <div class="col-md-6 col-xs-12"> 
                                                <form:input path="sortOrder" maxlength="25" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        </br>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Status</label>
                                            <div class="col-md-6 col-xs-12">                                                                                
                                                <form:select path="active" cssClass="form-control select">
                                                    <form:option value="1" selected="selected" label="Active"/>
                                                    <form:option value="0" label="Inactive"/>
                                                </form:select>
                                            </div>
                                        </div>  
                                        </br>
                                        <div class="col-md-12 col-xs-12">                                                                                
                                            <spring:hasBindErrors name="questionOption">
                                                <span class="text-danger">
                                                    <form:errors path="*"/>
                                                </span>
                                            </spring:hasBindErrors>
                                        </div>
                                        </br>
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

        <script type="text/javascript">

            var jvalidate = $("#form1").validate({
                ignore: [],
                rules: {
                    'questionOptionName': {
                        required: true
                    },
                    questionValue: {
                        required: true
                    },
                    'labelValue': {
                        required: true
                    },
                    'active': {
                        required: true
                    },
                    'sortOrder': {
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

            $('.select').on('change', function () {
                if ($(this).val() != "") {
                    $(this).valid();
                    $(this).next('div').addClass('valid');
                } else {
                    $(this).next('div').removeClass('valid');
                }
            });

        </script>
    </body>
</html>