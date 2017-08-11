/**
 * Created by Ivan on 22.06.2017.
 */
var xhttpPerson = new XMLHttpRequest();
window.onload = function () {
    xhttpPerson.open("GET", "/user", true);
    xhttpPerson.send();
};


xhttpPerson.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        insertPrincipal();
    }
};

function insertPrincipal() {
    var userTO = JSON.parse(xhttpPerson.responseText);
    console.log(userTO);
    document.getElementById("userEmail").innerHTML = userTO.email;
    document.getElementById("userBalance").innerHTML = parseFloat(userTO.balance).toFixed(2) + "$";
}