import Vue from 'vue';
import Router from 'vue-router';
import store from '@/store';

const About = () => import('./views/About.vue');
const Register = () => import('@/views/Register.vue');
const Login = () => import('@/views/Login.vue');
const StudentHome = () => import('@/views/StudentHome.vue');
const Course = () => import('@/views/Course.vue');
const CourseRegistration = () => import('@/components/course/CourseRegistration.vue');
const RegisteredCourses = () => import('@/components/course/RegisteredCourses.vue');
const ViewCourse = () => import('@/components/course/ViewCourse.vue');

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
            path: '/lecturer',
            name: 'about',
            component: About,
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
            path: '*',
            redirect: '/login',
        }
    ],
    mode: 'history',
});
