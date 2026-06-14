/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;

import model.role.Role;

/**
 * Builder for Role model.
 */
public class RoleBuilder {

    private Role role;

    private RoleBuilder() {
        this.role = new Role();
    }

    public static RoleBuilder builder() {
        return new RoleBuilder();
    }

    public RoleBuilder setMaTaiKhoan(String maTaiKhoan) {
        role.setMaTaiKhoan(maTaiKhoan);
        return this;
    }

    public RoleBuilder setRoleName(String roleName) {
        role.setRoleName(roleName);
        return this;
    }

    public Role build() {
        return role;
    }
}
