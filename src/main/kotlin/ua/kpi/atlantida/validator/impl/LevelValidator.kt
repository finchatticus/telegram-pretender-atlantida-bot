package ua.kpi.atlantida.validator.impl

import ua.kpi.atlantida.validator.Validator

class LevelValidator(private val error: String, private val levelList: List<String>) : Validator<String> {

    override fun isValid(value: String) = levelList.contains(value)

    override fun getDescription() = error
}