function login(){

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            
        }
    }

}

function register(){


}


function addNote(){


}

function editNote(){

}

function deleteNote(){

}

//bunu tablea uyarlaman lazım tabi!
function validateForm() {
    let x = document.forms["myForm"]["fname"].value;
    if (x == "") {
      alert("Name must be filled out");
      return false;
    }
  }


