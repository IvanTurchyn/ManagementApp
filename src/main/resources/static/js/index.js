var baseUrl = "http://localhost:8080";

document.addEventListener("DOMContentLoaded", function () {
  const userList = document.getElementById("userList");

  fetch(baseUrl + "/users")
    .then((response) => response.json())
    .then((data) => {
      data.forEach((user) => {
        const tr = document.createElement("tr");
        tr.innerHTML = `<td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td><button class="btn btn-primary mr-2 edit-user" data-id="${user.id}">Edytuj</button>
                            <button class="btn btn-danger mr-2 delete-user" data-id="${user.id}">Usuń</button>
                            <button class="btn btn-primary mr-2 view-user" data-id="${user.id}">Podgląd</button></td>`;
        userList.appendChild(tr);
      });
    })
    .catch((error) => {
      console.log(error);
    });

  document.addEventListener("click", function (event) {
    if (event.target.classList.contains("delete-user")) {
      const id = event.target.getAttribute("data-id");
      const row = event.target.closest("tr");
      const firstName = row.cells[0].innerText;
      const lastName = row.cells[1].innerText;
      const name = firstName + " " + lastName;
      if (confirm("Czy napewno chcesz usunąć dane użytkownika " + name + "?")) {
        fetch(baseUrl + "/users/" + id, {
          method: "DELETE",
        })
          .then(() => {
            row.remove();
            alert("Użytkownik został usunięty z bazy danych");
          })
          .catch((error) => {
            console.log(error);
          });
      }
    }
  });

  document.getElementById("addUser").addEventListener("click", function () {
    window.location.href = "addUser.html";
  });

  document.addEventListener("click", function (event) {
    if (event.target.classList.contains("edit-user")) {
      const userId = event.target.getAttribute("data-id");
      location.href = "editUser.html?id=" + userId;
    }
  });

  document.addEventListener("click", function (event) {
    if (event.target.classList.contains("view-user")) {
      const userId = event.target.getAttribute("data-id");
      location.href = "viewDataUser.html?id=" + userId;
    }
  });
});



