package io.reactivesw.admin.authentication.application.model.action;

import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateActionUtils;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Change admin password
 */
@Data
public class ChangeAdminPassword implements UpdateAction {

  /**
   * Old password.
   */
  @NotNull
  private String oldPassword;

  /**
   * New password.
   */
  @NotNull
  private String newPassword;

  /**
   * Get action name.
   *
   * @return String
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.CHANGE_ADMIN_PASSWORD;
  }
}
