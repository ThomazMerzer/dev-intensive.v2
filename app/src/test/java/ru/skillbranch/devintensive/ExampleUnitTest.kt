package ru.skillbranch.devintensive

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import java.util.*

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
        val user2 = user.copy(lastVisit = Date())
        val user3 = user.copy(lastName = "Cena", lastVisit = Date().add(1, TimeUnits.DAYS))

        println("""
            ${user.lastVisit?.format("dd.MM.yy")}
            ${user2.lastVisit?.format("dd.MM.yy")}
            ${user3.lastVisit?.format("dd.MM.yy")}
        """.trimIndent())

        val testVal: List<String> = user2.lastVisit?.format("HH:mm:ss")!!.split(":")
        val hh = testVal[0]
        val mm = testVal[1]
        val ss = testVal[2]
        println("Часы $hh Минуты $mm Секунды $ss")
    }

    @Test
    fun test_data_mapping() {
        val user = User.makeUser("Thomaz Merzer")

        user.printMe()

        val userView = user.toUserView()

        userView.printMe()
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Thomaz Merzer")
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any image message", type = "image")

        when(txtMessage) {
            is TextMessage -> println("This is text message")
        }
        when(imgMessage) {
            is ImageMessage -> println("This is image message")
        }

        println(txtMessage.formatMessage())
        println(imgMessage.formatMessage())
    }
}
