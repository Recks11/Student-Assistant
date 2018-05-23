<template>
    <main class="course">
        <app-course-header :header="'Register Courses'"></app-course-header>
        <div v-if="studentHasChosenProgram===false">
            <!-- Modal -->
            <program-modal></program-modal>
        </div>

        <div v-if="studentHasChosenProgram===true" class="container-fluid">
            <div class="row">
                <div class="col-12 col-md-5 text-center added-courses"
                >
                    <div class="card">
                        <div class="card-header">
                            {{numberOfRegisteredCourses}} Added Courses
                        </div>
                        <ul class="list-group list-group-flush">
                            <li v-for="course in currentStudent.courses"
                                class="list-group-item d-flex justify-content-between align-items-center"
                                :key="course.id">

                                <span class="hide-overflow">
                                    {{course.compulsory ? '[C]' : '[E]'}} {{course.code}} {{course.title}}
                                </span>
                                <span class="badge badge-pill badge-danger">
                                    <a @click="removeCourse(course.id)">
                                        <strong style="color: white;">X</strong>
                                    </a>
                                </span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-12 col-md-7">
                    <h3 class="text-center"> REGISTER COURSES </h3>
                    <form method="post">
                        <div v-for="course in unregisteredCourses"
                             class="form-check d-flex justify-content-between">
                            <span class="add-course hide-overflow">
                                <input class="form-check-input"
                                       :id="course.id"
                                       type="checkbox"
                                       :value="course"
                                       v-model="selectedCourses"/>
                                <label class="form-check-label"
                                       :for="course.id"
                                       style="width: 100%;line-height: 2;">
                                    {{course.code}} - {{course.compulsory ? '[C]' : '[E]'}} {{course.title}}
                                </label>
                                </span>
                            <span class="badge badge-pill response-badge"
                                  :class="{'badge-danger': course.semester==='ALPHA', 'badge-primary':course.semester === 'OMEGA'}"
                                  @click="registerCourses([course])">
                                {{course.semester}}
                                </span>
                        </div>
                        <button type="submit" value="Submit" class="btn btn-info submit" style="width: 80px"
                                @click.prevent="registerCourses(selectedCourses)">Add
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </main>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import Student from '../../model/Student';
    import ProgramModal from '../modal/ProgramModal.vue'
    import Course from '../../model/Course';
    import CourseHeader from './CourseHeader';

    @Component({
        components: {
            programModal: ProgramModal,
            appCourseHeader: CourseHeader,
        },
    })
    export default class CourseRegistration extends Vue {
        public selectedCourses: Course[] = [];

        public get numberOfRegisteredCourses(): number {
            return this.$store.getters['student/GET_REGISTERED_COURSES'].length
        }

        public get currentStudent(): void {
            return this.$store.getters[ 'student/GET_STUDENT' ];
        }

        public get studentHasChosenProgram(): boolean {
            let actualStudent: Student = this.$store.getters[ 'student/GET_STUDENT' ];
            return actualStudent.program.id !== '';
        }

        public get unregisteredCourses() {
            return this.$store.getters[ 'student/GET_UNREGISTERED_COURSES' ];
        }

        public registerCourses(courses: Course[]): void {
            this.$store.dispatch('course/REGISTER_STUDENT', courses)
        }
        public removeCourse(id: string): void {
            this.$store.dispatch('student/REMOVE_COURSE', id)
    }

        public created(): void {
            this.$store.commit('UPDATE_UNREGISTERED_COURSES');
        }
    }
</script>

<style scoped>
    html, body {
        width: 100vw;

    }
    .badge-pill {
        cursor: pointer;
    }
    h3.text-center {
        margin-bottom: 0;
    }
    .badge-primary:hover {
        background-color: #10b0ff;
    }
    .badge-danger:hover {
        background-color: #ff3a4e;
    }

    .form-check {
        position: relative;
        display: block;
        padding: 11px 2.55rem;
        border: 1px solid rgba(0, 0, 0, .125);
    }

    .added-courses {
        margin-top: 33px;
    }

    .submit {
        margin-top: 10px;
    }

    input[type="checkbox"] {
        margin-top: 10px;
    }

    .response-badge {
        line-height: 2.1;
    }

    @media screen and (max-width: 1280px) {
        .hide-overflow {
            white-space: nowrap !important;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .form-check {
            padding-right: 10px;
            height: 55px;
        }
    }

    @media screen and (max-width: 575px) {
        html, body {
            margin-top: 0;
        }

        .col-12 {
            padding-bottom: 10px;
        }

        .added-courses {
            margin-top: 8px;
        }

        .hide-overflow, .form-check > span {
            font-size: 12px;
        }

        .form-check {
            height: 48px;
        }

        .response-badge {
            padding-top: 0;
            padding-bottom: 0;
            height: 24px;
        }

        .add-course {
            height: 24px;
        }

        input[type="checkbox"] {
            margin-top: 6px;
        }
    }
</style>