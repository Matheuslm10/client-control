<template>
    <div>
        <p>Email: {{ email }}</p>
        <p>localidade: {{ localidade }}</p>
        <MyButton></MyButton>
    </div>
</template>

<script>
    import MyButton from './MyButton'
    export default {
        components: {
            MyButton
        },
        computed: {
            email () {
                return this.$store.state.user.email
            },
            localidade () {
                return this.$store.getters.localidade
            }
        }
    }
</script>

