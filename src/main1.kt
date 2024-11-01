import java.util.Scanner

fun main() {

        val userRepository: UserRepostry = FileUserRepository("users.txt")

        while (true) {
            println("\n1. Ro'yxatdan o'tish")
            println("2. Foydalanuvchini qidirish")
            println("3. Foydalanuvchini o'chirish")
            println("4. Foydalanuvchini o'zgartirish")
            println("5. Foydalanuvchilar ro'yxatini ko'rish")
            println("6. Chiqish")

            when (readLine()?.toIntOrNull()) {
                1 -> {
                    println("Foydalanuvchi nomini kiriting:")
                    val username = readLine() ?: ""
                    println("Parolni kiriting:")
                    val password = readLine() ?: ""
                    println("Emailni kiriting:")
                    val email = readLine() ?: ""
                    userRepository.register(User(username, password, email))
                    println("$username ro'yxatdan o'tdi.")
                }
                2 -> {
                    println("Qidirilayotgan foydalanuvchi nomini kiriting:")
                    val username = readLine() ?: ""
                    val user = userRepository.findUser(username)
                    if (user != null) {
                        println("Foydalanuvchi topildi: $user")
                    } else {
                        println("Foydalanuvchi topilmadi.")
                    }
                }
                3 -> {
                    println("O'chiriladigan foydalanuvchi nomini kiriting:")
                    val username = readLine() ?: ""
                    if (userRepository.deleteUser(username)) {
                        println("$username o'chirildi.")
                    } else {
                        println("Foydalanuvchi topilmadi.")
                    }
                }
                4 -> {
                    println("O'zgartiriladigan foydalanuvchi nomini kiriting:")
                    val username = readLine() ?: ""
                    val existingUser = userRepository.findUser(username)
                    if (existingUser != null) {
                        println("Yangi parolni kiriting:")
                        val newPassword = readLine() ?: existingUser.password
                        println("Yangi emailni kiriting:")
                        val newEmail = readLine() ?: existingUser.email
                        userRepository.updateUser(User(username, newPassword, newEmail))
                        println("$username ma'lumotlari yangilandi.")
                    } else {
                        println("Foydalanuvchi topilmadi.")
                    }
                }
                5 -> {
                    val users = userRepository.listUsers()
                    if (users.isEmpty()) {
                        println("Hech qanday foydalanuvchi mavjud emas.")
                    } else {
                        users.forEach { user ->
                            println(user)
                        }
                    }
                }
                6 -> {
                    println("Dasturdan chiqilyapti.")
                    return
                }
                else -> println("Noto'g'ri tanlov.")
            }
        }
    }

