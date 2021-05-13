<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 
  <title>Sollibyte payment options page</title>
  <meta name="description"
    content="Get efficiency in learner evaluation with sollibyte the best teacher aiding tool for kenyan competency based curriculum  made with the latest robust technology">

  <meta content="CBC competency based curriculum sollibyte kenyan education system" name="keywords">
   <link href="assets/img/favicon.jpg" rel="icon">
  

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

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

@media (min-width: 768px) {
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
    
  

</head>

<body>

  <div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
       <div class="sidebar-heading h4 text-info text-center">Solidarity</div>
            <div class="list-group list-group-flush">
                
      </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

      <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
        <button class="btn btn-primary" id="menu-toggle">Menu</button>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item active">
              <a class="nav-link  text-primary" href="verifyHeadTeacherDuplicate">Home <span class="sr-only">(current)</span></a>
            </li>
              <li class="nav-item"><a class="nav-link text-danger" href="logout">Logout</a></li>
          </ul>
        </div>
      </nav>

      <div class="container-fluid">
           
       <div class="row">  <!-- Page Heading -->
        <div 
          class="col-md-12" style="margin-top: 20px">
          <h1 class="h3 mb-0 text-danger text-center">Please note that payment should be made from 0-14 days after beginning of term. </h1>
        </div>
      </div>
        <div style="margin-top:40px;" class="row">
            <div class="col-lg-6">

            <!-- Dropdown Card Example -->
            <div class="card shadow mb-4">
              <div class="card-header py-3">
                <h4 class="m-0 font-weight-bold text-success text-center">First 
                  Payment</h4>
              </div>
              <div class="card-body">
                <p class="text-primary">
                  This payment approach is made by the head teacher on behalf of the whole school on a termly basis after collecting KES 100 per learner.
                  The Head teacher  does not recieve a kick back on this first payment,however,this is to compensate for the data entry of learner's previous performance on the various grades.
                </p>
                <p class="text-danger">
                  N/B:
                  Failing to pay the will disable generation of termly academic report generation</b>
                </p>
                <hr>
                 <h6 class="text-center text-success">Payment information</h6>
                  <table class="table table-sm">
                  <thead>
                    <tr>
                      <th scope="col">Detail</th>
                      <th scope="col">Number</th>
                    </tr>
                      
                  </thead>
                  <tbody>
                  
                    <tr>
                      <td>Pay Bill Number</td>
                      <td>12345</td>
                    </tr>
                     <tr>
                      <td>Account Number </td>
                      <td>0000${accountNumber}</td>
                    </tr>
                    <tr>
                      <td>Number of Learners</td>
                      <td>${numberOfLearners}</td>
                    </tr>
                    <tr>
                      <td>Amount Payable in KES</td>
                      <td>${amountPayable}</td>
                    </tr>
                  </tbody>
                </table>
                <hr>
                <br>
                <h6 class="text-center text-success">Price Break Down Per Learner</h6>
                <table class="table table-sm">
                  <thead>
                    <tr>
                      <th scope="col">Factor</th>
                      <th scope="col">Price Awarded</th>
                    </tr>
                      
                  </thead>
                  <tbody>
                  
                    <tr>
                      <td>System</td>
                      <td>50 KES</td>
                    </tr>
                     <tr>
                      <td>Data Entry</td>
                      <td>50 KES</td>
                    </tr>
                  </tbody>
                </table>

                <p class="text-danger">
                  Note :
                  On the first payment however the head teacher recieves 0 KES per learner this is to cater for previous data entry purposes.</b>
                </p>
                <a href="verifyHeadTeacherDuplicate" role="button" class="btn btn-danger btn-sm">Back</a>

              </div>
            </div>

          </div>

          

          <div class="col-lg-6">

            <!-- Dropdown Card Example -->
            <div class="card shadow mb-4">
              <div class="card-header py-3">
                <h4 class="m-0 font-weight-bold text-primary text-center">Continuous
                  payment</h4>
              </div>
              <div class="card-body">
                <p class="text-primary">
                  This payment approach is made by the head teacher on behalf of the whole school on a termly basis after collecting KES 100 per learner.
                  The Head teacher benefits from the money paid by the learners based on  the rates listed below.
                </p>
                <p class="text-danger">
                  N/B:
                  The payment should me made in full  so that the system distributes it</b>
                </p>
                <hr>
                 <h6 class="text-center text-success">Payment information</h6>
                  <table class="table table-sm">
                  <thead>
                    <tr>
                      <th scope="col">Detail</th>
                      <th scope="col">Number</th>
                    </tr>
                      
                  </thead>
                  <tbody>
                  
                    <tr>
                      <td>Pay Bill Number</td>
                      <td>12345</td>
                    </tr>
                     <tr>
                      <td>Account Number </td>
                      <td>0000${accountNumber}</td>
                    </tr>
                    <tr>
                      <td>Number of Learners</td>
                      <td>${numberOfLearners}</td>
                    </tr>
                    <tr>
                      <td>Amount Payable in KES</td>
                      <td>${amountPayable}</td>
                    </tr>
                  </tbody>
                </table>
                <hr>
                <br>
                <h6 class="text-center text-success">Price Break Down Per Learner</h6>
                <table class="table table-sm">
                  <thead>
                    <tr>
                      <th scope="col">Factor</th>
                      <th scope="col">Price Awarded</th>
                      <th>Period</th>
                    </tr>
                      
                  </thead>
                  <tbody>
                  
                    <tr>
                      <td>System</td>
                      <td>70 KES</td>
                      <td></td>

                    </tr>
                     <tr>
                      <td>Head Teacher</td>
                      <td>30 KES</td>
                      <td>0-7 days</td>
                    </tr>
                    <tr>
                      <td>Head Teacher</td>
                      <td>15 KES</td>
                      <td>7-14 days</td>
                    </tr>
                    <tr>
                      <td>Head Teacher</td>
                      <td>0 KES</td>
                      <td>past 14 days</td>
                    </tr>
                  </tbody>
                </table>

                <p class="text-danger">
                  Note : When the head teacher pays early he/she  recieves 30KES per learner  if between 0-7 days and 7-14 days awards 15 shillings per learner. 
                  
                </p>
                <a href="verifyHeadTeacherDuplicate" role="button" class="btn btn-danger btn-sm">Back</a>

              </div>
            </div>

          </div>
         
          
         
        </div>
    </div>
    <!-- /#page-content-wrapper -->

  </div>
  <!-- /#wrapper -->

  <!-- Bootstrap core JavaScript -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


  <!-- Menu Toggle Script -->
  <script>
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>

</body>

</html>
