let forms;
let imageInputs;
let addToggles;
let updateToggles;
let resetButtons;
let images;

function toggle(){
    let trigForm = document.getElementById(event.target.dataset.toggleId);
    let isHidden = trigForm.classList.contains("hidden");
    forms.forEach(item => {
        if(!item.classList.contains("hidden")) item.classList.add("hidden");
    });
    if(isHidden) trigForm.classList.remove("hidden");
}

function toggleAndSet(){
    toggle();
    let form = document.getElementById(event.target.dataset.toggleId);
    if(event.target.dataset.toggleId == "pizza-update-form"){
        let pizza;
        let pizzaId = event.target.getAttribute("value");
        for(item of pizzas){
            if(item.pizzaId == pizzaId){
                pizza = item;
                break;
            }
        }
        if(pizza === undefined) return;
        let id = document.getElementById("pizza-id-box");
        let name = document.getElementById("pizza-name-update-input");
        let price =  document.getElementById("pizza-price-update-input");
        let vegetarian = document.getElementById("pizza-vegetarian-update-input");
        let spicy = document.getElementById("pizza-spicy-update-input");
        let image = document.getElementById("pizza-image-update-form");

        id.value = pizzaId;
        name.value = pizza.good.goodName;
        price.value = pizza.good.goodPrice;
        vegetarian.checked = pizza.vegetarian;
        spicy.checked = pizza.spicy;
        image.src = "images/" + pizza.pizzaImg;
    }
    if(event.target.dataset.toggleId == "salad-update-form"){
        let salad;
        let saladId = event.target.getAttribute("value");
        for(item of salads){
            if(item.saladId == saladId){
                salad = item;
                break;
            }
        }
        if(salad === undefined) return;
        let id = document.getElementById("salad-id-box");
        let name = document.getElementById("salad-name-update-input");
        let price =  document.getElementById("salad-price-update-input");
        let vegetarian = document.getElementById("salad-vegetarian-update-input");
        let warm = document.getElementById("salad-warm-update-input");
        let image = document.getElementById("salad-image-update-form");

        id.value = saladId;
        name.value = salad.good.goodName;
        price.value = salad.good.goodPrice;
        vegetarian.checked = salad.vegetarian;
        warm.checked = salad.warm;
        image.src = "images/" + salad.saladImg;
    }
    if(event.target.dataset.toggleId == "drink-update-form"){
        let drink;
        let drinkId = event.target.getAttribute("value");
        for(item of drinks){
            if(item.drinkId == drinkId){
                drink = item;
                break;
            }
        }
        if(drink === undefined) return;
        let id = document.getElementById("drink-id-box");
        let name = document.getElementById("drink-name-update-input");
        let price =  document.getElementById("drink-price-update-input");
        let alc = document.getElementById("drink-alcohol-update-input");
        let warm = document.getElementById("drink-warm-update-input");
        let gazed = document.getElementById("drink-gazed-update-input");
        let caffeine = document.getElementById("drink-caffeine-update-input");
        let image = document.getElementById("drink-image-update-form");

        id.value = drinkId;
        name.value = drink.good.goodName;
        price.value = drink.good.goodPrice;
        alc.checked = drink.alcohol;
        warm.checked = drink.warm;
        gazed.checked = drink.gazed;
        caffeine.checked = drink.hasCaffeine;
        image.src = "images/" + drink.drinkImg;
    }
}

function changeImage(){
    let img = document.getElementById(event.target.dataset.toggleId);
    img.src = (!event.target.files.length) ? "images/noimg.jpg" : "images/" + event.target.files[0].name;
}

function scaleImage(){
    let isScaled = event.target.classList.contains("scale");
    images.forEach(item => { if(item.classList.contains("scale"))
        item.classList.remove("scale");
    });
    if(!isScaled) event.target.classList.add("scale");
}

function resetForm(){
    let form = document.getElementById(event.target.dataset.formId);
    form.reset();
    let imageInput = document.querySelector("#" + event.target.dataset.formId + " input[type=\"file\"]")
    imageInput.dispatchEvent(new Event("change"));
}

function ready() {
    forms = document.querySelectorAll(".form");
    imageInputs = document.querySelectorAll(".image-input");
    addToggles = document.querySelectorAll(".add-toggle");
    updateToggles = document.querySelectorAll(".update-toggle");
    resetButtons = document.querySelectorAll(".reset");
    images = document.querySelectorAll(".good-image");

    resetButtons.forEach(item => item.addEventListener("click", resetForm ));
    imageInputs.forEach(item => item.addEventListener("change", changeImage));
    addToggles.forEach(item => item.addEventListener("click", toggle));
    images.forEach(item => item.addEventListener("click", scaleImage));
    updateToggles.forEach(item => item.addEventListener("click", toggleAndSet));
    forms.forEach(item => item.classList.add("hidden"));
}
document.addEventListener("DOMContentLoaded", ready);