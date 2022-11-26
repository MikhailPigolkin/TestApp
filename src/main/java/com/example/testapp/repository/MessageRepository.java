package com.example.testapp.repository;

import com.example.testapp.entity.Message;
import com.example.testapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findAllByUserOrderByCreatedDateDesc (User user);

}
