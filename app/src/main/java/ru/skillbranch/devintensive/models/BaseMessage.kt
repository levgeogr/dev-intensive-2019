package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage(): String

    companion object AbstractFactory {
        var id = -1

        fun makeMessage(
            from: User?,
            chat: Chat,
            date: Date = Date(),
            type: String = "text",
            payload: Any?,
            isIncoming: Boolean = false
        ): BaseMessage {
            id++
            return when (type) {
                "image" -> ImageMessage("$id", from, chat, date = date, image = payload as String, isIncoming = isIncoming)
                "text" -> TextMessage("$id", from, chat, date = date, text = payload as String, isIncoming = isIncoming)
                else -> throw IllegalStateException()
            }

        }
    }
}