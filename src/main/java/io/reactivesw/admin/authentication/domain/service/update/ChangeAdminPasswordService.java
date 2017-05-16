package io.reactivesw.admin.authentication.domain.service.update;

import io.reactivesw.admin.authentication.application.model.action.ChangeAdminPassword;
import io.reactivesw.admin.authentication.domain.model.Admin;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateAction;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateActionUtils;
import io.reactivesw.admin.authentication.infrastructure.util.PasswordUtil;
import io.reactivesw.exception.PasswordErrorException;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Change admin's password.
 */
@Service(value = UpdateActionUtils.CHANGE_ADMIN_PASSWORD)
public class ChangeAdminPasswordService implements Updater<Admin, UpdateAction> {

  /**
   * Change admin's password.
   *
   * @param admin        Admin
   * @param updateAction Action
   */
  @Override
  public void handle(Admin admin, UpdateAction updateAction) {
    ChangeAdminPassword action = (ChangeAdminPassword) updateAction;
    boolean checkOld = PasswordUtil.checkPassword(action.getOldPassword(), admin.getPassword());
    if (!checkOld) {
      throw new PasswordErrorException("Old password is not correct.");
    }
    admin.setPassword(PasswordUtil.hashPassword(action.getNewPassword()));
  }
}
