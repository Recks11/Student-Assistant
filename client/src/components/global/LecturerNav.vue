<template>
    <div class="NavBar">
        <div class="container">
            <nav class="navbar navbar-expand-md fixed-top navbar-dark bg-dark justify-content-between"
                 style="padding: 2px 20px;">
                <router-link class="navbar-brand" :to="'/'+role.toLowerCase()">
                    {{role}} HOME
                    <span class="status mobile"
                          :class="{'online': lecturer.inOffice===true, 'offline': lecturer.inOffice===false}"></span>
                </router-link>
                <button class="navbar-toggler" :class="{collapsed: !showMobile}" type="button" data-toggle="collapse"
                        data-target="#navbarContent"
                        aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation"
                        @click.prevent="showMobile = !showMobile; showLogout = false">
                    <span>
                        <svg width="16.707589" height="16.171875" viewBox="1.289062 -35.239955 16.707589 16.171875"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill="white"
                                  d="M17.996652 -27.421875C17.996652 -27.276786 17.940848 -27.148438 17.829241 -27.036830L10.027902
                                  -19.235491C9.916295 -19.123884 9.787946 -19.068080 9.642857 -19.068080C9.497768
                                  -19.068080 9.369420 -19.123884 9.257812 -19.235491L1.456473 -27.036830C1.344866 -27.148438 1.289062 -27.276786 1.289062
                                  -27.421875C1.289062 -27.566964 1.344866 -27.695312 1.456473
                                  -27.806920L2.293527 -28.643973C2.405134 -28.755580 2.533482 -28.811384 2.678571 -28.811384C2.823661 -28.811384 2.952009
                                  -28.755580 3.063616 -28.643973L9.642857 -22.064732L16.222098 -28.643973C16.333705 -28.755580 16.462054 -28.811384 16.607143
                                  -28.811384C16.752232 -28.811384 16.880580 -28.755580 16.992188 -28.643973L17.829241 -27.806920C17.940848 -27.695312 17.996652
                                  -27.566964 17.996652 -27.421875ZM17.996652 -33.850446C17.996652 -33.705357 17.940848 -33.577009 17.829241 -33.465402L10.027902
                                  -25.664062C9.916295 -25.552455 9.787946 -25.496652 9.642857 -25.496652C9.497768 -25.496652 9.369420 -25.552455 9.257812 -25.664062L1.456473
                                  -33.465402C1.344866 -33.577009 1.289062 -33.705357 1.289062 -33.850446C1.289062 -33.995536 1.344866 -34.123884 1.456473 -34.235491L2.293527
                                  -35.072545C2.405134 -35.184152 2.533482 -35.239955 2.678571 -35.239955C2.823661 -35.239955 2.952009 -35.184152 3.063616 -35.072545L9.642857
                                  -28.493304L16.222098 -35.072545C16.333705 -35.184152 16.462054 -35.239955 16.607143 -35.239955C16.752232 -35.239955 16.880580 -35.184152 16.992188
                                  -35.072545L17.829241 -34.235491C17.940848 -34.123884 17.996652 -33.995536 17.996652 -33.850446ZM17.996652 -33.850446">
                            </path>
                        </svg>
                    </span>
                    <span class="notification-number">10</span>
                </button>

                <div class="collapse navbar-collapse" :class="{show: showMobile}" id="navbarContent">
                    <ul class="navbar-nav mr-auto">
                        <li v-if="isLoggedIn" class="nav-item dropdown" :class="{show: showCourse}">
                            <a class="nav-link dropdown-toggle" id="menuLinks" data-toggle="dropdown"
                               @click="showCourse = !showCourse;showLogout = false; showStatus = false">
                                Courses
                            </a>
                            <div v-if="isLoggedIn && role.toLowerCase() === 'lecturer'" class="dropdown-menu"
                                 :class="{show: showCourse}" aria-labelledby="navbarDropdownMenuLink">
                                <router-link class="dropdown-item" tag="a" to="/lecturer/courses/add"> Add</router-link>
                                <router-link class="dropdown-item" tag="a" to="/lecturer/courses/view"> view
                                </router-link>
                            </div>
                        </li>
                    </ul>

                    <div>
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item dropdown"
                                @click="showStatus = !showStatus;showLogout = false; showCourse = false">
                                <a class="nav-link status"
                                   :class="{'online': lecturer.inOffice===true, 'offline': lecturer.inOffice===false}"
                                   id="MenuLink" @click="toggleOffice()">
                                    {{lecturer.inOffice === true ? 'In Office' : 'Not Available'}}
                                </a>
                            </li>
                            <li v-if="isLoggedIn" class="nav-item dropdown" :class="{show: showLogout}">
                                <a class="nav-link dropdown-toggle"
                                   id="logoutLinks" data-toggle="dropdown" @click="showLogout = !showLogout">
                                    {{lecturer.firstName}} {{lecturer.lastName}}
                                </a>
                                <div class="dropdown-menu" :class="{show: showLogout}" aria-labelledby="MenuLink">
                                    <router-link v-if="role.toLowerCase() === 'lecturer'"
                                                 class="dropdown-item" to="/lecturer/account">My Account
                                    </router-link>
                                    <a class="dropdown-item" @click="logout">logout</a>
                                </div>
                            </li>

                            <li class="nav-item">
                                <a class="notification-icon">
                                    <svg class="notification-svg" xmlns="http://www.w3.org/2000/svg" height="40"
                                         viewBox="0 0 48 48">
                                        <path fill="none" d="M0 0h48v48H0z"></path>
                                        <path fill="white"
                                              d="M40 8H8c-2.21 0-3.98 1.79-3.98 4L4 36c0 2.21 1.79 4 4 4h32c2.21 0 4-1.79 4-4V12c0-2.21-1.79-4-4-4zm0 28H8V16l16 10 16-10v20zM24 22L8 12h32L24 22z"></path>
                                    </svg>
                                    <span>10</span> <!-- NUMBER OF NOTIFICATIONS -->
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import {mapGetters} from 'vuex';

    @Component({
        computed: {
            ...mapGetters({
                isLoggedIn: 'login/LOGGED_IN',
                lecturer: 'ACTIVE_LECTURER',
            }),
        },
    })
    export default class LecturerNav extends Vue {
        public showCourse: boolean = false;
        public showLogout: boolean = false;
        public showMobile: boolean = false;
        public showStatus: boolean = false;
        public role: string = this.$store.state.login.role.toUpperCase();
        public response: string = '';

        public logout(): void {
            this.$store.dispatch('login/LOGOUT_ACTION');
            this.$router.push('/login');
        }

        public hideDropdowns(): void {
            this.showCourse = false;
            this.showLogout = false;
            this.showMobile = false;
            this.showStatus = false;
        }

        public toggleOffice(): void {
            this.$store.dispatch('lecturer/toggleInOffice')
                .then((r) => this.response = r);
            this.hideDropdowns();
        }

        public created(): void {
            this.$router.afterEach((to, from) => {
                this.hideDropdowns();
            });
        }
    }
</script>

<style scoped>
    .notification-icon > span {
        background-color: red;
        color: white;
        width: 20px;
        height: 20px;
        position: absolute;
        top: 1px;
        right: 14px;
        text-align: center;
        line-height: 1.5;
        font-size: 12px;
        -webkit-border-radius: 50%;
        -moz-border-radius: 50%;
        border-radius: 50%;
    }

    .dropdown-menu {
        background-color: #343a40;
    }

    .dropdown-menu > a {
        color: #fff;
        cursor: pointer;
    }

    .dropdown-menu > a:hover {
        color: #16181b;
        cursor: pointer;
    }

    .nav-link {
        cursor: pointer;
    }

    .status::before {
        display: inline-block;
        width: 10px;
        height: 10px;
        margin-left: .255em;
        content: "";
        border-bottom: 0;
        border-radius: 50%;
        background-color: transparent;
    }

    .online::before {
        background-color: springgreen;
    }

    .offline::before {
        background-color: red;
    }

    .mobile {
        display: none;
    }

    .notification-number {
        background-color: red;
        color: white;
        width: 20px;
        height: 20px;
        position: absolute;
        top: 5px;
        right: 5px;
        text-align: center;
        line-height: 1.6;
        font-size: 12px;
        -webkit-border-radius: 50%;
        -moz-border-radius: 50%;
        border-radius: 50%;
    }

    .notification-svg {
        max-width: 50px;
    }

    @media screen and (max-width: 768px) {
        .mobile {
            display: inline-block;
        }
    }

    @media screen and (max-width: 576px) {
        .collapsed > .notification-number { /*notification icon before toggle*/
            display: unset !important;
            top: 0;
            right: 12px;
        }

        .notification-icon > span {
            display: block;
            /*position: fixed;*/
            left: calc(50% + 7px);
            bottom: 30px;
            top: unset !important;
            right: unset !important;

        }

        .navbar-toggler > .notification-number { /*notification icon after toggle*/
            display: none;
        }
    }
</style>