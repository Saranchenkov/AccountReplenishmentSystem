/**
 * Created by Ivan on 18.06.2017.
 */
var page;
var table;
var entity;
var xhttp = new XMLHttpRequest();

window.onload = function () {
    entity = document.getElementsByTagName("body")[0].getAttribute("id");
    console.log(entity);
    xhttp.open("GET", "/" + entity + "/0", true);
    xhttp.send();
};


xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        table = document.getElementsByClassName("container")[0];
        table.lastElementChild.innerHTML = getTBody(entity);
    }
};

// --------------Parses server response and creates table content------------------
function getTBody(entity) {
    var tableContent = "";
    var response = JSON.parse(xhttp.responseText);
    console.log(response);
    if (response.hasOwnProperty("content")) {
        page = response;
        createPagination();
        for (var i = 0; i < page.numberOfElements; i++) {
            switch (entity){
                case "users": tableContent += getUserRow(page.content[i]); break;
                case "balances": tableContent += getBalanceRow(page.content[i]); break;
            }
        }
    } else {
        tableContent += getUserRow(response);
        removePaginagion();
    }
    return tableContent;
}

// ----------------Gets table row of User object------------------
function getUserRow(user) {
    var date = new Date(user.registerDate);
    return "<tr id='" + user.id + "'>" +
        "<td onclick='openModal(this)'>" + user.email + "</td>" +
        "<td>" + user.balance.toFixed(2) + "$</td>" +
        "<td>" + getFormattedDate(date) + "</td>" +
        "</tr>";
}

// ----------------Gets table row of Balance object------------------
function getBalanceRow(balance) {
    var date = new Date(balance.date);
    return "<tr>" +
        "<td>" + balance.adminEmail + "</td>" +
        "<td>" + balance.userEmail + "</td>" +
        "<td>" + getFormattedDate(date) + "</td>" +
        "<td>" + balance.amount.toFixed(2) + "$</td>" +
        "</tr>";
}

// ----------------Formats date to pattern "dd-MM-yy"------------------
function getFormattedDate(date) {
    var month = date .getMonth() + 1 < 10 ? "0" + (date .getMonth() + 1) : (date .getMonth() + 1);
    var day = date .getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var year = date .getFullYear()%100;
    return day + "/" + month + "/" + year;
}

// ----------------FILTERING------------------

function filter() {
    var startDate = document.getElementById("startDate").value;
    var endDate = document.getElementById("endDate").value;
    var url = "/balances/filter/0?startDate=" + startDate + "&endDate=" + endDate;
    xhttp.open("GET", url, true);
    xhttp.send();
}

// ----------------SEARCHING------------------

function search() {
    var email = document.getElementById("email").value;
    var url = "/users/search?email=" + email;
    xhttp.open("GET", url, true);
    xhttp.send();
}