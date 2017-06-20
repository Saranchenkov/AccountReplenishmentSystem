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
}


xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        table = document.getElementsByClassName("container")[0];
        table.lastElementChild.innerHTML = getTBodyAsHTML(entity);
        createPagination();
    }
};

function getTBodyAsHTML(entity) {
    var tableContent = "";
    page = JSON.parse(xhttp.responseText);
    console.log(page);
    for(var i = 0; i < page.numberOfElements; i++){
        if (entity == "users"){
            tableContent += getUserRow(i);
        } else {
            tableContent += getBalanceRow(i);
        }
    }
    return tableContent;
}

function getUserRow(index) {
    var user = page.content[index];
    var date = new Date(user.registerDate);
    var row = "" +
        "<tr>" +
            "<td onclick='openModal(this)'>" + user.email + "</td>" +
            "<td>" + user.balance.toFixed(2) + "$</td>" +
            "<td>" + getFormattedDate(date) + "</td>" +
        "</tr>";
    return row;
}

function getBalanceRow(index) {
    var balance = page.content[index];
    var date = new Date(balance.date);
    var row = "" +
        "<tr>" +
        "<td>" + balance.adminEmail + "</td>" +
        "<td>" + balance.userEmail + "</td>" +
        "<td>" + getFormattedDate(date) + "</td>" +
        "<td>" + balance.amount.toFixed(2) + "$</td>" +
        "</tr>";
    return row;
}

function getFormattedDate(date) {
    var month = date .getMonth() + 1 < 10 ? "0" + (date .getMonth() + 1) : (date .getMonth() + 1);
    var day = date .getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var year = date .getFullYear() - 2000;
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
