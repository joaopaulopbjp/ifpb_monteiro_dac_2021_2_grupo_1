<template>
    <main class="container">
        <div>
            <header>
            <div class="jumbotron mb-0">
                <h1 class="display-4">Adicionar Endereços</h1>
            </div>
            </header>

            <div class="card">
            <form class="card-body" @submit.prevent>
                <div class="form-group">
                    <label for="cep" class="form-label">CEP:</label>
                    <input type="text" required id="cep" class="form-control" v-model="endereco.cep">
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <label for="estado" class="form-label">Estado:</label>
                    <input type="estado" required id="estado" class="form-control" v-model="endereco.estado">
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <label for="bairro" class="form-label">Bairro:</label>
                    <input type="bairro" required id="bairro" class="form-control" v-model="endereco.bairro">
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <label for="rua" class="form-label">Rua:</label>
                    <input type="rua" required id="rua" class="form-control" v-model="endereco.rua">
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <label for="numero" class="form-label">Número:</label>
                    <input type="numero" required id="numero" class="form-control" v-model="endereco.numero">
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <label for="complemento" class="form-label">Complemento:</label>
                    <input type="complemento" required id="complemento" class="form-control" v-model="endereco.complemento">
                    <div class="invalid-feedback"></div>
                </div>
                
                    
                <button type="submit" value="Enviar" v-on:click="enviarNovoAutor(autor)">Enviar</button>
            </form>
            </div>
        </div>
    </main>
</template>


<script>
export default {
    name: 'AdicionarEndereco',
    data() {
        return {
            endereco:{
            cep:"",
            estado:"",
            cidade:"",
            bairro:"",
            rua:"",
            numero:"",
            complemento:"",

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
