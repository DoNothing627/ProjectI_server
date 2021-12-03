package projectI.demo.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import projectI.demo.server.repositories.CompanyRepository;
import projectI.demo.server.repositories.ProductRepository;
import projectI.demo.server.repositories.UserRepository;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final CompanyRepository companyRepository;

	@Autowired
	public ServerApplication(UserRepository userRepository, ProductRepository productRepository, CompanyRepository companyRepository) {
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.companyRepository = companyRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
