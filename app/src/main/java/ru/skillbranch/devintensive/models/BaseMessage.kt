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
                "image" -> ImageMessage("$id", from, chat, isIncoming, date, payload.toString())
                else -> TextMessage("$id", from, chat, isIncoming, date, payload.toString())
            }

        }
    }
}