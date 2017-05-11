package io.reactivesw.admin.authentication.domain.service;

import io.reactivesw.admin.authentication.domain.model.Role;
import io.reactivesw.admin.authentication.infrastructure.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Role service, used to manage roles.
 */
@Service
public class RoleService {

  /**
   * LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);

  /**
   * Role repository.
   */
  @Autowired
  private transient RoleRepository roleRepository;

  /**
   * Create module with sample.
   *
   * @param role Role
   * @return Role
   */
  public Role create(Role role) {
    LOG.debug("Enter. role: {}.", role);

    Role savedRole = this.roleRepository.save(role);

    LOG.debug("Exit. role: {}.", savedRole);

    return savedRole;
  }

}
