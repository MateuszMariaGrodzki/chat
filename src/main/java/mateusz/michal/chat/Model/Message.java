package mateusz.michal.chat.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "message_date")
    private Date messageDate;

    @Column(name = "content")
    private String content;
}
