/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Lenovo
 */
public class RolePermissionModel extends UtilityModel{
    private Integer rolePermissionId;
    private PermissionModel permissionModel;
    private RoleModel roleModel;

    public Integer getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public PermissionModel getPermissionModel() {
        return permissionModel;
    }

    public void setPermissionModel(PermissionModel permissionModel) {
        this.permissionModel = permissionModel;
    }

    public RoleModel getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }
}
