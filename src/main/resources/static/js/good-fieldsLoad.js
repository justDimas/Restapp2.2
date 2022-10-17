function ready() {
    let categories = document.querySelectorAll("input[id^='input-category-']");
    if(typeof good === 'undefined'){
        categories[0].checked = 'checked';
    }else{
        let description = document.querySelector('#textarea-description');
        let properties = document.querySelectorAll("input[id^='input-property-']");
        let ingredients = document.querySelectorAll("input[id^='input-ingredient-']");
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
}
document.addEventListener('DOMContentLoaded', ready);