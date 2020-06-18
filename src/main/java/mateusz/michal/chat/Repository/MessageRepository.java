package mateusz.michal.chat.Repository;

import mateusz.michal.chat.Model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message , Integer> {
    Message findById(int id);
}
