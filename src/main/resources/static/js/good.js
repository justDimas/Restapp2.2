function ready() {
    let fileInputs = [document.getElementById("drink-image-add-input"),
                      document.getElementById("drink-image-update-input")];
    fileInputs.forEach(item => item.addEventListener('change',()=>{
        let img = document.getElementById(event.target.dataset.toggleId);
        img.src = (!event.target.files.length) ? "images/noimg.jpg" : "images/" + event.target.files[0].name;
    }));

    let forms = [document.getElementById("add-pizza-form"),
                 document.getElementById("update-pizza-form"),
                 document.getElementById("add-salad-form"),
                 document.getElementById("update-salad-form"),
                 document.getElementById("add-drink-form"),
                 document.getElementById("update-drink-form")];
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
        case "show-add-pizza-button":
        case "show-add-salad-button":
        case "show-add-drink-button": {
            let form = document.getElementById(event.target.dataset.toggleId);
            t.toggle(form);
            break;
        }
        case "show-update-pizza-button": {break;}
        case "show-update-salad-button": {break;}
        case "show-update-drink-button": {
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