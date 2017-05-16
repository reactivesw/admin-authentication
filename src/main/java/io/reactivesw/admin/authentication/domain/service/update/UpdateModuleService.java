package io.reactivesw.admin.authentication.domain.service.update;

import io.reactivesw.admin.authentication.application.model.action.UpdateModule;
import io.reactivesw.admin.authentication.domain.model.Module;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Update module service.
 */
@Service(value = UpdateActionUtils.UPDATE_MODULE)
public class UpdateModuleService implements Updater<Module, UpdateAction> {

  /**
   * Update module.
   *
   * @param module       Module
   * @param updateAction UpdateAction
   */
  @Override
  public void handle(Module module, UpdateAction updateAction) {
    UpdateModule updateModule = (UpdateModule) updateAction;
    module.setPath(updateModule.getPath());
    module.setModuleName(updateModule.getModuleName());
    // TODO update the login admin's scope
  }
}
