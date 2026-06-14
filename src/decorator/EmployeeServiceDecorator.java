/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decorator;

import service.EmployeeService;

/**
 *
 * @author FPTSHOP
 */
public abstract class EmployeeServiceDecorator
        implements EmployeeService {

    protected EmployeeService service;

    public EmployeeServiceDecorator(
            EmployeeService service) {

        this.service = service;
    }
}
