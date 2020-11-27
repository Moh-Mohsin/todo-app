package io.github.moh_mohsin.todoapp.data

import io.github.moh_mohsin.todoapp.R

sealed class AppException(
    open val msg: Message = Message.Res(R.string.error_occurred)
) : Exception()

class NetworkException : AppException()
class UnknownException : AppException(Message.Res(R.string.error_unknown))
class NotFoundException : AppException(Message.Res(R.string.error_not_found))