# NICE PAY API 연동 모듈

Java/Spring 기반의 NICEPAY 결제 시스템 연동 모듈입니다.  
공식 문서를 기반으로 실제 결제 시스템 경험을 바탕으로 안정적이고 효율적인 기능을 제공합니다.

## 주요 기능 (Features)

- TID(Transaction ID)로 결제 내역 조회
- 비정상 예약 미매칭 결제 자동 취소 (session out 등)
- 특정 결제 건 취소
- 미정산 금액 발생 시 SMS 결제 발송
- 발송된 SMS 결제 내역 조회

## 기술 스택 (Tech Stack)

- **Back End**: Java, Spring Boot, REST API
- **세부 기술**:
    - 멀티스레딩: `ScheduledExecutorService` 기반 비동기 작업 스케줄링 및 동시성 처리
    - AOP: `@RestControllerAdvice` 활용 전역 예외 처리
    - 로깅: SLF4J 기반 오류 및 디버그 로그 관리
- **개발 환경**:
    - 언어: Java 17
    - 프레임워크: Spring Boot 3.4.0
    - 빌드 툴: Gradle

## Error Handling

예외 발생 시 `NicePayException`을 통해 공통 코드/메시지 구조를 반환

```json
{
  "code": "E400",
  "message": "잘못된 요청입니다.",
  "data": "object"
}
```

---

## 요구사항 (Requirements)

- JDK 17+
- Gradle 8.x
- NICEPAY 가맹점 Key 및 API 인증 정보

---

## run

```shell
   git clone [프로젝트_레포지토리_주소]
   cd [프로젝트_폴더명]
```

```shell
./gradlew bootRun
```

---

## 💡 Issues

## 📝 Posting

[나이스페이 망취소 API 도입기](https://sunghomong.github.io/posts/service-nicePay_java01/)
[결제 조회 API 연동](https://sunghomong.github.io/posts/service-nicePay_java/)

## 🖥 References

RESTful API
https://sas-study.tistory.com/265
https://m.blog.naver.com/genycho/221309436556

Git
https://medium.com/hashbox/git-commit-%EB%A9%94%EC%84%B8%EC%A7%80-%EA%B7%9C%EC%B9%99-conventional-commits-71710f7f53c
https://meetup.toast.com/posts/106
https://javakong.tistory.com/217

AWS
https://aws.amazon.com/ko/getting-started/hands-on/create-mysql-db/
https://leveloper.tistory.com/18
https://twofootdog.tistory.com/41
https://miniminis.github.io/2019/10/13/spring/springboot-deploy/