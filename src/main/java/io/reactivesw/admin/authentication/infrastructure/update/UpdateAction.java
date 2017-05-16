package io.reactivesw.admin.authentication.infrastructure.update;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.reactivesw.admin.authentication.application.model.action.ChangeAdminPassword;
import io.reactivesw.admin.authentication.application.model.action.SetAdminRoles;
import io.reactivesw.admin.authentication.application.model.action.SetRoleName;
import io.reactivesw.admin.authentication.application.model.action.SetRoleScopes;
import io.reactivesw.admin.authentication.application.model.action.SetScopeModules;
import io.reactivesw.admin.authentication.application.model.action.SetScopeName;
import io.reactivesw.admin.authentication.application.model.action.SetScopePermissions;
import io.reactivesw.admin.authentication.application.model.action.UpdateModule;

/**
 * Configurations for common update actions that will be used in more thant one service
 * and this action also extends other action configure in each service.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property =
    "action")
@JsonSubTypes( {
    @JsonSubTypes.Type(value = UpdateModule.class, name = UpdateActionUtils.UPDATE_MODULE),
    @JsonSubTypes.Type(value = SetScopePermissions.class, name = UpdateActionUtils
        .SET_SCOPE_PERMISSIONS),
    @JsonSubTypes.Type(value = SetScopeName.class, name = UpdateActionUtils.SET_SCOPE_NAME),
    @JsonSubTypes.Type(value = SetScopeModules.class, name = UpdateActionUtils.SET_SCOPE_MODULES),
    @JsonSubTypes.Type(value = SetRoleScopes.class, name = UpdateActionUtils.SET_ROLE_SCOPES),
    @JsonSubTypes.Type(value = SetRoleName.class, name = UpdateActionUtils.SET_ROLE_NAME),
    @JsonSubTypes.Type(value = SetAdminRoles.class, name = UpdateActionUtils.SET_ADMIN_ROLES),
    @JsonSubTypes.Type(value = ChangeAdminPassword.class, name = UpdateActionUtils
        .CHANGE_ADMIN_PASSWORD)
})
public interface UpdateAction {
  /**
   * Get action name.
   *
   * @return String.
   */
  String getActionName();
}
