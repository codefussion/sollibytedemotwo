<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Sollibyte  enroll class teacher form </title>
<meta name="description"
    content="Get efficiency in learner evaluation with sollibyte the best teacher aiding tool for kenyan competency based curriculum  made with the latest robust technology">

  <meta content="CBC competency based curriculum sollibyte kenyan education system" name="keywords">
   <link href="assets/img/favicon.jpg" rel="icon">


<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
    href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
    integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
    crossorigin="anonymous">
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
var gradeNumber=document.myform.gradeNumber.value; 
var streamName=document.myform.streamName.value; 
var first_name=document.myform.first_name.value; 
var second_name=document.myform.second_name.value; 
var last_name=document.myform.last_name.value; 
var phoneNumber=document.myform.phoneNumber.value; 
var password=document.myform.password.value; 

if (schoolCode==null || schoolCode==""){  
   document.getElementById("txt").innerHTML = "Please fill in the blank!";
  return false;  
}
else if (gradeNumber==null || gradeNumber==""){  
   document.getElementById("txt2").innerHTML = "Please fill in the blank!";
  return false;  
}
else if (streamName==null || streamName==""){  
   document.getElementById("txt3").innerHTML = "Please fill in the blank!";
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
            <div class="sidebar-heading">
                <p class="text-info h4 text-center">Solidarity</p>
            </div>
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

                        <li class="nav-item"><a class="nav-link text-danger"
                            href="logout">logout</a></li>

                    </ul>
                </div>
            </nav>

            <div class="container">
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <h4 class="text-center text-info">Enroll Class Teacher</h4>
                        <form action="saveClassTeacher" method="post" name="myform"
                            onsubmit="return validateform()">
                            <p class="text-danger">${msgThree}</p>
                            <div class="form-group">
                                <label for="phone-number">Enter School Code</label> <input
                                    type="text" class="form-control" id="phone-number"
                                    placeholder="Enter school Code number" name="schoolCode">
                                <span id="txt" style="color: red"> ${msgOne}</span> 
                            </div>
                            <div class="form-group">
                                <label for="email">Enter Grade Number</label> <select name="gradeNumber"
                                    class="form-control">
                                    <optgroup label="Pre-primary">
                                    <option value="1">PP-1</option>
                                    <option value="2">PP-2</option>
                                    </optgroup>
                                    <optgroup label="Lower primary">
                                    <option selected="selected" value="3">Grade 1</option>
                                    <option value="4">Grade 2</option>
                                    <option value="5">Grade 3</option>
                                </optgroup>
                                <optgroup label="Upper primary">
                                    <option value="6">Grade 4</option>
                                    <option value="7">Grade 5</option>
                                    <option value="8">Grade 6</option>
                                </optgroup>

                                 <optgroup label="Lower secondary">
                                    <option value="9">Grade 7</option>
                                    <option  value="10">Grade 8</option>
                                    <option value="11">Grade 9</option>
                                </optgroup>
                                <optgroup label="Senior secondary">
                                    <option value="12">Grade 10</option>
                                    <option value="13">Grade 11</option>
                                    <option value="14">Grade 12</option>
                                </optgroup>
                                </select>
                               
                            </div>
                            <div class="form-group">
                                <label for="username">Enter Grade Stream </label> <input
                                    type="text" class="form-control" id="username"
                                    placeholder="Enter grade Stream if available e.g orange or One  when unavailable"
                                    name="streamName"> <span id="txt3" style="color: red">
                                        ${msgTwo}
                                </span> 
                            </div>
                            <div class="form-group">
                                <label for="username">Enter Year </label> <select name="year"
                                    class="form-control">
                                    <option value="2030">2030</option>
                                    <option value="2029">2029</option>
                                    <option value="2028">2028</option>
                                    <option value="2027">2027</option>
                                    <option value="2026">2026</option>
                                    <option value="2025">2025</option>
                                    <option value="2024">2024</option>
                                    <option value="2023">2023</option>
                                    <option value="2022">2022</option>
                                    <option selected="selected" value="2021">2021</option>
                                    <option value="2020">2020</option>
                                    <option value="2019">2019</option>
                                    <option value="2018">2018</option>
                                    <option value="2017">2017</option>
                                    <option value="2016">2016</option>
                                    <option value="2015">2015</option>
                                    <option value="2014">2014</option>
                                    <option value="2013">2013</option>
                                    <option value="2012">2012</option>
                                    <option value="2011">2011</option>
                                    <option value="2010">2010</option>
                                    <option value="2009">2009</option>
                                    <option value="2008">2008</option>
                                    <option value="2007">2007</option>
                                    <option value="2006">2006</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="username">Enter First Name </label> <input
                                    type="text" class="form-control" id="username"
                                    placeholder="Enter first name e.g john" name="first_name">
                                <span id="txt4" style="color: red"> </span>
                            </div>
                            <div class="form-group">
                                <label for="username">Enter Second Name </label> <input
                                    type="text" class="form-control" id="username"
                                    placeholder="Enter Second Name e.g william" name="second_name">
                                <span id="txt5" style="color: red"> </span>
                            </div>
                            <div class="form-group">
                                <label for="username">Enter Last Name </label> <input
                                    type="text" class="form-control" id="username"
                                    placeholder="Enter Last Name e.g bahati" name="last_name">
                                <span id="txt6" style="color: red"> </span>
                            </div>
                            <div class="form-group">
                                <label for="username">Enter Phone Number </label> <input
                                    type="text" class="form-control" id="username"
                                    placeholder="Enter phone number please use safaricom"
                                    name="phoneNumber"> <span id="txt7" style="color: red">
                                </span>
                            </div>
                            <div class="form-group">
                                <label for="username">Enter password </label> <input type="text"
                                    class="form-control" id="username" placeholder="Enter password"
                                    name="password"> <span id="txt8" style="color: red">
                                </span>
                            </div>
                            <a class="btn btn-danger   btn-sm" href="controlVerification"
                                role="button"> <i class="fa fa-arrow-left"
                                aria-hidden="true"></i> Back
                            </a>
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
