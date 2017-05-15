package io.reactivesw.admin.authentication.application.model.mapper;

import io.reactivesw.admin.authentication.application.model.ScopeDraft;
import io.reactivesw.admin.authentication.application.model.ScopeView;
import io.reactivesw.admin.authentication.domain.model.Scope;

import java.util.ArrayList;
import java.util.List;

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
  public static Scope toEntity(ScopeDraft draft) {
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
  public static ScopeView toModel(Scope scope) {
    ScopeView view = new ScopeView();
    view.setId(scope.getId());
    view.setCreatedAt(scope.getCreatedAt());
    view.setLastModifiedAt(scope.getLastModifiedAt());
    view.setVersion(scope.getVersion());
    view.setScopeName(scope.getScopeName());
    view.setModules(ModuleMapper.toModel(scope.getModules()));
    view.setPermissions(scope.getPermissions());

    return view;
  }

  /**
   * Convert list of scope to list of scope view.
   *
   * @param scopes scope list
   * @return scope view list
   */
  public static List<ScopeView> toModel(List<Scope> scopes) {
    List<ScopeView> scopeViews = new ArrayList<>();
    scopes.stream().forEach(
        scope -> scopeViews.add(toModel(scope))
    );
    return scopeViews;
  }
}
