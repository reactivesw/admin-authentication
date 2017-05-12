package io.reactivesw.admin.authentication.application.model.mapper;

import io.reactivesw.admin.authentication.infrastructure.enums.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * Permission mapper.
 */
public final class PermissionMapper {

  /**
   * Private default constructor.
   */
  private PermissionMapper(){
    // do nothing
  }

  /**
   * Convert list of string to list of permission.
   *
   * @param permissions permission string list
   * @return List of permissions
   */
  public static List<Permission> toModel(List<String> permissions) {

    List<Permission> permissionList = new ArrayList<>();
    permissions.stream().forEach(
        perStr -> {
          Permission permission = Permission.getPermission(perStr);
          if (permission != null) {
            permissionList.add(permission);
          }
        }
    );
    return permissionList;
  }
}
