<template>
    <div class="ViewCourse">
        <app-course-header :course="course" :userRole="userRole"></app-course-header>
        <div class="table-responsive-md">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">email</th>
                    <th scope="col">office</th>
                    <th scope="col">location</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(lecturer, index) in course.lecturers">
                    <th scope="row"> {{index + 1}}</th>
                    <td>{{lecturer.firstName}} {{lecturer.lastName}}</td>
                    <td><a :href="'mailto:'+ lecturer.email"> {{lecturer.email}} </a></td>
                    <td> {{lecturer.office}}</td>
                    <td> {{lecturer.inOffice ? 'In Office' : 'Not Available'}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import CourseHeader from '../CourseHeader.vue';
    import Course from '../../../model/Course';

    @Component({
        components: {
            appCourseHeader: CourseHeader,
        },
    })
    export default class ViewCourse extends Vue {
        public get course(): Course {
            return this.$store.getters[ 'GET_COURSE' ];
        }

        public get userRole(): string {
            return this.$store.getters[ 'login/USER_ROLE' ];
        }
    }
</script>

<style scoped>

</style>