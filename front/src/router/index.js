import Vue from 'vue'
import Router from 'vue-router'
import Cats from '@/components/Cats'
import Scores from '@/components/Scores'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Cats',
      component: Cats
    },
    {
    	path: '/scores',
    	name: 'Scores',
    	component: Scores
    }
  ]
})
