<template>
    <main class="login-main">
        <div class="login">
            <header>
            <div class="jumbotron mb-0">
                <h1 class="display-4">Entrar</h1>
            </div>
            </header>

            <div class="card">
            <form class="card-body" @submit.prevent>
                <div class="form-login">
                    <label for="email" class="form-label">Email:</label>
                    <input type="text" required id="email" class="form-control" v-model="login.email">
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-login">
                    <label for="senha" class="form-label">Senha:</label>
                    <input type="password" required id="senha" class="form-control" v-model="login.senha">
                    <div class="invalid-feedback"></div>
                </div>
                
                    
                <button class="btn-login" type="submit" value="Enviar" v-on:click="enviarLogin(login)">Entrar </button>
            </form>
            </div>
        </div>
    </main>
</template>


<script>
export default {
    name: 'Login',
    data() {
        return {
            login:{
                email:"",
                senha:"",
            }
        }
        },
    methods:{
        enviarNovoAutor: function(endereco){
        console.log("Endereco adicionado: "+endereco.nome);
        }
    }
}
</script>

<style>
    @import "./stylesheet/login.css";
</style>
