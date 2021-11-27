
function alterarNomeAutor(id) {
    let txt = document.getElementById(`span-autor-${id}`).innerHTML;
    let element = document.getElementById(`form-autor-${id}`);

    element.innerHTML = `<input onblur='atualizar(${id})' value='${txt}' field='*{nome}' name='nome' id='input-autor-${id}'/>`;
    document.getElementsByTagName('input')[2].focus();
} 
  
function atualizar(id) {
    let txt = document.getElementById(`input-autor-${id}`).value;
    let element = document.getElementById(id);

    element.innerHTML = `<span onclick='alterarNomeAutor(${id})' id='span-autor-${id}'> ${txt} </span>`;
}