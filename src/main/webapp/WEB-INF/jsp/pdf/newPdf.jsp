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
                                <form:form modelAttribute="testSessions" method="POST" name="form1" id="form1" action="../pdf/newPdf.htm">
                                    <h1 style="text-align: center; color:Black;">Official C.O.D.E.Dossier</h1>
                                    <h2  style="text-align: center;">#${testSessions.taker.takerId}</h2>
                                    <div class="panel-body">
                                        <h4 style="text-align: left; color:Black;"><strong>Test Taker: </strong> ${testSessions.taker.firstName} ${testSessions.taker.lastName}</h4>
                                        <h4 style="text-align: left; color:Black;"><strong>Test Started: </strong> ${testSessions.testStartedTime}</h4>
                                        <h4 style="text-align: left; color:Black;"><strong>Test Ended: </strong> ${testSessions.testEndedTime}</h4>
                                        <h4 style="text-align: left; color:Black;"><strong>Testing Session ID: </strong> ${testSessions.testSessionId}</h4>
                                    </div>
                                    <br>
                                    <!--16Jan2020-->

                                    <div class="panel-body">
                                        <!-- Value Module Start -->
                                        <div class="panel-body">
                                            <c:set var="secondTime" value="1"/>
                                            <c:set var="value" value="0"/>
                                            <c:forEach items="${testSessions.currentQuestion}" var="t" >
                                                <c:set var="option" value="1"/>
                                                <c:if test="${secondTime==1 && t.dimension.id==3  && t.questionTypeId!=5 && (t.questionId==1 || t.questionId==2 || t.questionId==3)}"> 
                                                    <h2 style="text-align: left; color:Black;">VALUES DIMENSION</h2>
                                                    <c:set var="secondTime" value="0"/> 
                                                </c:if>
                                                <c:if test="${t.dimension.id==3 && t.questionTypeId!=5 &&(t.questionId==1 || t.questionId==2 || t.questionId==3) }" >
                                                    <h6><strong>${t.name}</strong></h6>
                                                    <c:forEach items="${t.questionOptionList}" var="tq" >
                                                        <c:choose>  
                                                            <c:when test="${tq.questionOptionValue != 0.0}">  
                                                                <h6>${tq.questionOptionValue}</h6>
                                                            </c:when>
                                                            <c:otherwise>  
                                                                <h6>${tq.questionOptionAdditionalValue}</h6>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                    <p></p>                                       
                                                </c:if>
                                                <c:if test="${t.dimension.id==1 && t.questionTypeId!=5   && t.questionTypeId!=5 &&(t.questionId==1 || t.questionId==2 || t.questionId==3)}">
                                                    <br>
                                                </c:if> 
                                            </c:forEach>  
                                            <!-- Value Module Stop -->
                                            <!-- Cognition Module Start -->
                                            <br>
                                            <h2 style="text-align: left; color:Black;">COGNITION DIMENSION</h2>
                                            <c:forEach items="${testSessions.currentQuestion}" var="t" >
                                                <c:if test="${t.dimension.id==1 && t.questionTypeId!=5 &&  t.trait.id!=6 &&  t.trait.id!=7 &&  t.trait.id!=8  }" >
                                                    <h6><strong>${t.name}</strong></h6>
                                                    <c:forEach items="${t.questionOptionList}" var="tq" >
                                                        <c:choose>  
                                                            <c:when test="${tq.questionOptionValue != 0.0}">  
                                                                <h6>${tq.questionOptionValue}</h6>
                                                            </c:when>
                                                            <c:otherwise>  
                                                                <h6>${tq.questionOptionAdditionalValue}</h6>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                    <p></p>                                       
                                                </c:if>
                                                <c:if test="${t.dimension.id==1 && t.questionTypeId!=5 &&  t.trait.id!=6 &&  t.trait.id!=7 &&  t.trait.id!=8  }">
                                                    <br>
                                                </c:if> 
                                            </c:forEach> 

                                            <div class="panel-default">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="panel panel-default">
                                                            <div class="panel-heading">
                                                                <h3 class="panel-title">Problem Solving Approach</h3>
                                                                <ul class="panel-controls" style="margin-top: 2px;">
                                                                    <li><a href="#" class="panel-fullscreen"><span class="fa fa-expand"></span></a></li>                                      
                                                                </ul>  
                                                            </div>
                                                            <div class="panel-body">
                                                                <div class="col-md-12 chart-holder">
                                                                    <div>
                                                                        <canvas id="problemSolvingApproachGraph"></canvas>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="panel panel-default">
                                                            <div class="panel-heading">
                                                                <h3 class="panel-title">Collaborative Approach</h3>
                                                                <ul class="panel-controls" style="margin-top: 2px;">
                                                                    <li><a href="#" class="panel-fullscreen"><span class="fa fa-expand"></span></a></li>                                      
                                                                </ul>  
                                                            </div>
                                                            <div class="panel-body">
                                                                <div class="col-md-12 chart-holder">
                                                                    <div>
                                                                        <canvas id="collaborativeGraph"></canvas>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="panel panel-default">
                                                            <div class="panel-heading">
                                                                <h3 class="panel-title">Treat Response Approach</h3>
                                                                <ul class="panel-controls" style="margin-top: 2px;">
                                                                    <li><a href="#" class="panel-fullscreen"><span class="fa fa-expand"></span></a></li>                                      
                                                                </ul>  
                                                            </div>
                                                            <div class="panel-body">
                                                                <div class="col-md-12 chart-holder">
                                                                    <div>
                                                                        <canvas id="treatResponseApproachGraph"></canvas>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
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
                                                                        <th style="font-weight:bold">Test Percentage</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr class="table_row">
                                                                        agreeablenessTrait :- ${agreeablenessTraitData}
                                                                        compassion :- ${compassionData}
                                                                        politeness :- ${politenessData}
                                                                        <td style="color: black ; font-size: 15px; font-weight:bold; ">Agreeableness</td>
                                                                        <td><c:out value="${agreeablenessTraitData['trait'].currentMean}"/></td>
                                                                        <td><c:out value="${agreeablenessTraitData['trait'].normalDistribution}"/></td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Compassion</td>
                                                                        <td><c:out value="${compassionData['aspect'].currentMean}"/></td>
                                                                        <td><c:out value="${compassionData['aspect'].normalDistribution}"/></td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Politeness</td>
                                                                        <td><c:out value="${politenessData['aspect'].currentMean}"/></td>
                                                                        <td><c:out value="${politenessData['aspect'].normalDistribution}"/></td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td style="color: black ; font-size: 15px; font-weight:bold; ">Conscientiousness</td>
                                                                        <td><c:out value="${conscientiousnessTraitData['trait'].currentMean}"/></td>
                                                                        <td><c:out value="${conscientiousnessTraitData['trait'].normalDistribution}"/></td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Industriousness</td>
                                                                        <td><c:out value="${industriousnessData['aspect'].currentMean}"/></td>
                                                                        <td><c:out value="${industriousnessData['aspect'].normalDistribution}"/></td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Orderliness</td>
                                                                        <td><c:out value="${orderlinessData['aspect'].currentMean}"/></td>
                                                                        <td><c:out value="${orderlinessData['aspect'].normalDistribution}"/></td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td style="color: black ; font-size: 15px; font-weight:bold; ">Extroversion</td>
                                                                        <td><c:out value="${extroversionData['trait'].currentMean}"/></td>
                                                                        <td><c:out value="${extroversionData['trait'].normalDistribution}"/></td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Enthusiasm</td>
                                                                        <td><c:out value="${enthusiasmData['aspect'].currentMean}"/></td>
                                                                        <td><c:out value="${enthusiasmData['aspect'].normalDistribution}"/></td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Assertiveness</td>
                                                                        <td><c:out value="${assertivenessData['aspect'].currentMean}"/></td>
                                                                        <td><c:out value="${assertivenessData['aspect'].normalDistribution}"/></td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td style="color: black ; font-size: 15px; font-weight:bold; ">Neuroticism</td>
                                                                        <td><c:out value="${neuroticismData['trait'].currentMean}"/></td>
                                                                        <td><c:out value="${neuroticismData['trait'].normalDistribution}"/></td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Withdrawal</td>
                                                                        <td><c:out value="${withdrawalData['aspect'].currentMean}"/></td>
                                                                        <td><c:out value="${withdrawalData['aspect'].normalDistribution}"/></td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Volatility</td>
                                                                        <td><c:out value="${volatilityData['aspect'].currentMean}"/></td>
                                                                        <td><c:out value="${volatilityData['aspect'].normalDistribution}"/></td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td style="color: black ; font-size: 15px; font-weight:bold; ">Openness To Experience</td>
                                                                        <td><c:out value="${opennesstoExperienceData['trait'].currentMean}"/></td>
                                                                        <td><c:out value="${opennesstoExperienceData['trait'].normalDistribution}"/></td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Intellect</td>
                                                                        <td><c:out value="${intellectData['aspect'].currentMean}"/></td>
                                                                        <td><c:out value="${intellectData['aspect'].normalDistribution}"/></td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Openness</td>
                                                                        <td><c:out value="${opennessData['aspect'].currentMean}"/></td>
                                                                        <td><c:out value="${opennessData['aspect'].normalDistribution}"/></td>
                                                                    </tr>
                                                                </tbody>
                                                            </table> 
                                                            <%--------------------------------------------%>
                                                            <!-- Description Module Start -->
                                                            <c:forEach items="${extremismDescList}" var="desc">
                                                                ${desc}<br>
                                                            </c:forEach><br>

                                                            <!-- Description Module End-->

                                                            <%----------------------------------------------%>
                                                            <p class="col-12" style="font-size: 15px; font-weight:normal; ">
                                                                Explanation: The Big Five Aspect Scale is probably the most extensively validated psychometric test after IQ, and has more than 40 years of refinement to desribe what a
                                                                person's personality is like right now. This will be affected by things such as environemnt and emotional state, which is encapsulated in the DISPOSITION DIMENSION.
                                                                Yet, however you score is the Avatar Level that you are currently operating at. Your level may be higher and may be being suppressed or represssed by things like negative
                                                                emotion, which the rest of the assessments can get at. Your Deathproof Factor can jump by as much as 60 points in 6-months, we already have an example of this. The
                                                                Deathproof Factor ranges from -145 to +145, and has been normalized to a 0-100 scale to give you your Avatar Level. Though other levels of the other factors are at play,
                                                                your Avatar Level is the main number you should be looing to improve, and you do that by raising the other levels in VCBED. Increasing you level and score on any of the
                                                                other tests will inevitably translate to a higher Avatar Level.
                                                            </p>
                                                            <p class="col-12" style="font-size: 15px; font-weight:normal; ">
                                                                <strong>Deathproof Factor</strong> takes the personality structure of a person as they are now and examines what traits they have that make them most prone to living an effective life.
                                                                Overall, the Deathproof Factor = the ability to enter and exit Hero's Journeys with ease. The goal of playing the game of life is to go on as many Hero's Journeys as
                                                                possible in as many different aspects of your life as possible. Ultimately once at a high enough level, you can and should start Hero's Journeys working towards solving
                                                                bigger and bigger problems. But, just like in any video game, if you try to attack a given Hero's Journey (quest/mission) without enought training to be at a high enough
                                                                level, you are going to get brutally destroyed. In real life this means financial, relationship, physical, mental, and social failure, potentially creating a bigger Dragon by
                                                                making the problem worse, and finally sending you into a negative emotional spiral.
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <br><br>
                                                    <!-- Avatar Level Statification Start -->
                                                    <div class="row">
                                                        <h3>Avatar Level : ${AvtarLevel}</h3>
                                                        <h3>Wisdom :  ${DeathProofScore}</h3>
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
                                                    </div>
                                                    <!-- Avatar Level Statification Stop -->
                                                    <br><br>
                                                    <!-- ANPS Calculator Module Start -->
                                                    <div class="row">
                                                        <h2 style="font-size: 20px; font-weight:normal; ">ANPS CALCULATOR</h2>

                                                        <div class="col-md-9 scrollable">
                                                            <table class="table table-bordered">
                                                                <thead> 
                                                                    <tr>
                                                                        <th></th>
                                                                        <th style="font-weight:bold; text-align: center;">Mean</th>
                                                                        <th style="font-weight:bold; text-align: center;">Score</th>
                                                                        <th style="font-weight:bold; text-align: center;">Percentage</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td style="text-align: center;">Play Total</td>
                                                                        <td style="text-align: center;">${AnpsPlayAverage}</td>
                                                                        <td style="text-align: center;" >${AnpsPlayScore}</td>
                                                                        <td style="text-align: center;">${AnpsPlayPercentage}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center; ">Seeking Total</td>
                                                                        <td style="text-align: center;">${AnpsSeekingAverage}</td>
                                                                        <td style="text-align: center;" >${AnpsSeekingScore}</td>
                                                                        <td style="text-align: center;">${AnpsSeekingPercenatge}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center;">Care Total</td>
                                                                        <td style="text-align: center;">${AnpsCareAverage}</td>
                                                                        <td style="text-align: center;" >${AnpsCareScore}</td>
                                                                        <td style="text-align: center;">${AnpsCarePercentile}</td>
                                                                    <tr>
                                                                        <td style="text-align: center;">Fear Total</td>
                                                                        <td style="text-align: center;">${AnpsFearAverage}</td>
                                                                        <td style="text-align: center;" >${AnpsFearScore}</td>
                                                                        <td style="text-align: center;">${AnpsFearPercentile}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center;">Anger Total</td>
                                                                        <td style="text-align: center;">${AnpsAngerAverage}</td>
                                                                        <td style="text-align: center;" >${AnpsAngerScore}</td>
                                                                        <td style="text-align: center;">${AnpsAngerPercentile}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center;">Sadness Total</td>

                                                                        <td style="text-align: center;">${AnpsSadnessAverage}</td>
                                                                        <td style="text-align: center;" >${AnpsSadnessScore}</td>
                                                                        <td style="text-align: center;">${AnpsSadnessPercentile}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center;">Spirituality Total</td>

                                                                        <td style="text-align: center;">${AnpsSpiritualityAverage}</td>
                                                                        <td style="text-align: center;" >${AnpsSpiritualityScore}</td>
                                                                        <td style="text-align: center;">${AnpsSpiritualityPercentile}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center;">Lust Total </td>
                                                                        <td style="text-align: center;">${AnpsLustAverage}</td>
                                                                        <td style="text-align: center;" >${AnpsLustScore}</td>
                                                                        <td style="text-align: center;">${AnpsLustPercentile}</td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>                  
                                                        </div>
                                                    </div>
                                                    <!-- ANPS Calculator Module Stop -->
                                                    <div class="row mt-5">
                                                        <div class="col-12" style="font-size: 15px; font-weight:normal; ">
                                                            Explanation: The Affective Neuroscience Personality Scale specififcally measures what emotions you are likely experiencing at a given time, and how your emotionality for a given trait emotion (e.g. PLAY, FEAR, CARE), compares to the rest of the population.  The Affect Dynamism Score is a single distillation of your emotional personality to quickly describe how favorable you emotional system is wired for health, happiness, meaning, self-transcendence, and long term life satisfaction.  A score below 100 means you could definitely use some rewiring, a score below 50 means your day-to-day experience is likely incredibly unpleasant.
                                                        </div>
                                                    </div>
                                                    <div class="row mt-5">
                                                        <div class="col-12" style="font-size: 15px; font-weight:normal; ">
                                                            How to read the results: The Test Total is a measurement of how many points on the Likert-Scales you have cumulatively (all but SPIRTUALITY, which is out of 36, are out of 42).  The Test Percentage is out of the maximum points how you scored against the test.  The Percentile Scores are how you score against other people.  CS stands for College Student, JA for Job Applicant, M for male, F for Female.  The Job applicant scores are likely skewed lower for FEAR, ANGER, and SADNESS, which results in a slightly higher percentage score.  So if you are older than college aged, understand that your negative emotion scores are somewhere in between the two.
                                                        </div>
                                                    </div>
                                                    <br><br>
                                                    <!-- Dark  Factor Calculator Start -->
                                                    <div class="row">
                                                        <h2 style="font-size: 20px; font-weight:normal; ">DARK FACTOR CALCULATOR</h2>

                                                        <div class="col-md-9 scrollable">
                                                            <table class="table table-bordered">
                                                                <thead> 
                                                                    <tr>
                                                                        <th style="font-weight:bold;text-align: center;"></th>
                                                                        <th style="font-weight:bold;text-align: center;">Mean</th>
                                                                        <th style="font-weight:bold;text-align: center;">Score</th>
                                                                        <th style="font-weight:bold;text-align: center;">Percentage</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td style="text-align: center;">Egoism</td>
                                                                        <td style="text-align: center;" >${darkfactorEgoismAverage}</td>
                                                                        <td style="text-align: center;">${darkfactorEgoismTotal}</td>
                                                                        <td style="text-align: center;">${darkfactorEgoismPercentile}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center;">Machiavellianism</td>

                                                                        <td style="text-align: center;" >${darkfactorMachiavellianismAverage}</td>
                                                                        <td style="text-align: center;">${darkfactorMachiavellianismtotal}</td>
                                                                        <td style="text-align: center;">${darkfactorMachiavellianismPercentile}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center;">Narcissism</td>

                                                                        <td style="text-align: center;" >${darkfactorNarcissismAverage}</td>
                                                                        <td style="text-align: center;">${darkfactorNarcissismTotal}</td>
                                                                        <td style="text-align: center;">${darkfactorNarcissismPercentile}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center;">Psychopathy</td>

                                                                        <td style="text-align: center;" >${darkfactorPsychopathyAverage}</td>
                                                                        <td style="text-align: center;">${darkfactorPsychopathyTotal}</td>
                                                                        <td style="text-align: center;">${darkfactorPsychopathyPercentile}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center;">Moral Disengagement</td>

                                                                        <td style="text-align: center;" >${darkfactorMoralDisengagementAverage}</td>
                                                                        <td style="text-align: center;">${darkfactorMoralDisengagementTotal}</td>
                                                                        <td style="text-align: center;">${darkfactorMoraldisengagementPercentile}</td>
                                                                    <tr>
                                                                        <td style="text-align: center;">Psychological Entitlement </td>

                                                                        <td style="text-align: center;" >${darkfactorPsychologicalAverage}</td>
                                                                        <td style="text-align: center;">${darkfactorPsychologicalTotal}</td>

                                                                        <td style="text-align: center;">${darkfactorPsychologicalPercentile}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center;">Self-Interest</td>

                                                                        <td style="text-align: center;" >${darkfactorSelfInterestAverage}</td>
                                                                        <td style="text-align: center;">${darkfactorSelfInterestTotal}</td>
                                                                        <td style="text-align: center;">${darkfactorSelfInterestPercentile}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center;">Spitefulness</td>
                                                                        <td style="text-align: center;" >${darkfactorSpitefulnessAverage}</td>

                                                                        <td style="text-align: center;">${darkfactorSpitefulnessTotal}</td>

                                                                        <td style="text-align: center;">${darkfactorSpitefulnessPercentile}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center;">Sadism</td>

                                                                        <td style="text-align: center;" >${darkfactorSadismAverage}</td>
                                                                        <td style="text-align: center;">${darkfactorSadismTotal}</td>

                                                                        <td style="text-align: center;">${darkfactorSadismPercentile}</td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>                  
                                                        </div>
                                                    </div>
                                                    <!-- Dark  Factor Calculator Stop -->
                                                    <br><br>
                                                    <!-- Dark  Factor Traits Start -->
                                                    <div class="row">
                                                        <h2 style="font-size: 20px; font-weight:normal; ">DARK FACTOR TRAITS</h2>

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
                                                            <div class="row mt-5">
                                                                <div class="col-12" style="font-size: 15px; font-weight:normal; ">
                                                                    Explanation: Dark Total is the sum of all the perenctiles for your scorings on dark traits.
                                                                    Dragon Factor is essentially the ability to take advantage of the perks you unlock by
                                                                    playing the videogame of life, and to ensure optimal outcome in a situation, often by
                                                                    bending chaos to one's own will.
                                                                </div>
                                                            </div>
                                                            <div class="row mt-5">
                                                                <div class="col-12" style="font-size: 15px; font-weight:normal; ">
                                                                    Interpreting the results: For full explainations of the definitions of these categories please see the additional sheets sent to you. The
                                                                    numbers are in percentile form such that it ranks how you score compared to the rest of the population. A Dragon Factor score lower than
                                                                    130 means that you likely to be unable to fully take advantage of situations and are likely to be taken advantage of by other players in the
                                                                    game of life. A score of 300 is the minimum recommended score after sufficient Shadow integration, 300-450 seems to be a good place for
                                                                    most people to get to so they have teeth, so to speak. Higher numbers can be indicative of genuine psychopathy/sociopathy, or quasipathy
                                                                    (a term a friend coined such that you can call upon psychopathic traits but feel no general desire to act in an antisocial manner). The
                                                                    DARK Leverage Ratio is a measure of how well you are leveraging the dark side of your personality for postive gain. Due to how it is
                                                                    calculated, scores above 1 (which is the target zone) are difficult to get if your scores are oriented suggestive of anti-social tenedencies.
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Dark  Factor Traits Stop -->
                                                </div>
                                            </div>

                                        </div>
                                        <!-- Extremism Module Stop -->
                                        <%-------------------------------------------------------------------%>
                                        <!-- Disposition Module Start -->
                                        <div class="panel-body">
                                            <h2 style="text-align: left; color:Black;">DISPOSITION DIMENSION</h2>

                                            <div class="panel-default">
                                                <div class="panel-body">
                                                    <div class="row">
                                                        <div class="col-md-9 scrollable">
                                                            <table class="table table-bordered">
                                                                <thead> 
                                                                    <tr>
                                                                        <th style="font-weight:bold">Cumulative Results</th>
                                                                        <th style="font-weight:bold">Mean</th>
                                                                        <th style="font-weight:bold">Score</th>
                                                                        <th style="font-weight:bold">Percentage</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr class="table_row">
                                                                        <td style="font-weight:bold">Wholeheartedness</td>
                                                                        <td >${wholeTraitAverage}</td>
                                                                        <td>${wholeTraitTotal}</td>
                                                                        <td>${wholeheartedTraitND}</td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Sense of self</td>
                                                                        <td>${senseOfSelfMean}</td>
                                                                        <td>${senseOfSelfTotal}</td>
                                                                        <td>${senseOfSelfND}</td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Thinking</td>
                                                                        <td>${thinkingMean}</td>
                                                                        <td>${thinkingTotal}</td>
                                                                        <td>${thinkingND}</td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Action</td>
                                                                        <td>${actionMean}</td>
                                                                        <td>${actionTotal}</td>
                                                                        <td>${actionND}</td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td style="font-weight:bold">Self Actualization</td>
                                                                        <td>${selfActualizationMean}</td>
                                                                        <td>${selfActualizationTotal}</td>
                                                                        <td>${selfActualizationND}</td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Depth</td>
                                                                        <td>${depthMean}</td>
                                                                        <td>${depthTotal}</td>
                                                                        <td>${depthND}</td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Acceptance</td>
                                                                        <td>${acceptanceMean}</td>
                                                                        <td>${acceptanceTotal}</td>
                                                                        <td>${acceptanceND}</td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Impact</td>
                                                                        <td>${impactMean}</td>
                                                                        <td>${impactTotal}</td>
                                                                        <td>${impactND}</td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>Individuality</td>
                                                                        <td>${individualityMean}</td>
                                                                        <td>${individualityTotal}</td>
                                                                        <td>${individualityND}</td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td style="font-weight:bold">UCLA Loneliness</td>
                                                                        <td >${uclaTraitMean}</td>
                                                                        <td>${uclaTotal}</td>
                                                                        <td>${uclaTraitND}</td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td>L</td>
                                                                        <td>${lTraitMean}</td>
                                                                        <td >${lTraitTotal}</td>
                                                                        <td>${lTraitND}</td>
                                                                    </tr>

                                                                    <tr class="table_row">
                                                                        <td style="font-weight:bold">RSES</td>
                                                                        <td>${rsesTraitMean}</td>
                                                                        <td>${rsesTotal}</td>
                                                                        <td>${rsesTraitND}</td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td >sl</td>
                                                                        <td>${slAspectMean}</td>
                                                                        <td>${slAspectTotal}</td>
                                                                        <td>${slAspectND}</td>
                                                                    </tr>
                                                                    <tr class="table_row">
                                                                        <td >sc</td>
                                                                        <td>${scAspectMean}</td>
                                                                        <td>${scAspectTotal}</td>
                                                                        <td>${scAspectND}</td>
                                                                    </tr>

                                                                </tbody>
                                                            </table>                  
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Disposition Module Stop -->
                                        <!-- Behaviour Module Start -->
                                        <div class="panel-body">
                                            <h2 style="text-align: left; color:Black;">BEHAVIOUR DIMENSION</h2>
                                            <div class="panel-default">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <h3>Mind Likert Sum</h3>
                                                        <h4>${mindLinkertSum}</h4>
                                                        <h3>Mastery Sum</h3>
                                                        <h4>${masterySum}</h4>
                                                        <h3>Abstinence Sum</h3>
                                                        <h4>${abstinenceSum}</h4>
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
                                                        <h4>${question2Name}</h4>
                                                        <h6><strong>${question2Value1}</strong> </h6>
                                                        <h6><strong>${question2Value2}</strong></h6>
                                                        <c:forEach items="${question3Value}" var="qv" varStatus="loop" >
                                                            <c:if test="${loop.count==1}">
                                                                <h4>${qv.QUESTION_NAME}</h4>
                                                            </c:if>
                                                            <strong>${qv.QUESTION_OPTION_ADDITIONAL_VALUE}</strong>
                                                            <strong>${qv.QUESTION_OPTION_NAME}</strong>
                                                        </c:forEach>
                                                        <h4>${question4Name}</h4>
                                                        <h6><strong>${question4Value1}</strong></h6>
                                                        <h6><strong>${question4Value2}</strong></h6>
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
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="panel panel-default">
                                                            <div class="panel-heading">
                                                                <h3 class="panel-title">Mental Improvement Activities</h3>
                                                                <ul class="panel-controls" style="margin-top: 2px;">
                                                                    <li><a href="#" class="panel-fullscreen"><span class="fa fa-expand"></span></a></li>                                      
                                                                </ul>  
                                                            </div>
                                                            <div class="panel-body">
                                                                <div class="col-md-12 chart-holder">
                                                                    <div>
                                                                        <canvas id="mentalImprovementActivitiesPanel"></canvas>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <sec:authorize access="hasAuthority('ROLE_BF_CREATE_QUESTIONS')">
                                                    <div class="panel-body">
                                                        <h2 style="text-align: left; color:Black;">EXTENDED FACET</h2>

                                                        <div class="panel-default">
                                                            <div class="panel-body">
                                                                <div class="row">
                                                                    <div class="col-md-9 scrollable">
                                                                        <table class="table table-bordered">
                                                                            <thead> 
                                                                                <tr>
                                                                                    <th style="font-weight:bold">Aspect Name</th>
                                                                                    <th style="font-weight:bold">Average</th>
                                                                                    <th style="font-weight:bold">Test Percentage</th>
                                                                                </tr>
                                                                            </thead>
                                                                            <tbody>
                                                                                <c:forEach items="${extendedDossierForTraitList}" var="traitItem">
                                                                                    <tr>
                                                                                        <td style="color: black ; font-size: 15px; font-weight:bold; ">${traitItem.value.trait.name}</td>
                                                                                        <td>${traitItem.value.trait.currentMean}</td> 
                                                                                        <td>${traitItem.value.trait.normalDistribution}</td>   
                                                                                    </tr>
                                                                                    <c:forEach items="${extendedDossierList}" var="aspectItem">
                                                                                        <c:if test="${aspectItem.value.trait.id==traitItem.value.trait.id}">
                                                                                            <tr>
                                                                                                <td>${aspectItem.value.aspect.name}</td>
                                                                                                <td>${aspectItem.value.aspect.currentMean}</td> 
                                                                                                <td>${aspectItem.value.aspect.normalDistribution}</td>   
                                                                                            </tr>
                                                                                        </c:if>
                                                                                    </c:forEach> 
                                                                                </c:forEach>   

                                                                            </tbody>
                                                                        </table> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </sec:authorize> 
                                            </div>
                                        </form:form>
                                    </div>
                                    <!-- Behaviour Module Stop -->
                                </div>
                                <form:form name="pdfForm" id="pdfForm" method="POST" action="../pdf/newPdf.htm?takerId=${takerId}">
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
                <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
                <script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-select.js"></script>
                <script type="text/javascript" src="../js/plugins/datatables/jquery.dataTables.min.js"></script>    
                <!-- END THIS PAGE PLUGINS-->        

                <!-- START TEMPLATE -->
                <script type="text/javascript" src="../js/plugins.js"></script>        
                <script type="text/javascript" src="../js/actions.js"></script>
                <script type="text/javascript" src="../js/Chart.min.js"></script>

                <!-- END TEMPLATE -->
                <script type="text/javascript">
                    new Chart(document.getElementById("problemSolvingApproachGraph"), {
                        type: 'horizontalBar',
                        data: {
                            labels: ['3.Red', '4.Blue', '5.Crimson', '6. Sage', '7. Slate', '8.Violet', '9.Scarlet', '10.Azure'],
                            datasets: [
                                {
                                    label: [''],
                                    backgroundColor: '#3374FF',
                                    borderColor: '#3374FF ',
                                    borderWidth: 1,
                                    data: [${problemSolvingList}]
                                }
                            ]
                        },
                        options: {
                            scales: {
                                yAxes: [{gridLines: {display: false},
                                        afterFit: function (scale) {
                                            var chartWidth = scale.chart.width;
                                            var new_width = chartWidth * 0.15;
                                            scale.width = new_width;
                                        }
                                    }],
                                //, ticks: {fontSize: 10} 
                                xAxes: [{dispay: false, ticks: {beginAtZero: true, min: 0, max: 10}, gridLines: {display: false}}]
                            }
                        },
                        plugins: [{
                                beforeDraw: function (c) {
                                    var chartHeight = c.chart.height;
                                    c.scales['y-axis-0'].options.ticks.fontSize = chartHeight * 5 / 100; //fontSize: 6% of canvas height
                                }
                            }]
                    });
                    new Chart(document.getElementById("collaborativeGraph"), {
                        type: 'horizontalBar',
                        data: {
                            labels: ['3.Red', '4.Blue', '5.Crimson', '6. Sage', '7. Slate', '8.Violet', '9.Scarlet', '10.Azure'],
                            datasets: [
                                {
                                    label: [''],
                                    backgroundColor: '#3374FF',
                                    borderColor: '#3374FF',
                                    borderWidth: 1,
                                    data: [${collaborativeApproachList}]
                                }
                            ]
                        },
                        options: {
                            scales: {
                                yAxes: [{gridLines: {display: false},
                                        afterFit: function (scale) {
                                            var chartWidth = scale.chart.width;
                                            var new_width = chartWidth * 0.15;
                                            scale.width = new_width;
                                        }}],
                                xAxes: [{dispay: false, ticks: {beginAtZero: true, min: 0, max: 10}, gridLines: {display: false}}]
                            }
                        },
                        plugins: [{
                                beforeDraw: function (c) {
                                    var chartHeight = c.chart.height;
                                    c.scales['y-axis-0'].options.ticks.fontSize = chartHeight * 5 / 100; //fontSize: 6% of canvas height
                                }
                            }]
                    });
                    new Chart(document.getElementById("treatResponseApproachGraph"), {
                        type: 'horizontalBar',
                        data: {
                            labels: ['3.Red', '4.Blue', '5.Crimson', '6. Sage', '7. Slate', '8.Violet', '9.Scarlet', '10.Azure'],
                            datasets: [
                                {
                                    label: [''],
                                    backgroundColor: '#3374FF',
                                    borderColor: '#3374FF',
                                    borderWidth: 1,
                                    data: [${treatResponseApproachList}]
                                }
                            ]
                        },
                        options: {
                            scales: {
                                yAxes: [{gridLines: {display: false},
                                        afterFit: function (scale) {
                                            var chartWidth = scale.chart.width;
                                            var new_width = chartWidth * 0.15;
                                            scale.width = new_width;
                                        }}],
                                xAxes: [{dispay: false, ticks: {beginAtZero: true, min: 0, max: 10}, gridLines: {display: false}}]
                            }
                        },
                        plugins: [{
                                beforeDraw: function (c) {
                                    var chartHeight = c.chart.height;
                                    c.scales['y-axis-0'].options.ticks.fontSize = chartHeight * 5 / 100; //fontSize: 6% of canvas height
                                }
                            }]
                    });
                    new Chart(document.getElementById("mentalImprovementActivitiesPanel"), {
                        type: 'horizontalBar',
                        data: {
                            labels: ['Meditation', 'Podcasts', 'Challenging Productive Conversation', 'College', 'Graduate School', 'Internet Forums', 'Pursuing Doctorate', 'Gratitude Journaling', 'Listen Online Lectures', 'Psychedelics', 'Hours Spent Working On Mind'],
                            datasets: [
                                {
                                    label: [''],
                                    backgroundColor: '#3374FF',
                                    borderColor: '#3374FF',
                                    borderWidth: 1,
                                    data: [${mentalImproveListForGraph}]
                                }
                            ]
                        },
                        options: {
                            scales: {
                                yAxes: [{gridLines: {display: false},
                                        afterFit: function (scale) {
                                            var chartWidth = scale.chart.width;
                                            var new_width = chartWidth * 0.44;
                                            scale.width = new_width;
                                        }
                                    }],
                                xAxes: [{dispay: false, ticks: {beginAtZero: true, min: 0, max: 10}, gridLines: {display: false}}]
                            }
                        },
                        plugins: [{
                                beforeDraw: function (c) {
                                    var chartHeight = c.chart.height;
                                    c.scales['y-axis-0'].options.ticks.fontSize = chartHeight * 5 / 100; //fontSize: 6% of canvas height
                                }
                            }]
                    });
                </script>
                </body>
                </html>