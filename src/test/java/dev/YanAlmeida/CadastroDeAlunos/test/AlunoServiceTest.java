package dev.YanAlmeida.CadastroDeAlunos.test;

import dev.YanAlmeida.CadastroDeAlunos.service.AlunoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlunoServiceTest {

    @Autowired
    private AlunoService alunoService;

    @Test
    void deveLimparCpfCorretamente() {
        String cpf = "123.456.789-10";
        String resultado = alunoService.limparCpf(cpf);
        assertEquals("12345678910", resultado);
    }

    @Test
    void deveFormatarCpfComMascara() {
        String cpf = "12345678910";
        String resultado = alunoService.formatarCpfComMascara(cpf);
        assertEquals("123.456.789-10", resultado);
    }

    @Test
    void deveRejeitarCpfInvalido() {
        assertThrows(Exception.class, () -> {
            alunoService.validarCpf("111.111.111-11");
        });
    }
}