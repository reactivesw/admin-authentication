package io.reactivesw.admin.authentication.application.service;

import io.reactivesw.admin.authentication.application.model.ScopeDraft;
import io.reactivesw.admin.authentication.application.model.ScopeView;
import io.reactivesw.admin.authentication.application.model.mapper.PermissionMapper;
import io.reactivesw.admin.authentication.application.model.mapper.ScopeMapper;
import io.reactivesw.admin.authentication.domain.model.Module;
import io.reactivesw.admin.authentication.domain.model.Scope;
import io.reactivesw.admin.authentication.domain.service.ModuleService;
import io.reactivesw.admin.authentication.domain.service.ScopeService;
import io.reactivesw.admin.authentication.infrastructure.enums.Permission;
import io.reactivesw.admin.authentication.infrastructure.update.ScopeUpdaterService;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.exception.ConflictException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Scope application.
 */
@Service
public class ScopeApplication {
  /**
   * LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ScopeApplication.class);

  /**
   * Module service.
   */
  @Autowired
  private transient ModuleService moduleService;

  /**
   * Scope service.
   */
  @Autowired
  private transient ScopeService scopeService;

  /**
   * Scope updater service.
   */
  @Autowired
  private transient ScopeUpdaterService scopeUpdaterService;

  /**
   * Create scope from draft.
   *
   * @param draft ScopeDraft
   * @return ScopeView
   */
  public ScopeView create(ScopeDraft draft) {
    LOG.debug("Enter. draft: {}.", draft);

    Scope scope = ScopeMapper.toEntity(draft);

    List<Permission> permissions = PermissionMapper.toModel(draft.getPermissions());
    scope.setPermissions(permissions);

    List<Module> modules = moduleService.getListById(draft.getModules());
    scope.setModules(modules);

    Scope savedScope = scopeService.save(scope);

    ScopeView scopeView = ScopeMapper.toModel(savedScope);

    LOG.debug("Exit. scopeView: {}.", scopeView);
    return scopeView;
  }

  /**
   * Get ScopeView by id.
   *
   * @param id String
   * @return ScopeView
   */
  public ScopeView getById(String id) {
    LOG.debug("Enter. id: {}.", id);

    Scope scope = scopeService.getById(id);
    ScopeView view = ScopeMapper.toModel(scope);

    LOG.debug("Enter. scopeView: {}.", view);
    return view;
  }

  /**
   * Get all scopes.
   *
   * @return List of scope view
   */
  public List<ScopeView> getAll() {
    LOG.debug("Enter.");

    List<Scope> scopes = scopeService.getAll();
    List<ScopeView> views = ScopeMapper.toModel(scopes);

    LOG.debug("Enter. scopeView size: {}.", views.size());
    LOG.trace("ScopeViewList: {}.", views);
    return views;
  }

  /**
   * Update a scope
   *
   * @param id      String
   * @param version integer
   * @param actions action list
   * @return Scope view
   */
  public ScopeView update(String id, Integer version, List<UpdateAction> actions) {
    LOG.debug("Enter. id: {}, version: {}, actions: {}.", id, version, actions);

    Scope valueInDb = scopeService.getById(id);
    LOG.trace("Data in db: {}", valueInDb);
    checkVersion(version, valueInDb.getVersion());

    actions.stream().forEach(
        action -> scopeUpdaterService.handle(valueInDb, action)
    );

    Scope savedValue = scopeService.save(valueInDb);
    ScopeView view = ScopeMapper.toModel(savedValue);

    LOG.debug("Exit. updatedView: {}.", view);
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
      LOG.debug("Scope version is not correct.");
      throw new ConflictException("Scope version is not correct.");
    }
  }
}

