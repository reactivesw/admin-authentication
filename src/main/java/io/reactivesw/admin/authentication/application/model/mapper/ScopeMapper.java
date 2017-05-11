package io.reactivesw.admin.authentication.application.model.mapper;

import io.reactivesw.admin.authentication.application.model.ScopeDraft;
import io.reactivesw.admin.authentication.application.model.ScopeView;
import io.reactivesw.admin.authentication.domain.model.Scope;

/**
 * Scope mapper.
 */
public final class ScopeMapper {

  /**
   * Constructor.
   */
  private ScopeMapper() {
    // do nothing
  }

  /**
   * Convert scope draft to entity.
   *
   * @param draft ScopeDraft
   */
  public static Scope toModel(ScopeDraft draft) {
    Scope scope = new Scope();
    scope.setScopeName(draft.getScopeName());
    return scope;
  }

  /**
   * Convert entity to view.
   *
   * @param scope Scope
   * @return ScopeView
   */
  public static ScopeView toView(Scope scope) {
    ScopeView view = new ScopeView();
    view.setId(scope.getId());
    view.setCreatedAt(scope.getCreatedAt());
    view.setLastModifiedAt(scope.getLastModifiedAt());
    view.setVersion(scope.getVersion());
    view.setScopeName(scope.getScopeName());
    view.setModules(scope.getModules());
    view.setPermissions(scope.getPermissions());

    return view;
  }
}
