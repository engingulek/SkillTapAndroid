package com.example.skilltap.ui.message

data class MessageData(var id:Int,
                   var message:String,
                   var messageType:MessageType,
                   var date:String) {
}

enum class MessageType {
    TEXT,
    FILE
}