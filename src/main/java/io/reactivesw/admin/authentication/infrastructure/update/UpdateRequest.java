package io.reactivesw.admin.authentication.infrastructure.update;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Update request.
 */
public class UpdateRequest {
  /**
   * The expected version of the category on which the changes should be applied.
   * If the expected version does not match the actual version, a 409 Conflict will be returned.
   */
  @NotNull
  @Min(0)
  private Integer version;

  /**
   * Array of UpdateAction.
   * The list of update action to be performed on the category.
   * Required.
   */
  @NotNull
  @Valid
  private List<UpdateAction> actions;

  /**
   * convert to UpdateActions.
   *
   * @return list of UpdateAction
   */
  public List<UpdateAction> getActions() {
    return actions.parallelStream().map(action -> (UpdateAction) action).collect(Collectors
        .toList());
  }

  /**
   * Get version.
   *
   * @return Integer
   */
  public Integer getVersion() {
    return version;
  }

  /**
   * Set version.
   *
   * @param version Integer
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * Set actions.
   *
   * @param actions List of UpdateAction
   */
  public void setActions(List<UpdateAction> actions) {
    this.actions = actions;
  }
}
