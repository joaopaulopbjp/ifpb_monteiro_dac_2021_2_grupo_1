function alterarNomeEditora(id) {
    let txt = document.getElementById(`span-editora-nome-${id}`).innerHTML;
    let element = document.getElementById(`form-editora1-${id}`);

    element.innerHTML = `<input onblur='atualizarNomeEditora(${id})' value='${txt}' field='*{nome}' name='nome' id='input-editora1-${id}'/>`;
    document.getElementsByTagName('input')[2].focus();
} 

function alterarCidadeEditora(id) {
    let txt = document.getElementById(`span-editora-cidade-${id}`).innerHTML;
    let element = document.getElementById(`form-editora2-${id}`);

    element.innerHTML = `<input onblur='atualizarCidadeEditora(${id})' value='${txt}' field='*{cidade}' name='cidade' id='input-editora2-${id}'/>`;
    document.getElementsByTagName('input')[2].focus();
} 
  
function atualizarNomeEditora(id) {
    let txt = document.getElementById(`input-editora1-${id}`).value;
    let element = document.getElementById(id);

    element.innerHTML = `<span onclick='alterarNomeCategoria(${id})' id='span-editora-nome-${id}'> ${txt} </span>`;
}

function atualizarCidadeEditora(id) {
    let txt = document.getElementById(`input-editora2-${id}`).value;
    let element = document.getElementById(id);

    element.innerHTML = `<span onclick='alterarCidadeCategoria(${id})' id='span-editora-cidade-${id}'> ${txt} </span>`;
}