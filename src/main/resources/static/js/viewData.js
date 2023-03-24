var baseUrl = "http://localhost:8080";

document.getElementById("cancelButton").addEventListener("click", function (event) {
  event.preventDefault();
  window.location.href = "index.html";
});

document.addEventListener("DOMContentLoaded", function () {
  const urlParams = new URLSearchParams(window.location.search);
  const userId = urlParams.get("id");

  const xhr = new XMLHttpRequest();
  xhr.open("GET", baseUrl + "/users/" + userId + "/timestamps");
  xhr.send();
  xhr.onload = function () {
    if (xhr.status === 200) {
      const response = JSON.parse(xhr.responseText);
      const tr = document.createElement("tr");
      const tdName = document.createElement("td");
      tdName.innerText = response.firstName;
      tr.appendChild(tdName);
      const tdLastName = document.createElement("td");
      tdLastName.innerText = response.lastName;
      tr.appendChild(tdLastName);
      const tdEmail = document.createElement("td");
      tdEmail.innerText = response.email;
      tr.appendChild(tdEmail);
      const tdCreatedAt = document.createElement("td");
      const dateAdded = new Date(response.added);
      tdCreatedAt.innerText = dateAdded.toLocaleString();
      tr.appendChild(tdCreatedAt);
      const tdLastEdit = document.createElement("td");
      const dateModified = new Date(response.modified);
      tdLastEdit.innerText = dateModified.toLocaleString();
      tr.appendChild(tdLastEdit);
      document.querySelector("#userData").appendChild(tr);
      }
    };
});


