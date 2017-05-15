package io.reactivesw.admin.authentication.domain.service;

import io.reactivesw.admin.authentication.domain.model.Admin;
import io.reactivesw.admin.authentication.infrastructure.repository.AdminRepository;
import io.reactivesw.exception.AlreadyExistException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

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
  public Admin save(Admin admin) {
    LOG.debug("Enter. admin: {}.", admin);

    try {
      Admin savedAdmin = this.adminRepository.save(admin);

      LOG.debug("Exit. admin: {}.", savedAdmin);

      return savedAdmin;
    } catch (DataIntegrityViolationException ex) {
      ConstraintViolationException ee = (ConstraintViolationException) ex.getCause();
      String state = ee.getSQLState();
      // 23505 means sql some unique column already exist
      if ("23505".equals(state)) {
        throw new AlreadyExistException("Admin already exist.");
      } else {
        throw ex;
      }
    }
  }

  /**
   * Get admin by id.
   *
   * @param id String
   * @return Admin
   */
  public Admin getById(String id) {
    LOG.debug("Enter. id: {}.", id);

    Admin admin = this.adminRepository.findOne(id);

    LOG.debug("Exit. admin: {}.", admin);

    return admin;
  }

  /**
   * Find one by email.
   *
   * @param email String
   * @return Admin
   */
  public Admin getByEmail(String email) {
    LOG.debug("Enter. email: {}.", email);

    Admin admin = this.adminRepository.findOneByEmail(email);

    LOG.debug("Exit. admin: {}.", admin);

    return admin;
  }

  /**
   * Get all admin from database.
   *
   * @return List of admin
   */
  public List<Admin> getAll() {
    LOG.debug("Enter.");

    List<Admin> admins = adminRepository.findAll();

    LOG.debug("Exit. adminSize: {}.", admins.size());
    LOG.trace("AdminList: {}.", admins);

    return admins;
  }

}
