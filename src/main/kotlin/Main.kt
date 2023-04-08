import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

fun main(){
    var connection : Connection? = null
    try{

        val url = "jdbc:mysql://localhost:3306/newdb"
        val user = "developer"
        val password = "1234"

        connection = DriverManager.getConnection(url, user, password)
        val statement = connection.createStatement()

        statement.execute("CREATE VIEW italian_students AS SELECT first_name, last_name FROM students WHERE country = 'italia'")
        statement.execute("CREATE VIEW german_students AS SELECT first_name, last_name FROM students WHERE country = 'germania'")

        val italianList = mutableListOf<Student>()
        val germanList = mutableListOf<Student>()

        val italianDetailsSet = statement.executeQuery("SELECT first_name, last_name FROM italian_students")

        while(italianDetailsSet.next()){
            val name = italianDetailsSet.getString("first_name")
            val surname = italianDetailsSet.getString("last_name")
            val italianStudent = Student(name, surname)
            italianList.add(italianStudent)
        }

        val germanDetailsSet = statement.executeQuery("SELECT first_name, last_name FROM german_students")

        while(germanDetailsSet.next()){
            val name = germanDetailsSet.getString("first_name")
            val surname = germanDetailsSet.getString("last_name")
            val germanStudent = Student(name, surname)
            germanList.add(germanStudent)
        }

        println(italianList)
        println("-------------------------")
        println(germanList)




    }catch (e: SQLException){
        println(e.message)
    } finally {
        connection?.close()
        try {
        }catch (ex: SQLException) {
            println(ex.message)
        }
    }
}
