import {http} from './configuracoes'

// const cors = require('cors');
// http.use(cors());


export default{
	async login (user){
		console.log("Chegou no login " + user.login)

		http.post('/seguranca/login', user).then(response => console.log(response.data.email))
	},
    
    getEnderecos() {
        console.log("Chegou no login ")
        return http.get('/endereco/todos').then(response => console.log(response.data[0].cidade))
    }

}