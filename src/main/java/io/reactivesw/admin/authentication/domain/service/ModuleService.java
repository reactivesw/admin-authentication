package io.reactivesw.admin.authentication.domain.service;

import io.reactivesw.admin.authentication.domain.model.Module;
import io.reactivesw.admin.authentication.infrastructure.repository.ModuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Module service, used to manage modules.
 */
@Service
public class ModuleService {

  /**
   * LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ModuleService.class);

  /**
   * Module repository.
   */
  @Autowired
  private transient ModuleRepository moduleRepository;

  /**
   * Create module with sample.
   *
   * @param module Module
   * @return Module
   */
  public Module create(Module module) {
    LOG.debug("Enter. module: {}.", module);

    Module savedModule = this.moduleRepository.save(module);

    LOG.debug("Exit. module: {}.", savedModule);

    return savedModule;
  }

}
