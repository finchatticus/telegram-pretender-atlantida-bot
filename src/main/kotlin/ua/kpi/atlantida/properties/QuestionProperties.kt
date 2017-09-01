package ua.kpi.atlantida.properties

class QuestionProperties : MyProperties(FILE_NAME) {

    private companion object {
        private val FILE_NAME = "questions/ua.properties"
        private val PROP_SURNAME = "surname"
        private val PROP_NAME = "name"
        private val PROP_PATRONYM = "patronym"
        private val PROP_LEVEL = "level"
        private val PROP_FACULTY = "faculty"
        private val PROP_SWIMMING = "swimming"
        private val PROP_SWIMMING_LEVEL = "swimming_level"
        private val PROP_PHONE = "phone"
        private val PROP_EMAIL = "email"
        private val PROP_PROFILE = "profile"
        private val PROP_HOBBIES = "hobbies"
        private val PROP_MOTIVATION = "motivation"
        private val PROP_MARKETING = "marketing"
        private val PROP_LEVELS = "levels"
        private val PROP_SWIMMING_LEVELS = "swimming_levels"
        private val PROP_YES = "yes"
        private val PROP_NO = "no"
    }

    val surname: String
        get() = readProperties().getProperty(PROP_SURNAME) ?: throw RuntimeException("surname is not set in $FILE_NAME")

    val name: String
        get() = readProperties().getProperty(PROP_NAME) ?: throw RuntimeException("name is not set in $FILE_NAME")

    val patronym: String
        get() = readProperties().getProperty(PROP_PATRONYM) ?: throw RuntimeException("patronym is not set in $FILE_NAME")

    val level: String
        get() = readProperties().getProperty(PROP_LEVEL) ?: throw RuntimeException("level is not set in $FILE_NAME")

    val faculty: String
        get() = readProperties().getProperty(PROP_FACULTY) ?: throw RuntimeException("faculty is not set in $FILE_NAME")

    val swimming: String
        get() = readProperties().getProperty(PROP_SWIMMING) ?: throw RuntimeException("swimming is not set in $FILE_NAME")

    val swimmingLevel: String
        get() = readProperties().getProperty(PROP_SWIMMING_LEVEL) ?: throw RuntimeException("swimming_level is not set in $FILE_NAME")

    val phone: String
        get() = readProperties().getProperty(PROP_PHONE) ?: throw RuntimeException("phone is not set in $FILE_NAME")

    val email: String
        get() = readProperties().getProperty(PROP_EMAIL) ?: throw RuntimeException("email is not set in $FILE_NAME")

    val profile: String
        get() = readProperties().getProperty(PROP_PROFILE) ?: throw RuntimeException("profile is not set in $FILE_NAME")

    val hobbies: String
        get() = readProperties().getProperty(PROP_HOBBIES) ?: throw RuntimeException("hobbies is not set in $FILE_NAME")

    val motivation: String
        get() = readProperties().getProperty(PROP_MOTIVATION) ?: throw RuntimeException("motivation is not set in $FILE_NAME")

    val marketing: String
        get() = readProperties().getProperty(PROP_MARKETING) ?: throw RuntimeException("marketing is not set in $FILE_NAME")

    val levels: List<String>
        get() {
            val levelsStr = readProperties().getProperty(PROP_LEVELS) ?: throw RuntimeException("levels is not set in $FILE_NAME")
            return levelsStr.split(",")
        }

    val swimmingLevels: List<String>
        get() {
            val swimmingLevelsStr = readProperties().getProperty(PROP_SWIMMING_LEVELS) ?: throw RuntimeException("swimming_levels is not set in $FILE_NAME")
            return swimmingLevelsStr.split(",")
        }

    val yes: String
        get() = readProperties().getProperty(PROP_YES) ?: throw RuntimeException("yes is not set in $FILE_NAME")

    val no: String
        get() = readProperties().getProperty(PROP_NO) ?: throw RuntimeException("no is not set in $FILE_NAME")
}