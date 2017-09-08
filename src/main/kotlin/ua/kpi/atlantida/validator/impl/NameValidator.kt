package ua.kpi.atlantida.validator.impl

import ua.kpi.atlantida.validator.Validator


class NameValidator(private val error: String) : Validator<String> {

    private companion object {
        const val NAME_REGEX = "[А-Яа-яёЁЇїІіЄєҐґa-zA-Z '.-]"
    }

    override fun isValid(value: String) = value.matches(Regex(NAME_REGEX))

    override fun getDescription() = error

}