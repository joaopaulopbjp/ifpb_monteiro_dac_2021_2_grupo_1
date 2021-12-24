import {http} from './configuracoes'

export default{
	async login (user){
		return await http.post('/seguranca/login', user);
	},
    
    getEnderecos() {
        console.log("Chegou no login ")
        return http.get('/endereco/todos').then(response => console.log(response.data[0].cidade))
    }

}