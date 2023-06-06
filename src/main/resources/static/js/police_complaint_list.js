//////////////////////////////////////////////////////
// Without pagination
// Wait for the DOM to load
document.addEventListener('DOMContentLoaded', function () {
    var loadAction = document.getElementById('searchForm').getAttribute('data-load-action');

    if (loadAction === '/police-complaint/load-latest') {
        // Fetch the latest results when the page is first loaded
        fetchLatestResults();
    }

    function fetchLatestResults() {
        return fetch('/police-complaint/latest-results')
            .then(response => response.json())
            .then(data => {
                // Update the search results on the page
                updateSearchResults(data);
            })
            .catch(error => {
                // Handle any errors that occur during the request
                console.error('에러: ', error);
            });
    }

    function updateSearchResults(results) {
        var tbody = document.getElementById("tbody");
        tbody.innerHTML = ""; // Clear existing content

        // Loop through the latest results and create table rows
        for (var i = 0; i < results.length; i++) {
            var result = results[i];

            // Create and populate each table row
            var row = document.createElement("tr");

            // Set the height of the table row
            row.style.height = "2.5em"; // Adjust the height value as needed
            // Set the font size of the table row
            row.style.fontSize = "15px";

            // Create and populate each table cell
            var idCell = document.createElement("td");
            idCell.textContent = result.pcId;
            row.appendChild(idCell);

            var titleCell = document.createElement("td");
            titleCell.classList.add("truncate");
            titleCell.textContent = result.pcTitle.length > 20 ? result.pcTitle.substring(0, 17) + "..." : result.pcTitle;
            row.appendChild(titleCell);

            var nameCell = document.createElement("td");
            nameCell.textContent = result.pcName.substring(0, 1) + "**";
            row.appendChild(nameCell);

            var dateCell = document.createElement("td");
            dateCell.textContent = result.pcDateFormatted;
            row.appendChild(dateCell);

            var statusCell = document.createElement("td");
            statusCell.textContent = result.pcResponseStatus;
            row.appendChild(statusCell);

            tbody.appendChild(row);

            // Add click event listener to handle opening with the right password
            row.addEventListener("click", function(event) {
                var pcPw = prompt("비밀번호를 입력해주세요");
                if (pcPw === result.pcPw) {
                    // Open the result
                    window.location.href = "/police-complaint/" + result.pcId;
                } else {
                    alert("비밀번호가 일치하지 않습니다.");
                }
            });
        }
    }
});


//////////////////////////////////////////////////////
// Pagination
// Wait for the DOM to load
// document.addEventListener('DOMContentLoaded', function () {
//     var pageNumber = 1; // Initialize page number to 1
//     var pageSize = 5; // Set the desired page size
//     var loadAction = document.getElementById('searchForm').getAttribute('data-load-action');
//
//     // Get the pagination container element
//     var paginationContainer = document.getElementById('pagination');
//
//     // Function to show or hide the pagination container
//     function togglePagination(show) {
//         var paginationContainer = document.getElementById('pagination');
//         paginationContainer.style.display = show ? 'block' : 'none';
//
//         if (show) {
//             paginationContainer.style.display = 'inline-flex';
//             paginationContainer.style.justifyContent = 'space-evenly';
//
//             var links = paginationContainer.getElementsByTagName('a');
//             for (var i = 0; i < links.length; i++) {
//                 links[i].style.textDecoration = 'none';
//                 links[i].style.color = 'black';
//             }
//         } else {
//             paginationContainer.style.display = 'none';
//         }
//     }
//
//
//     if (loadAction === '/online-complaint/load-latest') {
//         // Fetch the latest results when the page is first loaded
//         fetchLatestResults(pageNumber, pageSize);
//     }
//
//     function fetchLatestResults(pageNumber, pageSize) {
//         // Make a GET request to the server endpoint that returns the latest results
//         fetch('/online-complaint/latest-results?pageNumber=' + pageNumber + '&pageSize=' + pageSize)
//             .then(response => response.json())
//             .then(data => {
//                 // Handle the received data, such as updating the UI with the latest results
//                 updateSearchResults(data);
//                 togglePagination(true); // Show the pagination container
//             })
//             .catch(error => {
//                 // Handle any errors that occur during the request
//                 console.error('에러: ', error);
//             });
//     }
//
//     function updateSearchResults(results) {
//         var tbody = document.getElementById("tbody");
//         tbody.innerHTML = ""; // Clear existing content
//
//         // Loop through the latest results and create table rows
//         for (var i = 0; i < results.length; i++) {
//             var result = results[i];
//
//             // Create and populate each table row
//             var row = document.createElement("tr");
//
//             // Set the height of the table row
//             row.style.height = "2.5em"; // Adjust the height value as needed
//             // Set the font size of the table row
//             row.style.fontSize = "15px";
//
//             // Create and populate each table cell
//             var idCell = document.createElement("td");
//             idCell.textContent = result.ocId;
//             row.appendChild(idCell);
//
//             var titleCell = document.createElement("td");
//             titleCell.classList.add("truncate");
//             titleCell.textContent =
//                 result.ocTitle.length > 20 ? result.ocTitle.substring(0, 17) + "..." : result.ocTitle;
//             row.appendChild(titleCell);
//
//             var advisorCell = document.createElement("td");
//             advisorCell.textContent = result.ocAdvisor;
//             row.appendChild(advisorCell);
//
//             var nameCell = document.createElement("td");
//             nameCell.textContent = result.ocName.substring(0, 1) + "**";
//             row.appendChild(nameCell);
//
//             var dateCell = document.createElement("td");
//             dateCell.textContent = result.ocDateFormatted;
//             row.appendChild(dateCell);
//
//             var statusCell = document.createElement("td");
//             statusCell.textContent = result.ocResponseStatus;
//             row.appendChild(statusCell);
//
//             tbody.appendChild(row);
//
//             // Add click event listener to handle opening with the right password
//             row.addEventListener("click", function (event) {
//                 var ocPw = prompt("비밀번호를 입력해주세요");
//                 if (ocPw === result.ocPw) {
//                     // Open the result
//                     window.location.href = "/online-complaint/" + result.ocId;
//                 } else {
//                     alert("비밀번호가 일치하지 않습니다.");
//                 }
//             });
//         }
//     }
//
//     // Define the total number of pages and current page
//     var totalPages = 10;
//     var currentPage = 1;
//
//     // Generate pagination links
//     var prevPageLink = document.createElement('a');
//     prevPageLink.href = '#';
//     prevPageLink.textContent = '<<';
//     paginationContainer.appendChild(prevPageLink);
//
//     var prevLink = document.createElement('a');
//     prevLink.href = '#';
//     prevLink.textContent = '<';
//     paginationContainer.appendChild(prevLink);
//
//     var links = []; // Array to store the pagination links
//
//     for (var i = 1; i <= totalPages; i++) {
//         var link = document.createElement('a');
//         link.href = '#';
//         link.textContent = i;
//         paginationContainer.appendChild(link);
//         links.push(link); // Add the link to the array
//
//         // Add event handler for pagination link click
//         link.addEventListener('click', function (event) {
//             event.preventDefault();
//             // Handle pagination link click, e.g., fetch results for the clicked page
//             var page = parseInt(event.target.textContent);
//             fetchResultsForPage(page);
//         });
//     }
//
//     var nextLink = document.createElement('a');
//     nextLink.href = '#';
//     nextLink.textContent = '>';
//     paginationContainer.appendChild(nextLink);
//
//     var nextPageLink = document.createElement('a');
//     nextPageLink.href = '#';
//     nextPageLink.textContent = '>>';
//     paginationContainer.appendChild(nextPageLink);
//
//     // Set the current page as active
//     links[currentPage - 1].classList.add('active');
//
//     // JavaScript code to handle pagination
//     function fetchResultsForPage(page) {
//         var pageSize = 5; // Set the desired page size
//
//         // Make an AJAX request to fetch the results for the specified page
//         fetch('/online-complaint/latest-results?page=' + page)
//             .then(response => response.json())
//             .then(data => {
//                 // Handle the received data, such as updating the UI with the results
//                 updateSearchResults(data);
//                 updatePagination(page); // Update the pagination links and current page
//
//                 // Update the total number of pages based on the received results
//                 totalPages = Math.ceil(data.length / pageSize);
//             })
//             .catch(error => {
//                 // Handle any errors that occur during the request
//                 console.error('에러: ', error);
//             });
//     }
//
//     function updatePagination(page) {
//         // Remove the active class from the current page link
//         links[currentPage - 1].classList.remove('active');
//
//         // Update the current page with the new value
//         currentPage = page;
//
//         // Add the active class to the new current page link
//         links[currentPage - 1].classList.add('active');
//     }
//
//     // Example usage:
//     // When the next page link is clicked, call fetchResultsForPage with the appropriate page number
//     nextPageLink.addEventListener('click', function () {
//         if (currentPage < totalPages) {
//             fetchResultsForPage(currentPage + 1);
//         }
//     });
//
//     // When the previous page link is clicked, call fetchResultsForPage with the appropriate page number
//     prevPageLink.addEventListener('click', function () {
//         if (currentPage > 1) {
//             fetchResultsForPage(currentPage - 1);
//         }
//     });
//
// });