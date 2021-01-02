package com.adrianhoelzl.ktrie.extension

fun <T> List<T>.head(): T? = firstOrNull()
fun <T> List<T>.tail(): List<T> = drop(1)