package kantan.csv.scalaz

import arbitrary._
import kantan.csv.laws.discipline.{CellCodecTests, RowCodecTests}
import org.scalatest.FunSuite
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.typelevel.discipline.scalatest.Discipline
import scalaz.\/

class DisjunctionTests extends FunSuite with GeneratorDrivenPropertyChecks with Discipline {
  checkAll("Int \\/ Boolean", CellCodecTests[Int \/ Boolean].codec[Byte, Float])
  checkAll("(Int, Int, Int) \\/ (Boolean, Float)", RowCodecTests[(Int, Int, Int) \/ (Boolean, Float)].codec[Byte, String])
}