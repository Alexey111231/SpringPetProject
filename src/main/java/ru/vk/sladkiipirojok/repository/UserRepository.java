package ru.vk.sladkiipirojok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vk.sladkiipirojok.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
