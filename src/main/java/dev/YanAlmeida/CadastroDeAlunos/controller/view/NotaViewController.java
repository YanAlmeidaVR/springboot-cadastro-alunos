package dev.YanAlmeida.CadastroDeAlunos.controller.view;

import dev.YanAlmeida.CadastroDeAlunos.dto.notas.NotaCreateDTO;
import dev.YanAlmeida.CadastroDeAlunos.service.AlunoService;
import dev.YanAlmeida.CadastroDeAlunos.service.NotaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/notas")
public class NotaViewController {

    private final NotaService notaService;
    private final AlunoService alunoService;

    public NotaViewController(NotaService notaService, AlunoService alunoService) {
        this.notaService = notaService;
        this.alunoService = alunoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("notas", notaService.listarTodas());
        return "notas/listar";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("nota", new NotaCreateDTO());
        model.addAttribute("alunos", alunoService.listarTodos());
        return "notas/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute NotaCreateDTO nota) {
        notaService.save(nota);
        return "redirect:/web/notas";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        notaService.deletar(id);
        return "redirect:/web/notas";
    }

}
