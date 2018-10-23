package ru

import java.time.LocalDate
import java.time.temporal.ChronoUnit.DAYS

import ru.Helper._
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.util.Random

object Tinkoff extends App {
  val collectionOfRows: List[Row] = { for (i <- 1 to 10) yield { Row(i) } }.toList
      collectionOfRows.foreach(println)

  val ids = compareRows(collectionOfRows)
      ids.foreach(println)
}

case class Row(id: Int, date: LocalDate = getRandomDate())

object Helper {
  private val from = LocalDate.of(2018, 10, 1)
  private val to   = LocalDate.of(2018, 10, 30)

  def getRandomDate(): LocalDate = {
    val diff   = DAYS.between(from, to)
    val random = new Random(System.nanoTime)
    from.plusDays(random.nextInt(diff.toInt))
  }

 private[this] def isBackDating(left: Row, right: Row): Boolean = left.date.isAfter(right.date)

  def compareRows(rowList: List[Row]): ListBuffer[Int] = {
    val ids = new ListBuffer[Int]

    @tailrec
    def compare(rows: List[Row], rowWithMaxDate: Row): Row = {
      rows match {
        case _ :: Nil  ⇒ rowWithMaxDate
        case _ :: tail ⇒ {
          val newMax = if (isBackDating(rowWithMaxDate, tail.head)) {
                ids += tail.head.id
                rowWithMaxDate
                }
                else { tail.head }
          compare(tail, newMax)
        }
      }
    }
    compare(rowList, rowList.head)
    ids
  }

}
