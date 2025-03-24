select * from faq;
select * from theater;
select * from qna;
insert into faq (answer, category, question, count, createDate) values ( '- 할인 쿠폰 자동 적용 버튼 클릭 시 고객님이 보유하고 있는 쿠폰 중 우선순위에 따라 최적의 할인을 적용한 것입니다.
※ 무료쿠폰 or 할인비율 100%> 할인비율 높은순> 이용금액 낮은순> 할인금액 높은순> 쿠폰 만료일이 가까운순', '결제수단/관람권', '예매, 모바일오더 결제 시 할인쿠폰 자동 적용 버튼은 무엇인가요?', 1, '2024-12-10');
insert into faq (answer, category, question, count, createDate) values ( '온라인 예매 시(메가박스 홈페이지 및 APP) 1회 결제 시 최대 8매까지 예매 가능합니다.
', '영화예매', '예매할 수 있는 횟수나 매수등의 제한이 있나요?', 1, '2024-12-10');
insert into faq (answer, category, question, count, createDate) values ( '적립된 포인트는 사용 가능한 포인트 내에서 상품 정가 전액 기준으로 사용 가능합니다.
', '영화예매', '영화 예매 시 메가박스 포인트 선택이 되지 않아요', 1, '2024-12-10');
insert into faq (answer, category, question, count, createDate) values ( '인터넷 예매시에는 예매매수의 전체환불 및 교환만 가능합니다. 
인터넷 예매분에 대해 교환환불 및 취소하고자 하실 경우, 예매하신 내역 전체에 대해 취소 후
새로 예매를 하시거나 해당 영화관을 방문하셔서 처리하셔야 합니다. 
', '영화예매', '인터넷 예매 시 부분환불/교환이 가능한가요?', 1, '2024-12-10');
insert into faq (answer, category, question, coun, createDatet) values ( '◆ 전화로 상담하는 ARS
사용하시는 휴대폰 또는 유선전화로 ARS서비스를 받으실 수 있습니다. 상담 가능한 시간을 확인 하신 후 이용해 주세요.
- 고객센터 운영시간 : 10:00~19:00 
', '극장/특별관', 'ARS 이용안내', 1);

insert into theater (address, area, description, theater) values ( '서울특별시 서초구 서초대로 77길 3 (서초동) 아라타워 8층', '서울', '강남역 9번출구와 연결된 편리한 접근성과 위치! 강남을 한 눈에 볼 수 있는 최상의 VIEW' ,'강남');
insert into theater (address, area, description, theater) values ( '서울특별시 강동구 성내로 48', '서울', '강동구청역 도보 5분, 전 좌석 가죽시트로 쾌적하고 편안하게! 언제나 즐거운 우리동네 극장','강동');
insert into theater (address, area, description, theater) values ( '서울특별시 광진구 능동로 289(군자동)', '서울', '군자역 도보 5분, 편안한 양팔걸이와 전 좌석 가죽시트로 쾌적하고 편안하게', '군자');
insert into theater (address, area, description, theater) values ( '서울특별시 양천구 목동동로 257, (목동) 현대백화점유플렉스 목동점 지하2층', '서울', '영화의 감동을 넘어 편안함의 감동까지, 리클라이너와 라운지로 프리미엄하게', '더 부티크 목동현대백화점');
insert into theater (address, area, description, theater) values ( '서울특별시 중구 장충단로 247, (을지로6가) 굿모닝시티 9층', '서울', '영화의 감동을 넘어 편안함의 감동까지, 리클라이너로 프리미엄하게', '동대문');
insert into theater (address, area, description, theater) values ( '서울특별시 강서구 공항대로 247, (마곡동) 퀸즈파크나인 B동 4층', '서울', '전 관 레이저 영사기로 선명하게! 단체 및 대관 특화 영화관', '마곡');
insert into theater (address, area, description, theater) values ( '서울특별시 양천구 목동동로 309, (목동) 행복한백화점 6층', '서울', 'Dolby 사운드의 생생한 감동, 다양한 취향에 맞게 즐기는 야미버스와 퍼니버스', '목동');
insert into theater (address, area, description, theater) values ( '서울특별시 강남구 논현동 도산대로 118', '서울', '글로벌 트렌드 메카 가로수길 인접, 신사역 도보 1분', '브로드웨이(신사)');
insert into theater (address, area, description, theater) values ( '서울특별시 중랑구 망우로 30길 3, (상봉동) 안산빌딩 4층', '서울', '전 관 위생적인 가죽시트, 편안한 양팔걸이와 사이드 테이블의 상영관', '상봉');
insert into theater (address, area, description, theater) values ( '서울특별시 마포구 월드컵로 240, (성산동) 월드컵주경기장 1층', '서울', '메가박스 100호점! Dolby 사운드의 생생한 감동이 영화의 감동으로', '상암월드컵경기장');
insert into theater (address, area, description, theater) values ( '서울특별시 성동구 왕십리로 50, (성수동 1가) 메가박스스퀘어 3층', '서울', 'Dolby 사운드의 생생한 감동이 영화의 감동으로! 리클라이너와 라운지로 프리미엄하게', '성수');
insert into theater (address, area, description, theater) values ( '서울특별시 서초구 신반포로 176, (반포동) 센트럴시티 빌딩 지하1층', '서울', '편안하게 즐기는 부티크 호텔 감성의 아늑한 라운지와 상영관', '센트럴');
insert into theater (address, area, description, theater) values ( '서울특별시 송파구 송파대로 111,(문정동,파크하비오푸르지오) 204동 지하1층 메가박스', '서울', '당구, 포켓볼, 코인노래방, 키즈라이브러리 등 놀거리 가득한 복합문화공간', '송파파크하비오');
insert into theater (address, area, description, theater) values ( '서울특별시 서대문구 신촌역로 30, (신촌동) 신촌민자역사 5층', '서울', '여유로운 주차 공간, 전 관 위생적인 가죽시트', '신촌');
insert into theater (address, area, description, theater) values ( '서울특별시 동작구 동작대로 89, (사당동) 골든시네마타워 7층', '서울', '이수역 도보 1분, 전 관 위생적인 가죽시트', '이수');
insert into theater (address, area, description, theater) values ( ' 서울특별시 도봉구 도봉로 558 (창동)', '서울', '전 좌석 가죽시트로 쾌적하고 편안하게! 언제나 즐거운 우리동네 극장', '창동');
insert into theater (address, area, description, theater) values ( '서울특별시 강남구 봉은사로524, (삼성동) 코엑스몰 지하2층', '서울', '명실상부 국내 최대 영화관, ALL IN MEGA! 메가박스의 모든 특별관이 이곳에', '코엑스');
insert into theater (address, area, description, theater) values ( '서울특별시 마포구 양화로 147, (동교동) 아일렉스 7층', '서울', '홍대입구역 도보 1분, 20대들의 놀이터! 전 관 레이저 영사기로 선명하게', '홍대');
insert into theater (address, area, description, theater) values ( '서울특별시 강서구 화곡로 142, (화곡동) 메가스퀘어 4층', '서울', '화곡역 도보 3분, 전 좌석 가죽시트로 쾌적하고 편안하게! 언제나 즐거운 우리동네 극장', '화곡');
insert into theater (address, area, description, theater) values ( '서울특별시 동작구 사당동 동작대로 89 골든시네마타워', '서울', '강남 최초 예술영화관, 미술전시, 음악공연, 토크 프로그램 등이 펼쳐지는 색다른 문화복합공간', 'ARTNINE');





insert into theater (address, area, description, theater) values ( '경기도 고양시 덕양구 고양대로 1955 (동산동) 스타필드고양 4층', '경기', '스타필드고양 필수 코스! 사운드 특별관 DOLBY ATMOS관, 가족을 위한 KIDS관', '고양스타필드');
insert into theater (address, area, description, theater) values ( '경기도 광명시 양지로 17, (일직동) AK플라자 광명점 지하1층', '경기', '광명역 도보 5분, 여유로운 4시간 무료 주차! 전 좌석 소파석으로 프리미엄하게', '광명AK플라자');
insert into theater (address, area, description, theater) values ( '경기도 광명시 소하로 190, (소하동) G-타워 지하1층', '경기', '전 관 레이저 영사기와 리클라이너 좌석으로 선사하는 완벽한 영화 관람', '광명소하');
insert into theater (address, area, description, theater) values ( '경기도 군포시 엘에스로143, (금정동) AK플라자금정 1층', '경기', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 소파석으로 프리미엄하게', '금정AK플라자');
insert into theater (address, area, description, theater) values ( '경기도 김포시 김포한강9로 75번길 180, (구래동) 두원타워 8층', '경기', '여유로운 주차 공간, 전 관 레이저 영사기로 선명하게!', '김포한강신도시');
insert into theater (address, area, description, theater) values ( '경기도 남양주시 호평동 늘을2로 26 메인시네마타워', '경기', '영화의 감동을 넘어 편안함의 감동까지, 리클라이너로 프리미엄하게', '남양주');
insert into theater (address, area, description, theater) values ( '경기도 남양주시 다산순환로 50, (다산동) 현대프리미엄아울렛 스페이스원 3층', '경기', '완벽한 영화 관람을 완성하는 하이엔드 DOLBY CINEMA', '남양주현대아울렛 스페이스원');
insert into theater (address, area, description, theater) values ( '경기도 화성시 반송동 동탄지성로 11 동탄SR GOLD PLAZA', '경기', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '동탄');
insert into theater (address, area, description, theater) values ( '경기도 하남시 미사강변 중앙로 218', '경기', '미사역 도보 3분, 영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '미사강변');
insert into theater (address, area, description, theater) values ( '경기도 고양시 일산동구 강송로33, (백석동) 벨라시타 2층', '경기', '여유로운 10시간 무료 주차! 편안하게 즐기는 아늑한 라운지와 상영관', '백석벨라시타');
insert into theater (address, area, description, theater) values ( '경기도 남양주시 별내동 두물로 19', '경기', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '별내');
insert into theater (address, area, description, theater) values ( '경기도 부천시 옥길로 1, (옥길동) 스타필드시티 부천점 4층', '경기', '전 관 레이저 영사기로 선명하게! 여유롭고 편안한 까페형 로비', '부천스타필드시티');
insert into theater (address, area, description, theater) values ( '경기도 성남시 분당구 황새울로 332, (서현동) 분당문화센터 1층', '경기', '서현역 도보 1분, 리클라이너와 프라이빗한 발코니로 프리미엄하게', '분당');
insert into theater (address, area, description, theater) values ( '경기도 수원시 권선구 권선동 경수대로 270 수원버스터미널 4층 메가박스 수원지점', '경기', '영화의 감동을 넘어 편안함의 감동까지, 리클라이너로 프리미엄하게', '수원');
insert into theater (address, area, description, theater) values ( '경기도 수원시 팔달구 매산로1가 덕영대로 924 AK플라자, 6층', '경기', '완벽한 영화 관람을 완성하는 하이엔드 DOLBY CINEMA, 전 좌석 소파석으로 프리미엄하게', '수원AK플라자(수원역)');
insert into theater (address, area, description, theater) values ( '경기도 수원시 팔달구 팔달로3가 행궁로 71', '경기', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '수원남문');
insert into theater (address, area, description, theater) values ( '경기도 수원시 장안구 정자동 111-14', '경기', 'Dolby 사운드의 생생한 감동이 영화의 감동으로, 사운드 특별관 DOLBY ATMOS관', '수원스타필드');
insert into theater (address, area, description, theater) values ( '경기도 수원시 팔달구 효원로 278, 파비오더씨타 4층(인계동)', '경기', '수원시청역 도보 1분, 전 좌석 리클라이너와 다양한 팝콘을 즐길 수 있는 시즈닝바', '수원인계');
insert into theater (address, area, description, theater) values ( '경기도 수원시 권선구 호매실동 호매실로90번길 76, 아진스퀘어 7~8층', '경기', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '수원호매실');
insert into theater (address, area, description, theater) values ( '경기도 시흥시 정왕동 배곧3로 96 M플러스', '경기', 'Dolby 사운드의 생생한 감동이 영화의 감동으로, 돌비 애트모스 도입', '시흥배곧');
insert into theater (address, area, description, theater) values ( '경기도 안산시 단원구 고잔동 고잔2길 41 신양복합빌딩', '경기', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '안산중앙');
insert into theater (address, area, description, theater) values ( '경기도 안성시 공도읍 서동대로 3930-39, (진사리) 스타필드 안성 3층', '경기', '완벽한 영화 관람을 완성하는 하이엔드 DOLBY CINEMA', '안성스타필드');
insert into theater (address, area, description, theater) values ( '경기도 양주시 고읍남로 6-6 (광사동, 해동센트럴타워 9층)', '경기', '영화의 감동을 넘어 편안함의 감동까지, 리클라이너로 프리미엄하게', '양주');
insert into theater (address, area, description, theater) values ( '경기도 용인시 기흥구 고매동 신고매로 59, (고매동) 리빙파워센터 4층', '경기', '전 관 레이저 영사기와 리클라이너 좌석으로 선사하는 완벽한 영화 관람', '용인기흥');
insert into theater (address, area, description, theater) values ( '경기도 용인시 기흥구 구성로 357 (청덕동) 용인테크노밸리 A동 지하1층', '경기', '여유로운 커피 한잔을 즐길 수 있는 카페형 로비와 용인 최초의 빈백 좌석', '용인테크노밸리');
insert into theater (address, area, description, theater) values ( '경기도 의정부시 오목로205번길 55, (민락동) 메가타워 5층', '경기', '전 관 레이저 영사기와 리클라이너 좌석으로 선사하는 완벽한 영화 관람', '의정부민락');
insert into theater (address, area, description, theater) values ( '경기도 고양시 일산서구 호수로 817, (대화동) 레이킨스몰 3층', '경기', 'GTX역 도보 5분, 영화의 감동을 넘어 편안함의 감동까지, 리클라이너로 프리미엄하게', '킨텍스');
insert into theater (address, area, description, theater) values ( '경기도 파주시 금촌동 중앙로 328', '경기', 'Dolby 사운드의 생생한 감동이 영화의 감동으로, 돌비 애트모스 도입', '파주금촌');
insert into theater (address, area, description, theater) values ( '경기도 파주시 경의로 989 에스비프라자 3층 메가박스 파주운정점', '경기', '여유로운 주차공간! 언제나 즐거운 우리동네 극장', '파주운정');
insert into theater (address, area, description, theater) values ( '경기도 평택시 비전동 경기대로 279 뉴코아 아울렛 백화점 10층', '경기', '리클라이너 좌석으로 편안하게 관람하는 우리동네 극장', '평택비전');
insert into theater (address, area, description, theater) values ( '경기도 하남시 미사대로 750, (신장동) 스타필드 하남 4층', '경기', '스타필드하남 필수 코스! ALL IN MEGA! 메가박스의 모든 특별관이 이곳에', '하남스타필드');
insert into theater (address, area, description, theater) values ( '경기도 고양시 덕양구 고양대로 1955 (동산동) 스타필드고양 4층', '경기', '스타필드고양 필수 코스! 사운드 특별관 DOLBY ATMOS관, 가족을 위한 KIDS관', '고양스타필드');








insert into theater (address, area, description, theater) values ( '인천광역시 서구 서곶로 788 (당하동) 홀리랜드 4층 메가박스 검단', '인천', '영화의 감동을 넘어 편안함의 감동까지, 리클라이너로 프리미엄하게', '검단');
insert into theater (address, area, description, theater) values ( '인천광역시 연수구 송도과학로 16번길 33-4, (송도동) 트리플 스트리트 D동 2층', '인천', '인천 유일, 완벽한 영화 관람을 완성하는 하이엔드 DOLBY CINEMA', '송도');
insert into theater (address, area, description, theater) values ( '인천 중구 영종대로 184(운서동, 7층)', '인천', '언제나 즐거운 우리동네 극장', '영종');
insert into theater (address, area, description, theater) values ( '인천광역시 중구 하늘중앙로 195번길 15, 세영프라자 3층', '인천', '언제나 즐거운 우리동네 극장', '영종하늘도시');
insert into theater (address, area, description, theater) values ( '인천광역시 남동구 논현동 논고개로 61 라피에스타', '인천', 'TRINNOV SOUND SYSTEM으로 압도하는 사운드', '인천논현');





insert into theater (address, area, description, theater) values ( '충청남도 공주시 신관동 흑수골길 12', '대전/충청/세종', '공주시 유일 멀티플렉스, 전 관 레이저 영사기로 선명하게!', '공주');
insert into theater (address, area, description, theater) values ( '충청남도 논산시 중앙로 255, 3,4층(내동, 우리메가박스타운)', '대전/충청/세종', '영화의 감동을 넘어 편안함의 감동까지, 리클라이너로 프리미엄하게', '논산');
insert into theater (address, area, description, theater) values ( '대전광역시 서구 문정로 77, (탄방동) 로데오타운 5층', '대전/충청/세종', '여유로운 주차 공간, 단체 및 대관 특화 영화관', '대전');
insert into theater (address, area, description, theater) values ( '대전광역시 유성구 엑스포로1, (도룡동) 대전신세계아트앤사이언스 6층', '대전/충청/세종', '충청 유일, 돌비 시네마 & 더 부티크 스위트에서 경험하는 프리미엄 문화 생활', '대전신세계 아트앤사이언스');
insert into theater (address, area, description, theater) values ( '대전광역시 유성구 계룡로132번길 10, (봉명동) 센트럴프라자 5층', '대전/충청/세종', '대학가의 중심, 유성온천역 도보 5분, 언제나 즐거운 우리동네 극장', '대전유성');
insert into theater (address, area, description, theater) values ( '대전광역시 중구 중앙로 126, (대흥동) 4층', '대전/충청/세종', '대전 빵세권의 중심! 대전중앙로역 도보 2분, 성심당 도보 5분', '대전중앙로');
insert into theater (address, area, description, theater) values ( '대전광역시 유성구 테크노중앙로 123, (용산동) 현대프리미엄아울렛 대전점 3층', '대전/충청/세종', 'Dolby 사운드의 생생한 감동이 영화의 감동으로, 사운드 특별관 DOLBY ATMOS관', '대전현대아울렛');
insert into theater (address, area, description, theater) values ( '세종특별자치시 조치원읍 새내로 122 메가박스', '대전/충청/세종', '전 좌석 가죽시트로 쾌적하고 편안하게! 언제나 즐거운 우리동네 극장', '세종(조치원)');
insert into theater (address, area, description, theater) values ( '세종특별자치시 나성로 38, (나성동) 세종 가로수길 건물 5층 A동', '대전/충청/세종', '나성동 번화가, BRT 라인! 여유로운 무료 주차에 전 관 위생적인 가죽시트까지', '세종나성');
insert into theater (address, area, description, theater) values ( '세종특별자치시 도움3로 19, (어진동) 엠브릿지 지하1층', '대전/충청/세종', '세종시 유일 전석 소파석 도입 전관 레이저 영사기, JBL 스피커 설치로 편안하고 쾌적한 영화 관람 환경 조성', '세종청사');
insert into theater (address, area, description, theater) values ( '충북 청주시 청원구 오창읍 중심상업1로 8-9, 3층', '대전/충청/세종', '전 좌석 가죽시트로 쾌적하고 편안하게! 리클라이너 로얄석으로 프리미엄하게', '오창');
insert into theater (address, area, description, theater) values ( '충청북도 진천군 진천읍 중앙북1길 3 진천터미널', '대전/충청/세종', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '진천');
insert into theater (address, area, description, theater) values ( '충청남도 천안시 서북구 두정동 1공단1길 52, (두정동) 센트하임프라자 3층', '대전/충청/세종', '여유로운 4시간 무료 주차! 전 관 레이저 영사기로 선명하게', '천안');
insert into theater (address, area, description, theater) values ( '충청북도 청주시 서원구 사창동 1순환로 682', '대전/충청/세종', '전 관 레이저 영사기와 리클라이너 좌석으로 선사하는 완벽한 영화 관람', '청주사창');
insert into theater (address, area, description, theater) values ( '충청북도 청주시 상당구 상당로81번길 33, 쥬네쓰 쇼핑몰 3층', '대전/충청/세종', '전 관 레이저 영사기로 선명하게! 편안하고 안락한 리클라이너까지', '청주성안길');
insert into theater (address, area, description, theater) values ( '충청북도 충주시 번영대로 211, 3층(아토몰)', '대전/충청/세종', '돌비 애트모스관, 리클라이너 좌석, 전 관 레이저 영사기가 도입된 영화관', '충주연수');
insert into theater (address, area, description, theater) values ( '충청남도 홍성군 홍북읍 청사로 174번길 2, 6층 메가박스 홍성내포점', '대전/충청/세종', '전 좌석 가죽시트로 쾌적하고 편안하게! 언제나 즐거운 우리동네 극장', '홍성내포');



insert into theater (address, area, description, theater) values ( '경상북도 예천군 호명면 새움3로 70 3층', '부산/대구/경상', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '경북도청');
insert into theater (address, area, description, theater) values ( '경상북도 경산시 하양읍 대경로 765', '부산/대구/경상', '무료 주차! 전 좌석 가죽시트로 쾌적하고 편안하게! 언제나 즐거운 우리동네 극장', '경산하양');
insert into theater (address, area, description, theater) values ( '경상북도 구미시 인동가산로 14, (진평동) 시네마월드 7층', '부산/대구/경상', '구미 인동 문화의 중심, 여유로운 4시간 무료 주차! Dolby 사운드 프로세서 도입', '구미강동');
insert into theater (address, area, description, theater) values ( '경상북도 김천시 김천로74 한일빌딩4층', '부산/대구/경상', '김천 유일 멀티플렉스, 김천역 도보 3분', '김천');
insert into theater (address, area, description, theater) values ( '경상북도 포항시 남구 오천읍 하원로47번길9, (원리) 1층', '부산/대구/경상', '무료 주차! 전 좌석 가죽시트로 쾌적하고 편안하게', '남포항');
insert into theater (address, area, description, theater) values ( '대구광역시 북구 동천동 동암로 90 세븐밸리 5층', '부산/대구/경상', '전 관 레이저 영사기와 리클라이너 좌석으로 선사하는 완벽한 영화 관람', '대구세븐밸리(칠곡)');
insert into theater (address, area, description, theater) values ( '대구광역시 동구 동부로 149, (신천동) 신세계백화점 8층', '부산/대구/경상', '대구경북 유일, 완벽한 영화 관람을 완성하는 하이엔드 DOLBY CINEMA', '대구신세계(동대구)');
insert into theater (address, area, description, theater) values ( '대구광역시 동구 봉무동 팔공로49길 51, (봉무동) 메가맥스타워 2층', '부산/대구/경상', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너와 프라이빗한 발코니', '대구이시아');
insert into theater (address, area, description, theater) values ( '대구광역시 중구 국채보상로 547 (종로1가)', '부산/대구/경상', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '대구프리미엄만경관');
insert into theater (address, area, description, theater) values ( '부산광역시 북구 덕천동 만덕대로 23 폴라렉스', '부산/대구/경상', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '덕천');
insert into theater (address, area, description, theater) values ( '경상남도 창원시 마산합포구 해안대로 51 유로스퀘어 6층(구.성지아울렛)', '부산/대구/경상', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '마산(경남대)');
insert into theater (address, area, description, theater) values ( '경상북도 문경시 모전로 65 (홈플러스 문경점 1층)', '부산/대구/경상', '영화 촬영의 메카! 문경 최초 유일의 영화관', '문경');
insert into theater (address, area, description, theater) values ( '부산광역시 중구 비프광장로 36(남포동5가)', '부산/대구/경상', '언제나 즐거운 우리동네 극장', '부산극장');
insert into theater (address, area, description, theater) values ( '부산광역시 금정구 장전로 12번길 55, (장전동) 라퓨타 아일랜드 4층', '부산/대구/경상', '부산대역 도보 3분, 영화의 감동을 넘어 편안함의 감동까지, 리클라이너로 프리미엄하게', '부산대');
insert into theater (address, area, description, theater) values ( '대구광역시 북구 동암로 100', '부산/대구/경상', '전 관 레이저 영사기와 리클라이너 좌석으로 선사하는 완벽한 영화 관람', '북대구(칠곡)');
insert into theater (address, area, description, theater) values ( '부산광역시 사상구 사상로 201, (괘법동) 서부시외버스터미널 4층', '부산/대구/경상', '사상역 도보 1분, 전 관 레이저 영사기로 선명하게! 언제나 즐거운 우리동네 극장', '사상');
insert into theater (address, area, description, theater) values ( '경상남도 사천시 해안관광로 109-10(실안동), 아르떼 리조트 본관 4층', '부산/대구/경상', '바다가 보이는 영화관, 전 좌석 리클라이너와 선명한 레이저 영사기', '삼천포');
insert into theater (address, area, description, theater) values ( '부산광역시 부산진구 중앙대로692번길 16(부전동), 대한프라자 3층', '부산/대구/경상', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '서면대한');
insert into theater (address, area, description, theater) values ( '경상남도 양산시 중부동 강변로 440 쇼핑몰 바나나빌딩 6,8,9층', '부산/대구/경상', '영화의 감동을 넘어 편안함의 감동까지, 리클라이너로 프리미엄하게', '양산');
insert into theater (address, area, description, theater) values ( '경상남도 양산시 물금읍 증산역로 177, (가촌리) 라피에스타 6층', '부산/대구/경상', '전 관 레이저 영사기와 리클라이너 좌석으로 선사하는 완벽한 영화 관람', '양산증산');
insert into theater (address, area, description, theater) values ( '울산광역시 중구 성남동 젊음의거리 73, 2층', '부산/대구/경상', '영화의 감동을 넘어 편안함의 감동까지, 리클라이너로 프리미엄하게', '울산');
insert into theater (address, area, description, theater) values ( '부산광역시 기장군 정관읍 정관6로 39, 큐엠시네마타워 5층 메가박스 정관점', '부산/대구/경상', '전 관 레이저 영사기와 리클라이너 좌석으로 선사하는 완벽한 영화 관람', '정관');
insert into theater (address, area, description, theater) values ( '경상남도 진주시 중안동 진양호로 521, 4층', '부산/대구/경상', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '진주(중안)');
insert into theater (address, area, description, theater) values ( '경상남도 창원시 성산구 용지로 58, (중앙동) 메가플렉스 8층', '부산/대구/경상', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '창원');
insert into theater (address, area, description, theater) values ( '경상남도 창원시 마산회원구 내서읍 광려천서로 81, (내서읍) ISC프라자 10층', '부산/대구/경상', '전 관 레이저 영사기와 리클라이너 좌석으로 선사하는 완벽한 영화 관람', '창원내서');
insert into theater (address, area, description, theater) values ( '경상북도 포항시 남구 중흥로 77, (상도동) 밸류플러스 7층', '부산/대구/경상', 'Dolby 사운드의 생생한 감동이 영화의 감동으로, 사운드 특별관 DOLBY ATMOS관', '포항');
insert into theater (address, area, description, theater) values ( '부산광역시 해운대구 해운대로 813 (좌동, NC백화점 8층) 메가박스 해운대장산지점', '부산/대구/경상', '영화의 감동을 넘어 편안함의 감동까지, 전 관 리클라이너로 프리미엄하게', '해운대(장산)');



insert into theater (address, area, description, theater) values ( '광주광역시 서구 치평동 시청로60번길 21-6, 3층 콜롬버스시네마', '광주/전라', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '광주상무');
insert into theater (address, area, description, theater) values ( '광주광역시 광산구 우산동 풍영철길로 15 콜럼버스월드', '광주/전라', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 리클라이너로 프리미엄하게', '광주하남');
insert into theater (address, area, description, theater) values ( '전라남도 목포시 옥암로 95 (상동) 포르모큐브몰 3층', '광주/전라', '여유로운 주차 공간, 전 관 레이저 영사기로 선명하게', '목포하당(포르모)');
insert into theater (address, area, description, theater) values ( '전라남도 순천시 덕암동 충효로 15 메가박스', '광주/전라', '영화의 감동을 넘어 편안함의 감동까지, 리클라이너로 프리미엄하게', '순천');
insert into theater (address, area, description, theater) values ( '전남 여수시 신월로 96 3층 메가박스 여수웅천점', '광주/전라', '영화의 감동을 넘어 편안함의 감동까지, 리클라이너로 프리미엄하게', '여수웅천');
insert into theater (address, area, description, theater) values ( '광주광역시 북구 중흥동 우치로 60', '광주/전라', '전 좌석 가죽시트로 쾌적하고 편안하게! 언제나 즐거운 우리동네 극장', '전대(광주)');
insert into theater (address, area, description, theater) values ( '전북특별자치도 전주시 완산구 전주객사4길 74-12(고사동) 메가박스 전주객사지점', '광주/전라', '양팔걸이와 리클라이너 의자로 편안하고 프리미엄하게', '전주객사');
insert into theater (address, area, description, theater) values ( '전북특별자치도 전주시 덕진구 기지로 77 (장동, 전북혁신도시 디엠시티) 2층', '광주/전라', '사운드 특별관 DOLBY ATMOS관, 전 관 레이저 영사기로 선명하게', '전주혁신');
insert into theater (address, area, description, theater) values ( '광주광역시 광산구 쌍암동 앰코로 35 폭스존', '광주/전라', '전 관 레이저 영사기로 선명하고 리클라이너로 프리미엄하게', '첨단');

insert into theater (address, area, description, theater) values ( '강원특별자치도 춘천시 춘천로17번길 17 (온의동, 뉴타운빌딩 2F)', '강원', '높은 층고가 주는 시원한 개방감, 편안한 양팔걸이 의자', '남춘천');
insert into theater (address, area, description, theater) values ( '강원특별자치도 속초시 조양동 조양로142번길 20 메가박스', '강원', '언제나 즐거운 우리동네 극장', '속초');
insert into theater (address, area, description, theater) values ( '강원특별자치도 원주시 황금로 2, 센트럴파크 7층 / 강원특별자치도 원주시 양지로 80, (반곡동, 센트럴파크 7층)', '강원', 'Dolby 사운드의 생생한 감동과 전 좌석 리클라이너로 프리미엄하게', '원주혁신');
insert into theater (address, area, description, theater) values ( '강원특별자치도 춘천시 후석로 120, (석사동) 춘천NH타운 ENTA 2층', '강원', '영화의 감동을 넘어 편안함의 감동까지, 전 좌석 소파석으로 프리미엄하게', '춘천석사');
insert into theater (address, area, description, theater) values ( '제주특별자치도 제주시 삼봉로 11 (삼양이동)', '제주', '전 관 레이저 영사기와 리클라이너 좌석으로 선사하는 완벽한 영화 관람', '제주삼화');
insert into theater (address, area, description, theater) values ( '제주특별자치도 서귀포시 동홍동 1372-1', '제주', '전 관 레이저 영사기와 리클라이너 좌석으로 선사하는 완벽한 영화 관람', '제주서귀포');
insert into theater (address, area, description, theater) values ( '제주특별자치도 제주시 아라일동 구산로 4 메가타워', '제주', '영화의 감동을 넘어 편안함의 감동까지, 리클라이너로 프리미엄하게', '제주아라');