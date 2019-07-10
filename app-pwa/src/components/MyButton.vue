<template>
    <div>
        <v-btn color="info" :disabled="!hasUser" @click="mudarDados">Mudar dados do usuário</v-btn>
    </div>
</template>

<script>
    import { mapActions } from 'vuex';
    export default {
        methods: {
            mudarDados () {
                const payload = {
                    name: 'Joaquim da Silva',
                    email: 'email@email.com',
                    level: 'usuario-raso',
                    city: 'São Paulo',
                    state: 'SP'
                }
                this.changeUser(payload)
            },
            ...mapActions(['changeUser'])
        },
        computed: {
            hasUser() {
                return this.$store.state.user.name !== ''
            }
        }
    }
</script>

