package com.tsovedenski.csp

import com.tsovedenski.csp.strategies.Backtracking
import kotlin.system.measureTimeMillis

/**
 * Created by Tsvetan Ovedenski on 14/11/2018.
 */
interface Solvable <V, D> {
    fun toProblem(): Problem<V, D>
}

fun <V, D> Solvable<V, D>.solve(strategy: Strategy<V, D> = Backtracking<V, D>()): Solution<V, D>  {
    val problem = toProblem()
    val assignment = problem.toAssignment().consistentWith(problem.constraints)
    val job = Job(assignment, problem.constraints)

    var solved: Job<V, D>? = null // TODO: Change to `val` once KT-27856 is done
    val time = measureTimeMillis {
        solved = strategy.run(job)
    }

    return when (solved) {
        null -> NoSolution
        else -> Solved(solved!!.assignment, Statistics(solved!!.counter, time))
    }
}
