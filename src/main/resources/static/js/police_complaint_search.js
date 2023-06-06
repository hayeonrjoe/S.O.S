// Wait for the DOM to load
document.addEventListener('DOMContentLoaded', function() {
    var searchForm = document.getElementById('searchForm');
    searchForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent the default form submission
        search(); // Call your search function
    });
    function search() {
        var searchTerm = document.getElementById("searchInput").value;

        // Make an AJAX request to the server
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/police-complaint/search?query=" + searchTerm, true);

        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    // Update the search results on the page
                    var searchResults = JSON.parse(xhr.responseText);
                    var tbody = document.getElementById("tbody");
                    tbody.innerHTML = ""; // Clear existing content

                    // Loop through the search results and create table rows
                    for (var i = 0; i < searchResults.length; i++) {
                        var result = searchResults[i];
                        var row = document.createElement("tr");

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
                } else {
                    // Handle the error case
                    console.error("에러: " + xhr.status);
                }
            }
        };

        // Send the AJAX request
        xhr.send();
    }
});
