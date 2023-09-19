document.addEventListener('DOMContentLoaded', function() {
    var searchForm = document.getElementById('searchForm');
    searchForm.addEventListener('submit', function(event) {
        event.preventDefault();
        search();
    });

    function search() {
        var searchTerm = document.getElementById("searchInput").value;


        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/online-complaint/admin/l/search?query=" + searchTerm, true);

        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {

                    var searchResults = JSON.parse(xhr.responseText);

                    searchResults = searchResults.filter(function(result) {
                        return result.ocAdvisor === "변호사" && result.ocResponseStatus === "답변대기";
                    });

                    searchResults.sort(function(a, b) {
                        return b.ocId - a.ocId;
                    });
                    var tbody = document.getElementById("tbody");
                    tbody.innerHTML = "";

                    for (let i = 0; i < searchResults.length; i++) {
                        let result = searchResults[i];
                        let row = document.createElement("tr");


                        row.style.height = "2.5em";
                        row.style.fontSize = "15px";

                        let idCell = document.createElement("td");
                        idCell.textContent = result.ocId;
                        row.appendChild(idCell);

                        let titleCell = document.createElement("td");
                        titleCell.classList.add("truncate");
                        titleCell.textContent = result.ocTitle.length > 20 ? result.ocTitle.substring(0, 17) + "..." : result.ocTitle;
                        row.appendChild(titleCell);

                        let advisorCell = document.createElement("td");
                        advisorCell.textContent = result.ocAdvisor;
                        row.appendChild(advisorCell);

                        let nameCell = document.createElement("td");
                        nameCell.textContent = result.ocName.substring(0, 1) + "**";
                        row.appendChild(nameCell);

                        let dateCell = document.createElement("td");
                        dateCell.textContent = result.ocDateFormatted;
                        row.appendChild(dateCell);

                        let statusCell = document.createElement("td");
                        statusCell.textContent = result.ocResponseStatus;
                        row.appendChild(statusCell);

                        tbody.appendChild(row);

                        row.addEventListener("click", function() {
                            window.location.href = "/online-complaint-comment-form/admin/l?num=" + result.ocId;
                        });
                    }

                } else {
                    console.error("에러: " + xhr.status);
                }
            }
        };

        xhr.send();
    }
});
