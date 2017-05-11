package io.reactivesw.admin.authentication.application.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Module view.
 */
@Data
public class ModuleDraft {

  /**
   * Module name.
   */
  @NotNull
  private String moduleName;

  /**
   * Path of this module.
   */
  @NotNull
  private String path;
}
