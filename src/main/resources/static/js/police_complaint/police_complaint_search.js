document.addEventListener('DOMContentLoaded', function() {
    var searchForm = document.getElementById('searchForm');
    searchForm.addEventListener('submit', function(event) {
        event.preventDefault();
        search();
    });
    function search() {
        var searchTerm = document.getElementById("searchInput").value;

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/police-complaint/search?query=" + searchTerm, true);

        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    var searchResults = JSON.parse(xhr.responseText);
                    searchResults.sort(function(a, b) {
                        return b.pcId - a.pcId;
                    });
                    var tbody = document.getElementById("tbody");
                    tbody.innerHTML = "";

                    for (var i = 0; i < searchResults.length; i++) {
                        var result = searchResults[i];
                        var row = document.createElement("tr");

                        row.style.height = "2.5em";
                        row.style.fontSize = "15px";

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

                        row.addEventListener("click", createRowClickListener(result));
                    }

                } else {
                    console.error("에러: " + xhr.status);
                }
            }
        };

        xhr.send();
    }
    function createRowClickListener(result) {
        return function (event) {
            var pcPw = prompt("비밀번호를 입력해주세요");
            if (pcPw === result.pcPw) {
                window.location.href = "/police-complaint/detail?num=" + result.pcId;
            } else {
                alert("비밀번호가 일치하지 않습니다.");
            }
        };
    }
});

