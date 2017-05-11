package io.reactivesw.admin.authentication.application.model;

import io.reactivesw.admin.authentication.domain.model.Module;
import io.reactivesw.admin.authentication.infrastructure.enums.Permission;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Scope View.
 */
@Data
public class ScopeView {

  /**
   * Uuid.
   */
  private String id;

  /**
   * The Created at.
   */
  private ZonedDateTime createdAt;

  /**
   * The Last modified at.
   */
  private ZonedDateTime lastModifiedAt;

  /**
   * Version.
   */
  private Integer version;

  /**
   * Scope name.
   */
  private String scopeName;

  /**
   * List of permissions for all modules contains in this scope.
   */
  private List<Permission> permissions;

  /**
   * List of modules.
   */
  private List<Module> modules;
}
