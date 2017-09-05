package ua.kpi.atlantida.validator.impl

import ua.kpi.atlantida.validator.Validator

class SwimmingLevelValidator(private val error: String, private val swimmingList: List<String>) : Validator<String> {

    override fun isValid(value: String) = swimmingList.contains(value)

    override fun getDescription() = error
}