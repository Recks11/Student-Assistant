<template>
    <div class="ViewCourse">
        <app-course-header :header="'Your Courses'"></app-course-header>
        <div class="container-fluid">
            <div class="table-responsive-md">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Level</th>
                        <th scope="col">Semester</th>
                        <th scope="col">Code</th>
                        <th scope="col">Title</th>
                        <th scope="col">Units</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="(course, index) in registeredCourses" :key="course.id"
                        @click="navigateToCourse(course.id)" style="cursor: pointer;">
                        <th>{{index + 1}}</th>
                        <td>{{course.level}}</td>
                        <td>{{course.semester}}</td>
                        <td>{{course.code}}</td>
                        <td> {{course.title}}</td>
                        <td>{{course.units}}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import CourseHeader from '../CourseHeader';
    import Course from '../../../model/Course';

    @Component({
        components: {
            appCourseHeader: CourseHeader,
        },
    })
    export default class ViewCourse extends Vue {
        public get registeredCourses(): Course[] {
            return this.$store.getters['ACTIVE_LECTURER'].courses
        }
        public navigateToCourse(id: string): void {
            this.$router.push({path: 'view/'+ id});
        }
    }
</script>

<style scoped>

</style>