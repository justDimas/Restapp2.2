function changeImage(img, imgInput){
    let validExpr = /^image\//;
    if(!event.target.files.length){
        alert('изображение не выбрано');
        return;
    }
    if(!validExpr.test(event.target.files[0].type)){
        alert('некорректный тип файла');
        return;
    }
    imgInput.value = event.target.files[0].name;
    img.src = '/images/' + event.target.files[0].name;
}

function imageError(imgInput){
    alert('некорректный путь к файлу');
    event.target.src = '/images/good-noimg.jpg';
    imgInput.value = 'good-noimg.jpg';
}

function ready() {
    let image = document.querySelector('.good-image');
    let imageInput = document.querySelector('#input-image');
    let imageSelector = document.querySelector('#selector-image');
    let categories = document.querySelectorAll("input[id^='input-category-']");
    if(typeof good === 'undefined'){
        categories[0].checked = 'checked';
        image.src = '/images/good-noimg.jpg';
        imageInput.value = 'good-noimg.jpg';
    }else{
        let description = document.querySelector('#textarea-description');
        let properties = document.querySelectorAll("input[id^='input-property-']");
        let ingredients = document.querySelectorAll("input[id^='input-ingredient-']");

        image.src = '/images/'+good.goodImage;
        imageInput.value = good.goodImage;
        description.value = good.goodDescription;
        categories.forEach((category)=>{
            if(category.value==good.goodCategory.categoryId){
                category.checked='checked';
            }
        });
        properties.forEach((property)=>{
            good.goodProperties.forEach((goodProperty)=>{
                if(property.value == goodProperty.propertyId){
                    property.checked='checked';
                }
            });
        });
        ingredients.forEach((ingredient)=>{
            good.goodIngredients.forEach((goodIngredient)=>{
                if(ingredient.value == goodIngredient.ingredientId){
                    ingredient.checked='checked';
                }
            });
        });
    }

    imageSelector.addEventListener('change', ()=>changeImage(image, imageInput));
    image.addEventListener('error', ()=>imageError(imageInput));
}
document.addEventListener('DOMContentLoaded', ready);