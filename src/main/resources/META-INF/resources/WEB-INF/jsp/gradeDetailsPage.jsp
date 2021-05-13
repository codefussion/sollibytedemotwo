<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Sollibyte grade details page</title>
<meta name="description"
	content="Get efficiency in learner evaluation with sollibyte the best teacher aiding tool for kenyan competency based curriculum  made with the latest robust technology">

<meta
	content="CBC competency based curriculum sollibyte kenyan education system"
	name="keywords">
<link href="assets/img/favicon.jpg" rel="icon">


<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

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

<script>
    $(document).ready(
            function() {
                $("#myInput").on(
                        "keyup",
                        function() {
                            var value = $(this).val().toLowerCase();
                            $("#myTable tr").filter(
                                    function() {
                                        $(this).toggle(
                                                $(this).text().toLowerCase()
                                                        .indexOf(value) > -1)
                                    });
                        });
            });
</script>

</head>

<body>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<div class="bg-light border-right" id="sidebar-wrapper">
			<div class="sidebar-heading h4 text-info text-center">Solidarity</div>
			<div class="list-group list-group-flush"></div>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">

			<nav
				class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
				<button class="btn btn-primary" id="menu-toggle">Menu</button>

				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">

					<ul class="navbar-nav ml-auto mt-2 mt-lg-0">
						<li class="nav-item active"><a class="nav-link  text-primary"
							href="verifyHeadTeacherDuplicate">Home <span class="sr-only">(current)</span></a>
						</li>
						<li class="nav-item"><a class="nav-link text-danger"
							href="logout">Logout</a></li>
					</ul>
				</div>
			</nav>

			<div class="container-fluid">
				<!-- Page Heading -->
				<div
					class="d-sm-flex align-items-center justify-content-between mb-4">
					<h1 class="h3 mb-0 text-gray-800">${grade.school.schoolName}</h1>
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
											Number of Learners</div>
										<div class="h5 mb-0 font-weight-bold text-gray-800">${learnersNumber}</div>
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
											Number of teachers</div>
										<div class="h5 mb-0 font-weight-bold text-gray-800">${subjectTeacherNumber}</div>
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
											class="text-xs font-weight-bold text-info text-uppercase mb-1">Number
											of Learning Areas</div>
										<div class="row no-gutters align-items-center">
											<div class="col-auto">
												<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${learningAreaNumber}</div>
											</div>
											<div class="col">
												<div class="progress progress-sm mr-2">
													<div class="progress-bar bg-info" role="progressbar"
														style="width: 50%" aria-valuenow="50" aria-valuemin="0"
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
											Grade Id</div>
										<div class="h5 mb-0 font-weight-bold text-gray-800">0000${grade.gradeId}</div>
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

					<div class="col-lg-6">

						<!-- Default Card Example -->
						<div class="card shadow mb-4">
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-warning">Account
									Details</h6>
							</div>
							<div class="card-body">
								<p>
									<b class="text-info">Class Teacher:</b>${grade.class_teacher.first_name}
									${grade.class_teacher.last_name}
								</p>
								<p>
									<b class="text-info">Role:</b> Class Teacher
								</p>
								<p>
									<b class="text-info">School:</b> ${grade.school.schoolId}
								</p>
							</div>
						</div>


						<!-- Basic Card Example -->
						<div class="card shadow mb-4">
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-primary">Learning
									Areas</h6>
							</div>
							<div class="card-body">
								<table class="table table-sm">
									<thead>
										<tr>
											<th scope="col">Learning Area Name</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${learningAreas}" var="learningArea">
											<tr>
												<td scope="row"><a href="">
														${learningArea.learningAreaName}</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>

					</div>

					<div class="col-lg-6">

						<!-- Dropdown Card Example -->
						<div class="card shadow mb-4">
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-primary">Performance
									Graph</h6>
							</div>
							<div class="card-body">
								<img alt="" class="img-fluid" src="barGraph.jpeg">
							</div>
						</div>


						<!-- Collapsable Card Example -->
						<div class="card shadow mb-4">
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-primary">Learners In
									Grade</h6>
							</div>
							<div class="card-body">
								<div class="container search-table">
									<div class="search-box">
										<div class="row">
											<div class="col-md-3"></div>
											<div class="col-md-9">
												<input type="text" id="myInput" onkeyup="myFunction()"
													class="form-control" placeholder="Search learner's name">

											</div>
										</div>
									</div>
									<div class="search-list">
										<h5 class="text-center text-primary">List of learners</h5>
										<form>
											<table
												class="table table-striped table-bordered table-sm table-hover"
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
															<td><a
																href="viewLearnerProfile?learnerId=${learner.learnerId}">view</a></td>

														</tr>

													</c:forEach>

												</tbody>
											</table>
										</form>
									</div>
								</div>
							</div>
						</div>


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
