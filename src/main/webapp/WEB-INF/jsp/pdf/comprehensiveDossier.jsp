<%--
    Document   : testResultList
    Created on : 5th Sept 2019, 08:13 AM
    Author     : Yogesh Bhagwat
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- META SECTION -->
        <%@include file="../common/meta.jsp"%>
        <!-- END META SECTION -->
        <%@include file="../common/css.jsp"%>
        <!-- CSS INCLUDE -->
        <style type="text/css">

            .row [class^='col-md-']{
                padding-left: 1px;
                padding-right: 1px;
            }
            #valueHealthChartId{
                left: -10px;
                right: -100px;
            }
            #forcedValueChartId{
                left: -10px;
                right: -100px;
            }
            #sourcesofValidChartId{
                left: -10px;
                right: -100px;
            }
            #problemSolvingChartId{
                left: -10px;
                right: -100px;
            }
            #collaborativeApproachchartId{
                left: -10px;
                right: -100px;
            }
            #treatResponseChartId{
                left: -10px;
                right: -100px;
            }
            #mentalImprovementChartId{
                left: -10px;
                right: -100px; 
            }
            .AggreeablenessId:before           { content: "\f107"; }
            .AggreeablenessId.is-active:before { content: "\f106"; }

            .CompassionId:before           { content: "\f107"; }
            .CompassionId.is-active:before { content: "\f106"; }

            .CompassionTechnicalId:before           { content: "\f107"; }
            .CompassionTechnicalId.is-active:before { content: "\f106"; }

            .PolitenessId:before           { content: "\f107"; }
            .PolitenessId.is-active:before { content: "\f106"; }

            .PolitenessTechnicalId:before           { content: "\f107"; }
            .PolitenessTechnicalId.is-active:before { content: "\f106"; }

            .ConscientiousnessId:before           { content: "\f107"; }
            .ConscientiousnessId.is-active:before { content: "\f106"; }

            .IndustriousnessId:before           { content: "\f107"; }
            .IndustriousnessId.is-active:before { content: "\f106"; }

            .IndustriousnessTechnicalId:before           { content: "\f107"; }
            .IndustriousnessTechnicalId.is-active:before { content: "\f106"; }

            .OrderlinessId:before           { content: "\f107"; }
            .OrderlinessId.is-active:before { content: "\f106"; }

            .OrderlinessTechnicalId:before           { content: "\f107"; }
            .OrderlinessTechnicalId.is-active:before { content: "\f106"; }

            .ExtroversionId:before           { content: "\f107"; }
            .ExtroversionId.is-active:before { content: "\f106"; }

            .ExtroversionTechnicalId:before           { content: "\f107"; }
            .ExtroversionTechnicalId.is-active:before { content: "\f106"; }

            .EnthusiasmId:before           { content: "\f107"; }
            .EnthusiasmId.is-active:before { content: "\f106"; }

            .EnthusiasmTechnicalId:before           { content: "\f107"; }
            .EnthusiasmTechnicalId.is-active:before { content: "\f106"; }

            .AssertivenessId:before           { content: "\f107"; }
            .AssertivenessId.is-active:before { content: "\f106"; }

            .AssertivenessTechnicalId:before           { content: "\f107"; }
            .AssertivenessTechnicalId.is-active:before { content: "\f106"; }

            .NeuroticismId:before           { content: "\f107"; }
            .NeuroticismId.is-active:before { content: "\f106"; }

            .NeuroticismTechnicalId:before           { content: "\f107"; }
            .NeuroticismTechnicalId.is-active:before { content: "\f106"; }

            .WithdrawalId:before           { content: "\f107"; }
            .WithdrawalId.is-active:before { content: "\f106"; }

            .WithdrawalTechnicalId:before           { content: "\f107"; }
            .WithdrawalTechnicalId.is-active:before { content: "\f106"; }

            .VolatilityId:before           { content: "\f107"; }
            .VolatilityId.is-active:before { content: "\f106"; }

            .VolatilityTechnicalId:before           { content: "\f107"; }
            .VolatilityTechnicalId.is-active:before { content: "\f106"; }

            .OpennessToExpId:before           { content: "\f107"; }
            .OpennessToExpId.is-active:before { content: "\f106"; }

            .OpennessToExpTechnicalId:before           { content: "\f107"; }
            .OpennessToExpTechnicalId.is-active:before { content: "\f106"; }

            .IntellectId:before           { content: "\f107"; }
            .IntellectId.is-active:before { content: "\f106"; }

            .IntellectTechnicalId:before           { content: "\f107"; }
            .IntellectTechnicalId.is-active:before { content: "\f106"; }

            .PlayTotalId:before           { content: "\f107"; }
            .PlayTotalId.is-active:before { content: "\f106"; }

            .PlayTotalTechnicalId:before           { content: "\f107"; }
            .PlayTotalTechnicalId.is-active:before { content: "\f106"; }

            .SeekingTotalId:before           { content: "\f107"; }
            .SeekingTotalId.is-active:before { content: "\f106"; }

            .SeekingTotalTechnicalId:before           { content: "\f107"; }
            .SeekingTotalTechnicalId.is-active:before { content: "\f106"; }

            .CareTotalId:before           { content: "\f107"; }
            .CareTotalId.is-active:before { content: "\f106"; }

            .FearTotalId:before           { content: "\f107"; }
            .FearTotalId.is-active:before { content: "\f106"; }

            .AngerTotalId:before           { content: "\f107"; }
            .AngerTotalId.is-active:before { content: "\f106"; }

            .SadnessTotalId:before           { content: "\f107"; }
            .SadnessTotalId.is-active:before { content: "\f106"; }

            .SpiritualityTotalId:before           { content: "\f107"; }
            .SpiritualityTotalId.is-active:before { content: "\f106"; }

            .SpiritualityTotalTechnicalId:before           { content: "\f107"; }
            .SpiritualityTotalTechnicalId.is-active:before { content: "\f106"; }

            .LustTotalId:before           { content: "\f107"; }
            .LustTotalId.is-active:before { content: "\f106"; }

            .LustTotalTechnicalId:before           { content: "\f107"; }
            .LustTotalTechnicalId.is-active:before { content: "\f106"; }

            .EgoismId:before           { content: "\f107"; }
            .EgoismId.is-active:before { content: "\f106"; }

            .MachiavellianismId:before           { content: "\f107"; }
            .MachiavellianismId.is-active:before { content: "\f106"; }

            .NarcissismId:before           { content: "\f107"; }
            .NarcissismId.is-active:before { content: "\f106"; }

            .PsychopathyId:before           { content: "\f107"; }
            .PsychopathyId.is-active:before { content: "\f106"; }

            .MoralDisengagementId:before           { content: "\f107"; }
            .MoralDisengagementId.is-active:before { content: "\f106"; }

            .PsychologicalEntitlementId:before           { content: "\f107"; }
            .PsychologicalEntitlementId.is-active:before { content: "\f106"; }

            .PsychologicalEntitlementTechnicalId:before           { content: "\f107"; }
            .PsychologicalEntitlementTechnicalId.is-active:before { content: "\f106"; }

            .SelfInterestId:before           { content: "\f107"; }
            .SelfInterestId.is-active:before { content: "\f106"; }

            .SelfInterestTechnicalId:before           { content: "\f107"; }
            .SelfInterestTechnicalId.is-active:before { content: "\f106"; }

            .SpitefulnessId:before           { content: "\f107"; }
            .SpitefulnessId.is-active:before { content: "\f106"; }

            .SpitefulnessTechnicalId:before           { content: "\f107"; }
            .SpitefulnessTechnicalId.is-active:before { content: "\f106"; }

            .SadismId:before           { content: "\f107"; }
            .SadismId.is-active:before { content: "\f106"; }

            .WholeheartednessId:before           { content: "\f107"; }
            .WholeheartednessId.is-active:before { content: "\f106"; }

            .SenseOfSelfId:before           { content: "\f107"; }
            .SenseOfSelfId.is-active:before { content: "\f106"; }

            .ThinkingId:before           { content: "\f107"; }
            .ThinkingId.is-active:before { content: "\f106"; }

            .ActionId:before           { content: "\f107"; }
            .ActionId.is-active:before { content: "\f106"; }

            .SelftActualizationId:before           { content: "\f107"; }
            .SelftActualizationId.is-active:before { content: "\f106"; }

            .DepthId:before           { content: "\f107"; }
            .DepthId.is-active:before { content: "\f106"; }

            .AcceptanceId:before           { content: "\f107"; }
            .AcceptanceId.is-active:before { content: "\f106"; }

            .ImpactId:before           { content: "\f107"; }
            .ImpactId.is-active:before { content: "\f106"; }

            .IndividualityId:before           { content: "\f107"; }
            .IndividualityId.is-active:before { content: "\f106"; }

            .UclaLonelinessId:before           { content: "\f107"; }
            .UclaLonelinessId.is-active:before { content: "\f106"; }

            .SelfEsteemId:before           { content: "\f107"; }
            .SelfEsteemId.is-active:before { content: "\f106"; }

            .SelfEsteemTechnicalId:before           { content: "\f107"; }
            .SelfEsteemTechnicalId.is-active:before { content: "\f106"; }

            .SelfLikingId:before           { content: "\f107"; }
            .SelfLikingId.is-active:before { content: "\f106"; }

            .SelfCompetenceId:before           { content: "\f107"; }
            .SelfCompetenceId.is-active:before { content: "\f106"; }

            .SatisfactionWithLifeId:before           { content: "\f107"; }
            .SatisfactionWithLifeId.is-active:before { content: "\f106"; }

            .BeckDepressionInventoryId:before           { content: "\f107"; }
            .BeckDepressionInventoryId.is-active:before { content: "\f106"; }

            .ConnectionId:before           { content: "\f107"; }
            .ConnectionId.is-active:before { content: "\f106"; }

            .CoOpScoreId:before           { content: "\f107"; }
            .CoOpScoreId.is-active:before { content: "\f106"; }

            .MindLikertSumId:before           { content: "\f107"; }
            .MindLikertSumId.is-active:before { content: "\f106"; }

            .MasterySumId:before           { content: "\f107"; }
            .MasterySumId.is-active:before { content: "\f106"; }

            .AbstinenceSumId:before           { content: "\f107"; }
            .AbstinenceSumId.is-active:before { content: "\f106"; }

            .DarkFactorCalculatorId:before           { content: "\f107"; }
            .DarkFactorCalculatorId.is-active:before { content: "\f106"; }

            /* Helpers */
            .is-hidden { display: none; } 
        </style>
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
                    <li><a href="#">Test Results</a></li>
                    <li><a href="#">View</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <img src="../img/code_logo.png" alt="..." class="img-responsive img-fluid rounded mx-auto d-block center-block">
                    <!-- END MESSAGE SECTION -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-default">
                                <form:form modelAttribute="testSessions" method="POST" name="form1" id="form1" action="../pdf/comprehensiveDossier.htm">
                                    <h1 style="text-align: center; color:Black;">Official C.O.D.E. Dossier</h1>
                                    <h2  style="text-align: center;">#${testSessions.taker.takerId}</h2>
                                    <div class="panel-body">
                                        <h4 style="text-align: left; color:Black;"><strong>Test Taker: </strong> ${testSessions.taker.firstName} ${testSessions.taker.lastName}</h4>
                                        <h4 style="text-align: left; color:Black;"><strong>Test Started: </strong> ${testSessions.testStartedTime}</h4>
                                        <h4 style="text-align: left; color:Black;"><strong>Test Ended: </strong> ${testSessions.testEndedTime}</h4>
                                        <h4 style="text-align: left; color:Black;"><strong>Testing Session ID: </strong> ${testSessions.testSessionId}</h4>
                                    </div>
                                    <br>
                                    <!--16Jan2020-->
                                    <!--<h6 style="font-size: 18px; font-weight:normal; color:Black;">&nbsp;&nbsp;&nbsp;Note: Your dossier is a constant work in progress to bring you greater value and to help you understand yourself more comprehensively .</h6>-->
                                    <!--<h6 style="font-size: 18px; font-weight:normal; color:Black;">&nbsp;&nbsp;&nbsp;Any numbers highlighted in red should be disregarded while we work out a few bugs. We appreciate your understanding!</h6>-->
                                    <!--<br>-->
                                    <div class="panel-body">
                                        <!-- Value Module Start -->
                                        <c:set var="questionName" value=""/>
                                        <c:set var="value" value="0"/>
                                        <h2 style="text-align: left; color:Black;">VALUES DIMENSION</h2>
                                        <c:forEach items="${tsDimentionData}" var="t" >
                                            <c:if test="${t.questionResponses.question.questionDimension.dimensionId==3 && (t.questionResponses.question.questionId==1 || t.questionResponses.question.questionId==2 || t.questionResponses.question.questionId==3 || t.questionResponses.question.questionId==4 || t.questionResponses.question.questionId==5)}" >
                                                <c:if test="${questionName!=t.questionResponses.question.questionName}">
                                                    <br>
                                                    <h4>${t.questionResponses.question.questionName}</h4>
                                                </c:if>
                                                <h6>${t.questionResponses.questionOptionValue}</h6>
                                                <h6>${t.questionResponses.questionOptionAdditionalValue}</h6>

                                            </c:if> 
                                            <c:set var="questionName" value="${t.questionResponses.question.questionName}"/>
                                        </c:forEach>
                                        <c:forEach items="${questionWiseNDList}" var="valueDimensionId">
                                            <c:if test="${valueDimensionId.key==19 || valueDimensionId.key==20 || valueDimensionId.key==21}">
                                                <tr class="table_row">
                                                <br>
                                                <h4>${valueDimensionId.value['ques'].name}</h4> 
                                                <h6>score: ${valueDimensionId.value['ques'].score}</h6> 
                                                <h6>Population Percentile: ${valueDimensionId.value['ques'].normalDistribution}</h6>   
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                        <c:forEach items="${tsDimentionData}" var="t" >
                                            <c:if test="${t.questionResponses.question.questionDimension.dimensionId==3 && (t.questionResponses.question.questionId==25 || t.questionResponses.question.questionId==26 || t.questionResponses.question.questionId==27 || t.questionResponses.question.questionId==28 )}" >
                                                <c:if test="${questionName!=t.questionResponses.question.questionName}">
                                                    <br>
                                                    <h4>${t.questionResponses.question.questionName}</h4>
                                                </c:if>
                                                <h6>${t.questionResponses.questionOptionValue}</h6>
                                                <h6>${t.questionResponses.questionOptionAdditionalValue}</h6>

                                            </c:if> 
                                            <c:set var="questionName" value="${t.questionResponses.question.questionName}"/>
                                        </c:forEach>
                                        <br>

                                        <div class="col-md-6">
                                            <div class="panel panel-default" style="background-color: black;"  >
                                                <div class="panel-heading">
                                                    <h3 class="panel-title">Value Health and Development</h3>
                                                    <!--                                                    <ul class="panel-controls" style="margin-top: 2px;">
                                                                                                            <li><a href="#" class="panel-fullscreen"><span class="fa fa-expand"></span></a></li>                                      
                                                                                                        </ul>  -->
                                                </div>
                                                <!--<div class="panel-body" id="valueHealthChartId" >-->
                                                <div class="chart-holder" id="valueHealthChartId">
                                                    <!--<div>-->
                                                    <canvas id="valueHealthAndDevelopmentGraph" ></canvas>
                                                    <!--</div>-->
                                                </div>
                                                <!--</div>-->
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="panel panel-default" style="background-color: black;">
                                                <div class="panel-heading">
                                                    <h3 class="panel-title">Forced Value Shape</h3>
                                                    <!--                                                    <ul class="panel-controls" style="margin-top: 2px;">
                                                                                                            <li><a href="#" class="panel-fullscreen"><span class="fa fa-expand"></span></a></li>                                      
                                                                                                        </ul>  -->
                                                </div>
                                                <!--<div class="panel-body" id="forcedValueChartId">-->
                                                <div class="chart-holder " id="forcedValueChartId">
                                                    <div>
                                                        <canvas id="forcedValueShapeGraph"></canvas>
                                                    </div>
                                                </div>
                                                <!--</div>-->
                                            </div>
                                        </div>
                                        <div class="col-md-10">
                                            <div class="panel panel-default"  style="background-color: black;">
                                                <div class="panel-heading">
                                                    <h3 class="panel-title">Sources of Validation</h3>
                                                    <ul class="panel-controls" style="margin-top: 2px;">
                                                        <li><a href="#" class="panel-fullscreen"><span class="fa fa-expand"></span></a></li>                                      
                                                    </ul>  
                                                </div>
                                                <!--<div class="panel-body">-->
                                                <div class="chart-holder" id="sourcesofValidChartId">
                                                    <!--<div>-->
                                                    <canvas id="sourcesOfValidationGraph"></canvas>
                                                    <!--</div>-->
                                                </div>
                                                <!--</div>-->
                                            </div>
                                        </div>

                                    </div>
                                    <!-- Value Module Stop -->
                                    <%-------------------------------------------------------------------%>
                                    <!-- Cognition Module Start -->
                                    <br>
                                    <div class="panel-body">
                                        <c:set var="questionName" value=""/>
                                        <c:set var="value" value="0"/>
                                        <h2 style="text-align: left; color:Black;">COGNITION DIMENSION</h2>
                                        <c:forEach items="${tsDimentionData}" var="t" >
                                            <c:if test="${t.questionResponses.question.questionDimension.dimensionId==1 && (t.questionResponses.question.questionId==29 || t.questionResponses.question.questionId==30 || t.questionResponses.question.questionId==31 || t.questionResponses.question.questionId==32)}" >
                                                <c:if test="${questionName!=t.questionResponses.question.questionName}">
                                                    <br>
                                                    <h4>${t.questionResponses.question.questionName}</h4>
                                                </c:if>
                                                <h6>${t.questionResponses.questionOptionValue}</h6>
                                                <h6>${t.questionResponses.questionOptionAdditionalValue}</h6>
                                                <br>
                                            </c:if>
                                            <c:set var="questionName" value="${t.questionResponses.question.questionName}"/>
                                        </c:forEach>
                                        <c:forEach items="${questionWiseNDList}" var="cognitionDimensionId">
                                            <c:if test="${cognitionDimensionId.key==33}">
                                                <tr class="table_row">
                                                <br>
                                                <h4>${cognitionDimensionId.value['ques'].name}</h4>
                                                <h6>score: ${cognitionDimensionId.value['ques'].score}</h6> 
                                                <h6>Population Percentile: ${cognitionDimensionId.value['ques'].normalDistribution}</h6>   
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                        <c:forEach items="${tsDimentionData}" var="t" >
                                            <c:if test="${t.questionResponses.question.questionDimension.dimensionId==1 && (t.questionResponses.question.questionId==34 )}" >
                                                <c:if test="${questionName!=t.questionResponses.question.questionName}">
                                                    <br>
                                                    <h4>${t.questionResponses.question.questionName}</h4>
                                                </c:if>
                                                <h6>${t.questionResponses.questionOptionValue}</h6>
                                                <h6>${t.questionResponses.questionOptionAdditionalValue}</h6>
                                            </c:if>
                                            <c:set var="questionName" value="${t.questionResponses.question.questionName}"/>
                                        </c:forEach>
                                        <c:forEach items="${questionWiseNDList}" var="cognitionDimensionId">
                                            <c:if test="${cognitionDimensionId.key==35||cognitionDimensionId.key==36}">
                                                <tr class="table_row">
                                                <br>
                                                <h4>${cognitionDimensionId.value['ques'].name}</h4>
                                                <h6>score: ${cognitionDimensionId.value['ques'].score}</h6> 
                                                <h6>Population Percentile: ${cognitionDimensionId.value['ques'].normalDistribution}</h6>   
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                        <c:forEach items="${questionWiseNDList}" var="cognitionDimensionId">
                                            <c:if test="${cognitionDimensionId.key==41}">
                                                <tr class="table_row">
                                                <br>
                                                <h4>${cognitionDimensionId.value['ques'].name}</h4> 
                                                <h6>score: ${cognitionDimensionId.value['ques'].score}</h6> 
                                                <h6>Population Percentile: ${cognitionDimensionId.value['ques'].normalDistribution}</h6>   
                                                </tr>
                                            </c:if>
                                        </c:forEach>

                                        <br>

                                        <div class="col-md-6">
                                            <div class="panel panel-default" style="background-color: black;">
                                                <div class="panel-heading">
                                                    <h3 class="panel-title">Problem Solving Approach</h3>
                                                    <!--                                                    <ul class="panel-controls" style="margin-top: 2px;">
                                                                                                            <li><a href="#" class="panel-fullscreen"><span class="fa fa-expand"></span></a></li>                                      
                                                                                                        </ul>  -->
                                                </div>
                                                <!--<div class="panel-body" id="problemSolvingChartId">-->
                                                <div class="chart-holder" id="problemSolvingChartId">
                                                    <!--<div>-->
                                                    <canvas id="problemSolvingApproachGraph"></canvas>
                                                    <!--</div>-->
                                                </div>
                                                <!--</div>-->
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="panel panel-default" style="background-color: black;">
                                                <div class="panel-heading">
                                                    <h3 class="panel-title">Collaborative Approach</h3>
                                                    <!--                                                    <ul class="panel-controls" style="margin-top: 2px;">
                                                                                                            <li><a href="#" class="panel-fullscreen"><span class="fa fa-expand"></span></a></li>                                      
                                                                                                        </ul>  -->
                                                </div>
                                                <!--<div class="panel-body" id="collaborativeApproachchartId">-->
                                                <div class=" chart-holder" id="collaborativeApproachchartId">
                                                    <!--<div>-->
                                                    <canvas id="collaborativeGraph"></canvas>
                                                    <!--</div>-->
                                                </div>
                                                <!--</div>-->
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="panel panel-default" style="background-color: black;">
                                                <div class="panel-heading">
                                                    <h3 class="panel-title">Treat Response Approach</h3>
                                                    <!--                                                    <ul class="panel-controls" style="margin-top: 2px;">
                                                                                                            <li><a href="#" class="panel-fullscreen"><span class="fa fa-expand"></span></a></li>                                      
                                                                                                        </ul>  -->
                                                </div>
                                                <!--<div class="panel-body" id="treatResponseChartId">-->
                                                <div class="chart-holder"  id="treatResponseChartId">
                                                    <!--<div>-->
                                                    <canvas id="treatResponseApproachGraph"></canvas>
                                                    <!--</div>-->
                                                </div>
                                                <!--</div>-->
                                            </div>
                                        </div>
                                        <!-- Cognition Module Stop -->
                                    </div>
                                    <%---------------------------------------------------------------%>
                                    <!-- Extremism Module Start -->
                                    <div class="panel-body">
                                        <h2 style="text-align: left; color:Black;">EXTREMISM DIMENSION</h2>

                                        <div class="panel-default">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-md-9 scrollable">
                                                        <table class="table table-bordered">
                                                            <thead> 
                                                                <tr>
                                                                    <th style="font-weight:bold">Aspect Name</th>
                                                                    <th style="font-weight:bold">Average</th>
                                                                    <th style="font-weight:bold">Population Percentile</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr class="table_row">
                                                                    <td style="color: black ; font-size: 15px; font-weight:bold; "><label id="AgreeblenessLabelId">Agreeableness</label> <i class="fa fa-angle-down AggreeablenessId" ></i></td>
                                                                    <td><c:out value="${agreeablenessTraitData['trait'].currentMean}"/></td>
                                                                    <td><c:out value="${agreeablenessTraitData['trait'].normalDistribution}"/></td>
                                                                </tr>
                                                                <tr class=" option-content is-hidden table_row" id="AggreeablenessIdData">
                                                                    <td colspan="3">We all need to be able to get along with each other and have some mutual level of respect for one another. This is one of the defining advantages humans have over other species. Though other animals can hunt together, likely how cooperation started as humans), no other animal can coordinate complexly in as great of number as we can, all while accounting for the level of free will each of us possesses. We all have the potential to do extreme damage to each other at all times. When we are in public, we are surrounded by complete strangers who have no obligation to us whatsoever. We have laws, but it is often less reliable than we might think to catch people who have done harm to us. Agreeableness can be seen as the complex trait that governs this, even in the absence of laws. As a lesson from history, the ancient city of Rome exceeded 1 million people, yet despite the lack of modern policing technologies, the rates of crimes were plenty low to keep people from being turned off of living there. It is worth mentioning that some of this ability ties in with Conscientiousness, which can be seen as one’s orientation toward acting in service of the future. Nonetheless, how Agreeable we are is the main aspect that takes care of this getting along with others. It is important to note that it breaks down into Compassion and Politeness, which tend to be two different systems for dealing with the task of getting along with others. In what we consider to be normal, healthy adults, both systems tend to come into play, so we will elaborate on them more individually. Agreeableness helps us to see others as deserving of care and respect, and it helps us to maintain necessary levels of stability within a society by ensuring that no group is doing too poorly. It further allows us to extend the same level of positive treatment, previously reserved from the more immediate members of our tribe, to our fellow citizens, other members of the human race, and even beyond to animals. Additionally, Agreeableness serves as a system to make us more likely to respect authority, without necessarily needing to be afraid of the authority or be incentivized to do be respectful of authority for any other reason.
                                                                        <br>
                                                                        When we are high in Agreeableness, we view others as exceptionally deserving of our respect and care. We see this as an unconditional feature of them being our fellow man, or even fellow creature. We value and defer to those in positions of authority, even though we give up some of our autonomy in the process. We do what is expected of us by others, relative to our position to greater or lesser authority. This means we might perform certain duties (such as our spousal or parental ones) or act out these roles, even if they do not feel sufficiently genuinely motivating in and of themselves. We experience a level of satisfaction and happiness knowing that we did our part in the ways that are expected of us. Much of our pride and identity is built on being good romantic partners, good parents, good neighbors, good employees, good citizens, and ultimately good human beings. We take threats to such roles especially hard, as anything threatening how we see ourselves in those roles is a threat to our identities. We are more likely to respectfully uphold the status quo, as well as to respectfully criticize and push for what moderate amounts of change might be necessary. We do not fully understand how others can live with themselves being disrespectful and uncaring toward others. We are quick to criticize behaviors that fall outside the norms mentioned above, particularly if we are higher in Orderliness. We often fail to recognize that the successes and freedoms we enjoy (especially if we are members of Western nations), often are the result of a few specifically Disagreeable people throughout history. Sometimes we need Disagreeable people to shake things up when conditions become stale or overtly tyrannical and authoritarian. Agreeableness also leads us to accept the current conditions, even when things are not that good (higher Neuroticism plays a role here, too). We often do not decide to pick up and leave unfavorable conditions, and we are much less likely to consider some form of outright revolt when things are especially intolerable. We might even experience conflict as physically, mentally, and emotionally painful, which can lead us to shy away from it at all costs.
                                                                        <br>
                                                                        Sometimes, conditions are intolerable enough that we need those of us who are Disagreeable (low in Agreeableness) to shake things up and institute serious change. This is complex because of how Agreeableness breaks down, but it is nonetheless useful to understand. When we are low in Agreeableness, we are more willing to be confrontational, and we are much less willing to let anything slide (though Machiavellianism affects this). We lack the willingness of Agreeable people to let things go for the sake of some illusion of peace and harmony (as we often see it as an illusion/delusion). We value straightforwardness even at the cost of offending others and hurting their feelings. We are no stranger to stepping on a few toes and making a few enemies to get what we want. We generally see ourselves as tougher and rougher around the edges, less willing to be tamed and fully ‘civilized.’ Depending on our other scores, we may not only be unwilling to take other’s feelings and sense of reality into consideration, we may be outright unable to do so. We feel particularly useful when the going gets rough, which is why we can feel particularly out of place during times of peace. We even unknowingly and unintentionally stir things up, so we might feel enlivened by the thrill of some form of conflict. When we hurt other people’s feelings, we can be quick to convince ourselves that we do not care, and it is good for them since they need to toughen up. We are much worse around children, and anyone that has a lower ability than us, in any particular field that might be relevant. Though Volatility (an aspect we will discuss later) plays a more serious role in how quickly we anger, Agreeableness is responsible for how much patience we have to run out of before our ANGER emotional system is triggered (another trait you can find below). What others generally do not realize about us is that we are often as hard on ourselves as we are on them. Since we have grown used to this, we do not understand how others cannot handle it. Unfortunately, there are not too many places for us. We let people in less deep, and we often prefer to use common ‘tough guy’ methods of dealing with our feelings, in lieu of discussing them and being ‘open and vulnerable.’ We are much more likely to be men, but if we are women, we tend to suffer much more severely for it. We generally do not fit in well with women, and we do not tend to identify with conventional female gender roles. 
                                                                        <br> <br>
                                                                        <strong>Note:</strong> Due to where the mean is (the average of how all people score), even if you just responded neutral for all of the questions related to this, your results will show that you are quite Disagreeable. 
                                                                        <br><br>
                                                                        <strong>Disagreeableness role in Society:</strong>
                                                                        <br>
                                                                        Warrior/gardener quote
                                                                    </td>
                                                                </tr>
                                                                <tr class="table_row">
                                                                    <td ><span  id="CompassionLabelId">Compassion</span> <i style="font-size: 14px;" class="fa fa-angle-down CompassionId" ></i></td>
                                                                    <td><c:out value="${compassionData['aspect'].currentMean}"/></td>
                                                                    <td><c:out value="${compassionData['aspect'].normalDistribution}"/></td>
                                                                </tr>
                                                                <tr class=" option-content is-hidden table_row" id="CompassionId">
                                                                    <td colspan="3">Compassion is why it actually feels good to care for others, even in ways that have no foreseeable benefits to ourselves. As humans, we are quite different from all other animals, and our capacity for compassion is one of the key factors. As humans, we possess a relatively unique ability to see ourselves in others, and we actually use this as an expression. As older people, it is often one of our highest compliments to say we see a great deal of ourselves in another person, typically younger. Though we are capable of relating to others in a variety of ways, including thinking about them (a more intellectual approach), our most powerful way of relating to others comes from compassion. We intuitively understand that we are all similar and connected by our humanity, which leads us to be able to empathize with others and put ourselves in their shoes. When we experience jealousy of another person, we actively suppress our understanding of them through compassion because if we were using it; we would understand that they are people like us. They certainly experience their fair share of problems, pain, and sacrifice, which is generally how they got to wherever they are in the first place. When we see “successful” people through an purely cognitive or insecurity/inadequacy lens, we fail to empathize with all the sacrifices they likely have made and are making in order to have the life they have. We fail to understand that often there is a drive within them that forces them to be constantly working and to be uncomfortable with relaxing. We fail to appreciate how much we either intentionally or unintentionally have chosen to spend more time with friends and/or family. Though people who achieve higher levels of conventional success (fame, fortune, contribution to our species, etc.) feel a need to do so throughout their lives, they often experience a level of sadness and regret for not having spent more time with the people they care about. We all suffer and succeed in ways valuable to us, as unique individuals, 
                                                                        <br>
                                                                        Further, compassion is generally understood as the desire or pull to help when another is suffering. It is our willingness to sacrifice our time and or resources to come to the aid of another. It is the part of us that is responsible for how much we enjoy taking time for others. Similarly, it is responsible for how closely related people tend to need to be in order for us to feel for them and feel a desire to do things for them. It is also the part of us that leads us to being curious about the wellbeing of those around us. 
                                                                        <br>
                                                                        For those of us who are higher in compassion, we view people, animals, and sometimes even the more material elements of the universe itself as things to care for, to nurture, and to be there for in their times of need. We often feel better about helping others than we do when we help ourselves. As such, we often help others to the point that we are sacrificing too much of ourselves in an unsustainable manner. This is dangerous, because once we wear ourselves out, our compassion naturally drops, and we will see that people have not reciprocated (returned our kindness), which we normally do not expect from them. However, in such a state, it can lead us to become bitter and resentful, so we may end up being turned off from helping. We have to keep in mind that we need to do it in a way that is sustainable for ourselves. Being high in compassion, we also might want to take care of things on behalf of others, but we have to be careful that we are not infantilizing them (making them incapable of taking care of themselves) in the process. Most little things can trigger a compassionate response, so we are especially attracted to babies (humans and animals). Compassion in this capacity is what allows mothers as well as especially patient fathers to care for children despite how draining and annoying they would otherwise seem compassion and the CARE system helping us tolerate it their behavior (we will discuss care later). We often expect others to be more compassionate, and we have a harder time remembering and expressing gratitude for our circumstances, which allows us to care about being compassionate. We tend to quickly forget the sacrifices made by those who experienced a truly more difficult life than ourselves. This ends up irritating and angering people who are less compassionate because they had to be “tougher,” having had a harder existence. 
                                                                        <br><br>
                                                                        <Strong>Note:</Strong> We all need to acknowledge that although some aspects of life seem like they are “objectively” more difficult, having to hunt for our food for example, due to how our brains work, we experience roughly similar levels of pain and suffering. The further removed we are from our routine awareness of death, the more we feel comfortable looking to the future. This is a move from a more survivalistic life to a more existential one. As such, when our vision of our own future is threatened (by a bad grade or a social failing, for example) we experience it as severely as a more survival-oriented person experiencing a physical threat on their life. This is too complex to go into here, but this is both a good and bad thing, as most things are.
                                                                        <br>
                                                                        When we are lower in compassion, we often do not see the value/point of caring for others. If we are more conservative by nature, we are more resistant to seeing the point of caring for people beyond our more immediate tribal circle of family and friends. This is where we strike a necessary balance through conversation and compromise between more liberal people and more conservative people, though this is seemingly less common at the moment. We experience lower levels of compassion when we are not doing well ourselves. If we are lonely, depressed, under threat, or otherwise negatively affected, we feel little internal motivation to take care of others. Those of us who grew up having to fend for ourselves, often times in ways that are extreme, might have a lower level of compassion because we have a need to justify our previously harsh conditions. If we are attached to seeing it as solely a good thing that “toughened us up,” then we are much more likely to think others need to go through the same things. We need to understand that as human civilization progresses, life gets a bit easier; we move further away from the daily struggle against hunger, thirst, insecurity, and ultimately death. As a society, we evolve to be able to afford a greater level of sensitivity and care for more people. Our compassion grows in breadth and depth. Especially if we are older and we feel we have sacrificed to allow younger generations to have it easier, we feel that they do not understand our sacrifices and are ungrateful. Here we must understand that without living our lives, they cannot experience what it was like to go through the hardships we went through. We must take pause and consider “do we really want them to go through such things?” Most of us would say “no.” We can take comfort knowing that this has been a relationship repeated generation to generation as long as there has been any noticeable level of progress made (so it is somewhat new to the past 150ish years). Our children will end up in the same position as us eventually, saying how their children have it so much easier than they had it. In the end, we should be mindful that all we usually want is to be acknowledged and thanked for the hardships we endured and the efforts we made to allow the next generation to be better off to any degree. 
                                                                        <br><br>
                                                                        <Strong>Note:</Strong> Higher Compassion  is usually better, except for when it leads to one self-sacrificing beyond one’s means to do so, or when one is inclined to violate or compromise their ability to care for those that they have a more immediate responsibility toward, such as their family. To clarify, it is problematic when we do this to try to care for those whom we would have a less immediate responsibility toward. For example, it is wrong if you cannot feed your family because you gave all your money to charity, or as is becoming increasingly common and problematic, you gave too much money to some e-girl/Twitch streamer.
                                                                        <br><br>
                                                                        <Strong>Quick Note:</Strong> Compassion combined with Extraversion (especially Enthusiasm) tends to control how quickly and deeply we let people into our lives, as well as how quickly and deeply we desire to form a relationship with them. Neuroticism has a negative effect on this process.
                                                                        <br><br>
                                                                        <strong>Technical Defination:</strong> <i style="font-size: 14px;"  class="fa fa-angle-down CompassionTechnicalId" ></i>
                                                                        <br>
                                                            <h class="option-content is-hidden table_row" id="CompassionTechnicalId" colspan="3">
                                                                Compassion literally means “to suffer together.” Among emotion researchers, it is defined as the feeling that arises when you are confronted with another’s suffering and feel motivated to relieve that suffering.
                                                                <br>
                                                                Compassion is not the same as empathy or altruism, though the concepts are related. While empathy refers more generally to our ability to take the perspective of, and feel the emotions of, another person, compassion is when those feelings and thoughts include the desire to help. Altruism, in turn, is the kind, selfless behavior often prompted by feelings of compassion, though one can feel compassion without acting on it, and altruism isn’t always motivated by compassion.
                                                                <br>
                                                                While cynics may dismiss compassion as touchy-feely or irrational, scientists have started to map the biological basis of compassion, suggesting its deep evolutionary purpose. This research has shown that when we feel compassion, our heart rate slows down, we secrete the “bonding hormone” oxytocin, and regions of the brain linked to empathy, caregiving, and feelings of pleasure light up, which often results in our wanting to approach and care for other people.

                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td ><span  id="PolitenessLabelId">Politeness</span> <i style="font-size: 14px;" class="fa fa-angle-down PolitenessId" ></i></td>
                                                                <td><c:out value="${politenessData['aspect'].currentMean}"/></td>
                                                                <td><c:out value="${politenessData['aspect'].normalDistribution}"/></td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row table_row" id="PolitenessId">
                                                                <td colspan="3">
                                                                    Politeness in this sense, does not mean how much we mind our manners as it often means for people. The two are related, but do not necessarily need to coincide behaviorally. Politeness tends reflect our level of  respect for the social boundaries and norms of our family, culture, community, and nation, and it can be seen as our willingness to sacrifice otherwise selfish, undisciplined, or otherwise seemingly socially unacceptable ways of behaving in order to contribute to and uphold the general status quo. Though we could generally see acting from politeness as a less ideal aspect to come from, it is still very useful for society in general. When we are children, we are generally taught to observe the ways of being deemed socially acceptable. If acting from Compassion is how we show others we care and are worth being cared for, then Politeness is how we show others that we are capable of fitting in and giving up a level of freedom to behave however we want, to be considered part of the group. All the groups we belong to demonstrate different norms that we must observe to continue to be desired and considered as a member of the group. Even groups where much of the commonality is low Politeness and generally low Agreeableness, there are still often unspoken rules that need observing. If we violate them too much or for reasons the group deems unacceptable, then we are just as likely to find ourselves kicked out as we would be in a group that shares high Politeness and Agreeableness as a commonality. An expensive and exclusive country club, a book club, and homeowner’s association are examples of generally high Politeness environment; whereas, a particularly aggressive gym, a bar where fights are common, or a prison, would be good examples of lower Politeness environments. Generally, with Politeness, individuals tend to be expected to pick up on the rules, or they should have them explained to them by someone who is responsible for initiating them into the group. We make rules obvious and explicit under a few conditions. We make our rules explicit (known) when we take excessive pride in the nature of our rules (Very High Politeness), when there is some combination of too many people who are sufficiently different in their personalities (we need to make them known to all because we cannot trust that we will all intuitively/automatically pick up on them), and when the group/environment is unstably unruly and Disagreeable. In this last example, the rules are needed to keep out, or punish, the types of people that are so prone to fighting that their free inclusion would destabilize the group. 
                                                                    <br>
                                                                    Those of us higher in politeness tend to avoid imposing our will upon others, and we typically prefer to avoid conflict. We tend to observe the letter of the law instead of the spirit of the law (though Orderliness plays a role here too.) We see it as our responsibility to conform to the expectations of those around us, and we either do not, or we suppress our desires to, rock the boat. We prefer stability and a stable society, and we are less directly concerned with everything being as good as it could be. We derive value from acting from tradition and passing these traditions on to our kids. Somewhat depending on how long we have been high in Politeness, as well as other aspects of our results, we may lack much of an ability to consider options that would go against convention. We feel quite uncomfortable whenever we are asked to go against the expectations that we perceive others to have of us. We do what we can and must to avoid causing problems for others and society at large, although some would criticize us as being complacent. We rarely see ourselves as, or even are out for our personal gain, since we are generally thinking of the expectations of those around us to help guide our thinking, decisions, and actions. We also allow things to slide and let things go, which often leads to problems building up overtime. We might even have occasional outbursts over things that seem less serious than our reaction might suggest. This is because we have held our tongues and held back too many times such that we are finally at wits’ end. Similar to with Agreeableness, we need to remember and understand the value of Impolite people. We should remember that these people are our innovators, our rebels, and our mavericks. They push the envelope of what is possible and acceptable, generally in ways that benefit all of us in the end. In the United States, we owe our founding to our sufficiently Impolite Founding Fathers, who said enough is enough. They rebelled and built a country where we are free enough to think outside the box, yet we are stable enough not to do so to such a degree that we destroy everything we have built. 
                                                                    <br>
                                                                    Impoliteness, on the other hand, lends itself to our willingness to challenge the status quo, to say “No” when something does not work for us or is unacceptable. Impoliteness is our inner willingness to flip anyone or anything the middle finger and not budge, no matter how much the odds are stacked against us. Impoliteness is our love of a good fight, and it is our willingness to draw our own lines in the sand to fight and die to protect. It is rare, unless our souls have been eroded repeatedly and over a long time, that we all do not have some point where we say, “Enough is Enough!” Impoliteness defines how much we can be pushed before we fight back. Though those of us that are impolite can come off as Volatile and quick to ANGER (easy activation of our brain’s ANGER system), it is often that we see little things as worth fighting over. If someone crosses some line of ours, even if it will cause a disturbance or be awkward, we are quick to let them know. If they continue to do so, we are generally willing to resort to violence, as we would rather break the law than allow such a violation to go unpunished. Though other traits tend to mediate how this is carried out, we tend to be less tactful of how we go about dealing with things from Impoliteness. Depending on how tactful we choose to be, we are more prone to ruining relationships, deals, or even at times our reputation, when we feel we need to behave this way. Those of us who are extremely Low in Politeness lose sight of the value of politeness. Often seeing High Politeness people as being weak, cowardly, subservient, and spineless. We might be blind to how and when we act in a High Politeness way too, which leads to many other problems. Since we are required to hold our tongues and show restraint in certain circumstances, or risk isolating ourselves from everyone around us, we can deny that we are acting from Politeness, and we repress it such that we will experience inappropriate outbursts over otherwise insignificant things. We feel uncomfortable when we go too long without experiencing conflict, so we often start fights or even self-sabotage because deep down we hunger for a good conflict. We care much less about respecting people as a social convention, instead requiring that people demonstrate and prove to us why they deserve our respect. As a consequence, we sometimes do not grant people the level of initial respect that would be necessary, so they will have a chance to prove to us why they deserve our respect. We find ourselves outcast and rejected by people, groups, and society at large as a result of our need for conflict and our lack of respect for letting things go for the sake of peace, even if it is somewhat of a lie.   
                                                                    <br> <br>
                                                                    <strong>Technical Defination:</strong> <i style="font-size: 14px;"  class="fa fa-angle-down PolitenessTechnicalId" ></i>
                                                                    <br>
                                                            <h class="option-content is-hidden table_row table_row" id="PolitenessTechnicalId" colspan="3">
                                                                Politeness is one of the more recently studied aspects of Agreeableness, so we are still learning more deeply what it truly is. One could argue that Politeness is the respect for boundaries, often established and maintained by Orderliness. We can consider Politeness to be the system that regulates one’s relationship to boundaries, which are a more abstract concept than what compassion deals with. Compassion can be understood to regulate interpersonal relations, having little to do with socio-cultural norms or boundaries. While the expression and potential overt experience of feelings of compassion and empathy can be regulated by politeness, one still tends to experience the emotions and feelings associated with it. Politeness is associated with being conservative, which itself is highly correlated with being politically conservative, but does not necessarily have to be. It tends to mean that you like predictability and stability.   
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td style="color: black ; font-size: 15px; font-weight:bold; "><label id="ConscientiousnessLabelId">Conscientiousness</label> <i class="fa fa-angle-down ConscientiousnessId" ></i></td>
                                                                <td><c:out value="${conscientiousnessTraitData['trait'].currentMean}"/></td>
                                                                <td><c:out value="${conscientiousnessTraitData['trait'].normalDistribution}"/></td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row table_row" id="ConscientiousnessId">
                                                                <td colspan="3">
                                                                    Our future is uncertain. That there will even be a future, or a tomorrow, is itself uncertain. We cannot be certain enough that tomorrow will come, no matter how much evidence there is that the world will not end tomorrow, or even ten years from now, but at some level, we take the idea of a future on faith. Without our ability to sacrifice for the future, we as a species would never have been able to reach such highs of complexity and thriving. When we live as though there is only the present, we extract as much value as we can from everything around us. We exploit our environment and those who inhabit it. This can be seen in how we lived as our most primitive ancestors. Though much the result of a bit of Conscientiousness, the continued stabilization of the world around us granted us the ability to become more Conscientious. This feedback loop has further served to increase the value of Conscientiousness over time. Our willingness to sacrifice and restrain ourselves for the sake of future security has not always been deeply ingrained. Even now, we can quickly revert back to “getting while the getting is good,” particularly when we perceive there being a threat to the security of our futures. In times of crisis, we rush to the stores to clear them out of supplies, exploiting a common pool of resources to ensure the safety and security of the future of those we have a direct responsibility to, and our own. We ourselves are at near our worst in this regard on Black Friday, as people behave like animals to get what they want at bit of a discount. Conscientiousness seems to be a more fragile system in our minds, as it is often the result of conditioning and discipline, and it certainly requires such training to score in the highest levels of it. Conscientiousness primarily breaks down into Industriousness (think work ethic) and Orderliness (think how organized we are). The way we invest in the future can be seen to break down in such a way. As humans, we possess a great deal of freedom in how we spend our time. We should find it interesting that we even think of it in terms of “spending our time,” “wasting our time,” and “investing our time.” We must have some understanding that we have a finite amount of time and that we see there being better and worse ways to spend it. What we are often unaware of is how much we change our minds over time as to what things are most valuable for us to spend our time doing. Where we score high or low in this, we tend to view the opposite as not understanding something fundamental, and we are quick to criticize each other as wasting time.
                                                                    <br>
                                                                    As we score higher in Conscientiousness, we derive more value and satisfaction from devoting our time to things that serve not only the futures of ourselves, those we have a responsibility to, and the species in general, but also to the very idea of the future. In part, this is why we are more likely to believe in God/a Higher Power, in some form or another, and we are particularly more likely to act in service of it. To the degree that we are particularly religious, the way we live our lives comes to reflect the nature of what a good member of our religion might strive to live as. We act in service and devotion to this higher power, while finding such a way of being as especially meaningful. If we are especially high in Openness, we are less likely to act in ways that are quite so conventionally religious. We will explore for ourselves what it means to live a “good life.” Further, we content ourselves with sacrificing our chances at more of the fleeting worldly pleasures or otherwise seemingly frivolous endeavors (pointless and impractical). Instead, we work to invest our time to build up a level of security and stability for our families and, to whatever degree possible, our future generations to come. Scoring highly can mean we willfully sacrifice our opportunities for more leisurely activities, such as fun with friends, to continue to work, build, and contribute. For this reason, those of us who end up with self-made fortunes tend rarely, if ever, to retire. The same way of being that got us to such a level of wealth is hard to put down once there is no longer any need per se to continue to work. We tend not to work simply because we feel that we have to in order to provide and survive, but instead, we work because there is something inside us that compels us to do so. Later in life, as almost all of us do, we come to value time with family and friends, really living life so to speak, more than working. To the degree we feel we have sacrificed time we could have spent with them and missed out, we often experience regret. However, we absolutely must understand that this is due to us moving into the next level of human development later in life, and who we were all along was someone who genuinely lacked the ability to experience the value of such things that we now see as more important. We must look back with pride at how we took care of, provided for, and ultimately set up our families, our children, and even the world so they all might be able to experience a bit easier life. We can feel good that having raised our children right to the best of our abilities, they will go on to do the same for their families as well.
                                                                    <br>
                                                                    The lower we score Conscientiousness the less we care about spending/investing our time in the things that the rest of society tends to tell us we should. To the degree that we are not so low in Conscientiousness that we lack the basic ability to meet our survival and security needs, we experience less stress. We are considered and consider ourselves laid back. We are prone to seeing ourselves as choosing not to play “the game,” or be influenced by “the man,” “the system,” or whatever other phrase exists to represent the way of life that the majority of the rest of society lives. We do not see the point, and we seek to do what we can to keep from being tied down. We try to “live in the moment,” but we often fail to understand what this really means, as it is not the same thing as the spiritual idea of “being present.” We act more from a desire to do as we please (for the most part) so we are free to be spontaneous, rebellious, and carefree. We shirk responsibility when we can, often feeling fine to let others pick up the slack. Interestingly, we tend to fit into two camps; the first of which is those of us who seek a relaxing and easy lifestyle without really taking advantage of others to do it. The other group is those of us who are more anti-social (think sociopathic/psychopathic), such that we seek to take advantage of others’ hard work and kindness for personal gain or at least so we do not need to work hard ourselves. The first camp is more associated with surfers, hippies, and those that manage to live an alternative lifestyle, generally being mindful of harming others. The second describes those who take advantage of others, lie, cheat, steal, are gang members (though this is more work than most of us realize), etc. When we are in the second camp, we rarely can hold down a job for long, as our exploitation of it and those around us eventually becomes apparent. This will be discussed more in the Dark Factor section. Our failings tend to come in the form of our criticisms of the way of life/the process that gave rise to the world we live in, which is secure enough and easy enough to allow for the possibility of an unconventional lifestyle. We are quick to level judgement while we use the tools and products that the most Conscientious people created. We are easy to think how we would do it better, all without having truly attempted anything of difficulty in the “real world,” so we might find out just how hard it is to do or change anything, let alone create things. We must see ourselves as benefiting from such people on the opposite end of the spectrum, and we might try our hand at benefiting them when they are older and weary from a life of hard work. We can introduce them to the things we value such as the wonder of a nomadic lifestyle, a focus on experiences over material possessions, and whatever else our lives have shown us to be of value that they are missing out on experiencing.
                                                                    <br>
                                                                    We either sacrifice the future, sacrifice for the future, or sacrifice ourselves, so we may fully revel in our experience of the present. There is no getting around sacrifice.  
                                                                </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td ><span  id="IndustriousnessLabelId">Industriousness</span> <i style="font-size: 14px;" class="fa fa-angle-down IndustriousnessId" ></i></td>
                                                                <td><c:out value="${industriousnessData['aspect'].currentMean}"/></td>
                                                                <td><c:out value="${industriousnessData['aspect'].normalDistribution}"/></td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row table_row" id="IndustriousnessId">
                                                                <td colspan="3">
                                                                    Though it may be a difficult idea to accept, we as modern humans are at least as hardworking as any of our ancestors of the past. We might see this as unlikely, but it has to do with how efficient different aspects of us are. Over time, most of us traded our physical labor jobs for mental labor or at least some combination of physical and mental. Oftentimes, where we work in such a combination, the demands and consequences of making a mistake mentally are extreme. For example, as teachers, doctors, nurses, and construction workers, we need to be on our game, since a failure to do so likely results in, at best, our being fired and at worst, others or ourselves being injured or dying. Industriousness can be considered a collection of characteristics, such as our tendency to follow through on the things we say we are going to do, as well as our ability to focus, especially for extended durations of time. Industriousness is further responsible for how much we enjoy/need work and the pursuit of things that can be considered productive. To more deeply understand it, it is useful to think of groups of humans, especially nations, and at this point, even our species, as one extended animal (macro-organism). Unlike with the cells in our bodies, each of us is more, and capable of being much more, than our jobs. Regardless of whether we exist as self-sufficient people, like primitive humans as well as settlers of the American frontier, we have always had a need to be productive. The main difference, over time, has become how specialized we are able to be without compromising our ability to support ourselves and our families. Therefore, we all need to be able to experience being ‘productive’ as not just motivating but also fulfilling. Most of the other traits and aspects influence in some way how productive we are driven to be, as well as how we experience our productivity. We have always had a difficult time getting away with not being productive, as others generally do not take kindly to freeloading. The advantage of the increasing complexity of humans over time can be seen in part as an increase in efficiency/leverage. One farmer today can produce almost unimaginably more than farmers without the advantages of modern technology. This has allowed us to allocate our labor force such that the work of one individual, beyond allowing oneself to survive, benefits an extraordinary amount of people. We tend to be rewarded financially on par with something like how much we benefit others times (X/multiplied by) how many people we benefit. We can see this as Net Contribution Value (NCV). If we wanted to get really specific, we could further see that number as divided by something like our inefficiency (the cost and number of workers required) times the level of skill (and therefore compensation) required to deliver the value. Let’s call this Net Contribution Cost (NCC). So, we end up with (NCV)/(NCC). Though we could add more resolution, this is a quick and easy way to think about how much we will be financially compensated for our contribution. We need to remember that value is determined by the market, so some activities that seem to be parasitic or negative can be valued, even if they are a cost to us in general.
                                                                    <br>
                                                                    Higher Industriousness tends to be a bit difficult to wrap our heads around. We often have difficulty understanding how much a bit more Industriousness adds up over time, but this is because we fail to consider the additional positive effects of it beyond the obvious. The more Industrious of us not only earn a bit more by working a few more hours, but by doing so, we are much more likely to get pay raises and be promoted over time. By further developing our skills through putting in more hours, we become more efficient and effective, so we produce as much as someone lower in industriousness would in much less time, even though we continue to work more than them. When we look at why some people earn far more than others, we can see that the payoff is rarely 1:1 for how many hours we work. We see that working 80+ hours a week over time results in an income that is many times higher. Those of us who are very high in industriousness tend to feel uncomfortable if we are not keeping busy. It also governs the degree to which we are likely to procrastinate, and it is less dependent upon how engaging we find the task at hand. Finally, it tends to govern decisiveness, which is also related to procrastination. We are able to focus much more intently for much longer periods of time, all while making fewer mistakes. By virtue of just putting in more time, we get better at the tasks, but if we are more intelligent, then the effect can be significantly increased. We accomplish things in a timely manner, usually striking an effective balance between quality and turn around time. Though other things play a role here, we quickly make decisions, and we tend to make higher quality decisions.
                                                                    <br>
                                                                    When we are lower in industriousness, we experience a level of resistance to working. We are more selective in the tasks we are readily willing to do. We often need more motivation and/or a greater understand of why it is necessary for us to do something, especially if it is not something that we like doing. Our self-worth is less tied to our ability to achieve; however, since this is an innate part of human psychology, we tend to experience negative feelings about our lack of productivity and achievement. We are generally lacking in our ability to care that we need to work hard to not only ensure that we have a good future, but so we actually even have a future. We will need constant motivation and supervision to ensure we do the things that are required of us, and we may need reminded multiple times along the way. We are likely to blow off our “to-do list” and other such obligations to spend time on other distractions.  Often this is videogames, TV, social interaction, recreational drug use, philosophizing, or any other activity that is more appealing than the tasks at hand. We are likely to get distracted by our social interactions and frequent many different social gatherings (if we have high Extraversion). We are likely to be very sociable as a way to distract ourselves from our work or our goals. We are likely to place other’s needs before our own to distract ourselves from having to work on our own goals. If others do not do what they said they were going to do for us, we probably will not be very concerned about it because we allow ourselves to not accomplish our own goals or do what we say we will. We have a habit of making excuses as well, which over time makes it increasingly difficult to do things. Since about half of Industriousness is not genetic, we can seek out “accountability buddies” to help us in keeping on track with our goals. We can and do typically tend to improve on this over time, and we can train ourselves to be more productive.
                                                                    <br><br>
                                                                    <strong>Technical Description:</strong> <i style="font-size: 14px;" class="fa fa-angle-down IndustriousnessTechnicalId" ></i>
                                                                    <br>
                                                            <h class="option-content is-hidden table_row table_row" id="IndustriousnessTechnicalId" colspan="3">
                                                                Industriousness is also a relatively new psychological facet, so the research on it is relatively sparse. It tends to govern the degree to which you desire and derive reward from fulfilling your perceived role in the sets of domains within which you understand yourself to operate. That is to say, if you consider yourself a parent, a member of your local community, an employee of your corporation, and a citizen of your state, then industriousness can govern the degree to which you derive fulfillment and meaning from your purposeful acting in such roles.    
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td ><span  id="OrderlinessLabelId">Orderliness</span> <i style="font-size: 14px;" class="fa fa-angle-down OrderlinessId" ></i></td>
                                                                <td><c:out value="${orderlinessData['aspect'].currentMean}"/></td>
                                                                <td><c:out value="${orderlinessData['aspect'].normalDistribution}"/></td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row table_row" id="OrderlinessId">
                                                                <td colspan="3">
                                                                    Orderliness plays a primary role in governing how we relate to chaos/the unknown, anything that is foreign (especially germs and dirt), as well as our need for neatness and organization. For this reason, it can constrain creativity, as the production of something novel tends to require an exploration into chaos, discomfort, and the unknown. We can understand many of the political contentions that exist in society today, as well as historically, as disputes over orderliness. For better or worse, differences in this trait can produce many of the most contentious misunderstandings between us, since it so heavily influences our realities. Most of us do not see there as being a distinction between perception and what is real, and without a neuroscience/philosophy degree, why should we? Our world seems to behave fairly predictably, that is, until it doesn’t… When we run into others who see things very differently from ourselves, our minds are quick to highlight how that person is not one of us. Though we are equally quick, in that moment, to forget about how different we are from the other members of whatever group(s) we feel we are a part of in that moment. As humans, we do not actively engage with sufficiently complex beings that are different from us. We might engage with pets, but this is not significant enough to help us constantly be aware of how similar we all are, relative to everything else. This is likely because our interpersonal differences as humans are one of, if not the primary, obstacle to navigate on a regular basis. To get back more specifically to Orderliness, Orderliness not only affects who we see as part of what groups, but even the types of things we might include in drawing such distinctions. To clarify, some of us feel only humans, especially humans very similar to us, are deserving of respect, dignity, and the other rights we enjoy. The higher the Orderliness, the less we are able to consider other parameters, beyond the ones we embody. The lower the Orderliness, the more we can freely extend this to different skin colors, cultures, religions, and general ways of being. We can even extend this, not stopping at humans, to animals, plants, and the universe itself. However, we must keep in mind that this is not exclusively controlled by Orderliness, but our Openness and Spirituality scores also play a significant role. To experience that appropriately without losing our ability to function as humans is a precarious line to walk. Orderliness along with Neuroticism, and a few others, play a role in how much we need certainty to feel secure enough to function normally. Along with Politeness, it further governs how much we value stability versus change and fluidity. When we ask why we do not have more than two prominent political parties in the U.S., we can understand that the most significant difference between us is whether we are the type of people who value stability versus the type who value change. We should all strive to value both and to acknowledge the role that people who lean the opposite direction play.  Once we evolve past Stage 6 of human development, we begin to understand the complex need for both in just the right ways at just the right times.
                                                                    <br>
                                                                    For those of us that are high in Orderliness, we are disturbed by mess and chaos. As such, we keep everything tidy, neat, and organized. Since Orderliness plays a role in how we experience our realities, we tend to think in terms of black and white with very few grey areas. We strongly prefer things to have their place, and we need things to always be where they belong. Valuing structure and predictability, we create schedules and follow them, and we deviate infrequently from our routines, if we can help it. We are detail oriented, often to the point of being unable to see the forest through the trees. It is easy for us to focus so much on the individual details that we do not accomplish what we need to (if not properly balanced by Industriousness). This can lead us to being inefficient and being perfectionists, even with tasks we do not really care about as much as others. We desire everyone to be on the same page, so we value rules being explicit. As such, we are rule-abiding and insist that rules be observed by others. Few things are more stressful to us than unpredictability, which is related to our hesitation to change things that are working relatively well. We tend to be more adept at making sure that complex sensitive processes are managed, since we are more likely to monitor them meticulously.   
                                                                    <br> <br>
                                                                    <strong>Technical Description:</strong> <i style="font-size: 14px;" class="fa fa-angle-down OrderlinessTechnicalId" ></i>
                                                                    <br>
                                                            <h class="option-content is-hidden table_row table_row" id="OrderlinessTechnicalId" colspan="3">
                                                                Orderliness can be understood as largely serving the role of governing one’s conceptualization of boundaries. It plays a significant role in defining how well defined, and how rigid/subject to change one’s boundaries are. As such, at the extremes it can lead to OCD and dogmatic standards regarding how a person prefers, or even needs for, a task to be executed. While politeness tends to govern the upholding of social and interpersonal boundaries, Orderliness sets the boundaries, along with the politeness, while further defining all other sorts of boundaries and standards. 
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td style="color: black ; font-size: 15px; font-weight:bold; "><label id="ExtroversionLabelId">Extroversion</label> <i class="fa fa-angle-down ExtroversionId" ></i></td>
                                                                <td><c:out value="${extroversionData['trait'].currentMean}"/></td>
                                                                <td><c:out value="${extroversionData['trait'].normalDistribution}"/></td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="ExtroversionId">
                                                                <td colspan="3">
                                                                    Extraversion tends to be how much you enjoy/need to be around other people to feel happiness and fulfillment. There is a strong correlation between the amount of happiness people are likely to experience and their level of extraversion. The more extraverted a person is, the more likely they are to be talkative, energetic, assertive, and generally personable. More extraverted people are oftentimes more ambitious than their more introverted counterparts. Introversion tends to be where individuals are less energetic around others, and they are often more stimulated by pursing things by themselves. There tends to be a correlation between people who think of themselves as introverts and people who are more idea/thought driven. However, our analysis suggests that these are relatively independent, and should be viewed as such.
                                                                    <br>
                                                                    There are quite a few people who think of themselves as introverts when then are actually highly selective extraverts. This means that they will only truly ‘come alive’ conversationally with select people when they feel there is a strong connection.
                                                                    <br><br>
                                                                    <strong>Technical Defination:</strong> <i style="font-size: 14px;"  class="fa fa-angle-down ExtroversionTechnicalId" ></i>
                                                                    <br>
                                                            <h class="option-content is-hidden table_row" id="ExtroversionTechnicalId" colspan="3">
                                                                Extraversion (also spelled as extroversion) is the state of primarily obtaining gratification from outside oneself. However, if a person scores highly on openness, particularly on the aspect of Intellect, the individual is likely to obtain gratification from more internal and cognitive means as well. Extraverts tend to enjoy human interactions and to be enthusiastic, talkative, assertive, and gregarious. Extraverts are energized and thrive off of being around other people. They take pleasure in activities that involve large social gatherings, such as parties, community activities, public demonstrations, and business or political groups. They also tend to work well in groups. An extraverted person is likely to enjoy time spent with people and find less reward in time spent alone. They tend to be energized when around other people, and they are more prone to boredom when they are by themselves.
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td ><span  id="EnthusiasmLabelId">Enthusiasm</span> <i style="font-size: 14px;" class="fa fa-angle-down EnthusiasmId" ></i></td>
                                                                <td><c:out value="${enthusiasmData['aspect'].currentMean}"/></td>
                                                                <td><c:out value="${enthusiasmData['aspect'].normalDistribution}"/></td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="EnthusiasmId">
                                                                <td colspan="3">
                                                                    Enthusiasm governs one’s overall level of energy and excitement that is brought into a wide variety of situations. It is also the more significant aspect of Extraversion governing how much one tends to need/desire to interact with others. One of the more telling signs of it are the number of words per minute a person speaks, especially when they are excited. Enthusiasm also governs how quickly one warms up to others in interpersonal contexts. It further regulates the need for stimulation, activity, and fun that one needs to have. It can govern how fun a person is; however, play is a better predictor of such things.
                                                                    <br> <br>
                                                                    <strong>Technical Description:</strong> <i style="font-size: 14px;" class="fa fa-angle-down EnthusiasmTechnicalId" ></i>
                                                                    <br>
                                                            <h class="option-content is-hidden table_row" id="EnthusiasmTechnicalId" colspan="3">
                                                                Although being higher in enthusiasm tends to lead to a greater level of enjoyment and fulfillment in one’s life, as the depth and breadth of richness in any moment is more fully realized, it can lead to people being caught up in the excitement and making poor choices upon further reflection after the fact. Enthusiasm largely governs easily encouraged a person is, particularly when the individual might desire to take the potentially inadvisable action. This directly relates to their susceptibility to peer pressure. The desire for the outgoing and excited appreciation of others can further lead such individuals to taking actions that they might not otherwise take on their own.
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td ><span  id="AssertivenessLabelId">Assertiveness</span> <i style="font-size: 14px;" class="fa fa-angle-down AssertivenessId" ></i></td>
                                                                <td><c:out value="${assertivenessData['aspect'].currentMean}"/></td>
                                                                <td><c:out value="${assertivenessData['aspect'].normalDistribution}"/></td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="AssertivenessId">
                                                                <td colspan="3">
                                                                    Assertiveness is responsible for how much a person tends to take charge when the opportunity arises, and it is further related to how effective one is at influencing others. It further governs boldness and one’s desire or need to feel as though one is ‘ahead’ of others. This can be both positive and negative, as it is necessary to have various leaders in various contexts. Those who are less assertive often view those who are more assertive with an air of suspicion, often questioning “what makes them so fit to lead?” Often the answer is merely the fact that they desire it more and are less inhibited in the execution of their desire to go after it. They are not necessarily more intelligent or more qualified, as such things can actually have inhibiting effect on one’s pursuit of a leadership role. Assertiveness seems to also correlate well with the level of testosterone in men and women, and for that reason, it seems to also correlate to some degree with sex drive. Those who score higher in assertiveness tend to be discontent with having any subordinate status, as well as being treated in a way that might be received as such.
                                                                    <br><br>
                                                                    <strong>Technical Description:</strong> <i style="font-size: 14px;" class="fa fa-angle-down AssertivenessTechnicalId" ></i>
                                                                    <br>
                                                            <h class="option-content is-hidden table_row" id="AssertivenessTechnicalId" colspan="3">
                                                                Assertiveness plays a primary role in voluntary risk-taking behavior, and the disinhibition of an individual toward taking action. This is primarily for the sake of getting ahead and attainment. Assertiveness relates heavily to competitive behavior and the desire to attain and maintain status. The behavioral output is related to the perceived domain(s) of competition of the individual. This can render the overall/societal effects of individual competition either positive or negative, as well as drive the individual toward greater levels of personal success in either societally approved or prohibited manners. That is to say, the same function and system responsible for someone desiring to become the leader of a gang is at play when a similar individual desires to attain the status of CEO.
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td style="color: black ; font-size: 15px; font-weight:bold; "><label id="NeuroticismLabelId">Neuroticism</label> <i class="fa fa-angle-down NeuroticismId" ></i></td>
                                                                <td><c:out value="${neuroticismData['trait'].currentMean}"/></td>
                                                                <td><c:out value="${neuroticismData['trait'].normalDistribution}"/></td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="NeuroticismId">
                                                                <td colspan="3">
                                                                    Neuroticism regulates our emotional experience, particularly our experience of conventionally negative emotions. Since we tend to only perceive ourselves as experiencing one emotional experience at a time, higher neuroticism will increase the amount of negative emotions experienced, often blocking out our ability to experience more positive emotional experiences. Higher neuroticism also leads to us dwelling more on the experience of the emotions, and it will further reduce our ability to find out why we are feeling what we are feeling. When we score high on neuroticism, we are more likely than average to be moody and to experience negative feelings such as anxiety, worry, fear, anger, frustration, envy, jealousy, guilt, depressed mood, and loneliness. If we are more neurotic, it tends to mean that we have a harder time responding to threats than less neurotic people. We are also more likely to perceive some things to be threats or problems that others might not see anything wrong with. If we are less neurotic, then it usually means that we often do not respond to things that others might see as problems or threats. This can be a blessing and a curse as most things are, as it means that we can address such things with greater impartiality, but we are less likely to see them as something that needs addressing in the first place.
                                                                    <br><br>
                                                                    <strong>Technical Description: </strong><i style="font-size: 14px;"  class="fa fa-angle-down NeuroticismTechnicalId" ></i>
                                                                    <br>
                                                            <h class="option-content is-hidden table_row" id="NeuroticismTechnicalId" colspan="3">
                                                                Neuroticism, as a fundamental trait of general personality, refers to an enduring tendency or disposition to experience negative emotional states. Individuals who score high on neuroticism are more likely than the average person to experience such feelings as anxiety, anger, guilt, and depression. They respond poorly to environmental stress, are likely to interpret ordinary situations as threatening, and can experience minor frustrations as hopelessly overwhelming. They are often self-conscious and shy, and they may have trouble controlling urges and impulses when feeling upset.
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td ><span  id="WithdrawalLabelId">Withdrawal</span> <i style="font-size: 14px;" class="fa fa-angle-down WithdrawalId" ></i></td>
                                                                <td><c:out value="${withdrawalData['aspect'].currentMean}"/></td>
                                                                <td><c:out value="${withdrawalData['aspect'].normalDistribution}"/></td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="WithdrawalId">
                                                                <td colspan="3">
                                                                    Withdrawal is our primary personality aspect that governs the level and likelihood of anxiety (fear) and sadness we experience on a regular basis, as well as under conditions that might trigger such a response. It further dictates how we will respond to a potentially threatening or problematic situation. The higher the score, the more fearful and discouraged we will be at the prospect of facing the challenge that lies ahead. Higher scores also mean that we will feel worse and more negatively internalize the experience of such challenges or threats afterward. This can lead to even greater levels of anxiety in the future for new problems we are faced with. On the other hand, lower scores indicate that someone experiences obstacles more as challenges and opportunities rather than threats. Those of us that score low in this are less inhibited to try new things, to explore and experiment, as well as to be willing to risk failure. When we are low in this, we recover more quickly from anything that goes poorly and may be able to ultimately internalize it as a positive thing.
                                                                    <br>
                                                                    <strong>Technical Description: </strong><i style="font-size: 14px;" class="fa fa-angle-down WithdrawalTechnicalId" ></i>
                                                                    <br>
                                                            <h class="option-content is-hidden table_row" id="WithdrawalTechnicalId" colspan="3">
                                                                Withdrawal as a personality aspect consists of the behavioral and linguistic convergence of the Fear and Sadness (Panic) emotional systems.  Further understanding of how one’s Withdrawal can be characterized should be assessed via such emotional subsystems.
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td ><span  id="VolatilityLabelId">Volatility</span> <i style="font-size: 14px;" class="fa fa-angle-down VolatilityId" ></i></td>
                                                                <td><c:out value="${volatilityData['aspect'].currentMean}"/></td>
                                                                <td><c:out value="${volatilityData['aspect'].normalDistribution}"/></td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="VolatilityId">
                                                                <td colspan="3">
                                                                    Volatility is responsible for how our moods change. If we are higher in volatility, we tend to more easily and quickly switch our moods/how we are feeling. If we are particularly high in this, we seem to be all over the place with how we feel. We might even quickly shift between generally positive and negative emotions. Volatility also tends to describe how good we are at regulating our emotions. When we are quite low in it, it tends to mean that we can quite easily control how we feel. Further, it means that we are also generally more consistent in the emotions we experience. If you are lower in it, it tends to mean that you are also more difficult to disturb you from how you presently feel. So, if you normally feel quite at peace, then it is more difficult to disturb you from that peace.
                                                                    <br><br>
                                                                    <strong>Technical Description: </strong><i style="font-size: 14px;" class="fa fa-angle-down VolatilityTechnicalId" ></i>
                                                                    <br>
                                                            <h class="option-content is-hidden table_row" id="VolatilityTechnicalId" colspan="3">
                                                                Volatility can generally be understood as the degree to which an individual demonstrates the ability to regulate their emotional state, as well as the general consistency of their given emotional states. This is not to say that if a person is sad, then they are going to be sad for a longer period, if they are low in Volatility; rather, it means that such a person is not going to be likely to move from happy to sad back to happy, or to experience any other less directly related emotions to their current experience. More volatile individuals may bounce back and forth among a number of different emotional states or moods in a short period of time.
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td style="color: black ; font-size: 15px; font-weight:bold; "><label id="OpennessToExpLabelId">Openness To Experience</label> <i class="fa fa-angle-down OpennessToExpId" ></i></td>
                                                                <td><c:out value="${opennesstoExperienceData['trait'].currentMean}"/></td>
                                                                <td><c:out value="${opennesstoExperienceData['trait'].normalDistribution}"/></td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="OpennessToExpId">
                                                                <td colspan="3">
                                                                    Openness dictates how much we are driven and attracted to such things as beauty, creativity, intellectuality, and change. Openness tends to control how easily a person can change, as well as how intrinsically rewarded a person is by the experience of changing. This can be both external, being driven by change in the world itself, as well as internal, being driven by change within ourselves. Unfortunately, change within ourselves tends to be much slower, if it progresses at all, as we tend to surround ourselves with people who are similar to us, while being resistant to crossing the barriers with each other that would push us to be better in general. This is true even in people that are extremely high in Openness. If we are higher in openness, then we will have a more profound experience of most, if not all, forms of beauty, such as nature, poetry, art, music, videogames, movies, etc.
                                                                    <br><br>
                                                                    <strong>Technical Description: </strong><i style="font-size: 14px;" class="fa fa-angle-down OpennessToExpTechnicalId" ></i>
                                                                    <br>
                                                            <h class="option-content is-hidden table_row" id="OpennessToExpTechnicalId" colspan="3">
                                                                Openness is seen in the breadth, depth, and permeability of consciousness, and in the recurrent need to enlarge and examine experience. Seeing Openness thusly, renders it fundamentally an intrapsychic variable, associated with such esoteric phenomena as chills in response to sudden beauty, the experience of déjà vu, and homesickness for the unknown. Highly open people are thus seen as imaginative, sensitive to art and beauty, emotionally differentiated, behaviorally flexible, intellectually curious, and liberal in values. Closed people are down-to-earth, uninterested in art, shallow in affect, set in their ways, lacking curiosity, and traditional in values. Though most psychologists consider openness to be a good thing, among the general public there is a strong correlation between their social desirability ratings of Openness and their own self-reports; open people admire openness, closed people despise it.
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td ><span  id="IntellectLabelId">Intellect</span> <i style="font-size: 14px;" class="fa fa-angle-down IntellectId" ></i></td>
                                                                <td><c:out value="${intellectData['aspect'].currentMean}"/></td>
                                                                <td><c:out value="${intellectData['aspect'].normalDistribution}"/></td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="IntellectId">
                                                                <td colspan="3">
                                                                    Intellect is the trait most directly related to our intellectual abilities/prowess (IQ). Intellect is responsible for how quickly and easily we understand things, as well as how interested in ideas we are. It can be a bit of a self-fulfilling trait. This is to say that if it comes easier to us to think and understand things, we tend to find more joy in it, thus it reinforces itself. Intellect also dictates how curious we are about ideas, concepts, and things that are generally considered more philosophical/less practical. There are often disputes among people regarding the usefulness of this. If we are relatively high in this, but not near the maximum, we often use thinking as a means of trying to escape or get around taking the necessary actions. This can be problematic because when we do not do the things we need to do, we often cannot get to the higher place/perspective necessary to continue to think better. Those of us at the highest end of this trait can struggle with doing things that are mundane and practical, even though such an extreme level of intellect tends to be what is necessary to truly innovate. We are likely to disregard those who score lower, but without them, the more seemingly mundane things would not get done, and we would not have the fortunate positions we have to spend so much time thinking. When we are lower in intellect, we often dislike people that are at much higher levels of intellect. 
                                                                    <br><br>
                                                                    <strong>Technical Description:</strong> <i style="font-size: 14px;" class="fa fa-angle-down IntellectTechnicalId" ></i>
                                                                    <br>
                                                            <h class="option-content is-hidden table_row" id="IntellectTechnicalId" colspan="3">
                                                                Intellect as a spectrum tends to encompass the degree to which one is considered intelligent, analytical, and perceptive. As stated above, of the traits, it is most closely related to general intelligence (IQ). It primarily correlates with Openness when we consider divergent thinking, which relates to creativity itself. One can understand its relationship to creativity as playing a primary role in governing the creative realm of complexity that an individual has access to/operates from. That is to say, a person who is more intellectual will typically have access to less common realms of creativity than an equally creative person who is less intellectual. 
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td>Openness</td>
                                                                <td><c:out value="${opennessData['aspect'].currentMean}"/></td>
                                                                <td><c:out value="${opennessData['aspect'].normalDistribution}"/></td>
                                                            </tr>
                                                            </tbody>
                                                        </table>

                                                        <!-- Description Module for Extremism Start -->
                                                        <c:forEach items="${extremismDescList}" var="desc">
                                                            ${desc}<br>
                                                        </c:forEach><br>
                                                        <!-- Description Module for Extremism End-->

                                                        <p class="col-12" style="font-size: 15px; font-weight:normal; ">
                                                            Explanation: The Big Five Aspect Scale is probably the most extensively validated psychometric test after IQ, and it has more than 40 years of refinement to describe what a
                                                            person's personality is like right now. This will be affected by things such as environment and emotional state, which is encapsulated in the DISPOSITION DIMENSION.
                                                            Yet, however you score is the Avatar Level that you are currently operating at. Your level may be higher and may be being suppressed or repressed by things like negative
                                                            emotion, which the rest of the assessments can get at. Your Deathproof Factor can jump by as much as 60 points in 6-months, we already have an example of this. The
                                                            Deathproof Factor ranges from -145 to +145, and it has been normalized to a 0-100 scale to give you your Avatar Level. Though other levels of the other factors are at play,
                                                            your Avatar Level is the main number you should be looking to improve, and you do that by raising the other levels in VCBED. Increasing you level and score on any of the
                                                            other tests will inevitably translate to a higher Avatar Level.
                                                        </p>
                                                        <p class="col-12" style="font-size: 15px; font-weight:normal; ">
                                                            <strong>Deathproof Factor</strong> takes the personality structure of a person as they are now and examines what traits they have that make them most prone to living an effective life.
                                                            Overall, the Deathproof Factor = the ability to enter and exit Hero's Journeys with ease. The goal of playing the game of life is to go on as many Hero's Journeys as
                                                            possible in as many different aspects of your life as possible. Ultimately once at a high enough level, you can and should start Hero's Journeys working towards solving
                                                            bigger and bigger problems. But, just like in any video game, if you try to attack a given Hero's Journey (quest/mission) without enough training to be at a high enough
                                                            level, you are going to get brutally destroyed. In real life this means financial, relationship, physical, mental, and social failure, potentially creating a bigger Dragon by
                                                            making the problem worse, and finally sending you into a negative emotional spiral.
                                                        </p>
                                                    </div>
                                                </div>
                                                <br><br>
                                                <div class="row">
                                                    <h3>Avatar Level: ${AvtarLevel}</h3>
                                                    <h3>Wisdom: ${DeathProofScore}</h3>
                                                    <h2  style="font-size: 20px; font-weight:normal; ">AVATAR LEVEL STRATIFICATION</h2>

                                                    <div class="col-md-6 scrollable">
                                                        <table class="table table-bordered">
                                                            <thead> 
                                                                <tr>
                                                                    <th style="font-weight:bold">Strata Number</th>
                                                                    <th style="font-weight:bold">Level Range</th>
                                                                    <th style="font-weight:bold">Level Range</th>
                                                                    <th style="font-weight:bold">Color Name</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr style="background-color: #000000;color: #ffffff;">
                                                                    <td>26</td>
                                                                    <td>100</td>
                                                                    <td>99</td>
                                                                    <td>Abyssal</td>
                                                                </tr>
                                                                <tr style="background-color: #ffffff">
                                                                    <td>25</td>
                                                                    <td>99</td>
                                                                    <td>96</td>
                                                                    <td>Diamond 2</td>
                                                                </tr>
                                                                <tr style="background-color: #ffffff">
                                                                    <td>24</td>
                                                                    <td>96</td>
                                                                    <td>92</td>
                                                                    <td>Diamond</td>
                                                                </tr>
                                                                <tr style="background-color: #455257;color: #ffffff;">
                                                                    <td>23</td>
                                                                    <td>92</td>
                                                                    <td>88</td>
                                                                    <td>Obsidian 2</td>
                                                                </tr>
                                                                <tr style="background-color: #455257;color: #ffffff;">
                                                                    <td>22</td>
                                                                    <td>88</td>
                                                                    <td>84</td>
                                                                    <td>Obsidian</td>
                                                                </tr>
                                                                <tr style="background-color: #007FFF;color: #ffffff;">
                                                                    <td>21</td>
                                                                    <td>84</td>
                                                                    <td>80</td>
                                                                    <td>Azure 2</td>
                                                                </tr>
                                                                <tr style="background-color: #007FFF;color: #ffffff;">
                                                                    <td>20</td>
                                                                    <td>80</td>
                                                                    <td>76</td>
                                                                    <td>Azure</td>
                                                                </tr>
                                                                <tr style="background-color: #FE4E32;color: #ffffff;">
                                                                    <td>19</td>
                                                                    <td>76</td>
                                                                    <td>72</td>
                                                                    <td>Scarlet 2</td>
                                                                </tr>
                                                                <tr style="background-color: #FE4E32;color: #ffffff;">
                                                                    <td>18</td>
                                                                    <td>72</td>
                                                                    <td>68</td>
                                                                    <td>Scarlet</td>
                                                                </tr>
                                                                <tr style="background-color: #6d0175;color: #ffffff;">
                                                                    <td>17</td>
                                                                    <td>68</td>
                                                                    <td>64</td>
                                                                    <td>Violet 2</td>
                                                                </tr>
                                                                <tr style="background-color: #6d0175;color: #ffffff;">
                                                                    <td>16</td>
                                                                    <td>64</td>
                                                                    <td>60</td>
                                                                    <td>Violet</td>
                                                                </tr>
                                                                <tr style="background-color: #8e8e8e;color: #ffffff;">
                                                                    <td>15</td>
                                                                    <td>60</td>
                                                                    <td>56</td>
                                                                    <td>Slate 2</td>
                                                                </tr>
                                                                <tr style="background-color: #8e8e8e;color: #ffffff;">
                                                                    <td>14</td>
                                                                    <td>56</td>
                                                                    <td>52</td>
                                                                    <td>Slate</td>
                                                                </tr>
                                                                <tr style="background-color: #059b03;color: #ffffff;">
                                                                    <td>13</td>
                                                                    <td>52</td>
                                                                    <td>48</td>
                                                                    <td>Sage 2</td>
                                                                </tr>
                                                                <tr style="background-color: #059b03;color: #ffffff;">
                                                                    <td>12</td>
                                                                    <td>48</td>
                                                                    <td>44</td>
                                                                    <td>Sage</td>
                                                                </tr>
                                                                <tr style="background-color: #a50404;color: #ffffff;">
                                                                    <td>11</td>
                                                                    <td>44</td>
                                                                    <td>40</td>
                                                                    <td>Crimson 2</td>
                                                                </tr>
                                                                <tr style="background-color: #a50404;color: #ffffff;">
                                                                    <td>10</td>
                                                                    <td>40</td>
                                                                    <td>36</td>
                                                                    <td>Crimson</td>
                                                                </tr>
                                                                <tr style="background-color: #526ef9;color: #ffffff;">
                                                                    <td>9</td>
                                                                    <td>36</td>
                                                                    <td>32</td>
                                                                    <td>Blue 2</td>
                                                                </tr>
                                                                <tr style="background-color: #526ef9;color: #ffffff;">
                                                                    <td>8</td>
                                                                    <td>32</td>
                                                                    <td>28</td>
                                                                    <td>Blue</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>    

                                                    </div>
                                                    <div class="col-md-6 scrollable">
                                                        <table class="table table-bordered">
                                                            <thead> 
                                                                <tr>
                                                                    <th style="font-weight:bold">Strata Number</th>
                                                                    <th style="font-weight:bold">Level Range</th>
                                                                    <th style="font-weight:bold">Level Range</th>
                                                                    <th style="font-weight:bold">Color Name</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr style="background-color: #ff0000;color: #ffffff;">
                                                                    <td>7</td>
                                                                    <td>28</td>
                                                                    <td>24</td>
                                                                    <td>Red 2</td>
                                                                </tr>
                                                                <tr style="background-color: #ff0000;color: #ffffff;">
                                                                    <td>6</td>
                                                                    <td>24</td>
                                                                    <td>20</td>
                                                                    <td>Red</td>
                                                                </tr>
                                                                <tr style="background-color: #d1abfc">
                                                                    <td>5</td>
                                                                    <td>20</td>
                                                                    <td>16</td>
                                                                    <td>Lavender 2</td>
                                                                </tr>
                                                                <tr style="background-color: #d1abfc">
                                                                    <td>4</td>
                                                                    <td>16</td>
                                                                    <td>12</td>
                                                                    <td>Lavender</td>
                                                                </tr>
                                                                <tr style="background-color: #e2e2e2">
                                                                    <td>3</td>
                                                                    <td>12</td>
                                                                    <td>8</td>
                                                                    <td>Grey 3</td>
                                                                </tr>
                                                                <tr style="background-color: #e2e2e2">
                                                                    <td>2</td>
                                                                    <td>8</td>
                                                                    <td>4</td>
                                                                    <td>Grey 2</td>
                                                                </tr>
                                                                <tr style="background-color: #e2e2e2">
                                                                    <td>1</td>
                                                                    <td>4</td>
                                                                    <td>0</td>
                                                                    <td>Grey</td>
                                                                </tr>
                                                            </tbody>
                                                        </table> 
                                                        <div class="col-12" style="font-size: 15px; font-weight:normal; ">
                                                            The Avatar level stratification is a stratification of levels into similar "Strata."
                                                            Each Strata has a "sub-strata" that is denoted by a 2 (no 2 just means it is the
                                                            first sub-strata). You will be most similar to people in your same sub-strata,
                                                            and fairly similar in values and operation to people in the same Strata as you.
                                                            They make it easy to remember how you are categorized and how the levels
                                                            look in real world operation. The higher the Strata you fall into, the more
                                                            potential you have to be effective in the world and the game of life in general.
                                                            However, there are plenty of handicaps that this score does not necessarily
                                                            take into account. How you score on the DISPOSITION DIMENSION and
                                                            some of the other tests can paint a more accurate total picture of your
                                                            effectiveness overall.
                                                        </div>
                                                    </div>
                                                </div><br><br>
                                                <!-- Anps Module Start -->
                                                <div class="row">
                                                    <h2 style="font-size: 20px; font-weight:normal; ">ANPS CALCULATOR</h2>

                                                    <div class="col-md-9 scrollable">
                                                        <table class="table table-bordered">
                                                            <thead> 
                                                                <tr>
                                                                    <th></th>
                                                                    <th style="font-weight:bold; text-align: center;">Mean</th>
                                                                    <%--<th style="font-weight:bold; text-align: center;">Excel Mean</th> --%>
                                                                    <th style="font-weight:bold; text-align: center;">Total Score</th>
                                                                    <th style="font-weight:bold; text-align: center;">Population Percentile</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td style="text-align: center;"><span  id="PlayTotalLabelId">Play Total</span> <i style="font-size: 14px;" class="fa fa-angle-down PlayTotalId" ></i></td>
                                                                    <td style="text-align: center;">${AnpsPlayAverage}</td>
                                                                    <%-- <td style="text-align: center;"><fmt:formatNumber type="number" value="${AnpsPlayScore/14}" maxFractionDigits="10" minFractionDigits="1" /></td>--%>
                                                                    <td style="text-align: center;">${AnpsPlayScore}</td>
                                                                    <td style="text-align: center;">${AnpsPlayPercentage}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="PlayTotalId">
                                                                    <td colspan="4">
                                                                        Play alters how much we desire to be having fun vs. being serious, our enjoyment of playing games with physical contact, humor, and laughter, and being generally happy and joyful. We all enjoy having fun with our friends and laughing at a good joke. These are all related to ancestral PLAY urges that we still share with the other mammals. As a reward system, the activation of PLAY fills us with “delight” as we have fun with our friends. It helps us to not take things too seriously. It can be seen as playing a role in our acclimation (getting used to) things that might otherwise be dangerous, so we may be better prepared in the even that such a dangerous situation arises. Similarly, laugher and humor are often how we respond to something (usually something surprising) that we deem to not be a threat (see more about this in the technical description). When we are higher in play, we often do not take things too seriously, and we see them as an opportunity for fun and enjoyment. This can enhance our ability to achieve our desired outcomes, while reducing how badly we feel when do not succeed. When we are higher in play, we tend to have a broader and deeper sense of humor, and we rarely take offense to anything that was intended to be funny. We also are more likely to enjoy joking around and teasing one another, as well as being teased ourselves. We tend not to take such things personally, and we see it as a sign that the other person feels a sense of affection, closeness, and comfort with us. We tend to have a negative view of people who look down on this sort of fun, as we see them as being stuck up, stuffy, overly sensitive, and even being weak. On the other hand, when we are lower in play, we tend to take things more seriously more often. We are more likely to respond with feelings of sadness, anger, and fear when things do not go how we wanted them to go. We are also more likely to take offense to things that are meant to be funny, which are usually things that other people find funny. Further, if we are low in Play, we tend to take offense much more often when someone is teasing us. We tend to see it as an attack on ourselves, our social status, and even a threat to our very sense-of-self. We are much less able to understand why people do not take offense at such things, as we may even become emotionally triggered just by being around such teasing (even if we are not participating). We often see people who like to tease one another as crass, offensive, unruly, lower-class/status, uncultured, insensitive, etc. It helps for us all to understand that often, how much we fall into one camp or the other is dictated by how we were raised, as well as innate genetic differences. Neither way is wrong, or necessarily entirely better. Both ways of being have their purposes and their levels of usefulness. As humans, we tend not to evolve various ways of being unless they serve some useful purpose in at least one context.
                                                                        <br><br>
                                                                        <strong>Technical Description: </strong> <i style="font-size: 14px;" class="fa fa-angle-down PlayTotalTechnicalId" ></i>
                                                                        <br>
                                                            <h colspan="4" class="option-content is-hidden table_row" id="PlayTotalTechnicalId">
                                                                Young animals have a need for physical play (running, pouncing, wrestling…) these “aggressive/assertive” actions are consistently accompanied by positive affect and intense social joy. The PLAY/Joy system motivates physical social engagement (aka “rough-and-tumble”) play in all young mammals and commonly provides an affectively positive developmental context for learning how to socially interact with others, which thereby facilitates social integration in general. A key function of social play is to learn social rules and refine social interactions. This necessarily increases the ability of an individual to partake of more complex sorts of play/games. It is also where we get the saying “it is not about whether you win or lose, but how you play the game.” Such a comment of the nature of sportsmanship reflects the complexity of how humans think about “games.” An ungracious winner is just as despised as a sore loser. Games often reflect a means to test how much we want to engage in a variety of pursuits/activities with one another beyond merely PLAY. Subcortically concentrated PLAY urges may actually encourage the epigenetic growth of higher social brain functions such as empathy. PLAY further serves a key role in the testing of our own limitations as well as the limitations and boundaries of those around us. It is natural, especially for a highly PLAYful person to push the boundaries of what is acceptable in whatever domain such an individual finds oneself, often to the point of crossing said boundaries, usually evoking a negative reaction on the part of the individual whose boundaries this particularly PLAYful person crossed.     
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="text-align: center;"><span id="SeekingTotalLabelId">Seeking Total</span> <i style="font-size: 14px;" class="fa fa-angle-down SeekingTotalId" ></i></td>
                                                                <td style="text-align: center;">${AnpsSeekingAverage}</td>
                                                                 <%-- <td style="text-align: center;"><fmt:formatNumber type="number" value="${AnpsSeekingScore/14}" maxFractionDigits="10" minFractionDigits="1" /></td>--%>
                                                                <td style="text-align: center;">${AnpsSeekingScore}</td>
                                                                <td style="text-align: center;">${AnpsSeekingPercenatge}</td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="SeekingTotalId">
                                                                <td colspan="4">
                                                                    Our seeking system is active when we all get curious and energized during new experiences, whether about new neighbors moving in next door or the excitement of buying a new car, especially our first one. On the positive side, the SEEKING system provides us with a very special euphoric “buzz” (we often call it enthusiasm) as we explore possibilities and anticipate desired outcomes. Seeking provokes intense and enthusiastic exploration and appetitive anticipatory excitement/learning. The higher we score in this, the more this is true. When fully aroused, seeking fills our minds with interest and motivates us to effortlessly search for the things we need, crave, and desire. It further generates and sustains curiosity for things from the mundane up to our highest intellectual pursuits. Our activity in our seeking system is reduced if we are experiencing addictive drug withdrawal, chronic stress, and sickness, and with accompanying feelings of depression. This means that when we are experiencing those things, we will feel much less excited by the pursuits and activities we normally find meaningful. It is valuable for us to take into account our current life circumstances when we look at how highly we score on this.  Overactivity of this system can promote excessive and impulsive behaviors, along with psychotic delusions and manic thoughts. We can think of this as heavily involved in the creation of our individual realities, as the seeking system appears to generate our beliefs and experiences about the nature of the world from the perception of correlated events. To clarify, the higher we score on this, the more we tend to experience things as meaningful and connected, while continuing to pursue ever more enriching experiences. 
                                                                    <br><br>
                                                                    <strong>Technical Description: </strong> <i style="font-size: 14px;" class="fa fa-angle-down SeekingTotalTechnicalId" ></i>
                                                                    <br>
                                                            <h colspan="4" class="option-content is-hidden table_row" id="SeekingTotalTechnicalId">
                                                                The experience of the arousal of the seeking system is defined as feeling curious, feeling like exploring, striving for solutions to problems and puzzles, positively anticipating new experiences, and a sense of being able to accomplish almost anything.” The seeking/desire system is confluent with the medial forebrain bundle (MFB), aka the brain reward system. The MFB and major dopamine-driven, self-stimulation “reward” circuitry coursing from ventral midbrain to nucleus accumbens and medial frontal cortex, where it can promote frontal cortical functions related to planning and foresight. This system is essential for animals to be able to gather all resource needs for survival. It provokes the arousal needed for enthusiastic exploration, appetitive anticipatory excitement/learning, and is a major source of life energy sometimes called libido.
                                                                <br>
                                                                The SEEKING system may have originally evolved as a general-purpose foraging system (a “seek and find” system) energizing the search for food and other resources needed for survival. With other life goals, the function of this all-important system (which seems to lie at the core of our feelings of “selfhood,”) evolutionarily broadened to energize the exploration for resources in general.
                                                                <br>
                                                                All antipsychotics reduce arousability of this “reality-creating” (seeking) mechanism of the brain.
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="text-align: center;"><span id="CareTotalLabelId">Care Total</span> <i style="font-size: 14px;" class="fa fa-angle-down CareTotalId" ></i></td>
                                                                <td style="text-align: center;">${AnpsCareAverage}</td>
                                                                <%--  <td style="text-align: center;"><fmt:formatNumber type="number" value="${AnpsCareScore/14}" maxFractionDigits="10" minFractionDigits="1" /></td>--%>
                                                                <td style="text-align: center;" >${AnpsCareScore}</td>
                                                                <td style="text-align: center;">${AnpsCarePercentile}</td>
                                                            <tr>
                                                            <tr class="option-content is-hidden table_row" id="CareTotalId">
                                                                <td colspan="4">
                                                                    Many of us would feel especially tenderhearted and CAREing toward baby animals and might be inclined to give a little money to a homeless beggar. The CARE system infuses us with a “warm glow” as we support the lives of our children and help others overcome their problems. The CARE system, along with the PANIC/Sadness system, may be especially important for engendering our feelings of empathy and sympathy when bad things happen to others nearby, but especially to those whom we love. The CARE/Nurturance system motivates and coordinates the caretaking and rearing of infants from the time they are totally dependent newborns throughout the long period of early childhood development. However, the CARE system may also motivate social helping behaviors in general.
                                                                </td>
                                                            </tr>
                                                            <td style="text-align: center;"><span id="FearTotalLabelId">Fear Total</span> <i style="font-size: 14px;" class="fa fa-angle-down FearTotalId" ></i></td>
                                                            <td style="text-align: center;">${AnpsFearAverage}</td>
                                                            <%--<td style="text-align: center;"><fmt:formatNumber type="number" value="${AnpsFearScore/14}" maxFractionDigits="10" minFractionDigits="1" /></td>--%>
                                                            <td style="text-align: center;" >${AnpsFearScore}</td>
                                                            <td style="text-align: center;">${AnpsFearPercentile}</td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="FearTotalId">
                                                                <td colspan="4">
                                                                    Most of us are afraid of snakes and bears and no doubt would be a bit anxious if we were lost in the woods or had to walk alone through a rundown neighborhood in a strange city. The capacity for FEARfulness is built into us. A negative aspect of the FEAR system makes us anxious, and can indeed, grip us with “terror” when we sense that our life or well-being is in danger. As we as a species have grown more complex, this has been abstracted to be triggered when our way of life is threatened as well. This is because we think of our ‘life’ as a much more extended thing than merely our survival. The FEAR/Anxiety system identifies and predicts when dangers are imminent and prepares the body to either freeze or flee, depending on which response will be most adaptive. It also interacts with the RAGE/anger system to see whether or not the best response is fight.
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="text-align: center;"><span id="AngerTotalLabelId">Anger Total</span> <i style="font-size: 14px;" class="fa fa-angle-down AngerTotalId" ></i></td>
                                                                <td style="text-align: center;">${AnpsAngerAverage}</td>
                                                                <%--  <td style="text-align: center;"><fmt:formatNumber type="number" value="${AnpsAngerScore/14}" maxFractionDigits="10" minFractionDigits="1" /></td>--%>
                                                                <td style="text-align: center;" >${AnpsAngerScore}</td>
                                                                <td style="text-align: center;">${AnpsAngerPercentile}</td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="AngerTotalId">
                                                                <td colspan="4">
                                                                    We will refer to the Anger system as the RAGE system, as it is technically more accurate.  This system is active when we get extremely frustrated when we do not get the job we want and perhaps more than a little irritated when family members do not do their share of the work. Such examples cause us to become  enRAGEd). The lower we score on this, the more difficult is to trigger this system. The higher we score, the easier it is to trigger our RAGE response. Just as it can be a bad thing to be too easily overcome with feelings of RAGE and anger, it can be problematic if it is too difficult to trigger this response in us as well. The RAGE system responds when the loss of resources is threatened—for example, loss of food, family, or money—and prepares the body to fight to get them back, if necessary. On the negative side, the RAGE/Anger system sparks feelings of “irritation” and directs us to “attack” whoever or whatever threatens us or our possessions. 
                                                                    <br>
                                                                    It can also become triggered
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="text-align: center;"><span id="SadnessTotalLabelId">Sadness Total</span> <i style="font-size: 14px;" class="fa fa-angle-down SadnessTotalId" ></i></td>
                                                                <td style="text-align: center;">${AnpsSadnessAverage}</td>
                                                                <%--  <td style="text-align: center;"><fmt:formatNumber type="number" value="${AnpsSadnessScore/14}" maxFractionDigits="10" minFractionDigits="1" /></td>--%>
                                                                <td style="text-align: center;" >${AnpsSadnessScore}</td>
                                                                <td style="text-align: center;">${AnpsSadnessPercentile}</td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="SadnessTotalId">
                                                                <td colspan="4">
                                                                    We all feel loneliness and psychological pain that comes with broken relationships, especially the death of a loved one, and a similar feeling of “separation distress” when we are socially marginalized or rejected; we call this feeling PANIC/Sadness. The PANIC/Sadness system overwhelms us with “desperate helplessness.” This is the painful distress (that can gradually become despair) we felt when, as children, we lost contact with our parents, or later in life when we lose (or are suddenly locked out of) a close, sustaining relationship. As such, the PANIC system can play a role in determining how comfortable we are with venturing out of our comfort zone, as well as how we respond when a threat appears that is beyond our level of comfort to confront. The role it plays for us is usually related to how we have been able to handle such negative experiences in the past. Our PANIC system can prime our FEAR system if we have experienced traumatizing levels of PANIC/sadness in the past, where we did not feel we had enough support.    
                                                                    <br>
                                                                    The PANIC/Sadness system is engaged, especially in youngsters, when they lose contact with their mothers—we assume this is the feeling of psychological distress/pain that all infant mammals and birds suddenly feel when they lose close contact with supportive others. It is often associated with crying in children separated from their parents, with the death of a loved one, and with social rejection in general. At times we have also called it the Grief system or the Sadness system, because many people don’t understand the implications of PANIC—the extremely agitated state young animals exhibit when they are lost or even accidentally separated from parents for even very short periods of time.
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="text-align: center;"><span id="SpiritualityTotalLabelId">Spirituality Total</span> <i style="font-size: 14px;" class="fa fa-angle-down SpiritualityTotalId" ></i></td>
                                                                <td style="text-align: center;">${AnpsSpiritualityAverage}</td>
                                                                <%--  <td style="text-align: center;"><fmt:formatNumber type="number" value="${AnpsSpiritualityScore/12}" maxFractionDigits="10" minFractionDigits="1" /></td>--%>
                                                                <td style="text-align: center;" >${AnpsSpiritualityScore}</td>
                                                                <td style="text-align: center;">${AnpsSpiritualityPercentile}</td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="SpiritualityTotalId">
                                                                <td colspan="4">
                                                                    We experience the activation of the Spirituality system as the feeling of being “connected” to humanity and creation as a whole, feeling a sense of “oneness” with creation. We further experience it while striving for inner peace and harmony, relying on spiritual principles, and searching for meaning in life (though this last point is related to SEEKING).                                                                       
                                                                    <br><br>
                                                                    <strong>Technical Description: </strong> <i style="font-size: 14px;" class="fa fa-angle-down SpiritualityTotalTechnicalId" ></i>
                                                                    <br>
                                                            <h colspan="4" class="option-content is-hidden table_row" id="SpiritualityTotalTechnicalId">
                                                                This scale was added for “a hypothesized higher-order affective human attribute” and was also found to be relevant in the recovery of alcoholism. 
                                                                <br>
                                                                Positively related to the caring and seeking scales.   
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="text-align: center;"><span id="LustTotalLabelId">Lust Total</span> <i style="font-size: 14px;" class="fa fa-angle-down LustTotalId" ></i></td>
                                                                <td style="text-align: center;">${AnpsLustAverage}</td>
                                                                 <%-- <td style="text-align: center;"><fmt:formatNumber type="number" value="${AnpsLustScore/14}" maxFractionDigits="10" minFractionDigits="1" /></td>--%>
                                                                <td style="text-align: center;">${AnpsLustScore}</td>
                                                                <td style="text-align: center;">${AnpsLustPercentile}</td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="LustTotalId">
                                                                <td colspan="4">
                                                                    Our sexual system, LUST, bridges ancient socioerotic SEEKING urges and mammalian desires to CARE for young and for the young to PLAY with each other. To put it more simply, our LUST system combines and coordinates elements of desire, care, and playfulness to elevate our sexual interactions to a much more evolved and complex status than purely mating, as is seen with most other animals. As humans, we have a large spectrum of sexuality. Some of us have an extremely strong sexual drive, where we feel the need for this system to be stimulated and satisfied extremely frequently (up to multiple times per day in both men and women, for reference). Whereas, others experience extremely low, and for some, even seemingly non-existent sex drives. Such individuals may never experience any sexual feelings or desires. Where we fall on this spectrum determines how much we are attracted to, or are turned off by, all things sexual. This system plays a role in the depth of the sexual nature of how we experience a range of things, as well as our level of comfort with such an experience. To clarify, for those of us who score highly on this, we will experience a much greater depth of sensuality relating to anything that we experience as sexual. This can be anything from casual flirting to a deeply intimate experience. On the other hand, if we score low on this, we generally do not experience a great deal of satisfaction and enjoyment, even from what others might see as deeply erotic experiences. There are other systems that come into play with how comfortable we are with the experiences related to this system. Other systems such as PLAY, CARE, and SEEKING are also responsible for how broad our range of experiences is that we can experience as sexual in nature. For example, for those of us who score high on CARE and LUST, we might experience cuddling/comforting our romantic partner as at least a somewhat sexual experience. However, this does not necessarily mean that we desire to be sexually intimate with our partner in that moment. This might even be the case for doing such a thing for friends, (more likely of the opposite sex). Our underlying emotional systems do not always respect our more cognitively imposed boundaries. Similarly, if we are high in SEEKING and LUST, we might experience intellectual discussions as sexually arousing (again this is more likely with members of the opposite sex, but it does not necessarily have to be the case). On the other hand, for those of us who are lower in the three previously mentioned systems, we might only experience sex itself to be sexual. It would serve us all well to understand and respect how different we all are with regard to the nature of our sexuality.
                                                                    <br>
                                                                    Finally, the more negatively experienced emotional systems of RAGE/Anger, PANIC/Sadness, and FEAR, as well as one’s past experiences (especially the emotional systems that were active around such experiences) influence our level of comfort with all things sexual. The more we have experienced understanding, acceptance, and openness regarding all things relating to sex, the more comfortable we tend to be with it. The lower we score on these, the more likely we are to avoid feeling emotionally triggered in sexual situations.
                                                                    <br><br>
                                                                    <strong>Technical Description: </strong> <i  style="font-size: 14px;" class="fa fa-angle-down LustTotalTechnicalId" ></i>
                                                                    <br>
                                                            <h colspan="4" class="option-content is-hidden table_row" id="LustTotalTechnicalId">
                                                                Aroused by male and female sex hormones which control many brain chemistries including, specifically in this case, oxytocin transmission promoted by estrogen in females and vasopressin transmission by testosterone in males. Oxytocin promotes trust and confidence in females and thus leads to sexual readiness. Vasopressin promotes assertiveness, and sometimes jealous behaviors. The LUST system interacts with the seeking system which aids in the search for sexual rewards. It plays a role in our ability to derive more complex forms of pleasure    
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>                  
                                                    </div>
                                                </div>
                                                <!-- Description Module Start -->
                                                <c:forEach items="${anpsDescList}" var="desc">
                                                    ${desc}<br>
                                                </c:forEach><br>

                                                <!-- Description Module End-->

                                                <!-- Anps Module Stop -->
                                                <div class="row mt-5">
                                                    <div class="col-12" style="font-size: 15px; font-weight:normal; ">
                                                        Explanation: The Affective Neuroscience Personality Scale specifically measures what emotions you are likely experiencing at a given time, and how your emotionality for a given trait emotion (e.g. PLAY, FEAR, CARE), compares to the rest of the population.
                                                    </div>
                                                </div>
                                                <br><br>
                                                <!-- Dark Factor Calculator Start -->
                                                <div class="row">
                                                    <td><label style="font-size: 20px; font-weight:normal; " id="DarkFactorCalculatorLabelId">DARK FACTOR CALCULATOR</label> <i style="font-size: 20px; font-weight:normal; " class="fa fa-angle-down DarkFactorCalculatorId" ></i></td>
                                                    <br>
                                                    <h class="option-content is-hidden table_row" id="DarkFactorCalculatorId">
                                                        When we score highly (about 75th Percentile) in multiple Dark Traits (usually more than 5 or 6), it is reasonably likely that we might have some type of diagnosable condition, although there are still exceptions to this. Scoring highly on a few does not necessarily indicate that you have a condition associated with that trait. That is to say, we can score highly on Psychopathy (even over the 99th percentile) and still not be a psychopath. We can even score over the 99th percentile on all three of the Dark Triad (Machiavellianism, Narcissism, and Psychopathy) and still not warrant being diagnosed as a true narcissist or psychopath.
                                                        <br>
                                                        With the majority of this research and classification, we have to look at essentially three different groups. The conventionally studied group is people who have a specific level and style of these traits that warrants their classification as having a psychological disorder (though the term psychological disorder is often too loosely understood). These tend to be individuals who see nothing wrong with thinking and behaving in these ways that diverge sharply from how more normal adults are (there is a reasonably tight range that constitutes normal). We can typically see this if a person scores highly on multiple Dark Traits, and especially if they tend to lack an ability or willingness to act from them. When we fall into this category, we often cannot help but act in anti-social ways that make others not want to interact with us. Often this means that we will have difficulty forming solid long-term relationships, especially with more normal people. We also tend to have trouble holding down a consistent job, as we take less responsibility, exploit others, and generally do not make good team players. 
                                                        <br>
                                                        The second group is people who score highly on a few Dark Traits, but understand that to some degree, they are socially unacceptable, such that they generally do not act from them. This might reflect a person being able to be particularly cold, having sadistic fantasies (particularly with other systems stepping in to curtail any desire to explore them), etc. These people tend to fall within the classification of normal. They are a bit more divergent and different, or at least they have the ability to be, but these types of people typically operate in ways that are consistent with people who score relatively low or average on all of the traits. When we are like this, we might see the world a bit differently than the more normal people around us. We are more likely to wonder “what if?” regarding more antisocial ways of being. We can often tap slightly into a level of the darker side of behavior if absolutely needed, that others often have difficulty even imagining.
                                                        <br>
                                                        The third group is the rarest, and the one that can lead to the most confusion surrounding the Dark Traits. These are typically individuals that score highly on a number of the Dark Traits, but generally possess some aversion toward using them for nefarious/antisocial purposes. These people tend to achieve higher levels of success and status, which leads to the stereotype that there are a disproportionate number of psychopaths and narcissists that are doctors (especially surgeons), CEOs, and politicians. This classification is sloppy to say the least. While these professions are likely to have a slightly higher number of people that do cross the line to clinically diagnosable disorders, such individuals do not tend to do very well or last very long. If a person crosses the line into full blown psychopathy, or unreasonable levels of grandiose narcissism, they typically do something that sabotages the long-term viability of their career/position. Consequently, such positions require a high degree of self-control and a willingness and ability to choose to act counter to these darker impulses/desires. When we score this way, we use these darker ways of being in generally more prosocial ways. That is not to say that we will not take some actions that put us ahead of our less dark peers, but this is typically done with the justification that we are better qualified, and the better person for the job. This can be justified by our very ability and willingness to think and behave in such ways, as people who are incapable of conceiving of the types of manipulations/strategies we use are vulnerable to people that are even more predatory than us (and who might be genuinely nefarious). We are still bothered when we see someone genuinely psychopathic preying on the helpless and innocent. As such, we can be tempted to fight fire with fire so to speak. Often, when we score in a way that suggests we have a diagnosable disorder, we cannot derive joy, meaning, and even love, from things that most people can. However, when we score this way, (multiple high dark traits but no disorder) we can generally derive the same positive feelings from experiences similar to normal people. However, without certain counterbalancing traits and without a high enough perspective, we can get caught up in attempting to derive a large portion of our joy from competing, achievement, status, or whatever metrics of success we are using. Whereas most other people ultimately derive joy and life satisfaction from their relationships. This is why it is especially useful and necessary for us to develop to a sufficiently high perspective, such that we can properly integrate and leverage all of the advantageous traits we possess, without suffering the downside consequences of them as well.
                                                        <br>
                                                        <strong>Technical Note on Dark Traits:</strong>
                                                        <br>
                                                        It should be understood that the interaction of the dark traits with the rest of a person’s psyche is what produces their behavioral outputs over time. It is useful to consolidate many of the traits loosely to think of them as promoting dark behavior or inhibiting it. Though that is an oversimplification, it is useful. Simply because someone scores highly on certain dark traits does not mean they will behave accordingly. Typically, what is required is opportunity and a lack of inhibition to behave otherwise. This disinhibition is what tends to separate truly clinically diagnosable individuals from the rest of the population.   
                                                    </h>
                                                    <br><br>
                                                    <div class="col-md-9 scrollable">
                                                        <table class="table table-bordered">
                                                            <thead> 
                                                                <tr>
                                                                    <th style="font-weight:bold;text-align: center;"></th>
                                                                    <th style="font-weight:bold;text-align: center;">Mean</th>
                                                                    <th style="font-weight:bold;text-align: center;">Score</th>
                                                                    <th style="font-weight:bold;text-align: center;">Population Percentile</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td style="text-align: center;"><span id="EgoismLabelId">Egoism</span> <i style="font-size: 14px;" class="fa fa-angle-down EgoismId" ></i></td>
                                                                    <td style="text-align: center;" >${darkfactorEgoismAverage}</td>
                                                                    <td style="text-align: center;">${darkfactorEgoismTotal}</td>
                                                                    <td style="text-align: center;">${darkfactorEgoismPercentile}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="EgoismId">
                                                                    <td colspan="4">
                                                                        The excessive concern with one’s own pleasure or advantage at the expense of community well-being.
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td style="text-align: center;"><span id="MachiavellianismLabelId">Machiavellianism</span> <i style="font-size: 14px;" class="fa fa-angle-down MachiavellianismId" ></i></td>
                                                                    <td style="text-align: center;" >${darkfactorMachiavellianismAverage}</td>
                                                                    <td style="text-align: center;">${darkfactorMachiavellianismtotal}</td>
                                                                    <td style="text-align: center;">${darkfactorMachiavellianismPercentile}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="MachiavellianismId">
                                                                    <td colspan="4">
                                                                        There tends to be a great deal of misunderstanding around the nature of this Dark Trait, as well as many others. See the “Note on Dark Traits” above. Machiavellianism (Mach for short) is characterized by manipulativeness, callous affect, and a strategic-calculating orientation. We have to understand this relative to the three groups people tend to fall into with regard to it. When we score low in Mach, we tend to be more naïve, less tactful and strategic in our actions and words, and we are generally warmer, more genuinely caring people. We also tend to trust more readily and easily, without needing to feel that we have some means of recourse, legal or otherwise. This is extremely valuable and necessary for a functioning society, as we cannot constantly be looking over our shoulders, worrying about whether or not people are trying to take advantage of us. However, this does make us much more vulnerable to scams, and generally being taken advantage of in any situation. 
                                                                        <br>
                                                                        Compared to low Machs, high Machs gave high priority to money, power, and competition and relatively low priority to community building, self-love, and family concerns (McHoskey, 1999). Machs admitted to a focus on unmitigated achievement and winning at any cost
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td style="text-align: center;"><span id="NarcissismLabelId">Narcissism</span> <i style="font-size: 14px;" class="fa fa-angle-down NarcissismId" ></i></td>
                                                                    <td style="text-align: center;" >${darkfactorNarcissismAverage}</td>
                                                                    <td style="text-align: center;">${darkfactorNarcissismTotal}</td>
                                                                    <td style="text-align: center;">${darkfactorNarcissismPercentile}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="NarcissismId">
                                                                    <td colspan="4">
                                                                        Narcissism, along with self-esteem, coordinates how we think and feel about ourselves, especially our status and perceived level of excellence. Like many of the other Dark Traits, Narcissism is not necessarily a bad thing. When we are high in narcissism, we think highly of ourselves, tend to regard ourselves as special, and we think we are better than others (your percentile can be seen as what percentage of the population you think of yourself as better than).
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td style="text-align: center;"><span id="PsychopathyLabelId">Psychopathy</span> <i style="font-size: 14px;" class="fa fa-angle-down PsychopathyId" ></i></td>
                                                                    <td style="text-align: center;" >${darkfactorPsychopathyAverage}</td>
                                                                    <td style="text-align: center;">${darkfactorPsychopathyTotal}</td>
                                                                    <td style="text-align: center;">${darkfactorPsychopathyPercentile}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="PsychopathyId">
                                                                    <td colspan="4">
                                                                        The Psychopathy trait plays a role in governing our emotional sensitivity; the higher we are in psychopathy, the less emotionally sensitive we are. Depending on how we score on other traits, especially the emotional side of things, this is not necessarily the case. When we score this way, it may simply be more possible for us to turn our emotional sensitivity up and down. This trait also helps determine how impulsive we tend to be, as it is one trait that plays a role in the self-control system. When we score lower in this, it usually means we are not very impulsive, and we do not have too much control over how much we feel our emotions, so they are often experienced more deeply, and they have a more significant impact on our thinking and behavior.
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td style="text-align: center;"><span id="MoralDisengagementLabelId">Moral Disengagement</span> <i style="font-size: 14px;" class="fa fa-angle-down MoralDisengagementId" ></i></td>
                                                                    <td style="text-align: center;" >${darkfactorMoralDisengagementAverage}</td>
                                                                    <td style="text-align: center;">${darkfactorMoralDisengagementTotal}</td>
                                                                    <td style="text-align: center;">${darkfactorMoraldisengagementPercentile}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="MoralDisengagementId">
                                                                    <td colspan="4">
                                                                        This trait plays a primary role in how much we are influenced by our sense of what is right and wrong. It is responsible for how strictly we feel the need to stick to sets of moral guidelines that come from places like society, religion, and our own life experience.     
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td style="text-align: center;"><span id="PsychologicalEntitlementLabelId">Psychological Entitlement</span> <i style="font-size: 14px;" class="fa fa-angle-down PsychologicalEntitlementId" ></i></td>
                                                                    <td style="text-align: center;" >${darkfactorPsychologicalAverage}</td>
                                                                    <td style="text-align: center;">${darkfactorPsychologicalTotal}</td>

                                                                    <td style="text-align: center;">${darkfactorPsychologicalPercentile}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="PsychologicalEntitlementId">
                                                                    <td colspan="4">
                                                                        Psychological Entitlement (PE) can be understood as the trait that explains how deserving and entitled, we feel on a consistent basis. This is somewhat related to whether we feel ‘worthy’ of things that we desire. However, PE in the extreme tends to describe when worthiness goes wrong. As with most of these, how highly we score is a bit less important than why we score the way we do. The more highly we score in PE, the more we feel that things should go our way. When this is mixed with other issues, it can lead to us not being able to handle or understand why things did not work out the way we wanted them to when they do not go our way. PE also plays a role in the level of standards that we feel comfortable demanding.  For those of us who score more highly in this regard, we are usually better at playing Zero-Sum Games (games where not everyone gets to be an equal winner). We are less likely to feel bad about getting the spoils of victory at the expense of the other people involved. 
                                                                        <br>
                                                                        PE also strongly dictates how easy it is for us to accept acts of kindness and help from others. If we are lower in this, we may feel unworthy or have a generally difficult time accepting others doing things for us. On the other hand, if we are higher in it, we may expect others to do helpful things for us, often for no particular reason. 
                                                                        <br><br>
                                                                        <strong>Technical Description: </strong> <i style="font-size: 14px;" class="fa fa-angle-down PsychologicalEntitlementTechnicalId" ></i>
                                                                        <br>
                                                            <h class="option-content is-hidden table_row" id="PsychologicalEntitlementTechnicalId" colspan="4">
                                                                Psychological Entitlement is a stable and pervasive sense that one deserves more and is entitled to more than others.    
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="text-align: center;"><span id="SelfInterestLabelId">Self-Interest</span> <i style="font-size: 14px;" class="fa fa-angle-down SelfInterestId" ></i></td>
                                                                <td style="text-align: center;" >${darkfactorSelfInterestAverage}</td>
                                                                <td style="text-align: center;">${darkfactorSelfInterestTotal}</td>
                                                                <td style="text-align: center;">${darkfactorSelfInterestPercentile}</td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="SelfInterestId">
                                                                <td colspan="4">
                                                                    The trait Self-Interest (SI) determines how much we pursue more personal goals and gains versus attempting to put others first. On the Consciousness Continuum, this means the higher we score in SI, the more we will pursue the vectors (values) associated with the odd levels of development. When we score higher in this, we are more driven by material possessions, wealth, social status, power, personal achievement, and our personal experience of happiness. Conversely, when we score on the low end, we are more motivated and fulfilled by our relationships, our connection/devotion to a greater good and a higher power (these are deeply related), as well as how interested we are in equality and fairness for all peoples.
                                                                    <br><br>
                                                                    <strong>Technical Definition: </strong> <i style="font-size: 14px;" class="fa fa-angle-down SelfInterestTechnicalId" ></i>
                                                                    <br>
                                                            <h class="option-content is-hidden table_row" id="SelfInterestTechnicalId" colspan="4">
                                                                Self-Interest can be understood as the pursuit of gains in socially valued domains, including material goods, social status, recognition, academic or occupational achievement, and happiness.
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="text-align: center;"><span id="SpitefulnessLabelId">Spitefulness</span> <i style="font-size: 14px;" class="fa fa-angle-down SpitefulnessId" ></i></td>
                                                                <td style="text-align: center;" >${darkfactorSpitefulnessAverage}</td>

                                                                <td style="text-align: center;">${darkfactorSpitefulnessTotal}</td>

                                                                <td style="text-align: center;">${darkfactorSpitefulnessPercentile}</td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="SpitefulnessId">
                                                                <td colspan="4">
                                                                    Spitefulness changes how much we have preferences or urges that harm another but that would also entail harm to oneself. This harm could be social, financial, physical, or an inconvenience. How much we have experienced traumas and been wronged by others tends to influence the progression of how spiteful we are toward others. However, there are also innate differences that are widespread between individuals. 
                                                                    <br><br>
                                                                    <strong>Technical Definition: </strong> <i style="font-size: 14px;" class="fa fa-angle-down SpitefulnessTechnicalId" ></i>
                                                                    <br>
                                                            <h class="option-content is-hidden table_row" id="SpitefulnessTechnicalId" colspan="4">
                                                                Example:
                                                                <br>
                                                                “One of the best ways to study spite is to use an ultimatum game paradigm. Imagine yourself in the following scenario: A stranger comes up to you and says that he was just given $20, under the conditions that:
                                                                <br>
                                                                He must share it with someone.
                                                                <br>
                                                                He gets to decide how it is divided, but—
                                                                <br>
                                                                If the person he offers the money to rejects the offer, no one gets anything.
                                                                <br>
                                                                In this ultimatum game, there is no negotiation and no second chance. Let's say the stranger offers you $8 and says he will keep $12. If you are like most people, you will probably take the $8. It’s not an even split, but it’s not too unfair and, after all, it’s free money.
                                                                <br>
                                                                Now imagine that instead of $20, it’s $20,000 and the offer is still $8, with the stranger proposing that he keep $19,992. Do you accept this offer? Probably not, even though you are turning down $8 of free money. In this latter instance, when you reject the offer, you're acting spitefully, suffering a harm to harm the greedy jerk who wanted to keep almost all of the money. Consider the price at which you would have accepted the offer: Would you have kept $100, $500, or only accepted an even split?”
                                                            </h>
                                                            </tr>
                                                            </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="text-align: center;"><span id="SadismLabelId">Sadism</span> <i style="font-size: 14px;" class="fa fa-angle-down SadismId" ></i></td>
                                                                <td style="text-align: center;" >${darkfactorSadismAverage}</td>
                                                                <td style="text-align: center;">${darkfactorSadismTotal}</td>

                                                                <td style="text-align: center;">${darkfactorSadismPercentile}</td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="SadismId">
                                                                <td colspan="4">
                                                                    Few of us are actually very capable of doing harm to others, let alone taking pleasure out of it (in our normal state of mind that is). However, history has shown us that depending on the conditions and traditions of a society, it may be far easier than most of us like to think, for us to take pleasure from such things. It has not been that long in human history since public executions were one of the attended spectacles, even in the western world, which tends to think of itself as generally more progressive. It is important for all of us to acknowledge that we are all human, and as such, we often do not understand the depths of depravity to which any of us, including ourselves, can sink. Part of the reason we use this type of “we” inclusive language throughout is because we are ALL HUMAN. Many of these traits are the general tendencies to behave in certain ways; however, this does not mean any of us are truly incapable of manifesting any given set of behaviors, given the right push and circumstances. 
                                                                    <br>
                                                                    To elaborate, sadism relates specifically to how much pleasure we are generally capable of deriving from the suffering and anguish of others, especially if we are the ones responsible for it. When we tend to skew more towards the side of clinically diagnosable disorders, we tend to think that in general, most people would derive pleasure from harming others. While most people are capable of deriving pleasure from harming others, it tends to take a great deal of doing to make this happen. In the gulags of the Soviet Union and in the Nazi prison camps, relatively normal people were able to be incentivized and conditioned to give into this side of themselves. It is quite the stretch to consider ourselves so different.  Consider how many of us play video games, especially ones where you are given more freedom. If you have played the Grand Theft Auto, Elderscrolls (such as Skyrim, Oblivion, etc.), or any other game that allows you to harm innocent bystanders, you probably have done so and derived some pleasure from this. We can guarantee that not all, or not even necessarily most of those people (who enjoy harming innocents in a videogame) score highly on sadism, but it is telling that so many psychologically normal people can derive enjoyment from it. 
                                                                    <br>
                                                                    When we score very highly on this, we can feel a strong urge, or even need, to harm others for our own pleasure. Though this can be seen as inhuman, we have to understand that most disorders are the consequences of features, not bugs, of the human condition, turned up too high or down too low. Whether or not we act on these inclinations has a great deal to do with how we score elsewhere. Do we acknowledge we have these challenges? Do we have systems in place to prevent us from acting on such desires psychological or otherwise?
                                                                    <br>
                                                                    When we score very low on this, we tend to have a very difficult time imagining how others can derive pleasure from hurting people. We also believe ourselves to be the kinds of people that would never do anything to hurt others, which can lead to a dangerous level of shadow repression (in Jungian terms). What this means is that we may likely have these urges, but we disidentify with them so strongly that we act and believe as though we do not have them, even when presented with evidence to the contrary. This can lead to them being unleashed all at once in ways that can be utterly catastrophic and ruin the rest of our lives. It is far better for us all to acknowledge our individual capacity for doing terrible things.
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>                  
                                                    </div>
                                                </div>
                                                <!-- Dark Factor Calculator Stop -->
                                                <br><br>
                                                <!-- Dark Factor Traits Start -->
                                                <div class="row">
                                                    <h2 style="font-size: 20px; font-weight:normal; ">DARK FACTOR TRAITS</h2>
                                                    <h6 style="font-weight:normal; "></h6>
                                                    <div class="row mt-5">
                                                        <div class="col-12" style="font-size: 15px; font-weight:normal; ">
                                                            All values below are your Population Percentiles from above
                                                        </div>
                                                    </div>
                                                    <div class="col-md-10 scrollable">
                                                        <table class="table table-bordered">
                                                            <thead> 
                                                                <tr >
                                                                    <th style="background-color: #890000;color: #ffffff;font-weight: bolder">Egoism</th>
                                                                    <th style="background-color: #890000;color: #ffffff;font-weight: bolder">Machiavellianism</th>
                                                                    <th style="background-color: #890000;color: #ffffff;font-weight: bolder">Narcissism</th>
                                                                    <th style="background-color: #890000;color: #ffffff;font-weight: bolder">Psychopathy</th>
                                                                    <th style="background-color: #890000;color: #ffffff;font-weight: bolder">Moral Disengagement</th>
                                                                    <th style="background-color: #890000;color: #ffffff;font-weight: bolder">Psychological Entitlement</th>
                                                                    <th style="background-color: #890000;color: #ffffff;font-weight: bolder">Self-Interest</th>
                                                                    <th style="background-color: #890000;color: #ffffff;font-weight: bolder">Spitefulness</th>
                                                                    <th style="background-color: #890000;color: #ffffff;font-weight: bolder">Sadism</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td style="text-align: center;">${darkfactorEgoismPercentile}</td>
                                                                    <td style="text-align: center;">${darkfactorMachiavellianismPercentile}</td>
                                                                    <td style="text-align: center;">${darkfactorNarcissismPercentile}</td>
                                                                    <td style="text-align: center;">${darkfactorPsychopathyPercentile}</td>
                                                                    <td style="text-align: center;">${darkfactorMoraldisengagementPercentile}</td>
                                                                    <td style="text-align: center;">${darkfactorPsychologicalPercentile}</td>
                                                                    <td style="text-align: center;">${darkfactorSelfInterestPercentile}</td>
                                                                    <td style="text-align: center;">${darkfactorSpitefulnessPercentile}</td>
                                                                    <td style="text-align: center;">${darkfactorSadismPercentile}</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>                  
                                                    </div>
                                                </div>

                                                <br><br>
                                                <div class="row">
                                                    <div class="col-md-10 scrollable">
                                                        <table class="table table-bordered">
                                                            <thead> 
                                                                <tr>
                                                                    <th style="background-color: #890000;color: #ffffff;font-weight: bolder">DARK Total</th>
                                                                    <th style="background-color: #890000;color: #ffffff;font-weight: bolder">Dragon Factor</th>
                                                                    <th style="background-color: #890000;color: #ffffff;font-weight: bolder">DARK Leverage Ratio</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td style="text-align: center;">${darkfactorTotal}</td>
                                                                    <td style="text-align: center;">${dragonfactorTotal}</td>
                                                                    <td style="text-align: center;">${darkfactorLeverageRatio}</td>
                                                                </tr>
                                                            </tbody>
                                                        </table> 
                                                        <!-- Description Module for Dark Factor Start -->
                                                        <c:forEach items="${dFactorDescList}" var="desc">
                                                            ${desc}<br>
                                                        </c:forEach><br>
                                                        <!-- Description Module for Dark Factor End-->
                                                        <div class="row mt-5">
                                                            <div class="col-12" style="font-size: 15px; font-weight:normal; ">
                                                                Explanation: Dark Total is the sum of all the percentiles for your scorings on dark traits.
                                                                Dragon Factor is essentially the ability to take advantage of the perks you unlock by
                                                                playing the videogame of life, and to ensure optimal outcome in a situation, often by
                                                                bending chaos to one's own will.
                                                            </div>
                                                        </div>
                                                        <div class="row mt-5">
                                                            <div class="col-12" style="font-size: 15px; font-weight:normal; ">
                                                                Interpreting the results: For full explanations of the definitions of these categories please see the additional sheets sent to you. The
                                                                numbers are in percentile form such that it ranks how you score compared to the rest of the population. A Dragon Factor score lower than
                                                                130 means that you likely to be unable to fully take advantage of situations and are likely to be taken advantage of by other players in the
                                                                game of life. A score of 300 is the minimum recommended score after sufficient Shadow integration, 300-450 seems to be a good place for
                                                                most people to get to so they have teeth, so to speak. Higher numbers can be indicative of genuine psychopathy/sociopathy, or quasipathy
                                                                (a term a friend coined such that you can call upon psychopathic traits but feel no general desire to act in an antisocial manner). The
                                                                DARK Leverage Ratio is a measure of how well you are leveraging the dark side of your personality for positive gain. Due to how it is
                                                                calculated, scores above 1 (which is the target zone) are difficult to get if your scores are oriented suggestive of anti-social tendencies.
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- Dark Factor Traits Stop -->
                                            </div>
                                        </div>

                                    </div>
                                    <!-- Extremism Module Stop -->
                                    <%-------------------------------------------------------------------%>
                                    <!-- Disposition Module Start -->
                                    <div class="panel-body">
                                        <h2 style="text-align: left; color:Black;">DISPOSITION DIMENSION</h2>

                                        <div class="panel-default" >
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-md-9 scrollable">
                                                        <table class="table table-bordered">
                                                            <thead> 
                                                                <tr>
                                                                    <th style="font-weight:bold">Cumulative Results</th>
                                                                    <th style="font-weight:bold">Mean</th>
                                                                    <th style="font-weight:bold">Score</th>
                                                                    <th style="font-weight:bold">Population Percentile</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr class="table_row">
                                                                    <td style="color: black ; font-size: 14px; font-weight:bold; "><label id="WholeheartednessLabelId">Wholeheartedness</label> <i class="fa fa-angle-down WholeheartednessId" ></i></td>
                                                                    <td >${wholeTraitAverage}</td>
                                                                    <td>${wholeTraitTotal}</td>
                                                                    <td>${wholeheartedTraitND}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="WholeheartednessId">
                                                                    <td colspan="4">
                                                                        Wholeheartedness is the capacity to engage in our lives with authenticity, cultivate courage and compassion, and embrace — not in that self-helpy, motivational-seminar way, but really, deeply, profoundly embrace — the imperfections of who we really are. It is really quite heavily related to when we are developing through stage 6 of human development, and it is usually more fully realized in stage 8. When we attempt to live more wholeheartedly, we try to move towards and even truly discover who we are as individuals. We draw firmer boundaries relating to our willingness to compromise on who we are just to satisfy others, while attempting to do this from acceptance and compassion. The more highly we score on this, the more we embody these traits, and the more we tend to have developed into and through stage 6. If we score lower in this, we tend to still be searching for approval outside of ourselves, and/or we are holding onto reasons as to why we are not yet good enough to give ourselves such a level of respect and love.   
                                                                    </td>
                                                                </tr>
                                                                <tr class="table_row">
                                                                    <td ><span  id="SenseOfSelfLabelId">Sense of self</span> <i style="font-size: 14px;" class="fa fa-angle-down SenseOfSelfId" ></i></td>
                                                                    <td>${senseOfSelfMean}</td>
                                                                    <td>${senseOfSelfTotal}</td>
                                                                    <td>${senseOfSelfND}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="SenseOfSelfId">
                                                                    <td colspan="4">
                                                                        Wholehearted Sense of Self relates to how we think and feel about ourselves. To be more specific, our Sense of Self relates to the beliefs, standards, and opinions we hold about ourselves, more than the nature of the individual thoughts we have. It tends to govern the ways in which we think about what others think of us, and it changes how much that affects us. For most of us, what others are thinking or saying about us tends to matter quite a lot. This is a generally useful function for maintaining good social relationships and for a well-functioning society. However, this often runs counter to our ability to give ourselves love, respect, and approval, which in turn limits our ability to be happy. While this has been less of a concern for us as a species and as individuals throughout our historical past, as our world increases in complexity and advancement, we can afford such luxuries as happiness and fulfillment to a greater degree. The higher we are in this, the less hard we are on ourselves for things we cannot help, and the more positive approach we take to the things we can help. As such, it often leads to a different type of strength and resiliency than what tends to be cultivated up to this. We could see it as a strengthening of our spirit.  
                                                                    </td>
                                                                </tr>
                                                                <tr class="table_row">
                                                                    <td ><span  id="ThinkingLabelId">Thinking</span> <i style="font-size: 14px;" class="fa fa-angle-down ThinkingId" ></i></td>
                                                                    <td>${thinkingMean}</td>
                                                                    <td>${thinkingTotal}</td>
                                                                    <td>${thinkingND}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="ThinkingId">
                                                                    <td colspan="4">
                                                                        We all can experience a great degree of difficulty in controlling and conditioning the thoughts we have. For many of us, it may feel as though thoughts “have us” as much as we have them. This is where Wholehearted Thinking comes into play. Much of this can arise relating to the Sense of Self discussed earlier. As humans striving for fulfillment, happiness, and meaning, it is important that we do not just let our minds do whatever they want to do naturally. Most of the thinking and thoughts we do and have are useless, if not harmful, to these previously mentioned desires that we all innately have. The more we examine where these harmful thoughts are coming from, the more we can begin to understand and change why we have them. Further, we can more easily practice discarding them and recognizing them as disingenuous. When we score highly in this, we tend to not need “certainty;” we can “trust in the process” so to speak. Whatever that means for you, it is a necessary, albeit often scary, thing to practice, whether it is trusting in some higher power, the universe, or simply your unconscious/less filtered mind (which is responsible for much of the greatest inventions, discoveries, realizations, philosophies, and works of art we have created as a species). The more highly we score on this, the more we tend to think positive thoughts, and we tend to be better at dealing with negative thoughts/situations in a healthy manner.  We experience and express more gratitude and joy as well. Our creativity is not hindered by our constant need/impulse to compare ourselves and our work to others. 
                                                                        <br>
                                                                        When we are low in these, we think self-deprecating thoughts, have thoughts that stem from our limiting beliefs and past traumas, and we identify more deeply with our thoughts themselves. We have a harder time letting go of negative experiences, including ones that really do not reflect on ourselves. We often do not think of ourselves as creative people, even before trying to be creative and giving it a real shot. Though not all of us can be so creative as to make a living or be remembered for it, it can be an important and fulfilling part of our existences. Creativity does not have to look like what most of us are prone to thinking of it as, at least in order for us to derive enjoyment from it! We can be creative in how we approach solutions in business, in how we structure our routines, in how we care for and play with our kids, etc. Though this might not get us recognized as creative geniuses (which is not the point anyway), it will lead to a richer and more fulfilling life for us and those around us. 
                                                                    </td>
                                                                </tr>
                                                                <tr class="table_row">
                                                                    <td ><span  id="ActionLabelId">Action</span> <i style="font-size: 14px;" class="fa fa-angle-down ActionId" ></i></td>
                                                                    <td>${actionMean}</td>
                                                                    <td>${actionTotal}</td>
                                                                    <td>${actionND}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="ActionId">
                                                                    <td colspan="4">
                                                                        For all of us, leisure time represents an important part of what it means to be human, to be more than simply a cog in the machine or a cell in the macro-organism of the human species. Our attempts to fill our time outside of work or other obligations are where Wholehearted Action comes into full effect. Most of us fill what little free time we have with distractions and often shallow forms of entertainment that do not bring much deeper value, beyond the feelings they momentarily evoke. Social media is dangerous in such a way, as we often consume and scroll and get enough addictive stimulation to stick around and come back for more content. But at the end of the day, we have to decide for ourselves whether this is truly what connection is, and we have to ask whether  or not it is genuinely enriching our lives. When we engage in Wholehearted Action, we let loose and are unconcerned with what others think; we engage in deeply meaningful activities. For many of us, this can be singing, laughing, and dancing. We are unconcerned with how we come off to others, while we still often walk a relatively fine line to not be overtly offensive. Another component of this is how frequently we strive to engage in meaningful work, even if this means changing careers, or working for ourselves (even if we will be making less money). We weigh the quality of life we experience outside of work against the benefits that any given line of work provides.  
                                                                    </td>
                                                                </tr>
                                                                <tr class="table_row">
                                                                    <td style="color: black ; font-size: 14px; font-weight:bold; "><label id="SelftActualizationLabelId">Self Actualization</label> <i class="fa fa-angle-down SelftActualizationId" ></i></td>
                                                                    <td>${selfActualizationMean}</td>
                                                                    <td>${selfActualizationTotal}</td>
                                                                    <td>${selfActualizationND}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="SelftActualizationId">
                                                                    <td colspan="4">
                                                                        Though Self-Actualization is somewhat related to Wholeheartedness, we tend to experience this more in the next stage of development, stage 7. We begin to, at least unconsciously, understand ourselves to be more complex, often seemingly a collection of various desires, values, and attitudes. We balance these to achieve a level of strength, effectiveness, and peace that is not available at other stages. Unfortunately, by the time most of us are old enough or have the necessary experiences, we are at, or past, the point of retirement. The novelty-oriented world we live in does not yet know how to properly reintegrate us into more humanistic roles, to capitalize on such invaluable perspective. In seeing from a higher and more complex perspective, we begin to understand the value and necessity of all the components of the organizations and systems around us. We tend to relate more easily to others through our experiences or even simply through an openness and curiosity in theirs. When we score low on this, we cannot see past our own perspective very easily, if at all. We cannot see how others think and grant them their differences from us. We judge the world around us relative to ourselves, without the understanding that we do not have that deep of an understanding of ourselves (we usually do not even really like ourselves all that much). We are more likely to propose solutions that benefit a few at the expense of others, and we feel good about benefiting our group despite the cost to other groups. 
                                                                        <br>
                                                                        We either lack experience or are blind to what our experiences have told us.    
                                                                    </td>
                                                                </tr>
                                                                <tr class="table_row">
                                                                    <td ><span  id="DepthLabelId">Depth</span> <i style="font-size: 14px;" class="fa fa-angle-down DepthId" ></i></td>
                                                                    <td>${depthMean}</td>
                                                                    <td>${depthTotal}</td>
                                                                    <td>${depthND}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="DepthId">
                                                                    <td colspan="4">
                                                                        What if we are not all equally deep? We often believe that there are aspects to us all that are universal, despite overwhelming evidence to the contrary. Just as we cannot all dunk a basketball, and even among those who can there is a spectrum of excellence, we are different across aspects of ourselves such as depth. Our depth determines how deep of relationships we have with others, as well as ourselves. The depth of the relationship you can have with others is limited by the depth of your actual self. We do not believe in stating feel good statements. With that in mind, we all possess nearly unimaginable potential for depth. What determines how deep we are is how much we have explored and sorted out the deepest depths of ourselves. The deeper we are, the richer our connection with all there is around us, the more transcendently (without necessarily being religious) we experience experience itself. With depth comes a level of comfort with who we truly are at our core. As such, we feel comfortable entertaining new ideas, attitudes, and even values, that might be contradictory or even entirely incompatible with our own. When a good case is made, we shift such aspects of ourselves because we remain less attached to such things.
                                                                        <br>
                                                                        Depth is often the core and starting point in our being for achieving a level of Self-Actualization, which is integral to living a maximally fulfilling life.
                                                                    </td>
                                                                </tr>
                                                                <tr class="table_row">
                                                                    <td ><span  id="AcceptanceLabelId">Acceptance</span> <i style="font-size: 14px;" class="fa fa-angle-down AcceptanceId" ></i></td>
                                                                    <td>${acceptanceMean}</td>
                                                                    <td>${acceptanceTotal}</td>
                                                                    <td>${acceptanceND}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="AcceptanceId">
                                                                    <td colspan="4">
                                                                        Acceptance represents the part of Self-Actualization most similar to aspects of Wholeheartedness. It is simply how willing and able we are to accept without judgement ourselves (our strengths and limitations), accept others (understanding they have their own strengths and limitations), and finally, accept the world around us (for all of its seeming wonders and seeming flaws). We often find this unnecessarily difficult, because we want reasons for why we need to do such things. The counterintuitive thing is that there really are not reasons, or at least there are not really reasons that we will truly understand before we accept such things. 
                                                                        <br>
                                                                        The easiest to understand reason to accept such things is the following:
                                                                        <br>
                                                                        Why we must accept all is because it is whatever it is, and it will be whatever it will be regardless of how much we judge and desire things to be any other way. Only after accepting such things will we end up on the other side of this, able to begin to see things for what they are. We step away from the innately human mechanisms that render virtually all of existence as means to further innately biological dives. We all need to allow space for things to reveal themselves in time, while having experienced that things really do tend to become apparent at the right time…whatever that means. A case can be made that things are a level of perfect that virtually none of us are developed enough to criticize, but that is not useful to go into here. It is, however, useful to entertain the possibility and to look back at our pasts and at history itself to see how this might be the case. Consequently, we find ease in our lives when we move with the natural flow of things, which is a view that can only be understood once we get in the river, instead of criticizing it from the banks.
                                                                        <br>
                                                                        <strong>Only on the other side of this can we possibly possess the wisdom to truly and consciously make things even better, and even that is a reasonably ambitious statement.</strong>
                                                                        <br>
                                                                        When we are unaccepting, we do not examine what it is about ourselves that might have led to us finding ourselves in this position, with this relationship to everything, including ourselves. We close ourselves off to the growth and the signs all around us, showing us the path of growth and betterment. We find flaws in others that are innately related to things we do not like about ourselves. We possess unrealistic expectations of ourselves and others, based on our own thinking and experiences (and of course, genetic predispositions), which have not led us to being well developed/fulfilled people.
                                                                    </td>
                                                                </tr>
                                                                <tr class="table_row">
                                                                    <td ><span  id="ImpactLabelId">Impact</span> <i style="font-size: 14px;" class="fa fa-angle-down ImpactId" ></i></td>
                                                                    <td>${impactMean}</td>
                                                                    <td>${impactTotal}</td>
                                                                    <td>${impactND}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="ImpactId">
                                                                    <td colspan="4">
                                                                        An essential part of being human relates to our desire to make a difference and to contribute to the world around us as well as our desire to contribute to those who inhabit it. Negative feelings fester in all of us when we feel as though there is no place for us to do our part. We unfortunately may expect things in return before we have laid the necessary foundations to become competent enough to do more good than harm. Seeking to make a difference, not for fame, fortune, or anything other than the simple fact that we want to change something for the better, represents something most of us strive towards. In today’s society, more and more people are concerned with change, but seemingly just as few possess the wisdom and detachment from the outcomes necessary to be proper stewards of said change. When we lead change, we face limitations in how others think, feel, and will behave. We face no certainty whatsoever that it will work out, and we understand that “if people would just X (insert way of being), then everything would be better.” Regardless, when we score highly in this, it means that we are attempting to enact change for the sake of whatever aim/cause we are hoping to further. Our self-worth/identity is not tied to how much we succeed or fail, but still we seek to contribute to the greatest degree possible. We seek to utilize our creativeness and gifts, not just for our own fulfillment and advancement, but we enact these gifts to benefit others, with no expectation of it benefitting us.
                                                                        <br>
                                                                        When we are low in this, we feel we are not making a difference. We may not even be on the path to starting to make a difference. The lower we are, the more we do not even care about making a difference, and the more we negatively view those who are pushing for change. We often have many other things we are worrying about and have to take care of, long before we could begin to concern ourselves about change. We must first look inward and change ourselves, so we may be the people who even feel a desire to see change in the world, especially if our motives are to be pure.   
                                                                    </td>
                                                                </tr>
                                                                <tr class="table_row">
                                                                    <td ><span  id="IndividualityLabelId">Individuality</span> <i style="font-size: 14px;" class="fa fa-angle-down IndividualityId" ></i></td>
                                                                    <td>${individualityMean}</td>
                                                                    <td>${individualityTotal}</td>
                                                                    <td>${individualityND}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="IndividualityId">
                                                                    <td colspan="4">
                                                                        In the western world, we are of the belief (or we aspire to be) that we are a society of individuals. We do not concern ourselves with the idea of ‘how much of an individual each person is.’ We choose to operate this way despite the limitations of it, because we are a society that believes in the potential of each of us. It is our responsibility at CODE to deal with the nuances as well as for us to see if we can’t improve upon this a bit. We are individuals to the degree that we understand ourselves, which is a generally terrifying prospect, even if most of us might pretend that it is not. The harsh truth is that many of us are not that much of individuals. We represent the expression of the variety within a category of our species, which is to say that we can often be swapped out for a quite similar person and they would not live our lives all that differently. This works out great for the macro-organism of the human species as a whole, to which we are all a means to an ends to some degree or another. It means that as far as the functions you perform, the whole system will not fall apart if you die, regardless of who you are. We should not take offense to this, as this is even true of the leaders of nations, movements, and massive corporations, as we have unfortunately seen tested on numerous occasions. Yet our functions, analogous to cells in our own bodies, are not where we draw all of our satisfaction and meaning from. 
                                                                        <br>
                                                                        Hence, we find it necessary and meaningful to explore ourselves, to understand our experiences, and importantly, to think of ourselves as far more than merely a means to some ends. To properly become an individual, we must be able to practice a level of detachment, further freeing ourselves from our more biological drives. We must begin to think for ourselves, being fully capable and willing to weigh all sides of things, even the aspects we feel we do not like. When we score more highly in this, we are unconcerned with passing trends, with the fickle opinions of others. We do not care nearly as much about fitting in with others, which is an inherently prey animal behavior. We stand out, not because we have some need to, but for a reason. We are willing to call out what seems wrong or in need of addressing in ourselves, in the world, and in others. We tactfully choose to go against the grain when we feel our lines have been crossed, while we are also much less quick to take offense or be “triggered” over trivialities. 
                                                                    </td>
                                                                </tr>
                                                                <tr class="table_row">
                                                                    <td style="color: black ; font-size: 14px; font-weight:bold; "><label id="UclaLonelinessLabelId">UCLA Loneliness</label> <i class="fa fa-angle-down UclaLonelinessId" ></i></td>
                                                                    <td >${uclaTraitMean}</td>
                                                                    <td>${uclaTotal}</td>
                                                                    <td>${uclaTraitND}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="UclaLonelinessId">
                                                                    <td colspan="4">
                                                                        Loneliness tends to relate very strongly our experience of sadness, particularly from a neurological standpoint. However, it is useful to look at it individually as a strong contributing factor to depression and sadness. Whether we are high in this is one clue as to whether or not we are feeling an unwarranted level of depression, or whether we are very justifiably feeling sad. If we are feeling very sad or depressed because we feel lonely, then medication might help us tolerate that, but it certainly will not get us out of it. Medication (such as SSRI anti-depressants) are much more useful when we are feeling a seemingly undue level of sadness that does not seem to correlate with what we would reasonably be expected to feel, given our circumstances. That being said, at CODE, we have rarely if ever encountered the individual who did not have sufficiently good reason for their level of sadness/depression. More often than not, it took us some digging to find exactly what it was. The relationship between loneliness and sadness plays a role in why it is that the more extraverted among us tend to be happier. Being around other people (particularly when we are having fun) actively engages the PLAY system, that suppresses our SADness system (which is technically our PANIC system). It is the system responsibly for mammals experiencing distress when we are away from our parents, especially as children/young. If we feel lonely, like people cannot relate to us, or anything of that sort, it can trigger that system. We should understand that loneliness is still possible, even if we are surrounded by people, even if those people are great and deeply love/care for us.  
                                                                    </td>
                                                                </tr>
                                                                <c:forEach items="${uclaDataMap}" var="uclaQue">
                                                                    <tr class="table_row">
                                                                        <td>${uclaQue.key}</td>
                                                                        <td>${uclaQue.value['ques'].currentMean}</td> 
                                                                        <td>${uclaQue.value['ques'].score}</td>   
                                                                        <td>${uclaQue.value['ques'].normalDistribution}</td>   
                                                                    </tr>
                                                                </c:forEach>
                                                                <tr class="table_row">
                                                                    <td style="color: black ; font-size: 14px; font-weight:bold; "><label id="SelfEsteemLabelId">Self-Esteem</label> <i class="fa fa-angle-down SelfEsteemId" ></i></td>
                                                                    <td>${rsesTraitMean}</td>
                                                                    <td>${rsesTotal}</td>
                                                                    <td>${rsesTraitND}</td>
                                                                </tr>
                                                                <tr class="option-content is-hidden table_row" id="SelfEsteemId">
                                                                    <td colspan="4">
                                                                        We likely have all heard of Self-Esteem and the supposed importance of it, but what is the truth of the matter? Is it as simple as high Self-Esteem being what we should all seek? No, as humans we are supposed to be different across a wide range of aspects of ourselves and Self-Esteem is no different. The key with our Self-Esteem is that it should be proportional to how we are doing, relative to everyone else (or at least whoever is the best frame of comparison). When we are young and mostly still potential, it is probably not a good thing to have a higher overall level of Self-Esteem than older more accomplished people, who have proven themselves to themselves and others. It is difficult for us to generally increase Self-Esteem, the way we try to test for it. Instead we tend to increase Narcissism. When our Narcissism score is much higher (usually more than 15-20%) than our Self-Esteem, it means we have created a false (fabricated) sense of how special we are, which tends to be a recipe for disaster. While Self-Esteem has been stressed as valuable for children, the understanding and way we have gone about this has created some problems, relating to the complexity of it overall. 
                                                                        <br>
                                                                        When we are higher in Self-Esteem, we are more likely to feel worthy and go after the goals and things we desire, especially to do so more vigorously. We are more likely to step up and take responsibility as a leader in whatever it is that we are good/competent at. We generally feel better and how we walk, talk, and think reflects a greater feeling of confidence in ourselves. The more stable this is, the more we can face challenges and threats to ourselves without it shaking our Self-Esteem. The more we develop, the less we begin to require proof for how much Self-Esteem we should have. This is to say, we start to regard who we are as what is deserving of esteem, as opposed to what we can do.
                                                                        <br>
                                                                        When we are low in Self-Esteem, we often experience other related negative emotions more severely. We feel less worthy of the things we are even obviously worthy of (though these things are usually less obvious than they seem). We pursue our goals much less intensely. We have a hard time accepting praise, and we generally do not believe or properly receive the praise we do get. We see most new challenges as threats to our already lower level of Self-Esteem, as opposed to opportunities to prove ourselves to ourselves and others. We are more likely to be shy, seemingly introverted, prefer/allow others to take the lead, and be less likely to speak up about something that bothers us. 
                                                                        <br>
                                                                        An important point on this is that when we are depressed, or if we are feeling like we are relatively pathetic, and our lot in life reflects that, it is important to cling to the core idea that virtually all humans have the potential to change and grow out of whatever unfavorable situation they find themselves in (we say virtually because we avoid unfounded global statements). This is a necessary level of confidence in our ability to grow and dig ourselves out. Without this, we continue to experience whatever level of misery we are at, barring some serious external intervention.
                                                                        <br>
                                                                        So, what is Self-Esteem? Self-Esteem is what determines how we feel about ourselves relative to others. It comprises how much we think we are good at the things we take pride in, as well as how much we like ourselves. This is the difference between Self-Competence and Self-Liking. 
                                                                        <br>
                                                                        Self-Esteem is heavily intertwined with, and derives in part from, many of the other traits and aspects. More about this will be discussed in the technical section.
                                                                        <br>
                                                                        <strong>*A NOTE from CODE:</strong> We here at CODE feel that most people should have higher levels of general Self-Esteem, as very few, if any, of us understand the nature of how much potential we possess as human beings. That being said, how we relate to this elevated Self-Esteem, stemming from our potential, must be considered and healthy, lest it lead to arrogance and simply narcissism.
                                                                        <br>
                                                                        <strong>-Advanced note:</strong> There should be a floor on how low one can be in Self-Esteem and its subcomponents.
                                                                        <br>
                                                                        Where did the Self-Esteem kick come from? 
                                                                        <br>
                                                                        Without regard to various popular psychology influences, from a psychological analysis of parents, we could suggest that it came from many parents and psychologists reaching some level of stage 6 of development. They would have attained a level of success, respect, and comfort with themselves (best described as self-acceptance), that they then believed should be imparted to their children. This stage is also more egalitarian, which is why likely the same parents would be pushing for no trophies and no keeping score in games. Regardless of whether you were, or are, one of these people, please understand that existence keeps score, and you personally keep score unconsciously. This is part of the function of the serotonin system. We only get to the point where we can feel this way as a consequence of many years of hard work to play well and do well within the parameters of society. You may not be keeping score anymore, but that is because as far as your neurology (brain) and society is concerned, for the most part, you have already won! * 
                                                                        <br>
                                                                        *(Which is great!) 
                                                                        <br><br>
                                                                        <strong>Technical Defination:</strong> <i style="font-size: 14px;" class="fa fa-angle-down SelfEsteemTechnicalId" ></i>
                                                                        <br>
                                                            <h class="option-content is-hidden table_row" id="SelfEsteemTechnicalId" colspan="3">
                                                                Self-competence is the instrumental feature of the self as causal agent, the sense that one is confident, capable, and efficacious. Self-liking is the intrinsic feature of the self as a social object, the sense that one is a good person, is socially relevant, and contributes to group harmony. Tafarodi and his colleagues argue that there is an inherent tradeoff between these competing components of global self-esteem. In individualistic cultures (such as the United States), self-confidence, independence, and the priority of the instrumental self, take precedence over group harmony, resulting in higher levels of self-competence but lower levels of self-liking. In collectivistic cultures (such as China), the individual needs for self-confidence and efficacy are subordinated to the social needs of others, resulting in overall higher self-liking but lower self-competence.  
                                                            </h>
                                                            </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td ><span  id="SelfLikingLabelId">Self-Liking</span> <i style="font-size: 14px;" class="fa fa-angle-down SelfLikingId" ></i></td>
                                                                <td>${slAspectMean}</td>
                                                                <td>${slAspectTotal}</td>
                                                                <td>${slAspectND}</td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="SelfLikingId">
                                                                <td colspan="4">
                                                                    Self-Liking is the component of Self-Esteem responsible for how much we feel we like ourselves. Though this may seem somewhat obvious, it is not. We often do not conventionally consider ourselves to be people in the way we think of others. So, we do not always think about how much we ‘like’ ourselves. As it turns out, we often do not like ourselves as much as we might think. We also are prone to taking actions that suggest to ourselves that we do not like ourselves as much as we might otherwise think. This is somewhat less apparent in this section, as people do tend to score more highly on it, but it can be evident. When we think about ourselves, we should consider how much we like who we are. Are we someone we would want to be friends with? Are we someone whom we could depend upon? Would we enjoy being around ourselves? Although we often have friends who are sufficiently different in certain ways to complement how we are, this is still necessary to consider. This also relates to how satisfied we are with ourselves (<strong>note:</strong> this is different from how satisfied we are with our lives). It also relates to how much we take a positive attitude toward ourselves, such that we are never too hard on ourselves. This is also that part of Self-Esteem most closely related to how much we respect ourselves. This can be messier when we think about Self-Competence as well, but the difference is that self-respect under Self-Liking relates to a level of unconditional self-respect, (similar to the idea of intrinsic dignity), while under Self-Competence we often need some form of external proof.    
                                                                </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td ><span  id="SelfCompetenceLabelId">Self-Competence</span> <i style="font-size: 14px;" class="fa fa-angle-down SelfCompetenceId" ></i></td>
                                                                <td>${scAspectMean}</td>
                                                                <td>${scAspectTotal}</td>
                                                                <td>${scAspectND}</td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="SelfCompetenceId">
                                                                <td colspan="4">
                                                                    We all have various things we enjoy doing, have to do, and want to start exploring. Some of these are skills that can easily be viewed as contributing to humanity overall (usually through our jobs). These are generally the most relevant to Self-Competence. It is natural for us to derive esteem from our ability to contribute (though this is a bit more prevalent long term for men, in strictly a labor sense). It is one of the hallmarks of a healthy person to derive pleasure from contributing to the wellbeing of others, as well as the world around us.
                                                                    <br>
                                                                    What is Self-Competence? Self-Competence is the degree to which we feel positively about ourselves because of what we have done and can do. Usually, the latter is a more positive position to take. We can use our past experiences with success and failure (and how we dealt with such failures) to understand what we can do. We can also use it along with our general level (and understanding of that level) of skillfulness at becoming better at things, to take a healthier perspective on our excellence. When we rely on only what we have done in the past for what we feel we are capable of in the present, as well as how much Self-Competence we have, we tend to have a much more fragile sense of Self-Esteem as a whole.  When we face new challenges, we are more likely to see them as threats. We are also less likely to tackle things that seem a fair bit beyond what we have done in the past, since we do not have prior proof of our ability to do it. This way of thinking and being tends to be a bit more prevalent in more conservatively minded people, while basing our Self-Competence more on our potential skews toward a more liberal mindset.
                                                                    <br>
                                                                    When we are higher in Self-Competence, we see ourselves as quite capable and able to contribute in whatever ways we deem necessary. We are more likely to take charge, or voice our input where we feel it would be valuable, and we may voice our input when we do not have the necessary level of information or understanding to be useful, simply because we feel we are generally competent. We are more likely to be in higher positions in whatever our line of work might be because we have achieved more than others. To a degree, if we started with a higher (but not harmfully high) level of Self-Competence, we are more likely to take the risks and responsibilities that lead to us advancing faster than others. We are more likely to be off-putting to those who are more average in this regard, and especially to those who are lower. If we are intelligent, we can be looked down upon by other intelligent, but less confident people, because they do not understand how we see ourselves as having such a level of competence. It is important to understand that the nature of what makes us like this at our core, is a willingness to charge into the uncertainty and figure it out, as opposed to having figured everything out beforehand. Even when we have felt we have figured things out beforehand, once we are in the thick of it, we almost always find out that there was much more to understand. The key here is that there are variables and challenges that only reveal themselves to us once we have begun to grapple with the challenges themselves. Such is the nature of what is commonly considered the “unknown unknowns.”
                                                                    <br>
                                                                    When we are lower in Self-Competence, we feel we are less capable than others, even if we are actually more capable than them or better suited to the task at hand. We often feel like we cannot contribute, and we do not, or maybe even cannot, come to possess the skills and expertise necessary to do what we desire. This can stem from a variety of places. Sometimes due to our pasts and life history, we experience a higher level of negative emotion, which leads us to see ourselves as incompetent. Other times, we may simply be stuck overanalyzing the challenge/task, even to the point where we feel that nobody could actually be up to it. These ways of being are dangerous for us all, as they lead to self-destructive feedback loops and can heavily negatively influence our lives. When we are lower in Self-Competence, we end up with lower quality romantic partners, especially as men but also for women. We experience much fewer instances of accomplishment, and we experience even these instances with lesser intensity than those with higher Self-Competence. 

                                                                </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td style="color: black ; font-size: 14px; font-weight:bold; "><label id="SatisfactionWithLifeLabelId">Satisfaction With Life</label> <i class="fa fa-angle-down SatisfactionWithLifeId" ></i></td>
                                                                <td>${swlsTraitMean}</td>
                                                                <td>${swlsTotal}</td>
                                                                <td>${swlsTraitND}</td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="SatisfactionWithLifeId">
                                                                <td colspan="4">
                                                                    Satisfaction With Life (SWL) helps us understand how much we feel our lives are going the ways we desire them to go. Though SWL can be seen as playing a role in dictating some of our behaviors, it tends to be more a result of how we feel about the results of our behaviors (the ways in which we are living our lives) as well as the consequences of them. We all have our own individual ideas of what a good life looks like overall, as well as what we would like our momentary experience to be like in the present. How we draw a relationship between these plays a key role in our level of SWL. We can characterize what stage of development people are coming from largely by looking at what metrics they are using to determine this. Our level of satisfaction is also mediated by how much we are carrying a sense of regret with us. If we feel there are many things that we would change about how we have lived and are living our lives, we tend to feel much less satisfied. However, this often means that we have yet to derive sufficient meaning and lessons from such ways of being. We are not here to tell you that “everything happens for a reason.” We can say that it has been found through extensive research on people, especially the elderly, that those of us who have lived looking for why things happened the way they did experience much more meaning, satisfaction, general mental healthiness, and growth throughout our lives, than those of us who tend to view otherwise. Even if we attribute it purely to causality, we can look for why things happened, and we can often address issues within ourselves or the world as a result. 
                                                                    <br>
                                                                    This score should tend to go up over time, as we make progress in our lives or as we move toward a more stable existence (depending on which we are seeking). We may experience dips in this when we are going through oftentimes necessary and worthwhile hardships, but we tend to come out on the other side with another bump in our level of Satisfaction With Life. 
                                                                    <br>
                                                                    Relatively higher scores would indicate that we perceive areas of our lives that we consider important to be going well. When we score highly, we feel more at peace within ourselves as well is in our relationships with others and the world around us. We may feel we are being challenged to an optimal degree, and we are maximally equipped to face these challenges. We feel our life is meaningful, enjoyable, and overall, quite worth living.
                                                                    <br>
                                                                    When we score lower, it often points to things not going well for us in the moment. We often are suffering through hardships, losses, or challenges that seem overwhelming. We feel trapped and as though we do not want to be living our lives. Extremely low scores can suggest that we are depressed to the point of being suicidal, as we think and feel our life is not worth living at the moment. If we are not taking a more expansive view of our current circumstances and feelings, we feel hopeless. We feel there is no way out and whatever lies before us is insurmountable. 
                                                                    <br>
                                                                    <Strong>IMPORTANT FOR LOW SCORERS:</Strong> We all need to understand that the feelings stated above are virtually never true (as hard as this is to accept in this state); we simply need to take things one step at a time, one day at a time. We have to believe/understand that many others are feeling this way, and many more have found themselves in such positions. Most of these people have taken it one day at a time to get themselves out of the situation, and it has worked! Despite most of us having had experienced some devastating hardship at some point(s) in our lives, the rate of suicide is very low. This means the vast majority of us eventually overcame and got through such hardship.
                                                                    <br><br>
                                                                    <Strong> NOTE:</Strong>It is important to be clear that this is not to say that if you were who you are now, you would not like to make different choices with hindsight, but these are not at all the same thing. Even so, if we take the idea of the ripple/butterfly effect seriously (which, to some degree, we should), we should be quite careful with how willing we are to criticize our own pasts. It practically inevitably tends to be the case that the more we come to understand, the more we understand the necessity of things to unfold in such a way. 

                                                                </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td style="color: black ; font-size: 14px; font-weight:bold; "><label id="BeckDepressionInventoryLabelId">Beck Depression Inventory</label> <i class="fa fa-angle-down BeckDepressionInventoryId" ></i></td>
                                                                <td></td>
                                                                <td></td>
                                                                <td></td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="BeckDepressionInventoryId">
                                                                <td colspan="4">
                                                                    Unfortunately, many of us have a slight level of depression that is going unacknowledged and untreated, because we often see it as normal, and/or we often have resistance for seeking “help.” 
                                                                    <br>
                                                                    We are not fans of the word “help” because all of us at CODE have had some relationship to depression, so we have an intimate understanding of it. A more appropriate way to look at it is that we think of it as working with someone collaboratively, so we seek to optimize ourselves through the means available to us through others. Some level of depression is natural, as life itself is hard, and even if we are to believe that everything is perfect, we have every reason from a biological and survival standpoint to not experience it as such. Having depression, in any form, does not mean that there is something wrong with us, and we need others to “help” us “fix” whatever it is. Unless your life is perfect (which is beyond our ability to truly conceptualize), it is natural to feel some level of depression. Oftentimes when we are being treated for depression, we simply find ourselves in terrible circumstances, made worse if they are out of our control. Depression is the natural response to this, and it should not be made to go away, which it almost never will. It will really only ever go away when we are “out of the woods,” so to speak. A further note regarding the Stigma related to this and other mental health/wellbeing topics is the relatively older, but still persistent idea of not wanting a “shrink poking around in my head.” To that we give the following analogy: we hire accountants to do our taxes, lawyers to defend us in court, mechanics to repair and maintain our cars, yet we think that without any training, we are all properly equipped to maintain the most complex aspect of our lives, our very consciousness (minds). Although many people dealing with the mind (coaches, therapists, counselors, psychologists, and psychiatrists), are not as good at their jobs as some of us would like, or is even often required, to deal with the complexity of ourselves, this should not dissuade us from seeking out ideal individuals/means to maintain and fine tune these aspects of ourselves.
                                                                    <br>
                                                                    <Strong>Ultimately, we should not see it as getting help or trying to be fixed but as our working with another to explore ourselves more deeply, so we may become who we desire to be.</Strong>


                                                                    <br>
                                                                    <strong>The Nature of Depression is twofold:</strong>
                                                                    <br>
                                                                    It is our awareness and experience of the implications of an <u>appropriate</u> appraisal of the negative nature of our present and prospective future realities, and our experiences thereof.
                                                                    <br>
                                                                    It is our awareness and experience of the implications of an <u>inappropriate</u> appraisal of the negative nature of our present and prospective future realities, and our experiences thereof, generally considered incompatible with how most people would feel in such circumstances.
                                                                    <br>
                                                                    Such an understanding allows us to see why depression stems from a variety of possible sources/causes, and it has a variety of technical classifications.
                                                                    <br>
                                                                    From our experience and review of the psychological research literature, we find that the former of these two possibilities is more often the one at play. The psychological community tends to view this as being/feeling “depressed,” though it is rarely as clear cut. It is further, rarer that psychological professionals understand the difference and attempt to treat them differently. This is understandable, since even with testing methods, individuals vary greatly. It is more difficult to treat an individual who is experiencing depression for what we could consider to be externally valid reasons (reasons that most people would agree warrant feeling depressed). This is because it typically means creating and executing strategies to move the person out of their present circumstances, which can take a fair bit of time. Finally, people who seem to have little reason to be feeling depressed who are being treated for some depressive disorder often times have a less than obvious reason for feeling that way and the burden is on the psychological professional to dig deep enough to figure out why this might be.
                                                                    <br>
                                                                    The technical classifications are available below, along with other “psychological disorders” that include depression as a component. Many of us who have been diagnosed with a Depressive Disorder do not realize that we may have something else that includes depression.   
                                                                </td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td>BDI Extended Population Percentile</td>
                                                                <td>${BDIND}</td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td>BDI Extended Composite Total</td>
                                                                <td>${BDITotal}</td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td>BDI Extended Composite Average</td>
                                                                <td>${BDIAverage}</td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td>Depression Reference Scoring</td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td>Levels of Depression</td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td>1-10 These ups and downs are considered normal</td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td>11-16 Mild mood disturbance</td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td>17-20 Borderline clinical depression</td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td>21-30 Moderate depression</td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td>31-40 Severe depression</td>
                                                            </tr>
                                                            <tr class="table_row">
                                                                <td>over 40 Extreme depression</td>
                                                            </tr>

                                                            </tbody>
                                                        </table>

                                                        <!-- Description Module for Disposition Start -->
                                                        <%--<c:forEach items="${dispositionDescList}" var="desc">
                                                            ${desc}<br>
                                                           </c:forEach><br>--%>
                                                        <!-- Description Module for Disposition End-->
                                                    </div>
                                                </div>
                                            </div>
                                            </br>
                                            <div class="row">
                                                <div class="col-md-9 scrollable">
                                                    <table class="table table-bordered">
                                                        <thead> 
                                                            <tr>
                                                                <th></th>
                                                                <th style="font-weight:bold;text-align: center;">Mean</th>
                                                                <th style="font-weight:bold;text-align: center;">Score</th>
                                                                <th style="font-weight:bold;text-align: center;">Population Percentile</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr class="table_row">
                                                                <td ><span  id="ConnectionLabelId">Connection</span> <i style="font-size: 14px;" class="fa fa-angle-down ConnectionId" ></i></td>
                                                                <td style="text-align: center;">${connectionMean}</td>
                                                                <td style="text-align: center;">${connectionTotal}</td>
                                                                <td style="text-align: center;">${connectionND}</td>
                                                            </tr>
                                                            <tr class="option-content is-hidden table_row" id="ConnectionId">
                                                                <td colspan="4">
                                                                    We rarely talk all that much about Connection, what it is and why it matters, but of the traits and various aspects of ourselves and our experiences, it is hard to point to anything more foundational. Connection is our experience of feeling and thinking of ourselves as a part of something beyond ourselves. We can experience so many different forms of Connection, often times controlled by the other traits of ourselves, but it is the stage of development we are coming from that most heavily influences how we experience Connection. So that we are naturally motivated to not act purely in self-interest against the interests of others (a proven bad strategy), it is important to experience ourselves as part of a greater whole. When we are coming from the even numbers of development, we tend to experience ourselves and operate more as a member of whatever it is that we are connected with, as opposed to an individual. We give up elements of our individual identities and needs/desires to identify with the group more heavily. This leads us to seeing the betterment of the group as the betterment of ourselves, leading us to act most prominently in ways that we see as most heavily benefiting the group as a whole. When we come from the odd stages of development, we strive to stand out from the group, to build a name, an identity, and a true sense of individuality, for ourselves. Where we band together with others, it is more as a means of maximizing how well we will be able to win/attain things such as power, glory, fame, fortune, and other such means of distinguishing ourselves. 
                                                                    <br>
                                                                    Various external and internal factors mediate (control) our experience of Connection. The more and the higher the quality of friends, family, romantic partner, and children we have, the more powerfully we feel connected to them, and we stand to feel a deeper, more profound Connection beyond them, through them. We further feel connected to, or even identify with, our community, our state, our nation, or even our species. There is no limit to this, as we can even identify with the very universe itself (and beyond) and everything in it. The depth and nature of our identification with such things stems from how high the quality of such groups is, as well as how much their characteristics are similar/compatible with ours, and ultimately how overtly compelling they are. To clarify the last point, a high quality, compatible group might be less identified with than one that is more dogmatic and/or charismatic. 
                                                                    <br>
                                                                    When we feel extremely connected (and our scores reflect that), we possess a capacity to experience a level identification, that can even become an experience of oneness with whatever it may be that we are presently experiencing a connection with. This experience may shift as our interactions redirect the flow of our experience of such oneness from our romantic partner, to our species, to ourselves, to all life. Much of the purpose and value of spirituality stems from its value as an approach to experiencing and understanding a greater level of Connection to things beyond ourselves, as well as to things that are less easily defined. We can even experience such connection and individuality simultaneously, though it takes practice and discipline. A further characteristic of our experience when we score highly is that we experience a much greater feeling of meaning in our actions, our thoughts, and our feelings. We derive inspiration and serenity, as well as comfort, from our experiences of connection, beauty, and purposefulness. 
                                                                    <br>
                                                                    When we score low in Connection, it means we do not have many great relationships that we feel are deep and meaningful. We either simply do not or cannot invest time in our relationships. We do not feel as though we are a member of a larger group that elevates who we are. This is heavily related to our experience of negative emotion (Neuroticism), Depression, Loneliness, and Sadness. One of the major functions of our Panic/Sadness system (and those directly relating to it) is to create a feeling of positive experience when we feel connected to others, as well as (and primarily) to register when we do not have a significant enough level of Connection. When we feel alone, we experience sadness for this reason. Being low in Connection means we lack companionship, and we lack people who are there to look out for us, if need be. Through our experience of isolation from others and the potential groups that we might identify with, our ability to experience connection to all that lies beyond such conventional things is limited. This means we experience less of a feeling of oneness, beauty, purposefulness, and general love of others, ourselves, and existence itself. 
                                                                    <br>
                                                                    As complexly conscious beings, we have an understanding that our lives are extended in time, such that we experience a sense of Connection with our future selves. If our future self is threatened, or if aspects of our Connection to the idea we have of that future self are threatened, we experience sadness and distress. If our prospects and/or outlook for Connection for that future self are threatened, we will experience a level of sadness as well.    
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-9 scrollable">
                                                    <table class="table table-bordered">
                                                        <thead> 
                                                            <tr>
                                                                <th style="font-weight:bold">Tribe Satisfaction</th>
                                                                <th style="font-weight:bold;text-align: center;">Mean</th>
                                                                <th style="font-weight:bold;text-align: center;">Score</th>
                                                                <th style="font-weight:bold;text-align: center;">Population Percentile</th>
                                                            </tr>
                                                            </tr>                                        
                                                        </thead>
                                                        <c:forEach items="${tsDimentionData}" var="tribeQue">
                                                            <c:if test="${tribeQue.questionResponses.question.questionTrait.traitId==35 && (tribeQue.questionResponses.question.questionId==84 || tribeQue.questionResponses.question.questionId==93)}">
                                                                <tr class="table_row">
                                                                    <td>${tribeQue.questionResponses.question.questionName}</td>
                                                                    <td></td>
                                                                    <td>${tribeQue.questionResponses.questionOptionValue}${tribeQue.questionResponses.questionOptionAdditionalValue}</td>
                                                                    <td></td>
                                                                </tr>
                                                            </c:if>
                                                        </c:forEach> 
                                                        <c:forEach items="${questionWiseNDList}" var="tribeTraitId">
                                                            <c:if test="${tribeTraitId.key==85 || tribeTraitId.key==86 || tribeTraitId.key==87|| tribeTraitId.key==88|| tribeTraitId.key==89|| tribeTraitId.key==90|| tribeTraitId.key==91|| tribeTraitId.key==92}">
                                                                <tr class="table_row">
                                                                    <td>${tribeTraitId.value['ques'].name}</td> 
                                                                    <td>${tribeTraitId.value['ques'].currentMean}</td> 
                                                                    <td>${tribeTraitId.value['ques'].score}</td>   
                                                                    <td>${tribeTraitId.value['ques'].normalDistribution}</td>     
                                                                </tr>
                                                            </c:if>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            </br>
                                            <div class="row">
                                                <div class="col-md-9 scrollable">
                                                    <table class="table table-bordered">
                                                        <thead> 
                                                            <tr>
                                                                <th style="font-weight:bold">Co-Op Section</th>
                                                                <th style="font-weight:bold;text-align: center;">Mean</th>
                                                                <th style="font-weight:bold;text-align: center;">Score</th>
                                                                <th style="font-weight:bold;text-align: center;">Population Percentile</th>
                                                            </tr>    

                                                        </thead>
                                                        <tr class="table_row">
                                                            <td ><span  id="CoOpScoreLabelId">Co-Op Score</span> <i style="font-size: 14px;" class="fa fa-angle-down CoOpScoreId" ></i></td>
                                                            <td>${coOpScoreMean}</td> 
                                                            <td>${coOpScoreTotal}</td>   
                                                            <td>${coOpScoreND}</td> 
                                                        </tr>
                                                        <tr class="option-content is-hidden table_row" id="CoOpScoreId">
                                                            <td colspan="4">
                                                                Co-Op Score, not dissimilar to a what it means to play a Co-Op video game, is how we well we are living life, with others. While most of us have friends, very few of us have worked on ourselves, grown, and become people who truly have a “tribe.” When we say this, we are talking about a small group of people who are very close to us, who have a deep and intimate understanding of us, as we have a deep and intimate understanding of them. When we interact with members of this tribe we are talking about, we do not need to pretend, to posture, to placate, or to do anything other than be our most authentic, genuine selves. We hold each other to the standard of being maximally genuine, open, and vulnerable. We explore what that means to us individually, as well as to us as a tribe. We grow, laugh, cry, love, and experience all that existence has to offer TOGETHER. To get to a point where this is truly possible, we must sacrifice and endeavor to grow and become more than we are. Often, we must become much more than we imagine we even could be. We must be willing to see those who we have been close with (who are not doing such things) as our friends, as we reserve seeing those with whom we share such a bond as brothers and sisters. 
                                                                <br>
                                                                When we are high on Co-Op, we feel accepted, loved, understood, appreciated, valued, held in esteem and high regard, not necessarily for any reason other than who we are. Ultimately, we feel SEEN, and we feel all that flows forth from that. Though most of us experience these feelings from their parents, extended family, romantic partner, and children, we know on some level that there is a level of obligation on their part to feel those ways toward us and to give us those things. It is another thing entirely to receive it from people for whom it is entirely optional. As such, it is just as powerful for us to give it to people toward whom we have no such obligation. This feeling of being SEEN is like the wind at our backs and under our wings, as the limitations stemming from our own misperceptions about ourselves, as well as those of others, are blown away. 
                                                                <br>
                                                                When we score low, we feel as though we do not have high quality relationships. We feel as though the people that we have relationships with do not understand us, who we truly are, and who we desire to become. We feel insufficiently supported, and we often feel as though we are really on our own. We further experience more sadness, loneliness (even if we have friends and loved ones), and we experience much less overall satisfaction with ourselves and life. If we see ourselves as quite different from the people around us, we feel out of place and as though we do not belong. We often come to think of ourselves as introverts when we are simply selective extroverts. We are unaccepting of others’ praise, if it really even registers. We do not derive sufficient esteem from those around us praising or congratulating us. We seek those who will truly understand us to see us for our excellence; only then will we allow ourselves to fully see it for ourselves. This is to say that praise from those who lack the necessary perspective or experience in a given field will mean very little to us, especially compared to those who have earned the right to be taken seriously in the field.
                                                                <br><br>
                                                                <strong>NOTE:</strong> This is an inherently difficult prospect, as we are all very different, and the more unique we are in any way, the more spread out people who are like us are. At CODE, with our capacities for analysis, this has been, and will continue to be, one of our primary focuses. We firmly see the vast majority of humans as starving for more and deeper connection, while such desires are attempting to be met with the insufficient means of the present social mediums.    
                                                            </td>
                                                        </tr>
                                                        <tr class="table_row">
                                                            <td>Romantic Relationship</td> 
                                                            <td>${romanticRelationShipMean}</td> 
                                                            <td>${romanticRelationShipAspectTotal}</td>   
                                                            <td>${romanticRelationShipAspectND}</td> 
                                                        </tr>
                                                        <c:forEach items="${questionWiseNDList}" var="coOpQueId">
                                                            <c:if test="${coOpQueId.key==94 || coOpQueId.key==95 || coOpQueId.key==96|| coOpQueId.key==97|| coOpQueId.key==98|| coOpQueId.key==99|| coOpQueId.key==100|| coOpQueId.key==101|| coOpQueId.key==102|| coOpQueId.key==103|| coOpQueId.key==581}">
                                                                <tr class="table_row">
                                                                    <td>${coOpQueId.value['ques'].name}</td> 
                                                                    <td>${coOpQueId.value['ques'].currentMean}</td> 
                                                                    <td>${coOpQueId.value['ques'].score}</td>   
                                                                    <td>${coOpQueId.value['ques'].normalDistribution}</td> 
                                                                </tr>
                                                            </c:if>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <!-- Disposition Module Stop -->
                                            <!-- Behaviour Module Start -->
                                            <div class="panel-body">
                                                <h2 style="text-align: left; color:Black;">BEHAVIOUR DIMENSION</h2>
                                                <div class="panel-default">
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <h4><span id="MindLikertSumLabelId">Mind Likert Sum</span> <i class="fa fa-angle-down MindLikertSumId" ></i></h4>
                                                            <h  class="option-content is-hidden table_row" id="MindLikertSumId">
                                                                We have discussed how we all have potential for growth, just as we all have grown, or will grow, into adults. There is no reason why this growth should not continue into adulthood. Though many of the aspects of ourselves that play a role in this growth are somewhat difficult to control, there is one that we have a tremendous amount of control over. This is specifically how much we work on ourselves, in this case, our minds in particular. This can be partially determined by how much we view ourselves (and others) as possessing the capacity to change (a growth mindset) versus how much we view ourselves and others as unable to change (a fixed mindset). These operate as self-fulfilling prophecies in large part. The actions we take, the people we surround ourselves with, and the thoughts we think reflect how much we lean in one direct or the other. The more we work on our minds, the more we grow, and come to believe in the possibility of greater change. 
                                                                <br>
                                                                The graph shows how we are spending our time when it comes to improving our minds. We all benefit from diversifying how we are working on our minds. Even if we are the type of person who dives deep into topics, consumes a great deal of information, meditates, etc., we still heavily benefit from talking to others (especially in person, over the phone, or on video). There is hardly a greater way to test and improve our knowledge and understanding than testing it against a fellow competent individual, who has done their own work and learning. We further derive value from getting out there and testing what we think we understand in the real world. The world reveals hidden variables and hidden Truths, that would otherwise be impossible to account for in our purely mental exploration of matters.
                                                                <br>
                                                                Those of us who score lower in this view the world through a fixed, and generally more ego-centric lens. To clarify, when we score lower, we tend to think of others in terms that are more black and white, and often as mistakenly similar to ourselves, in ways that are dramatically incorrect. Without exploring and overcoming our own limitations for the sake of our own growth and betterment, we are terribly unaware of how much “better” (in any given sense of the word) others are than us. This can be at a specific thing or in a generalized fashion. Without seeing clearly how much better we can be than we currently are, especially as adults, we tend to conceptualize others in terms that are more proximal to how we are, or we think of others as so vastly different that it is not worth us trying to improve ourselves. Almost paradoxically, we fantasize about how much better it might be to be in someone else’s shoes, while we possess little to no understanding of the hard choices they have made to get to that place. We neglect to understand the pressure, the responsibility, and ultimately, the limitations on such a person’s freedom as a consequence of the circumstances of their life. This is a tale that has been explored through many fictitious works, from plays, to books, to movies. If you seek the lifestyle of another, it would be worth seeking out such works and seeing what lessons you can derive from them.
                                                                <br>
                                                                For those of us who score higher, we see the world as ever changing, ever growing, and we view ourselves and others with the same potential. We can have a more difficult time understanding why others view the world and themselves as static, and it is our hope here to help clarify this. We often are fortunate in our upbringings and our genetic predispositions; this is not to say “privileged” in the modern sense of the term. It is nonetheless necessary to understand that such differences exist. We explore and push the boundaries of what we (and usually others) consider possible of ourselves, so we may eventually push the boundaries of what is thought possible of the world around us. We admire those who show their growth; we are more accepting of the missteps along the way of others, as we hope they will be of us. We see failure as an opportunity to learn and push ourselves further. We view success and victory as temporary, and as generally valuable indicators that we are moving in the right directions. We do not rest on our laurels, as we are generally the people who are hardest on ourselves.  
                                                            </h>
                                                            <h6>score: ${mindTotal}</h6>
                                                            <h6>Population Percentile: ${mindNd}</h6>

                                                            <h4><span id="MasterySumLabelId">Mastery Sum</span> <i class="fa fa-angle-down MasterySumId" ></i></h4>
                                                            <h class="option-content is-hidden table_row" id="MasterySumId">
                                                                Beyond simply applying ourselves to the furthering of our minds or bodies, we should make time to explore and understand our strengths, our weaknesses, and what those might look like into the future. Our dedication to enhancing our strengths builds excellence; our dedication to our weaknesses builds character. When we explore and allow ourselves the right kinds of freedom, we almost inevitably discover new passions. These passions can even become our true strengths given time and the proper honing of them. As humans, we often need a more realistic occupation to start with, to establish the necessary levels of security and stability, such that we might explore further. Most of us work this way to some degree or another. Those of us who pursue our utmost passions out of the gate, often are problematically oblivious to the mountain ahead of us. We forget that those who earn a living from their passions typically are mix of innate natural talent and a lengthy dedication to making the most of such talent. Those of us similar to the former could stand to dream a bit more, while those of us similar to the latter could stand a bit more humility and realism. Generally, both camps end up more critical of one another than helping each other from their respective strengths.
                                                                <br>
                                                                When we are the type of people who dedicate a meaningful amount of time to Mastery, we see ourselves as capable of more than we currently are capable of doing and being. We treat ourselves, and are more prone to treating others, as our potential, as our processes of changing and growing. We take care not to hastily rule out what we might come to be able to do and who we might come to be. We understand that neither talent nor hard work alone are sufficient to reach the apex of what we seek to master. We understand, even if only implicitly and unconsciously, that our reaching of any given goal is not why we do what we do, nor will it bring fulfillment to our lives. Those of us who are the kinds of people to achieve such accomplishments are not the kinds of people to be satisfied by them. We usually are more disciplined, having exercised and trained our willpower to be able to pursue mastery along with the other more conventional obligations we might have. Ultimately, we are united in that we do not know how good we are, do not even dare think to bind ourselves to the limitations of how we perceive ourselves. However, we all devote ourselves to finding out just how good we can become.
                                                                <br>
                                                                Those of us who do not dedicate time to Mastery, view ourselves as being incapable of achieving a meaningful level of mastery. We might chalk it up to lacking the time, resources, natural talent, or whatever other viable reason we might reach for to avoid pursuing such things. Note that we say reason, not excuse, because when we understand how people work, we see that there really is no such thing as an excuse. An excuse is the reason we offer when we do not fully understand the reason, which most of the time we do not. The reasons we come up with for why we do not dedicate time to Mastery, or even generally hobbies (that we might become masterful at), become the actual reasons, the more we believe them and act from them. In this way, these reasons are far more dangerous to us than excuses. Further, we view such things as unrealistic, pointless, and often times unnecessary. We content ourselves with family, friends, and our jobs, generally all of which further serve our idea that it is not worth pursuing some form of mastery. Those around us, and we ourselves, focus on the achievements, the goals, the concrete milestones that seem out of reach, and we tell ourselves that if we cannot reach them then it is not worth the effort. 
                                                                <br>
                                                                All the while, we forget that the endeavor, the pursuit, the processes itself is what is meaningful and fulfilling. 
                                                                We all can strive to master becoming better friends, better romantic partners, better citizens, and truly we can all strive to become better humans. Little, if anything else, can be experienced as nearly as meaningful.
                                                                <br><br>
                                                                <strong>What we all Fail to Consider:</strong>
                                                                <br>
                                                                It is important for us all to remember that strengths and weaknesses do not simply relate to the external things we are good or bad at. Rather, these more obvious and measurable strengths/weaknesses are usually the consequences of who we are. Since it is harder to measure such things, we can be quick to forget this. We benefit most from working on who we are, as it has the potential to change and improve what we do, how we do it, why we do it, and ultimately whether or not we do it. Most of us spend a tremendous amount of time doing things that we come to see as inauthentic, upon further inspection. Unfortunately, there are no classes in school to teach us these things, and our parents are often still trying to figure them out for themselves.   
                                                            </h>
                                                            <h6>score: ${masteryTotal}</h6>
                                                            <h6>Population Percentile: ${masteryNd}</h6>

                                                            <h4><span id="AbstinenceSumLabelId">Abstinence Sum</span> <i class="fa fa-angle-down AbstinenceSumId" ></i></h4>
                                                            <h class="option-content is-hidden table_row" id="AbstinenceSumId">
                                                                Though this is often one of the more controversial measurements, it is crucial for understanding what might be standing in the way of us living our most satisfying lives. We use most of the things that relate to this score as means of distracting ourselves, particularly when we are not doing all that well. Unfortunately, since our systems as human beings are calibrated by default to a much more survival-oriented existence, we lack much of the necessary sensitivity to noticing when things are in the way of a fulfilling life. For the majority of human existence, we have been much closer to potential death than we are in the modern world. The signals that tell us when something is not necessarily severely wrong, but rather not quite right are subtle, at best. Further, these signals are easily overpowered by more biological signals, such as being hungry, thirsty, tired, or lusty (to put it technically). Over time, these signals whisper to us and nag at us that something still is not right, not the way we would desire it to be, at least. Most of us end up turning to ancient techniques of quieting, and coping, with these signals. This is much of the reason behind our tendencies to use alcohol, drugs, masturbation, or any other behavior that provides us with little more than a short-term benefit (often characterized by relief and or distraction), generally with a necessary illusion of long-term gain (the tending of relationships, etc.). If we need to abuse and distract ourselves together, then these are not relationships, this is collective coping. Unfortunately, such things are both widely and deeply culturally founded. 
                                                                <br>
                                                                Scoring higher in this generally means we are suffering more, often a substantial amount more, than others. We do not simply desire distraction and relief, we crave it, and ultimately, we need it. The higher we score, the more unbearable our lives feel. We feel unable to face the mounting challenges and threats that await us, if we were to examine our current predicament more thoroughly and objectively. Depending on just how highly we score, we are approaching, at, or past the point of hopelessness. We may find ourselves thinking that there is no point in trying to overcome what awaits us, as even if we do, all that awaits us is more suffering. We have forgotten and are blind to the aspects of existence that are powerful enough to counter the tragedy of it all. We lack the clarity and perspective necessary to see beyond ourselves, to see and care how our actions are affecting those we care about, even if we do not care enough about ourselves to overcome for our own sake. We rarely score very highly in this without also being severely depressed. 
                                                                <br>
                                                                <Strong>Note:</Strong> If you or someone you know scores highly, please reach out to us immediately, and please seek professional help. Often those of us who score highly have sought help, but we have been turned off by the experience. We assure you, there are those out there that are excellent at what they do (at all price ranges), no matter how unique you might think your predicament is.
                                                                <br>
                                                                Most of us will score somewhere in the middle. Our lives are typically going relatively normally. We are generally not experiencing an excessive amount of tragedy or suffering. Most of us were raised and brought up in a culture where the things we are doing are considered acceptable, though some of us might branch out slightly into more taboo things such as marijuana. We tend to be and think of ourselves as normal well-functioning adults and members of society. We feel our lives could be better than they are; we know we are less satisfied than we could be but generally, we have resigned ourselves to see our current lives as being “good enough.” We feel we have little time or resources, in general, to devote to trying to change things very much. We feel, or at least tell ourselves, that we would be unsupported in our pursuit of more fulfillment through healthy means, which is reinforced by our lack of examples of people pursuing that around us. As such, we distract ourselves from the routine hardships and the subtle urge to seek more, through conventional means. We partake of these “necessary vices” sufficiently to distract ourselves, to quiet the signals that say there is more waiting for us. 
                                                                <br>
                                                                Since you are reading this, it is our desire to let you know that such change, especially the small first steps, is much easier, and much less painless than you might think.
                                                                <br>
                                                                A lower score on Abstinence tends to be less a consequence of “trying” to abstain, but it is rather a consequence of our different perspective on such things. Very few, if any, of us who score exceptionally low have never done any of the things asked about. We come to view these things as unnecessary and even counter to the way we desire and choose to experience our lives. We understand that such things are merely distractions from underlying issues, deficits in ourselves, etc. Most of us have gotten to the point of refraining by setting these distractions aside, in pursuit of solutions to the underlying issues. Though there may be those who score low due to strict adherence to something such as religious practices, they are not who we are talking about. Generally, such people have other addictions that are more culturally sanctioned. This can be things such as mindlessly watching hours of TV and compulsively scrolling through social media. Even though these are more socially accepted, they are still quite harmful, and they can seriously reduce our quality of life. Though we need not have “solved” all of our issues to score low, most of us have made significant progress. We can only ever get to a point of no desire for such things, to where we require no willpower to say “no,” by getting to the point where our experience of our lives and our way of being is better than the distraction and relief that such things provide. Our discipline is focused on sorting through who we are, carefully working towards who we are becoming, such that this constant self-induced discomfort stands as a substitute for the tragedies that would otherwise befall us. We accept the difficult truth that most of the tragedies in our lives can be seen as some consequence of missteps on our part, or that they serve to teach us some set of valuable lessons that we would not have learned otherwise. This is not the often-used platitude of “everything happens for a reason.” This tends to be a statement deferring responsibility and shying away from our recognition that sometimes existence is simply, nonsensically, unbelievably tragic. On the other hand, we choose to find all the possible lessons and meanings in whatever we are confronted with. We understand that only by doing so are we able to head off tragedy before it strikes, and only by becoming who this way of being makes us, might we be prepared to handle the tragedies that are unavoidable. Such a path, such a way of being, is the primary alternative to seeking to cope with any of the dissatisfying aspects of our lives.
                                                                <br>
                                                                We understand and can appreciate the appeal of seeking relief and distraction from the hardships we all face. We have been there, tried that, and we became tired of it. We decided to explore and see what we could do about the problems inherent to ourselves, and maybe we could even work towards alleviating the problems inherent to existence.
                                                                <br>
                                                                The harshest truth is that at the end of our lives, when we have nothing but time to reflect on how we have lived, the question is no longer can we live with ourselves, but can we die with ourselves? Death, the phenomenon we have spent our whole lives struggling against, ultimately brings into stark clarity, just what we think of ourselves and how we feel about ourselves. There is little worse than going to sleep one last time, with the regret of knowing that we could have lived more weighing on our hearts. 
                                                                <br>
                                                                <u>Can these addictions be used a “right way?”</u>
                                                                <br>
                                                                The evidence suggests, quite overwhelmingly, that though we can think of how such vices could be used positively, they virtually never are. The important thing for us to understand is that we are rarely very aware of why we do what we do, even if we meditate extensively, are enlightened, sanctified, etc. The nature of our minds and how much is going on unconsciously (by necessity), makes us unaware of most of our motivations and intentions, at least in some significant way. What we know, is that the people who are experiencing the maximally satisfying lives stay away from these things as much as possible. This generally does not take the form of “Abstinence, or Sobriety,” but rather those of us who do not partake of such things see them as unnecessary and undesirable modifiers of our experiences.
                                                                <br>
                                                                <strong>Note:</strong> Many people like to see themselves as more virtuous or holier than others because they do not do such things. Such a self-perceptions can be particularly prominent, especially for those who have never tried such things. This is an inappropriate way of looking at things, as such people do not actually understand why others might do those things, and they typically have their own equally, but less obviously, harmful ways of coping. Such a need to see oneself as better, especially for those reasons, is as much an indicator that you are in the same boat as them, far more than you are in any way, above them.
                                                                <br>
                                                                <u>Existence is Hard; Coping with Existence is Much Harder</u>
                                                                <br>
                                                                As stated, for the majority of human existence, we have always been a stone’s throw away from death and disaster. Our more animalistic aspects of ourselves remind us not to become too complacent or comfortable, lest our adaptive instincts, honed of millions of years, dull. The pull of these instincts competes with, and generally derails, our desire to pursue a satisfying existence. Most of us associate satisfaction and comfort with the type of complacency that likely killed many of our would-be ancestors. We have not found how to truly embrace the process of making ourselves uncomfortable and growing from it. Instead, most of us are humans skillful and useful enough to those beyond ourselves to feel a certain level of satisfaction (enough to know we could be much more fulfilled), but conditioned enough by the harsh nature of existence to seek to cope with it, rather than seek much else. We grow, achieve, and specialize, more for the sake of those around us, their approval, and our biological duties, than for ourselves. We generally do not know what it means to struggle and strive for ourselves alone, without expectation of the approval or awards we will get along the way. 
                                                                <br>

                                                                Attempting to Solve the problem of existence is harder yet.
                                                            </h>
                                                            <h6>score: ${abstinenceTotal}</h6>
                                                            <h6>Population Percentile: ${abstinenceNd}</h6>

                                                        </div>
                                                        <div class="col-md-6">

                                                            <c:forEach items="${question1Value1}" var="qv" varStatus="loop" >
                                                                 <c:if test="${loop.count==1}">
                                                                <h4>${qv.QUESTION_NAME}</h4>
                                                                </c:if>
                                                                <strong>${qv.QUESTION_OPTION_ADDITIONAL_VALUE}</strong>
                                                                <strong>${qv.QUESTION_OPTION_NAME}</strong>
                                                                <c:if test="${!loop.last}">,</c:if>
                                                            </c:forEach>
        <!--                                                    <h4>${question2Name}</h4>
                                                            <h6><strong>${question2Value1}</strong> </h6>
                                                            <h6><strong>${question2Value2}</strong></h6>-->
                                                            <c:forEach items="${questionWiseNDList}" var="SeekingTraitId">
                                                                <c:if test="${SeekingTraitId.key==42}">
                                                                    <tr class="table_row">
                                                                    <br>
                                                                    <h4>${SeekingTraitId.value['ques'].name}</h4> 
                                                                    <h6>score: ${SeekingTraitId.value['ques'].score}</h6>  
                                                                    <h6>Population Percentile: ${SeekingTraitId.value['ques'].normalDistribution}</h6>   
                                                                    </tr>
                                                                </c:if>
                                                            </c:forEach>
                                                            <c:forEach items="${question3Value}" var="qv" varStatus="loop" >
                                                                <c:if test="${loop.count==1}">
                                                                <h4>${qv.QUESTION_NAME}</h4>
                                                                </c:if>
                                                                <strong>${qv.QUESTION_OPTION_ADDITIONAL_VALUE}</strong>
                                                                <strong>${qv.QUESTION_OPTION_NAME}</strong>
                                                            </c:forEach>
                                                            <c:forEach items="${question4Value}" var="qv" varStatus="loop" >
                                                                <c:if test="${loop.count==1}">
                                                                    <h4>${qv.QUESTION_NAME}</h4>
                                                                </c:if>
                                                                <strong>${qv.QUESTION_OPTION_ADDITIONAL_VALUE}</strong>
                                                                <strong>${qv.QUESTION_OPTION_NAME}</strong>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <br><br>
                                                    <h3>4 Tendencies Test Percentage</h3>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <table class="table table-bordered">
                                                                <thead>
                                                                    <tr>
                                                                        <th style="width: 170px; text-align: center;">QUOR</th>
                                                                        <th style="width: 100px; text-align: center;">Ratio</th>
                                                                        <th style="width: 100px; text-align: center;">Percent</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td style="text-align: center;">Q</td>
                                                                        <td style="text-align: center;" >${tendenciesTestPercForQRation}</td>
                                                                        <td style="text-align: center;">${tendenciesTestPercForQPercentage}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center;">U</td>
                                                                        <td style="text-align: center;" >${tendenciesTestPercForURation}</td>
                                                                        <td style="text-align: center;">${tendenciesTestPercForUPercentage}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center;">O</td>
                                                                        <td style="text-align: center;" >${tendenciesTestPercForORation}</td>
                                                                        <td style="text-align: center;">${tendenciesTestPercForOPercentage}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center;">R</td>
                                                                        <td style="text-align: center;" >${tendenciesTestPercForRRation}</td>
                                                                        <td style="text-align: center;">${tendenciesTestPercForRPercentage}</td>
                                                                    </tr>
                                                                    </div>
                                                                </tbody>
                                                            </table>  
                                                        </div>
                                                    </div>

                                                    <div class="col-md-11">
                                                        <div class="panel panel-default"  style="background-color: black;">
                                                            <div class="panel-heading">
                                                                <h3 class="panel-title">Mental Improvement Activities</h3>
                                                                <ul class="panel-controls" style="margin-top: 2px;">
                                                                    <li><a href="#" class="panel-fullscreen"><span class="fa fa-expand"></span></a></li>                                      
                                                                </ul>  
                                                            </div>
                                                            <!--<div class="panel-body">-->
                                                            <div class="chart-holder" id="mentalImprovementChartId">
                                                                <!--<div>-->
                                                                <canvas id="mentalImprovementActivitiesPanel"></canvas>
                                                                <!--</div>-->
                                                            </div>
                                                            <!--</div>-->
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                        <!--                                        <h6 style="font-size: 18px; font-weight:normal; color:Black;">&nbsp;&nbsp;&nbsp;Note: Your dossier is a constant work in progress to bring you greater value and to help you understand yourself more comprehensively .</h6>
                                                                                <h6 style="font-size: 18px; font-weight:normal; color:Black;">&nbsp;&nbsp;&nbsp;Any numbers highlighted in red should be disregarded while we work out a few bugs. We appreciate your understanding!</h6>-->
                                    </div>                           
                                </form:form>
                            </div>
                            <form:form name="pdfForm" id="pdfForm" method="POST" action="../pdf/comprehensiveDossier.htm?takerId=${takerId}">
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
        <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        <script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-select.js"></script>
        <script type="text/javascript" src="../js/plugins/datatables/jquery.dataTables.min.js"></script>    
        <script type='text/javascript' src='../js/plugins/jquery-validation/jquery.validate.js'></script>
        <!-- END THIS PAGE PLUGINS-->        

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>        
        <script type="text/javascript" src="../js/actions.js"></script>
        <script type="text/javascript" src="../js/Chart.min.js"></script>

        <!-- END TEMPLATE -->
        <script type="text/javascript">
            new Chart(document.getElementById("problemSolvingApproachGraph"), {
                type: 'bar',
                data: {
                    labels: ['3.Red', '4.Blue', '5.Crimson', '6. Sage', '7. Slate', '8.Violet', '9.Scarlet', '10.Azure'],
                    datasets: [
                        {
                            label: [''],
                            backgroundColor: '#CC0000',
                            borderColor: '#CC0000 ',
                            borderWidth: 1,
                            data: [${problemSolvingList}]
                        }
                    ]
                },
                options: {
                    legend: {
                        position: 'right',
                        display: false
                    },
                    scales: {
                        xAxes: [{gridLines: {display: false},
                                afterFit: function (scale) {
                                    var chartWidth = scale.chart.width;
                                    var new_width = chartWidth * 0.15;
                                    scale.width = new_width;
                                    scale.height = new_width;
                                }, scaleLabel: {
                                    display: true,
                                    labelString: "Value Levels",
                                    fontColor: "white",
                                    padding: "2",
                                    fontSize: "10"
                                }, ticks: {
                                    fontColor: 'white'
                                }
                            }],
                        yAxes: [{dispay: false, ticks: {beginAtZero: true, min: 0,
                                    max: 10, fontColor: 'white'}, gridLines: {display: false}
                                , scaleLabel: {
                                    display: true,
                                    labelString: "Frequency of Approach Use(1-5 Likert)",
                                    fontColor: "red",
                                    padding: "2",
                                    fontSize: "10"
                                }}]
                    },
                },
                plugins: [{
                        beforeDraw: function (c) {
                            var chartWidth = c.chart.width;
                            c.scales['x-axis-0'].options.ticks.fontSize = chartWidth * 2 / 100; //fontSize: 6% of canvas width
                            c.scales['y-axis-0'].options.ticks.fontSize = chartWidth * 2 / 100; //fontSize: 6% of canvas width
                        }
                    }]
            });
            new Chart(document.getElementById("collaborativeGraph"), {
                type: 'bar',
                data: {
                    labels: ['3.Red', '4.Blue', '5.Crimson', '6. Sage', '7. Slate', '8.Violet', '9.Scarlet', '10.Azure'],
                    datasets: [
                        {
                            label: [''],
                            backgroundColor: '#CC0000',
                            borderColor: '#CC0000',
                            borderWidth: 1,
                            data: [${collaborativeApproachList}]
                        }
                    ]
                },
                options: {
                    legend: {
                        position: 'right',
                        display: false
                    },
                    scales: {
                        xAxes: [{gridLines: {display: false},
                                afterFit: function (scale) {
                                    var chartWidth = scale.chart.width;
                                    var new_width = chartWidth * 0.15;
                                    scale.width = new_width;
                                    scale.height = new_width;
                                }, scaleLabel: {
                                    display: true,
                                    labelString: "Value Levels",
                                    fontColor: "white",
                                    padding: "2",
                                    fontSize: "10"
                                }, ticks: {
                                    fontColor: 'white'
                                }}],
                        yAxes: [{dispay: false, ticks: {beginAtZero: true, min: 0,
                                    max: 10, fontColor: 'white'}, gridLines: {display: false},
                                scaleLabel: {
                                    display: true,
                                    labelString: "Frequency of Approach(1-5 Likert)",
                                    fontColor: "red",
                                    padding: "2",
                                    fontSize: "10"
                                }}]
                    }
                },
                plugins: [{
                        beforeDraw: function (c) {
                            var chartWidth = c.chart.width;
                            c.scales['x-axis-0'].options.ticks.fontSize = chartWidth * 2 / 100; //fontSize: 6% of canvas width
                            c.scales['y-axis-0'].options.ticks.fontSize = chartWidth * 2 / 100; //fontSize: 6% of canvas width
                        }
                    }]
            });
            new Chart(document.getElementById("treatResponseApproachGraph"), {
                type: 'bar',
                data: {
                    labels: ['3.Red', '4.Blue', '5.Crimson', '6. Sage', '7. Slate', '8.Violet', '9.Scarlet', '10.Azure'],
                    datasets: [
                        {
                            label: [''],
                            backgroundColor: '#CC0000',
                            borderColor: '#CC0000',
                            borderWidth: 1,
                            data: [${treatResponseApproachList}]
                        }
                    ]
                },
                options: {
                    legend: {
                        position: 'right',
                        display: false
                    },
                    scales: {
                        xAxes: [{gridLines: {display: false},
                                afterFit: function (scale) {
                                    var chartWidth = scale.chart.width;
                                    var new_width = chartWidth * 0.15;
                                    scale.width = new_width;
                                    scale.height = new_width;
                                }, scaleLabel: {
                                    display: true,
                                    labelString: "Value Levels",
                                    fontColor: "white",
                                    padding: "2",
                                    fontSize: "10"
                                }, ticks: {
                                    fontColor: 'white'
                                }}],
                        yAxes: [{dispay: false, ticks: {beginAtZero: true, min: 0,
                                    max: 10, fontColor: 'white'}, gridLines: {display: false}
                                , scaleLabel: {
                                    display: true,
                                    labelString: "Frequency of Response(1-5 Likert)",
                                    fontColor: "red",
                                    padding: "2",
                                    fontSize: "10"
                                }}]
                    }
                },
                plugins: [{
                        beforeDraw: function (c) {
                            var chartWidth = c.chart.width;
                            c.scales['x-axis-0'].options.ticks.fontSize = chartWidth * 2 / 100; //fontSize: 6% of canvas width
                            c.scales['y-axis-0'].options.ticks.fontSize = chartWidth * 2 / 100; //fontSize: 6% of canvas width

                        }
                    }]
            });
            new Chart(document.getElementById("mentalImprovementActivitiesPanel"), {
                type: 'bar',
                data: {
                    labels: ['Meditation', 'Watch Informative Content (such as youtube videos)', 'Reading (or Listening to Books) (and Articles)', 'Podcasts', 'Challenging Productive Conversation', 'College', 'Graduate School', 'Internet Forums', 'Pursuing Doctorate', 'Gratitude Journaling', 'Listen Online Lectures', 'Psychedelics', 'Hours Spent Working On Mind'],
                    datasets: [
                        {
                            label: [''],
                            backgroundColor: '#CC0000',
                            borderColor: '#CC0000',
                            borderWidth: 1,
                            wrapLabels: true,
                            data: [${mentalImproveListForGraph}]
                        }
                    ]
                },
                options: {
                    legend: {
                        position: 'right',
                        display: false
                    },
                    scales: {
                        xAxes: [{gridLines: {display: false},
                                afterFit: function (scale) {
                                    var chartWidth = scale.chart.width;
                                    var new_width = chartWidth * 0.15;
                                    scale.width = new_width;
                                    scale.height = new_width;
                                }
                                , ticks: {
                                    callback: function (label, index, labels) {
                                        if (/\s/.test(label)) {
                                            return label.split(" ");
                                        } else {
                                            return label;
                                        }
                                    }, maxRotation: 90, fontColor: 'white'
                                }, scaleLabel: {
                                    display: true,
                                    labelString: "Mental Activities",
                                    fontColor: "white",
                                    padding: "2",
                                    fontSize: "10"
                                }}],
                        //, ticks: {fontSize: 10} 
                        yAxes: [{dispay: false, ticks: {beginAtZero: true, min: 0,
                                    max: 10, fontColor: 'white'}, gridLines: {display: false}, scaleLabel: {
                                    display: true,
                                    labelString: "Frequency of Action(1-7 Likert)",
                                    fontColor: "red",
                                    padding: "2",
                                    fontSize: "10"
                                }}]
                    }
                },
                plugins: [{
                        beforeDraw: function (c) {
                            var chartWidth = c.chart.width;
                            c.scales['x-axis-0'].options.ticks.fontSize = (chartWidth) * 2 / 200; //fontSize: 6% of canvas width
                            c.scales['y-axis-0'].options.ticks.fontSize = chartWidth * 2 / 120; //fontSize: 6% of canvas width

                        }
                    }]
            });
            new Chart(document.getElementById("valueHealthAndDevelopmentGraph"), {
                type: 'bar',
                data: {
                    labels: ['1.Grey', '2.Lavender', '3.Red', '4.Blue', '5.Crimson', '6.Sage', '7.Slate', '8.Violet', '9.Scarlet', '10.Azure'],
                    datasets: [
                        {
                            label: [''],
                            backgroundColor: '#CC0000',
                            borderColor: '#CC0000',
                            borderWidth: 1,
                            data: [${valueHealthAndDevelopmentGraph}]
                        }
                    ]
                },
                options: {
                    legend: {
                        position: 'right',
                        display: false
                    },
                    scales: {
                        xAxes: [{gridLines: {display: false},
                                afterFit: function (scale) {
                                    var chartWidth = scale.chart.width;
                                    var new_width = chartWidth * 0.15;
                                    scale.width = new_width;
                                    scale.height = new_width;
                                },
                                scaleLabel: {
                                    display: true,
                                    labelString: "Value Levels",
                                    fontColor: "white",
                                    padding: "2",
                                    fontSize: "10"
                                }, ticks: {
                                    fontColor: 'white'
                                }
//                                ,
//                                gridLines: {
//                                    display: false,
//                                    tickMarkLength: 40
//                                }
                            }],
                        //, ticks: {fontSize: 10} 
                        yAxes: [{dispay: false, ticks: {beginAtZero: true, min: 0, max: 7, fontColor: 'white'},
                                gridLines: {display: false},
                                scaleLabel: {
                                    display: true,
                                    labelString: "AGREEMENT Agreement(1-7 Likert)",
                                    fontColor: "red",
                                    padding: "2",
                                    fontSize: "10"
                                }}]
                    }
                },
                plugins: [{
                        beforeDraw: function (c) {
                            var chartWidth = c.chart.width;
                            c.scales['x-axis-0'].options.ticks.fontSize = chartWidth * 2 / 100; //fontSize: 6% of canvas width                      
                            c.scales['y-axis-0'].options.ticks.fontSize = chartWidth * 2 / 100; //fontSize: 6% of canvas width
                        }
                    }]
            });
            new Chart(document.getElementById("forcedValueShapeGraph"), {
                type: 'bar',
                data: {
                    labels: ['1.Grey', '2.Lavender', '3.Red', '4. Blue', '5. Crimson', '6.Sage', '7.Slate', '8.Violet', '9.Scarlet', '10.Azure'],
                    datasets: [
                        {
                            label: [''],
                            backgroundColor: '#CC0000',
                            borderColor: '#CC0000 ',
                            borderWidth: 1,
                            data: [${forcedValueShapeGraph}]
                        }
                    ]
                },
                options: {
                    legend: {
                        position: 'right',
                        display: false
                    },
                    scales: {
                        xAxes: [{gridLines: {display: false},
                                afterFit: function (scale) {
                                    var chartWidth = scale.chart.width;
                                    var new_width = chartWidth * 0.15;
                                    scale.width = new_width;
                                    scale.height = new_width;
                                },
                                scaleLabel: {
                                    display: true,
                                    labelString: "Value Levels",
                                    fontColor: "white",
                                    padding: "2",
                                    fontSize: "10"
                                }, ticks: {
                                    fontColor: 'white'
                                }
                            }],
                        //, ticks: {fontSize: 10} 
                        yAxes: [{dispay: false, ticks: {beginAtZero: true, min: 0, max: 10, fontColor: 'white'},
                                gridLines: {display: false},
                                scaleLabel: {
                                    display: true,
                                    labelString: "Value Ordering",
                                    fontColor: "red",
                                    padding: "2",
                                    fontSize: "10"
                                }}]
                    }
                }, layout: {
                    padding: {
                        left: -10,
                        right: -10,
                        top: -10,
                        bottom: -10
                    }
                },
                plugins: [{
                        beforeDraw: function (c) {
                            var chartWidth = c.chart.width;
                            c.scales['x-axis-0'].options.ticks.fontSize = chartWidth * 2 / 100; //fontSize: 6% of canvas width
                            c.scales['y-axis-0'].options.ticks.fontSize = chartWidth * 2 / 100; //fontSize: 6% of canvas width
                        }
                    }]
            });

            new Chart(document.getElementById("sourcesOfValidationGraph"), {
                type: 'bar',
                data: {
                    labels: ['Artistic skills/aesthetic appreciation', 'Sense of Humor', 'Relations with family/friends', 'Spontaneity/living in the moment', 'Social skills', 'Athletics', 'Musical ability/appreciation', 'Physical attractiveness', 'Creativity', 'Business/managerial /leadership skills', 'Romantic values/skills', 'Spiritual Development', 'Altruism', 'Vision Realization'],
                    datasets: [
                        {
                            label: [''],
                            backgroundColor: '#CC0000',
                            borderColor: '#CC0000',
                            borderWidth: 1,
                            wrapLabels: true,
                            data: [${sourcesOfValidationGraph}]
                        }
                    ]
                },
                options: {
                    legend: {
                        position: 'right',
                        display: false
                    },
                    scales: {
                        xAxes: [{gridLines: {display: false},
                                afterFit: function (scale) {
                                    var chartWidth = scale.chart.width;
                                    var new_width = chartWidth * 0.15;
                                    scale.width = new_width;
                                    scale.height = new_width;
                                }
                                , ticks: {
                                    callback: function (label, index, labels) {
                                        if (/\s/.test(label)) {
                                            return label.split(" ");
                                        } else {
                                            return label;
                                        }
                                    }, maxRotation: 90, minRotation: 60, fontColor: 'white'
                                }, scaleLabel: {
                                    display: true,
                                    labelString: "Value Levels",
                                    fontColor: "white",
                                    padding: "2",
                                    fontSize: "10"
                                }}],
                        //, ticks: {fontSize: 10} 
                        yAxes: [{dispay: false, ticks: {beginAtZero: true, min: 0,
                                    max: 14, fontColor: 'white'}, gridLines: {display: false}, scaleLabel: {
                                    display: true,
                                    labelString: "AGREEMENT Agreement(1-7 Linkert)",
                                    fontColor: "red",
                                    padding: "2",
                                    fontSize: "10"
                                }}]
                    }
                    , layout: {
                        padding: {
                            top: 10
                        }
                    }
                },
                plugins: [{
                        beforeDraw: function (c) {
                            var chartWidth = c.chart.width;
                            c.scales['x-axis-0'].options.ticks.fontSize = (chartWidth) * 2 / 200; //fontSize: 6% of canvas width
                            c.scales['y-axis-0'].options.ticks.fontSize = chartWidth * 2 / 120; //fontSize: 6% of canvas width

                        }
                    }]
            });

            $(".AggreeablenessId,#AgreeblenessLabelId").on('click', function () {
                $('.AggreeablenessId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#AggreeablenessIdData').slideToggle();
            });

            $(".CompassionId,#CompassionLabelId").on('click', function () {
                $('.CompassionId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#CompassionId').toggle();
                $('#CompassionTechnicalId').hide();
            })
                    ;
            $(".CompassionTechnicalId").on('click', function () {
                $('.CompassionTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#CompassionTechnicalId').toggle();
            });
            ;
            $(".PolitenessId,#PolitenessLabelId").on('click', function () {
                $('.PolitenessId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#PolitenessId').toggle();
                $('#PolitenessTechnicalId').hide();
            });
            $(".PolitenessTechnicalId").on('click', function () {
                $('.PolitenessTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#PolitenessTechnicalId').toggle();
            })
                    ;
            $(".ConscientiousnessId,#ConscientiousnessLabelId").on('click', function () {
                $('.ConscientiousnessId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#ConscientiousnessId').toggle();
            });
            $(".IndustriousnessId,#IndustriousnessLabelId").on('click', function () {
                $('.IndustriousnessId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#IndustriousnessId').toggle();
                $('#IndustriousnessTechnicalId').hide();
            });
            $(".IndustriousnessTechnicalId").on('click', function () {
                $('.IndustriousnessTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#IndustriousnessTechnicalId').toggle();
            });
            $(".OrderlinessId,#OrderlinessLabelId").on('click', function () {
                $('.OrderlinessId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#OrderlinessId').toggle();
                $('#OrderlinessTechnicalId').hide();
            });
            $(".OrderlinessTechnicalId").on('click', function () {
                $('.OrderlinessTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#OrderlinessTechnicalId').toggle();
            });
            $(".ExtroversionId,#ExtroversionLabelId").on('click', function () {
                $('.ExtroversionId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#ExtroversionId').toggle();
                $('#ExtroversionTechnicalId').hide();
            });
            $(".ExtroversionTechnicalId").on('click', function () {
                $('.ExtroversionTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#ExtroversionTechnicalId').toggle();
            });
            $(".EnthusiasmId,#EnthusiasmLabelId").on('click', function () {
                $('.EnthusiasmId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#EnthusiasmId').toggle();
                $('#EnthusiasmTechnicalId').hide();
            });
            $(".EnthusiasmTechnicalId").on('click', function () {
                $('.EnthusiasmTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#EnthusiasmTechnicalId').toggle();
            });
            $(".AssertivenessId,#AssertivenessLabelId").on('click', function () {
                $('.AssertivenessId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#AssertivenessId').toggle();
                $('#AssertivenessTechnicalId').hide();
            });
            $(".AssertivenessTechnicalId").on('click', function () {
                $('.AssertivenessTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#AssertivenessTechnicalId').toggle();
            });
            $(".NeuroticismId,#NeuroticismLabelId").on('click', function () {
                $('.NeuroticismId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#NeuroticismId').toggle();
                $('#NeuroticismTechnicalId').hide();
            });
            $(".NeuroticismTechnicalId").on('click', function () {
                $('.NeuroticismTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#NeuroticismTechnicalId').toggle();
            });
            $(".WithdrawalId,#WithdrawalLabelId").on('click', function () {
                $('.WithdrawalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#WithdrawalId').toggle();
                $('#WithdrawalTechnicalId').hide();
            });
            $(".WithdrawalTechnicalId").on('click', function () {
                $('.WithdrawalTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#WithdrawalTechnicalId').toggle();
            });
            $(".VolatilityId,#VolatilityLabelId").on('click', function () {
                $('.VolatilityId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#VolatilityId').toggle();
                $('#VolatilityTechnicalId').hide();
            });
            $(".VolatilityTechnicalId").on('click', function () {
                $('.VolatilityTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#VolatilityTechnicalId').toggle();
            });
            $(".OpennessToExpId,#OpennessToExpLabelId").on('click', function () {
                $('.OpennessToExpId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#OpennessToExpId').toggle();
                $('#OpennessToExpTechnicalId').hide();
            });
            $(".OpennessToExpTechnicalId").on('click', function () {
                $('.OpennessToExpTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#OpennessToExpTechnicalId').toggle();
            });
            $(".IntellectId,#IntellectLabelId").on('click', function () {
                $('.IntellectId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#IntellectId').toggle();
                $('#IntellectTechnicalId').hide();
            });
            $(".IntellectTechnicalId").on('click', function () {
                $('.IntellectTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#IntellectTechnicalId').toggle();
            });
            $(".PlayTotalId,#PlayTotalLabelId").on('click', function () {
                $('.PlayTotalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#PlayTotalId').toggle();
                $('#PlayTotalTechnicalId').hide();
            });
            $(".PlayTotalTechnicalId").on('click', function () {
                $('.PlayTotalTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#PlayTotalTechnicalId').toggle();
            });
            $(".SeekingTotalId,#SeekingTotalLabelId").on('click', function () {
                $('.SeekingTotalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SeekingTotalId').toggle();
                $('#SeekingTotalTechnicalId').hide();
            });
            $(".SeekingTotalTechnicalId").on('click', function () {
                $('.SeekingTotalTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SeekingTotalTechnicalId').toggle();
            });
            $(".CareTotalId,#CareTotalLabelId").on('click', function () {
                $('.CareTotalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#CareTotalId').toggle();
            });
            $(".FearTotalId,#FearTotalLabelId").on('click', function () {
                $('.FearTotalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#FearTotalId').toggle();
            });
            $(".AngerTotalId,#AngerTotalLabelId").on('click', function () {
                $('.AngerTotalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#AngerTotalId').toggle();
            });
            $(".SadnessTotalId,#SadnessTotalLabelId").on('click', function () {
                $('.SadnessTotalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SadnessTotalId').toggle();
            });
            $(".SpiritualityTotalId,#SpiritualityTotalLabelId").on('click', function () {
                $('.SpiritualityTotalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SpiritualityTotalId').toggle();
                $('#SpiritualityTotalTechnicalId').hide();
            });

            $(".SpiritualityTotalTechnicalId").on('click', function () {
                $('.SpiritualityTotalTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SpiritualityTotalTechnicalId').toggle();
            });
            $(".LustTotalId,#LustTotalLabelId").on('click', function () {
                $('.LustTotalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#LustTotalId').toggle();
                $('#LustTotalTechnicalId').hide();
            });
            $(".LustTotalTechnicalId").on('click', function () {
                $('.LustTotalTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#LustTotalTechnicalId').toggle();
            });
            $(".EgoismId,#EgoismLabelId").on('click', function () {
                $('.EgoismId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#EgoismId').toggle();
            });
            $(".MachiavellianismId,#MachiavellianismLabelId").on('click', function () {
                $('.MachiavellianismId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#MachiavellianismId').toggle();
            });
            $(".NarcissismId,#NarcissismLabelId").on('click', function () {
                $('.NarcissismId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#NarcissismId').toggle();
            });
            $(".PsychopathyId,#PsychopathyLabelId").on('click', function () {
                $('.PsychopathyId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#PsychopathyId').toggle();
            });
            $(".MoralDisengagementId,#MoralDisengagementLabelId").on('click', function () {
                $('.MoralDisengagementId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#MoralDisengagementId').toggle();
            });
            $(".PsychologicalEntitlementId,#PsychologicalEntitlementLabelId").on('click', function () {
                $('.PsychologicalEntitlementId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#PsychologicalEntitlementId').toggle();
                $('#PsychologicalEntitlementTechnicalId').hide();
            });
            $(".PsychologicalEntitlementTechnicalId").on('click', function () {
                $('.PsychologicalEntitlementTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#PsychologicalEntitlementTechnicalId').toggle();
            });
            $(".SelfInterestId,#SelfInterestLabelId").on('click', function () {
                $('.SelfInterestId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SelfInterestId').toggle();
                $('#SelfInterestTechnicalId').hide();
            });
            $(".SelfInterestTechnicalId").on('click', function () {
                $('.SelfInterestTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SelfInterestTechnicalId').toggle();
            });
            $(".SpitefulnessId,#SpitefulnessLabelId").on('click', function () {
                $('.SpitefulnessId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SpitefulnessId').toggle();
                $('#SpitefulnessTechnicalId').hide();
            });
            $(".SpitefulnessTechnicalId").on('click', function () {
                $('.SpitefulnessTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SpitefulnessTechnicalId').toggle();
            });
            $(".SadismId,#SadismLabelId").on('click', function () {
                $('.SadismId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SadismId').toggle();
            });
            $(".WholeheartednessId,#WholeheartednessLabelId").on('click', function () {
                $('.WholeheartednessId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#WholeheartednessId').toggle();
            });
            $(".SenseOfSelfId,#SenseOfSelfLabelId").on('click', function () {
                $('.SenseOfSelfId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SenseOfSelfId').toggle();
            });
            $(".ThinkingId,#ThinkingLabelId").on('click', function () {
                $('.ThinkingId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#ThinkingId').toggle();
            });
            $(".ActionId,#ActionLabelId").on('click', function () {
                $('.ActionId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#ActionId').toggle();
            });
            $(".SelftActualizationId,#SelftActualizationLabelId").on('click', function () {
                $('.SelftActualizationId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SelftActualizationId').toggle();
            });
            $(".DepthId,#DepthLabelId").on('click', function () {
                $('.DepthId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#DepthId').toggle();
            });
            $(".AcceptanceId,#AcceptanceLabelId").on('click', function () {
                $('.AcceptanceId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#AcceptanceId').toggle();
            });
            $(".ImpactId,#ImpactLabelId").on('click', function () {
                $('.ImpactId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#ImpactId').toggle();
            });
            $(".IndividualityId,#IndividualityLabelId").on('click', function () {
                $('.IndividualityId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#IndividualityId').toggle();
            });
            $(".UclaLonelinessId,#UclaLonelinessLabelId").on('click', function () {
                $('.UclaLonelinessId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#UclaLonelinessId').toggle();
            });
            $(".SelfEsteemId,#SelfEsteemLabelId").on('click', function () {
                $('.SelfEsteemId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SelfEsteemId').toggle();
                $('#SelfEsteemTechnicalId').hide();
            });
            $(".SelfEsteemTechnicalId").on('click', function () {
                $('.SelfEsteemTechnicalId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SelfEsteemTechnicalId').toggle();
            });
            $(".SelfLikingId,#SelfLikingLabelId").on('click', function () {
                $('.SelfLikingId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SelfLikingId').toggle();
            });
            $(".SelfCompetenceId,#SelfCompetenceLabelId").on('click', function () {
                $('.SelfCompetenceId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SelfCompetenceId').toggle();
            });
            $(".SatisfactionWithLifeId,#SatisfactionWithLifeLabelId").on('click', function () {
                $('.SatisfactionWithLifeId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#SatisfactionWithLifeId').toggle();
            });
            $(".BeckDepressionInventoryId,#BeckDepressionInventoryLabelId").on('click', function () {
                $('.BeckDepressionInventoryId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#BeckDepressionInventoryId').toggle();
            });
            $(".ConnectionId,#ConnectionLabelId").on('click', function () {
                $('.ConnectionId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#ConnectionId').toggle();
            });
            $(".CoOpScoreId,#CoOpScoreLabelId").on('click', function () {
                $('.CoOpScoreId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#CoOpScoreId').toggle();
            });
            $(".MindLikertSumId,#MindLikertSumLabelId").on('click', function () {
                $('.MindLikertSumId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#MindLikertSumId').toggle();
            });
            $(".MasterySumId,#MasterySumLabelId").on('click', function () {
                $('.MasterySumId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#MasterySumId').toggle();
            });
            $(".AbstinenceSumId,#AbstinenceSumLabelId").on('click', function () {
                $('.AbstinenceSumId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#AbstinenceSumId').toggle();
            });
            $(".DarkFactorCalculatorId,#DarkFactorCalculatorLabelId").on('click', function () {
                $('.DarkFactorCalculatorId').toggleClass('is-active').next(".option-content").stop().slideToggle(500);
                $('#DarkFactorCalculatorId').toggle();
            });




        </script>
    </body>
</html>