---
pid: "247-en"
type: "post"
title: "4 ways to make a for loop in Java"
url: "/2017/07/4-ways-to-make-a-for-loop-in-java/"
date: 2020-11-01T18:00:00+01:00
language: "en"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo", "programacion"]
summary: "The usual way in Java to do a loop is with a _for_ or _while_ statement but with the addition of iterators in Java 5 it is not necessary to have a variable to keep the index of the loop. Since Java 8, _streams_ have been added that offer other new ways of iterating over the elements of a collection, in the latter case with techniques of functional languages."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Until Java 5 to make a loop from 0 to N elements you had to use a variable to keep a counter, do a comparison to check if the limit had been reached and increase the variable in the next execution. The code was quite verbose and since loops are a basic construction of any programming language it is used many times in any algorithm.

Some of these examples of loops are usable from Java 5, in more recent versions many other new features have been added such as [_lambdas_, _streams_, methods in interfaces and new API for dates][blogbitix-17] in Java 8, [the modularity, improved _try-with-resource_, jlink or a new publishing model][blogbitix-263] in Java 9, [type inference for local variables][blogbitix-306] in Java 10, [an HTTP client][blogbitix-350] in Java 11 and other [novelties in the Java language and platform][blogbitix-serie-java-platform].

Loops are one of [Java's basic flow control structures and statement types][blogbitix-494] that allow repeating the execution of a block of statements as long as the repetition condition expression is met, in each iteration of the loop the condition expression is evaluated and when it is not fulfilled, it continues with the next sentence of the program.

{{< tableofcontents >}}

## for loop

Before Java 5, a _for_ loop from 0 to 5 and a collection was done in the following way, maintaining a variable normally named _i_ that acts as a counter and _j_ if the _for_ loop is nested in another. In addition to the counter variable, it requires setting the condition that allows to exit the loop when the end of the iteration is reached, the condition is very important not to create an infinite loop.

{{<code file="For.java" language="java" options="">}}
{{<code file="Iterator.java" language="java" options="">}}

## foreach loop

In Java 5 the _for_ loop was significantly enriched, the _foreach_ loop is an improved _for_ loop with which you can loop through a collection and any object that implements the [Iterable](javadoc8:java/lang/Iterable.html) interface. This loop has the advantage that there is no need to maintain a variable that acts as a counter, nor does it require establishing a condition to check if the end of the iteration has been reached, this avoids the possibility of creating an infinite loop. With the _foreach_ loop a [Collection](javadoc8:java/util/Collection.html) is traversed as follows.

{{<code file="Foreach.java" language="java" options="">}}

## Loop with Iterable

But the _forearch_ is for collections if you want to make a loop of a fixed number of iterations as in the first case, from 0 to 5, knowing that to use the _foreach_ it is enough that we indicate an object that implements the interface _Iterable_ we can use the following expression and its implementation that has the advantage of not having to include the initial value of the counter, the condition expression and the increment or decrement of the variable. The _Counter_ class implements the _Iterable_ interface and returns an _Iterator_ on the values ​​of the indicated range.

{{<code file="CounterIterable.java" language="java" options="">}}
{{<code file="Counter.java" language="java" options="">}}

## Loop with streams

In Java 8 with the introduction of [Stream](javadoc8:java/util/stream/Stream.html) and [IntStream](javadoc8:java/util/stream/IntStream.html) we can use the [range](javadoc8:java/util/stream/IntStream.html#range-int-int-) and [rangeClosed](javadoc8:java/util/stream/IntStream.html#rangeClosed-int-int-) to get a _Stream_ of integers and make a loop with a behavior similar to the previous ones.

{{<code file="Stream.java" language="java" options="">}}

The Java 8 _Stream_ are fine to simplify some complex operations but for a simple _for_ loop it has its drawbacks such as significantly obfuscating the _stacktrace_ in case of an exception. Any option can be used, but the first with the traditional _for_ loop is the least recommended, having at our disposal the _Counter_ class with Java 5 or the _Stream_ and _lambdas_ with Java 8.

## Example with the different types of loop

The following program shows the four options, its output on the console would be the following:

{{<code file="Main.java" language="java" options="">}}

For any of the ways of doing the _for_ loop the behavior is the same, iterate a finite number of times or over the elements of a collection. Choosing which one to use among the different types of loops depends on the case and personal preferences but also considering the readability and expressiveness of the source code.

{{< code file="System.out" language="plain" options="" >}}

{{< sourcecode git="blog-ejemplos/tree/master/JavaForeach" command="./gradlew run" >}}

{{% /post %}}
