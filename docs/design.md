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

- Model: AdminSession
```java
  
  private long loginTime;

  private long lastVisitTime;

  private long expiresIn;
```

- Model: VerifyBody
```java

  private String token;

  private String resource;

  private String action;
```

- Model: VerifyResult
```java

  private boolean isLogin;

  private boolean hashPermission;

  private String adminId;
```


# 3 Module design 

## 3.1 Create module
Create new module.
**Key Points**
- Make sure path in each module is unique.
### 3.1.1 Workflow
- Receive draft from request
- Convert draft to module
- Save to database
- Convert saved module to view
- Return created view

## 3.2 Get Module by id
Get module by id.
### 3.2.1 Workflow
- Receive id from request
- Get module from database, and if module not exist, then throw exception
- Convert module to module view, and return it

## 3.3 Get all modules
Get all modules from database.
### 3.3.1 Workflow
- Fetch all modules from database
- Convert module list to module view list
- Return it

## 3.4 Update module
- Receive id, update request form request
- Get module from database, and if module not exist, then throw exception
- Check version, if version not correct, then throw exception
- Run each update service
- Save the updated module to database
- Convert the saved module to view and return it

---

# 4 Scope design

## 4.1 Create Scope
Create new Scope.
**Key Point**
- Make sure the modules 
### 4.1.1 Workflow
- Receive `ScopeDraft` from request
- Convert permission string list to permission list
- Convert module string list to module list, if module not found, then throw exception
- Create a new scope, and set the permission, module, name
- Save the new scope to database
- Convert scope to scope view and return it

## 4.2 Get scope by id
Get scope by id.
### 4.2.1 Workflow
- Receive id from request
- Get scope from database, and if scope not exist, then throw exception
- Convert scope to scope view, and return it

## 4.3 Get all scopes
Get all scopes from database.
### 4.3.1 Workflow
- Fetch all scopes from database
- Convert scope list to scope view list
- Return it

## 4.4 Update scope
- Receive id, update request form request
- Get scope from database, and if scope not exist, then throw exception
- Check version, if version not correct, then throw exception
- Run each update service
- Save the updated scope to database
- Convert the saved scope to view and return it

---

# 5 Role design

## 5.1 Create role
Create new role.
**Key Point**
- Make sure the scope exists. 
### 5.1.1 Workflow
- Receive `RoleDraft` from request
- Convert scope string list to scope list
- Create a new Role, and set the scope, name
- Save the new role to database
- Convert role to role view and return it

## 5.2 Get role by id
Get role by id.
### 5.2.1 Workflow
- Receive id from request
- Get role from database, and if role not exist, then throw exception
- Convert role to role view, and return it

## 5.3 Get all roles
Get all roles from database.
### 5.3.1 Workflow
- Fetch all roles from database
- Convert role list to role view list
- Return it

## 5.4 Update role
- Receive id, update request form request
- Get role from database, and if role not exist, then throw exception
- Check version, if version not correct, then throw exception
- Run each update service
- Save the updated role to database
- Convert the saved role to view and return it

---

# 6 Admin design
## 6.1 Add admin
Add an admin of the shop to the system.
**Key Points**
- Make sure the roles contained exists in the system.
## 6.1.1 Workflow
- Receive email, password and role list from request
- Check if the password is correct and the roles exist in the system
- Save the new admin to database
- Return the saved admin

## 6.2 Get admin by id
Get admin by id.
### 6.2.1 Workflow
- Receive id from request
- Get admin from database, and if admin not exist, then throw exception
- Convert admin to role view, and return it

## 6.3 Get all admins
Get all admins from database.
### 6.3.1 Workflow
- Fetch all admins from database
- Convert admin list to admin view list
- Return it

## 6.4 Update admin
- Receive id, update request form request
- Get admin from database, and if admin not exist, then throw exception
- Check version, if version not correct, then throw exception
- Run each update service
- Save the updated admin to database
- Convert the saved admin to view and return it

---
 
# 7 Login design
Use email and password to login to the admin system, and the server will keep the session.
## 7.1 Workflow
- Receive email and password from request 
- Get admin by email from the database, if not exist then throw not exist exception
- Check the password, if not match then throw password not correct exception
- Generate admin session and cache it
- return login result

# 8 Verify admin status
Verify admin status with token, and verify if the admin login, and if the admin has the correct permissions.
## 8.1 Workflow
- Receive `token`, `resource`, `action` from the request
- Parse the token, and get adminId, if can not parse the token, then throw exception
- Check if this token's session correct: existing and not expired
- Check if the admin has the right permission for the resource and action
- return the verify result

# 9 How 