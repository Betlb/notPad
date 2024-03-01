let currentUser = null;

//currentUserı tutar.
function onLoad() {
    currentUser = JSON.parse(localStorage.getItem("USER"));
    document.getElementById('userNameUserLastName').innerHTML = currentUser.userName;
}

document.querySelector('a[href="#allNotes"]').addEventListener("click", function(event){
    event.preventDefault();//prevent the default link behavior events are objects that represent these occurrences
    allNotes();
})

async function allNotes(){

    try{
    const response = await fetch('/notepadUser/getUserAllNotes') 
    if(!response.ok){
        throw new Error('Failed to fetch ntoes');
    }
    console.log(response);
    const data = await response.json();
    console.log(data);
    length = data.length;
    let temp = "";
    temp+= "<table>";
    for(i=0;i<length;i++){
        temp+="<tr>";
        temp+="<td>"+data[i]+"</td><br>";
        temp+="</tr>";
    }
    temp+= "</table>";
    document.getElementById("notes-container").innerHTML = temp;
    }
    catch(error){
        console.error(error);
    }
}

function addNote(){}
function deleteNote(){}
function editNote(){}

