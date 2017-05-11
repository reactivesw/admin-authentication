package io.reactivesw.admin.authentication.domain.model;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Admin in this system.
 */
@Entity
@Table(name = "admin")
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Admin {


  /**
   * Uuid.
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
   * Email of this account.
   */
  @Email
  @Column(unique = true)
  private String email;

  /**
   * Password of this account.
   */
  @Column
  private String password;

  /**
   * List of roles.
   */
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Role> roles;

  /**
   * Override toString method, do not show password.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "Admin{"
        + "id='" + id + '\''
        + ", createdAt=" + createdAt
        + ", lastModifiedAt=" + lastModifiedAt
        + ", version=" + version
        + ", email='" + email + '\''
        + ", roles=" + roles
        + '}';
  }

  /**
   * Get Id.
   *
   * @return String
   */
  public String getId() {
    return id;
  }

  /**
   * Set id.
   *
   * @param id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Get create at.
   *
   * @return ZonedDateTime
   */
  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  /**
   * Set create at.
   *
   * @param createdAt ZonedDateTime
   */
  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Get last modified time.
   *
   * @return ZonedDateTime
   */
  public ZonedDateTime getLastModifiedAt() {
    return lastModifiedAt;
  }

  /**
   * Set last modified time.
   *
   * @param lastModifiedAt ZonedDateTime
   */
  public void setLastModifiedAt(ZonedDateTime lastModifiedAt) {
    this.lastModifiedAt = lastModifiedAt;
  }

  /**
   * Get version
   *
   * @return version
   */
  public Integer getVersion() {
    return version;
  }

  /**
   * Set version
   *
   * @param version Integer
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * Get email.
   *
   * @return String email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Set email.
   *
   * @param email String
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Get Password.
   *
   * @return String
   */
  public String getPassword() {
    return password;
  }

  /**
   * Set password.
   *
   * @param password String
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Get roles.
   *
   * @return List of roles
   */
  public List<Role> getRoles() {
    return roles;
  }

  /**
   * Set roles.
   *
   * @param roles List of roles
   */
  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
}
