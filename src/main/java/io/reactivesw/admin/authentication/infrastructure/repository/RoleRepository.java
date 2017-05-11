package io.reactivesw.admin.authentication.infrastructure.repository;

import io.reactivesw.admin.authentication.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Role repository.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String>,
    CrudRepository<Role, String> {
}
