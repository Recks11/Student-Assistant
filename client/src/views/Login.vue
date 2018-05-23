<template>
    <div class="parent">
        <div class="login-form">
            <div class="actual-form">
                <div class="form-body">
                    <a>
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512">
                            <path class="desktop-logo"
                                  d="M528 0H48C21.5 0 0 21.5 0 48v320c0 26.5 21.5 48 48 48h192l-16 48h-72c-13.3 0-24 10.7-24 24s10.7 24 24 24h272c13.3 0 24-10.7 24-24s-10.7-24-24-24h-72l-16-48h192c26.5 0 48-21.5 48-48V48c0-26.5-21.5-48-48-48zm-16 352H64V64h448v288z"></path>
                        </svg>
                    </a>
                    <form class="form-signin" method="post">
                        <div class="input-group">
                            <i class="fa fa-user"></i>
                            <input id="username" type="text" class="underline-blue" name="username"
                                   autocomplete="username" placeholder="Username" v-model="user.username"/>
                        </div>
                        <div class="input-group">
                            <i class="fa fa-lock"></i>
                            <input id="password" type="password" class="underline-blue" name="password"
                                   autocomplete="password" placeholder="Password" v-model="user.password"/>
                        </div>
                        <div class="submit-btn">
                            <button type="submit" value="login" class="underline-blue" @click.prevent="loginPerson()">
                                Login
                            </button>
                            <router-link tag="a" to="/register" class="navigation-button">Register</router-link>
                        </div>
                    </form>
                    <div v-if="error || loggedIn" class="alert alert-error">
                        <div id="status" class="text-center" style="padding-top:5px; text-align: center">
                            {{ message }}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import {mapGetters} from 'vuex';

    @Component({
        computed: {
            ...mapGetters({
                loggedIn: 'login/LOGGED_IN',
            }),
        },
    })

    export default class Login extends Vue {
        public message: string = '';
        public error: boolean = false;
        public user = {
            username: '',
            password: '',
        };

        public loginPerson(): void {
            this.$store.dispatch('login/LOG_IN_ACTION', this.user)
                .catch((reason: string) => {
                    this.error = true;
                    this.message = reason;
                })
                .then(() => {
                    this.$router.push({path: '/'+ this.$store.state.login.role})
                });
        }

        public created(): void {
            if ( this.$store.getters[ 'login/LOGGED_IN' ] === true ) {
                this.$router.push({path: '/' + this.$store.state.login.role});
            }
        }
    }
</script>

<style scoped>
    * {
        color: white;
    }

    html, body {
        height: 100%;
        margin: 0;
    }

    input[type="text"], input[type="password"] {
        color: black;
        font-family: 'Montserrat', sans-serif;
    }

    a {
        width: 10vw !important;
        min-width: 90px;
    }

    .desktop-logo {
        fill: white;
    }

    .parent {
        background-color: #030717;
    }

    .actual-form {
        padding: 10px;
        font-family: 'Montserrat', sans-serif;
    }

    /*
        Flex
    */
    .parent {
        display: flex;
        height: 100vh;
        width: 100vw;
        align-items: center;
        justify-content: center;
        position: fixed;
        top: 0;
        left: 0;
        z-index: 1001;
    }

    .form-body {
        width: 350px;
        height: 450px;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .input-group {
        display: flex;
        flex-direction: row;
    }

    /*
        End Flex
    */
    .login-form {
        background-color: #030717;
    }

    /* form Area*/
    .form-body > p {
        margin-top: 2px;
    }

    img {
        width: 175px;
        margin-top: 40px;
        margin-bottom: 40px;
    }

    .form-signin {
        width: 100%;
    }

    .input-group {
        width: 100%;
        margin-top: 30px;
    }

    .input-group > .fa {
        font-size: 20px;
        margin-top: 10px;
        margin-right: 5px;
    }

    input[type="text"], input[type="password"] {
        padding: 5px 5px 5px 10px;
        line-height: 2;
        color: white;
        border: 0 solid blue;
        border-bottom: 2px solid white;
        background-color: transparent;
        width: 100%;
        font-size: 15px;
    }

    /* end form area */
    /* Submit Button Area*/
    .submit-btn {
        text-align: center;
        margin-top: 50px;
        margin-bottom: 30px;
    }

    button, a {
        padding: 5px;
        color: white;
        border: 0 solid blue;
        border-bottom: 2px solid white;
        background-color: transparent;
        font-size: 15px;
    }

    button:hover, button:focus, a:hover, a:focus {
        color: #4155ff;
        border-bottom: 2px solid #4155ff;
    }

    /* end Submit Button Area*/
    input:focus {
        outline: 0;
    }

    @media screen and (max-width: 400px) {
        .form-body, .login-form {
            width: 100%;
        }

    }
</style>
