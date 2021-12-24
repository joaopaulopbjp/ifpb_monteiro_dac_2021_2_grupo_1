<template>
<div class="nav">
  <h3>Menu de navegação:</h3>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid"> 
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link class="nav-link" to="/endereco"> Lista de Endereços </router-link>
            </li>
            <li class="nav-item">
              <router-link v-on:click="adicionarEndereco()" class="nav-link" to="/add"> Adicionar Endereço </router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
</div>
</template>

<script>
export default {
  name: 'navegacao',
  data() {
      return {};
  },
  methods:{
    adicionarEndereco : function() {
      this.$store.state.endereco = {
            cep:"",
            estado:"",
            cidade:"",
            bairro:"",
            rua:"",
            numero:"",
            complemento:""
            }
    }
  }
}
</script>
<style >
  @import "./stylesheet/navegacao.css"
</style>