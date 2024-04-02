    //const emailInp = document.getElementById("email").value;
    //const usernameInp = document.getElementById("username").value;
    //const passwordInp = document.getElementById("password").value;

    //const passwordErrorInp = document.getElementById("passwordError").value;
    //const emailErrorInp = document.getElementById("email").value;
    //const userNameErrorInp = document.getElementById("userName").value;
    
    //boyle uste variable tanımlama?biraz sccopelari karisik aynı da olsa her funct tekrar tanımladim temiz değil ama basit.

async function register(){
    const emailInp = document.getElementById("email").value;
    const usernameInp = document.getElementById("username").value;
    const password1Inp = document.getElementById("password1").value;

    const  response = await fetch('notepadUser/addNewUser',{
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            userName: usernameInp,
            password: password1Inp,
            email: emailInp  }),
    });


    const data = await response.json(); // Parse response as text

    if (data!=null) {
        console.log("User is successfully registered.");
        localStorage.setItem("USER",JSON.stringify(data)); //daha sonra useri kullanabilmek için.
        location.href = "category.html";
    } else {
        alert("Choose a different username this is taken or this email adress is already used.");
    }

}


 async function validateForm(){

    const password1Inp = document.getElementById("password1").value;
    const password2Inp = document.getElementById("password2").value;

    
    
    let passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[.!]).{10,}$/;

    if(password1Inp != password2Inp){
        alert("Passwords must be same!!");
        //passwordError2Inp.textContent = "Password must be same!!";
        //passwordError2Inp.style.display = "block";
        return false;
    }

    if(!passwordRegex.test(password1Inp)){
        alert('Password should be at least 10 characters and include at least one lowercase letter, one uppercase letter, one digit, and one of . or !');
        //passwordError1Inp.textContent = 'Password should be at least 10 characters and include at least one lowercase letter, one uppercase letter, one digit, and one of . or !';
        //passwordError1Inp.style.display = "block";
        return false;
    }

    
        
    //passwordError1Inp.textContent = "";
    //passwordError1Inp.style.display = "none";
    //passwordError2Inp.textContent = "";
    //passwordError2Inp.style.display = "none";

    await register();

}