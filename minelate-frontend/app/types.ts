export interface Project {
    id: string,
    name: string,
    description: string,
}

export interface MinelateUser {
    id: string,
    preferred_username: string,
    email: string,
    roles: string[],
    picture: string,
}