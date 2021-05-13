<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Sollibyte grade list page</title>
<meta name="description"
	content="Get efficiency in learner evaluation with sollibyte the best teacher aiding tool for kenyan competency based curriculum  made with the latest robust technology">

  <meta content="CBC competency based curriculum sollibyte kenyan education system" name="keywords">
   <link href="assets/img/favicon.jpg" rel="icon">




<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
    src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<style type="text/css">
.main {
    margin-top: 10%;
}

.mainTwo {
    background-color: #ffe6e6;
}
</style>

</head>
<body>
    <div class="row main">

        <div class="col-md-3"></div>
        <div class="col-lg-6">

                        <!-- Dropdown Card Example -->
                        <div class="card shadow mb-4" style="margin-top:20px;">
                          <a class="btn btn-primary   btn-sm "  href="dataSpecialistLoginVerificationDuplicate" role="button">Back</a>
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary text-center">${school.schoolName}</h6>
                            </div>
                            <div class="card-body">

                                <table class="table table-sm">
                                    <thead>
                                        <tr>
                                            <th scope="col">Grade</th>
                                            <th scope="col">Stream</th>
                                            <th scope="col">Year</th>
                                        </tr>
                                            
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${grades}" var="grade">
                                        <tr>
                                            <td><a href="selectGrade?gradeId=${grade.gradeId}">${grade.gradeDisplayNumber}</a></td>
                                            <td><a href="selectGrade?gradeId=${grade.gradeId}">${grade.gradeStream}</a></td>
                                            <td><a href="selectGrade?gradeId=${grade.gradeId}">${grade.year}</a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div class="row"> 
                                 <div class="col-md-12">
                                     
                                  <a class="btn btn-success  text-right  btn-sm" href="createNewGrade?schoolId=${school.schoolId}" role="button">Do not see grade? Click here to create</a>   
                                 </div>                             
                                </div>
                            </div>
                        </div>

                    </div>


        </div>
        <div class="col-md-3"></div>

    </div>

</body>
</html>