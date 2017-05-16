package io.reactivesw.admin.authentication.domain.service.update;

import io.reactivesw.admin.authentication.application.model.action.SetRoleScopes;
import io.reactivesw.admin.authentication.domain.model.Role;
import io.reactivesw.admin.authentication.domain.model.Scope;
import io.reactivesw.admin.authentication.domain.service.ScopeService;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Set role scopes.
 */
@Service(value = UpdateActionUtils.SET_ROLE_SCOPES)
public class SetRoleScopesService implements Updater<Role, UpdateAction> {

  /**
   * Scope service.
   */
  @Autowired
  private transient ScopeService scopeService;

  /**
   * Set role's scopes.
   *
   * @param role         role
   * @param updateAction Action
   */
  @Override
  public void handle(Role role, UpdateAction updateAction) {
    SetRoleScopes setRoleScopes = (SetRoleScopes) updateAction;
    List<Scope> scopes = scopeService.getListById(setRoleScopes.getScopes());
    role.setScopes(scopes);
    // TODO update the login admin's scope
  }
}
