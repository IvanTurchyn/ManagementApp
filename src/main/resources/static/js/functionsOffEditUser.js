var baseUrl = "http://localhost:8080";

document.addEventListener("DOMContentLoaded", function () {
  let urlParams = new URLSearchParams(window.location.search);
  let userId = urlParams.get("id");

  fetch(baseUrl + "/users/" + userId)
    .then((response) => response.json())
    .then((user) => {
      document.querySelector("#firstName").value = user.firstName;
      document.querySelector("#lastName").value = user.lastName;
      document.querySelector("#email").value = user.email;
    })
    .catch((error) => {
      console.log(error);
    });

  document.querySelector("#saveUser").addEventListener("click", function (event) {
    event.preventDefault();

    let firstName = document.querySelector("#firstName").value;
    let lastName = document.querySelector("#lastName").value;
    let email = document.querySelector("#email").value;

    if (firstName === "" || lastName === "" || email === "") {
      alert("Wszystkie pola są wymagane");
    } else {
      let userData = JSON.stringify({
        firstName: firstName,
        lastName: lastName,
        email: email,
        modified: new Date().toISOString(),
      });

      fetch(baseUrl + "/users/" + userId, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: userData,
      })
        .then((response) => {
          if (response.ok) {
          alert("Dane użytknika zostały zmienione");
          location.href = "index.html";
          } else {
          throw new Error("Wystąpił błąd podczas aktualizacji danych użytkownika");
          }
          })
          .catch((error) => {
          console.log(error);
          });
          }
});
document.getElementById("cancelButton").addEventListener("click", function (event) {
window.location.href = "index.html";
});
});
