/*function ajaxCall(url, method, data, successCallback, errorCallback) {
    var xhttp = new XMLHttpRequest();
    xhttp.open(method, url,true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === XMLHttpRequest.DONE) {
            if (xhttp.status === 200) {
                successCallback(xhttp.responseText);
            } else {
                errorCallback(xhttp.status);
            }
        }
    };
    xhttp.send(JSON.stringify(data));
}



/* Example usage
var apiUrl = 'https://example.com/api';
var requestData = { key: 'value' };

makeAjaxCall(apiUrl, 'POST', requestData, 
    function(response) {
        // Success callback
        console.log('Success:', response);
        // Handle response
    },
    function(status) {
        // Error callback
        console.error('Error:', status);
        // Handle error
    }
);*/

function login(){
    const userNameInp = document.getElementById("username");
    const passwordInp = document.getElementById("password");


    ajaxCall("/notepadUser/loginNotepad?user_name=" + userNameInp.value + "&pass=" + passwordInp.value, "GET", null, function(response) {
        // Success callback
        console.log('Success:', response);
       // Hide login form
        document.getElementById("login-form").style.display = "none";

        // Display user's notes
        document.getElementById("anasayfa-container").style.display = "block";
        //window.location.href = "notes.html";
   
    }, 
    function(status) {
        // Error callback
        console.error('Error:', status);
        alert("Error: " + status + ". Please try again.");
        // Handle error
    });
}

function register(){

    const userNameInp = document.getElementById("username");
    const passwordInp = document.getElementById("password");
    const emailInp = document.getElementById("email");
    const date = new Date().toISOString();
    let userCreateTime = date;
    let userUpdateTime= date;

    let data =  {
        userCreateTime: date,
        userUpdateTime: date,
        userName: userNameInp.value,
        password: passwordInp.value,
        email: emailInp.value
    };

    ajaxCall("/notepadUser/addNewUser", "POST", data, function(response) {
        // Success callback
        console.log('Success:', response);
        alert("Succesfully registered.");
      
   
    }, 
    function(status) {
        // Error callback
        console.error('Error:', status);
        alert("Error: " + status + ". Please try again.");
        // Handle error
    });

}


//bunu tablea uyarlaman lazım tabi!
function validateForm() {
    let x = document.forms["myForm"]["fname"].value;
    if (x == "") {
      alert("Name must be filled out");
      return false;
    }
  }

  
  function getNotes(categories) {
    fetch(`/category/getAllNotesOfCategory`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(note => {
            // Handle the retrieved note data here
            console.log('Retrieved note:', note);
        })
        .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
        });
}



function getCategoriesAllNotes(){}

function addNote(){}

function editNote(){}

function deleteNote(){}

function addCategory(){}


*/