<template>
    <div class="ViewCourseStudents">
        <app-course-header :course="course"></app-course-header>
        <br/>
        <div>
            <div class="container-fluid">
                <div class="table-responsive-md">
                    <table class="table table-sm">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Room</th>
                            <th scope="col">Program</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(student, idx) in course.students">
                            <th scope="row">{{idx+1}}</th>
                            <td>{{student.firstName}} {{student.lastName}}</td>
                            <td><a :href="'mailto:'+ student.email.toLowerCase()">{{student.email}}</a></td>
                            <td>{{student.hall}} {{student.roomNumber}}</td>
                            <td>{{student.program.name}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import Course from '../../../model/Course';
    import CourseHeader from '../CourseHeader.vue';

    @Component({
        components: {
            appCourseHeader: CourseHeader,
        },
    })
    export default class ViewCourse extends Vue {
        public get course(): Course {
            return this.$store.getters[ 'GET_COURSE' ];
        }
    }
</script>

<style scoped>

</style>