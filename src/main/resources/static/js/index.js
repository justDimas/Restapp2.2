let modal;
let urlParams;
let errorBlock;
let modalToggle;
let popupsToggle;

function toggle(block){
    if(block.classList.contains("hidden")){
        block.classList.remove("hidden");
        return true;
    }else{
        block.classList.add("hidden");
        return false;
    }
}

function toggleModal(){
    if(toggle(modal)){
        document.body.style.overflow = 'hidden';
    }else
        document.body.style.overflow = 'overlay';
}

function togglePopup(){
    let popup = event.target.parentNode.lastElementChild;
    toggle(popup);
}

function ready() {
    modalToggle = document.querySelectorAll(".toggle");
    modal = document.querySelector(".modal");
    modalToggle.forEach(item => item.addEventListener("click", toggleModal));

    popupsToggle = document.querySelectorAll(".popup-toggle");
    popupsToggle.forEach(item => item.addEventListener("click", togglePopup));
    document.querySelectorAll(".popup").forEach(item => item.classList.add("hidden"));

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