package com.example.myapplication.algorithms

import java.util.concurrent.ConcurrentHashMap
import kotlin.system.measureTimeMillis

var solutionCount = 0
val allResult: ConcurrentHashMap<String, ArrayList<Int>> = ConcurrentHashMap()
val allStep: ConcurrentHashMap<String, String> = ConcurrentHashMap()
val moneyWithdrawSteps: ArrayList<String> = ArrayList()

fun main() {
    val listOfMoney = arrayListOf(1, 2, 5)
    val withdrawAmount = 10

    val time = measureTimeMillis {
        withdraw(listOfMoney = listOfMoney, withdrawAmount = withdrawAmount, step = null)
    }

    println("Completed in $time ms")
    println("Withdraw $withdrawAmount with list of money $listOfMoney")
    println("Solution count = $solutionCount")
    for (step in moneyWithdrawSteps) {
        println(step)
    }
}

fun withdraw(listOfMoney: ArrayList<Int>, withdrawAmount: Int, step: String?) {
    var moneyWithdrawStep = step ?: ""
    var totalMoney = withdrawAmount

    if (listOfMoney.isNotEmpty()) {
        allResult["$withdrawAmount$listOfMoney"] = listOfMoney
        allStep["$totalMoney$listOfMoney"] = moneyWithdrawStep
        val moneyUsed = listOfMoney.first()

        while (totalMoney - moneyUsed >= 0) {
            moneyWithdrawStep += if (moneyWithdrawStep.isEmpty()) {
                "$moneyUsed"
            } else {
                " + $moneyUsed"
            }
            totalMoney -= moneyUsed
            allResult["$totalMoney$listOfMoney"] = listOfMoney
            val tempStep = allStep["${totalMoney + moneyUsed}$listOfMoney"]
            tempStep?.let { it ->
                allStep["$totalMoney$listOfMoney"] = if (it.isNotEmpty()) {
                    "$it + $moneyUsed"
                } else {
                    "$moneyUsed"
                }
            }

            if (totalMoney == 0) {
                solutionCount += 1
                moneyWithdrawSteps.add(
                    "Solution $solutionCount = $moneyWithdrawStep"
                )
            }
        }
    }

    //println(allResult)
    //println(allStep)
    //println("-----------------------------------")

    if (allResult.keys.isNotEmpty()) {
        for (key in allResult.keys) {
            val newWithdrawAmount = key.split("[").first().toInt()

            if (newWithdrawAmount != 0) {
                val newMoneyList: ArrayList<Int> = ArrayList()
                allResult[key]?.let { moneyList -> newMoneyList.addAll(moneyList) }
                if (newMoneyList.isNotEmpty()) {
                    newMoneyList.removeFirst()
                    allResult.remove(key = key)
                    val newStep = allStep[key]
                    allStep.remove(key = key)
                    withdraw(
                        listOfMoney = newMoneyList,
                        withdrawAmount = newWithdrawAmount,
                        step = newStep
                    )
                }
            }
        }
    }
}




