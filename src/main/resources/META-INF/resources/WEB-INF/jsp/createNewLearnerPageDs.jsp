<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Sollibyte create new learner page </title>
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
<script>  
function validateform(){  
var nemissNumber=document.myform.nemissNumber.value; 
var firstName=document.myform.firstName.value;
var secondName=document.myform.secondName.value;
var surName=document.myform.surName.value;  

if (nemissNumber==null ||nemissNumber ==""){  
   document.getElementById("txt").innerHTML = "Please fill in the blank !";
  return false;  
}

else if(firstName ==null || firstName ==""){
  document.getElementById("txt2").innerHTML = "Please fill in the blank !";
  return false;
}
  else if(secondName==null || secondName==""){
  document.getElementById("txt3").innerHTML = "Please in the blank !";
  return false
}

  else if(surName==null || surName==""){
  document.getElementById("txt4").innerHTML = "Please in the blank !";
  return false
}

else{
    return true;
}

}  
</script>


</head>
<body>
    <div class="row main">

        <div class="col-md-3"></div>
        <div class="col-lg-6">

            <!-- Dropdown Card Example -->
            <div class="card shadow mb-4">
                <a class="btn btn-primary   btn-sm" href="selectGradeReplica" role="button">Back</a>
                <div class="card-header py-3">
                    <h4 class="text-center text-success">Create new learner</h4>
                </div>
                <div class="card-body">
                    <form action="saveNewLearnerDs" name="myform" method="post" onsubmit="return validateform()">
                    <p class="text-danger">${msg}</p>
                            <div class="form-group">
                                <label for="phone-number">Enter Nemiss Number</label> <input
                                    type="text" class="form-control" id="phone-number"
                                    placeholder="Nemiss number eg 1234567 from ministry of education" name="nemissNumber">
                                    <span id="txt" style="color:red"> </span> 
                                    <p class="text-danger">${msgOne}</p>
                            </div>
                            <div class="form-group">
                                <label for="username">Enter First Name </label> <input
                                    type="text" class="form-control" id="username"
                                    placeholder="Enter First Name e.g John" name="firstName">
                                    <span id="txt2" style="color:red"> </span> 
                                    <p class="text-danger">${msgTwo}</p>
                            </div>
                            <div class="form-group">
                                <label for="username">Enter Second Name </label> <input
                                    type="text" class="form-control" id="username"
                                    placeholder="Enter second name e.g William" name="secondName">
                                    <span id="txt3" style="color:red"> </span> 
                            </div>
                            <div class="form-group">
                                <label for="username">Enter Surname </label> <input
                                    type="text" class="form-control" id="username"
                                    placeholder="Enter Surname e.g Doe" name="surName">
                                    <span id="txt4" style="color:red"> </span> 
                            </div>
                            
                            <button type="submit" class="btn btn-primary btn-sm">Submit</button>
                        </form>


                    <div class="row">
                        <div class="col-md-12"></div>
                    </div>
                </div>
            </div>

        </div>


    </div>
    <div class="col-md-3"></div>



</body>
</html>