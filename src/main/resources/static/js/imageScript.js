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
    imgInput.value = 'good-noimg.jpg';
    target.src = '/images/good-noimg.jpg';
    alert('некорректный путь к файлу');
}

function ready(){
    let image = document.querySelector('.cell-image');
    let imageInput = document.querySelector('#input-image');
    let imageSelector = document.querySelector('#input-selector');

        console.log(imageSelector);
        console.log(image);
        console.log(imageInput);

    imageSelector.addEventListener('change', ()=>changeImage(image, imageInput));
    image.addEventListener('error', ()=>imageError(imageInput));
}
document.addEventListener('DOMContentLoaded', ready);