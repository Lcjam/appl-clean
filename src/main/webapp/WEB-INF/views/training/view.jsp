<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>종합교육연수원 연수 참가신청서</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
  </head>
  <body>
      <div class="view-container">
          <div class="title-container">
        <h1>종합교육연수원 연수 참가신청서</h1>
    </div>

    <table class="form-table">
        <tr>
            <th>사업연도</th>
            <td colspan="3">2025</td>
        </tr>
        <tr>
            <th>회원 아이디</th>
            <td colspan="3">${training.kipaId}</td>
        </tr>
        <tr>
            <th>교육기관 구분</th>
            <td colspan="3">
                <div class="checkbox-container">
                    <span class="${training.hasOrgType('D001') ? 'checkbox-checked' : 'checkbox-label'}">발명교육센터</span>
                </div>
                <div class="checkbox-container">
                    <span class="${training.hasOrgType('D002') ? 'checkbox-checked' : 'checkbox-label'}">발명영재학급, 발명영재교육원</span>
                </div>
                <div class="checkbox-container">
                    <span class="${training.hasOrgType('D003') ? 'checkbox-checked' : 'checkbox-label'}">발명동아리</span>
                </div>
                <div class="checkbox-container">
                    <span class="${training.hasOrgType('D099') ? 'checkbox-checked' : 'checkbox-label'}">기타</span>
                    <c:if test="${not empty training.orgTypeEtcTxt}">
                        [ ${training.orgTypeEtcTxt} ]
                    </c:if>
                </div>
            </td>
        </tr>
        <tr>
            <th>교육 직무연수 선택</th>
            <td colspan="3">${training.courseCode}</td>
        </tr>
        <tr>
            <th>교육 직무연수 일정</th>
            <td colspan="3">
                ${training.trainingDesc}
                <c:if test="${not empty training.finishDesc}">
                    (${training.finishDesc} 시간)
                </c:if>
            </td>
        </tr>
        <tr>
            <th class="main-header" rowspan="3">신청자 학교정보</th>
            <th class="sub-header">학교명</th>
            <td>${training.dispatchOrgName}</td>
            <th class="sub-header">학제</th>
            <td>${training.schoolSystemName}</td>
        </tr>
        <tr>
            <th class="sub-header">시도교육청</th>
            <td>${eduOfficeName}</td>
            <th class="sub-header">교육지원청</th>
            <td>${training.localEduOfficeName}</td>
        </tr>
        <tr>
            <th class="sub-header">설립 구분</th>
            <td colspan="3">${foundTypeName}</td>
        </tr>
        <tr>
            <th>파견기관명</th>
            <td colspan="3">${training.dispatchFlag == 'Y' ? '있음' : '없음'} 
                <c:if test="${training.dispatchFlag == 'Y'}">
                    / ${training.dispatchOrgName}
                </c:if>
            </td>
        </tr>
        <tr>
            <th class="main-header" rowspan="2">신청자 기본정보</th>
            <th class="sub-header">성명</th>
            <td>${training.userName}</td>
            <th class="sub-header">직위</th>
            <td>${training.position}</td>
        </tr>
        <tr>
            <th class="sub-header">성별</th>
            <td colspan="3">${genderName}</td>
        </tr>
        <tr>
            <th class="main-header" rowspan="2">신청자 세부정보</th>
            <th class="sub-header">생년월일</th>
            <td>${training.formattedBirthDate}</td>
            <th class="sub-header">나이스 번호</th>
            <td>${training.nicePin}</td>
        </tr>
        <tr>
            <th class="sub-header">휴대전화</th>
            <td>${training.userMobile}</td>
            <th class="sub-header">이메일</th>
            <td>${training.userEmail}</td>
        </tr>
    </table>
    
    <div class="center-text">
        상기와 같이 종합교육연수원 연수 참가신청서를 제출합니다.
    </div>
    
    <div class="signature-section">
        <fmt:formatDate value="${training.applyTime}" pattern="yyyy-MM-dd HH:mm" /> <br>
        신청인 : ${training.userName}
    </div>
    
        <div class="button-group">
            <a href="${pageContext.request.contextPath}/trainings/list" class="btn">목록</a>
        </div>
      </div>
</body>
</html> 