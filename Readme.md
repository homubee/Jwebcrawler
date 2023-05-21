# Jwebcrawler
## Description
> Jwebcrawler : Java Web Crawler
> 
> 온라인 웹 크롤링 서비스
>
> 온라인 상에서 간편하게 웹 크롤링을 경험해볼 수 있는 서비스입니다.


## Demo Website
> API Docs : http://15.164.189.25:8090/swagger-ui/index.html
> 
> Web Demo : https://jwebcrawler.vercel.app
> 
> ※ 원활한 사이트 관리를 위해 사용자 정보는 주기적으로 삭제될 수 있습니다.


## Tech Stack
### Backend
<div align=center>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
  <img src="https://img.shields.io/badge/hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white">
  <br>

  <img src="https://img.shields.io/badge/postgresql-4169E1?style=for-the-badge&logo=postgresql&logoColor=white">
  <img src="https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black">
  <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
  <img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white">
  <br>

  <img src="https://img.shields.io/badge/amazon ec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">
  <img src="https://img.shields.io/badge/amazon rds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white">
  <img src="https://img.shields.io/badge/intellij-000000?style=for-the-badge&logo=intellijidea&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
</div>

### Frontend
<div align=center>
  <img src="https://img.shields.io/badge/typescript-3178C6?style=for-the-badge&logo=typescript&logoColor=white">
  <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black">
  <img src="https://img.shields.io/badge/redux-764ABC?style=for-the-badge&logo=redux&logoColor=white">
  <img src="https://img.shields.io/badge/mui-2196F3?style=for-the-badge&logo=mui&logoColor=white">
  <br>

  <img src="https://img.shields.io/badge/vercel-000000?style=for-the-badge&logo=vercel&logoColor=white">
  <img src="https://img.shields.io/badge/vscode-007ACC?style=for-the-badge&logo=visual studio code&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
</div>

## Environment

### Backend
- Spring Boot 2.7.11
- JDK 11
- Ubuntu 20.04
- Jsoup 1.15.4

### Frontend
- React 18.2.0
- Node.js 16.14.2


## Project Structure
![project structure](https://github.com/homubee/Jwebcrawler/assets/83688807/cbb98652-8ea1-4230-88e8-656d44d4bfc2)


## Main Feature

### Web API
- Spring Security 기반 인증 시스템
  - 로그인/회원가입: JWT 기반 인증 (Access Token / Refresh Token)
  - USER, ADMIN 권한 관리
- 게시판 기능: 작성, 조회, 수정, 삭제 가능
- 댓글 기능: 작성, 조회 가능

### Crawling API
- Jsoup 기반 웹크롤링 API 제공
  - 본문 내용 크롤링: p 태그, br 태그 등 줄바꿈 유형에 따른 크롤링 가능
  - 목록 항목 크롤링: li 태그, tr 태그 등 목록 항목에 관한 내용 크롤링 가능
