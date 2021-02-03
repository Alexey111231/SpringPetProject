package ru.vk.sladkiipirojok.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vk.sladkiipirojok.model.Message;

import java.util.List;

public interface MessagesRepository extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
