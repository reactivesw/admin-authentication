package io.reactivesw.admin.authentication.application.model.action;

import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateActionUtils;
import lombok.Data;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * Set scope modules
 */
@Data
public class SetScopeModules implements UpdateAction {

  /**
   * List of module ids.
   */
  @NotNull
  private List<String> modules;

  /**
   * Get action name.
   *
   * @return String
   */
  @Override
  public String getActionName() {
    return UpdateActionUtils.SET_SCOPE_MODULES;
  }
}
