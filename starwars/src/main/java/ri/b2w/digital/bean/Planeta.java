/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ri.b2w.digital.bean;

import java.util.Objects;

/**
 *
 * @author Carine Henriques
 */
public class Planeta {
    private long id;
    private String nome;
    private String clima;
    private String terreno;
    private int quantidadeAparicao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public int getQuantidadeAparicao() {
        return quantidadeAparicao;
    }

    public void setQuantidadeAparicao(int quantidadeAparicao) {
        this.quantidadeAparicao = quantidadeAparicao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.clima);
        hash = 23 * hash + Objects.hashCode(this.terreno);
        hash = 23 * hash + this.quantidadeAparicao;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Planeta other = (Planeta) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quantidadeAparicao != other.quantidadeAparicao) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.clima, other.clima)) {
            return false;
        }
        if (!Objects.equals(this.terreno, other.terreno)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Planeta{" + "id=" + id + ", nome=" + nome + ", clima=" + clima + ", terreno=" + terreno + ", quantidadeAparicao=" + quantidadeAparicao + '}';
    }

}
