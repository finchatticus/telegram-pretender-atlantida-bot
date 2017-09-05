package ua.kpi.atlantida.validator

import java.util.*

class ValidatorComposer<T>(vararg validators: Validator<T>) : Validator<T> {
    private var validators: List<Validator<T>> = Arrays.asList(*validators)
    private var description: String = "Error"

    override fun isValid(value: T): Boolean {
        for (validator in validators) {
            if (!validator.isValid(value)) {
                description = validator.getDescription()
                return false
            }
        }
        return true
    }

    override fun getDescription() = description
}