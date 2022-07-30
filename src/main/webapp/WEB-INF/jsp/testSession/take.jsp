<%--
    Document   : taker
    Created on : 24t Nov 2019, 23:07:50
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

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>jQuery UI Sortable - Handle empty lists</title>
        <style>
            #sortable1, #sortable2, #sortable3 { list-style-type: none; margin: 0; float: left; margin-right: 10px; background: #eee; padding: 5px; width: 143px;}
            #sortable1 li, #sortable2 li, #sortable3 li { margin: 5px; padding: 5px; font-size: 1.2em; width: 120px; }

            .select ul {
                li: ui-state-default;
            }
            [class*='col'] {
                position: static;
            }
            div.fixed {
                position: fixed;
                margin-left: -50px; 
            }
            label.radioId {  
                /*position: fixed;*/
                font-size: 15px;
                /*font-size: 2.2vmin;*/
            }

        </style>
        <style>
            #sortableContainer { list-style-type: none; margin: 0; padding: 0; width: 60%; }
            #sortableContainer li { margin: 0 3px 3px 3px; padding: 0.4em; padding-left: 1.5em; font-size: 1.4em; height: 60%; }
            #sortableContainer li span { position: absolute; margin-left: -1.3em; }

        </style>
    </head>
    <body>
        <!-- START PAGE CONTAINER -->
        <div class="page-navigation-toggled page-container-wide">

            <!-- PAGE CONTENT -->
            <div class="page-content" style="background-color: #012e4f;">
                <div class="col-md-12">
                    <div class="col-md-10"><img src="../img/code_logo.png" alt="..." class="img-responsive img-fluid rounded mx-auto d-block center-block"></div>


                    <div class="col-md-3 pull-right ">
                        <a href="#" onclick="myFunction()"  style="color: white;" ><span class="fa fa-bars fa-2x" ></span> <span class="xn-text"></span></a>
                        <div id="loginAndContinue" hidden="true" class="fixed">
                            <a href="../home/login.htm" class="alink" style="color: white;">Login & View Results</a><br>
                            <a href="../takers/resume.htm" id="regBtn" style="color: white;">Continue Test</a><br>
                            <a href="#" class="add-button" style="color: white;">Install C.O.D.E.</a>
                        </div>
                    </div>

                </div>

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">

                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->
                    <div class="row">
                        <div class="col-md-12">
                            <form:form modelAttribute="testSession" method="POST" name="form1" id="form1" action="">
                                <div class="panelTaker panel-default">
                                    <div class="panel-body">

                                        <div class="form-group">
                                            <div class="col-md-2 col-xs-12"></div>
                                            <div class="col-md-8">
                                                <form:hidden  path="testSessionId"/>
                                                <h4><font color="white">User : ${testSession.taker.firstName}</h4>
                                                <h4><font color="white">#Test Session ID ${testSession.testSessionId}</font></h4>
                                                    <form:hidden path="currentQuestion.questionTypeId"/>
                                                    <form:hidden path="currentQuestion.sortOrder"/>
                                                    <form:hidden path="currentQuestion.questionId"/>
                                                    <form:hidden path="taker.takerId"/>
                                                <h4><b><font color="white">Progress - </font></b><font color="white">Question Id #${testSession.noOfQuestionsAnswered+1} <c:if test="${developerValue==1}"><span style="color: red; font-size: 12px;">Is Question a Reverse key: ${testSession.currentQuestion.reverseKey}</span></c:if></h4>
                                                    <div class="progress progress-striped progress-striped active" >
                                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="95" aria-valuemin="0" aria-valuemax="594" style="width: ${percentage}%;"></div>
                                                </div>
                                                <h4><font color="white">You have answered ${testSession.noOfQuestionsAnswered} questions </font></h4>
                                                <%--<h4><font color="white">You are now </font><font color="#F49F1C"><b>${takerLabel}</b></font></h4><br>--%>
                                                <h3><font color="white">${testSession.currentQuestion.name}</font></h3>
                                                <h4><font color="white"> <c:if test="${testSession.currentQuestion.descToggle==true}">(${testSession.currentQuestion.description})</c:if></font></h4>
                                                    <c:choose>
                                                        <c:when test="${testSession.currentQuestion.sortOrder==3}">
                                                        <h4>
                                                            <font color="bcol-md-116lack">
                                                            <input  name="questionOption"   id="medicalProfessionalYes" type="radio" value="true" />
                                                            <font color="white"><label for="medicalProfessionalYes">Yes</label></font>
                                                            <input  name="questionOption"   id="medicalProfessionalNo" type="radio" value="false"/>
                                                            <font color="white"><label for="medicalProfessionalNo">No</label></font>
                                                            </font>
                                                        </h4>
                                                    </c:when>
                                                    <c:when test="${testSession.currentQuestion.questionTypeId==GlobalConstants.QUESTION_TYPE_ID_RADIO}">
                                                        <c:forEach items="${testSession.currentQuestion.questionOptionList}" var="item" varStatus="recordCount">
                                                            <h4>
                                                                <font color="bcol-md-116lack">
                                                                <input id="questionOptionId${item.questionOptionId}" name="questionOption"  type="radio" value= "${item.questionOptionId}"/>
                                                                </font>
                                                                <font color="white"><label class="radioId" for="questionOptionId${item.questionOptionId}">${item.questionOptionText}</label></font>
                                                            </h4>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:when test="${testSession.currentQuestion.questionTypeId==GlobalConstants.QUESTION_TYPE_ID_MULTISELECT}">
                                                        <div class="form-group">
                                                            <select name="questionOptionId" id="questionOptionId" class="form-control select" multiple="multiple">
                                                                <c:forEach items="${testSession.currentQuestion.questionOptionList}" var="item">
                                                                    <option value="${item.questionOptionId}">${item.questionOptionText}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${testSession.currentQuestion.questionTypeId==GlobalConstants.QUESTION_TYPE_ID_TEXT_FIELD}">
                                                        <h4>
                                                            <font color="black">
                                                            <input id="questionOptionId" name="questionOption" class="form-control" type="text" value= ""  />
                                                            </font>
                                                            <font color="white"></font>
                                                        </h4>
                                                    </c:when>
                                                    <c:when test="${testSession.currentQuestion.questionTypeId==GlobalConstants.QUESTION_TYPE_ID_NUMBER}">
                                                        <h4>
                                                            <font color="black">
                                                            <input id="questionOptionId" name="questionOption" class="form-control" type="text" value= ""  />
                                                            </font>
                                                            <font color="white"></font>
                                                        </h4>
                                                    </c:when>
                                                    <c:when test="${testSession.currentQuestion.questionTypeId==GlobalConstants.QUESTION_TYPE_ID_TEXT_AREA}">
                                                        <h4>
                                                            <font color="black">
                                                            <input id="questionOptionId" name="questionOption" class="form-control"  type="textarea" value= "" />
                                                            </font>
                                                            <font color="white"></font>
                                                        </h4>
                                                    </c:when>  
                                                    <c:when test="${testSession.currentQuestion.questionTypeId==GlobalConstants.QUESTION_TYPE_ID_ORDERED_GROUP}">
                                                        <div>
                                                            <ul id="sortableContainer">
                                                                <c:forEach items="${testSession.currentQuestion.questionOptionList}" var="item">                                                                        
                                                                    <li id="${item.questionOptionId}" class="ui-state-default" style="color:white; font-size: 12px; font-weight:bold; border:solid 1px;  padding: 5px;">${item.questionOptionText}</li>
                                                                    </c:forEach>
                                                            </ul>    
                                                        </div>
                                                    </c:when>      
                                                </c:choose>
                                                <c:if test="${testSession.currentQuestion.questionTypeId!=GlobalConstants.QUESTION_TYPE_ID_MULTISELECT}">
                                                    <input type="hidden" name="questionOptionId" id="questionOptionIdHidden" />
                                                </c:if>
                                                <div class="form-group" id="covidDiv" style="display: none;">                                        
                                                    <label class="col-md-4 col-xs-12 control-label" style="font-size: 12px;">Related to covid </label>
                                                    <div class="col-md-6">                                                                                
                                                        <select name="covid" maxlength="50" class="form-control select">
                                                            <option value="0">Select Any</option>
                                                            <option value="Directly related to COVID">Directly related to COVID</option>
                                                            <option value="Indirectly related to COVID">Indirectly related to COVID</option>
                                                            <option value="Not related to COVID">Not related to COVID</option>
                                                        </select>
                                                    </div>       
                                                    <br><br>
                                                </div>
                                                <div class="row">                                            
                                                    <div class="col-md-5">
                                                    </div>
                                                    <div class="col-md-5">
                                                        <button type="submit" class="btn btn-success " id="loginBtn" disabled="disabled">Answer & Continue </button>
                                                    </div>
                                                </div>
                                            </div>            
                                            <!-- Modal -->
                                            <div class="modal fade" id="myModal" role="dialog">
                                                <div class="modal-dialog modal-dialog-centered modal-lg">

                                                    <!-- Modal content-->
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                            <h4 class="modal-title">Your C.O.D.E. Test Result</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="row">
                                                                <div class="col-md-12 scrollable">
                                                                    <table class="table datatable table-bordered">
                                                                        <thead> 
                                                                            <tr>
                                                                                <th>Question</th>
                                                                                <th>Percentage</th>                                                                                                                
                                                                            </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                            <c:forEach items="${insightResult}" var="item">
                                                                                <tr>
                                                                                    <td style="color: black;">${item['question']}</td>
                                                                                    <td style="color: black;">${item['normalDstrValue']}</td>                                                            
                                                                                </tr>
                                                                            </c:forEach>                                                   
                                                                        </tbody>
                                                                    </table>                                                          
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>   
                                        </div>

                                        <c:if test="${developerValue==1}">
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-md-5">
                                                        <br/><br/>
                                                        <table class="table datatable table-bordered" >
                                                            <thead>
                                                                <tr>
                                                                    <th>Aspect Id</th>
                                                                    <th>Aspect Mean for Taker</th>
                                                                    <th>Population Mean for AspectId</th>
                                                                    <th>Population StdDev for AspectId</th>
                                                                    <th>Normal Distribution</th>
                                                                    <th>Score</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr id="trait" class="${rowStyle}">
                                                                    <td style="color:white;"><c:out value="${normalDistStats['aspect'].id}"/></td>
                                                                    <td style="color:white;"><c:out value="${normalDistStats['aspect'].currentMean}"/></td>
                                                                    <td style="color:white;"><c:out value="${normalDistStats['aspect'].populationMean}"/></td>
                                                                    <td style="color:white;"><c:out value="${normalDistStats['aspect'].populationStdDev}"/></td>
                                                                    <td style="color:white;"><c:out value="${normalDistStats['aspect'].normalDistribution}"/></td>
                                                                    <td style="color:white;"><c:out value="${normalDistStats['aspect'].score}"/></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>    
                                                    </div>
                                                    <div class="col-md-5">
                                                        <br/><br/>
                                                        <table class="table datatable table-bordered" >
                                                            <thead>
                                                                <tr>
                                                                    <th>Trait Id</th>
                                                                    <th>Trait Mean for Taker</th>
                                                                    <th>Population Mean for TraitId</th>
                                                                    <th>Population StdDev for TraitId</th>
                                                                    <th>Normal Distribution</th>
                                                                    <th>Score</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr id="aspect" class="${rowStyle}">
                                                                    <td style="color:white;"><c:out value="${normalDistStats['trait'].id}"/></td>
                                                                    <td style="color:white;"><c:out value="${normalDistStats['trait'].currentMean}"/></td>
                                                                    <td style="color:white;"><c:out value="${normalDistStats['trait'].populationMean}"/></td>
                                                                    <td style="color:white;"><c:out value="${normalDistStats['trait'].populationStdDev}"/></td>
                                                                    <td style="color:white;"><c:out value="${normalDistStats['trait'].normalDistribution}"/></td>
                                                                    <td style="color:white;"><c:out value="${normalDistStats['trait'].score}"/></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>    
                                                    </div>            
                                                </div>
                                            </div>
                                        </c:if>
                                    </div><br>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
            <!--END PAGE CONTENT WRAPPER-->
        </div>
        <!-- END PAGE CONTENT 

        <!-- END PAGE CONTENT WRAPPER 
    </div>
        <!-- END PAGE CONTENT -->
        <!-- END PAGE CONTAINER -->

        <%@include file="../common/messagebox.jsp" %>


        <%@include file="../common/script.jsp" %>        
        <!-- START THIS PAGE PLUGINS-->        
        <script type="text/javascript" src="../js/plugins/jquery-touch-punch/jquery.ui.touch-punch.min.js"></script>
        <script type='text/javascript' src='../js/plugins/icheck/icheck.min.js'></script>        
        <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        <script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-select.js"></script>
        <script type='text/javascript' src='../js/plugins/jquery-validation/jquery.validate.js'></script>
        <script type="text/javascript" src="../js/plugins/datatables/jquery.dataTables.min.js"></script>    
        <script type="text/javascript" src="../js/plugins/pwa/pwa-customization.js"></script>    
        <!-- END THIS PAGE PLUGINS-->        
        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>        
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->

        <script>
                            $(function () {
                                $("#sortableContainer").sortable();
                                $("#sortableContainer").disableSelection();
                            });
                            $("li").click(function () {
                                $(this).toggleClass("selected");
                            });
        </script>
        <script type="text/javascript">

            $("#medicalProfessionalYes") // select the radio by its id
                    .change(function () { // bind a function to the change event
                        if ($(this).is(":checked")) { // check if the radio is checked
                            var val = $(this).val(); // retrieve the value
                            if (val == "true") {
                                $("#covidDiv").show();
                            }

                        }
                    });
            $("#medicalProfessionalNo") // select the radio by its id
                    .change(function () { // bind a function to the change event
                        if ($(this).is(":checked")) { // check if the radio is checked
                            var val = $(this).val(); // retrieve the value
                            if (val == "false") {
                                $("#covid").val("0");
                                $("#covidDiv").hide();
                            }
                        }
                    });




            $('#sortableContainer').sortable();
            $("#loginBtn").click(function () {
                var scoringTemplateJson;
                if (${testSession.currentQuestion.questionTypeId==GlobalConstants.QUESTION_TYPE_ID_RADIO}) {
                    var selectedId = $("input[name='questionOption']:checked").val();
                    if (selectedId != undefined)
                    {
                        scoringTemplateJson = selectedId;
                    } else
                    {
                        alert("Please select option");
                        return false;
                    }
                    $("#questionOptionIdHidden").val(scoringTemplateJson);
                }

                if (${testSession.currentQuestion.questionTypeId==GlobalConstants.QUESTION_TYPE_ID_TEXT_FIELD 
                  || testSession.currentQuestion.questionTypeId==GlobalConstants.QUESTION_TYPE_ID_TEXT_AREA 
                  || testSession.currentQuestion.questionTypeId==GlobalConstants.QUESTION_TYPE_ID_NUMBER}) {
                    var textValue = $("#questionOptionId").val();
                    if (textValue != null && textValue != "")
                    {
                        scoringTemplateJson = textValue;
                    } else {
                        alert("Please fill the data");
                        return false;
                    }

                    $("#questionOptionIdHidden").val(scoringTemplateJson);
                }

                if (${testSession.currentQuestion.questionTypeId==GlobalConstants.QUESTION_TYPE_ID_ORDERED_GROUP}) {
                    var itemOrder = $('#sortableContainer').sortable("toArray");
                    if (itemOrder != '[object Object]')
                    {
                        scoringTemplateJson = itemOrder;
                    } else {
                        alert("Please select option");
                        return false;
                    }
                    $("#questionOptionIdHidden").val(scoringTemplateJson);
                }

                if (${testSession.currentQuestion.questionTypeId==GlobalConstants.QUESTION_TYPE_ID_MULTISELECT}) {
                    var textValue = $("#questionOptionId").val();
                    if (textValue != null && textValue != "")
                    {
                        scoringTemplateJson = textValue;
                    } else {
                        alert("Please select option");
                        return false;
                    }
                    $("#questionOptionIdHidden").val(scoringTemplateJson);
                }
            });

        </script>
        <script type="text/javascript">
            $("ul.droptrue").sortable({
                connectWith: "ul"
            });
            $("#sortable1, #sortable2, #sortable3").disableSelection();

        </script>
        <script>
            //disble automatic table sorting
            $('.table').dataTable({
                "dom": '<"toolbar">rt<"toolbar">',
                "order": [],
                "bPaginate": false
            });

            <%--  $(document).ready(function () {
              <c:if test="${resultSize > 0}">
                  $("#myModal").modal('show');
              </c:if>
              });--%>


            <c:if test="${testSession.currentQuestion.questionTypeId==GlobalConstants.QUESTION_TYPE_ID_NUMBER}">
            var jvalidate = $("#form1").validate({
                ignore: [],
                rules: {
                    questionOption: {
                        number: true
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
            </c:if>
        </script>
        <script>
            $(function () {
                $("ul.droptrue").sortable({
                    connectWith: "ul"
                });

                $("ul.dropfalse").sortable({
                    connectWith: "ul",
                    dropOnEmpty: false
                });

                $("#sortable1, #sortable2, #sortable3").disableSelection();
            });
        </script>
        <script>
            function myFunction() {
                $("#loginAndContinue").toggle();
            }
            $(window).load(function () {
                if ($('#loginBtn').length > 0) {
                    $('#loginBtn').prop('disabled', false);
                }
            });
        </script>
        <script language="JavaScript">
            javascript:window.history.forward(1);

        </script>

    </body>
</html>                                                   
