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

---

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
modules         | List<Module>      | None              | List of modules

- ScopeDraft

|Column Name     | Type              | Constraint        | Means
---|---|---|---
scopeName       | String            | None              | scope name
permissions     | List<String>      | None              | List of permission ids
modules         | List<String>      | None              | List of module ids


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
- Response: Scope

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





