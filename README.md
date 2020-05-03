<h1 align="center">Welcome to Biz-Box 👋</h1>

<p>
 
</p>

 서울시 공공데이터를 이용하여 주요상권분석 웹 서비스
## 상권 분석 웹 서비스
![version](https://img.shields.io/badge/version-0.0.1-orange?)
![vue](https://img.shields.io/badge/vue-3.0.0-blue?logo=Vue.js)
![spring](https://img.shields.io/badge/spring-4.0.0-yellow?logo=spring)
![spring-boot](https://img.shields.io/badge/springboot-4.0.0-yellow?logo=spring)
![html](https://img.shields.io/badge/html-html5-red?logo=html5)
![css](https://img.shields.io/badge/css-css3-red?logo=css3)
![sass](https://img.shields.io/badge/sass-1.23.0-red?logo=sass)
![javascript](https://img.shields.io/badge/javascript-es6-yellowgreen?logo=javascript)
![aws-rds](https://img.shields.io/badge/aws%20-rds-ff69b4?logo=Amazon)

## Table of Contents
- [상권 분석 웹 서비스](#%ec%8b%9d%eb%8b%b9-%eb%a6%ac%eb%b7%b0-sns)
  - [Table of Contents](#table-of-contents)
  - [About](#about)
  - [사용기술](#%ec%82%ac%ec%9a%a9%ea%b8%b0%ec%88%a0)
  - [Team](#team)
  - [Demo](#demo)
  - [Stack](#stack)
  - [license](#license)
  
## **about**


### 🏠 [Homepage](http://i02a207.p.ssafy.io/)
![img1](./img1.PNG)
![img2](./img2.PNG)
공공데이터를 기반으로 다양한 상권지표 제공 및 상권의 종합 점수를 제공합니다.

또한 지역별 업종의 주요고객과 시간대를 추천하고 해당 업종의 매출 현황을 제공합니다.

로그인 기능을 이용하여 자신의 업소를 등록 할수 있고 통계데이터에 반영됩니다.

## **MyRole**

**BackEnd** 

상권분석을 위한 공공데이터를 수집하고 `MySql`을 통해 DB를 구축하였습니다.

`Spring` 프레임워크를 이용하여 상권의 유동인구, 매출, 변화지표등을 제공하는 `Rest Controller`를 구현하였습니다.

공공 주소 API를 이용하여 주소를 통한 검색 기능을 구현하였습니다.

DB에 저장된 좌표를 이용해 반경내 상점정보 검색 기능 구현.

`Ehcache` 캐시를 이용하여 자주호출되는 함수에 캐시적용하였습니다.

`JWT` 토큰을 이용하여 카카오 로그인 구현.

**frontEnd**

서울지역의 지도를 기본 UI로 사용하기 위해 `KakaoMap API`를 이용하여 구현하였습니다.

상점을 추가하는 컴포넌트

모달창과 모달창내의 그래프탭 내의 컴포넌트 구현하였습니다.

## 사용기술

Spring boot : 상권 데이터 제공 Rest Controller 구현 

Kakao Login API : 간편 소셜 로그인 

KakaoMap API : 행정동 별 상권지표에 따른 색 구분

SpringSecurity + JWT : 로그인한 사용자만 서비스를 사용가능 하도록(filter), Token을 계속 검사해서 유효한 회원인지 확인

JavaScript : 유동인구, 매출현황, 연령별 매출 등 데이터 그래프 출력

MySql : 공공데이터를 기반으로 DB 구축

AWS : 호스팅

Qgis : 서울시의 행정동 공간정보 

jusoAPI : 도로명, 지번 등 주소를 통한 검색 구현


## Team

👤 문지현
👤 오승완
👤 음영현
👤 양희철
👤 김재현
👤 문요한


Github: [@moonjihyeon1994](https://github.com/moonjihyeon1994)

## Demo
[bizbox](http://i02a207.p.ssafy.io/)  

## Stack
<img src="https://user-images.githubusercontent.com/56620330/75417977-a60ae900-5975-11ea-9e05-bc6b938fb197.png" alt="스프링" style="zoom: 33%;" />    <img src="https://user-images.githubusercontent.com/56620330/75418145-144fab80-5976-11ea-9650-5f9fab957792.png" alt="뷰제이에스" style="zoom: 33%;" /> <img src="https://user-images.githubusercontent.com/56620330/75418176-26c9e500-5976-11ea-9cb8-8ce5f42013cb.PNG" alt="마이에스큐엘" style="zoom: 50%;" />

 <img src="https://user-images.githubusercontent.com/56620330/75417521-9c34b600-5974-11ea-9c3f-edf81cdbb967.PNG" alt="큐지아이에스" style="zoom: 25%;" />   <img src="https://user-images.githubusercontent.com/56620330/75417523-9ccd4c80-5974-11ea-801a-eea0ead0865b.PNG" alt="카카오맵" style="zoom:33%;" /> <img src="https://user-images.githubusercontent.com/56620330/75417524-9ccd4c80-5974-11ea-9074-76d35eb0d8de.PNG" alt="시에스에스" style="zoom: 67%;" />

## Show your support

Give a ⭐️ if this project helped you!

## 📝 License

This project is MIT licensed.
