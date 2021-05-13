<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no">


<title>Sollibyte download with learners page</title>
<meta name="description"
	content="Get efficiency in learner evaluation with sollibyte the best teacher aiding tool for kenyan competency based curriculum  made with the latest robust technology">

  <meta content="CBC competency based curriculum sollibyte kenyan education system" name="keywords">
   <link href="assets/img/favicon.jpg" rel="icon">


<!-- Bootstrap core CSS -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
    src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- Custom styles for this template -->
<style type="text/css">
#wrapper {
    overflow-x: hidden;
}

#sidebar-wrapper {
    min-height: 100vh;
    margin-left: -15rem;
    -webkit-transition: margin .25s ease-out;
    -moz-transition: margin .25s ease-out;
    -o-transition: margin .25s ease-out;
    transition: margin .25s ease-out;
}

#sidebar-wrapper .sidebar-heading {
    padding: 0.875rem 1.25rem;
    font-size: 1.2rem;
}

#sidebar-wrapper .list-group {
    width: 15rem;
}

#page-content-wrapper {
    min-width: 100vw;
}

#wrapper.toggled #sidebar-wrapper {
    margin-left: 0;
}

@media ( min-width : 768px) {
    #sidebar-wrapper {
        margin-left: 0;
    }
    #page-content-wrapper {
        min-width: 0;
        width: 100%;
    }
    #wrapper.toggled #sidebar-wrapper {
        margin-left: -15rem;
    }
}
</style>

<style type="text/css">
.search-table {
    padding: 10%;
    margin-top: -6%;
}

.search-box {
    background: #87CEFA;
    /*border: 1px solid #ababab;*/
    padding: 3%;
}

.search-box input:focus {
    box-shadow: none;
    /*border:2px solid #eeeeee;*/
}

.search-list {
    background: #fff;
    /*border: 1px solid #ababab;*/
    border-top: none;
}

.search-list h3 {
    background: red;
    padding: 3%;
    margin-bottom: 0%;
}
</style>



</head>

<body>

    <div class="d-flex" id="wrapper">

        <!-- Sidebar -->
        <div class="bg-light border-right" id="sidebar-wrapper">
            <div class="sidebar-heading h4 text-center text-success">Solidarity</div>
            <div class="list-group list-group-flush">
                <a href="enrollLearningArea"
                    class="list-group-item list-group-item-action bg-light text-primary">Register
                    Learning Area</a> <a href="enrollLearner"
                    class="list-group-item list-group-item-action bg-light text-primary">Enroll
                    Learner </a>
                    
                    <a href="acceptLearner"
                    class="list-group-item list-group-item-action bg-light text-primary">Accept
                    Learner </a>
                    
                    <a href="showLearningAreaListPage"
                    class="list-group-item list-group-item-action bg-light text-primary">Match learners to learning area
                    </a>
                    
                    <a href="startTerm"
                    class="list-group-item list-group-item-action bg-light text-primary">Start Term</a>
                    
                    <a href="endTermRequest"
                    class="list-group-item list-group-item-action bg-light text-primary">End Term</a>
                    
                     <a href="downloadAcademicReport"
                    class="list-group-item list-group-item-action bg-light text-primary">Generate Academic Reports</a>
                    <a href="nextGradeRequest"
                    class="list-group-item list-group-item-action bg-light text-primary">Move to next Grade</a>
                     <a href="transferLearner"
                    class="list-group-item list-group-item-action bg-light text-primary">Transfer
                    Learner</a>
            </div>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">

            <nav
                class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                <button class="btn btn-primary" id="menu-toggle">Toggle
                    Menu</button>

                <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                        <li class="nav-item active"><a class="nav-link" href="verifyClassTeacherDuplicate">Home
                                <span class="sr-only">(current)</span>
                        </a></li>
                        <li class="nav-item"><a class="nav-link" href="logout">logout</a></li>
                        
                    </ul>
                </div>
            </nav>

            <div class="container-fluid">
            <div class="row">
                    <div class="container search-table">
                    <h3 class="text-center text-success">Grade: ${grade.gradeNumber} ${grade.gradeStream} ${grade.year}</h3>
                    <h4 class="text-cemter text-success">Term: ${term}</h4>
                    <a class="btn btn-danger   btn-sm" href="verifyClassTeacherDuplicate" role="button"> <i class="fa fa-arrow-left" aria-hidden="true"></i>   Back</a>
                    <div class="search-box">
                        <div class="row">
                            <div class="col-md-3">
                                <h5>Search All Fields</h5>
                            </div>
                            <div class="col-md-9">
                                <input type="text" id="myInput" onkeyup="myFunction()"
                                    class="form-control" placeholder="Search learner's name">
                                <script>
                            $(document).ready(function () {
                                $("#myInput").on("keyup", function () {
                                    var value = $(this).val().toLowerCase();
                                    $("#myTable tr").filter(function () {
                                        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                                    });
                                });
                            });
                        </script>
                            </div>
                        </div>
                    </div>
                    <div class="search-list">
                        <h5 class="text-center text-primary">List of learners</h5>
                        <form>
                            <table class="table table-striped table-bordered table-sm table-hover"
                                id="myTable">
                                <thead>
                                    <tr>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Action</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${learners}" var="learner">

                                        <tr>

                                            <td>${learner.firstName}</td>
                                            <td>${learner.surName}</td>
                                            <td><a href="downloadReport?learnerId=${learner.learnerId}">Download</a></td>

                                        </tr>

                                    </c:forEach>

                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </div>


            </div>
            <!-- /#page-content-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- Bootstrap core JavaScript -->
        <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


        <!-- Menu Toggle Script -->
        <script>
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>
</body>

</html>
