package io.reactivesw.admin.authentication.application.model.mapper;

import io.reactivesw.admin.authentication.application.model.RoleDraft;
import io.reactivesw.admin.authentication.application.model.RoleView;
import io.reactivesw.admin.authentication.domain.model.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Role mapper.
 */
public final class RoleMapper {

  /**
   * Constructor.
   */
  private RoleMapper() {
    // do nothing
  }

  /**
   * Convert role draft to entity.
   *
   * @param draft RoleDraft
   */
  public static Role toEntity(RoleDraft draft) {
    Role role = new Role();
    role.setRoleName(draft.getRoleName());
    return role;
  }

  /**
   * Convert entity to view.
   *
   * @param role Role
   * @return RoleView
   */
  public static RoleView toModel(Role role) {
    RoleView view = new RoleView();
    view.setId(role.getId());
    view.setCreatedAt(role.getCreatedAt());
    view.setLastModifiedAt(role.getLastModifiedAt());
    view.setVersion(role.getVersion());
    view.setRoleName(role.getRoleName());
    view.setScopes(ScopeMapper.toModel(role.getScopes()));

    return view;
  }

  /**
   * Convert list of roles to list of role view.
   *
   * @param roles role list
   * @return role view list
   */
  public static List<RoleView> toModel(List<Role> roles) {
    List<RoleView> roleViews = new ArrayList<>();
    roles.stream().forEach(
        role -> roleViews.add(toModel(role))
    );
    return roleViews;
  }
}