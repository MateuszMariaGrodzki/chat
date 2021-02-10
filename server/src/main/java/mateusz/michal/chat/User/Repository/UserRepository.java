package mateusz.michal.chat.User.Repository;

import mateusz.michal.chat.User.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User , Integer>, PagingAndSortingRepository<User, Integer> {
    User findByEmail(String email);
    User findByName(String name);
    Page<User> findAll(Pageable pageable);
    User findBySlug(String slug);
}
