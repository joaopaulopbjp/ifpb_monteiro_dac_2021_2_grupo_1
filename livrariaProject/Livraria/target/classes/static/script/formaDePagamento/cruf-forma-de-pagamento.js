function alterarNomeFormaDePagamento(id) {
    let txt = document.getElementById(`span-formaDePagamento-${id}`).innerHTML;
    let element = document.getElementById(`form-formaDePagamento-${id}`);

    element.innerHTML = `<input onblur='atualizar(${id})' value='${txt}' field='*{nome}' name='nome' id='input-formaDePagamento-${id}'/>`;
    document.getElementsByTagName('input')[2].focus();
} 
  
function atualizar(id) {
    let txt = document.getElementById(`input-formaDePagamento-${id}`).value;
    let element = document.getElementById(id);

    element.innerHTML = `<span onclick='alterarNomeFormaDePagamento(${id})' id='span-formaDePagamento-${id}'> ${txt} </span>`;
}