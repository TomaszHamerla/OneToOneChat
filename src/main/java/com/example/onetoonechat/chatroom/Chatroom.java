package com.example.onetoonechat.chatroom;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chatrooms")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chatroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String chatId;
    private String senderId;
    private String recipientId;
}
