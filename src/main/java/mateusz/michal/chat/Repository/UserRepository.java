package mateusz.michal.chat.Repository;

import mateusz.michal.chat.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User , Integer> {
    User findByEmail(String email);
    User findByName(String name);
    User findById(int id);
    User findBySlug(String slug);
}
