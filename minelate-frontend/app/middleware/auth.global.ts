export default defineNuxtRouteMiddleware(async (to, from) => {
    const { user, fetchUser } = useAuth()

    if (!user.value) {
        const fetchedUser = await fetchUser()
        console.log(fetchedUser)

        if (user.value && to.path === '/login') {
            return navigateTo('/projects')
        }

        if (!fetchedUser && to.path !== '/login') {
            return navigateTo('/login')
        }
    }
})