function ready() {
    let forms = [document.getElementById("add-pizza-form"),
                 document.getElementById("update-pizza-form"),
                 document.getElementById("add-salad-form"),
                 document.getElementById("update-salad-form"),
                 document.getElementById("add-drink-form"),
                 document.getElementById("update-drink-form")];

    forms.forEach(item => { item.classList.add("hidden") });
}
document.addEventListener("DOMContentLoaded", ready);

function toggle(target){
    if(target.classList.contains("hidden")){
        target.classList.remove("hidden");
    }else{
        target.classList.add("hidden")
    }
}

document.onclick = function(event){
    if(event.target.id == "show-add-pizza-button" ||
       event.target.id == "show-add-salad-button" ||
       event.target.id == "show-add-drink-button")
    {
        let form = document.getElementById(event.target.dataset.toggleId);
        toggle(form);
    }
};
