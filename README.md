## 🍓딸기말차 인터넷 강의 사이트🍵

### 소개
코딩애플 사이트를 클론 코딩함으로써 실제 서비스되고 있는 환경을 최대한 유사하게 체험

### 개발 목적
정보기술이 빠르게 발전하는 정보환경에서 홈페이지 관리와 운영, 서비스 전반에 걸쳐 핵심적인 역할을 수행 및 효과적으로 활용할 수 있는 인터넷 강의 사이트를 구축하는 것이 목적

### 주제 선정
1. 팀원 모두가 공감할 수 있는 주제 선정
2. 현재까지 배우고 구현한 기능을 적용
3. 많은 가르침을 주고 계신 딸기말차 헌정 사이트 구축
→ 코딩과 관련있는 딸기말차 인터넷 강의 사이트를 주제로 선정

### 개발 환경
Spring Boot : 2.7.15, security : 5.3.0, queryDSL : 5.0.0
Gradle Project
Database : MariaDB

### 개발 일정
2023.09.18 ~ 2023.09.25

## ✍️계획 구상

- 설계


요구사항 정리, 확정, 분석 결과 도출, 로직 설계, DB 설계


- 기능 세분화


공통 : 회원가입, 로그인, 마이 페이지, 시큐리티


사용자 : 강의 신청, 강의 결제, 질문 등록, 질문 수정


관리자 : 강의 등록, 답변 등록, 사용자 조회


## 테이블 구조 사진
![TABLE](https://github.com/Lee-HyunSoo/bootcamp-project/assets/102834723/004faef0-fb06-4276-adeb-b6fb8d535bd7)

## 🛠️ 정적 팩토리 메서드 및 빌더 사용
![__________________________1_480](https://github.com/Lee-HyunSoo/bootcamp-project/assets/102834723/dd4ab84c-308e-4056-a7c6-0504493ee6f3)


![______________720](https://github.com/Lee-HyunSoo/bootcamp-project/assets/102834723/9d789f73-82b6-45b4-a026-e9ed750076e2)


![_______720](https://github.com/Lee-HyunSoo/bootcamp-project/assets/102834723/bbcf9608-d6cf-4caf-8f97-1e8afb886c0c)

## 📹 대용량 파일 업로드 - tus

## 🎬 동영상 스트리밍

## 💸 결제 시스템 - iamport

## 로그 추적
![log1_720](https://github.com/Lee-HyunSoo/bootcamp-project/assets/102834723/a9a9f32a-b6de-407a-9aeb-73fdc4ab6c38)


![log2 (1)](https://github.com/Lee-HyunSoo/bootcamp-project/assets/102834723/cfe5218a-1074-4565-86cd-3738b517eec4)

## 테스트 코드 활용
![image_720](https://github.com/Lee-HyunSoo/bootcamp-project/assets/102834723/cec1f584-1f3f-44bd-91df-abe953898797)



