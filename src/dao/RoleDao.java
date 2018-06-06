/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import models.RoleModel;

/**
 *
 * @author Rehan Ali Azeemi
 */
public interface RoleDao {
    public RoleModel getRoleWithRoleId(Integer roleId);
    public RoleModel getRoleWithRoleName(String roleName);
    public ResultSet getAllRoles();
    public Integer addRole(RoleModel roleModel);
    public Integer updateRole(RoleModel roleModel);
    public Integer deleteRole(Integer id);
}
