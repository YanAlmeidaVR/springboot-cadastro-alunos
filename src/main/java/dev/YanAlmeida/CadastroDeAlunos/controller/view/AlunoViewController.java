package dev.YanAlmeida.CadastroDeAlunos.controller.view;

import dev.YanAlmeida.CadastroDeAlunos.dto.alunos.AlunoCreateDTO;
import dev.YanAlmeida.CadastroDeAlunos.dto.alunos.AlunoResponseDTO;
import dev.YanAlmeida.CadastroDeAlunos.service.AlunoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/alunos")
public class AlunoViewController {

    private final AlunoService alunoService;

    public AlunoViewController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alunos", alunoService.listarTodos());
        return "alunos/listar";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("aluno", new AlunoCreateDTO());
        return "alunos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute AlunoCreateDTO aluno) {
        alunoService.save(aluno);
        return "redirect:/web/alunos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        AlunoResponseDTO aluno = alunoService.buscarPorId(id);

        // Converte ResponseDTO para CreateDTO para o formulário
        AlunoCreateDTO alunoDTO = new AlunoCreateDTO();
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setCpf(aluno.getCpf());
        alunoDTO.setEmail(aluno.getEmail());
        alunoDTO.setIdade(aluno.getIdade());

        model.addAttribute("aluno", alunoDTO);
        model.addAttribute("alunoId", id); // Passa o ID para o form
        return "alunos/form";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute AlunoCreateDTO aluno) {
        alunoService.atualizar(id, aluno);
        return "redirect:/web/alunos";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        alunoService.deletar(id);
        return "redirect:/web/alunos";
    }
}