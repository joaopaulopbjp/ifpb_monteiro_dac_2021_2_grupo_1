import {http} from './configuracoes'
import store from '@/store'

export default{
	adicionarEndereco (endereco){
		return http.post('/endereco/cadastrar-endereco', endereco, {headers: {Authorization: 'Bearer ' + store.state.usuario.token}})
	},
	atualizarEndereco (endereco){
		return http.post('/endereco/atualizar-endereco/' + endereco.id, endereco, {headers: {Authorization: 'Bearer ' + store.state.usuario.token}})
	},
	removerEndereco (endereco){
		return http.delete('/endereco/remover-endereco/' + endereco.id, {headers: {Authorization: 'Bearer ' + store.state.usuario.token}})
	},

	lista:() => {return http.get("/endereco/lista", {headers: {Authorization: 'Bearer ' + store.state.usuario.token}})}
}
