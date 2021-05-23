<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Sollibyte enter result page</title>
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
var learningAreaName=document.myform.learningAreaName.value; 

if (learningAreaName==null ||learningAreaName ==""){  
   document.getElementById("txt").innerHTML = "Please fill in the blank !";
  return false;  
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
                    <h4 class="text-center text-success">Enter Result </h4>
                </div>
                <div class="card-body">
                    <form action="saveLearningAreaDs" name="myform" method="post" onsubmit="return validateform()">
                        <p class="text-success">${msg}</p>
                            <div class="form-group">
                                <label for="phone-number">Enter Learning Area Name</label> <input
                                    type="text" class="form-control" id="phone-number"
                                    placeholder="Enter Learning Area Name" name="learningAreaName">
                                    <span id="txt" style="color:red"> </span> 
                                    <p class="text-danger">${msgOne}</p>
                            </div>
                            <div class="form-group">
                         
                            <label for="sel1">Select Term (select one):</label> 
                            <select name="termNumber" class="form-control" id="sel1">
                                <option value="1">One</option>
                                <option value="2">Two</option>
                                <option value="3">Three</option>
                            </select>
                        </div>
                            <div class="form-group">
                            <label for="sel1">Enter Score:</label> 
                            <select name="scoreValue" class="form-control" id="sel1">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                            </select>
                        </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
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