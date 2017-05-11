package io.reactivesw.admin.authentication.domain.service;

import io.reactivesw.admin.authentication.domain.model.Scope;
import io.reactivesw.admin.authentication.infrastructure.repository.ScopeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Scope service, used to manage scopes.
 */
@Service
public class ScopeService {

  /**
   * LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ScopeService.class);

  /**
   * Scope repository.
   */
  @Autowired
  private transient ScopeRepository scopeRepository;

  /**
   * Create module with sample.
   *
   * @param scope Scope
   * @return Scope
   */
  public Scope create(Scope scope) {
    LOG.debug("Enter. scope: {}.", scope);

    Scope savedScope = this.scopeRepository.save(scope);

    LOG.debug("Exit. scope: {}.", savedScope);

    return savedScope;
  }

}
