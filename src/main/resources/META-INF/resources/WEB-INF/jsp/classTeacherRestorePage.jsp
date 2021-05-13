<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Sollibyte class teacher restore page</title>
	<meta name="description"
	content="Get efficiency in learner evaluation with sollibyte the best teacher aiding tool for kenyan competency based curriculum  made with the latest robust technology">

  <meta content="CBC competency based curriculum sollibyte kenyan education system" name="keywords">
   <link href="assets/img/favicon.jpg" rel="icon">
	
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <style type="text/css">
  	body {
    background-color: #EBEAEF
}

.container {
    flex-wrap: wrap
}

.card {
    border: none;
    border-radius: 10px;
    background-color: #32627b;
    width: 350px;
    margin-top: -60px
}

p.mb-1 {
    font-size: 25px;
    color: #9FB7FD
}

.btn-primary {
    border: none;
    background: #32627b;
    margin-bottom: 60px
}

.btn-primary small {
    color: #9FB7FD
}

.btn-primary span {
    font-size: 13px
}

.card.two {
    border-top-right-radius: 60px;
    border-top-left-radius: 0
}

.form-group {
    position: relative;
    margin-bottom: 2rem
}

.form-control {
    border: none;
    border-radius: 0;
    outline: 0;
    border-bottom: 1.5px solid #E6EBEE
}

.form-control:focus {
    box-shadow: none;
    border-radius: 0;
    border-bottom: 2px solid #8A97A8
}

.form-control-placeholder {
    position: absolute;
    top: 15px;
    left: 10px;
    transition: all 200ms;
    opacity: 0.5;
    font-size: 80%
}

.form-control:focus+.form-control-placeholder,
.form-control:valid+.form-control-placeholder {
    font-size: 80%;
    transform: translate3d(0, -90%, 0);
    opacity: 1;
    top: 10px;
    color: #8B92AC
}

.btn-block {
    border: none;
    border-radius: 8px;
    background-color: #506CCF;
    padding: 10px 0 12px
}

.btn-block:focus {
    box-shadow: none
}

.btn-block span {
    font-size: 15px;
    color: #D0E6FF
}
  </style>
</head>
<body>
	<div class="container d-flex justify-content-center">
    <div class="d-flex flex-column justify-content-between">
       <form action="verifyRestoreClassTeacher">
               <div class="card mt-3 p-5">
            <div class="logo mb-3"><img src=""></div>
            <div>
                <h2 class="mb-1 text-center text-white"> Sollibyte</h2>
                <h4 class="mb-5 text-white text-center">Restore Password</h4>
            </div>
               <a href="logout" role="button" class="btn btn-primary btn-lg"><small>Back to home page?</small><span>&nbsp;Back</span></a>

        </div>
        <div class="card two bg-white px-5 py-4 mb-3">
            <div class="form-group"><input type="text" class="form-control" name="phoneNumber" id="number" required><label class="form-control-placeholder" for="number">Enter Phone Number</label></div>
            <div class="form-group"><input type="text" class="form-control"name="schoolCodeNumber" id="password" required><label class="form-control-placeholder" for="password">Enter School Code Number</label></div>
           <p class="text-danger">${msg}</p>  
             <button class="btn btn-primary btn-block btn-lg mt-1 mb-2"><span>confirm<i class="fas fa-long-arrow-alt-right ml-2"></i></span></button>
        </div>
       </form>
    </div>
</div>
</body>
</html>