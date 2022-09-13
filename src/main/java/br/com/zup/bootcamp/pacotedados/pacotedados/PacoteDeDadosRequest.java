package br.com.zup.bootcamp.pacotedados.pacotedados;

import br.com.zup.bootcamp.pacotedados.util.CpfUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class PacoteDeDadosRequest {

    @NotBlank
    private String nomeTitular;
    @NotBlank
    @CPF
    private String cpfTitular;
    @NotBlank
    @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}$")
    private String numeroCelular;
    @Min(5)
    @Max(50)
    @NotNull
    private Integer quantidadeDadosEmGb;
   // @CreationTimestamp
    private LocalDate cadastradoEm;


    public String getNomeTitular() {
        return nomeTitular;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public Integer getQuantidadeDadosEmGb() {
        return quantidadeDadosEmGb;
    }

    public LocalDate getCadastradoEm() {
        return cadastradoEm;
    }

    /**
     * Gera hash do cpf
     */
    public String getHashDoCpf() {
        return CpfUtils.hash(cpfTitular);
    }

    public PacoteDeDados toModel() {
        return new PacoteDeDados(nomeTitular,cpfTitular,numeroCelular,quantidadeDadosEmGb,cadastradoEm);
    }
}
