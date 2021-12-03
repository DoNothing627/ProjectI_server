package projectI.demo.server.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projectI.demo.server.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
