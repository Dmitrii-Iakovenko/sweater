package com.wutreg.sweater.repository;

import com.wutreg.sweater.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByTag(String filter);

}
