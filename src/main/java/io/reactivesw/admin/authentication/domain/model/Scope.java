package io.reactivesw.admin.authentication.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Scope is a combination of modules and permissions.
 */
@Entity
@Table(name = "scope")
@Data
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Scope {

  /**
   * uuid.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  private String id;

  /**
   * The Created at.
   */
  @CreatedDate
  @Column
  private ZonedDateTime createdAt;

  /**
   * The Last modified at.
   */
  @LastModifiedDate
  @Column
  private ZonedDateTime lastModifiedAt;

  /**
   * Version.
   */
  @Column
  @Version
  private Integer version;

  /**
   * Scope name.
   */
  @Column
  private String scopeName;

  /**
   * List of permissions for all modules contains in this scope.
   */
  @ElementCollection
  private List<String> permissions;

  /**
   * List of modules.
   */
  @ElementCollection
  private List<String> modules;
}
