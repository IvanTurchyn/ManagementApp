var baseUrl = "http://localhost:8080";

document.querySelector("#submitButton").addEventListener("click", function (event) {
  event.preventDefault();

  let isFormValid = true;
  let errorMessage = "";

  let firstName = document.querySelector("#firstName").value;
  let lastName = document.querySelector("#lastName").value;
  let email = document.querySelector("#email").value;

  if (!firstName) {
    errorMessage += "Pole Imie jest wymagane\n";
    isFormValid = false;
  }

  if (!lastName) {
    errorMessage += "Pole Nazwisko jest wymagane\n";
    isFormValid = false;
  }

  if (!email) {
    errorMessage += "Pole Email jest wymagane\n";
    isFormValid = false;
  }

  if (!isFormValid) {
    alert(errorMessage || "Wszystkie pola muszą być wypełnione");
  } else {
    var data = new FormData();
    data.append("firstName", firstName);
    data.append("lastName", lastName);
    data.append("email", email);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", baseUrl + "/users", true);
    xhr.onload = function () {
      if (xhr.status === 200) {
        window.location.href = "index.html";
      } else {
        console.error("Wystąpił błąd podczas zapisywania danych");
      }
    };
    xhr.send(data);
  }
});

document.getElementById("cancelButton").addEventListener("click", function (event) {
  event.preventDefault();
  window.location.href = "index.html";
});
