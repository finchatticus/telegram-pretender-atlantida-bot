package ua.kpi.atlantida.validator.impl

import ua.kpi.atlantida.validator.Validator


class NameValidator(private val error: String) : Validator<String> {

    override fun isValid(value: String) = true

    override fun getDescription() = error

}