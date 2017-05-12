package io.reactivesw.admin.authentication.infrastructure.update;

import io.reactivesw.admin.authentication.domain.model.Role;
import io.reactivesw.model.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Updater service.
 */
@Service
public class RoleUpdaterService implements Updater<Role, UpdateAction> {

  /**
   * ApplicationContext for get update services.
   */
  @Autowired
  private transient ApplicationContext context;

  /**
   * Put the value in action to entity.
   *
   * @param entity Role
   * @param action UpdateAction
   */
  @Override
  public void handle(Role entity, UpdateAction action) {
    Updater updater = getUpdateService(action);
    updater.handle(entity, action);
  }

  /**
   * Get mapper.
   *
   * @param action UpdateAction class
   * @return ZoneUpdateMapper
   */
  private Updater getUpdateService(UpdateAction action) {
    return (Updater) context.getBean(action.getActionName());
  }

}
