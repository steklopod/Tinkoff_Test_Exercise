package ru

import java.time.LocalDate
import java.time.temporal.ChronoUnit.DAYS

import ru.Helper._

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.util.Random

object Tinkoff extends App {
  val collectionOfRows : List[Row] = { for (i <- 1 to 10) yield { Row(i) } }.toList
  collectionOfRows.foreach(println)

  val ids = compareRows(collectionOfRows)
  ids.foreach(println)
}

case class Row(id: Int, date: LocalDate = getRandomDate())

object Helper {
  private val from = LocalDate.of(2018, 10, 1)
  private val to   = LocalDate.of(2018, 10, 30)

  def getRandomDate(): LocalDate = {
    val diff = DAYS.between(from, to)
    val random = new Random(System.nanoTime)
    from.plusDays(random.nextInt(diff.toInt))
  }

 private[this] def isBackDating(left: Row, right: Row): Boolean = left.date.isAfter(right.date)

  def compareRows(rowList: List[Row]): ListBuffer[Int] = {
    var ids = new ListBuffer[Int]
    var maxDate: Row = rowList.head

    @tailrec
    def compare(rows: List[Row]): Unit = {
      rows match {
        case _ :: Nil ⇒ ()
        case _ :: tail ⇒ {
          if (isBackDating(maxDate, tail.head)) { ids += tail.head.id } else {
            maxDate = tail.head
          }
          compare(tail)
        }
      }
    }
    compare(rowList)
    ids
  }

}
