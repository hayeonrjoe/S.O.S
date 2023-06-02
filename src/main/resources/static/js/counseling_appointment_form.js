document.addEventListener('DOMContentLoaded', function () {
    const regionDropdown = document.getElementById('region-dropdown');
    const locationDropdown = document.getElementById('location-dropdown');

    regionDropdown.addEventListener('change', function () {
        const selectedRegion = regionDropdown.value;
        locationDropdown.innerHTML = '';

        if (selectedRegion === '서울') {
            addOptionsToLocationDropdown(['서울 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (경찰병원 - 서울 송파구 가락본동 58)', '서울 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (서울대병원 - 서울시 종로구 대학로 101 서울대학교병원)', '서울(보라매) 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (보라매병원 - 서울 동작구 신대방동 보라매길 39)', '서울 해바라기 아동센터\n' +
            '                            (서울 마포구 신수동 63 14 구프라자 7층)', '서울 해바리기 여성·아동센터\n' +
            '                            (서울시 종로구 대학로 101 서울대학교병원)']);
        } else if (selectedRegion === '인천') {
            addOptionsToLocationDropdown(['인천 해바리기 여성·아동센터\n' +
            '                            (인천의료원 - 인천 동구 송림4동 318 1)', '인천 해바리기 여성·아동센터\n' +
            '                            (인천광역시 남동구 구월동 11 44 15번지 2층)']);
        } else if (selectedRegion === '울산') {
            addOptionsToLocationDropdown(['울산 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (동강병원 - 울산 중구 태화동 1233)']);
        } else if (selectedRegion === '강원') {
            addOptionsToLocationDropdown(['강원 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (강원대병원 - 강원 춘천시 효자3동 17 1)', '강원 해바리기 여성·아동센터\n' +
            '                            (강원도 춘천시 효자3동 18 60 동광빌딩 2층)', '강원 해바리기 여성·아동센터\n' +
            '                            (강원 강릉시 포남2동 1065 2번지 강릉동인병원)']);
        } else if (selectedRegion === '충북') {
            addOptionsToLocationDropdown(['충북 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (청주의료원 - 청주시 흥덕구 사직1동 554 6)', '충청 해바리기 아동센터\n' +
            '                            (충북 충주시 교현2동 616 2 보성빌딩 4층)']);
        } else if (selectedRegion === '전북') {
            addOptionsToLocationDropdown(['전북 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (전북대병원 - 전주시 덕진구 금암동 634 18)', '전북 해바리기 아동센터\n' +
            '                            (전북 전주시 덕진구 인후동 2가 1573 1 사학연금관리공단 2층)']);
        } else if (selectedRegion === '경북') {
            addOptionsToLocationDropdown(['경북 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (안동의료원 - 경북 안동시 북문동 470)']);
        } else if (selectedRegion === '대구') {
            addOptionsToLocationDropdown(['대구 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (대구의료원 - 대구시 서구 중리동 1162)', '대구 해바리기 아동센터\n' +
            '                            (대구 중구 삼덕동 2가 270 1 소석문화센터 10층)']);
        } else if (selectedRegion === '광주') {
            addOptionsToLocationDropdown(['광주 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (조선대병원 - 광주시 동구 서석동 588)', '광주 해바리기 아동센터\n' +
            '                            (광주 동구 남동 112 1 웰크리닉 4층)']);
        } else if (selectedRegion === '대전') {
            addOptionsToLocationDropdown(['대전 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (충남대병원 - 대전시 중구 대사동 640)']);
        } else if (selectedRegion === '경기') {
            addOptionsToLocationDropdown(['경기 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (아주대병원 - 수원시 영통구 원천동산5)', '경기북부 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (의정부시 의정부2동 433)', '경기 해바리기 아동센터\n' +
            '                            (경기 성남시 분당구 야탑동 532 3 한화빌딩 5층)']);
        } else if (selectedRegion === '경남') {
            addOptionsToLocationDropdown(['경남 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (마산의료원 - 마산시 중앙동3가 3번지)', '경남 해바리기 아동센터\n' +
            '                            (경남 진주시 칠암동 90 경상대학교병원)']);
        } else if (selectedRegion === '제주') {
            addOptionsToLocationDropdown(['제주 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (한라병원 - 제주시 연동 1963 2)']);
        } else if (selectedRegion === '전남') {
            addOptionsToLocationDropdown(['전남 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (성가롤로 병원 - 전남 순천시 조례동 1742)', '전남 해바리기 여성·아동센터\n' +
            '                            (전남 목포시 석현동 815 8 목포중앙병원)']);
        } else if (selectedRegion === '충남') {
            addOptionsToLocationDropdown(['충남 여성·학교폭력피해자 원스톱지원센터\n' +
            '                            (단국대병원 - 천안시 동남구 망향로 359)']);
        } else if (selectedRegion === '부산') {
            addOptionsToLocationDropdown(['부산 해바리기 아동센터\n' +
            '                            (부산 서구 동대신동 3가 1번지 동아대학교병원)', '부산 해바리기 여성·아동센터\n' +
            '                            (부산시 서구 동대신동 3가 1번지 동아대학교병원)']);
        }
    });

    function addOptionsToLocationDropdown(options) {
        options.forEach(function (option) {
            const optionElement = document.createElement('option');
            optionElement.textContent = option;
            optionElement.value = option;
            locationDropdown.appendChild(optionElement);
        });
    }

});