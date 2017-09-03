package ua.kpi.atlantida.properties

class QuestionProperties : MyProperties(FILE_NAME) {

    private companion object {
        private val FILE_NAME = "questions/ua.properties"
        private val PROP_NAME = "name"
        private val PROP_LEVEL = "level"
        private val PROP_FACULTY = "faculty"
        private val PROP_SWIMMING = "swimming"
        private val PROP_SWIMMING_LEVEL = "swimming_level"
        private val PROP_SWIMMING_RATING = "swimming_rating"
        private val PROP_PHONE = "phone"
        private val PROP_EMAIL = "email"
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
    val level = readProperties(PROP_LEVEL)
    val faculty = readProperties(PROP_FACULTY)
    val swimming = readProperties(PROP_SWIMMING)
    val swimmingLevel = readProperties(PROP_SWIMMING_LEVEL)
    val swimmingRating = readProperties(PROP_SWIMMING_RATING)
    val phone = readProperties(PROP_PHONE)
    val email = readProperties(PROP_EMAIL)
    val profile = readProperties(PROP_PROFILE)
    val motivation = readProperties(PROP_MOTIVATION)
    val marketing = readProperties(PROP_MARKETING)
    val levels = readProperties(PROP_LEVELS).split(",")
    val swimmingLevels = readProperties(PROP_SWIMMING_LEVELS).split(",")
    val faculties = readProperties(PROP_FACULTIES).split(",")
    val yes = readProperties(PROP_YES)
    val no = readProperties(PROP_NO)
}