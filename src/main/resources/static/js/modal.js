/**
 * Created by Ivan on 19.06.2017.
 */
var modal;
var closeSpan;
var xhttpModal = new XMLHttpRequest();

function openModal(emailNode) {
    modal = document.getElementById('myModal');
    modal.style.display = "block";
    var email = emailNode.innerHTML;
    document.getElementById("userEmail").innerHTML = email;
    document.getElementById("putMoney").onclick = function log() {sendPostRequest(email)};
    closeSpan = document.getElementsByClassName("close")[0];
    closeSpan.onclick = function () {
        modal.style.display = "none";
    }
}

function sendPostRequest(email){
    var user;
    for(var i = 0; i < page.size; i++){
        if (page.content[i].email == email){
            user = page.content[i];
        }
    }
    var amount = document.getElementById("amount").value;
    var balance = {
        id : null,
        date : new Date(),
        amount : amount,
        userEmail : email,
        adminEmail : "ivan.saranchenkov@gmail.com"
    };
    var url = "/user/" + user.id + "?amount=" + amount + "&newBalance=" + (user.balance + amount);
    console.log(balance);
    xhttpModal.open("POST", url, true);
    xhttpModal.setRequestHeader("Content-type", "application/json");
    xhttpModal.setRequestHeader("Accept", "application/json");
    xhttpModal.send(JSON.stringify(balance));
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}