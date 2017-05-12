package io.reactivesw.admin.authentication.application.model.action;

import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateActionUtils;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Set scope name
 */
@Data
public class SetScopeName implements UpdateAction {

  /**
   * Module name.
   */
  @NotNull
  private String scopeName;

  /**
   * Get action name.
   *
   * @return String
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_SCOPE_NAME;
  }
}
