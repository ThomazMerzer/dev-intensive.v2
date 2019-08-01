package ru.skillbranch.devintensive

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.skillbranch.devintensive.models.User

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
//      Тестим блок init{}
        val user1 = User("id 1")
        val user2 = User("id 2", "User", "Second")
        val user3 = User("id 3", "User", "Third", null, 1, 1, null, true)

        println()

//      Тестим метод каждого объекта
        user1.printMe()
        user2.printMe()
        user3.printMe()
    }

    @Test
    fun test_factory() {
        val user1 = User.Factory.makeUser("Юзер Юзерович")
        println(user1)

        val user2 = user1.copy(id = "id", firstName = "John", lastName = "Cena")
        println(user2)
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("Jonh Wick")

        fun getUserinfo() = user
        val (id, firstName, lastName) = getUserinfo()

        println("$id, $firstName, $lastName")

        println(user.component1())
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("John WIck")
        val user2 = user.copy(id = "1")

        if(user == user2) {
            println("Equals ${user.hashCode()}, ${user2.hashCode()}")
        } else {
            println("Not equals")
        }
    }
}
