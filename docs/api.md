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
- Payload: module id
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





