package io.reactivesw.admin.authentication.infrastructure.repository;

import io.reactivesw.admin.authentication.domain.model.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Scope repository.
 */
@Repository
public interface ScopeRepository extends JpaRepository<Scope, String>,
    CrudRepository<Scope, String> {
}
