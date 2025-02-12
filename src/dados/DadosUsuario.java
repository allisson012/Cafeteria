/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dados;

/**
 *
 * @author User
 */
public class DadosUsuario {
    private String nome;
    private String idade;
    private String imagePath;
    private String genero;
    private int id;

    public DadosUsuario() {
    }

    public DadosUsuario(String nome, String idade) {
        this.nome = nome;
        this.idade = idade;
    }
    
    
    public DadosUsuario(String nome, String idade, String imagePath, String genero, int id) {
        this.nome = nome;
        this.idade = idade;
        this.imagePath = imagePath;
        this.genero = genero;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
