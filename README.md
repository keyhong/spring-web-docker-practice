spring-web-docker-practice
===========================

## Project Summary

#### 🔥 Project Summary

쿠버네티스 국비교육 1차 과제. 김영한님의 [스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8) 강의를 듣고 만든 Spring-Boot 웹을 dokcker-compose를 이용하여 build하고 deploy하는 과제입니다.

#### 🔥 Project Period

약 2일 소요 (18시간) 되었습니다. docker-compose로 올리는 것은 쉬울지도 모르나, 실제 프로덕션 상황이라고 생각하고 환경 분리(local, dev, prod)를 목적으로 작업하다 보니 다소 시간이 많이 소요 되었습니다. 또한 제가 주로 썼던 python이 아닌 spring java 웹 프레임워크의 트러블슈팅에서도 다소 시간 소요되었습니다.

#### 🔥개발 동안 무엇에 집중하였나?

1. 가장 우선 순위는 Database의 접속 정보를 별도의 .env 파일로 분리하여 관리하는 데 신경을 썼습니다. 이에 docker-compose에서 `env_file:`로 환경 변수를 읽을 수 있게 하였고, 일반적인 설정은 yaml파일에 `environment:`를 그대로 활용하였습니다.
  
   local 이외의 prod용 .env파일은 버전관리 호스팅 웹(예 github)에 올라가지 않게 `.gitignore`에 기재하였습니다. 
```bash 
.envs/*
!.envs/.local/
```

2. docker-compose 내에서 환경변수(env_file, environment), volume, healthcheck, restart에 대해 고민 및 세부적인 옵션 지정하였습니다. 또한 Dockerfile로 빌드되는 image의 네이밍 컨벤션을 준수하여 `keyhong/spring-app:v1.0.0`와 같이 versioning 시도하였습니다.

3. image build시 databse 테이블 자동 생성에 대한 방법을 탐구했습니다. `/docker-entrypoint-initdb.d`에 init.sql을 COPY 하여 웹에서 조회하는 Entity 테이블을 자동생성 하도록 하였습니다.

4. 마지막으로 Makefile을 생성하여 docker-compose에 대한 명령어를 간소화하였습니다. 사용 방법은 `make <Target>`입니다. 타겟은 긴 command를 줄인 단어라고 생각하시면 되고, 제가 기재한 타겟의 리스트는 아래와 같습니다. 


#### 🔥Makefile 사용법

- 모든 docker container 실행

```make
$ make up 

up: docker compose -f docker-compose-local.yml up -d
```

- 모든 docker container 제거

```make
$ make down 

down: docker compose -f docker-compose-local.yml down
```

- (모든 docker container) & (container와 mount된 볼륨) 제거

```make
$ make down-v 

down-v: docker compose -f docker-compose-local.yml down -v	
```

- 모든 docker container 로그 한 화면에 출력
  
```make
$ make show-logs 

show-logs: docker compose -f docker-compose-local.yml logs
```

- postgres container 로그 출력
  
```make
$ make show-logs-postgres 

show-logs-postgres: docker compose -f docker-compose-local.yml logs postgres
```

- spring web container 로그 출력
  
```make
$ make show-logs-spring

show-logs-spring: docker compose -f docker-compose-local.yml logs spring
```

- postgres mount volume 정보 출력
  
```make
$ make volume

volume: docker volume inspect hello-spring_local_postgres_data
```

#### 🔥수행 결과 얻은 것

1. docker container 간의 네트워크 연결하는 트러블 슈팅을 해결하며 조금 더 이해가 늘게 되었습니다.

2. 기존에 공부했던 것을 혼자서 직접 만들어 보며, 손에 익히고 익숙해지게 되었습니다.

3. 프로젝트 구조를 어떻게 잡아야 할까 고민하면서 더 좋은 많이 생각하고 고민할 수 있었습니다.

### 🔥아직 가야할 길

django에서 만들던 구조를 spring에 적용하려고 하니, 프레임워크별 구조에 크게 영향을 받는다는 것을 알게 되었습니다. 보다 제너럴한 개발자가 되려면 다른 언어와 프레임워크에도 보다 관심을 가져야겠다는 생각이 들었습니다!! 🔥🔥🔥🔥🔥
