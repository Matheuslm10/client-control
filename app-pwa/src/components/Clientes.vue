<template>
  <v-container>
    <v-layout row wrap>
      <h1 class="display-4">Clientes Cadastrados</h1>
    </v-layout>

    <v-divider class="mx-2" inset vertical></v-divider>
    <v-spacer></v-spacer>

    <v-data-table :headers="headers" :items="clientes" class="elevation-1" color="blue">
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
      <v-spacer></v-spacer>
      <template v-slot:activator="{ on }">
        <v-btn color="blue" dark class="mb-2" v-on="on">Novo cliente</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">{{ formTitle }}</span>
        </v-card-title>

        <v-card-text>
          <v-container grid-list-md>
            <v-layout wrap>
              <v-flex xs12 sm6 md4>
                <v-text-field v-model="editedItem.nome" label="Nome" color="blue"></v-text-field>
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
  $_veeValidate: {
    validator: "new"
  },
  data() {
    return {
      cliente: {},
      clientes: [],
      headers: [
        { text: "ID", value: "id", sortable: true },
        { text: "Nome", value: "nome", sortable: true },
        { text: "Ações", value: "ação", sortable: false }
      ],
      dialog: false,
      editedIndex: -1,
      editedItem: {
        nome: ""
      },
      defaultItem: {
        nome: ""
      }
    };
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Novo Cliente" : "Editar Cliente";
    }
  },
  methods: {
    async buscarTodos() {
      var response = await axios.get("http://localhost:8080/clientes");
      this.clientes = response.data;
    },
    async salvar() {
      await axios.post("http://localhost:8080/clientes", this.editedItem);
      this.buscarTodos();
    },
    async alterar() {
      await axios.put("http://localhost:8080/clientes", this.editedItem);
      this.buscarTodos();
    },
    async deletar(index) {
      await axios.delete("http://localhost:8080/clientes/" + index);
      this.buscarTodos();
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
      confirm("tem certeza que quer deletar este cliente?") &&
        this.deletar(this.clientes[index].id) &&
        this.clientes.splice(index, 1);
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
        this.alterar();
      } else {
        this.clientes.push(this.editedItem);
        this.salvar();
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


