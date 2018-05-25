<template>
    <div class="AddCourse">
        <div class="container" v-for="department in departments" :key="department.id">
            <div class="row" style="margin-bottom: 20px;">
                <div class="col-12" :class="{'col-md-6': department.Programs.length === 2,'col-md-4': department.Programs.length === 3}" v-for="program in department.Programs">
                    <div class="card">
                        <div class="card-header">
                            {{program.name}}
                        </div>
                        <div class="wrapper">
                        <ul class="list-group">
                            <li class="list-group-item d-flex justify-content-between align-items-center" v-for="course in program.courses" v-if="lecturerLecturesCourse(course)">
                                <span class="hide-overflow">
                                    {{course.compulsory===true?'[C] ':'[E] '}} {{course.code}} {{course.title}}</span>
                                <span class="badge badge-pill badge-danger">
                            <a @click="removeCourse(course.id)">
                                <strong style="color: white;">X</strong>
                            </a>
                        </span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center"> Add Courses Below</li>
                        </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-12" :class="{'col-md-6': department.Programs.length === 2,'col-md-4': department.Programs.length === 3}" v-for="program in department.Programs">
                    <div class="card">
                        <div class="card-header">
                            {{program.name}}
                        </div>
                        <div class="wrapper">

                        <ul class="list-group">
                            <li class="list-group-item d-flex justify-content-between align-items-center" v-for="course in program.courses" v-if="!lecturerLecturesCourse(course)">
                                <span class="hide-overflow">
                                    {{course.compulsory===true?'[C] ':'[E] '}} {{course.code}} {{course.title}}</span>
                                <span class="badge badge-pill badge-success">
                            <a style="cursor: pointer" @click="addCourse(course)">
                                <strong style="color: white;">ADD</strong>
                            </a>
                        </span>
                            </li>
                        </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from 'vue-property-decorator';
    import Lecturer from '../../model/Lecturer';
    import Department from '../../model/Department';
    import Course from '../../model/Course';

    @Component
    export default class AddCourse extends Vue {
        public message: string = '';

        public lecturerLecturesCourse(course: Course): boolean {
            return this.lecturer.courses.findIndex(value => value.id === course.id) !== -1
        }

        get departments(): Department[] {
            return this.$store.getters['GET_DEPARTMENT_ARRAY'];
        }

        get lecturer(): Lecturer {
            return this.$store.getters['ACTIVE_LECTURER'];
        }

        public addCourse(course: Course): void {
            this.$store.dispatch('lecturer/ADD_COURSE', course)
                .then(() => this.message = 'success')
                .catch(() => this.message = 'An error occurred');
        }

        public removeCourse(id: string): void {
            this.$store.dispatch('lecturer/REMOVE_COURSE', id)
                .then(() => this.message = 'success')
                .catch(() => this.message = 'An error occurred');
        }
    }
</script>

<style scoped>
    .wrapper {
        max-height: 600px;
        overflow: scroll;
    }
    .card {
        margin-bottom: 20px;
    }
</style>