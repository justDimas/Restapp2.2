function toggle(forms){
    let trigForm = document.getElementById(event.target.dataset.toggleId);
    let isHidden = trigForm.classList.contains("hidden");
    forms.forEach(item => {
        if(!item.classList.contains("hidden")) item.classList.add("hidden");
    });
    if(isHidden) trigForm.classList.remove("hidden");
}

function toggleAndSet(forms){
    toggle(forms);
    let form = document.getElementById(event.target.dataset.toggleId);
    if(event.target.dataset.toggleId == "pizza-update-form"){
        let pizza;
        for(item of pizzas){
            if(item.pizzaId == event.target.getAttribute("value")){
                pizza = item;
                break;
            }
        }
        if(pizza === undefined) return;
        let name = document.getElementById("pizza-name-update-input");
        let price =  document.getElementById("pizza-price-update-input");
        let vegetarian = document.getElementById("pizza-vegetarian-update-input");
        let spicy = document.getElementById("pizza-spicy-update-input");
        let image = document.getElementById("pizza-image-update-form");

        name.value = pizza.good.goodName;
        price.value = pizza.good.goodPrice;
        vegetarian.checked = pizza.vegetarian;
        spicy.checked = pizza.spicy;
        image.src = "images/" + pizza.pizzaImg;
    }
    if(event.target.dataset.toggleId == "salad-update-form"){
        let salad;
        for(item of salads){
            if(item.saladId == event.target.getAttribute("value")){
                salad = item;
                break;
            }
        }
        if(salad === undefined) return;
        let name = document.getElementById("salad-name-update-input");
        let price =  document.getElementById("salad-price-update-input");
        let vegetarian = document.getElementById("salad-vegetarian-update-input");
        let warm = document.getElementById("salad-warm-update-input");
        let image = document.getElementById("salad-image-update-form");

        name.value = salad.good.goodName;
        price.value = salad.good.goodPrice;
        vegetarian.checked = salad.vegetarian;
        warm.checked = salad.warm;
        image.src = "images/" + salad.saladImg;
    }
    if(event.target.dataset.toggleId == "drink-update-form"){
        let drink;
        for(item of drinks){
            if(item.drinkId == event.target.getAttribute("value")){
                drink = item;
                break;
            }
        }
        if(drink === undefined) return;
        let name = document.getElementById("drink-name-update-input");
        let price =  document.getElementById("drink-price-update-input");
        let alc = document.getElementById("drink-alcohol-update-input");
        let warm = document.getElementById("drink-warm-update-input");
        let gazed = document.getElementById("drink-gazed-update-input");
        let caffeine = document.getElementById("drink-caffeine-update-input");
        let image = document.getElementById("drink-image-update-form");

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

function scaleImage(images){
    let isScaled = event.target.classList.contains("scale");
    Array.from(images).forEach(item => { if(item.classList.contains("scale"))
        item.classList.remove("scale");
    });
    if(!isScaled) event.target.classList.add("scale");
}

function ready() {
    let forms = [document.getElementById("pizza-add-form"),
                 document.getElementById("pizza-update-form"),
                 document.getElementById("salad-add-form"),
                 document.getElementById("salad-update-form"),
                 document.getElementById("drink-add-form"),
                 document.getElementById("drink-update-form")];
    let imageInputs = [document.getElementById("pizza-image-add-input"),
                      document.getElementById("pizza-image-update-input"),
                      document.getElementById("salad-image-add-input"),
                      document.getElementById("salad-image-update-input"),
                      document.getElementById("drink-image-add-input"),
                      document.getElementById("drink-image-update-input")];
    let addToggles = document.getElementsByClassName("add-toggle");
    let updateToggles = document.getElementsByClassName("update-toggle");
    let submitButtons = document.getElementsByClassName("submit");
    let resetButtons = document.getElementsByClassName("reset");
    let images = document.getElementsByClassName("good-image");

    imageInputs.forEach(item => item.addEventListener("change", changeImage));
    //TODO
    /*Array.from(submitButtons).forEach(item => item.addEventListener("click", ()=>{ toggle(forms); }));
    Array.from(resetButtons).forEach(item => item.addEventListener("click", ()=>{ toggle(forms); }));*/
    Array.from(addToggles).forEach(item => item.addEventListener("click", ()=>{ toggle(forms); }));
    Array.from(images).forEach(item => item.addEventListener("click", ()=>{ scaleImage(images); }));
    Array.from(updateToggles).forEach(item => item.addEventListener("click", ()=>{ toggleAndSet(forms); }));
    forms.forEach(item => item.classList.add("hidden"));
}
document.addEventListener("DOMContentLoaded", ready);