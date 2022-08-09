function ready() {
    let fileInputs = [document.getElementById("pizza-image-add-input"),
                      document.getElementById("pizza-image-update-input"),
                      document.getElementById("salad-image-add-input"),
                      document.getElementById("salad-image-update-input"),
                      document.getElementById("drink-image-add-input"),
                      document.getElementById("drink-image-update-input")];
    fileInputs.forEach(item => item.addEventListener('change',()=>{
        let img = document.getElementById(event.target.dataset.toggleId);
        img.src = (!event.target.files.length) ? "images/noimg.jpg" : "images/" + event.target.files[0].name;
    }));

    let forms = [document.getElementById("pizza-add-form"),
                 document.getElementById("pizza-update-form"),
                 document.getElementById("salad-add-form"),
                 document.getElementById("salad-update-form"),
                 document.getElementById("drink-add-form"),
                 document.getElementById("drink-update-form")];
    forms.forEach(item => item.classList.add("hidden"));
}
document.addEventListener("DOMContentLoaded", ready);

let t = new class{
    hiddenBlock = null;
    toggle(target){
        if(target.classList.contains("hidden")){
            if(this.hiddenBlock != null){
                this.hiddenBlock.classList.add("hidden");
            }
            target.classList.remove("hidden");
            this.hiddenBlock = target;
            return true;
        }else{
            this.hiddenBlock = null;
            target.classList.add("hidden");
            return false;
        }
    }
}

document.onclick = function(event){
    switch (event.target.id) {
        case "pizza-add-toggle":
        case "salad-add-toggle":
        case "drink-add-toggle": {
            let form = document.getElementById(event.target.dataset.toggleId);
            t.toggle(form);
            break;
        }
        case "pizza-update-toggle": {
            let form = document.getElementById(event.target.dataset.toggleId);
            let name = document.getElementById("pizza-name-update-input");
            let price =  document.getElementById("pizza-price-update-input");
            let vegetarian = document.getElementById("pizza-vegetarian-update-input");
            let spicy = document.getElementById("pizza-spicy-update-input");
            let image = document.getElementById("pizza-image-update-form");
            let pizza;
            for(item of pizzas){
                if(item.pizzaId == event.target.getAttribute("value")){
                    pizza = item;
                    break;
                }
            }
            if(pizza === undefined) break;
            if(t.toggle(form)){
                name.value = pizza.good.goodName;
                price.value = pizza.good.goodPrice;
                vegetarian.checked = pizza.vegetarian;
                spicy.checked = pizza.spicy;
                image.src = "images/" + pizza.pizzaImg;
            }else{
                name.value = "";
                price.value = "";
                vegetarian.checked = false;
                spicy.checked = false;
                image.src = "";
            }
            break;
        }
        case "salad-update-toggle": {
            let form = document.getElementById(event.target.dataset.toggleId);
            let name = document.getElementById("salad-name-update-input");
            let price =  document.getElementById("salad-price-update-input");
            let vegetarian = document.getElementById("salad-vegetarian-update-input");
            let warm = document.getElementById("salad-warm-update-input");
            let image = document.getElementById("salad-image-update-form");
            let salad;
            for(item of salads){
                if(item.saladId == event.target.getAttribute("value")){
                    salad = item;
                    break;
                }
            }
            if(salad === undefined) break;
            if(t.toggle(form)){
                name.value = salad.good.goodName;
                price.value = salad.good.goodPrice;
                vegetarian.checked = salad.vegetarian;
                warm.checked = salad.warm;
                image.src = "images/" + salad.saladImg;
            }else{
                name.value = "";
                price.value = "";
                vegetarian.checked = false;
                warm.checked = false;
                image.src = "";
            }
            break;
        }
        case "drink-update-toggle": {
            let form = document.getElementById(event.target.dataset.toggleId);
            let name = document.getElementById("drink-name-update-input");
            let price =  document.getElementById("drink-price-update-input");
            let alc = document.getElementById("drink-alcohol-update-input");
            let warm = document.getElementById("drink-warm-update-input");
            let gazed = document.getElementById("drink-gazed-update-input");
            let caffeine = document.getElementById("drink-caffeine-update-input");
            let image = document.getElementById("drink-image-update-form");
            let drink;
            for(item of drinks){
                if(item.drinkId == event.target.getAttribute("value")){
                    drink = item;
                    break;
                }
            }
            if(drink === undefined) break;
            if(t.toggle(form)){
                name.value = drink.good.goodName;
                price.value = drink.good.goodPrice;
                alc.checked = drink.alcohol;
                warm.checked = drink.warm;
                gazed.checked = drink.gazed;
                caffeine.checked = drink.hasCaffeine;
                image.src = "images/" + drink.drinkImg;
            }else{
                name.value = "";
                price.value = "";
                alc.checked = false;
                warm.checked = false;
                gazed.checked = false;
                caffeine.checked = false;
                image.src = "";
            }
            break;
        }
    }
}