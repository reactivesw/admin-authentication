package io.reactivesw.admin.authentication.application.model;

import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Admin View.
 */
@Data
public class AdminView implements Serializable {

  /**
   * Auto generated.
   */
  private static final long serialVersionUID = 5407650858590437934L;
  
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
   * List of roleViews.
   */
  private List<RoleView> roleViews;
}
