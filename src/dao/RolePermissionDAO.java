/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import models.RolePermissionModel;

/**
 *
 * @author Lenovo
 */
public interface RolePermissionDAO {
    
    public ResultSet getRolePermissions();
    public Integer addRolePermission(RolePermissionModel rolePermissionModel);
    public Integer updateRolePermission(RolePermissionModel rolePermissionModel);
    public Integer deleteRolePermissionById(RolePermissionModel rolePermissionModel);
    public RolePermissionModel getRolePermissionById(Integer rolePermissionId);
    public RolePermissionModel getRolePermissionByName(String rolePermissionName);
    public RolePermissionModel getRolePermissionRecord(Integer rolePermissionId);
    public ResultSet getAssignedPermissions(String roleType);
    public ResultSet getUnAssignedPermissions(String roleType);
}
