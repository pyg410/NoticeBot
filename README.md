# NoticeBot

학교 공지사항 메일봇 서비스

# 개요

~~전남대 공지사항 카카오톡으로 자동 알림 서비스~~

전남대 공지사항 메일 자동 알림봇 서비스

AI융합대, 소프트웨어공학과 공지사항을 매번 확인하기 귀찮아서 만들게된 자동 알림 서비스.

~~카카오톡으로 보낼지, 메일로 보낼지 전송 플랫폼은 아직 미정.~~

~~크롤링 관련 문의를 전남대에 해봐야하지만, 선개발 하고 배포하기 전에 확인할 예정~~( 전산원피셜 로그인 안해도 얻을 수 있는정보는 괜찮다고함.)

# 기술스택

- Mysql 8.0.32
- Springboot 2.7.11
- Jsoup 1.15.4
- Data JPA
- Java11
- Jakarta Commons email 1.5
- spring security 5.7.8

# V0

> 23.04.30 - 23.05.10

전남대학교 AI융합대학 / 소중사업단 / AICOSS / SW공학과 공지사항을 매일 아침 9시에 전송합니다.

- AI융합대학 공지사항 크롤링
- 공지사항의 중복된 내용 검증
- 소중사업단 공지사항 크롤링
- 전송할 내용이 없는 경우 특정 문구 출력
- 소프트웨어공학과 공지사항 크롤링
- AICOSS 공지사항 크롤링
- 매일 아침 9시로 등록된 사용자에게 메일 전송

# V1

> 23.05.11 - 진행중

회원가입/로그인, 메일 봇 서비스 신청 가능한 웹 페이지

- 로그인, 회원가입, 로그아웃
- 로그인 유지 및 보안을 위한 Access Token, Refresh Token
- 메일봇 신청폼
- 개발자 건의사항 작성페이지
