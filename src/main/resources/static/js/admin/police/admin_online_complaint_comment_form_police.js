document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const ocId = urlParams.get('num');

    fetch('/online-complaint-comment-form/admin/p?num=' + ocId)
        .then(response => response.json())
        .then(data => {
            console.log(data);

            var complaintData = data;

            const { ocName, ocContact, ocAdvisor, ocTitle, ocContent } = complaintData;

            const nameElement = document.querySelector('td.c_form_title:nth-child(1) + td > p');
            const contactElement = document.querySelector('td.c_form_title:nth-child(3) + td > p');
            const advisorElement = document.querySelector('td.c_form_title:nth-child(5) + td > p');
            const titleElement = document.querySelector('td.c_form_title:nth-child(7) + td > p');
            const contentElement = document.querySelector('td.c_form_title:nth-child(9) + td > p');

            nameElement.textContent = ocName;
            contactElement.textContent = ocContact;
            advisorElement.textContent = ocAdvisor;
            titleElement.textContent = ocTitle;
            contentElement.textContent = ocContent;

        })
        .catch(error => {
            console.error('에러: ', error);
        });
});
