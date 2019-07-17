package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    if (isKeyboardClosed()) return
    val view = currentFocus ?: return
    val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.isKeyboardOpen (): Boolean {
    val visibleBounds = Rect()
    val view = window.decorView
    view.getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = view.height - (visibleBounds.height())
    return heightDiff > 100
}

fun Activity.isKeyboardClosed():Boolean = !isKeyboardOpen()
