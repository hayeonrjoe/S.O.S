document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const pcId = urlParams.get('num');

    fetch('/police-complaint/detail?num=' + pcId)
        .then(response => response.json())
        .then(data => {
            console.log(data);

            var complaintJson = JSON.stringify(data);

            var complaintData = JSON.parse(complaintJson);

            const { pcTitle, pcContent, pcName, pcContact, pcVictimName, pcVictimAge, pcVictimContact, pcVictimOther, pcBullyName, pcBullyRelationship, pcBullyAge, pcBullyContact, pcBullyOther, pcResponseContent } = complaintData;

            const titleElement = document.querySelector('td.c_form_title:nth-child(2) + td > p');
            const contentElement = document.querySelector('td.c_form_title:nth-child(4) + td > p');
            const nameElement = document.querySelector('td.c_form_title:nth-child(5) + td > p');
            const contactElement = document.querySelector('td.c_form_title:nth-child(9) + td > p');
            const victimNameElement = document.querySelector('td.c_form_title:nth-child(3) + td > p');
            const victimAgeElement = document.querySelector('td.c_form_title:nth-child(5) + td > p');
            const victimContactElement = document.querySelector('td.c_form_title:nth-child(7) + td > p');
            const victimOtherElement = document.querySelector('td.c_form_title:nth-child(9) + td > p');
            const bullyNameElement = document.querySelector('td.c_form_title:nth-child(13) + td > p');
            const bullyRelationshipElement = document.querySelector('td.c_form_title:nth-child(15) + td > p');
            const bullyAgeElement = document.querySelector('td.c_form_title:nth-child(17) + td > p');
            const bullyContactElement = document.querySelector('td.c_form_title:nth-child(19) + td > p');
            const bullyOtherElement = document.querySelector('td.c_form_title:nth-child(21) + td > p');
            const responseElement = document.querySelector('.c_response > p');

            titleElement.textContent = pcTitle;
            contentElement.textContent = pcContent;
            nameElement.textContent = pcName;
            contactElement.textContent = pcContact;
            victimNameElement.textContent = pcVictimName;
            victimAgeElement.textContent = pcVictimAge;
            victimContactElement.textContent = pcVictimContact;
            victimOtherElement.textContent = pcVictimOther;
            bullyNameElement.textContent = pcBullyName;
            bullyRelationshipElement.textContent = pcBullyRelationship;
            bullyAgeElement.textContent = pcBullyAge;
            bullyContactElement.textContent = pcBullyContact;
            bullyOtherElement.textContent = pcBullyOther;
            responseElement.textContent = pcResponseContent;

            if (pcResponseContent === null) {
                responseElement.innerHTML = "[답변 대기] 상태입니다. \n [답변 완료] 상태 확인 후 다시 확인해주세요.";
            } else {
                responseElement.textContent = pcResponseContent;
            }
        })
        .catch(error => {
            console.error('에러: ', error);
        });
});
