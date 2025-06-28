<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>연수 신청</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <div class="container">
        <h2>연수 신청서</h2>
        
        <form:form modelAttribute="training" method="post" class="form-horizontal">
            <!-- 기본 정보 섹션 -->
            <div class="form-section">
                <h3>📝 기본 정보</h3>
                <div class="form-row">
                    <div class="form-group">
                        <label>신청자명 <span style="color: #dc3545;">*</span></label>
                        <form:input path="userName" class="form-control" required="true" placeholder="이름을 입력하세요"/>
                        <form:errors path="userName" class="error"/>
                    </div>
                    <div class="form-group">
                        <label>기관 유형 <span style="color: #dc3545;">*</span></label>
                        <form:select path="orgTypeCode" class="form-control" required="true">
                            <form:option value="">선택하세요</form:option>
                            <form:options items="${orgTypeCodes}" itemValue="code" itemLabel="codeName"/>
                        </form:select>
                        <form:errors path="orgTypeCode" class="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <label>기타 기관 설명</label>
                    <form:input path="orgTypeEtcTxt" class="form-control" placeholder="기타 사항이 있으면 입력하세요"/>
                </div>
            </div>

            <!-- 소속 정보 섹션 -->
            <div class="form-section">
                <h3>🏫 소속 정보</h3>
                <div class="form-row">
                    <div class="form-group">
                        <label>과정 코드 <span style="color: #dc3545;">*</span></label>
                        <form:select path="courseCode" class="form-control" required="true">
                            <form:option value="">선택하세요</form:option>
                            <form:options items="${courseCodes}" itemValue="code" itemLabel="codeName"/>
                        </form:select>
                        <form:errors path="courseCode" class="error"/>
                    </div>
                    <div class="form-group">
                        <label>학교 코드 <span style="color: #dc3545;">*</span></label>
                        <form:input path="schoolCode" class="form-control" required="true" placeholder="학교 코드"/>
                        <form:errors path="schoolCode" class="error"/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>학교급 <span style="color: #dc3545;">*</span></label>
                        <form:select path="schoolSystemCode" class="form-control" required="true">
                            <form:option value="">선택하세요</form:option>
                            <form:options items="${schoolSystemCodes}" itemValue="code" itemLabel="codeName"/>
                        </form:select>
                        <form:errors path="schoolSystemCode" class="error"/>
                    </div>
                    <div class="form-group">
                        <label>교육청 <span style="color: #dc3545;">*</span></label>
                        <form:select path="eduOfficeCd" class="form-control" required="true">
                            <form:option value="">선택하세요</form:option>
                            <form:options items="${eduOfficeCodes}" itemValue="code" itemLabel="codeName"/>
                        </form:select>
                        <form:errors path="eduOfficeCd" class="error"/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>설립 유형 <span style="color: #dc3545;">*</span></label>
                        <form:select path="foundTypeCode" class="form-control" required="true">
                            <form:option value="">선택하세요</form:option>
                            <form:options items="${foundTypeCodes}" itemValue="code" itemLabel="codeName"/>
                        </form:select>
                        <form:errors path="foundTypeCode" class="error"/>
                    </div>
                    <div class="form-group">
                        <label>파견 여부</label>
                        <form:select path="dispatchFlag" class="form-control">
                            <form:option value="N">아니오</form:option>
                            <form:option value="Y">예</form:option>
                        </form:select>
                    </div>
                </div>
                <div class="form-group">
                    <label>파견 기관명</label>
                    <form:input path="dispatchOrgName" class="form-control" placeholder="파견인 경우 기관명을 입력하세요"/>
                </div>
            </div>

            <!-- 개인 정보 섹션 -->
            <div class="form-section">
                <h3>👤 개인 정보</h3>
                <div class="form-row">
                    <div class="form-group">
                        <label>직위 <span style="color: #dc3545;">*</span></label>
                        <form:input path="position" class="form-control" required="true" placeholder="현재 직위"/>
                        <form:errors path="position" class="error"/>
                    </div>
                    <div class="form-group">
                        <label>성별 <span style="color: #dc3545;">*</span></label>
                        <form:select path="genderCode" class="form-control" required="true">
                            <form:option value="">선택하세요</form:option>
                            <form:options items="${genderCodes}" itemValue="code" itemLabel="codeName"/>
                        </form:select>
                        <form:errors path="genderCode" class="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <label>생년월일 <span style="color: #dc3545;">*</span></label>
                    <form:input path="birthDate" class="form-control" required="true" 
                               pattern="[0-9]{8}" placeholder="YYYYMMDD (예: 19900101)"/>
                    <form:errors path="birthDate" class="error"/>
                </div>
            </div>

            <!-- 연락처 정보 섹션 -->
            <div class="form-section">
                <h3>📞 연락처 정보</h3>
                <div class="form-row">
                    <div class="form-group">
                        <label>휴대폰 <span style="color: #dc3545;">*</span></label>
                        <form:input path="mobile" class="form-control" required="true" placeholder="010-1234-5678"/>
                        <form:errors path="mobile" class="error"/>
                    </div>
                    <div class="form-group">
                        <label>이메일 <span style="color: #dc3545;">*</span></label>
                        <form:input path="email" type="email" class="form-control" required="true" placeholder="example@email.com"/>
                        <form:errors path="email" class="error"/>
                    </div>
                </div>
            </div>
            
            <!-- 버튼 섹션 -->
            <div class="form-group" style="text-align: center; margin-top: 30px;">
                <button type="submit" class="btn btn-primary" style="padding: 12px 30px; margin-right: 10px;">✅ 신청하기</button>
                <a href="/trainings/list" class="btn btn-secondary" style="padding: 12px 30px;">📋 목록으로</a>
            </div>
        </form:form>
    </div>
</body>
</html> 