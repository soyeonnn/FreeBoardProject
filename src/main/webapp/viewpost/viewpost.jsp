<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, user.vo.*, post.vo.*, comment.vo.*"%>

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

      <h2>상세글보기</h2>
      	<form>
	      <%
			User user = (User)session.getAttribute("user");
    		Post post = (Post)request.getAttribute("post");
    		ArrayList<Comment> comment = (ArrayList<Comment>)request.getAttribute("comment");
    		
    		session.setAttribute("user", user);
		  %>
	      	<div class="input-group mt-3">
	    		<span class="input-group-text">글 제목</span>
	    		<input type="text" class="form-control" name="ptitle" value="<%= post.getPtitle() %>" readonly>
	  		</div>
		  	<div class="input-group mt-3">
	    		<span class="input-group-text">작성자</span>
	    		<input type="text" class="form-control" name="pwriter" value="<%= post.getPwriter() %>"readonly>
	  		</div>
			<div class="form-group mt-3">
			    <label for="exampleFormControlTextarea1">내용: </label>
			    <textarea class="form-control" id="exampleFormControlTextarea1" rows="8" name="pcontent" readonly><%= post.getPcontent() %></textarea>
			</div>
	    	
	    	<div class="input-group mt-3" style="width: 22%;">
	    		<span class="input-group-text">좋아요 <%= post.getPlike() %></span>
	    		<span class="input-group-text">조회수 <%= post.getPview() %></span>
	    		<span class="input-group-text">댓글수 <%= post.getPcomment() %></span>
	  		</div>
	  		
	  		<div class="text-end m-4">
	  			<span>작성일 <%= post.getPdate() %></span>
	  		</div>
	  	</form>
	  	
	  	<div class="my-3 p-3 bg-body rounded shadow-sm">
		    <h6 class="border-bottom pb-2 mb-0">댓글</h6>
		    <%
		    	if(comment != null) {
		    		for(Comment com : comment) {	
		    %>	
		    			<div class="input-group mt-3">
				    		<span class="input-group-text"><%= com.getCwriter() %></span>
				    		<input type="text" class="form-control" name="pwriter" value="<%= com.getCcontent() %>"readonly>				  			
	   		<%
	   					if(com.getCwriter().equals(user.getUid())) {
	   		%>		
	   					  	<input type="button" class="btn btn-primary" value="수정">
	   					  	<input type="button" class="btn btn-danger" value="삭제">					      	     							 				  
		   			<% } %>	
		   			</div>	   				
	    		  <% } %>
		    <% } %>
		    
		</div>
		<form action="/freeboard/writecomment" method="POST">
			<div class="input-group mb-3">
				<input type="text" name="pid" hidden="true">
 				<input type="text" name="ccontent" class="form-control" placeholder="댓글 입력">
				<button class="btn btn-success" type="submit">입력</button>
	  		</div>
		</form>
		
 		<%
  			if(user.getUid().equals(post.getPwriter())) {
  		%>
			<div class="text-end m-4">
				<a href="/freeboard/modifypost?modifypid=<%= post.getPid() %>" class="btn btn-dark mt-3">수정</a>
				<a href="/freeboard/deletepost?delpid=<%= post.getPid() %>" class="btn btn-dark mt-3">삭제</a>
	    	</div>  
  		<% } %>
    </main>
  </div>
</div>
  </body>
</html>