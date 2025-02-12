/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import dados.Fornecedor;
import dados.Produtos;
import javax.swing.JOptionPane;
import banco.Banco;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author User
 */
public class TelaCadastrarItens extends javax.swing.JFrame {
private Fornecedor fornecedorSelecionado;
private Produtos produtos;
private Banco banco;
private String nome;
private String marca;
private String preco;
private double precoconvertido;
private String quantidade;
private int quantidadeconvertido;
private List<String> nomesFornecedores;


    /**
     * Creates new form TelaCadastrarProduto
     */
    public TelaCadastrarItens() {
        initComponents();
        banco = new Banco();
        nomesFornecedores = banco.acharFornecedoresComboBox();
        setupComboBox();
    }

  private void setupComboBox() {
    jComboBoxFornecedores.removeAllItems();  

    for (String nome : nomesFornecedores) {
        jComboBoxFornecedores.addItem(nome);  
    }

    jComboBoxFornecedores.revalidate(); 
    jComboBoxFornecedores.repaint();
    
    for (java.awt.event.ActionListener al : jComboBoxFornecedores.getActionListeners()) {
        jComboBoxFornecedores.removeActionListener(al);
    }
    jComboBoxFornecedores.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBoxFornecedoresActionPerformed(evt);
        }
    });
  }      



  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        CampoNome = new javax.swing.JTextField();
        CampoMarca = new javax.swing.JTextField();
        CampoPreco = new javax.swing.JTextField();
        CampoQuantidade = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jComboBoxFornecedores = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(null);

        CampoNome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CampoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoNomeActionPerformed(evt);
            }
        });
        jPanel1.add(CampoNome);
        CampoNome.setBounds(400, 270, 230, 40);

        CampoMarca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(CampoMarca);
        CampoMarca.setBounds(400, 360, 230, 40);

        CampoPreco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(CampoPreco);
        CampoPreco.setBounds(400, 440, 230, 40);

        CampoQuantidade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(CampoQuantidade);
        CampoQuantidade.setBounds(470, 530, 200, 40);

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/concluir-removebg-preview.png"))); // NOI18N
        jToggleButton1.setText("concluir");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButton1);
        jToggleButton1.setBounds(893, 613, 120, 40);

        jComboBoxFornecedores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFornecedoresActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxFornecedores);
        jComboBoxFornecedores.setBounds(790, 260, 140, 22);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagem/cadastrarEstoque.png"))); // NOI18N
        jLabel5.setText("jLabel5");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(0, 0, 1380, 768);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1302, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
   
        nome = CampoNome.getText();
        marca = CampoMarca.getText();
        preco = CampoPreco.getText();
        quantidade = CampoQuantidade.getText();
        if(nome != null && !nome.trim().isEmpty() && marca != null && !marca.trim().isEmpty() && preco != null && !preco.trim().isEmpty() && quantidade != null && !quantidade.trim().isEmpty())
        { 
        try
        {
        precoconvertido = Double.parseDouble(preco);
        quantidadeconvertido = Integer.parseInt(quantidade);
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos para preço e quantidade.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        produtos = new Produtos(nome,marca,precoconvertido,quantidadeconvertido);
        
        System.out.println(quantidadeconvertido);
        System.out.println(precoconvertido);
       String nomeFornecedorSelecionado = (String) jComboBoxFornecedores.getSelectedItem();
       banco.inserirProduto(nomeFornecedorSelecionado,produtos);
        }
        else
        {
         JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jComboBoxFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFornecedoresActionPerformed

    String fornecedorSelecionado = (String) jComboBoxFornecedores.getSelectedItem();
    if (fornecedorSelecionado != null) {
        JOptionPane.showMessageDialog(this, 
            "Fornecedor Selecionado: " + fornecedorSelecionado, 
            "Fornecedor Selecionado", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    }//GEN-LAST:event_jComboBoxFornecedoresActionPerformed

    private void CampoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoNomeActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CampoMarca;
    private javax.swing.JTextField CampoNome;
    private javax.swing.JTextField CampoPreco;
    private javax.swing.JTextField CampoQuantidade;
    private javax.swing.JComboBox<String> jComboBoxFornecedores;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
