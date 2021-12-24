import {http} from './configuracoes'
import store from '@/store'

export default{
	adicionarEndereco (endereco){
		return http.post('/endereco/cadastrar-endereco', endereco)
	},
	listarEnderecos (){
		return http.get('/endereco/lista')
	},
	atualizarEndereco (endereco){
		return http.post('/endereco/atualizar-endereco/' + endereco.id, endereco)
	},
	removerEndereco (endereco){
		return http.delete('/endereco/remover-endereco/' + endereco.id)
	},

	lista:() => {return http.get("/endereco/lista", {headers: {Authorization: 'Bearer ' + store.state.usuario.token}})}
}
