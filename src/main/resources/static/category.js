
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
            temp += `<li id="${allCategories[i].categoryId}"> <a onclick="getUserNotesWithCategories(${allCategories[i].categoryId}),activated2(this,${allCategories[i].categoryId})">${allCategories[i].category_name}</a></li>`;
        }
        temp += `</ul>`;
        
        document.getElementById("categoryMenu").innerHTML = temp;
    }

 
    
    async function addNewCategory(){
        
        const categoryName = prompt('Enter the category name: ');
        if(categoryName!==null&&categoryName.trim()!==''){
        try{
            const url = `/category/addCategory?categoryName=${categoryName}&userId=${currentUser.userId}`;
            

            const response = await fetch(url,{
                method: 'POST',
                headers: {
                    'Content-type' : 'application/json'
                },
            })

            if(!response.ok){
                if (response.status === 400) {//bu cok doru gelmedi.
                    alert(`${categoryName} is already exist.`);
                } else {
                    console.error('Error:', response.status);
                }
            }
            else{
                const data = await response.json();
                categoryList(data);
            }
        }
        catch(error){
            console.error(error);
             }
            
        }
        else{
            alert('You did not enter category name!');
        }
       
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
                if(temp === null){
                    document.getElementById("notes-container").innerHTML = "";
                }
                else{
                    document.getElementById("notes-container").innerHTML = temp;}
            }
            
            } catch (error) {
                console.error(error);//"categoryId is null"
            }

    }

    function getNotes(data) {
        //console.log(data);
        let length = data.length;
        let temp = null;
        if(data.length === 0){
            return temp;
        }
        else{
            temp = "";
        }
        temp += "<table>";
       
            for (i = 0; i < length; i++) {
                temp += "<tr>";
                temp += `<td><input type="checkbox" name="note" value="${data[i].noteId}"></td>`;
                temp += `<td id='title_${data[i].noteId}'>${data[i].title}</td>`;
                temp += `<td id='content_${data[i].noteId}'>${data[i].content}</td>`;
                temp += "</tr>";
            }
    
        temp += "</table>";
        return temp;

    }


    /*async function addNewNote() {
        try{
            const newWindow = window.open('addNewNote.html','_blank','width=300 height=200');
          
            newWindow.onload = async function(){

                const noteTitleInp = await document.getElementById("noteTitle").value;
                const noteContentInp = await document.getElementById("noteContent").value;

                const response = await fetch('/note/createNote',{
                    method: 'POST',
                    headers: {
                        'Content-type': 'application/json'
                    },
                    body: JSON.stringify({
                        title: noteTitleInp,
                        content: noteContentInp,
                        notepadUserId : currentUser.userId,
                        categoryId : currentcategoryId
                    }),

                });

                if(!response.ok){
                    alert("Error: " + response.statusText);
                }
            
                const data= await response.json();
                localStorage.setItem("Note",JSON.stringify(data));
                window.location.href = "category.html";
        };
           
        }catch(error){
            alert("failed to fetch /note/createNote");
        }

    }
*/
async function openPrompt(){
    if(currentcategoryId==null){
        alert("Please firstly choose category!");
        return;
    }

    const modal =document.getElementById("notePrompt");
    const span = document.getElementsByClassName("close")[0];
    const submitBtn = document.getElementById("submitNote"); 

   
    modal.style.display = "block";
    
    span.onclick = function() {
        modal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == modal) {
          modal.style.display = "none";
        }
    }

    //title and content is filled check.

        submitBtn.onclick = async function(){
            const noteTitleInp = await document.getElementById("noteTitle").value;
            const noteContentInp = await document.getElementById("noteContent").value;

            if(noteTitleInp.trim() === '' || noteContentInp.trim() === ''){
                alert("Please fill in both title and content!");
            }
            else{
                await addNewNote(noteTitleInp,noteContentInp);
                modal.style.display = "none";
            }
        };

    
}

async function addNewNote(title,content){

    try{
                const response = await fetch('/note/createNote',{
                    method: 'POST',
                    headers: {
                        'Content-type': 'application/json'
                    },
                    body: JSON.stringify({
                        title: title,
                        content: content,
                        notepadUserId : currentUser.userId,
                        categoryId : currentcategoryId
                    }),

                });

                if(!response.ok){
                    alert("Error: " + response.statusText);
                }
            
                const data= await response.json();
                console.log(data);
                getUserNotesWithCategories(currentcategoryId);
                

    }catch(error){
        alert("failed to fetch /note/createNote");
      }


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

    async function editNote(note){
        try{
                //degistir yeni notla değişenleri
                const url = `/note/editNote?note_id=${note.noteId}`;
                const response = await fetch(url,{
                    method: "PUT",
                    headers: {
                        'Content-type':'application/json'
                    },
                    body: JSON.stringify({
                        categoryId: currentcategoryId,
                        title: note.title,
                        content : note.content
                    }),
                });
                if(!response.ok){
                    throw new Error("Failed to edit note.");
                }
            
                getUserNotesWithCategories(currentcategoryId);
        }
        catch(error){
            alert("failed to fetch editNote.");
        }
    }

    async function openPromptEdit(){

        const selectedNotes = Array.from(document.querySelectorAll('input[name=note]:checked')).map(checkbox => checkbox.value);
        if(selectedNotes.length > 1){
            alert("You have to choose exactly one note which will be edit!");
        }
        else if(selectedNotes.length === 0){
            alert("You did not choose any note. Please choose.");
        }
        else {
            let noteId = selectedNotes[0];
                const modal =document.getElementById("notePrompt");
                const span = document.getElementsByClassName("close")[0];
                const submitBtn = document.getElementById("submitNote"); 

                document.getElementById("noteTitle").value = document.getElementById(`title_${noteId}`).textContent;
                document.getElementById("noteContent").value = document.getElementById(`content_${noteId}`).textContent;
               
                modal.style.display = "block";
                
                span.onclick = function() {
                    modal.style.display = "none";
                }
            
                window.onclick = function(event) {
                    if (event.target == modal) {
                    modal.style.display = "none";
                    }
                }

                //title and content is filled check.
           
                submitBtn.onclick = async function(){
                    const noteTitleInp = document.getElementById("noteTitle").value;
                    const noteContentInp = document.getElementById("noteContent").value;

                    if(noteTitleInp.trim() === '' || noteContentInp.trim() === ''){
                        alert("Please fill gaps!");
                        
                    }
                    else{
                        const note = {
                            noteId : noteId,
                            title : noteTitleInp,
                            content : noteContentInp
                        }
                        await editNote(note);
                        modal.style.display = "none";   
                    }
                };
              
        }
        
    }

    function exit(){
        if(confirm("Are you sure you want to exit?")){
            localStorage.clear();
            window.location.href = "index.html";
        }

    }

