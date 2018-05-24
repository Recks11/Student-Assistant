<template>
    <main>
        <section class="container-fluid">
            <div class="row">
                <div class="col-md-4 text-center">
                    <img class="card-img-top img-thumbnail"
                         src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22286%22%20height%3D%22180%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20286%20180%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_16249ef39ba%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A14pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_16249ef39ba%22%3E%3Crect%20width%3D%22286%22%20height%3D%22180%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22107.1953125%22%20y%3D%2296.6%22%3E286x180%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E"
                         alt="Card image cap">
                </div>
                <div class="col-md-8 d-flex align-items-center">
                    <div class="card-body text-left">
                        <h3>Name: {{currentStudent.firstName}} {{currentStudent.lastName}}</h3>
                        <h3>Email: {{currentStudent.email}}</h3>
                        <template v-if="currentStudent.program.name !== ''">
                            <h3>College: {{ currentStudent.program.department.college.name }}</h3>

                            <h3>Department: {{ currentStudent.program.department.name}}</h3>

                            <h3> Program: {{currentStudent.program.name}}</h3>
                        </template>


                    </div>
                </div>
            </div>
        </section>
        <section class="breaks">
            <br/><br/>
            <br/><br/>
        </section>
        <section>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-header text-center">
                                COURSES
                            </div>
                            <div v-if="currentStudent.courses.length === 0" class="card-body">
                                <p class="card-text">You have not registered any courses</p>
                                <router-link to="/course/register" class="btn btn-success">Add Courses
                                </router-link>
                            </div>
                            <div v-else v-for="course in courseSnippet" :key="course.id" class="list-group">
                                <li class="list-group-item list-group-item-action d-flex justify-content-between"
                                    @click="goToCourse(course.id)" style="cursor: pointer">
                                    <span class="hide-overflow"> {{course.code}} {{course.title}}</span>
                                    <span class="badge badge-pill"
                                          :class="{'badge-danger': +course.units>=3, 'badge-info': course.units<3}">
                                        <strong style="color: white;">{{course.units}}</strong>
                                    </span>
                                </li>
                            </div>
                            <div v-if="currentStudent.courses.length >= 5" class="list-group-item">
                                <router-link to="/course/view">View all ({{currentStudent.courses.length}})</router-link>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-header text-center">
                                NOTIFICATIONS
                            </div>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item d-flex justify-content-between align-items-center hide-overflow">
                                    <a href="#">CSC 411 Test on friday by 10am </a><span
                                        class="badge badge-primary badge-pill">1</span></li>
                                <li class="list-group-item d-flex justify-content-between align-items-center hide-overflow">
                                    CSC 411 Test on friday by 10am <span class="badge badge-primary badge-pill">1</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center hide-overflow">
                                    CSC 411 Test on friday by 10am <span class="badge badge-primary badge-pill">1</span>
                                </li>
                            </ul>

                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-header text-center">
                                Calendar
                            </div>
                            <!-- Responsive calendar - START -->
                            <div v-if="currentStudent.program !== null" class="responsive-calendar">
                                <div style="height: 400px; width: 100%">
                                    <iframe src="https://calendar.google.com/calendar/embed?src=emmanuel.irem%40stu.cu.edu.ng&ctz=Africa%2FLagos"
                                            style="border: 0" width="100%" height="100%" frameborder="0"
                                            scrolling="no">
                                    </iframe>
                                </div>
                            </div>
                            <!-- Responsive calendar - END -->
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import Student from '../model/Student';
    import Course from '../model/Course';

    @Component
    export default class StudentHome extends Vue {
        public error: string = '';

        public goToCourse(id: string): void {
            this.$store.dispatch('course/GET_COURSE', id).then(
                () => {
                    this.$router.push({path: '/course/view/' + id});
                });
        }

        public get currentStudent(): Student {
            return this.$store.getters[ 'student/GET_STUDENT' ];
        }

        public get courseSnippet(): Course[] {
            if ( this.currentStudent.courses.length > 4 ) {
                let courseArray = this.currentStudent.courses;
                return courseArray.slice(((courseArray.length - 1) - 4), courseArray.length-1).reverse();
            }
            return this.currentStudent.courses;
        }

        public created() {
            console.log('created');
            if ( this.error !== '' ) {
                this.$router.push('/login');
            }
            if ( this.$store.getters[ 'GET_PROGRAM_ARRAY' ].length === 0 ) {
                this.$store.dispatch('program/GET_STORED_PROGRAMS');
            }
        }
    }
</script>

<style scoped>
    .col-md-4 {
        margin-bottom: 5px;
    }

    span {
        font-size: 1rem;
    }

    @media screen and (max-width: 1280px) {
        p, a, span, li, .card-header {
            font-size: 0.75rem;
        }

        h3 {
            font-size: 1.25rem;
        }
    }

    @media screen and (max-width: 768px) {
        h3 {
            font-size: 1.25rem;
        }
    }
</style>