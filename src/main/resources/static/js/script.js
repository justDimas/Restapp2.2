function ready() {
    let forms = [document.getElementById('pizza-form'),
                 document.getElementById('salad-form'),
                 document.getElementById('drink-form'),];

    forms.forEach(item => { item.style.display = 'none'; });
}
document.addEventListener("DOMContentLoaded", ready);

function showAdd(btnId, formId){
    let btn = document.getElementById(btnId);
    let form = document.getElementById(formId);
    btn.style.display = (btn.style.display === 'none') ? 'flex' : 'none';
    form.style.display = (form.style.display === 'none') ? 'flex' : 'none';
}

