<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>연수 신청 목록</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
  </head>
  <body>
      <div class="container">
          <h2>연수 신청 목록</h2>
    
    <!-- 페이지 표시 정보 계산 -->
    <c:set var="startNum" value="${(currentPage-1)*pageSize + 1}" />
    <c:set var="endNum" value="${currentPage*pageSize}" />
    <c:if test="${endNum > totalCount}">
        <c:set var="endNum" value="${totalCount}" />
    </c:if>
    
    <p>총 <strong>${totalCount}</strong>건의 데이터 중 <strong>${startNum}</strong>번부터 <strong>${endNum}</strong>번까지 표시</p>
    
    <table class="data-table">
        <thead>
            <tr>
                <th>번호</th>
                <th>신청자</th>
                <th>신청일</th>
                <th>상세</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${empty trainings}">
                <tr>
                    <td colspan="4" class="no-data">조회된 데이터가 없습니다.</td>
                </tr>
            </c:if>
            <c:forEach var="training" items="${trainings}">
                <tr>
                    <td data-label="번호">${training.trainingSeq}</td>
                    <td data-label="신청자">${training.userName}</td>
                    <td data-label="신청일">${training.formattedApplyTime}</td>
                    <td data-label="상세">
                        <a href="${pageContext.request.contextPath}/trainings/view/${training.trainingSeq}" class="btn-view">상세</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <!-- 페이징 UI -->
    <div class="pagination-container">
        <ul class="pagination">
            <!-- 처음 페이지로 이동 -->
            <li>
                <a href="${pageContext.request.contextPath}/trainings/list?page=1&size=${pageSize}" aria-label="처음">
                    &laquo;
                </a>
            </li>
            
            <!-- 이전 페이지로 이동 -->
            <c:if test="${currentPage > 1}">
                <li>
                    <a href="${pageContext.request.contextPath}/trainings/list?page=${currentPage - 1}&size=${pageSize}" aria-label="이전">
                        &lt;
                    </a>
                </li>
            </c:if>
            
            <!-- 페이지 번호 표시 (현재 페이지 중심으로 최대 5개) -->
            <c:set var="startPage" value="${currentPage - 2}" />
            <c:if test="${startPage < 1}">
                <c:set var="startPage" value="1" />
            </c:if>
            
            <c:set var="endPage" value="${startPage + 4}" />
            <c:if test="${endPage > totalPages}">
                <c:set var="endPage" value="${totalPages}" />
            </c:if>
            
            <c:forEach begin="${startPage}" end="${endPage}" var="pageNum">
                <li class="${pageNum == currentPage ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/trainings/list?page=${pageNum}&size=${pageSize}">
                        ${pageNum}
                    </a>
                </li>
            </c:forEach>
            
            <!-- 다음 페이지로 이동 -->
            <c:if test="${currentPage < totalPages}">
                <li>
                    <a href="${pageContext.request.contextPath}/trainings/list?page=${currentPage + 1}&size=${pageSize}" aria-label="다음">
                        &gt;
                    </a>
                </li>
            </c:if>
            
            <!-- 마지막 페이지로 이동 -->
            <li>
                <a href="${pageContext.request.contextPath}/trainings/list?page=${totalPages}&size=${pageSize}" aria-label="마지막">
                    &raquo;
                </a>
            </li>
        </ul>
        
        <div class="pagination-info">
            ${totalPages} 페이지 중 ${currentPage} 페이지
        </div>
    </div>
      </div>
</body>
</html> 