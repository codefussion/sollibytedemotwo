<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  
  <title>Sollibyte enroll school form</title>
  <meta name="description"
	content="Get efficiency in learner evaluation with sollibyte the best teacher aiding tool for kenyan competency based curriculum  made with the latest robust technology">

  <meta content="CBC competency based curriculum sollibyte kenyan education system" name="keywords">
   <link href="assets/img/favicon.jpg" rel="icon">
  

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

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
<script type="text/javascript">
    
function validateform(){  
var phoneNumber=document.myform.phoneNumber.value; 
var countyName=document.myform.countyName.value; 
var schoolName=document.myform.schoolName.value; 
var codeNumber=document.myform.codeNumber.value; 


if (phoneNumber==null || phoneNumber==""){  
   document.getElementById("txt").innerHTML = "Please fill in the blank!";
  return false;  
}
else if (countyName==null || countyName==""){  
   document.getElementById("txt2").innerHTML = "Please fill in the blank!";
  return false;  
}
else if (schoolName==null || schoolName==""){  
   document.getElementById("txt3").innerHTML = "Please fill in the blank!";
  return false;  

}
else if (codeNumber==null || codeNumber==""){  
   document.getElementById("txt4").innerHTML = "Please fill in the blank!";
  return false;  
  
}

else
{
    return true;
}

}  
</script>

    
  

</head>

<body>

  <div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading"><p class="text-center text-info h4">Solidarity</p></div>
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
            
            <li class="nav-item">
              <a class="nav-link text-danger" href="logout">Logout</a>
            </li>
            
          </ul>
        </div>
      </nav>

<div class="container">
  <div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">
      <h4 class="text-center text-info">Enroll School</h4>
      
  <form action="saveSchool" name="myform" onsubmit="  return validateform()" method="post">
       <p class="text-danger">${msg}</p>
    <div class="form-group">
      <label for="username">Confirm your phone number </label>
      <input type="text" class="form-control" id="username" placeholder="Enter phone number" name="phoneNumber">
      <span id="txt" style="color: red"></span>
      
    </div>
    <div class="form-group">
      <label for="business-name">Enter County</label>
      <input type="text" class="form-control" id="business-name" placeholder="Enter county" name="countyName">
      <span id="txt2" style="color: red"></span>
    </div>
    <div class="form-group">
      <label for="phone-number">Enter School Name</label>
      <input type="text" class="form-control" id="phone-number" placeholder="Enter school name" name="schoolName">
      <span id="txt3" style="color: red"></span>
    </div>
    <div class="form-group">
      <label for="email">Enter School Code Number</label>
      <input type="text" class="form-control" id="email" placeholder="Enter school  code number" name="codeNumber">
      <span id="txt4" style="color: red"></span>
    </div>
    <a class="btn btn-danger   btn-sm" href="controlVerification" role="button"> <i class="fa fa-arrow-left" aria-hidden="true"></i>   Back</a>
    <button type="submit" class="btn btn-primary btn-sm">Submit</button>
  </form>
    </div>
    <div class="col-md-3"></div>
    
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
