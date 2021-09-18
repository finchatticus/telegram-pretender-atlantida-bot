package ua.kpi.atlantida.properties

private const val FILE_NAME = "questions/ua.properties"
private const val PROP_NAME = "name"
private const val PROP_NAME_ERROR = "name_error"
private const val PROP_LEVEL = "level"
private const val PROP_LEVEL_ERROR = "level_error"
private const val PROP_FACULTY = "faculty"
private const val PROP_FACULTY_ERROR = "faculty_error"
private const val PROP_SWIMMING_RATING = "swimming_rating"
private const val PROP_PHONE = "phone"
private const val PROP_REQUEST_PHONE = "request_phone"
private const val PROP_PHONE_ERROR = "phone_error"
private const val PROP_EMAIL = "email"
private const val PROP_EMAIL_ERROR = "email_error"
private const val PROP_PROFILE = "profile"
private const val PROP_MOTIVATION = "motivation"
private const val PROP_MARKETING = "marketing"
private const val PROP_MARKETING_OPTIONS = "marketing_options"
private const val PROP_LEVELS = "levels"
private const val PROP_SWIMMING_LEVELS = "swimming_levels"
private const val PROP_FACULTIES = "faculties"
private const val PROP_YES = "yes"
private const val PROP_NO = "no"
private const val PROP_THANKS = "thanks"
private const val PROP_ERROR = "error"

class QuestionProperties : MyProperties(FILE_NAME) {
    val name = readProperties(PROP_NAME)
    val nameError = readProperties(PROP_NAME_ERROR)
    val level = readProperties(PROP_LEVEL)
    val levelError = readProperties(PROP_LEVEL_ERROR)
    val faculty = readProperties(PROP_FACULTY)
    val facultyError = readProperties(PROP_FACULTY_ERROR)
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
    val faculties = readProperties(PROP_FACULTIES).split(",")
    val thanks = readProperties(PROP_THANKS)
    val error = readProperties(PROP_ERROR)
}