package ua.kpi.atlantida.properties

class QuestionProperties : MyProperties(FILE_NAME) {

    private companion object {
        const val FILE_NAME = "questions/ua.properties"
        const val PROP_NAME = "name"
        const val PROP_NAME_ERROR = "name_error"
        const val PROP_REQUEST_NAME = "request_name"
        const val PROP_LEVEL = "level"
        const val PROP_LEVEL_ERROR = "level_error"
        const val PROP_FACULTY = "faculty"
        const val PROP_FACULTY_ERROR = "faculty_error"
        const val PROP_SWIMMING = "swimming"
        const val PROP_SWIMMING_ERROR = "swimming_error"
        const val PROP_SWIMMING_LEVEL = "swimming_level"
        const val PROP_SWIMMING_LEVEL_ERROR = "swimming_level_error"
        const val PROP_SWIMMING_RATING = "swimming_rating"
        const val PROP_PHONE = "phone"
        const val PROP_REQUEST_PHONE = "request_phone"
        const val PROP_PHONE_ERROR = "phone_error"
        const val PROP_EMAIL = "email"
        const val PROP_EMAIL_ERROR = "email_error"
        const val PROP_PROFILE = "profile"
        const val PROP_MOTIVATION = "motivation"
        const val PROP_MARKETING = "marketing"
        const val PROP_MARKETING_OPTIONS = "marketing_options"
        const val PROP_LEVELS = "levels"
        const val PROP_SWIMMING_LEVELS = "swimming_levels"
        const val PROP_FACULTIES = "faculties"
        const val PROP_YES = "yes"
        const val PROP_NO = "no"
        const val PROP_THANKS = "thanks"
        const val PROP_ERROR = "error"
    }

    val name = readProperties(PROP_NAME)
    val nameError = readProperties(PROP_NAME_ERROR)
    val requestName = readProperties(PROP_REQUEST_NAME)
    val level = readProperties(PROP_LEVEL)
    val levelError = readProperties(PROP_LEVEL_ERROR)
    val faculty = readProperties(PROP_FACULTY)
    val facultyError = readProperties(PROP_FACULTY_ERROR)
    val swimming = readProperties(PROP_SWIMMING)
    val swimmingError = readProperties(PROP_SWIMMING_ERROR)
    val swimmingLevel = readProperties(PROP_SWIMMING_LEVEL)
    val swimmingLevelError = readProperties(PROP_SWIMMING_LEVEL_ERROR)
    val swimmingRating = readProperties(PROP_SWIMMING_RATING)
    val phone = readProperties(PROP_PHONE)
    val requestPhone = readProperties(PROP_REQUEST_PHONE)
    val phoneError = readProperties(PROP_PHONE_ERROR)
    val email = readProperties(PROP_EMAIL)
    val emailError = readProperties(PROP_EMAIL_ERROR)
    val profile = readProperties(PROP_PROFILE)
    val motivation = readProperties(PROP_MOTIVATION)
    val marketing = readProperties(PROP_MARKETING)
    val marketingOptions = readProperties(PROP_MARKETING_OPTIONS).split(",")
    val levels = readProperties(PROP_LEVELS).split(",")
    val swimmingLevels = readProperties(PROP_SWIMMING_LEVELS).split(",")
    val faculties = readProperties(PROP_FACULTIES).split(",")
    val yes = readProperties(PROP_YES)
    val no = readProperties(PROP_NO)
    val thanks = readProperties(PROP_THANKS)
    val error = readProperties(PROP_ERROR)
}