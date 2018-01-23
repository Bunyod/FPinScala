package crackcodinginterview.chapter1

import scala.collection.immutable.IntMap


object HashTables extends App {

  case class Student(firstName: String, lastName: String, group: String)

  val students = Array(Student("John", "Defoe", "911-07"),
    Student("Abraham", "Rudy", "912-07"),
    Student("Elizabet", "Gilber", "911-07"),
    Student("Martin", "Oderskiy", "911-07")
  )

  var studentsMap = IntMap.empty[Student]
  for (i <- students.indices){
    studentsMap += i -> students(i)
  }
  println(studentsMap)
}