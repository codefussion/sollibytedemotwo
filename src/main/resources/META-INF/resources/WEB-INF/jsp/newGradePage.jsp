<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Sollibyte new grade page</title>
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
<script type="text/javascript">
    
function validateform(){  
var gradeNumber=document.myform.gradeNumber.value; 
var gradeStream=document.myform.gradeStream.value; 


 if (gradeNumber==null || gradeNumber==""){  
   document.getElementById("txt2").innerHTML = "Please fill in the blank!";
  return false;  
}
else if (gradeStream==null || gradeStream==""){  
   document.getElementById("txt3").innerHTML = "Please fill in the blank!";
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
    <div class="row main">

        <div class="col-md-3"></div>
        <div class="col-lg-6">

            <!-- Dropdown Card Example -->
            <div class="card shadow mb-4">
                <a class="btn btn-primary   btn-sm" href="confirmSchoolDuplicate" role="button">Back</a>
                <div class="card-header py-3">
                    <h4 class="text-center text-success">Create new grade</h4>
                </div>
                <div class="card-body">
                    <form action="saveNewGrade" name="myform"
                        onsubmit="return validateform()" method="post">
                        <p class="text-danger text-center">${msg}</p>
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
                            </span>
                        </div>
                        <div class="form-group">
                            <label for="username">Enter Grade Stream </label> <input
                                type="text" class="form-control" id="username"
                                placeholder="Enter grade Stream if available e.g orange or 1 when unavailable"
                                name="gradeStream"> <span id="txt3" style="color: red">
                            </span> ${msgTwo}
                        </div>
                        <div class="form-group">
                            <label for="username">Enter Year</label> <select name="year"
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