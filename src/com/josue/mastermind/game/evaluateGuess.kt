package com.josue.mastermind.game

import java.lang.StringBuilder

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var rightPosition = 0
    var wrongPosition = 0
    val secretActual = StringBuilder()
    val guessActual = StringBuilder()

    for(i in secret.indices) {
        if(secret[i] == guess[i]) {
            rightPosition++
        }
    }

    for(i in secret.indices) {
        if(secret[i] != guess[i]) {
            secretActual.append(secret[i])
            guessActual.append(guess[i])
        }
    }

    val evaluatedChars = mutableListOf<Char>()
    if(secretActual.isNotEmpty()) {
        for (element in guess) {
            if (!evaluatedChars.contains(element)) {
                var howManyInSecret = 0
                for (el in secretActual.toString()) {
                    if (el == element) {
                        howManyInSecret++
                    }
                }

                var howManyInGuess = 0
                for (el in guessActual.toString()) {
                    if (el == element) {
                        howManyInGuess++
                    }
                }

                wrongPosition += if (howManyInSecret == howManyInGuess || howManyInSecret > howManyInGuess) howManyInGuess
                else howManyInSecret

                evaluatedChars.add(element)
            }
        }
    }
    return Evaluation(rightPosition, wrongPosition)
}