# Admin authentication service requirements
This doc mainly describes the requirements for admin authentication service.

# Requirements
This part describes the detail requirements.

**Key futures**
- The service will start with a super manager account that has all permissions
- The admin can only login with email
- The admin can manage the limits of permissions of each employee
- When employee's permission changed, it must take effect immediately
- Each employee can only visit the pages that he/she has the permission
- The account in this service can only be assigned by admin/super admin


## Login with super manager
When the service start, it should already has one super manager created, so we can login with this account.

## Add Admin
Super admin can manager `Admin Account`.

## Add employee
All admins can add employee.

## Manage employee's permission
All admins can manage employee's permission, and if the permission changed, it must take effect immediately.

## Change password
All accounts in the system can change their own password.

## Reset Password
All admins can reset employee's password.