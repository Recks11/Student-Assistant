<template>
    <div class="LecturerHome">
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="alert alert-success" role="alert" >
                    <h4 class="alert-heading">Welcome {{lecturer.firstName}}!</h4>
                    <p v-if="lecturer.department!== ''" :style="{color: lecturer.departments.length === 0 ? 'red' : 'inherit'}">
                        You lecture in {{lecturer.departments.length}} department(s)</p>
                    <p v-if="lecturer.department=== ''">Student Assitant</p>
                    <p>{{message}}</p>
                    <hr>
                    <p class="mb-0">Interact with your students in a whole new way today</p>
                </div>
            </div>

            <div class="container-fluid">
                <div class="alert" role="alert" v-if="lecturer.courses.length > 0" v-for="course in lecturer.courses"
                     :class="{'alert-danger':course.students.size()===0, 'alert-success':course.students.size() > 0}">
                    <router-link :to="'/lecturer/courses/view/'+course.id"> <h4 class="alert-heading" >{{course.code}}  {{course.title}}</h4> </router-link>
                    <p> {{'There are '+course.students.length+' students registered for this course'}} </p>
                    <hr>
                    <p class="mb-0"> {{'This is an '+course.semester+' semester course'}} </p>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import Lecturer from '../model/Lecturer';

    @Component
    export default class LecturerHome extends Vue {
        public message: string = '';
        public get lecturer(): Lecturer {
            return this.$store.getters['GET_LECTURER'];
        }

        public created(): void {
            this.$store.dispatch('department/GET_STORED_DEPARTMENTS')
                .then(() => this.message = 'All departments loaded');
        }
    }
</script>

<style scoped>

</style>