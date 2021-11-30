function alterarNomeCategoria(id) {
    let txt = document.getElementById(`span-categoria-${id}`).innerHTML;
    let element = document.getElementById(`form-categoria-${id}`);

    element.innerHTML = `<input onblur='atualizar(${id})' value='${txt}' field='*{nome}' name='nome' id='input-categoria-${id}'/>`;
    document.getElementsByTagName('input')[2].focus();
} 
  
function atualizar(id) {
    let txt = document.getElementById(`input-categoria-${id}`).value;
    let element = document.getElementById(id);

    element.innerHTML = `<span onclick='alterarNomeCategoria(${id})' id='span-categoria-${id}'> ${txt} </span>`;
}