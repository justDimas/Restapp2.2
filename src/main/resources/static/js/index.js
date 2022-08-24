let modal;
let modalToggle;
let registration;

function toggle(){
    modal = document.querySelector(".modal");
    if(modal.classList.contains("hidden"))
        modal.classList.remove("hidden");
    else
        modal.classList.add("hidden");
}

function ready() {
    modalToggle = document.querySelectorAll(".toggle");
    modal = document.querySelector(".modal");
    registration = document.querySelector(".registration");
    if(user!=null){
        modalToggle.forEach(item => item.innerHTML = user.username);
        registration.innerHTML = "Выход из аккаунта";
        registration.addEventListener("click", ()=>{
            window.location.href = "/logout";
        });
    }else{
        registration.innerHTML = "Регистрация";
        modalToggle.forEach(item => item.addEventListener("click", toggle));
        registration.addEventListener("click", ()=>{
            window.location.href = "/registration";
        });
    }
    modal.classList.add("hidden");
}
document.addEventListener("DOMContentLoaded", ready);