<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="jobForm">	
	<input type='hidden' name="page" value=""/>
	<input type='hidden' name="perPageNum" value=""/>
	<input type='hidden' name="searchType" value="" />
	<input type='hidden' name="keyword" value="" />
	<input type='hidden' name="startday" value="" />
	<input type='hidden' name="endday" value="" />
</form>


<nav aria-label="Navigation">
	<ul class="pagination justify-content-center m-0">
		<li class="page-item ">
			<a href="javascript:list_go(1);" class="page-link">
				<i class="fas fa-angle-double-left"></i>
			</a>
		</li>
		<li class="page-item">
			<a class="page-link" href="javascript:list_go(${pageMaker.prev ? pageMaker.startPage-1 :cri.page });">
				<i class="fas fa-angle-left"></i>
			</a>						
		</li>
		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
			<li class="page-item ${cri.page == pageNum?'active':''}">
				<a class="page-link" href="javascript:list_go(${pageNum });">
					${pageNum }
				</a>						
			</li>
		</c:forEach>
		<li class="page-item">
			<a class="page-link" href="javascript:list_go(${pageMaker.next ? pageMaker.endPage+1 : cri.page });">
				<i class="fas fa-angle-right"></i>
			</a>						
		</li>
		<li class="page-item ">
			<a href="javascript:list_go(${pageMaker.realEndPage });" class="page-link">
				<i class="fas fa-angle-double-right"></i>
			</a>
		</li>
	</ul>
</nav>    

