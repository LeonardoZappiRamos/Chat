package br.unisales.crud.repositorio;

import br.unisales.crud.modelo.Contato;
import br.unisales.crud.persistencia.MySqlFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
public class ContatoDAO {
 
private MySqlFactory factory;
 
public ContatoDAO(MySqlFactory factory) {
    this.factory = factory;
}
 
public void insere(Contato t) throws Exception {
    try (Connection connection = MySqlFactory.getCon()) {
        PreparedStatement ps = connection.prepareStatement("insert into contato(nome, telefone) values (?, ?) ");
        ps.setObject(1, t.getNome());
        ps.setObject(2, t.getTelefone());
        ps.execute();
        try {
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
        }
    }
}
 
public Contato seleciona(Integer id) throws Exception {
    try (Connection connection = MySqlFactory.getCon()) {
    PreparedStatement ps = connection.prepareStatement("select id, nome, telefone from contato where id = ? ");
    ps.setObject(1, id);
    ResultSet rs = ps.executeQuery();
    Contato p = new Contato();
    if (rs.next()) {
        p.setId(id);
        p.setNome(rs.getString("nome"));
        p.setTelefone(rs.getString("telefone"));
    }
    return p;
    }
}
 
public List<Contato> selecionaVarios(Object... e) throws Exception {
    try (Connection connection = MySqlFactory.getCon()) {
        PreparedStatement ps = connection.prepareStatement("select id, nome, telefone from contato order by nome ");
        ResultSet rs = ps.executeQuery();
        List<Contato> lista = new ArrayList();
        while (rs.next()) {
            Contato p = new Contato();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setTelefone(rs.getString("telefone"));
            lista.add(p);
        }
        return lista;
    }
}
 
public void exclui(Contato t) throws Exception {
    try (Connection connection = MySqlFactory.getCon()) {
        PreparedStatement ps = connection.prepareStatement("delete from contato where id = ? ");
        ps.setObject(1, t.getId());
        ps.execute();
        try {
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
        }
    }
}
 
public void atualiza(Contato t) throws Exception {
    try (Connection connection = MySqlFactory.getCon()) {
        PreparedStatement ps = connection.prepareStatement("update contato set nome = ?, telefone = ? where id = ? ");
        ps.setObject(1, t.getNome());
        ps.setObject(2, t.getTelefone());
        ps.setObject(3, t.getId());
        ps.execute();
        try {
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
        }
    }
}
 
}