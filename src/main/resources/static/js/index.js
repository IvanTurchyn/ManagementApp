var baseUrl = "http://40.81.16.213";

$(document).ready(function() {
let userList = $('#userList');
$.ajax({
url: baseUrl + "/users",
type: "GET",
dataType: "json",
success: function(data) {
// Iterujemy przez tablicę użytkowników
$.each(data, function(index, user) {
// Tworzymy nowy wiersz i dodajemy go do tabeli
let tr = $('<tr>');
tr.append(`<td>${user.firstName}</td>`);
tr.append(`<td> ${user.lastName}</td>`);
tr.append(`<td>${user.email}</td>`);
tr.append(`<td> <button class="btn btn-primary mr-2 edit-user" data-id="${user.id}">Edytuj</button> <button class="btn btn-danger mr-2 delete-user" data-id="${user.id}">Usuń</button> <button class="btn btn-primary mr-2 view-user" data-id="${user.id}">Podgląd</button> </td>`);
userList.append(tr);
});
},
error: function(error) {
console.log(error);
}
});

// Obsługa kliknięcia przycisku Usuń
$(document).on('click', '.delete-user', function(event) {
event.preventDefault();
let id = $(this).data('id');
let row = $(this).closest('tr');
let firstName = row.find('td:first').text();
let lastName = row.find('td:eq(1)').text();
let name = firstName + " " + lastName;
if (confirm("Czy napewno chcesz usunąć dane użytkownika " + name + "?")) {
$.ajax({
url: baseUrl + "/users/" + id,
type: "DELETE",
success: function() {
row.remove();
// Dodanie zapytania do serwera, które usunie dane użytkownika z bazy danych
$.ajax({
url: baseUrl + "/users/" + id,
type: "DELETE",
success: function() {
alert("Użytkownik został usunięty z bazy danych");
},
error: function(error) {
console.log(error);
}
});
},
error: function(error) {
console.log(error);
}
});
}
});

//Przekierowywanie do pliku "addUser.html"
document.getElementById("addUser").addEventListener("click", function() {
window.location.href = "addUser.html";
});
});

$(document).ready(function() {
// Obsługa kliknięcia przycisku "Edytuj"
$('#userList').on('click', '.edit-user', function() {
// Pobieranie ID użytkownika z atrybutu "data-id" przycisku
let userId = $(this).data('id');
// Przekierowywanie do strony edycji użytkownika
location.href = "editUser.html?id=" + userId;
});
});

$(document).ready(function() {
// Obsługa kliknięcia przycisku "Podgląd"
$('#userList').on('click', '.view-user', function() {
// Pobieranie ID użytkownika z atrybutu "data-id" przycisku
let userId = $(this).data('id');
// Przekierowywanie do strony edycji użytkownika
location.href = "viewDataUser.html?id=" + userId;
});
});



