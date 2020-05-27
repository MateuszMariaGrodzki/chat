package mateusz.michal.chat.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private int id;

    @Column(name = "message_date")
    private Date messageDate;

    @Column(name = "content")
    private String content;
}
