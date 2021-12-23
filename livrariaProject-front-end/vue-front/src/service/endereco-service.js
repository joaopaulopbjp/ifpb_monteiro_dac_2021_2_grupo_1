import {http, httpBanco} from './configuracoes'

// const cors = require('cors');
// httpBanco.use(cors());

export default{
	adicionarEndereco (endereco){
		console.log("Chegou no service " + endereco.numero)
		return httpBanco.post('/cadastrar-endereco', endereco)
	},
	listarEnderecos (){
		return httpBanco.get('/lista')
	},
	atualizarEndereco (endereco){
		return httpBanco.post('/atualizar-endereco/' + endereco.id, endereco)
	},
	removerEndereco (endereco){
		return httpBanco.delete('/remover-endereco/' + endereco.id, endereco)
	},

	lista:() => {return http.get("lista-enderecos")},
	adiciona:(endereco) => {return http.post("/cadastrar-endereco",endereco)}
}
