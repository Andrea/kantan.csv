/*
 * Copyright 2015 Nicolas Rinaudo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kantan.csv

import java.text.SimpleDateFormat
import java.util.{Date, Locale}
import laws.discipline._, arbitrary._

class DateCodecTests extends DisciplineSuite {

  implicit val codec: CellCodec[Date] =
    CellCodec.dateCodec(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH))

  checkAll("CellEncoder[Date]", SerializableTests[CellEncoder[Date]].serializable)
  checkAll("CellDecoder[Date]", SerializableTests[CellDecoder[Date]].serializable)

  checkAll("RowEncoder[Date]", SerializableTests[RowEncoder[Date]].serializable)
  checkAll("RowDecoder[Date]", SerializableTests[RowDecoder[Date]].serializable)

  checkAll("CellCodec[Date]", CellCodecTests[Date].codec[String, Float])
  checkAll("RowCodec[Date]", RowCodecTests[Date].codec[String, Float])

}
