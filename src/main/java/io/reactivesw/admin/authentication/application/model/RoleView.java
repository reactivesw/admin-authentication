package io.reactivesw.admin.authentication.application.model;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Role View.
 */
@Data
public class RoleView {

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
   * Role name.
   */
  private String roleName;

  /**
   * List of scopes.
   */
  private List<ScopeView> scopes;
}
