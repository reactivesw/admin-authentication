package io.reactivesw.admin.authentication.domain.service.update;

import io.reactivesw.admin.authentication.application.model.action.SetRoleName;
import io.reactivesw.admin.authentication.domain.model.Role;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Set role name.
 */
@Service(value = UpdateActionUtils.SET_ROLE_NAME)
public class SetRoleNameService implements Updater<Role, UpdateAction> {

  /**
   * Set role's name.
   *
   * @param role         role
   * @param updateAction Action
   */
  @Override
  public void handle(Role role, UpdateAction updateAction) {
    SetRoleName setRoleName = (SetRoleName) updateAction;
    role.setRoleName(setRoleName.getScopeName());
  }
}
