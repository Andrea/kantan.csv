/*
 * Copyright 2016 Nicolas Rinaudo
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

import kantan.codecs.Codec

/** Declares helpful methods for [[CellCodec]] creation. */
object CellCodec {
  /** Creates a new [[CellCodec]] from the specified decoding and encoding functions. */
  def from[A](decoder: String ⇒ DecodeResult[A])(encoder: A ⇒ String): CellCodec[A] = Codec.from(decoder)(encoder)

  @deprecated("use from instead (see https://github.com/nrinaudo/kantan.csv/issues/44)", "0.1.14")
  def apply[A](decoder: String ⇒ DecodeResult[A])(encoder: A ⇒ String): CellCodec[A] = CellCodec.from(decoder)(encoder)
}

trait CellCodecInstances extends CellEncoderInstances with CellDecoderInstances