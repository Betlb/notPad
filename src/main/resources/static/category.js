
// window.addEventListener("load", function(){

    let previousCategoryElement = null;
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
        //console.log(response);
        const data = await response.json();
        let temp = `<ul class="categoryList">`;
        for (i = 0; i < data.length; i++) {
            temp += `<li id="${data[i].category_name}"> <a onclick="getUserNotesWithCategories(${data[i].categoryId}), activated2(this)">${data[i].category_name}</a></li>`;
        }
        temp += `</ul>`;
     
        document.getElementById("categoryMenu").innerHTML = temp;
    }
/*
    async function addCategoriesWindow(){

        window.open("", "", "width=300,height=200");

        const url = '/category/addCategory';
        const response = await fetch(url,{
            method: 'GET',
            headers: {
                'Content-type' : 'application/json'
            }

    })
    if(!response.ok){
        throw new Error("Failed to fetch addCategories.")

    }
    const data = await response.json();
    document.getElementById("anaMenu").innerHTML += `<li id="${data.category_name}"> <a onclick="getUserNotesWithCategories(${data.categoryId}), activated2(this)">${data.category_name}</a></li>`;
  
    }
*/
    function activated2(currentcategoryElement){
        currentcategoryElement.style.backgroundColor = "#49aa04";
        if(previousCategoryElement!==null){
            previousCategoryElement.style.backgroundColor = "#333";
        }
        previousCategoryElement = currentcategoryElement;
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
            console.log(data);
           {
                let temp = getNotes(data);
                document.getElementById("notes-container").innerHTML = temp;
            }
            
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

