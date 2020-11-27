package io.github.moh_mohsin.todoapp.data

import android.content.Context
import androidx.annotation.StringRes

sealed class Message {
    data class Res(@StringRes val stringId: Int): Message()
    data class Raw(val msg: String): Message()
}


fun Message.get(context: Context): String =
    when(this){
        is Message.Res -> context.getString(stringId)
        is Message.Raw -> msg
    }

fun Message.getRawOrDefault() = if (this is Message.Raw) this.msg else "An unknown error occurred"
