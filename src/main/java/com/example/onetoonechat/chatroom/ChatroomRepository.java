package com.example.onetoonechat.chatroom;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
    Optional<Chatroom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
