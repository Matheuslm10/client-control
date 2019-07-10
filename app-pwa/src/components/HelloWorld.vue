<template>
  <v-container>
    <v-layout text-xs-center wrap>
      <v-flex xs12>
        <v-img
          :src="`https://user-images.githubusercontent.com/7110136/29002857-9e802f08-7ab4-11e7-9c31-604b5d0d0c19.png`"
          class="my-3"
          contain
          height="100"
        ></v-img>
      </v-flex>

      <v-flex mb-4>
        <h1 class="display-2 font-weight-bold mb-3">Testando Vuex</h1>
        <p class="subheading font-weight-regular">{{ user }} {{ outraCoisa }}</p>

        <users></users>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import Users from "./Users";
import { setTimeout } from "timers";
import { mapState, mapActions } from "vuex";

export default {
  name: "VueApp",
  mounted() {
    const payload = {
      name: "Matheus Lima Machado",
      email: "matheus@matheus.com",
      level: "admin",
      city: 'Campo Grande',
      state: 'MS'
    };

    setTimeout(() => {
      this.changeUser(payload);
    }, 3000);
  },
  components: {
    Users
  },
  methods: {
    ...mapActions(['changeUser'])
  },
  computed: {
    outraCoisa() {
      return "Mais uma info!";
    },
    ...mapState({
      user: state => {
        const { name, email } = state.user;
        return `O usuário logado é ${name} e possui o email ${email}.`;
      }
    })
  }
};
</script>

<style>
</style>
