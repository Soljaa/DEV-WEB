package daniel.org.Finance_app_api.repository;

import daniel.org.Finance_app_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
