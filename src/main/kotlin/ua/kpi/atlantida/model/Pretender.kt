package ua.kpi.atlantida.model

data class Pretender(
        var chatId: Long = -1,
        var name: String? = null,
        var level: String? = null,
        var faculty: String? = null,
        var swimming: String? = null,
        var swimmingLevel: String? = null,
        var mySwimmingRating: String? = null,
        var phone: String? = null,
        var email: String? = null,
        var profile: String? = null,
        var motivation: String? = null,
        var marketing: String? = null
)