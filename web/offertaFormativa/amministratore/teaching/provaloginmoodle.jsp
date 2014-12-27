<%-- 
    Document   : provaloginmoodle
    Created on : 24-dic-2014, 17.42.50
    Author     : Alessandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="assets/js/jquery-1.11.1.min.js"></script>

    </head>
    <body >
        <h1>Hello World!</h1>
        <form action="http://localhost/moodle/login/index.php" method="post" id="login">
          <div class="loginform">
            <div class="form-label"><label for="username">Username</label></div>
            <div class="form-input">
              <input type="text" name="username" id="username" size="15" value="">
            </div>
            <div class="clearer"><!-- --></div>
            <div class="form-label"><label for="password">Password</label></div>
            <div class="form-input">
              <input type="password" name="password" id="password" size="15" value="">
              <input type="submit" id="loginbtn" value="Login">
            </div>
          </div>
            
        
            
        </form>
        <script type="text/javascript">
            
        </script>
    </body>
</html>
