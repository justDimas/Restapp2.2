function ready() {
    let elems = document.querySelectorAll('.addBtn');
    let blocks = document.querySelectorAll('.addForm');

    elems.forEach(item => { item.style.display = 'flex'; });
    blocks.forEach(item => { item.style.display = 'none'; });
}

function showAdd(index){
    let blocks = document.querySelectorAll('.addForm');
    let elems = document.querySelectorAll('.addBtn');

    elems[index].style.display = (elems[index].style.display === 'none') ? 'flex' : 'none';
    blocks[index].style.display = (blocks[index].style.display === 'none') ? 'flex' : 'none';
}

document.addEventListener("DOMContentLoaded", ready);