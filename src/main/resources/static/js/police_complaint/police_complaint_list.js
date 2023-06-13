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
                var pcPw = prompt("비밀번호를 입력해주세요");
                var pcId = row.querySelector("td:first-child").textContent;

                console.log("pcId:", pcId);
                console.log("pcPw:", pcPw);

                if (pcPw !== null) {
                    fetch('/police-complaint/check-password', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify({ pcId: pcId, pcPw: pcPw }),
                    })
                        .then(response => response.json())
                        .then(data => {
                            console.log("Response:", data);
                            if (data.valid) {
                                // Redirect to the new HTML page with the data
                                window.location.href = "/police-complaint/detail?pcId=" + pcId;
                            } else {
                                alert("비밀번호가 일치하지 않습니다.");
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

        }
    }
});