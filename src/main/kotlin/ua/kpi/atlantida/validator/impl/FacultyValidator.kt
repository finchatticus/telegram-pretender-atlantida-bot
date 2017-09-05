package ua.kpi.atlantida.validator.impl

import ua.kpi.atlantida.validator.Validator


class FacultyValidator(private val error: String, private val facultyList: List<String>) : Validator<String> {

    override fun isValid(value: String) = facultyList.contains(value)

    override fun getDescription() = error
}