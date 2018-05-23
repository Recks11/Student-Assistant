export interface LoginState {
    loggedIn: boolean;
    username: string;
    authenticationToken: string;
    role: string;
}

export interface UserDetails {
    username: string;
    password: string;
}
