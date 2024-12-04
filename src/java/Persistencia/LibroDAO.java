/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Model.LibroDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class LibroDAO implements Contrato<LibroDTO>{

    @Override
    public boolean create(LibroDTO nuevo){
        ConexionMySQL instance = new ConexionMySQL().getInstance();
        Connection cnn;
        boolean rs = false;
        try{
            cnn = instance.getCnn();
            java.sql.PreparedStatement st = cnn.prepareStatement("INSERT INTO libro(ISBN, titulo, autor, editorial,"
                    + " anio, slug) values (?, ?, ?, ?, ?, ?)");
            st.setString(1, nuevo.getIsbn());
            st.setString(2, nuevo.getTitulo());
            st.setString(3, nuevo.getAutor());
            st.setString(4, nuevo.getEditorial());
            st.setInt(5, nuevo.getAnio());
            st.setString(6, nuevo.getSlug());
            
            rs = st.execute();
            
            
        }catch(SQLException ex){
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            instance.cerrarConexion();
        }
        return rs;
    }

    @Override
    public boolean update(LibroDTO filter) {
        ConexionMySQL instance = new ConexionMySQL().getInstance();
        boolean rs = false;
        Connection cnn;
        try{
            cnn = instance.getCnn();
            java.sql.PreparedStatement st = cnn.prepareStatement("UPDATE libro SET ISBN = ?, titulo = ?, autor = ?, editorial = ?, anio = ?, slug = ? WHERE id = ?");
            st.setString(1, filter.getIsbn());
            st.setString(2, filter.getTitulo());
            st.setString(3, filter.getAutor());
            st.setString(4, filter.getEditorial());
            st.setInt(5, filter.getAnio());
            st.setString(6, filter.getSlug());
            st.setInt(7, filter.getId());
            rs = st.execute(); // La diferencia entre execute y executeUpdate es que execute update retorna el numero de filas el execute boolean
        }catch(SQLException ex){
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public boolean delete(int id) {
        ConexionMySQL instance = new ConexionMySQL().getInstance();
        boolean rs = true;
        Connection cnn;
        try{
            cnn = instance.getCnn();
            java.sql.PreparedStatement st = cnn.prepareStatement("DELETE FROM libro WHERE id = ?");
            st.setInt(1, id);
            rs = st.execute(); // La diferencia entre execute y executeUpdate es que execute update retorna el numero de filas el execute boolean
        }catch(SQLException ex){
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    @Override
    public LibroDTO read(int filter) {
        LibroDTO objLibro = null;
        ConexionMySQL instance = new ConexionMySQL().getInstance();
        Connection cnn;
        try{
            cnn = instance.getCnn();
            java.sql.PreparedStatement st = cnn.prepareStatement("SELECT titulo, ISBN, autor, editorial, anio, slug FROM libro WHERE id = ?");
            st.setInt(1, filter);
            
            ResultSet rs = st.executeQuery(); // La diferencia entre execute y executeUpdate es que execute update retorna el numero de filas el execute boolean
            if(rs.next()){
                objLibro = new LibroDTO();
                objLibro.setId(filter);
                objLibro.setTitulo(rs.getString("titulo"));
                objLibro.setIsbn(rs.getString("ISBN"));
                objLibro.setAutor(rs.getString("autor"));
                objLibro.setEditorial(rs.getString("editorial"));
                objLibro.setAnio(rs.getInt("anio"));
                objLibro.setSlug(rs.getString("slug"));
            }
        }catch(SQLException ex){
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            instance.cerrarConexion();
        }
        return objLibro;
    }

    @Override
    public List<LibroDTO> readAll() {
        List<LibroDTO> lista = null;
        ConexionMySQL instance = new ConexionMySQL().getInstance();
        Connection cnn;
        try{
            cnn = instance.getCnn();
            java.sql.PreparedStatement st = cnn.prepareStatement("SELECT * FROM libro");
            lista = new ArrayList<>();
            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                LibroDTO i = new LibroDTO();
                i.setId(rs.getInt("id"));
                i.setIsbn(rs.getString("ISBN"));
                i.setTitulo(rs.getString("titulo"));
                i.setAnio(rs.getInt("anio"));
                i.setSlug(rs.getString("slug"));
                i.setEditorial(rs.getString("editorial"));
                i.setAutor(rs.getString("autor"));
                lista.add(i);
            }
        }catch (SQLException ex) {
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            instance.cerrarConexion();
        }
        return lista;
    }
}
