document.addEventListener('DOMContentLoaded', function () {
    // Retrieve the ocId from the URL parameter
    const urlParams = new URLSearchParams(window.location.search);
    const ocId = urlParams.get('num');

    // Fetch the data using AJAX
    fetch('/online-complaint/admin/p/detail?num=' + ocId)
        .then(response => response.json())
        .then(data => {
            console.log(data); // Check the data received in the console

            // Access the JSON data from the model
            var complaintData = data;

            // Retrieve the necessary data from the complaintData object
            const { ocName, ocContact, ocAdvisor, ocTitle, ocContent, ocResponseContent } = complaintData;

            // Get the <p> elements where the data will be inserted
            const nameElement = document.querySelector('td.c_form_title:nth-child(1) + td > p');
            const contactElement = document.querySelector('td.c_form_title:nth-child(3) + td > p');
            const advisorElement = document.querySelector('td.c_form_title:nth-child(5) + td > p');
            const titleElement = document.querySelector('td.c_form_title:nth-child(7) + td > p');
            const contentElement = document.querySelector('td.c_form_title:nth-child(9) + td > p');
            const responseElement = document.querySelector('.c_response > p');

            // Set the retrieved data into the <p> elements
            nameElement.textContent = ocName;
            contactElement.textContent = ocContact;
            advisorElement.textContent = ocAdvisor;
            titleElement.textContent = ocTitle;
            contentElement.textContent = ocContent;
            responseElement.textContent = ocResponseContent;
        })
        .catch(error => {
            // Handle any errors that occur during the request
            console.error('에러: ', error);
        });
});
