package com.example.testapp.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "message_id")
    private UUID messageID;

    @Column(name = "text", columnDefinition = "TEXT")
    private String message;

    @ManyToOne(optional = true, cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(updatable = false)
    private LocalDateTime createdDate;

    public Message() {
    }

    // сразу записываем дату
    @PrePersist
    protected void onCreate()
    {
        this.createdDate = LocalDateTime.now();
    }

}
