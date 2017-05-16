package io.reactivesw.admin.authentication.domain.service.update;

import io.reactivesw.admin.authentication.application.model.action.SetScopePermissions;
import io.reactivesw.admin.authentication.application.model.mapper.PermissionMapper;
import io.reactivesw.admin.authentication.domain.model.Scope;
import io.reactivesw.admin.authentication.infrastructure.enums.Permission;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Set scope permissions.
 */
@Service(value = UpdateActionUtils.SET_SCOPE_PERMISSIONS)
public class SetScopePermissionService implements Updater<Scope, UpdateAction> {

  /**
   * Set scope's permissions.
   *
   * @param scope        scope
   * @param updateAction Action
   */
  @Override
  public void handle(Scope scope, UpdateAction updateAction) {
    SetScopePermissions setScopePermissions = (SetScopePermissions) updateAction;
    List<Permission> permissions = PermissionMapper.toEntity(setScopePermissions.getPermissions());
    scope.setPermissions(permissions);
    // TODO update the login admin's scope
  }
}
