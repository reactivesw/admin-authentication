package io.reactivesw.admin.authentication.application.service;

import io.reactivesw.admin.authentication.application.model.AdminDraft;
import io.reactivesw.admin.authentication.application.model.AdminView;
import io.reactivesw.admin.authentication.application.model.ScopeView;
import io.reactivesw.admin.authentication.application.model.mapper.AdminMapper;
import io.reactivesw.admin.authentication.domain.model.Admin;
import io.reactivesw.admin.authentication.domain.model.Role;
import io.reactivesw.admin.authentication.domain.service.AdminService;
import io.reactivesw.admin.authentication.domain.service.RoleService;
import io.reactivesw.admin.authentication.infrastructure.update.AdminUpdaterService;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.exception.ConflictException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Admin application.
 */
@Service
public class AdminApplication {
  /**
   * LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(AdminApplication.class);

  /**
   * Admin service.
   */
  @Autowired
  private transient AdminService adminService;

  /**
   * Role service.
   */
  @Autowired
  private transient RoleService roleService;

  /**
   * Admin updater service.
   */
  @Autowired
  private transient AdminUpdaterService adminUpdaterService;

  /**
   * Create admin from draft.
   *
   * @param draft AdminDraft
   * @return AdminView
   */
  public AdminView create(AdminDraft draft) {
    LOG.debug("Enter. draft: {}.", draft);

    Admin admin = AdminMapper.toEntity(draft);

    List<Role> roles = roleService.getListById(draft.getRoles());
    admin.setRoles(roles);

    Admin savedAdmin = adminService.save(admin);

    AdminView adminView = AdminMapper.toModel(savedAdmin);

    LOG.debug("Exit. adminView: {}.", adminView);
    return adminView;
  }

  /**
   * Get AdminView by id.
   *
   * @param id String
   * @return AdminView
   */
  public AdminView getById(String id) {
    LOG.debug("Enter. id: {}.", id);

    Admin admin = adminService.getById(id);
    AdminView view = AdminMapper.toModel(admin);

    LOG.debug("Enter. adminView: {}.", view);
    return view;
  }

  /**
   * Get all admins.
   *
   * @return List of admin view
   */
  public List<AdminView> getAll() {
    LOG.debug("Enter.");

    List<Admin> admins = adminService.getAll();
    List<AdminView> views = AdminMapper.toModel(admins);

    LOG.debug("Enter. adminView size: {}.", views.size());
    LOG.trace("AdminViewList: {}.", views);
    return views;
  }

  /**
   * Update a admin
   *
   * @param id      String
   * @param version integer
   * @param actions action list
   * @return admin view
   */
  public AdminView update(String id, Integer version, List<UpdateAction> actions) {
    LOG.debug("Enter. id: {}, version: {}, actions: {}.", id, version, actions);

    Admin valueInDb = adminService.getById(id);
    LOG.trace("Data in db: {}", valueInDb);
    checkVersion(version, valueInDb.getVersion());

    actions.stream().forEach(
        action -> adminUpdaterService.handle(valueInDb, action)
    );

    Admin savedValue = adminService.save(valueInDb);
    AdminView view = AdminMapper.toModel(savedValue);

    LOG.debug("Exit. adminView: {}.", view);
    return view;
  }

  /**
   * Get all scopes from admin.
   *
   * @param adminId admin id
   * @return list of ScopeView
   */
  public List<ScopeView> getScopes(String adminId) {
    LOG.debug("Enter. adminId: {}.", adminId);
    AdminView adminView = getById(adminId);
    List<ScopeView> scopeViews = new ArrayList<>();
    adminView.getRoleViews().stream().forEach(
        roleView -> scopeViews.addAll(roleView.getScopeViews())
    );
    LOG.debug("Exit. scopeViews size: {}.", scopeViews.size());
    LOG.trace("ScopeViews: {}.", scopeViews);
    return scopeViews;
  }

  /**
   * Check the version.
   *
   * @param inputVersion Integer
   * @param existVersion Integer
   */
  private void checkVersion(Integer inputVersion, Integer existVersion) {
    if (!inputVersion.equals(existVersion)) {
      LOG.debug("Admin version is not correct.");
      throw new ConflictException("Admin version is not correct.");
    }
  }
}

