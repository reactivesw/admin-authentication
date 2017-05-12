package io.reactivesw.admin.authentication.application.model;

import lombok.Data;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * Role View.
 */
@Data
public class RoleDraft {

  /**
   * Role name.
   */
  @NotNull
  private String roleName;

  /**
   * List of scopes.
   */
  private List<String> scopes;
}
