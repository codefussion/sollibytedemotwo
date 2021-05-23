<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>

<title>Sollibyte admin first page</title>
<meta name="description"
	content="Get efficiency in learner evaluation with sollibyte the best teacher aiding tool for kenyan competency based curriculum  made with the latest robust technology">

  <meta content="CBC competency based curriculum sollibyte kenyan education system" name="keywords">

<meta name="description"
    content="Get the best online deals in Kisumu with a wide range of products from smartphones, laptops, 
    make up kits, dresses, shoes, baby walkers, food, automobiles, computer accessories, phone accessories,
     vehicle spare parts. Building equipment’s, all in Kisumu offers">

<link rel="icon" href="./img/favicon.jpg" type="image/jpg">


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






<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">

<!-- the style -->
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
    a {
        color: aliceblue;
    }
}
</style>


<!-- this is for the table -->
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
            <div class="sidebar-heading"><p class="text-center text-danger h4">SolliByte</p></div>
            <div class="list-group list-group-flush">
                <a href="dataSpecialistProfiles" class="list-group-item list-group-item-action bg-light text-primary">Data Specialist Status</a>
                <a href="enrollerProfiles" class="list-group-item list-group-item-action bg-light text-primary">Enroller Status</a>
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
                        <li class="nav-item active"><a class="nav-link text-primary" href="logout">Home
                                <span class="sr-only">(current)</span>
                        </a></li>
                        <li class="nav-item"><a class="nav-link text-danger" href="logout">logout</a>
                        </li>

                    </ul>
                </div>
            </nav>

            <div class="container-fluid">

                <!-- Page Heading -->
                <div
                    class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Welcome to your wall</h1>
                </div>

                <div class="row">

                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div
                                            class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            Number of schools</div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">${schoolNumber}</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Earnings (Annual) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div
                                            class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                            Number of Learners</div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">${learnerNumber}</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Tasks Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div
                                            class="text-xs font-weight-bold text-info text-uppercase mb-1">Account
                                            Type</div>
                                        <div class="row no-gutters align-items-center">
                                            <div class="col-auto">
                                                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">Administrator</div>
                                            </div>
                                            <div class="col">
                                                <div class="progress progress-sm mr-2">
                                                    <div class="progress-bar bg-info" role="progressbar"
                                                        style="width: 50%" aria-valuenow="0" aria-valuemin="0"
                                                        aria-valuemax="100"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Pending Requests Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-warning shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div
                                            class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                            Name</div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">${admin.first_name}</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-comments fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="container search-table">
                        <h4 class="text-center text-danger"> School Status</h4>
                        <span style="margin-bottom:20px;">
                         <a class="btn btn-danger" href="resetAllPayments" role="button">Reset all payments</a>
                         </span>
                        <div class="search-box">
                            <div class="row">
                                <div class="col-md-3">
                                    <h5>Search All Fields</h5>
                                </div>
                                <div class="col-md-9">
                                    <input type="text" id="myInput" onkeyup="myFunction()"
                                        class="form-control" placeholder="Search ">
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

                            <form>
                            
                                <table class="table table-striped table-bordered  table-hover"
                                    id="myTable">
                                    <thead>
                                        <tr>
                                            <th>School Name</th>
                                            <th>Code</th>
                                            <th>HTR contacts</th>
                                            <th>Paid</th>
                                            <th>Verify</th>
                                        </tr>

                                    </thead>
                                    <tbody>

                                        <c:forEach items="${schools}" var="school">
                                            <tr>
                                                <td><c:out value="${school.schoolName}" /></td>
                                                <td><c:out value="${school.schoolId}" /></td>
                                                <td><c:out value="${school.head_teacher.phoneNumber}" /></td>
                                                <td><c:out value="${school.paid}" /></td>
                                                <td><a class="btn btn-sm btn-primary" href="verifySchoolPayment?schoolId=${school.schoolId}" role="button">Verify payment</a></td>
                                                
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
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Menu Toggle Script -->
        <script>
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>
</body>

</html>
