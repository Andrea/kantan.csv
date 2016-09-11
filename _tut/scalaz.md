---
layout: tutorial
title: "Scalaz module"
section: tutorial
sort_order: 23
---
Kantan.csv has a [scalaz](https://github.com/scalaz/scalaz) module that is, in its current incarnation, fairly bare
bones: it provides decoders for [`Maybe`] and [`\/`] as well as a few useful type class instances.

The `scalaz` module can be used by adding the following dependency to your `build.sbt`:

```scala
libraryDependencies += "com.nrinaudo" %% "kantan.csv-scalaz" % "0.1.14"
```

You then need to import the corresponding package:

```scala
import kantan.csv.scalaz._
```

## `\/` codecs

For any two types `A` and `B` that each have a [`CellEncoder`], there exists a
`CellEncoder[A \/ B]`. If `A` and `B` each have a [`RowEncoder`], there exists a `RowEncoder[A \/ B]`.

By the same token, for any two types `A` and `B` that each have a [`CellDecoder`], there exists a
`CellDecoder[A \/ B]`. If `A` and `B` each have a [`RowDecoder`], there exists a `RowDecoder[A \/ B]`.

First, a few imports:

```scala
import scalaz._
import kantan.csv.ops._
```

We can then simply write the following:

```scala
scala> "1,2\n4,true".readCsv[List, (Int, Int \/ Boolean)](',', false)
res0: List[kantan.csv.ReadResult[(Int, scalaz.\/[Int,Boolean])]] = List(Success((1,-\/(2))), Success((4,\/-(true))))

scala> "1,2\n4,true".readCsv[List, (Int, Int) \/ (Int, Boolean)](',', false)
res1: List[kantan.csv.ReadResult[scalaz.\/[(Int, Int),(Int, Boolean)]]] = List(Success(-\/((1,2))), Success(\/-((4,true))))
```


## `Maybe` decoder

For any type `A` that has:

 * a [`CellDecoder`], there exists a `CellDecoder[Maybe[A]]`
 * a [`RowDecoder`], there exists a `RowDecoder[Maybe[A]]`
 * a [`CellEncoder`], there exists a `CellEncoder[Maybe[A]]`
 * a [`RowEncoder`], there exists a `RowEncoder[Maybe[A]]`

You can write, for example:

```scala
scala> "1,2\n3,".readCsv[List, (Int, Maybe[Int])](',', false)
res2: List[kantan.csv.ReadResult[(Int, scalaz.Maybe[Int])]] = List(Success((1,Just(2))), Success((3,Empty())))
```

## Scalaz instances

The following instance for cats type classes are provided:

* [`Functor`] for all decoders ([`CellDecoder`] and [`RowDecoder`]).
* [`Contravariant`] for all encoders ([`CellEncoder`] and [`RowEncoder`]).
* [`Order`] for all result types ([`ReadResult`], [`ParseResult`] and [`DecodeResult`]).
* [`Show`] for all result types.
* [`Monoid`] for all result types.
* [`Traverse`] for all result types.
* [`Monad`] for all result types.
* [`BiFunctor`] for all result types.

[`Functor`]:https://oss.sonatype.org/service/local/repositories/releases/archive/org/scalaz/scalaz_2.11/7.2.3/scalaz_2.11-7.2.3-javadoc.jar/!/index.html#scalaz.Functor
[`BiFunctor`]:https://oss.sonatype.org/service/local/repositories/releases/archive/org/scalaz/scalaz_2.11/7.2.3/scalaz_2.11-7.2.3-javadoc.jar/!/index.html#scalaz.Bifunctor
[`Order`]:https://oss.sonatype.org/service/local/repositories/releases/archive/org/scalaz/scalaz_2.11/7.2.3/scalaz_2.11-7.2.3-javadoc.jar/!/index.html#scalaz.Order
[`Show`]:https://oss.sonatype.org/service/local/repositories/releases/archive/org/scalaz/scalaz_2.11/7.2.3/scalaz_2.11-7.2.3-javadoc.jar/!/index.html#scalaz.Show
[`Traverse`]:https://oss.sonatype.org/service/local/repositories/releases/archive/org/scalaz/scalaz_2.11/7.2.3/scalaz_2.11-7.2.3-javadoc.jar/!/index.html#scalaz.Show
[`Monad`]:https://oss.sonatype.org/service/local/repositories/releases/archive/org/scalaz/scalaz_2.11/7.2.3/scalaz_2.11-7.2.3-javadoc.jar/!/index.html#scalaz.Monad
[`Monoid`]:https://oss.sonatype.org/service/local/repositories/releases/archive/org/scalaz/scalaz_2.11/7.2.3/scalaz_2.11-7.2.3-javadoc.jar/!/index.html#scalaz.Monoid
[`\/`]:https://oss.sonatype.org/service/local/repositories/releases/archive/org/scalaz/scalaz_2.11/7.2.3/scalaz_2.11-7.2.3-javadoc.jar/!/index.html#scalaz.$bslash$div
[`Maybe`]:https://oss.sonatype.org/service/local/repositories/releases/archive/org/scalaz/scalaz_2.11/7.2.3/scalaz_2.11-7.2.3-javadoc.jar/!/index.html#scalaz.Maybe
[`CellEncoder`]:{{ site.baseurl }}/api/index.html#kantan.csv.package@CellEncoder[A]=kantan.codecs.Encoder[String,A,kantan.csv.codecs.type]
[`CellDecoder`]:{{ site.baseurl }}/api/#kantan.csv.package@CellDecoder[A]=kantan.codecs.Decoder[String,A,kantan.csv.DecodeError,kantan.csv.codecs.type]
[`RowDecoder`]:{{ site.baseurl }}/api/#kantan.csv.package@RowDecoder[A]=kantan.codecs.Decoder[Seq[String],A,kantan.csv.DecodeError,kantan.csv.codecs.type]
[`RowEncoder`]:{{ site.baseurl }}/api/index.html#kantan.csv.package@RowEncoder[A]=kantan.codecs.Encoder[Seq[String],A,kantan.csv.codecs.type]
[`ReadResult`]:{{ site.baseurl }}/api/index.html#kantan.csv.package@ReadResult[A]=kantan.codecs.Result[kantan.csv.ReadError,A]
[`ParseResult`]:{{ site.baseurl }}/api/index.html#kantan.csv.package@ParseResult[A]=kantan.codecs.Result[kantan.csv.ParseError,A]
[`DecodeResult`]:{{ site.baseurl }}/api/index.html#kantan.csv.package@DecodeResult[A]=kantan.codecs.Result[kantan.csv.DecodeError,A]