package io.reactivesw.admin.authentication.application.model.mapper;

import io.reactivesw.admin.authentication.application.model.AdminDraft;
import io.reactivesw.admin.authentication.application.model.AdminView;
import io.reactivesw.admin.authentication.domain.model.Admin;
import io.reactivesw.admin.authentication.infrastructure.util.PasswordUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Admin mapper.
 */
public final class AdminMapper {

  /**
   * Constructor.
   */
  private AdminMapper() {
    // do nothing
  }

  /**
   * Convert admin draft to entity.
   *
   * @param draft AdminDraft
   */
  public static Admin toEntity(AdminDraft draft) {
    Admin admin = new Admin();
    admin.setEmail(draft.getEmail());
    admin.setPassword(PasswordUtil.hashPassword(draft.getPassword()));
    return admin;
  }

  /**
   * Convert entity to view.
   *
   * @param admin Admin
   * @return RoleView
   */
  public static AdminView toModel(Admin admin) {
    AdminView view = new AdminView();
    view.setId(admin.getId());
    view.setCreatedAt(admin.getCreatedAt());
    view.setLastModifiedAt(admin.getLastModifiedAt());
    view.setVersion(admin.getVersion());
    view.setEmail(admin.getEmail());
    view.setRoleViews(RoleMapper.toModel(admin.getRoles()));

    return view;
  }

  /**
   * Convert list of admins to list of admin view.
   *
   * @param admins admin list
   * @return admin view list
   */
  public static List<AdminView> toModel(List<Admin> admins) {
    List<AdminView> adminViews = new ArrayList<>();
    admins.stream().forEach(
        role -> adminViews.add(toModel(role))
    );
    return adminViews;
  }
}
