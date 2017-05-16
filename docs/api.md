# Admin authentication api doc
This doc shows the apis that this service provided.

## 1 Model
- ModuleView

|Column Name     | Type              | Constraint        | Means
---|---|---|---
id              | String            | UUID              | id of the module, auto generated
createdAt       | ZonedDateTime     | Pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC"    | created time.
lastModifiedAt  | ZonedDateTime     | Pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC"    | Last modified time.
version         | Integer           | Auto increment    | version
moduleName      | String            | None              | module name
path            | String            | None              | path of module


- ModuleDraft

|Column Name     | Type              | Constraint        | Means
---|---|---|---
moduleName      | String            | None              | module name
path            | String            | None              | path of module

---

- ScopeView

|Column Name     | Type              | Constraint        | Means
---|---|---|---
id              | String            | UUID              | id, auto generated
createdAt       | ZonedDateTime     | Pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC"    | created time.
lastModifiedAt  | ZonedDateTime     | Pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC"    | Last modified time.
version         | Integer           | Auto increment    | version
scopeName       | String            | None              | scope name
permissions     | List<Permission>  | None              | List of permissions
moduleViews         | List<Module>      | None              | List of modules

- ScopeDraft

|Column Name     | Type              | Constraint        | Means
---|---|---|---
scopeName       | String            | None              | scope name
permissions     | List<String>      | None              | List of permission ids
modules         | List<String>      | None              | List of module ids

---

- RoleView

|Column Name     | Type              | Constraint        | Means
---|---|---|---
id              | String            | UUID              | id, auto generated
createdAt       | ZonedDateTime     | Pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC"    | created time.
lastModifiedAt  | ZonedDateTime     | Pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC"    | Last modified time.
version         | Integer           | Auto increment    | version
roleName        | String            | None              | role name
scopeViews      | List<ScopeView>   | None              | List of scope views

- RoleDraft

|Column Name     | Type              | Constraint        | Means
---|---|---|---
roleName        | String            | NOT NULL              | role name
scopeViews      | List<String>      | None                  | List of scope ids

---

- AdminView

|Column Name     | Type              | Constraint        | Means
---|---|---|---
id              | String            | UUID              | id, auto generated
createdAt       | ZonedDateTime     | Pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC"    | created time.
lastModifiedAt  | ZonedDateTime     | Pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC"    | Last modified time.
version         | Integer           | Auto increment    | version
email           | String            | None              | email name
roleViews       | List<RoleView>    | None              | List of role views

- AdminDraft

|Column Name     | Type              | Constraint        | Means
---|---|---|---
email           | String            | None              | email name
roleViews       | List<String>      | None              | List of role ids

---

- LoginResult

|Column Name     | Type              | Constraint        | Means
---|---|---|---
adminView        | AdminView         | None              | admin view
token            | String            | None              | string

- Login

|Column Name     | Type              | Constraint        | Means
---|---|---|---
email            | String            | None              | email
password         | String            | None              | password

---




## 2 Module API
All module apis.

### 2.1 Create Module
Create a new module from draft.
- Path: /modules
- Method: POST
- Payload: ModuleDraft
- Response: ModuleView

### 2.2 Get module by id
Get module by module id.
- Path: /modules/{id}
- Method: GET
- Payload: module id
- Response: ModuleView

### 2.3 Get all modules
Get all modules.
- Path: /modules
- Method: GET
- Payload: none
- Response: List<ModuleView>


### 2.4 Update a module
Update a existing module.
- Path: /modules/{id}
- Method: PUT
- Payload: UpdateRequest
- Response: ModuleView

### 2.5 Actions
- UpdateModule

|Column Name     | Type              | Nullable        | Means
---|---|---|---
moduleName      | String            | NOT NULL         | module name
path            | String            | NOT NULL         | path of module

---

## 3 Scope API
All scope apis.

### 3.1 Create Scope
Create a new scope from draft.
- Path: /scopes
- Method: POST
- Payload: ScopeDraft
- Response: ScopeView

### 3.2 Get scope by id
Get scope by scope id.
- Path: /scopes/{id}
- Method: GET
- Payload: scope id
- Response: ScopeView

### 3.3 Get all scopes
Get all scopes.
- Path: /scopes
- Method: GET
- Payload: none
- Response: List<ScopeView>


### 3.4 Update a scope
Update a existing scope.
- Path: /scopes/{id}
- Method: PUT
- Payload: UpdateRequest
- Response: ScopeView

### 3.5 Actions
- SetScopeName

|Column Name     | Type              | Nullable        | Means
---|---|---|---
scopeName       | String            | NOT NULL         | scope name

- SetScopePermissions

|Column Name     | Type              | Nullable        | Means
---|---|---|---
permissions      | List<String>      | NOT NULL         | List of permissions

- SetScopeModule

|Column Name     | Type              | Nullable        | Means
---|---|---|---
modules          | List<String>      | NOT NULL         | List of modules

---


## 4 Role API
All role apis.

### 4.1 Create role
Create a new role from draft.
- Path: /roles
- Method: POST
- Payload: RoleDraft
- Response: RoleView

### 4.2 Get role by id
Get role by role id.
- Path: /roles/{id}
- Method: GET
- Payload: role id
- Response: RoleView

### 4.3 Get all roles
Get all roles.
- Path: /roles
- Method: GET
- Payload: none
- Response: List<RoleView>


### 4.4 Update a role
Update a existing role.
- Path: /roles/{id}
- Method: PUT
- Payload: UpdateRequest
- Response: RoleView

### 4.5 Actions
- SetRoleName

|Column Name     | Type              | Nullable        | Means
---|---|---|---
roleName         | String            | NOT NULL         | role name

- SetRoleScopes

|Column Name     | Type              | Nullable        | Means
---|---|---|---
scopes           | List<String>      | NOT NULL         | role scope list

---

## 5 Admin API
All admin apis.

### 5.1 Create admin
Create a new admin from draft.
- Path: /admins
- Method: POST
- Payload: AdminDraft
- Response: AdminView

### 5.2 Get admin by id
Get admin by admin id.
- Path: /admins/{id}
- Method: GET
- Payload: admin id
- Response: AdminView

### 5.3 Get all admins
Get all admins.
- Path: /admins
- Method: GET
- Payload: none
- Response: List<AdminView>


### 5.4 Update a admin
Update a existing admin.
- Path: /admins/{id}
- Method: PUT
- Payload: UpdateRequest
- Response: AdminView

### 5.5 Actions
- SetAdminRoles

|Column Name     | Type              | Nullable        | Means
---|---|---|---
roles            | List<String>      | NOT NULL        | list of role ids

- ChangeAdminPassword

|Column Name     | Type              | Nullable        | Means
---|---|---|---
newPassword      | String           | NOT NULL         | new password
oldPassword      | String           | NOT NULL         | old password

---





