import { createRouter, createWebHistory } from 'vue-router'
import enderecos from '../components/enderecos.vue'
import adicionarEndereco from '../components/adicionar-endereco.vue'


const routes = [
  {
	path: '/',
	component: enderecos
  },
  {
	path: '/add',
	component: adicionarEndereco
  }
]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


export default router
