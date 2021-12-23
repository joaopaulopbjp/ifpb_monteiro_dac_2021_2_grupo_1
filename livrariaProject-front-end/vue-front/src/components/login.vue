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
                    <label for="login" class="form-label">Email:</label>
                    <input type="text" required id="login" class="form-control" v-model="data.login">
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-login">
                    <label for="senha" class="form-label">Senha:</label>
                    <input type="password" required id="senha" class="form-control" v-model="data.senha">
                    <div class="invalid-feedback"></div>
                </div>
                
                    
                <button type="btn-login" value="Enviar" v-on:click="fazerLogin(data)">Entrar </button>
            </form>
            </div>
        </div>
    </main>
</template>


<script>
    import userService from '../service/user-service' 

export default {
    name: 'Login',
    data() {
        return {
            data:{
                login:"",
                senha:"",
            }
        }
        },
    methods:{
        fazerLogin: function(data){
            let resposta = userService.login(data);
            resposta.then((response) => {
                this.$store.state.usuario = response.data;

                this.$store.state.enderecos = response.data.enderecos;

                this.$router.push('/endereco');
            });

        }
    }
}
</script>

<style>
    @import "./stylesheet/login.css";
</style>
