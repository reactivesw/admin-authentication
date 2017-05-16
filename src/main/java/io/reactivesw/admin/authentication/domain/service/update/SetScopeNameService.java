package io.reactivesw.admin.authentication.domain.service.update;

import io.reactivesw.admin.authentication.application.model.action.SetScopeName;
import io.reactivesw.admin.authentication.domain.model.Scope;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Set scope name.
 */
@Service(value = UpdateActionUtils.SET_SCOPE_NAME)
public class SetScopeNameService implements Updater<Scope, UpdateAction> {

  /**
   * Set scope's name.
   *
   * @param scope        scope
   * @param updateAction Action
   */
  @Override
  public void handle(Scope scope, UpdateAction updateAction) {
    SetScopeName setScopeName = (SetScopeName) updateAction;
    scope.setScopeName(setScopeName.getScopeName());
  }
}
