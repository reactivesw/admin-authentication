package io.reactivesw.admin.authentication.infrastructure.repository;

import io.reactivesw.admin.authentication.domain.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Admin repository.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, String>,
    CrudRepository<Admin, String> {
}
