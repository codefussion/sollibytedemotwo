<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Sollibyte delete account confirmation page </title>
<meta name="description"
	content="Get efficiency in learner evaluation with sollibyte the best teacher aiding tool for kenyan competency based curriculum  made with the latest robust technology">

  <meta content="CBC competency based curriculum sollibyte kenyan education system" name="keywords">
   <link href="assets/img/favicon.jpg" rel="icon">


<!-- Bootstrap core CSS -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
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
            <div class="sidebar-heading"><p class="text-info h4 text-center">Solidarity</p></div>
            <div class="list-group list-group-flush">
                <a href="showGrades?confirm=${false}"
                    class="list-group-item list-group-item-action  text-primary bg-light">Select
                    your Grade(s)</a> <a href="selectLearningAreaBySubjectTeacher"
                    class="list-group-item list-group-item-action bg-light text-primary">Select
                    your learning Areas</a> <a href="showAssessLearnerFirstForm"
                    class="list-group-item list-group-item-action bg-light text-primary">Assess
                    your Learners</a>
            </div>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">

            <nav
                class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                <button class="btn btn-primary" id="menu-toggle">
                    Menu</button>

                <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                        <li class="nav-item active"><a class="nav-link text-primary" href="verifySubjectTeacherDuplicate">Home
                                <span class="sr-only">(current)</span>
                        </a></li>
                        <li class="nav-item"><a class="nav-link text-danger" href="logout">logout</a></li>
                        
                    </ul>
                </div>
            </nav>

            <div class="container-fluid">

                <div class="col-md-3"></div>
                <div class="container col-md-6">


                    <div class="row text-center">
                        <div>
                            <br>
                            <br>
                            <h2 style="color: #0fad00">Delete Account?</h2>
                            <h3 class="text-danger">Warning !</h3>
                            <br>
                            <p style="font-size: 20px; color: #5C5C5C;">Deleting  the account 
                                will delete all user information do you want to continue?
                            </p>
                            <br>
                            <h4 class="text-danger">This action cannot be undone and you will not be able to login</h4>
                            <br> <a href="deleteSubjectTeacherAction" class="btn btn-danger btn-sm">Yes <i class="fa fa-arrow-right" aria-hidden="true"></i> </a> <br>
                            <br>
                            <a class="btn btn-info   btn-sm" href="verifySubjectTeacherDuplicate" role="button"> <i class="fa fa-arrow-left" aria-hidden="true"></i>   Back</a>
                            <br>
                        </div>


                    </div>
                </div>


                <div class="col-md-3"></div>
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
