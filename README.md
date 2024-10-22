## Introduction

⏳ 개발 기간: 2024.05 ~ 2024.06

👨🏻‍💻 프로젝트 소개
  - 프론트부터 백엔드 담당 네이버클라우드 우분투 서버를 통해 배포.
  - 사용자가 위치, 날씨, 선호도를 바탕으로 음식과 식당을 추천해주고, 이용한 장소들에 대해 내용을 공유할 수 있는 웹 어플리케이션.

## Architecture
![스크린샷 2024-10-22 오후 12 55 57](https://github.com/user-attachments/assets/55c30c96-8801-48c0-abbc-e17aa6b0a52f)

## ERD
![스크린샷 2024-10-22 오후 3 10 24](https://github.com/user-attachments/assets/a7ed74f7-dc40-4412-aa75-afa971370213)

<details>
  <summary>📂 서비스 주요 기능</summary>

  ### Member
    - 홈페이지 회원가입을 통한 로그인
    - sns 로그인
    - 회원 정보 수정

  ### Recommend
    - 음식 선호도 설문조사
    - 사용자가 위치한 곳의 날씨 정보 불러오기
    - 사용자가 선호할 만한 음식과 식당 추천

  ### Board
    - 컨텐츠 '좋아요' 하기
    - 컨텐츠 '좋아요' 취소
    - 컨텐츠 작성
    - 사용자 위치 컨텐츠 불러오기
</details>

## Description

🔎 목차
  - [OAuth2 & JWT 기반 로그인 기능](#oauth2--jwt-기반-로그인-기능)
  - [N+1 문제 해결](#n1-문제-해결)

---
