<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <title>travel</title>
	<!-- 폰트 적용 -->
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400&family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
	
	<!-- css 스타일 초기화 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.css">
	<link rel="stylesheet" href="css/main.css">
	<link rel="stylesheet" href="css/btn.css">
	<link rel="stylesheet" href="css/input--text.css">
	<link rel="stylesheet" href="css/float.css">
	<link rel="stylesheet" href="css/header.css">
	<link rel="stylesheet" href="css/section.css">
	<link rel="stylesheet" href="css/feature.css">
	<link rel="stylesheet" href="css/footer.css">
</head>
<body>
    <!-- body 태그 내부에 메인으로 쓸 body__container를 정의 -->
    <div class="body__container">
     	<c:import url="header.jsp"></c:import>
        <!-- 전체 구조의 중간 부분 -->
        <section class="section section--visual">
            <div class="inner">
                <!-- 중간섹션의 설명 요약부분 -->
                <div class="summary">	
                    <h2 class="summary__title">
                        <!-- &nbsp;를 통해 두 단어를 연결, but 사이에 띄어쓰기는 존재, 결과적으로 긴 단어를 만들어 화면에 표시될 때 자연스럽게 줄바꿈 유도 -->
<!--                         How people build&nbsp;software --><br>
							<br />
							<br />
		
                    </h2>
                    <p class="summary__description">
                        <!-- br 태그를 쓰면 반응형을 추가했을때 줄이 자연스럽게 바뀌질 않음 -->
<!--                         Millions of developers use GitHub to build personal projects, support their businesses, and&nbsp;work together on open source technologies. -->
                    </p>
                </div>
            </div>
        </section>

        <!-- 특징들 -->
        <section class="section section--feature">
            <!-- 1. 특징 : 내용 -->
            <div class="summary">
                <h2 class="summary__title">
                	Welcome, First movers !
                </h2>
                <p class="summary__description">
                    BOLT is the first travel agency to take all your needs for space travel. <br>
                    Be the member of BOLT and grab a chance to explore.
                </p>
            </div>
            <!-- 2. 특징 : 비디오 -->
            <div class="video">
                <!-- 그냥 비디오를 삽입하면 비율이 맞지않기 때문에 비율을 맞춰주기 위해 -->
                <div class="video-ratio">
                <iframe width="900" height="506" src="https://www.youtube.com/embed/XrOz3biQrEo" title="Thank You For Flying SpaceX" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
                </div>
                
            </div>
            <!-- 3. 특징 : 타일들(사진) -->
            <div class="tiles">
                <!-- 수평축을 다 사용할 것이기에 화면 가운데에 몰아줄 inner -->
                <div class="inner">
                    <ul>
                        <li>
                            <img src="imgs/feature-tile__build.png" alt="build">
                            <h3>Virgin Galactic</h3>
                            <p>Virgin Galactic is launching a new space age, where all are invited along for the ride.</p>
                        </li>
                        <li>
                            <img src="imgs/feature-tile__work.png" alt="build">
                            <h3>Blue Origin</h3>
                            <p>We're committed to building a road to space so our children can build the future.</p>
                        </li>
                        <li>
                            <img src="imgs/feature-tile__projects.png" alt="build">
                            <h3>Space X</h3>
                            <p>SpaceX designs, manufactures and launches advanced rockets and spacecraft.</p>
                        </li>
                        <li>
                            <img src="imgs/feature-tile__platform.png" alt="build">
                            <h3>BOLT</h3>
                            <p>BOLT, the pioneer for space travel gives you best experience.</p>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
    </div>
   	<c:import url="footer.jsp"></c:import>
</body>
</html>