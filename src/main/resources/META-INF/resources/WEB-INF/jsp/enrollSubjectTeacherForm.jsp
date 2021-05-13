<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Sollibyte enroll subject teacher form</title>
<meta name="description"
	content="Get efficiency in learner evaluation with sollibyte the best teacher aiding tool for kenyan competency based curriculum  made with the latest robust technology">

  <meta content="CBC competency based curriculum sollibyte kenyan education system" name="keywords">
   <link href="assets/img/favicon.jpg" rel="icon">


<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

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
<script type="text/javascript">
    
function validateform(){  
var schoolCode=document.myform.schoolCode.value; 
var first_name=document.myform.first_name.value; 
var second_name=document.myform.second_name.value; 
var last_name=document.myform.last_name.value; 
var phoneNumber=document.myform.phoneNumber.value; 
var password=document.myform.password.value; 

if (schoolCode==null || schoolCode==""){  
   document.getElementById("txt").innerHTML = "Please fill in the blank!";
  return false;  
}

else if (first_name==null || first_name==""){  
   document.getElementById("txt4").innerHTML = "Please fill in the blank!";
  return false;  
}
else if (second_name==null || second_name==""){  
   document.getElementById("txt5").innerHTML = "Please fill in the blank!";
  return false;  
}
else if (last_name==null || last_name==""){  
   document.getElementById("txt6").innerHTML = "Please fill in the blank!";
  return false;  
}
else if (phoneNumber==null || phoneNumber==""){  
   document.getElementById("txt7").innerHTML = "Please fill in the blank!";
  return false;  
}
else if (password==null || password==""){  
   document.getElementById("txt8").innerHTML = "Please fill in the blank!";
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
            <div class="sidebar-heading"><p class="h4 text-center text-info">Solidarity</p></div>
            <div class="list-group list-group-flush">
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
                        
                        <li class="nav-item"><a class="nav-link text-danger" href="logout">logout</a>
                        </li>
                        
                    </ul>
                </div>
            </nav>

            <div class="container">
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <h4 class="text-center text-info">Enroll Subject Teacher</h4>
                        <p>${msgThree}</p>
                        <form action="saveSubjectTeacher" method="post" name="myform" onsubmit="return validateform()">
                            <div class="form-group">
                                <label for="phone-number">Enter School Code</label> <input
                                    type="text" class="form-control" id="phone-number"
                                    placeholder="Enter school Code number" name="schoolCode">
                                <p class="text-danger">
                                <span id="txt" style="color:red"> </span> 
                                ${msgOne}</p>
                            </div>

                            <div class="form-group">
                                <label for="username">Enter First Name </label> <input
                                    type="text" class="form-control" id="username"
                                    placeholder="Enter first name e.g john" name="first_name">
                                    <span id="txt4" style="color:red"> </span> 
                            </div>
                            <div class="form-group">
                                <label for="username">Enter Second Name </label> <input
                                    type="text" class="form-control" id="username"
                                    placeholder="Enter Second Name e.g william" name="second_name">
                                    <span id="txt5" style="color:red"> </span> 
                            </div>
                            <div class="form-group">
                                <label for="username">Enter Last Name </label> <input
                                    type="text" class="form-control" id="username"
                                    placeholder="Enter Last Name e.g bahati" name="last_name">
                                    <span id="txt6" style="color:red"> </span> 
                            </div>
                            <div class="form-group">
                                <label for="username">Enter Phone Number </label> <input
                                    type="text" class="form-control" id="username"
                                    placeholder="Enter phone number please use safaricom"
                                    name="phoneNumber">
                                    <span id="txt7" style="color:red"> </span> 
                            </div>
                            <div class="form-group">
                                <label for="username">Enter Password </label> <input
                                    type="password" class="form-control" id="username"
                                    placeholder="Enter password" name="password">
                                    <span id="txt8" style="color:red"> </span> 
                            </div>
                            <a class="btn btn-danger   btn-sm" href="controlVerification" role="button"> <i class="fa fa-arrow-left" aria-hidden="true"></i>   Back</a>

                            <button type="submit" class="btn btn-sm btn-primary">Submit</button>
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
