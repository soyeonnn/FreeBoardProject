<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, user.vo.*, post.vo.*"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>FreeBoard</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
    </style>

    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">
  </head>
  <body>
 
<div class="container-fluid">
  <div class="row">
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3">       
      </div>

      <h2>글쓰기</h2>
      	<form action="/freeboard/writepost" method="POST">
	      <%
			User user = (User)session.getAttribute("user");
    		session.setAttribute("user", user);
		  %>
	      	<input class="form-control" name="uid" type="text" value=<%= user.getUid() %> readonly>
		  	<div class="input-group mt-3">
	    		<span class="input-group-text">글 제목</span>
	    		<input type="text" class="form-control" name="ptitle">
	  		</div>
			<div class="form-group mt-3">
			    <label for="exampleFormControlTextarea1">내용: </label>
			    <textarea class="form-control" id="exampleFormControlTextarea1" rows="8" name="pcontent"></textarea>
			</div>    
		    <div class="text-end m-4">
		      	<input type="submit" class="btn btn-dark mt-3" value="완료">
		    </div>  
      	</form>  
    </main>
  </div>
</div>
  </body>
</html>