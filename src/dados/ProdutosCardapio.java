/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dados;

/**
 *
 * @author User
 */
public class ProdutosCardapio {
    private String nome;
    private String preco;
    private String quantidade;
    private String caminho;

    public ProdutosCardapio(String nome, String preco, String quantidade, String caminho) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.caminho = caminho;
    }

    public ProdutosCardapio() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
    
}
