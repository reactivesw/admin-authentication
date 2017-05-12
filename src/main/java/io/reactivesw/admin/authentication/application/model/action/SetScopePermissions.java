package io.reactivesw.admin.authentication.application.model.action;

import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateActionUtils;
import lombok.Data;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * Set scope permissions
 */
@Data
public class SetScopePermissions implements UpdateAction {

  /**
   * List of permission.
   */
  @NotNull
  private List<String> permissions;

  /**
   * Get action name.
   *
   * @return String
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_SCOPE_PERMISSIONS;
  }
}
