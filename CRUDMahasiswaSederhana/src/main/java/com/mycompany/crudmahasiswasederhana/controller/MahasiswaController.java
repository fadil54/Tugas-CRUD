/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudmahasiswasederhana.controller;

import com.mycompany.crudmahasiswasederhana.db.ConnectionHelper;
import static com.mycompany.crudmahasiswasederhana.db.ConnectionHelper.st;
import com.mycompany.crudmahasiswasederhana.interfac.MahasiswaInterface;
import com.mycompany.crudmahasiswasederhana.models.Mahasiswa;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import com.mysql.cj.protocol.Resultset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer SPIN
 */
public class MahasiswaController implements MahasiswaInterface{
    PreparedStatement st;
    @Override
    public Mahasiswa insert(Mahasiswa o) throws SQLException {
        st = ConnectionHelper.getConnection().prepareStatement("insert into mahasiswa values(?,?,?)");
        st.setString(1, o.getNim());
        st.setString(2, o.getNama());
        st.setString(3, o.getAlamat());
        st.executeUpdate();
        return o;
    }

    @Override
    public void update(Mahasiswa o) throws SQLException {
        st = ConnectionHelper.getConnection().prepareStatement("update mahasiswa set nama=?, alamat=? where nim = ?");
        st.setString(1, o.getNim());
        st.setString(2, o.getNama());
        st.setString(3, o.getAlamat());
        st.executeUpdate();
    }

    @Override
    public void delete(String nim) throws SQLException {
        st = ConnectionHelper.getConnection().prepareStatement("delete from mahasiswa where nim=?");
        st.setString(1, nim);
        st.executeUpdate();
    }

    @Override
    public List<Mahasiswa> getAll() throws SQLException {
        Statement st = ConnectionHelper.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from mahasiswa");
        List<Mahasiswa> list = new ArrayList<Mahasiswa>();
        while(rs.next()){
            Mahasiswa  mhs = new Mahasiswa();
            mhs.setNim(rs.getString("nim"));
            mhs.setNama(rs.getString("nama"));
            mhs.setAlamat(rs.getString("alamat"));
            list.add(mhs);
        }
        return list;
    }
}
