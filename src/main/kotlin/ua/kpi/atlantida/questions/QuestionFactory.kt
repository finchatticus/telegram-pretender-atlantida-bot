package ua.kpi.atlantida.questions

import ua.kpi.atlantida.model.Pretender
import ua.kpi.atlantida.questions.impl.*

class QuestionFactory {

    fun create(pretender: Pretender): Question {
        return when {
            pretender.name == null -> NameQuestion()
            pretender.level == null -> LevelQuestion()
            pretender.faculty == null -> FacultyQuestion()
            pretender.swimming == null -> SwimmingQuestion()
            pretender.swimmingLevel == null -> SwimmingLevelQuestion()
            pretender.phone == null -> PhoneQuestion()
            pretender.email == null -> EmailQuestion()
            pretender.profile == null -> ProfileQuestion()
            pretender.motivation == null -> MotivationQuestion()
            pretender.marketing == null -> MarketingQuestion()
            else -> ThanksQuestion()
        }
    }

}
