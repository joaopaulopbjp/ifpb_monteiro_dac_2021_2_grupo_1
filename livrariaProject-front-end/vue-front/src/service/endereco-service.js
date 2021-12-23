import {http} from './configuracoes'

export default{
	adicionarEndereco (endereco){
		console.log("Chegou no service " + endereco.numero)
		return http.post('/endereco/cadastrar-endereco', endereco)
	},
	listarEnderecos (){
		return http.get('/endereco/lista')
	},
	atualizarEndereco (endereco){
		return http.post('/endereco/atualizar-endereco/' + endereco.id, endereco)
	},
	removerEndereco (endereco){
		return http.delete('/endereco/remover-endereco/' + endereco.id, endereco)
	},

	lista:() => {return http.get("/endereco/lista")},
	adiciona:(endereco) => {return http.post("/endereco/cadastrar-endereco",endereco)}
}
