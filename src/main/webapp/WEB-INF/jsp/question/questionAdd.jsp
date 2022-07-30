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
                    <li><a href="#">Question</a></li>
                    <li><a href="#">Add Question</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->
                    <div class="row">
                        <div class="col-md-12">
                            <form:form cssClass="form-horizontal" modelAttribute="question" method="POST" name="form1" id="form1" enctype="multipart/form-data">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Add Question</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Question Type</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:select path="questionType.questionTypeId" id="questionTypeId" cssClass="form-control select" onchange="checkQuestionType(this);">
                                                             <option value="" label="  -  "></option>
                                                    <form:options items="${questionTypeList}" itemLabel="questionTypeName" itemValue="questionTypeId"/>
                                                </form:select>
                                                <span class="help-block">Please enter the question</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Test</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:select path="questionTestType.questionTestTypeId" cssClass="form-control select">
                                                    <option value="" label="  -  "></option>
                                                    <form:options items="${testList}" itemLabel="questionTestTypeName" itemValue="questionTestTypeId"/>
                                                </form:select>
                                                <span class="help-block">Please enter test name</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Name</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="questionName"  class="form-control"/>
                                                <span class="help-block">Please enter the name</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-2 col-xs-12 control-label">Description</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:textarea path="description"  cssClass="form-control"/>
                                                <span class="help-block">Please enter description</span>
                                            </div>
                                        </div>
                                        <div class="form-group">                                        
                                            <label class="req col-md-2 col-xs-12 control-label">Do you want showing Desciption for the Taker</label>
                                            <div class="col-md-6 col-xs-12">  
                                                &nbsp;&nbsp;&nbsp;    On <form:radiobutton path="descToggle" value="true" code="yes" checked="true"/>
                                                &nbsp;&nbsp;&nbsp; Off
                                                <form:radiobutton path="descToggle" value="false" code="no" />
                                            </div>
                                        </div></br>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Sort Order</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="sortOrder"  class="form-control"/>
                                                <span class="help-block">Please enter the sort order</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Trait</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:select path="questionTrait.traitId" cssClass="form-control select">
                                                    <option value="" label="  -  "></option>
                                                    <form:options items="${traitList}" itemLabel="traitName" itemValue="traitId"/>
                                                </form:select>
                                                <span class="help-block">Please enter the trait</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Aspects</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:select path="questionAspect.aspectId" cssClass="form-control select">
                                                    <option value="" label="  -  "></option>
                                                    <form:options items="${aspectList}" itemLabel="aspectName" itemValue="aspectId"/>
                                                </form:select>
                                                <span class="help-block">Please enter the aspects</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Dimension</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:select path="questionDimension.dimensionId" cssClass="form-control select">
                                                    <option value="" label="  -  "></option>
                                                    <form:options items="${dimensionList}" itemLabel="dimensionName" itemValue="dimensionId"/>
                                                </form:select>
                                                <span class="help-block">Please enter the dimension</span>
                                            </div>
                                        </div>
                                        <div class="form-group">                                        
                                            <label class="col-md-2 col-xs-12 control-label"></label>
                                            <div class="col-md-6 col-xs-12">                                                                                
                                                <form:checkbox path="isReverseKey" />&nbsp;<b>Is Reverse Key</b>
                                            </div>
                                        </div>        
                                        <div class="form-group">                                        
                                            <label class="req col-md-2 col-xs-12 control-label">Status</label>
                                            <div class="col-md-6 col-xs-12">                                                                                
                                                <form:select path="active" cssClass="form-control select">
                                                    <form:option value="1" selected="selected" label="Active"/>
                                                    <form:option value="0" label="Inactive"/>
                                                </form:select>
                                                <span class="help-block">Select a status</span>
                                            </div>
                                        </div>
                                        <div class="form-group" id="questionOptionsDiv">                                        
                                            <label class="req col-md-2 col-xs-12 control-label">Question Options</label>
                                            <div class="col-md-6 col-xs-12" >                                                                                
                                                <form:select path="questionOptions" cssClass="form-control select" >
                                                    <form:option value="" label="-"/>
                                                    <form:options items="${questionOptionList}" itemLabel="questionOptionName" itemValue="questionOptionId"/>
                                                </form:select>
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
                    'questionType.questionTypeId': {
                        required: true
                    },
                    questionName: {
                        required: true
                    },
                    'questionAspect.aspectId': {
                        required: true
                    },
                    'questionDimension.dimensionId': {
                        required: true
                    },
                    'questionTrait.traitId': {
                        required: true
                    },
                    'questionTestType.questionTestTypeId': {
                        required: true
                    },
                    'sortOrder': {
                        required: true
                    }
//                    ,
//                    'questionOptions': {
//                        required: true
//                    }
                    ,
                    'active': {
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

            function checkQuestionType(questionTypeId) {
               if($('#questionTypeId').val()==2 || $('#questionTypeId').val()==3 || $('#questionTypeId').val()==6 )
               {
                    $('#questionOptionsDiv').hide();
               }else{
                  $('#questionOptionsDiv').show();  
               }
            };
        </script>

    </body>
</html>