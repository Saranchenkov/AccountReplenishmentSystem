/**
 * Created by Ivan on 18.06.2017.
 */
var paginationDiv;

// ----------Fills pagination <div> block with hyperlinks------------------
function createPagination() {
    paginationDiv = document.getElementsByClassName("pagination")[0];
    paginationDiv.innerHTML = getLinks();
    setOnclickEvent(paginationDiv.childNodes);
}

// --------Sets "onclick" event on each pagination hyperlink------------------
function setOnclickEvent(nodes) {
    nodes.forEach(node => {node.onclick = function(){sendRequest(this.innerHTML)}});
}

// ----------Defines number of page for loading and invokes "request" method------------------
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

// -----Sends GET request to get page with number "num"------------------
function request(num) {
    xhttp.open("GET", "/" + entity + "/" + num, true);
    xhttp.send();
}

// --------Gets list of pagination hyperlinks as HTML------------------
function getLinks() {
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

// --------Removes pagination------------------
function removePaginagion() {
    paginationDiv.innerHTML = null;
}
