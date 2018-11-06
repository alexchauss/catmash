import Vue from 'vue'
import Router from 'vue-router'
import Form from '@/components/Form'
import Cats from '@/components/Cats'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Form',
      component: Form
    },
    {
        path: '/cats',
        name: 'Cats',
        component: Cats
      }
  ]
})
