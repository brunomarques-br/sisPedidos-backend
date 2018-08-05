package br.com.sis.pedidos.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String cpf_cnpj;
    private Integer tipo;
    @Getter
    @Setter
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();
    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(name = "tabela_telefone")
    private Set<String> telefones = new HashSet<>();
    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
        super();
    }

    public Cliente(Integer id, String nome, String email, String cpf_cnpj, TipoCliente tipo) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf_cnpj = cpf_cnpj;
        this.tipo = (tipo == null) ? null : tipo.getCod();
    }

    public Cliente(Integer id, String nome, String email) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo.getCod();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
