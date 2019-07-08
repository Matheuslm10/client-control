<template>
  <v-container>
    <v-layout row wrap>
      <h1 class="display-4">Novo cliente</h1>
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
      <h1 class="display-4">Clientes Cadastrados</h1>
    </v-layout>

    <v-data-table :headers="headers" :items="clientes" class="elevation-1">
      <template v-slot:items="cliente">
        <td>{{ cliente.item.id }}</td>
        <td>{{ cliente.item.nome }}</td>
        <td class="justify-center layout px-0">
          <v-icon small class="mr-2" @click="editItem(cliente.item)">edit</v-icon>
          <v-icon small @click="deleteItem(cliente.item)">delete</v-icon>
        </td>
      </template>
    </v-data-table>

    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-title>
          <span class="headline">Editar Cliente</span>
        </v-card-title>

        <v-card-text>
          <v-container grid-list-md>
            <v-layout wrap>
              <v-flex xs12 sm6 md4>
                <v-text-field v-model="editedItem.name" label="Nome"></v-text-field>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click="close">Cancel</v-btn>
          <v-btn color="blue darken-1" flat @click="save">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
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
        { text: "Nome", value: "nome", sortable: true },
        { text: "Ações", value: "name", sortable: false }
      ],
      dialog: false,
      editedIndex: -1,
      editedItem: {
        name: ""
      },
      defaultItem: {
        name: ""
      }
    };
  },
  methods: {
    async buscarTodos() {
      var response = await axios.get("http://localhost:8080/clientes");
      this.clientes = response.data;
    },
    async salvar() {
      var response = await axios.post(
        "http://localhost:8080/clientes",
        this.cliente
      );
      this.buscarTodos();
      this.limparFormulario();
    },
    limparFormulario() {
      this.cliente.nome = "";
    },
    editItem(item) {
      this.editedIndex = this.clientes.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    deleteItem(item) {
      const index = this.clientes.indexOf(item);
      confirm("Are you sure you want to delete this item?") &&
        this.desserts.splice(index, 1);
    },

    close() {
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },

    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.clientes[this.editedIndex], this.editedItem);
        alert(this.clientes[this.editedIndex].nome)
      } else {
        this.clientes.push(this.editedItem);
      }
      this.close();
    }
  },
  mounted() {
    this.buscarTodos();
  }
};
</script>

<style scoped>
h1 {
  font-size: 30pt !important;
}
</style>


