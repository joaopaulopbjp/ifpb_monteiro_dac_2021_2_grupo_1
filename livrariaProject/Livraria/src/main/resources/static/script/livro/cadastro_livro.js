var listaAutores = [];
var autoresLivro = [];

function parseJSON(list) {
    let json = [];
    let element;
    let item = [];
    for (let index = 0; index < list.length; index++) {
        element = list[index].replace("Autor", "");
        item = element.split("=");
        element = "{\"id\":" + item[1].slice(0,-6) + ", \"nome\":" + "\""+item[2].slice(0, -1)+"\"}";
        json.push(JSON.parse(element));
    }
    return json;
}

function searchAutor() {
    mostrarSearchAutor();
    render(search.value);
}

function mostrarSearchAutor() {
    let div = document.getElementById("result-search-autor");
    div.classList.remove("remove");
}

function hiddenSearchAutor() {
    let div = document.getElementById("result-search-autor");
    div.classList.add("remove");
}

const cleanupWord = word => {
    return word.trim().toLowerCase().replace(/( )+/g, " ");
}

const filterData = word => {
    return listaAutores.filter(item => item.nome.toLowerCase().includes(word));
}

const render = (word = "") => {
    document.getElementById("ul-lista-autores").innerHTML;
    word = cleanupWord(word);
    const filtered = filterData(word);
    addLista(filtered);
}

function addLista(list) {
    let lista = "";
    list.forEach(element => {
        console.log(JSON.stringify(element));
        lista = lista 
        +   `<li>
                <label class='checkbox'>
                    <input id='input-autor-${element.id}' type='checkbox' value='${element.id}' field='*{autores}' name='autores' onchange='alterAutor(${JSON.stringify(element)})'>
                    <span th:text='${element.nome}'>${element.nome}</span>
                </label>
            </li>`;
    });
    document.getElementById("ul-lista-autores").innerHTML = lista;

    autoresLivro.forEach(element => {
        let input = document.getElementById(`input-autor-${element.id}`);
        if(input != null) {
            input.setAttribute("checked", true);
        }
    });
}


function alterAutor(autor) {
    console.log(autor);
    let input = document.getElementById(`input-autor-${autor.id}`);
    if(input.checked) {
        autoresLivro.push(autor);
    } else {
        for (let index = 0; index < autoresLivro.length; index++) {
            if (autoresLivro[index].id === autor.id) { 
                autoresLivro.splice(index, 1);
            }
       
        }
    }
}