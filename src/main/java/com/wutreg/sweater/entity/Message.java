package com.wutreg.sweater.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не должно быть пустым!")
    @Length(max = 2048, message = "Message too long (more to 2kB)")
    private String text;

    @NotBlank(message = "Поле не должно быть пустым!")
    @Length(max = 255, message = "Message too long (more to 255)")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", foreignKey=@ForeignKey(name = "fk_messages_users"))
    private User author;

    private String filename;

    public Message(String text, String tag, User author) {
        this.text = text;
        this.tag = tag;
        this.author = author;
    }
}
