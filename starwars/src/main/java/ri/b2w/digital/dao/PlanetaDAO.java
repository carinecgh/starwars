/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ri.b2w.digital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ri.b2w.digital.bean.Planeta;
import ri.b2w.digital.util.Conexao;

/**
 *
 * @author Carine Henriques
 */
public class PlanetaDAO {

    public void inserir(Planeta planeta) throws SQLException, ClassNotFoundException {
        try {
            Conexao db = new Conexao();
            Connection con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO tb_planetas (id, nm_nome, nm_clima, nm_terreno, qt_aparicao, dt_inclusao) VALUES (nextval('tb_planetas_id_seq'::regclass), ?, ?, ?, ?, current_date)");
            pstmt.setString(1, planeta.getNome());
            pstmt.setString(2, planeta.getClima());
            pstmt.setString(3, planeta.getTerreno());
            pstmt.setInt(4, planeta.getQuantidadeAparicao());
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException e) {
            throw e;
        }
    }

    public int excluir(long id) throws SQLException, ClassNotFoundException {
        int linhasAfetadas = 0;

        try {
            Conexao db = new Conexao();
            Connection con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM tb_planetas WHERE id = ?");
            pstmt.setLong(1, id);
            linhasAfetadas = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw e;
        }

        return linhasAfetadas;
    }

    public List<Planeta> pesquisar(String clausula) throws SQLException, ClassNotFoundException {
        try {
            Conexao db = new Conexao();
            Connection con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM tb_planetas WHERE " + clausula);
            ResultSet rs = pstmt.executeQuery();
            List<Planeta> planetas = new ArrayList<>();
            while(rs.next()) {
//System.out.println(parser(rs).toString());
                planetas.add(parser(rs));
            }
            rs.close();
            pstmt.close();
            
            return planetas;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Planeta> listar() throws SQLException, ClassNotFoundException {
        try {           
            Conexao db = new Conexao();
            Connection con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM tb_planetas ORDER BY id");
            ResultSet rs = pstmt.executeQuery();
            List<Planeta> planetas = new ArrayList<>();
            while (rs.next()) {
                planetas.add(parser(rs));
            }

            rs.close();
            pstmt.close();
            
            return planetas;
        } catch (SQLException e) {
            throw e;
        }
    }

    private Planeta parser(ResultSet rs) throws SQLException {
        Planeta p = new Planeta();

        p.setId(rs.getLong("id"));
        p.setNome(rs.getString("nm_nome"));
        p.setClima(rs.getString("nm_clima"));
        p.setTerreno(rs.getString("nm_terreno"));
        p.setQuantidadeAparicao(rs.getInt("qt_aparicao"));

        return p;
    }
}
