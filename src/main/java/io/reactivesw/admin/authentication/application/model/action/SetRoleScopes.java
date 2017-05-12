package io.reactivesw.admin.authentication.application.model.action;

import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateActionUtils;
import lombok.Data;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * Set role scopes.
 */
@Data
public class SetRoleScopes implements UpdateAction {

  /**
   * List of scopes.
   */
  @NotNull
  private List<String> scopes;

  /**
   * Get action name.
   *
   * @return String
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_ROLE_SCOPES;
  }
}
