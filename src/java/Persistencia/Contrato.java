/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import java.util.List;

/**
 *
 * @author oscar
 * @param <Cualquiercosa>
 */
public interface Contrato <Cualquiercosa>{
    public boolean create(Cualquiercosa nuevo);
    public boolean delete(int id);
    public boolean update(Cualquiercosa filter);
    public Cualquiercosa read(int filter);
    public List<Cualquiercosa> readAll();
}
