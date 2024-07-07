package com.example.onetoonechat.chat;

import com.example.onetoonechat.chatroom.ChatroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatroomService chatroomService;

    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = chatroomService.getChatroomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), false)
                .orElseThrow();
        chatMessage.setChatId(chatId);
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatroomService.getChatroomId(senderId, recipientId, false);
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
