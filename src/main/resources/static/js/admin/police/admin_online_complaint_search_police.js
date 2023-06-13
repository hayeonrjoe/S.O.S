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
        xhr.open("GET", "/online-complaint/admin/p/search?query=" + searchTerm, true);

        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    // Update the search results on the page
                    var searchResults = JSON.parse(xhr.responseText);
                    // Filter the search results based on ocAdvisor value
                    searchResults = searchResults.filter(function(result) {
                        return result.ocAdvisor === "경찰" && result.ocResponseStatus === "답변대기";
                    });

                    searchResults.sort(function(a, b) {
                        return b.ocId - a.ocId; // Sort in descending order based on ocId
                    });
                    var tbody = document.getElementById("tbody");
                    tbody.innerHTML = ""; // Clear existing content

                    // Loop through the search results and create table rows
                    for (var i = 0; i < searchResults.length; i++) {
                        var result = searchResults[i];
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
                        titleCell.textContent = result.ocTitle.length > 20 ? result.ocTitle.substring(0, 17) + "..." : result.ocTitle;
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

                        row.addEventListener("click", function(event) {
                            // Redirect to the next page with the ocId as a query parameter
                            var url = "/online-complaint/admin/p?num=" + result.ocId;
                            window.location.href = url;
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
