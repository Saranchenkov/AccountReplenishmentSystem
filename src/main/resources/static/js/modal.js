/**
 * Created by Ivan on 19.06.2017.
 */
var modal;
var closeSpan;
var xhttpModal = new XMLHttpRequest();

// --------Makes modal window visible and operable------------------
function openModal(emailNode) {
    modal = document.getElementById('replenishment');
    modal.style.display = "block";
    document.getElementById("userEmail").innerHTML = emailNode.innerHTML;
    closeSpan = document.getElementsByClassName("close")[0];
    closeSpan.onclick = function close() {modal.style.display = "none";};
    document.getElementById("putMoney").onclick = function() {sendPostRequest(emailNode.parentNode)};
}

// -----Gets value of email input, forms request body and sends POST request to server------------------
function sendPostRequest(userTrNode){
    var amount = document.getElementById("amount").value;
    var balance = {
        id : null,
        date : new Date(),
        amount : amount,
        userEmail : userTrNode.childNodes[0].innerHTML,
        adminEmail : "ivan.saranchenkov@gmail.com"
    };
    var userId = userTrNode.getAttribute("id");
    var newBalance = parseFloat(userTrNode.childNodes[1].innerHTML) + parseFloat(amount);
    var url = "/admin/user/" + userId + "?amount=" + amount + "&newBalance=" + newBalance;

    xhttpModal.open("POST", url, true);
    xhttpModal.setRequestHeader("Content-type", "application/json");
    xhttpModal.setRequestHeader("Accept", "application/json");
    xhttpModal.send(JSON.stringify(balance));
    xhttpModal.onloadend = function(){
        if (this.status == 200) {
            userTrNode.childNodes[1].innerHTML = newBalance.toFixed(2) + "$";
        }
    }
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}