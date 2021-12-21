import {http} from './configuracoes'


export default{
	lista:() => {return http.get("enderecos/lista")},
	adiciona:(endereco) => {return http.post("enderecos/adicionarEndereco",endereco)}
}
