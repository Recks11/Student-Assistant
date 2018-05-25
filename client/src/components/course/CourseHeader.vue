<template>
    <div class="container-fluid">
        <div v-if="course.id === ''" class="jumbotron">
            <h1>{{header}}</h1>
        </div>
        <div v-if="course.id !== ''" class="alert" role="alert"
             :class="{'alert-danger': course.lecturers.length === 0, 'alert-success': course.lecturers.length > 0}">

            <h4 class="alert-heading"> {{course.code}} {{course.title}} </h4>
            <p>There are {{course.lecturers.length}} lecturers for this course</p>
            <hr>
            <p class="mb-0"> this is an {{course.semester}} course </p>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from 'vue-property-decorator';
    import Course from '../../model/Course';

    @Component
    export default class CourseHeader extends Vue {
        @Prop({default: 'COURSE HEADER'}) header!: string;
        @Prop({default: () => new Course()}) course!: Object;
    }
</script>

<style scoped>
    .jumbotron {
        margin-bottom: 0;
    }
</style>