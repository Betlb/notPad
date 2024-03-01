async function login() {

    const usernameInp = document.getElementById("username").value;
    const passwordInp = document.getElementById("password").value;

    const response = await fetch('/notepadUser/loginNotepad', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            userName:
            usernameInp,
            password: passwordInp
        }),
    });
    const data = await response.json();

    //debugger;
    if (data === null) {
        alert("Error password or username");
    } else {
        console.log(data);
        localStorage.setItem("USER",JSON.stringify(data)); //daha sonra useri kullanabilmek için.
        window.open("category.html", "_self");
    }

}

