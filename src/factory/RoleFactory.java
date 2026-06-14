/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import builder.RoleBuilder;
import model.role.Role;

/**
 * Factory for creating Role records.
 */
public class RoleFactory {

    public static Role createRole(String maTaiKhoan, String roleName) {
        return RoleBuilder.builder()
                .setMaTaiKhoan(maTaiKhoan)
                .setRoleName(roleName)
                .build();
    }
}
