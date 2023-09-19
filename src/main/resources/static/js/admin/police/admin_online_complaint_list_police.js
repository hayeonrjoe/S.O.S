document.addEventListener('DOMContentLoaded', function () {
    var loadAction = document.getElementById('searchForm').getAttribute('data-load-action');

    if (loadAction === '/online-complaint/admin/p/load-latest') {
        fetchLatestResults()
            .then(() => {
                attachRowClickListener();
            })
            .catch(error => {
                console.error('에러: ', error);
            });
    }

    function fetchLatestResults() {
        return fetch('/online-complaint/admin/p/latest-results')
            .then(response => response.json())
            .then(data => {
                updateSearchResults(data);
                attachRowClickListener();
            })
            .catch(error => {
                console.error('에러: ', error);
            });
    }

    function attachRowClickListener() {
        var rows = document.querySelectorAll("#tbody tr");

        Array.from(rows).forEach(function(row) {
            row.addEventListener("click", function() {
                var ocId = row.querySelector("td:first-child").textContent;

                window.location.href = "/online-complaint-comment-form/admin/p?num=" + ocId;
            });
        });
    }



    function updateSearchResults(results) {
        var tbody = document.getElementById("tbody");
        tbody.innerHTML = ""; // Clear existing content

        var filteredResults = results.filter(function(result) {
            return result.ocAdvisor === "경찰" && result.ocResponseStatus === "답변대기";
        });

        for (var i = 0; i < filteredResults.length; i++) {
            var result = filteredResults[i];

            var row = document.createElement("tr");

            row.style.height = "2.5em";
            row.style.fontSize = "15px";

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