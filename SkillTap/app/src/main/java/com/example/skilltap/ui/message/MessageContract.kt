package com.example.skilltap.ui.message

import com.example.skilltap.R

object MessageContract {


    data class SenderMessageState(
        var message:String = "",
        var gone:Boolean = true,
        var textMessageGone:Boolean = true,
        var fileMessageGone:Boolean = true

    )

    data class ReceiverMessageState(
        var message:String = "",
        var gone:Boolean = true,
        var textMessageGone:Boolean = true,
        var fileMessageGone:Boolean = true

    )
}