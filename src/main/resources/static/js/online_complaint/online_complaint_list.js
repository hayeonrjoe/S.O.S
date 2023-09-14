document.addEventListener('DOMContentLoaded', function () {
    var loadAction = document.getElementById('searchForm').getAttribute('data-load-action');

    if (loadAction === '/online-complaint/load-latest') {
        fetchPageData();
    }

    var currentPage = 1;

    function fetchPageData(page) {
        // Fetch paginated data from the server using AJAX (e.g., fetch API)
        fetch('/online-complaint/latest-results?page=' + page)
            .then(response => response.json())
            .then(data => {
                currentPage = page; // Update currentPage before updating pagination

                updateSearchResults(data);
                attachRowClickListener();

                updatePagination(data.totalPages);
            })
            .catch(error => {
                console.error('에러: ', error);
            });
    }

    function updatePagination(totalPages) {
        var paginationContainer = document.querySelector(".paginationContainer");
        paginationContainer.innerHTML = "";

        var previousButton = document.createElement("button");
        previousButton.textContent = "<";
        previousButton.classList.add("pagination-button", "previous-button");
        previousButton.addEventListener("click", function () {
            if (currentPage > 1) {
                fetchPageData(currentPage - 1);
            }
        });
        paginationContainer.appendChild(previousButton);

        for (var i = 1; i <= totalPages; i++) {
            var pageButton = document.createElement("button");
            pageButton.textContent = i;
            pageButton.classList.add("pagination-button");
            pageButton.addEventListener("click", function () {
                var page = parseInt(this.textContent);
                fetchPageData(page);
            });

            if (i === currentPage) {
                pageButton.classList.add("active");
            }

            paginationContainer.appendChild(pageButton);
        }

        var nextButton = document.createElement("button");
        nextButton.textContent = ">";
        nextButton.classList.add("pagination-button", "next-button");
        nextButton.addEventListener("click", function () {
            if (currentPage < totalPages) {
                fetchPageData(currentPage + 1);
            }
        });
        paginationContainer.appendChild(nextButton);
    }

    function updateSearchResults(results) {
        var tbody = document.getElementById("tbody");
        tbody.innerHTML = "";

        for (var i = 0; i < results.length; i++) {
            var result = results[i];

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

    fetchPageData(currentPage);

    function attachRowClickListener() {
        var rows = document.querySelectorAll("#tbody tr");

        Array.from(rows).forEach(function (row) {
            row.addEventListener("click", function () {
                var ocPw = prompt("비밀번호를 입력해주세요");
                var ocId = row.querySelector("td:first-child").textContent; // Get ocId as a string

                console.log("ocId:", ocId);
                console.log("ocPw:", ocPw);

                if (ocPw !== null) {
                    fetch('/online-complaint/check-password', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify({ ocId: ocId, ocPw: ocPw }), // Pass ocId as a string
                    })
                        .then(response => response.json())
                        .then(data => {
                            console.log("Response:", data);
                            if (data.valid) {
                                // Redirect to the detail page with ocId as a query parameter
                                window.location.href = "/online-complaint/detail?num=" + ocId;
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
});
