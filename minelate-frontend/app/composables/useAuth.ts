import type {MinelateUser} from "~/types";

export const useAuth = () => {
    const config = useRuntimeConfig()
    const user = useState<MinelateUser | null>('user', () => null)

    const login = () => {
        navigateTo(`${config.public.apiBase}/oauth/login/authentik`, { external: true })
    }

    const fetchUser = async () => {
        try {
            const userData = await $fetch<any>(`${config.public.apiBase}/auth/me`, {
                credentials: 'include'
            })
            user.value = userData
            return userData
        } catch (e) {
            user.value = null
            return null
        }
    }

    return { user, login, fetchUser }
}