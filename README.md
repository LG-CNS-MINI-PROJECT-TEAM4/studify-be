## 4조의 주제: 스터디/프로젝트 모집 플랫폼 Studify
### 첫번째 미니프로젝트의 주제 중 3조의 주제 선정

### MSA 계획
> 호스트 서비스, 유저 서비스, 게이트웨이

### 역할 분담
#### 인프라 배포 및 운영 - 김민선, 조성민
- 환경 구성 및 네트워크 세팅  
> EC2 인스턴스 생성 및 보안 그룹 설계  
> Nginx 리버스 프록시 설정   
> Docker/Compose 네트워크 구축 및 공통 infra.yml 구성  

#### MSA 구성 - 강채민, 조현선
- 백엔드 아키텍처 전환
> Eureka Server/Client 구성  
> Gateway 라우팅 설정  
> JWT 인증 필터 적용  
> 기존 서비스 MSA화  

#### CI/CD 구축 - 김현우, 이용진
- 자동화 및 배포 파이프라인 구축
> Jenkins 서버 세팅 및 Pipeline 구성  
> Docker 이미지 빌드/푸시 자동화  
> Staging Prod 무중단 배포  

#### FE, API 연동 (Dockerizing, gateway api 연동) 및 QA (nGrinder) - 장미랑
> FE Docker 빌드 및 배포  
> Gateway 경유 API 통신 구현  
> JWT 토큰 연동 및 테스트 페이지 구성 확인  
> nGrinder Controller/Agent 구성  
> 부하 테스트 시나리오 작성  
> TPS, 응답 시간, 스케일링 검증 및 리포트 작성  
