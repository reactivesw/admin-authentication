package io.reactivesw.admin.authentication.application.model.action;

import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateActionUtils;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Update a module.
 */
@Data
public class UpdateModule implements UpdateAction {

  /**
   * Module name.
   */
  @NotNull
  private String moduleName;

  /**
   * Path of this module.
   */
  @NotNull
  private String path;

  /**
   * Get action name.
   *
   * @return String
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.UPDATE_MODULE;
  }
}
