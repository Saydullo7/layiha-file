import java.io.File
import java.io.FileNotFoundException

class FileUserRepository(private val filePath: String) :UserRepostry {
     val users =  mutableListOf<User>()

    init {
        loadUsersFromFile()
    }

    override fun register(user: User) {
        users.add(user)
        saveUsersToFile()
    }

    override fun findUser(username: String): User? {
        return users.find { it.username == username }
    }

    override fun deleteUser(username: String): Boolean {
        val user = findUser(username)
        return if (user != null) {
            users.remove(user)
            saveUsersToFile()
            true
        } else {
            false
        }
    }

    override fun updateUser(user: User): Boolean {
        val index = users.indexOfFirst { it.username == user.username }
        return if (index != -1) {
            users[index] = user
            saveUsersToFile()
            true
        } else {
            false
        }
    }

    override fun listUsers(): List<User> {
        return users
    }

    private fun saveUsersToFile() {
        File(filePath).printWriter().use { out ->
            users.forEach { user ->
                out.println("${user.username},${user.password},${user.email}")
            }
        }
    }

    private fun loadUsersFromFile() {
        try {
            File(filePath).forEachLine { line ->
                val parts = line.split(",")
                if (parts.size == 3) {
                    val user = User(parts[0], parts[1], parts[2])
                    users.add(user)
                }
            }
        } catch (e: FileNotFoundException) {
            // Fayl topilmadi, hech narsa qilmaymiz
        }
    }
}
