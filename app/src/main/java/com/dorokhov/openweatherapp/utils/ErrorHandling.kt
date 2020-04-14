package com.dorokhov.openweatherapp.utils

class ErrorHandling {

    companion object {

        private val TAG: String = "AppDebug"

        const val UNABLE_TO_RESOLVE_HOST = "Unable to resolve host"
        const val UNABLE_TODO_OPERATION_WO_INTERNET =
            "Can't do that operation without an internet connection"
        const val NO_RESULTS = "Не удалось найти никаких результатов"
        const val INCORRECT_INPUT = "Вам удалось обойти регулярное выражение, но все же введенные данные не верны"

        const val ERROR_CHECK_NETWORK_CONNECTION = "Check network connection."
        const val ERROR_UNKNOWN = "Unknown error"


        fun isNetworkError(msg: String): Boolean {
            when {
                msg.contains(UNABLE_TO_RESOLVE_HOST) -> return true
                else -> return false
            }
        }

        fun prettyMessage(msg: String): String {
            if (msg.contains("no results")) {
                return NO_RESULTS
            }

            if (msg.contains("is not a float")) {
                return INCORRECT_INPUT
            }

            return msg
        }

    }

}