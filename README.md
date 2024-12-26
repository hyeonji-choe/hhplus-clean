<img src="https://github.com/user-attachments/assets/6091611f-1770-490f-a771-d1c1e2421810"></img>
<h2>ERD</h2>
1. 사용자 User 테이블 
2. 특강 Course 테이블 
  2-1. seats ( 신청가능한 자리수 ) -> @version으로 관리 : 동시성 제어를 위해서 추가하였습니다. 
3. 특강 신청 이력 ApplyHiistory 테이블 

<h2>요구사항 정리</h2>
1. 특강신청 
  1-1. 최대인원 30명 (분산환경 동시성 제어) 
  1-2. 동일한 특강 동일한 사용자는 한번만 신청가능
2. 특강 목록 조회 
3. 사용자가 신청 성공한 특강 목록 조회 
