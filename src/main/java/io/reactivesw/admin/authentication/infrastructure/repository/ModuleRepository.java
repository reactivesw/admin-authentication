package io.reactivesw.admin.authentication.infrastructure.repository;

import io.reactivesw.admin.authentication.domain.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Module repository.
 */
@Repository
public interface ModuleRepository extends JpaRepository<Module, String>,
    CrudRepository<Module, String> {
}
