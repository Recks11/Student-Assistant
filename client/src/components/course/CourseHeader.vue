<template>
    <div class="container-fluid">
        <div v-if="course.id === ''" class="jumbotron">
            <h1>{{header}}</h1>
        </div>
        <div v-if="course.id !== ''" class="alert alert-success" role="alert"
             :class="{'alert-danger': userRole === 'student' ? course.lecturers.length === 0 : course.students.length === 0}">

            <h4 class="alert-heading"> {{course.code}} {{course.title}} </h4>

            <p v-if="userRole === 'student'">
                There {{course.lecturers.length===1?'is': 'are'}} {{course.lecturers.length}}
                lecturer{{course.lecturers.length===1?'': 's'}} for this course</p>

            <p v-if="userRole === 'lecturer'">{{course.students.length}} students are registered this course</p>

            <hr>
            <p class="mb-0"> This is an {{course.semester}} semester course </p>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from 'vue-property-decorator';
    import Course from '../../model/Course';

    @Component
    export default class CourseHeader extends Vue {
        @Prop({default: 'COURSE HEADER'}) 
        public header!: string;

        @Prop({default: () => new Course()}) 
        public course!: Object;

        public get userRole(): string {
            return this.$store.getters[ 'login/USER_ROLE' ];
        }
    }
</script>

<style scoped>
    .jumbotron {
        margin-bottom: 0;
    }
</style>