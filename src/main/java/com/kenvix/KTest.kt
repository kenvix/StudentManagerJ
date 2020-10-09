package com.kenvix

fun main() {
    val fuckToCharArray: Consumer<String, CharArray> = Consumer { it.toCharArray() }
    println(fuckToCharArray("fuck you"))
}

fun interface Consumer<T, R> {
    operator fun invoke(x: T): R
}