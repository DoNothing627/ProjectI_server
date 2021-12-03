package projectI.demo.server.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import projectI.demo.server.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
