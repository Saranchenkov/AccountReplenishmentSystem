/**
 * Created by Ivan on 22.06.2017.
 */
var registerForm;
var loginForm;
var registerBtn;
var xhttpRegister = new XMLHttpRequest();

window.onload = function () {
    registerForm = document.getElementById("register-form");
    loginForm = document.getElementById("login-form");
    registerForm.classList.add("invisible");
    registerBtn = document.getElementById("registerBtn");
    registerBtn.onclick = function () {register()};


    document.getElementById("signinMes").onclick = function () {changeVisibility()};
    document.getElementById("createMes").onclick = function () {changeVisibility()};
    };


function login() {
    var xhttpLogin = new XMLHttpRequest();
    var elements = loginForm.elements;
    var url = "/login?email=" + elements.item(0).value + "&password=" + elements.item(1).value;
    xhttpLogin.open("POST", url, true);
    xhttpLogin.send();
}

function register() {
    var user = {};
    var elements = registerForm.elements;
    for(var i = 0 ; i < elements.length ; i++){
        var item = elements.item(i);
        user[item.name] = item.value;
    }
    console.log(user);
    xhttpRegister.open("POST", "/register", true);
    xhttpRegister.setRequestHeader("Content-type", "application/json");
    xhttpRegister.send(JSON.stringify(user));
    xhttpRegister.onloadend = function () {
        var response = JSON.parse(xhttpRegister.responseText);
        var errors = document.getElementById("errors");
        var message = "";
        response.forEach(error => {message += error + "<br/>"});
        errors.innerHTML = message;
    }
}

function changeVisibility() {
    registerForm.classList.toggle("invisible");
    loginForm.classList.toggle("invisible");
}
