package com.rishi.groww.assignment.starwars.exceptions

class NoInternetException: Exception() {
    override fun getLocalizedMessage(): String? {
        return "No Internet Connectivity Issue"
    }
}