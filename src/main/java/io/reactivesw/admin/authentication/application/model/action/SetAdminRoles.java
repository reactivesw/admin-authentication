package io.reactivesw.admin.authentication.application.model.action;

import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateActionUtils;
import lombok.Data;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * Set admin roles.
 */
@Data
public class SetAdminRoles implements UpdateAction {

  /**
   * List of roles.
   */
  @NotNull
  private List<String> roles;

  /**
   * Get action name.
   *
   * @return String
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_ADMIN_ROLES;
  }
}
