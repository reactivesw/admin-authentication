package io.reactivesw.admin.authentication.domain.service;

import io.reactivesw.admin.authentication.domain.model.Admin;
import io.reactivesw.admin.authentication.infrastructure.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Admin service, used to manage admin's authentication info.
 */
@Service
public class AdminService {

  /**
   * LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(AdminService.class);

  /**
   * Admin repository.
   */
  @Autowired
  private transient AdminRepository adminRepository;

  /**
   * Create admin with sample.
   *
   * @param admin Admin
   * @return Admin
   */
  public Admin create(Admin admin) {
    LOG.debug("Enter. admin: {}.", admin);

    Admin savedAdmin = this.adminRepository.save(admin);

    LOG.debug("Exit. admin: {}.", savedAdmin);

    return savedAdmin;
  }

}
