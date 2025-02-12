/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package main;
import view.TelaLogin;
import banco.Banco;

public class Main {

    
    public static void main(String[] args) {
        System.out.println("comeceando a executar");
     TelaLogin tela = new TelaLogin();
      Banco banco = new Banco();
      try
      {
      banco.connect();
      }finally
      {
          banco.close();
      }
      tela.setExtendedState(TelaLogin.MAXIMIZED_BOTH);
      tela.setLocationRelativeTo(null);
      tela.setVisible(true);
    }

}
