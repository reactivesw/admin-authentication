package io.reactivesw.admin.authentication.application.service;

import io.reactivesw.admin.authentication.application.model.RoleDraft;
import io.reactivesw.admin.authentication.application.model.RoleView;
import io.reactivesw.admin.authentication.application.model.mapper.RoleMapper;
import io.reactivesw.admin.authentication.domain.model.Role;
import io.reactivesw.admin.authentication.domain.model.Scope;
import io.reactivesw.admin.authentication.domain.service.RoleService;
import io.reactivesw.admin.authentication.domain.service.ScopeService;
import io.reactivesw.admin.authentication.infrastructure.update.RoleUpdaterService;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.exception.ConflictException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Role application.
 */
@Service
public class RoleApplication {
  /**
   * LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(RoleApplication.class);

  /**
   * Scope service.
   */
  @Autowired
  private transient ScopeService scopeService;

  /**
   * Role service.
   */
  @Autowired
  private transient RoleService roleService;

  /**
   * Role updater service.
   */
  @Autowired
  private transient RoleUpdaterService roleUpdaterService;

  /**
   * Create Role from draft.
   *
   * @param draft RoleDraft
   * @return RoleView
   */
  public RoleView create(RoleDraft draft) {
    LOG.debug("Enter. draft: {}.", draft);

    Role role = RoleMapper.toEntity(draft);

    List<Scope> scopes = scopeService.getListById(draft.getScopes());
    role.setScopes(scopes);

    Role savedRole = roleService.save(role);

    RoleView roleView = RoleMapper.toModel(savedRole);

    LOG.debug("Exit. roleView: {}.", roleView);
    return roleView;
  }

  /**
   * Get RoleView by id.
   *
   * @param id String
   * @return RoleView
   */
  public RoleView getById(String id) {
    LOG.debug("Enter. id: {}.", id);

    Role role = roleService.getById(id);
    RoleView view = RoleMapper.toModel(role);

    LOG.debug("Enter. roleView: {}.", view);
    return view;
  }

  /**
   * Get all roleViews.
   *
   * @return List of roleViews
   */
  public List<RoleView> getAll() {
    LOG.debug("Enter.");

    List<Role> roles = roleService.getAll();
    List<RoleView> views = RoleMapper.toModel(roles);

    LOG.debug("Enter. roleView size: {}.", views.size());
    LOG.trace("RoleViewList: {}.", views);
    return views;
  }

  /**
   * Update a role
   *
   * @param id      String
   * @param version integer
   * @param actions action list
   * @return Role view
   */
  public RoleView update(String id, Integer version, List<UpdateAction> actions) {
    LOG.debug("Enter. id: {}, version: {}, actions: {}.", id, version, actions);

    Role valueInDb = roleService.getById(id);
    LOG.trace("Data in db: {}", valueInDb);
    checkVersion(version, valueInDb.getVersion());

    actions.stream().forEach(
        action -> roleUpdaterService.handle(valueInDb, action)
    );

    Role savedValue = roleService.save(valueInDb);
    RoleView view = RoleMapper.toModel(savedValue);

    LOG.debug("Exit. roleView: {}.", view);
    return view;
  }

  /**
   * Check the version.
   *
   * @param inputVersion Integer
   * @param existVersion Integer
   */
  private void checkVersion(Integer inputVersion, Integer existVersion) {
    if (!inputVersion.equals(existVersion)) {
      LOG.debug("Role version is not correct.");
      throw new ConflictException("Role version is not correct.");
    }
  }
}

