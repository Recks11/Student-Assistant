<template>
        <router-view/>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import Student from '../model/Student';

    @Component
    export default class Course extends Vue {
        public currentStudent: Student = this.$store.getters[ 'student/GET_STUDENT' ];
    }
</script>

<style scoped>
    .container {
        margin-top: 20px;
    }
</style>