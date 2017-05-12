package io.reactivesw.admin.authentication.application.model;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Admin View.
 */
@Data
public class AdminView {

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
   * Email of this account.
   */
  private String email;

  /**
   * List of roles.
   */
  private List<RoleView> roles;
}
