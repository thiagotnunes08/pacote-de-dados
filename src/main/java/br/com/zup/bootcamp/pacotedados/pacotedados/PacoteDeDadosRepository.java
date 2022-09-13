package br.com.zup.bootcamp.pacotedados.pacotedados;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacoteDeDadosRepository extends JpaRepository<PacoteDeDados,Long> {
    boolean existsByHashCpf(String cpfTitular);
}
