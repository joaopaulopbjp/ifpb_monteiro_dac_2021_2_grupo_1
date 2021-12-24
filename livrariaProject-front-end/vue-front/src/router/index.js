import { createRouter, createWebHistory } from 'vue-router'
import enderecos from '../components/enderecos.vue'
import adicionarEndereco from '../components/adicionar-endereco.vue'
import login from '../components/login.vue'
import store from '@/store'

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

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', ''];
  const autoRequired = !publicPages.includes(to.path);
  const logado = store.state.usuario;
  console.log(logado);

  if(autoRequired && !logado) {
    next('/login');
  } else {
    next();
  }
})


export default router
