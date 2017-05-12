package io.reactivesw.admin.authentication.application.controller;

import io.reactivesw.admin.authentication.application.model.RoleDraft;
import io.reactivesw.admin.authentication.application.model.RoleView;
import io.reactivesw.admin.authentication.application.service.RoleApplication;
import io.reactivesw.admin.authentication.infrastructure.Router;
import io.reactivesw.admin.authentication.infrastructure.update.UpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

/**
 * Role controller.
 */
@RestController
public class RoleController {

  /**
   * LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

  /**
   * Role application.
   */
  @Autowired
  private transient RoleApplication roleApplication;

  /**
   * Create a new Role.
   *
   * @param draft draft
   * @return RoleView
   */
  @PostMapping(Router.ROLE_ROOT)
  public RoleView create(@RequestBody @Valid RoleDraft draft) {
    LOG.info("Enter. roleDraft: {}.", draft);

    RoleView view = roleApplication.create(draft);

    LOG.info("Exit. roleView: {}.", view);
    return view;
  }

  /**
   * Get Role by id.
   *
   * @param id String
   * @return Role view
   */
  @GetMapping(Router.ROLE_WITH_ID)
  public RoleView getById(@PathVariable String id) {
    LOG.info("Enter. id: {}.", id);

    RoleView view = roleApplication.getById(id);

    LOG.info("Exit. roleView: {}.", view);
    return view;
  }

  /**
   * Get all roles
   *
   * @return list of role view
   */
  @GetMapping(Router.ROLE_ROOT)
  public List<RoleView> getAll() {
    LOG.info("Enter.");

    List<RoleView> views = roleApplication.getAll();

    LOG.info("Exit. roleView size: {}.", views.size());
    return views;
  }


  /**
   * Update one role
   *
   * @param id            String
   * @param updateRequest UpdateRequest
   * @return RoleView
   */
  public RoleView update(@PathVariable String id,
                          @RequestBody @Valid UpdateRequest updateRequest) {
    LOG.info("Enter. id: {}. updateRequest: {}.", id, updateRequest);

    RoleView view = roleApplication.update(id, updateRequest.getVersion(), updateRequest
        .getActions());

    LOG.info("Exit. roleView: {}.", view);
    return view;
  }
}
