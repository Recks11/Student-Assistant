import Vue from 'vue';
import Router from 'vue-router';

const LecturerHome = () => import('./views/LecturerHome.vue');
const Register = () => import('@/views/Register.vue');
const Login = () => import('@/views/Login.vue');
const StudentHome = () => import('@/views/StudentHome.vue');
const Course = () => import('@/views/Course.vue');
const CourseRegistration = () => import('@/components/course/CourseRegistration.vue');
const RegisteredCourses = () => import('@/components/course/RegisteredCourses.vue');
const ViewCourse = () => import('@/components/course/ViewCourse.vue');
const LecturerCourses = () => import('@/views/LecturerCourses.vue');

Vue.use(Router);

export const router: Router = new Router({
    routes: [
        {
            path: '/login',
            name: 'login',
            component: Login,
        },
        {
            path: '/register',
            name: 'register',
            component: Register,
        },
        {
            path: '/student',
            name: 'studentHome',
            component: StudentHome,
        },
        {
            path: '/course',
            component: Course,
            children: [
                {
                    path: 'register',
                    component: CourseRegistration
                },
                {
                    path: 'view',
                    component: RegisteredCourses,
                },
                {
                    name: 'courseInfo',
                    path: 'view/:id',
                    component: ViewCourse,
                },
            ],
        },
        {
            path: '/lecturer',
            component: LecturerHome
        },
        {
            path: '/lecturer/courses',
            component: LecturerCourses,
        },
        {
            path: '*',
            redirect: '/login',
        }
    ],
    mode: 'history',
});
