package io.reactivesw.admin.authentication.application.controller;

import io.reactivesw.admin.authentication.application.model.AdminDraft;
import io.reactivesw.admin.authentication.application.model.AdminView;
import io.reactivesw.admin.authentication.application.service.AdminApplication;
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
 * Admin controller.
 */
@RestController
public class AdminController {

  /**
   * LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

  /**
   * Admin application.
   */
  @Autowired
  private transient AdminApplication adminApplication;

  /**
   * Create a new admin.
   *
   * @param draft draft
   * @return AdminView
   */
  @PostMapping(Router.ADMIN_ROOT)
  public AdminView create(@RequestBody @Valid AdminDraft draft) {
    LOG.info("Enter. adminDraft: {}.", draft);

    AdminView view = adminApplication.create(draft);

    LOG.info("Exit. adminView: {}.", view);
    return view;
  }

  /**
   * Get admin by id.
   *
   * @param id String
   * @return admin view
   */
  @GetMapping(Router.ADMIN_WITH_ID)
  public AdminView getById(@PathVariable String id) {
    LOG.info("Enter. id: {}.", id);

    AdminView view = adminApplication.getById(id);

    LOG.info("Exit. adminView: {}.", view);
    return view;
  }

  /**
   * Get all admins
   *
   * @return list of admin view
   */
  @GetMapping(Router.ADMIN_ROOT)
  public List<AdminView> getAll() {
    LOG.info("Enter.");

    List<AdminView> views = adminApplication.getAll();

    LOG.info("Exit. adminView size: {}.", views.size());
    return views;
  }


  /**
   * Update one admin
   *
   * @param id            String
   * @param updateRequest UpdateRequest
   * @return AdminView
   */
  @PutMapping(Router.ADMIN_WITH_ID)
  public AdminView update(@PathVariable String id,
                          @RequestBody @Valid UpdateRequest updateRequest) {
    LOG.info("Enter. id: {}. updateRequest: {}.", id, updateRequest);

    AdminView view = adminApplication.update(id, updateRequest.getVersion(), updateRequest
        .getActions());

    LOG.info("Exit. adminView: {}.", view);
    return view;
  }
}
