<template>
    <div id="app">
        <app-student-nav v-if="role==='student'"></app-student-nav>
        <app-lecturer-nav v-if="role === 'lecturer'"></app-lecturer-nav>
        <router-view/>
        <div id="loading" :class="{'show-loading': isLoading, 'hide-loading': !isLoading}">
            <h3 style="color: white">LOADING...</h3>
        </div>
    </div>
</template>

<script lang="ts">
    import NavBar from '@/components/global/NavBar.vue'
    import LecturerNav from '@/components/global/LecturerNav.vue'
    import {Component, Vue} from 'vue-property-decorator';

    @Component({
        components: {
            appStudentNav: NavBar,
            appLecturerNav: LecturerNav
        },
        computed: {
            loggedIn() {
                return this.$store.getters['login/LOGGED_IN'];
            },
            role() {
                return this.$store.getters['login/USER_ROLE'];
            }
        }
    })
    export default class App extends Vue {

        public get isLoading(): boolean {
            return this.$store.getters['LOADING'];
        }

        public created(): void {
        }
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
    #loading {
        position: fixed;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        background-color: rgba(0, 0, 0, 0.4);
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        z-index: 1071;
    }
    .show-loading{
        display: flex !important;
    }
    .hide-loading{
        display: none !important;
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
