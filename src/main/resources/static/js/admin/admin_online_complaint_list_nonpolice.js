document.addEventListener('DOMContentLoaded', function () {
    var loadAction = document.getElementById('searchForm').getAttribute('data-load-action');

    if (loadAction === '/online-complaint/admin/n/load-latest') {
        // Fetch the latest results when the page is first loaded
        fetchLatestResults();
    }

    function fetchLatestResults() {
        return fetch('/online-complaint/admin/n/latest-results')
            .then(response => response.json())
            .then(data => {
                // Update the search results on the page
                updateSearchResults(data);
                attachRowClickListener();
            })
            .catch(error => {
                // Handle any errors that occur during the request
                console.error('에러: ', error);
            });
    }

    function attachRowClickListener() {
        var rows = document.querySelectorAll("#tbody tr");

        Array.from(rows).forEach(function (row) {
            row.addEventListener("click", function (event) {
                var ocAdvisor = prompt("관리자 형태(상담사, 변호사, 경찰)를 입력하세요.");
                var ocId = row.querySelector("td:first-child").textContent;

                console.log("ocId:", ocId);
                console.log("ocAdvisor:", ocAdvisor);

                if (ocAdvisor !== null) {
                    fetch('/online-complaint/admin/n/check-type', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify({ ocId: ocId, ocAdvisor: ocAdvisor }),
                    })
                        .then(response => response.json())
                        .then(data => {
                            console.log("Response:", data);
                            if (data.valid) {
                                // Redirect to the new HTML page with the data
                                window.location.href = "/online-complaint/admin/n/detail?num=" + ocId + "&advisor=" + ocAdvisor;
                            } else {
                                alert("권한이 없습니다.");
                            }
                        })
                        .catch(error => {
                            console.error('에러:', error);
                        });
                }
            });
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
            idCell.textContent = result.ocId;
            row.appendChild(idCell);

            var titleCell = document.createElement("td");
            titleCell.classList.add("truncate");
            titleCell.textContent =
                result.ocTitle.length > 20 ? result.ocTitle.substring(0, 17) + "..." : result.ocTitle;
            row.appendChild(titleCell);

            var advisorCell = document.createElement("td");
            advisorCell.textContent = result.ocAdvisor;
            row.appendChild(advisorCell);

            var nameCell = document.createElement("td");
            nameCell.textContent = result.ocName.substring(0, 1) + "**";
            row.appendChild(nameCell);

            var dateCell = document.createElement("td");
            dateCell.textContent = result.ocDateFormatted;
            row.appendChild(dateCell);

            var statusCell = document.createElement("td");
            statusCell.textContent = result.ocResponseStatus;
            row.appendChild(statusCell);

            tbody.appendChild(row);
        }
    }
});