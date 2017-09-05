package ua.kpi.atlantida.validator

interface Validator<T> {

    fun isValid(value: T): Boolean

    fun getDescription(): String
}