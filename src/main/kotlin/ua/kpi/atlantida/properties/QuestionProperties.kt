package ua.kpi.atlantida.properties

class QuestionProperties : MyProperties(FILE_NAME) {

    private companion object {
        private val FILE_NAME = "questions/ua.properties"
        private val PROP_NAME = "name"
        private val PROP_REQUEST_NAME = "request_name"
        private val PROP_LEVEL = "level"
        private val PROP_LEVEL_ERROR = "level_error"
        private val PROP_FACULTY = "faculty"
        private val PROP_FACULTY_ERROR = "faculty_error"
        private val PROP_SWIMMING = "swimming"
        private val PROP_SWIMMING_ERROR = "swimming_error"
        private val PROP_SWIMMING_LEVEL = "swimming_level"
        private val PROP_SWIMMING_LEVEL_ERROR = "swimming_level_error"
        private val PROP_SWIMMING_RATING = "swimming_rating"
        private val PROP_PHONE = "phone"
        private val PROP_REQUEST_PHONE = "request_phone"
        private val PROP_PHONE_ERROR = "phone_error"
        private val PROP_EMAIL = "email"
        private val PROP_EMAIL_ERROR = "email_error"
        private val PROP_PROFILE = "profile"
        private val PROP_MOTIVATION = "motivation"
        private val PROP_MARKETING = "marketing"
        private val PROP_LEVELS = "levels"
        private val PROP_SWIMMING_LEVELS = "swimming_levels"
        private val PROP_FACULTIES = "faculties"
        private val PROP_YES = "yes"
        private val PROP_NO = "no"
    }

    val name = readProperties(PROP_NAME)
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
    val levels = readProperties(PROP_LEVELS).split(",")
    val swimmingLevels = readProperties(PROP_SWIMMING_LEVELS).split(",")
    val faculties = readProperties(PROP_FACULTIES).split(",")
    val yes = readProperties(PROP_YES)
    val no = readProperties(PROP_NO)
}