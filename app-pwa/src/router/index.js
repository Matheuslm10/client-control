import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Clientes from '@/components/Clientes'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path:'/',
            name: 'HelloWorld',
            component: HelloWorld
        },
        {
            path:'/clientes',
            name: 'Clientes',
            component: Clientes
        }
    ]
})