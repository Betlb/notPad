
// window.addEventListener("load", function(){

    let previousCategoryElement = null;
    let currentUser = JSON.parse(localStorage.getItem("USER"));
    let currentcategoryId = null;
    let allCategories = [];

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
        //console.log(data);
        for(let i= 0;i<data.length;i++){
            //console.log(data[i])
            categoryList(data[i]);
        }
        
    }

    function categoryList(category){
        allCategories.push(category);
        let temp = `<ul class="categoryList">`;
        for (i = 0; i < allCategories.length; i++) {
            console.log(this);
            temp += `<li id="${allCategories[i].categoryId}"> <a onclick="getUserNotesWithCategories(${allCategories[i].categoryId}),activated2(this,${allCategories[i].categoryId})">${allCategories[i].category_name}</a></li>`;
        }
        temp += `</ul>`;
     
        document.getElementById("categoryMenu").innerHTML = temp;
    }
    
    async function addNewCategory(){
        
        const categoryName = prompt('Enter the category name: ');
        if(categoryName!==null&&categoryName.trim()!==''){

            const url = `/category/addCategory?categoryName=${categoryName}&userId=${currentUser.userId}`;
            

            const response = await fetch(url,{
                method: 'POST',
                headers: {
                    'Content-type' : 'application/json'
                },
            })
            if(!response.ok){
                throw new Error("Failed to fetch addNewCategory.")

            }
            const data = await response.json();
            categoryList(data);
        }
       /* else{
            alert('You did not enter category name!');
         }
        */
       /* getAllCategories().then(function (){
            document.getElementById("categoryMenu").innerHTML += `<li id="${data.categoryId}"> <a onclick="getUserNotesWithCategories(${data.categoryId}), activated2(this)">${data.category_name}</a></li>`;
        });

        location.replace('/category.html');*/

    }

    async function deleteCategory(){

        try{
            const url = `/category/deleteCategory?userId=${currentUser.userId}&categoryId=${currentcategoryId}`;
            const response = await fetch(url,{
                method: 'DELETE',
                headers: {
                    'Content-type': 'application/json'
                },
                body:JSON.stringify(currentcategoryId)
            });

            if(!response.ok){
                throw new Error('Failed to delete category');
            }
            else{
                allCategories=[];
                getAllCategories();
                getUserNotesWithCategories(currentcategoryId);
                currentcategoryId=null;
            }

        }catch(error){
            console.error(error);
        }

    }

    //this was opened with new window.
    /*
    async function addCategoriesWindow(){

      window.open('addCategory.html','_blank','width=300 height=200');

        
    }*/

    function activated2(currentcategoryElement,categId){
        currentcategoryElement.style.backgroundColor = "#49aa04";
        currentcategoryId = categId//.value idsini getirdi categlerin!!!
        console.log(currentcategoryId);
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
            //console.log(response);
            const data = await response.json();
            //console.log(data);
           {
                let temp = getNotes(data);
                document.getElementById("notes-container").innerHTML = temp;
            }
            
            } catch (error) {
                console.error(error + "categoryId is null");
            }

    }

    function getNotes(data) {
        //console.log(data);
        let length = data.length;
        let temp = "";
        temp += "<table>";
       
            for (i = 0; i < length; i++) {
                temp += "<tr>";
                temp += `<td><input type="checkbox" name="note" value="${data[i].noteId}"></td>`;
                temp += "<td>" + data[i].title + "</td>";
                temp += "<td>" + data[i].content + "</td><br>";
                temp += "</tr>";
            }
    
        temp += "</table>";
        return temp;

    }


    function addNote() {
    }

    async function deleteSelectedNotes() {
        try{
            const selectedNotes = Array.from(document.querySelectorAll('input[name=note]:checked')).map(checkbox => checkbox.value);
            if(selectedNotes.length===0){
                alert("You did not choose any notes.");
                return;
            }
            for(let i = 0;i< selectedNotes.length;i++){
                const url = `/note/deleteNote?id=${selectedNotes[i]}`;
                const response = await fetch(url,{
                    method: 'DELETE',
                    headers: {
                        'Content-type':'application/json'
                    },
                    body: JSON.stringify(selectedNotes[i])
                    
                });
                if(!response.ok){
                    throw new Error("Failed to delete notes.");
                }
            }
             getUserNotesWithCategories(currentcategoryId);

            }catch(error){
                console.error(error);
            
        }
    }

    function editNote() {
    }

