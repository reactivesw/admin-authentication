package io.reactivesw.admin.authentication.application.controller;

import io.reactivesw.admin.authentication.application.model.ScopeDraft;
import io.reactivesw.admin.authentication.application.model.ScopeView;
import io.reactivesw.admin.authentication.application.service.ScopeApplication;
import io.reactivesw.admin.authentication.infrastructure.Router;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

/**
 * Scope controller.
 */
@RestController
public class ScopeController {

  /**
   * LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ScopeController.class);

  /**
   * Scope application.
   */
  @Autowired
  private transient ScopeApplication scopeApplication;

  /**
   * Create a new Scope.
   *
   * @param draft draft
   * @return ScopeView
   */
  @PostMapping(Router.SCOPE_ROOT)
  public ScopeView create(@RequestBody @Valid ScopeDraft draft) {
    LOG.info("Enter. scopeDraft: {}.", draft);

    ScopeView view = scopeApplication.create(draft);

    LOG.info("Exit. scopeView: {}.", view);
    return view;
  }

  /**
   * Get scope by id.
   *
   * @param id String
   * @return Scope view
   */
  @GetMapping(Router.SCOPE_WITH_ID)
  public ScopeView getById(@PathVariable String id) {
    LOG.info("Enter. id: {}.", id);

    ScopeView view = scopeApplication.getById(id);

    LOG.info("Exit. scopeView: {}.", view);
    return view;
  }

  /**
   * Get all scopes
   *
   * @return list of scope view
   */
  @GetMapping(Router.SCOPE_ROOT)
  public List<ScopeView> getAll() {
    LOG.info("Enter.");

    List<ScopeView> views = scopeApplication.getAll();

    LOG.info("Exit. scopeView size: {}.", views.size());
    return views;
  }


  /**
   * Update one scope.
   *
   * @param id            String
   * @param updateRequest UpdateRequest
   * @return ScopeView
   */
  @PutMapping(Router.SCOPE_WITH_ID)
  public ScopeView update(@PathVariable String id,
                          @RequestBody @Valid UpdateRequest updateRequest) {
    LOG.info("Enter. id: {}. updateRequest: {}.", id, updateRequest);

    ScopeView view = scopeApplication.update(id, updateRequest.getVersion(), updateRequest
        .getActions());

    LOG.info("Exit. scopeView: {}.", view);
    return view;
  }
}
