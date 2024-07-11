function fillDiv(div, data){
    var divObj = document.getElementById(div);
    divObj.innerHTML = data;
}

function callGetAllUsers() {
    fetch("http://localhost:8080/api/v1/users?detail=true")
    .then(response => {
        if (!response.ok) {
            throw new Error("Network response was not ok: " + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        var respString = "No data received from database.";
        var dataLength = data.length;
        if(dataLength > 0){
            respString = "";
            if(Array.isArray(data) && data.length > 0){
                respString += "<div class=\"tbMain\">"
                respString += "<div class=\"tbRow\">"
                respString += "<div class=\"tbHead\">ID</div>";
                respString += "<div class=\"tbHead\">NAME</div>";
                respString += "<div class=\"tbHead\">SURNAME</div>";
                respString += "<div class=\"tbHead\">DETAIL</div>";
                respString += "<div class=\"tbHead\">UPDATE</div>";
                respString += "<div class=\"tbHead\">DELETE</div></div>";
                for(let i = 0; i < dataLength; i++){
                    respString += "<div class=\"tbRow\">"
                    respString += "<div class=\"tbCell\">" + data[i].id + "</div>";
                    respString += "<div class=\"tbCell\">" + data[i].name + "</div>";
                    respString += "<div class=\"tbCell\">" + data[i].surname + "</div>";
                    respString += "<div class=\"tbCell\"><a href=\"./?listuserdetailform&id=" + data[i].personID + "\">[ Show detail ]</a></div>";
                    respString += "<div class=\"tbCell\"><a href=\"./?updateuserform&id=" + data[i].personID + "&name=" + data[i].name + "&surname=" + data[i].surname + "\">[ Update ]</a></div>";
                    respString += "<div class=\"tbCell\"><a href=\"./?deleteuser&id=" + data[i].personID + "\" onclick=\"return confLink('Are you sure you want to DELETE item PersonID " + data[i].personID + "');\">[ Delete ]</a></div></div>";
                }
                respString += "</div>"
                fillDiv("mainStage", respString);
            }
        }
    })
    .catch(err => console.log('Fetch error: ', err));
}

function listUserDetail(id) {
    fetch("http://localhost:8080/api/v1/user/" + id + "?detail=true")
    .then(response => {
        if (!response.ok) {
            throw new Error("Network response was not ok: " + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        var respString = "No data received from database.";
        var dataLength = data.length;
        if(dataLength > 0){
            respString = "";
            if(Array.isArray(data) && data.length > 0){
                respString += "<div class=\"tbMain\">"
                respString += "<div class=\"tbHeadRow\">"
                respString += "<div class=\"tbHead\">ID</div>";
                respString += "<div class=\"tbHead\">NAME</div>";
                respString += "<div class=\"tbHead\">SURNAME</div>";
                respString += "<div class=\"tbHead\">PERSONID</div>";
                respString += "<div class=\"tbHead\">UUID</div></div>";
                for(let i = 0; i < dataLength; i++){
                    respString += "<div class=\"tbRow\">"
                    respString += "<div class=\"tbCell\">" + data[i].id + "</div>";
                    respString += "<div class=\"tbCell\">" + data[i].name + "</div>";
                    respString += "<div class=\"tbCell\">" + data[i].surname + "</div>";
                    respString += "<div class=\"tbCell\">" + data[i].personID + "</div>";
                    respString += "<div class=\"tbCell\">" + data[i].uuid + "</div></div>";
                }
                respString += "</div>"
                fillDiv("mainStage", respString);
            }
        }
    })
    .catch(err => console.log('Fetch error: ', err));
}

function addUser(){
    var respString =    "<form method=\"post\" action=\"/adduserformsent\" name=\"addForm\">" +
                            "<div class=\"formMainGroup\">" +
                                "<div class=\"formLabel\"></div>" +
                                "<div class=\"formInput\">Add new user</div>" +
                                "<div class=\"formLabel\">" +
                                    "<label for=\"name\">Name: </label></div>" +
                                "<div class=\"formInput\">" +
                                    "<input name=\"name\" type=\"text\"></div>" +
                                "<div class=\"formLabel\">" +
                                    "<label for=\"surname\">Surname: </label></div>" +
                                "<div class=\"formInput\">" +
                                    "<input name=\"surname\" type=\"text\"></div>" +
                                "<div class=\"formLabel\"></div>" +
                                "<div class=\"formInput\">" +
                                    "<input type=\"submit\" value=\"Add new user\" onclick=\"return addFormValidate('addForm');\"></div></div>" +
                        "</form>";

    fillDiv("mainStage", respString);
}

function deleteUser(id) {
    fetch("http://localhost:8080/api/v1/user/" + id, {
        method: "DELETE",
        haders: {
            "Content-Type": "application/json"
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Network response was not ok: " + response.statusText);
        }
        window.location.href="/listusers";
        return response.json();
    })
    .then(data => {
      console.log("Request succeeded with JSON response", data);
    })
    .catch(err => console.log("Request error: ", err));
}

function listUserDetailForm(){
    var respString =    "<form method=\"post\" action=\"/listuserdetailformsent\" name=\"detailForm\">" +
                            "<div class=\"formMainGroup\">" +
                                "<div class=\"formLabel\"></div>" +
                                "<div class=\"formInput\">Show user detail</div>" +
                                "<div class=\"formLabel\">" +
                                    "<label for=\"name\">PersonID: </label></div>" +
                                "<div class=\"formInput\">" +
                                    "<input name=\"personid\" type=\"text\"></div>" +
                                "<div class=\"formLabel\"></div>" +
                                "<div class=\"formInput\">" +
                                    "<input type=\"submit\" value=\"Show user detail\" onclick=\"return detailFormValidate('detailForm');\"></div></div>" +
                        "</form>";

    fillDiv("mainStage", respString);
}

function updateUserForm(id, name, surname){
    var respString =    "<form method=\"post\" action=\"/updateuserformsent\" name=\"updateForm\">" +
                            "<div class=\"formMainGroup\">" +
                                "<div class=\"formLabel\"></div>" +
                                "<div class=\"formInput\">Update user</div>" +
                                "<div class=\"formLabel\">" +
                                    "<label for=\"name\">Name: </label></div>" +
                                "<div class=\"formInput\">" +
                                    "<input name=\"name\" type=\"text\" value=\"" + name + "\"></div>" +
                                "<div class=\"formLabel\">" +
                                    "<label for=\"surname\">Surname: </label></div>" +
                                "<div class=\"formInput\">" +
                                    "<input name=\"surname\" type=\"text\" value=\"" + surname + "\"></div>" +
                                "<div class=\"formLabel\"></div>" +
                                "<div class=\"formInput\">" +
                                    "<input name=\"id\" type=\"hidden\" value=\"" + id + "\">" +
                                    "<input type=\"submit\" value=\"Update user\" onclick=\"return addFormValidate('updateForm');\"></div></div>" +
                        "</form>";

    fillDiv("mainStage", respString);
}

function showLandPage(){
    var respString =    "<div class=\"creditsGroup\">" +
                            "REGISTERING SYSTEM<br />" +
                            "version 1.4<br />" +
                            "created by Bonoman, 2024<br />" +
                            "(bonoman@volny.cz)"
                        "</div>";

    fillDiv("mainStage", respString);
}

function getUrlParams(){
    var urlParam;
    var returnArray = new Array();
    const url = window.location.href;
    const urlObj = new URL(url);
    const params = new URLSearchParams(urlObj.search);
    var paramsArray = Array.from(params.entries());
    if(paramsArray[0] != undefined){ // getting chapter
        const [key, value] = paramsArray[0];
        returnArray[0] = key;
    }
    if(paramsArray[1] != undefined){ // getting user PersonID
        const [key, value] = paramsArray[1];
        returnArray[1] = value;
    }
    if(paramsArray[2] != undefined){ // getting user name
        const [key, value] = paramsArray[2];
        returnArray[2] = value;
    }
    if(paramsArray[3] != undefined){ // getting user surname
        const [key, value] = paramsArray[3];
        returnArray[3] = value;
    }
    return returnArray;
}

document.addEventListener("DOMContentLoaded", function(){
    var urlParams = getUrlParams();
    switch(urlParams[0]){
        case "listusers":           callGetAllUsers();
                                    break;
        case "adduser":             addUser();
                                    break;
        case "listuserdetailform":  listUserDetail(urlParams[1]);
                                    break;
        case "listuserdetail":      listUserDetailForm();
                                    break;
        case "deleteuser":          deleteUser(urlParams[1]);
                                    break;
        case "updateuserform":      updateUserForm(urlParams[1], urlParams[2], urlParams[3]);
                                    break;
        default:                    showLandPage();
                                    break;
    }
});

function confLink(message){
	var diag = window.confirm(message);
     if(diag){
		return true;
	}else{
        return false;
	}
}

function addFormValidate(formName){
    retVal = true;
    var rsltStr = "";
    var name = document.forms[formName]["name"].value;
    var surname = document.forms[formName]["surname"].value;
    if(name == "" || name == null){
        rsltStr += "Name must be filled out.\n";
        retVal = false;
    }
    if(surname == "" || surname == null){
        rsltStr += "Surname must be filled out.\n\n";
        retVal = false;
    }
    if(!retVal){
        alert(rsltStr);
    }
    return retVal;
}

function detailFormValidate(formName){
    retVal = true;
    var rsltStr = "";
    var personid = document.forms[formName]["personid"].value;
    if(personid == "" || personid == null){
        rsltStr += "PersonID must be filled out.\n";
        retVal = false;
    }
    if(!retVal){
        alert(rsltStr);
    }
    return retVal;
}