package ru.skillbranch.devintensive.models

class Bender(
    var status: Status = Status.NORMAL,
    var question: Question = Question.NAME

) {

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {

        val invalidText = question.validate(answer)

        if (null != invalidText) {
            return (invalidText + question.question) to status.color
        }

        if (question.answers.contains(answer.toLowerCase())) {
            question = question.nextQuestion()
            return "Отлично - ты справился\n${question.question}" to status.color
        }

        status = status.nextStatus()

        return if (status == Status.NORMAL) {
            question = Question.NAME
            "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
        } else
            "Это неправильный ответ\n${question.question}" to status.color
    }


    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (ordinal < values().lastIndex) {
                values()[ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun validate(answer: String): String? {
                return if (!answer[0].isUpperCase())
                    "Имя должно начинаться с заглавной буквы\n"
                else null
            }
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun validate(answer: String): String? {
                return if (!answer[0].isLowerCase())
                    "Профессия должна начинаться со строчной буквы\n"
                else null
            }
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "iron", "metal", "wood")) {
            override fun validate(answer: String): String? {
                return if (answer.contains(Regex("\\d")))
                    "Материал не должен содержать цифр\n"
                else null
            }
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun validate(answer: String): String? {
                return if (answer.contains(Regex("\\D")))
                    "Год моего рождения должен содержать только цифры\n"
                else null
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun validate(answer: String): String? {
                return if (!Regex("\\d{7}").matches(answer))
                    "Серийный номер содержит только цифры, и их 7\n"
                else null
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun validate(answer: String): String? = ""
        };

        fun nextQuestion(): Question = when (this) {
            NAME -> PROFESSION
            PROFESSION -> MATERIAL
            MATERIAL -> BDAY
            BDAY -> SERIAL
            SERIAL -> IDLE
            IDLE -> IDLE
        }

        abstract fun validate(answer: String): String?
    }
}