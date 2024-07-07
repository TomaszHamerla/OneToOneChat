package com.example.onetoonechat.chat;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatNotification {

    private long id;
    private String senderId;
    private String recipientId;
    private String content;
}
