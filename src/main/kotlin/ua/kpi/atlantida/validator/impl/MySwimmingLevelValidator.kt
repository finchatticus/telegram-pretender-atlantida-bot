package ua.kpi.atlantida.validator.impl

import ua.kpi.atlantida.validator.Validator

/**
 * Created by vlad on 06.09.2018
 */
class MySwimmingLevelValidator(private val error: String, private val mySwimmingList: List<String>) : Validator<String> {

    override fun isValid(value: String) = mySwimmingList.contains(value)

    override fun getDescription() = error
}