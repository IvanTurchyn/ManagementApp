$(document).ready(function() {
// Pobranie ID użytkownika z parametru URL
let urlParams = new URLSearchParams(window.location.search);
let userId = urlParams.get("id");

// Pobieranie danych użytkownika za pomocą AJAX
$.ajax({
url: "http://localhost:8080/users/" + userId,
type: "GET",
dataType: "json",
success: function(user) {
// Wypełnienie formularza danymi użytkownika
$("#firstName").val(user.firstName);
$("#lastName").val(user.lastName);
$("#email").val(user.email);
},
error: function(error) {
console.log(error);
}
});

// Obsługa kliknięcia przycisku "Zapisz"
$("#saveUser").click(function(event) {
event.preventDefault();
// Pobieranie danych z formularza
let firstName = $("#firstName").val();
let lastName = $("#lastName").val();
let email = $("#email").val();

// Wysłanie zapytania do serwera za pomocą AJAX
$.ajax({
  url: "http://localhost:8080/users/" + userId,
  type: "PUT",
  contentType: "application/json",
  data: JSON.stringify({
    firstName: firstName,
    lastName: lastName,
    email: email
  }),
  success: function() {
    alert("Dane użytkownika zostały zmienione");
    location.href = "index.html";
  },
  error: function(error) {
    console.log(error);
  }
});
});
});