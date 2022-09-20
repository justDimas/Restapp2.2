let modal;
let modalToggle;
let urlParams;
let errorBlock;

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

    urlParams = new URLSearchParams(window.location.search);
    errorBlock = document.querySelector(".modal-window *:last-child");
    if(urlParams.has("error")){
        document.querySelector(".modal-window *:last-child");
        errorBlock.style.color = "red";
    }else{
        modal.classList.add("hidden");
        errorBlock.style.display = "none";
    }
}
document.addEventListener("DOMContentLoaded", ready);