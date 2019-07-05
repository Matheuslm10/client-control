<template>
  <v-container>

    <v-layout row wrap>
      <h1>Novo cliente</h1>
    </v-layout>

    <v-layout row>
      <v-flex xs6 offset-xs3>
        <v-form>
          <v-text-field v-model="cliente.nome" label="Nome" required></v-text-field>

          <v-btn @click="salvar">Salvar</v-btn>
          <v-btn @click="cancelar">Cancelar</v-btn>
        </v-form>
      </v-flex>
    </v-layout>

    <v-layout row wrap>
      <h1>Clientes Cadastrados</h1>
    </v-layout>

    <v-data-table :headers="headers" :items="clientes" class="elevation-1">
      <template v-slot:items="cliente">
        <td>{{ cliente.item.id }}</td>
        <td>{{ cliente.item.nome }}</td>
      </template>
    </v-data-table>
  </v-container>
</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      cliente: {},
      clientes: [],
      headers: [
        { text: "ID", value: "id", sortable: true },
        { text: "Nome", value: "nome", sortable: true }
      ]
    };
  },
  methods: {
    async buscarTodos() {
      var response = await axios.get("http://localhost:8080/clientes");
      this.clientes = response.data;
    },
    async salvar() {},
    cancelar() {
      this.cliente.nome = "";
    }
  },
  mounted() {
    this.buscarTodos();
  }
};
</script>

