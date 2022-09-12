let modal;
let modalToggle;

function toggle(){
    modal = document.querySelector(".modal");
    if(modal.classList.contains("hidden")){
        modal.classList.remove("hidden");
        document.body.style.overflow = 'hidden';
    }else{
        modal.classList.add("hidden");
        document.body.style.overflow = 'visible';
    }
}

function ready() {
    modalToggle = document.querySelectorAll(".toggle");
    modal = document.querySelector(".modal");
    modalToggle.forEach(item => item.addEventListener("click", toggle));
    modal.classList.add("hidden");
}
document.addEventListener("DOMContentLoaded", ready);