package io.reactivesw.admin.authentication.domain.service.update;

import io.reactivesw.admin.authentication.application.model.action.SetAdminRoles;
import io.reactivesw.admin.authentication.domain.model.Admin;
import io.reactivesw.admin.authentication.domain.model.Role;
import io.reactivesw.admin.authentication.domain.service.RoleService;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Set admin roles.
 */
@Service(value = UpdateActionUtils.SET_ADMIN_ROLES)
public class SetAdminRolesService implements Updater<Admin, UpdateAction> {

  /**
   * Role service.
   */
  @Autowired
  private transient RoleService roleService;

  /**
   * Set admin's roles.
   *
   * @param admin        Admin
   * @param updateAction Action
   */
  @Override
  public void handle(Admin admin, UpdateAction updateAction) {
    SetAdminRoles setAdminRoles = (SetAdminRoles) updateAction;
    List<Role> roles = roleService.getListById(setAdminRoles.getRoles());
    admin.setRoles(roles);
    // TODO update the login admin's scope
  }
}
