/**
 * Created by Ivan on 18.06.2017.
 */
var paginationDiv;

function createPagination() {
    paginationDiv = document.getElementsByClassName("pagination")[0];
    paginationDiv.innerHTML = getRefers();
    setEvent(paginationDiv.childNodes);
}

function setEvent(nodes) {
    nodes.forEach(node => {node.setAttribute("onclick", "sendRequest(this.innerHTML)")});
}

function sendRequest(innerHTML) {
    switch(innerHTML){
        case "Previous" : {if (!page.first) request(page.number - 1)}
            break;
        case "Next" : {if (!page.last) request(page.number + 1)}
            break;
        default: {
            var num = parseInt(innerHTML) - 1;
            if (num != page.number) request(num)
        }
    }
}

function request(num) {
    xhttp.open("GET", "/" + entity + "/" + num, true);
    xhttp.send();
}

function getRefers() {
    var paginationRefers = '<a>Previous</a>';
    for(var i = 0; i < page.totalPages; i++){
        if (page.number != i) {
            paginationRefers += '<a>' + (i + 1) + '</a>';
        } else {
            paginationRefers += '<a class="is-active">' + (i + 1) + '</a>';
        }
    }
    paginationRefers += '<a>Next</a>';
    return paginationRefers;
}
