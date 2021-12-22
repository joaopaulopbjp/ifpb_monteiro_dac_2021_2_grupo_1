<template>
    <main class="container">
        <navegacao></navegacao>
        <div>
        <header>
            <div class="jumbotron mb-0">
            <h1 class="display-4">Lista de Enderecos</h1>
            </div>
        </header>
        <div class="listaLivros" v-for="endereco in enderecos" :key="endereco.nome">
            <div class="card">
            <div class="card-header">
            </div>
            <div class="card-body">
                <div> cep: {{endereco.cep}}</div>
                <div> estado: {{endereco.estado}}</div>
                <div> cidade: {{endereco.cidade}}</div>
                <div> bairro: {{endereco.bairro}}</div>
                <div> rua: {{endereco.rua}}</div>
                <div> numero: {{endereco.numero}}</div>
                <div> complemento: {{endereco.complemento}}</div>
            </div>
            <br>
            </div>
        </div>
        </div>
    </main>
</template>


<script>
import serviceEndereco from '../service/endereco-service'
import navegacao from './navegacao.vue'



export default {
    name: 'pedido',
    data() {
        return {
            enderecos: this.$store.state.enderecos
        }
	},
    created(){
        console.log("Created.")
        serviceEndereco.lista().then(
        dado => {console.log( dado.data),
        this.$store.state.enderecos = dado.data,
        this.enderecos = this.$store.state.enderecos})
    },
    components: {
        navegacao
  }
}
</script>