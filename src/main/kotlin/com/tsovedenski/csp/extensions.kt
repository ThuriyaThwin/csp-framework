package com.tsovedenski.csp

import kotlin.math.min

/**
 * Created by Tsvetan Ovedenski on 15/10/2018.
 */
// pairs
internal fun <T> List<T>.pairs(): List<Pair<T, T>>
        = zip(1 .. size).flatMap { (a, i) -> drop(i).map { b -> a to b } }

internal fun <A, B> List<A>.pairs(other: List<B>): List<Pair<A, B>>
        = flatMap { a -> other.map { b -> a to b } }

internal fun <T> Sequence<T>.pairs(): Sequence<Pair<T, T>>
        = zip((1..count()).asSequence()).flatMap { (a, i) -> drop(i).map { b -> a to b } }

internal fun <A, B> Sequence<A>.pairs(other: Sequence<B>): Sequence<Pair<A, B>>
        = flatMap { a -> other.map { b -> a to b } }

// Pair map
fun <A, B, T> Pair<A, B>.mapLeft(f: (A) -> T): Pair<T, B> = Pair(f(first), second)
fun <A, B, T> Pair<A, B>.mapRight(f: (B) -> T): Pair<A, T> = Pair(first, f(second))
fun <A, B, X, Y> Pair<A, B>.map(left: (A) -> X, right: (B) -> Y): Pair<X, Y> = Pair(left(first), right(second))

// fix
tailrec fun <T> fix(value: T, f: (T) -> T): T {
    val next = f(value)
    return when (next) {
        value -> next
        else  -> fix(next, f)
    }
}