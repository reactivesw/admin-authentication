# Admin authentication
This doc describes the design of admin authentication service.

**Key Points**
- We separate our resource as models, like: product, category, inventory.
- We defines our permission with rules: `C` for `create`, `R` for `read`, `U` for Update, `D` for `Delete`.
- A scope contains a list of models, and a list of actions, meas that, the employee has those permissions on those models.
- A role contains a list of scopes.
- Each user of this service has a list of roles, so that the user can has those permissions contained in these roles.
- Admin, Role, Scope, Module are four independent resource, all of them should and must be created independently.


## 1. Basic futures
- Add admin, scope, role, module
- Login, Logout
- Check login status and permission
- Change password
- Reset employee's password
- Change employee's permission

## 2. Model design
- Entity: Admin
```java
  String id
  
  ZonedDateTime createdAt;

  ZonedDateTime lastModifiedAt;

  Integer version;
  
  String email;

  String password;
  
  List<String> roles;
```

- Entity: Role
```
  String id
  
  ZonedDateTime createdAt;

  ZonedDateTime lastModifiedAt;

  Integer version;
  
  String roleName;
  
  List<String> scopes;
```

- Entity: Scope
```
  String id
  
  ZonedDateTime createdAt;

  ZonedDateTime lastModifiedAt;

  Integer version;
  
  String scopeName;
  
  String permission;
  
  List<Model> models;
```

- Entity: Model
```java
  String id
  
  ZonedDateTime createdAt;

  ZonedDateTime lastModifiedAt;

  Integer version;
  
  String modelName;
  
  String path;
```

# 3. Detail design

## 3.1 Add admin
Add an admin of the shop to the system.
**Key Points**
- Make sure the roles contained exists in the system.

### 3.1.1 Workflow
- Receive email, password and role list from request
- Check if the password is correct and the roles exist in the system
- Save the new admin to database
- Return the saved admin

## 3.2 Create module
Create new module.
**Key Points**
- Make sure path in each module is unique.
### 3.2.1 Workflow
- Receive draft from request
- Convert draft to module
- Save to database
- Convert saved module to view
- Return created view

## 3.3 Get Module by id
Get Module by id


## 3.2 Login
Use email and password to login to the admin system, and the server will keep the session.

## 3.3 Logout
When logout from the system, the session will be clear immediately.

## 3.4 Check login status and permission
Check if the user has login, and if he/she has the right permissions.

## 3.5 Change password
User can change his/her password with his/her old password.

## 3.6 Reset employee's password
Admin can reset employees's password.

## 3.7 Change employee's permission
Admin can change employee's permission, and the permissions in session will be changed at the same time.
