package com.example.onetoonechat.chatroom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatroomService {

    private final ChatroomRepository chatroomRepository;

    public Optional<String> getChatroomId(
            String senderId,
            String recipientId,
            boolean createNewRoomIfNotExists
    ){
        return chatroomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(Chatroom::getChatId)
                .or(()->{
                    if(createNewRoomIfNotExists){
                        var chatId = createCHatId(senderId, recipientId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }

    private String createCHatId(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId);
        Chatroom senderRecipient = Chatroom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        Chatroom recipientSender = Chatroom.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        chatroomRepository.save(senderRecipient);
        chatroomRepository.save(recipientSender);

        return chatId;
    }
}
