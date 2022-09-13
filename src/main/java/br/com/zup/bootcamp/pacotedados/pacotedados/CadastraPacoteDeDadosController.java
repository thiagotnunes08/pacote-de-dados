package br.com.zup.bootcamp.pacotedados.pacotedados;

import br.com.zup.bootcamp.pacotedados.util.CpfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/pacote-de-dados")
public class CadastraPacoteDeDadosController {
    @Autowired
    private final PacoteDeDadosRepository repository;

    public CadastraPacoteDeDadosController(PacoteDeDadosRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@Valid @RequestBody PacoteDeDadosRequest request, UriComponentsBuilder uriComponentsBuilder){


        if (repository.existsByHashCpf(request.getHashDoCpf())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Usuário já possui esse pacote de dados!");
        }

        PacoteDeDados novoPacote = request.toModel();

        repository.save(novoPacote);

        URI location = uriComponentsBuilder.path("pacote-de-dados/{id}").buildAndExpand(novoPacote.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
    /**
     * @ExceptionHandler = menssagem amigavel local
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> nossoHandle(ConstraintViolationException ex){

        Map<String, Object> body = Map.of("menssagem:","Usuário já possui esse pacote de dados!",
                                           "correu em:", LocalDateTime.now(),
                                            "status code:","422");

        return ResponseEntity.unprocessableEntity().body(body);
    }
}
