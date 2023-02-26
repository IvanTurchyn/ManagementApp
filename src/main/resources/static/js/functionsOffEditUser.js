var baseUrl = "http://40.81.16.213";

$(document).ready(function() {
// Pobranie ID użytkownika z parametru URL
let urlParams = new URLSearchParams(window.location.search);
let userId = urlParams.get("id");

// Pobieranie danych użytkownika za pomocą AJAX
$.ajax({
url: baseUrl + "/users/" + userId,
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

// Walidacja danych formularza
if (firstName == "" || lastName == "" || email == "") {
alert("Wszystkie pola są wymagane");
} else {
// Wysłanie zapytania do serwera za pomocą AJAX
$.ajax({
url: baseUrl + "/users/" + userId,
type: "PUT",
contentType: "application/json",
data: JSON.stringify({
firstName: firstName,
lastName: lastName,
email: email,
modified: new Date().toISOString()
}),
success: function() {
alert("Dane użytkownika zostały zmienione");
location.href = "index.html";
},
error: function(error) {
console.log(error);
}
});
}
});
});

document.getElementById("cancelButton").addEventListener("click", function(event) {
event.preventDefault();
window.location.href = "index.html";
});