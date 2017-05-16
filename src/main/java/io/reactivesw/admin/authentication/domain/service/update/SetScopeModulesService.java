package io.reactivesw.admin.authentication.domain.service.update;

import io.reactivesw.admin.authentication.application.model.action.SetScopeModules;
import io.reactivesw.admin.authentication.domain.model.Module;
import io.reactivesw.admin.authentication.domain.model.Scope;
import io.reactivesw.admin.authentication.domain.service.ModuleService;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Set scope modules.
 */
@Service(value = UpdateActionUtils.SET_SCOPE_MODULES)
public class SetScopeModulesService implements Updater<Scope, UpdateAction> {

  /**
   * Module service.
   */
  @Autowired
  private transient ModuleService moduleService;

  /**
   * Set scope's modules.
   *
   * @param scope        scope
   * @param updateAction Action
   */
  @Override
  public void handle(Scope scope, UpdateAction updateAction) {
    SetScopeModules setScopeModules = (SetScopeModules) updateAction;
    List<Module> modules = moduleService.getListById(setScopeModules.getModules());
    scope.setModules(modules);
    // TODO update the login admin's scope
  }
}
