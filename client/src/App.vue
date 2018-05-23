<template>
    <div id="app">
        <app-nav v-if="loggedIn"></app-nav>
        <router-view/>
    </div>
</template>

<script lang="ts">
    import NavBar from '@/components/global/NavBar.vue'
    import {Component, Vue} from 'vue-property-decorator';

    @Component({
        components: {
            appNav: NavBar
        },
        computed: {
            loggedIn() {
                return this.$store.getters['login/LOGGED_IN'];
            }
        }
    })
    export default class App extends Vue {
    }
</script>

<style>
    html,body {
        margin-top: 30px;
    }
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
    }

    #nav {
        padding: 30px;
    }

    #nav a {
        font-weight: bold;
        color: #2c3e50;
    }

    #nav a.router-link-exact-active {
        color: #42b983;
    }

    .navigation-button {
        text-decoration: none !important;
        position: absolute;
        top: 5px;
        right: 10px;
        max-width: 70px;
    }
    .hide-overflow {
        white-space: nowrap !important;
        overflow: hidden;
        text-overflow:ellipsis;
    }
</style>
