/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import dados.SessaoUsuario;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import banco.Banco;
import dados.DadosUsuario;
/**
 *
 * @author User
 */
public class TelaPerfil extends javax.swing.JFrame {
    private JPanel contentPane;
    private FileInputStream fis;
    private String imagePath;
    private JButton jButton1;
    private JLabel picLabel;
    private JTextField campoNome;
    private JTextField campoIdade;
    private JComboBox<String> genero;
    private JLabel photoLabel;
    private SessaoUsuario capturaId;
    private JButton botaoConcluir;
    private Banco banco;
    private DadosUsuario dadosUsuario;
    private DadosUsuario dados;
    private String generoPegar;
    private String nome;
    private String idade;
    /**
     * Creates new form test02
     */
    public TelaPerfil() {
        initComponents();
        this.setTitle("Tela Perfil"); 
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.setSize(1400, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); 
        contentPane.setLayout(null);
        setContentPane(contentPane);
        capturaId = new SessaoUsuario();
        banco = new Banco();
        String imagePathFromDB = banco.recuperarImagemDoBanco(capturaId.getId());
        if (imagePathFromDB != null && !imagePathFromDB.isEmpty())
        { 
            picLabel = new JLabel(new ImageIcon(imagePathFromDB)); 
            imagePath = imagePathFromDB;
        }
        else {
            picLabel = new JLabel(new ImageIcon(getClass().getResource("iconfoto.png")));
        }
        dados = new DadosUsuario();
        picLabel.setBounds(900, 250, 300, 250);
        contentPane.add(picLabel);
        botaoConcluir = new JButton(new ImageIcon(getClass().getResource("concluir-removebg-preview.png")));
        botaoConcluir.setBounds(220, 680, 150, 30);
        botaoConcluir.addActionListener(e -> botaoConcluirMetodo());
        contentPane.add(botaoConcluir);
        jButton1 = new JButton(new ImageIcon(getClass().getResource("selecionarsemfundo.png")));
        jButton1.setBounds(1000, 550, 150, 30);
        jButton1.addActionListener(e -> selecionarImagem());
        contentPane.add(jButton1);
        setupComboBox();
        contentPane.add(genero);
        int id = capturaId.getId();
        dados = banco.acharDadosUsuarioNomeeIdade(id);
        campoNome = new JTextField();
        campoNome.setText("");
        if(dados.getNome() != null && !dados.getNome().trim().isEmpty())
        {
        campoNome.setText(dados.getNome());
        }
        campoNome.setBorder(new LineBorder(Color.BLACK, 1));
        campoNome.setBounds(350, 250, 250,50);
        contentPane.add(campoNome);
        campoIdade = new JTextField();
        if(dados.getIdade() != null && !dados.getIdade().trim().isEmpty())
        {
        campoIdade.setText(dados.getIdade());
        }
        else 
        {
            campoIdade.setText("");
        }
        campoIdade.setBorder(new LineBorder(Color.BLACK , 1));
        campoIdade.setBounds(350, 335, 250, 50);
        contentPane.add(campoIdade);
        photoLabel = new JLabel(new ImageIcon(getClass().getResource("perfil.png")));
        photoLabel.setBounds(-10, -10, 1400, 800);
        contentPane.add(photoLabel);
        System.out.println("id = "+capturaId.getId());
        
        
         this.setVisible(true);

    }
    private void selecionarImagem() {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Selecionar Arquivo");
        jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de Imagens (*.PNG, *.JPG, *.JPEG)", "png", "jpg", "jpeg"));
        int resultado = jfc.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = jfc.getSelectedFile();
                imagePath = selectedFile.getAbsolutePath();
                System.out.println("Caminho da imagem selecionada: " + imagePath);
                carregarImagem();  
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void carregarImagem() throws IOException {
        BufferedImage myPicture = ImageIO.read(new File(imagePath)); 
        picLabel.setIcon(new ImageIcon(myPicture));
        contentPane.revalidate();
        contentPane.repaint();
    }
      private void setupComboBox() {
          String [] generos = {"Masculino","Feminino","Prefiro não dizer","Outro"};
          genero = new JComboBox<>(generos);
          genero.setBounds(350, 440, 150, 30);
     
  }
private void botaoConcluirMetodo()
      {
          
          dadosUsuario = new DadosUsuario();
          generoPegar = (String) genero.getSelectedItem();
          nome = campoNome.getText();
          idade = campoIdade.getText();
          dadosUsuario.setId(capturaId.getId());
          dadosUsuario.setNome(nome);
          dadosUsuario.setIdade(idade);
          dadosUsuario.setImagePath(imagePath);
          dadosUsuario.setGenero(generoPegar);
          
if (banco.existeCadastro(capturaId.getId()))
{ 
banco.atualizarDadosUsuario(dadosUsuario);
JOptionPane.showMessageDialog(this, "Dados atualizados com sucesso!"); 
} 
else
{ 
banco.inserirDadosUsuario(dadosUsuario); 
JOptionPane.showMessageDialog(this, "Dados inseridos com sucesso!"); 
} 
}
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
