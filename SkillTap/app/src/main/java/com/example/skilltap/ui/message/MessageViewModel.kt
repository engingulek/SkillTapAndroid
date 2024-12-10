package com.example.skilltap.ui.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.http.Url

interface MessageViewModelInterface {
    var senderMessageState : LiveData<MessageContract.SenderMessageState>
    var receiverState : LiveData<MessageContract.ReceiverMessageState>
    var messageList : LiveData<List<MessageData>>
    fun onBindViewHolder(position:Int)
}


class MessageViewModel : ViewModel(), MessageViewModelInterface {
    private var _senderMessageState = MutableLiveData(MessageContract.SenderMessageState())
    override var senderMessageState  : LiveData<MessageContract.SenderMessageState> = _senderMessageState

    private  var _receiverState = MutableLiveData(MessageContract.ReceiverMessageState())
    override    var receiverState : LiveData<MessageContract.ReceiverMessageState> = _receiverState

    private var _messageDataList = MutableLiveData<List<MessageData>>(emptyList())
    override var messageList : LiveData<List<MessageData>> = _messageDataList

    init {
        fetchMessage()
    }
    private fun fetchMessage(){
        _messageDataList.value = listOf(
            MessageData(1,"hi",MessageType.TEXT,""),
            MessageData(1,"I want a app",MessageType.TEXT,""),
            MessageData(2,"ok",MessageType.TEXT,""),
            MessageData(2,"url",MessageType.FILE,""),
            MessageData(2,"realy good",MessageType.TEXT,""),
        )
    }


    override fun onBindViewHolder(position: Int) {

        _messageDataList.value?.let {
            val message = it[position]
            if (message.id == 1){
                if (message.messageType == MessageType.TEXT){
                  senderMessage(message.message,
                      false,
                      fileMessageGone = true,
                      textMessageGone = false
                  )
                }else{
                    senderMessage(message.message, false,
                        fileMessageGone = false,
                        textMessageGone = true
                    )
                }

                receiverMessage("",
                    gone = true,
                    fileMessageGone = true,
                    textMessageGone = true)
            }else{
                if (message.messageType == MessageType.TEXT){
                    receiverMessage(message.message,
                        false,
                        fileMessageGone = true,
                        textMessageGone = false
                    )
                }else{
                    receiverMessage(message.message, false,
                        fileMessageGone = false,
                        textMessageGone = true
                    )
                }

                senderMessage("",
                    gone = true,
                    fileMessageGone = true,
                    textMessageGone = true)

            }
        }
    }

    private fun senderMessage(text:String,
                                  gone:Boolean,
                                  fileMessageGone:Boolean,
                                  textMessageGone:Boolean){
        _senderMessageState.value = _senderMessageState.value?.copy(
            gone = gone,
            message = text,
            textMessageGone = textMessageGone,
            fileMessageGone = fileMessageGone
        )
    }

    private fun receiverMessage(text:String,gone:Boolean,
                                    fileMessageGone:Boolean
                                    ,textMessageGone:Boolean){
        _receiverState.value = _receiverState.value?.copy(
            gone = gone,
            message = text,
            textMessageGone = textMessageGone,
            fileMessageGone = fileMessageGone
        )
    }
}