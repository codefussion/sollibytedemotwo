<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>

<title>Sollibyte data specialist profiles page</title>
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
                <div class="row">
                    <div class="container search-table">
                        <h4 class="text-center text-danger"> Data specialist Status</h4>
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
                                            <th>Name</th>
                                            <th>Id</th>
                                            <th>phone</th>
                                            <th>Approved</th>
                                            <th>Approve</th>
                                            <th>Dissaprove</th>
                                        </tr>

                                    </thead>
                                    <tbody>

                                        <c:forEach items="${dataSpecialists}" var="dataSpecialist">
                                            <tr>
                                                <td><c:out value="${dataSpecialist.lastName}" /></td>
                                                <td><c:out value="${dataSpecialist.idNumber}" /></td>
                                                <td><c:out value="${dataSpecialist.phoneNumber}" /></td>
                                                <td><c:out value="${dataSpecialist.approved}" /></td>
                                                <td><a class="text-success" href="approveDataSpecialist?phoneNumber=${dataSpecialist.phoneNumber}" role="">Approve</a></td>
                                                <td><a class="text-danger" href="dissapproveDataSpecialist?phoneNumber=${dataSpecialist.phoneNumber}" role="">Dissapprove</a></td>
                                                
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
