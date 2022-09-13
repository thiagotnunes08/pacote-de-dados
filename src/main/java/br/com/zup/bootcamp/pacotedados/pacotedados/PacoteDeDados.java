package br.com.zup.bootcamp.pacotedados.pacotedados;

import br.com.zup.bootcamp.pacotedados.util.CpfUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Table(uniqueConstraints = @UniqueConstraint(name = "UK_hash_do_CPF",columnNames = "hashCpf"))
@Entity
public class PacoteDeDados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeTitular;

    @Column(nullable = false)
    private String cpfTitular;

    @Column(nullable = false,length = 64)
    private String hashCpf;

    @Column(nullable = false)
    private String numeroCelular;

    @Column(nullable = false)
    private Integer quantidadeDadosEmGb;

    @CreationTimestamp
    private LocalDate cadastradoEm;

    /**
     * @Deprecated = uso exclusivo do hibernate
     */
    @Deprecated
    public PacoteDeDados() {
    }

    public PacoteDeDados(String nomeTitular, String cpfTitular, String numeroCelular, Integer quantidadeDadosEmGb, LocalDate cadastradoEm) {
        this.nomeTitular = nomeTitular;
        this.cpfTitular = CpfUtils.anonymize(cpfTitular);
        this.hashCpf = CpfUtils.hash(cpfTitular);
        this.numeroCelular = numeroCelular;
        this.quantidadeDadosEmGb = quantidadeDadosEmGb;
        this.cadastradoEm = cadastradoEm;
    }

    public Long getId() {
        return id;
    }
}
