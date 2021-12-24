import { createRouter, createWebHistory } from 'vue-router'
import enderecos from '../components/enderecos.vue'
import adicionarEndereco from '../components/adicionar-endereco.vue'
import login from '../components/login.vue'

const routes = [
  {
    path: '',
    redirect: '/login'
  },
  {
	path: '/endereco',
	component: enderecos
  },
  {
	path: '/add',
	component: adicionarEndereco
  },
  {
  path: '/login',
	component: login
  }
]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


export default router
