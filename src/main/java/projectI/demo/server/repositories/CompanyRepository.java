package projectI.demo.server.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import projectI.demo.server.models.Company;

public interface CompanyRepository extends MongoRepository<Company, String> {
}
