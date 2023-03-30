package edu.maszek.brainpowerquiz.repository;

import edu.maszek.brainpowerquiz.role.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
