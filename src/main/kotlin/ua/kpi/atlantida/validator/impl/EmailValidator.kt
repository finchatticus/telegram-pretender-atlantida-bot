package ua.kpi.atlantida.validator.impl

import ua.kpi.atlantida.validator.Validator

class EmailValidator(private val error: String) : Validator<String> {

    private companion object {
        const val EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    }

    override fun isValid(value: String) = value.matches(Regex(EMAIL_REGEX))

    override fun getDescription() = error
}