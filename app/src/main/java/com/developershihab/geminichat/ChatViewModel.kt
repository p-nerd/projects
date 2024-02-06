package com.developershihab.geminichat

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developershihab.geminichat.data.Chat
import com.developershihab.geminichat.data.ChatData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {
    private val _chatState = MutableStateFlow(ChatState());
    val chatState = _chatState.asStateFlow()
    fun onEvent(event: ChatUIEvent) {
        when (event) {
            is ChatUIEvent.SendPrompt -> {
                if (event.prompt.isNotEmpty()) {
                   addPrompt(event.prompt, event.bitmap)
                    if (event.bitmap != null){
                        getResponseWithBitmap(event.prompt, event.bitmap)
                    } else {
                        getResponse(event.prompt);
                    }
                }
            }
            is ChatUIEvent.UpdatePrompt ->{
                _chatState.update {
                    it.copy(prompt = event.newPrompt)
                }
            }
        }
    }
    private fun addPrompt(prompt: String, bitmap: Bitmap?) {
        _chatState.update {
            it.copy(
                chatList = it.chatList.toMutableList().apply {
                    add(0, Chat(prompt, bitmap, true))
                },
                prompt = "",
                bitmap = null,
            )
        }
    }

    private fun getResponse(prompt: String) {
        viewModelScope.launch {
            val chat = ChatData.getResponse(prompt);
            _chatState.update {
                it.copy(
                    chatList = it.chatList.toMutableList().apply {
                        add(0, chat)
                    },
                )
            }
        }
    }

    private fun getResponseWithBitmap(prompt: String, bitmap: Bitmap) {
        viewModelScope.launch {
            val chat = ChatData.getResponse(prompt, bitmap);
            _chatState.update {
                it.copy(
                    chatList = it.chatList.toMutableList().apply {
                        add(0, chat)
                    },
                )
            }
        }
    }
}