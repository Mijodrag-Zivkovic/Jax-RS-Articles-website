import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Categories from "../views/Categories";
import CategoryEdit from "@/views/CategoryEdit";
import CategoryAdd from "@/views/CategoryAdd";
import Articles from "@/views/Articles";
import ArticlesByCategory from "@/views/ArticlesByCategory";
import ArticleAdd from "@/views/ArticleAdd";
import ArticleSingle from "@/views/ArticleSingle";
import ArticleEdit from "@/views/ArticleEdit";
import ArticlesByTag from "@/views/ArticlesByTag";
import Users from "@/views/Users";
import UserEdit from "@/views/UserEdit";
import UserAdd from "@/views/UserAdd";
import UserSingle from "@/views/UserSingle";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/categories',
    name: 'Categories',
    component: Categories
  },
  {
    path: '/categories/category',
    name: 'CategoryEdit',
    component: CategoryEdit,
    meta: {
      authRequired: true,
    }
  },
  {
    path: '/categories',
    name: 'CategoryAdd',
    component: CategoryAdd,
    meta: {
      authRequired: true,
    }
  },
  {
    path: '/articles',
    name: 'Articles',
    component: Articles
  },
  {
    path: '/articles/addArticle',
    name: 'ArticleAdd',
    component: ArticleAdd,
    meta: {
      authRequired: true,
    }
  },
  {
    path: '/articles/article',
    name: 'ArticleEdit',
    component: ArticleEdit,
    meta: {
      authRequired: true,
    }
  },
  {
    path: '/articles/articleSingle',
    name: 'ArticleSingle',
    component: ArticleSingle
  },
  {
    path: '/articles/categoryId/:id',
    name: 'ArticlesByCategory',
    component: ArticlesByCategory
  },
  {
    path: '/articles/tagId/:id',
    name: 'ArticlesByTag',
    component: ArticlesByTag
  },
  {
    path: '/users',
    name: 'Users',
    component: Users,
    meta: {
      authRequired: true,
    }
  },
  {
    path: '/users/user/edit',
    name: 'UserEdit',
    component: UserEdit,
    meta: {
      authRequired: true,
    }
  },
  {
    path: '/users/user/add',
    name: 'UserAdd',
    component: UserAdd,
    meta: {
      authRequired: true,
    }
  },
  {
    path: '/users/userSingle',
    name: 'UserSingle',
    component: UserSingle,
    meta: {
      authRequired: true,
    }
  },
  {
    path: '/subjects',
    name: 'Subjects',
    meta: {
      authRequired: true,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Subjects.vue')
  },
  {
    path: '/subjects/:id',
    name: 'single-subject',
    meta: {
      authRequired: true,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/SingleSubject.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.authRequired) {
    const jwt = localStorage.getItem('jwt');

    if (!jwt) {
      next({name: 'Login'});
      return;
    }

    const payload = JSON.parse(atob(jwt.split('.')[1]));
    const expDate = new Date(payload.exp * 1000);
    const status = payload.status;
    if (expDate < new Date() || status!=="active") {
      next({name: 'Login'});
      return;
    }
  }

  next();
});

export default router
