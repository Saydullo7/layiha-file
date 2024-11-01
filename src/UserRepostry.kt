interface UserRepostry {
    fun register(user: User)
    fun findUser(username: String): User?
    fun deleteUser(username: String): Boolean
    fun updateUser(user: User): Boolean
    fun listUsers(): List<User>
}