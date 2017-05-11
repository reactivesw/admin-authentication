package io.reactivesw.admin.authentication.application.model;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Module view.
 */
@Data
public class ModuleView {

  /**
   * UUID.
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
   * Module name.
   */
  private String moduleName;

  /**
   * Path of this module.
   */
  private String path;
}
