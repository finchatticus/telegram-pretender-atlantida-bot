package ua.kpi.atlantida.validator.impl

import ua.kpi.atlantida.validator.Validator

class PhoneValidator(private val error: String) : Validator<String> {

    private companion object {
        const val PHONE_REGEX = "\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|" +
                "2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|" +
                "4[987654310]|3[98643210]|2[70]|7|1)" +
                "\\W*\\d\\W*\\d\\W*\\d\\W*\\d\\W*\\d\\W*\\d\\W*\\d\\W*\\d\\W*(\\d{1,2})\$"
        const val PHONE_REGEX_1 = "\\d{10}"
        const val PHONE_REGEX_2 = "\\d{12}"
    }

    override fun isValid(value: String) = value.matches(Regex(PHONE_REGEX)) || (value.matches(Regex(PHONE_REGEX_1))) || (value.matches(Regex(PHONE_REGEX_2)))

    override fun getDescription() = error
}