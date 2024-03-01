let currentUser = JSON.parse(localStorage.getItem("USER"));
//document.addEventListener("DOMContentLoaded", onLoad);

//currentUserı tutar.
function onLoad() {
    currentUser = JSON.parse(localStorage.getItem("USER"));
    document.getElementById('userNameUserLastName').innerHTML = currentUser.userName;
}

document.getElementById("allNotesLink").addEventListener("click",allNotes);
document.getElementById("passwordsLink").addEventListener("click",getPasswords());

async function allNotes(){
    try{
        const url = '/notepadUser/getUserAllNotes?id=' +currentUser.userId;
        const response = await fetch(url,{
            method: 'GET',
            headers: {
                'Content-type': 'application/json'
            }
        })
        if(!response.ok){
            throw new Error("Failed to fetch notes.");
        }
        console.log(response);
        const data = await response.json();
        let temp = getNotes(data);
        document.getElementById("notes-container").innerHTML = temp;

    }
    catch(error){
        console.error(error);
    }
   
}

function getNotes(data){
    console.log(data);
        let length = data.length;
        let temp = "";
        temp+= "<table>";
        for(i=0;i<length;i++){
            temp+="<tr>";
            temp +="<td>"+ data[i].title+ "</td>";
            temp+="<td>"+data[i].content+"</td><br>";
            temp+="</tr>";
        }
        temp+= "</table>";
        return temp;

}

async function getPasswords(){
    try{
        const url = '/category?id=' +currentUser.userId;
        const response = await fetch(url,{
            method: 'GET',
            headers: {
                'Content-type': 'application/json'
            }
        })
        if(!response.ok){
            throw new Error("Failed to fetch notes.");
        }
        console.log(response);
        const data = await response.json();
        let temp = getNotes(data);
        document.getElementById("notes-container").innerHTML = temp;

    }
    catch(error){
        console.error(error);
    }
   
}

function addNote(){}
function deleteNote(){}
function editNote(){}

