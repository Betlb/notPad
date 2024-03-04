
// window.addEventListener("load", function(){

    let currentUser = JSON.parse(localStorage.getItem("USER"));

//document.addEventListener("DOMContentLoaded", onLoad);


    async function getAllCategories() {
        const url = '/category/getAllCategories';
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-type': 'application/json'
            }
        })
        if (!response.ok) {
            throw new Error("Failed to fetch getAllCategories.");
        }
        console.log(response);
        const data = await response.json();
        let temp = `<li id="categ">Categories</li>`;
        for (i = 0; i < data.length; i++) {
            temp += `<li> <a onclick="getUserNotesWithCategories(${data[i].categoryId})">${data[i].category_name}</a></li>`;
        }
        temp += `<li style="float:right"><a class="active" > <h5 id="userNameUserLastName"></h5></a></li>`;
        document.getElementById("anaMenu").innerHTML = temp;
    }


//currentUserı tutar.
     function onLoad() {
        currentUser = JSON.parse(localStorage.getItem("USER"));
        getAllCategories().then(function (){
            document.getElementById('userNameUserLastName').innerHTML = currentUser.userName;
        });


    }

   // document.getElementById("allNotesLink").addEventListener("click", getNotesWithUserIdAndCategoryId("allNotes"));


    async function getUserNotesWithCategories(categoryId) {
        try {
           // const url = '/note/getNotesWithUserIdAndCategoryId?userId=' + currentUser.userId + '&categoryId='+categoryId;
            const url = `/note/getUserNotesWithCategories?userId=${currentUser.userId}&categoryId=${categoryId}`;
            const response = await fetch(url, {
                method: 'GET',
                headers: {
                    'Content-type': 'application/json'
                }
            })
            if (!response.ok) {
                throw new Error("Failed to fetch notes.");
            }
            console.log(response);
            const data = await response.json();
            let temp = getNotes(data);
            document.getElementById("notes-container").innerHTML = temp;

        } catch (error) {
            console.error(error);
        }

    }

    function getNotes(data) {
        console.log(data);
        let length = data.length;
        let temp = "";
        temp += "<table>";
        for (i = 0; i < length; i++) {
            temp += "<tr>";
            temp += "<td>" + data[i].title + "</td>";
            temp += "<td>" + data[i].content + "</td><br>";
            temp += "</tr>";
        }
        temp += "</table>";
        return temp;

    }


    function addNote() {
    }

    function deleteNote() {
    }

    function editNote() {
    }

