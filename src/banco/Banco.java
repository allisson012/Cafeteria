/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco;
import java.sql.*;
import view.TelaPrincipal;
import dados.Login;
import dados.Produtos;
import dados.CriarConta;
import dados.DadosUsuario;
import dados.Fornecedor;
import dados.FornecedorAtualizado;
import dados.ProdutosCardapio;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import dados.SessaoUsuario;


/**
 *
 * @author User
 */
public class Banco {
private FornecedorAtualizado fornecedorAtbanco;
private Fornecedor fornecedor;
private String nomeFornecedor;
private int idFornecedor;
private SessaoUsuario capturaId;
private Produtos produtos;
private String nome;
private String nomeProduto;
private String marca;
private double preco;
private int quantidade;
private String cnpj;
private String telefone;
private String cep;
private String bairro;
private String rua;
private String numero;
private String emailFornecedor;
private Login logindb;
private CriarConta criarConta;
private  String login;  
private  String email;  
private  String senha;
private  String senha2;
private  String repetirSenha;
private static Connection connection;
private static  Statement statement;
private TelaPrincipal telaPrincipal;
private DadosUsuario dadosUsuario;
private String nomeUsuario;
private String idadeUsuario;

     public Banco()
     {
         telaPrincipal = new TelaPrincipal();
         logindb = new Login();
         fornecedor = new Fornecedor();
         dadosUsuario = new DadosUsuario();
     }
     public static Connection connect(){
         try
         {
             connection = DriverManager.getConnection("jdbc:sqlite:cafeteria.db");
             System.out.println("conexao realizada com sucesso");
             statement = connection.createStatement();
             statement.execute("CREATE TABLE IF NOT EXISTS USUARIOS(EMAIL VARCHAR , SENHA VARCHAR,ID_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT  )");
             statement.execute("CREATE TABLE IF NOT EXISTS FORNECEDORES(NOME VARCHAR , CNPJ VARCHAR , TELEFONE VARCHAR ,EMAIL VARCHAR , CEP VARCHAR , BAIRRO VARCHAR, RUA VARCHAR , NUMERO VARCHAR,ID INTEGER PRIMARY KEY AUTOINCREMENT,VALOR_GASTO DOUBLE DEFAULT 0.0)");
             statement.execute("CREATE TABLE IF NOT EXISTS ESTOQUE (ID INTEGER PRIMARY KEY AUTOINCREMENT ,NOME VARCHAR , MARCA VARCHAR , PRECO DOUBLE ,QUANTIDADE INT,ID_FORNECEDOR INTEGER,FOREIGN KEY (ID_FORNECEDOR) REFERENCES FORNECEDORES(ID))");
             statement.execute("CREATE TABLE IF NOT EXISTS PRODUTOS (ID_VENDA INTEGER PRIMARY KEY AUTOINCREMENT ,NOME VARCHAR, PRECO DOUBLE ,QUANTIDADE INT,IMAGEMPATH VARCHAR)");
             statement.execute("CREATE TABLE IF NOT EXISTS DADOS(NOME VARCHAR , IDADE VARCHAR, IMAGEMPATH VARCHAR , GENERO VARCHAR ,ID_DADOS INTEGER,FOREIGN KEY (ID_DADOS) REFERENCES USUARIOS (ID_USUARIO))");

             return connection;
         }
         catch(SQLException e){
             System.out.println(e.getMessage()); 
             return null;
         }
     }
     public void close() {
    if (connection != null) {
        try {
            connection.close();
            System.out.println("Conexao fechada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
     public static void close(Connection connection)
     {
         if(connection != null)
         {
             try
             {
                 connection.close();
                 System.out.println("conexao fechada com sucesso");
             }
             catch(SQLException e)
             {
                 System.out.println("erro ao fechar conexao "+ e.getMessage());
             }
         }
     }
     public void fazerLogin(Login logindb){
         Banco.connect();
         login = logindb.getEmail();
         senha = logindb.getSenha();
         if(connection != null)
         {
         try{
         PreparedStatement stmt = connection.prepareStatement("SELECT * FROM USUARIOS WHERE EMAIL = ? AND SENHA = ?");
         stmt.setString(1, this.login);
         stmt.setString(2, this.senha);
         ResultSet resultSet = stmt.executeQuery();
         if(resultSet.next()){
             capturaId = new SessaoUsuario();
             capturaId.setId(resultSet.getInt("ID_USUARIO"));
             System.out.println("Login bem-sucedido!");
             JOptionPane.showMessageDialog(null,"Login feito com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
               telaPrincipal.setExtendedState(telaPrincipal.MAXIMIZED_BOTH);
               telaPrincipal.setLocationRelativeTo(null);
               telaPrincipal.setVisible(true);
         }
         else{
             JOptionPane.showMessageDialog(null, "Erro ao realizar login ", "Erro", JOptionPane.ERROR_MESSAGE);
             System.out.println("login ou senha incorretos");
         }
         }
         catch(SQLException e){
            
             e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Erro ao realizar login " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
         }finally
         {
             Banco.close(connection);
         }
         
     }
     }
     public void registrarNoBanco(CriarConta criarConta)
     {
         Banco.connect();
         if(connection != null)
         {
       try{ 
         email = criarConta.getLogin();
         senha = criarConta.getSenha();
        statement.execute("INSERT INTO USUARIOS(EMAIL , SENHA) VALUES ('"+this.email+"' , '"+senha+"') ");
        JOptionPane.showMessageDialog(null,"conta criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        PreparedStatement stmt = connection.prepareStatement("SELECT * from USUARIOS");
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
                 this.email = resultSet.getString("EMAIL");
                 senha2 = resultSet.getString("SENHA");
                
                System.out.println(this.email + " - "+senha2);
        }
           telaPrincipal.setExtendedState(telaPrincipal.MAXIMIZED_BOTH);
           telaPrincipal.setLocationRelativeTo(null);
           telaPrincipal.setVisible(true);
        
       }
       catch(SQLException e)
       {
           System.out.println(e.getMessage());  
           JOptionPane.showMessageDialog(null, "Erro ao criar conta " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
       }finally
       {
           Banco.close(connection);
       }
         }
     }
    public void registrardadosFornecedor(Fornecedor fornecedor)
    {
        Banco.connect();
        this.nome = fornecedor.getNome();
        this.cnpj = fornecedor.getCnpj();
        this.telefone = fornecedor.getTelefone();
        this.emailFornecedor = fornecedor.getEmail();
        this.cep = fornecedor.getCep();
        this.bairro = fornecedor.getBairro();
        this.rua = fornecedor.getRua();
        this.numero = fornecedor.getNumero();
        if(nome != null && !nome.trim().isEmpty() && cnpj != null && !cnpj.trim().isEmpty() && telefone != null && !telefone.trim().isEmpty() && emailFornecedor != null && !emailFornecedor.trim().isEmpty() && cep != null && !cep.trim().isEmpty() && bairro != null && !bairro.trim().isEmpty() && rua != null && !rua.trim().isEmpty() && numero != null && !numero.trim().isEmpty() )
        {
            
        
        if(connection != null)
        {
        try
        {
          statement.execute("INSERT INTO FORNECEDORES(NOME , CNPJ , TELEFONE , EMAIL ,CEP, BAIRRO , RUA , NUMERO) VALUES ('"+this.nome+"','"+this.cnpj+"','"+this.telefone+"','"+this.emailFornecedor+"','"+this.cep+"','"+this.bairro+"','"+this.rua+"','"+this.numero+"')");
          JOptionPane.showMessageDialog(null,"Fornecedor cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } 
        
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
           JOptionPane.showMessageDialog(null, "Erro ao Cadastrar fornecedor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }finally
        {
            Banco.close(connection);
        }
        }
        }
        else
        {
           JOptionPane.showMessageDialog(null, "Erro ao Cadastrar fornecedor algum campo vazio " , "Erro", JOptionPane.ERROR_MESSAGE);
        }
      }
    
    public void deletarFornecedor(String nome)
    {
        String deletarNome = nome;
        Banco.connect();
        if(connection != null)
        {
            try{
                if(deletarNome != null && !deletarNome.trim().isEmpty())
                {
               statement.execute("DELETE  FROM FORNECEDORES WHERE NOME = '"+deletarNome+"' "); 
                System.out.println("deletado com sucesso");
                JOptionPane.showMessageDialog(null,"Fornecedor deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                JOptionPane.showMessageDialog(null, "Nenhum nome digito erro ao deletar fornecedor: ", "Erro", JOptionPane.ERROR_MESSAGE);
 
                }
            }
            catch(SQLException e){
                System.out.println("fornecedor não encontrado "+ e.getMessage());
                 JOptionPane.showMessageDialog(null, "Erro ao deletar fornecedor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }finally{
                Banco.close(connection);
            }
            
        }
    }
    public Fornecedor buscarFornecedor(String nomeFornecedor)
    {
        this.nomeFornecedor = nomeFornecedor;
        Banco.connect();
        fornecedor = new Fornecedor();
       
        if(connection != null)
        {
        try
        {
           PreparedStatement stmt = connection.prepareStatement("SELECT * FROM FORNECEDORES WHERE NOME = '"+this.nomeFornecedor+"'");
           try(ResultSet resultSet = stmt.executeQuery();){
           if(resultSet.next())
           {
             fornecedor.setNome(resultSet.getString("NOME"));
             fornecedor.setCnpj(resultSet.getString("CNPJ"));
             fornecedor.setTelefone(resultSet.getString("TELEFONE"));
             fornecedor.setEmail(resultSet.getString("EMAIL"));
             fornecedor.setCep(resultSet.getString("CEP"));
             fornecedor.setBairro(resultSet.getString("BAIRRO"));
             fornecedor.setRua(resultSet.getString("RUA"));
             fornecedor.setNumero(resultSet.getString("NUMERO"));
             
           }
           }
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }finally{
            Banco.close(connection);
        }
        }
        return fornecedor;
    }
    
    public void editarFornecedor(FornecedorAtualizado fornecedorAtualizado , String nomeAntigo)
    {
        
        fornecedorAtbanco = new FornecedorAtualizado();
        Banco.connect();
        this.fornecedorAtbanco = fornecedorAtualizado;
        StringBuilder sql = new StringBuilder("UPDATE FORNECEDORES SET ");
        boolean first = true;
        if(connection != null)
        {
           
             if (fornecedorAtbanco.getNome() != null && !fornecedorAtbanco.getNome().trim().isEmpty()) {
            sql.append("NOME = '").append(fornecedorAtbanco.getNome()).append("'");
            first = false;
        }

        if (fornecedorAtbanco.getCnpj() != null && !fornecedorAtbanco.getCnpj().trim().isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("CNPJ = '").append(fornecedorAtbanco.getCnpj()).append("'");
            first = false;
        }

        if (fornecedorAtbanco.getTelefone() != null && !fornecedorAtbanco.getTelefone().trim().isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("TELEFONE = '").append(fornecedorAtbanco.getTelefone()).append("'");
            first = false;
        }

        if (fornecedorAtbanco.getEmail() != null && !fornecedorAtbanco.getEmail().trim().isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("EMAIL = '").append(fornecedorAtbanco.getEmail()).append("'");
            first = false;
        }

        if (fornecedorAtbanco.getCep()!= null && !fornecedorAtbanco.getCep().trim().isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("CEP = '").append(fornecedorAtbanco.getCep()).append("'");
            first = false;
        }

        if (fornecedorAtbanco.getBairro() != null && !fornecedorAtbanco.getBairro().trim().isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("BAIRRO = '").append(fornecedorAtbanco.getBairro()).append("'");
            first = false;
        }

        if (fornecedorAtbanco.getRua() != null && !fornecedorAtbanco.getRua().trim().isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("RUA = '").append(fornecedorAtbanco.getRua()).append("'");
            first = false;
        }

        if (fornecedorAtbanco.getNumero() != null && !fornecedorAtbanco.getNumero().trim().isEmpty()) {
            if (!first) sql.append(", ");
            sql.append("NUMERO = '").append(fornecedorAtbanco.getNumero()).append("'");
            first = false;
        }
           if (!first){
            sql.append(" WHERE NOME = '").append(nomeAntigo).append("'");
             System.out.println(sql.toString());
       
            try
            {
             statement.execute(sql.toString());
             JOptionPane.showMessageDialog(null,"Fornecedor editado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(SQLException e)
            {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Erro ao editar fornecedor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }finally
            {
                Banco.close(connection);
            }
           }else
           {
               System.out.println("Nenhum campo válido para atualizar");
                JOptionPane.showMessageDialog(null, "Erro ao editar fornecedor: ", "Erro", JOptionPane.ERROR_MESSAGE);
           }
        }
    }
    
     public DefaultTableModel obterDadosDoBanco() {
        String[] colunas = {"Nome", "Cnpj", "Telefone","Email","Cep","Bairro","Rua","Numero"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:cafeteria.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM FORNECEDORES")) {

            while (rs.next()) {
                nome = rs.getString("NOME");
                cnpj = rs.getString("CNPJ");
                telefone = rs.getString("TELEFONE");
                emailFornecedor = rs.getString("EMAIL");
                cep = rs.getString("CEP");
                bairro = rs.getString("BAIRRO");
                rua = rs.getString("RUA");
                numero = rs.getString("NUMERO");
                model.addRow(new Object[]{nome,cnpj,telefone,emailFornecedor,cep,bairro,rua,numero});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            Banco.close(connection);
        }        
        return model;
    }
     
     public List<String> acharFornecedoresComboBox()
     {
         Banco.connect();
         
        List<String> nomesFornecedores = new ArrayList<>();
    
         if(connection != null)
         {
         try
         {
             String sql = "SELECT NOME FROM FORNECEDORES";
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery();
             
             while(resultSet.next())
             {
                
                 nomeFornecedor = resultSet.getString("NOME");
                 nomesFornecedores.add(nomeFornecedor);
             }
             
             connection.close();
         }
         catch(SQLException e)
                 {
                     e.printStackTrace();
                 }finally
               {
                Banco.close(connection);
               }
         }
          return nomesFornecedores;
     }
     
          public void inserirProduto(String nomeFornecedor , Produtos produtos)
     {
         this.nomeFornecedor = nomeFornecedor;
         connect();
         String sql = "SELECT ID FROM FORNECEDORES WHERE NOME = ?";
         if(connection != null)
         {
             try
             {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, this.nomeFornecedor);
                ResultSet resultSet = stmt.executeQuery();
               if(resultSet.next())
               {
                  idFornecedor = resultSet.getInt("ID");
               }
               
              
            String insertSql = "INSERT INTO ESTOQUE(NOME, MARCA, PRECO, QUANTIDADE, ID_FORNECEDOR) VALUES (?, ?, ?, ?, ?)";
             PreparedStatement insertStmt = connection.prepareStatement(insertSql);
             insertStmt.setString(1, produtos.getNome());
             insertStmt.setString(2, produtos.getMarca());
             insertStmt.setDouble(3, produtos.getPreco());
             insertStmt.setInt(4, produtos.getQuantidade());
             insertStmt.setInt(5, this.idFornecedor);
             insertStmt.executeUpdate();
             JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
             
             }catch(SQLException e)
             {
                 e.printStackTrace();
             }finally
             {
                 close(connection);
             }
         }
         adicionaValorFornecedores();
     }
          
public DefaultTableModel obterDadosDoEstoque() {
        String[] colunas = {"Nome", "Marca", "Preco","Quantidade","Nome fornecedor"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        produtos = new Produtos();
        String sql = "SELECT e.NOME, e.MARCA, e.PRECO, e.QUANTIDADE, f.NOME AS NOME_FORNECEDOR " + "FROM ESTOQUE e " + "JOIN FORNECEDORES f ON e.ID_FORNECEDOR = f.ID";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:cafeteria.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nome = rs.getString("NOME");
                String marca = rs.getString("MARCA");
                double preco = rs.getDouble("PRECO"); 
                int quantidade = rs.getInt("QUANTIDADE");
                String nomeFornecedor = rs.getString("NOME_FORNECEDOR");
                model.addRow(new Object[]{nome, marca, preco, quantidade, nomeFornecedor});            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            Banco.close(connection);
        }        
        return model;
    }
public DefaultTableModel obterDadosDoFornecedor() {
         produtos = new Produtos();
        String[] colunas = {"Nome", "Valor gasto"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        adicionaValorFornecedores();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:cafeteria.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM FORNECEDORES")) {
            
            while (rs.next()) {
                fornecedor.setNome(rs.getString("NOME"));
                fornecedor.setValorGasto(rs.getDouble("VALOR_GASTO"));
               
                model.addRow(new Object[]{fornecedor.getNome(),fornecedor.getValorGasto()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            Banco.close(connection);
        }        
        return model;
    }
public void adicionaValorFornecedores() {
    int id = 0;
    int quantidadeEstoque = 0;
    double precoEstoque = 0;
    double resultado = 0;
    String nomeFornecedor;
    Banco.connect();
    String sqlEstoque = "SELECT * FROM ESTOQUE WHERE ID_FORNECEDOR = ?";
    String sqlFornecedores = "SELECT * FROM FORNECEDORES";
    String sqlUpdate = "UPDATE FORNECEDORES SET VALOR_GASTO = ? WHERE ID = ?";
    double valorFinal = 0;

    if (connection != null) {
        PreparedStatement pstmFornecedores = null;
        ResultSet rtst = null;
        try {
            pstmFornecedores = connection.prepareStatement(sqlFornecedores);
            rtst = pstmFornecedores.executeQuery();

            while (rtst.next()) {
                id = rtst.getInt("ID");
                nomeFornecedor = rtst.getString("NOME");

                PreparedStatement stmtEstoque = null;
                ResultSet resultSet = null;
                try {
                    stmtEstoque = connection.prepareStatement(sqlEstoque);
                    stmtEstoque.setInt(1, id);
                    resultSet = stmtEstoque.executeQuery();

                    while (resultSet.next()) {
                        quantidadeEstoque = resultSet.getInt("QUANTIDADE");
                        precoEstoque = resultSet.getDouble("PRECO");
                        resultado = quantidadeEstoque * precoEstoque;
                        valorFinal += resultado;
                    }

                    System.out.println("Fornecedor: " + nomeFornecedor + " | Valor gasto: " + valorFinal);

                    if (valorFinal != 0) {
                        PreparedStatement pstmUpdate = null;
                        try {
                            pstmUpdate = connection.prepareStatement(sqlUpdate);
                            pstmUpdate.setDouble(1, valorFinal);
                            pstmUpdate.setInt(2, id);
                            pstmUpdate.executeUpdate();
                        } finally {
                            if (pstmUpdate != null) {
                                pstmUpdate.close();
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (stmtEstoque != null) {
                        stmtEstoque.close();
                    }
                }

                valorFinal = 0.0; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rtst != null) {
                try {
                    rtst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmFornecedores != null) {
                try {
                    pstmFornecedores.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally
                {
                    close(connection);
                }
            }
        }
    }
}

   
   
    public String acharNomeFornecedor(int id)
    {
        connect();
        String sql = "SELECT NOME FROM FORNECEDORES WHERE ID = ?"; 
        String nomeParaPassar = "";
        if(connection != null)
        {
            try
            {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1,id);
                ResultSet resultSet = stmt.executeQuery();
                
                if(resultSet.next())
                {
                    nomeParaPassar = resultSet.getString("NOME");
                }
            }catch(SQLException e)
            {
                e.printStackTrace();
            }finally
            {
                Banco.close(connection);
            }
        }
       return nomeParaPassar; 
    }
    
    public void inserirDadosUsuario(DadosUsuario dadosUsuario)
    {
       
        String sql = "INSERT INTO DADOS(NOME,IDADE,IMAGEMPATH,GENERO,ID_DADOS)VALUES(?,?,?,?,?)";
        connection = Banco.connect();
        if(connection != null)
        {
            try
            {
               PreparedStatement stmt = connection.prepareStatement(sql);
               stmt.setString(1, dadosUsuario.getNome());
               stmt.setString(2, dadosUsuario.getIdade());
               stmt.setString(3, dadosUsuario.getImagePath());
               stmt.setString(4, dadosUsuario.getGenero());
               stmt.setInt(5, dadosUsuario.getId());
               stmt.executeUpdate();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }finally
            {
                Banco.close(connection);
            }
        }
    }
        public void inserirDadosCardapio(String nome,String preco,String quantidade,String imagepath)
    {
       
        String sql = "INSERT INTO PRODUTOS(NOME,PRECO,QUANTIDADE,IMAGEMPATH)VALUES(?,?,?,?)";
        connection = Banco.connect();
        if(connection != null)
        {
            try
            {
               PreparedStatement stmt = connection.prepareStatement(sql);
               stmt.setString(1, nome);
               stmt.setString(2, preco);
               stmt.setString(3, quantidade);
               stmt.setString(4, imagepath);
               stmt.executeUpdate();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }finally
            {
                Banco.close(connection);
            }
        }
    }
    public void atualizarDadosUsuario(DadosUsuario dadosUsuario)
    {
        String sql = "UPDATE DADOS SET NOME = ?, IDADE = ?,IMAGEMPATH = ?,GENERO = ? WHERE ID_DADOS = ?";
        connection = Banco.connect();
        if(connection != null)
        {
            try
            {
                PreparedStatement stmt = connection.prepareStatement(sql);
                
                stmt.setString(1, dadosUsuario.getNome());
                stmt.setString(2, dadosUsuario.getIdade());
                stmt.setString(3, dadosUsuario.getImagePath());
                stmt.setString(4, dadosUsuario.getGenero());
                stmt.setInt(5, dadosUsuario.getId());
                stmt.executeUpdate();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }finally
            {
                Banco.close(connection);
            }
        }
    }
 
 public String recuperarImagemDoBanco(int userId) {
    String imagePath = null;
    String sql = "SELECT IMAGEMPATH FROM DADOS WHERE ID_DADOS = ? AND IMAGEMPATH IS NOT NULL";
    try (Connection connection = Banco.connect();
         PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, userId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                imagePath = rs.getString("IMAGEMPATH");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return imagePath;
}


public boolean existeCadastro(int userId) {
    String sql = "SELECT COUNT(*) FROM DADOS WHERE ID_DADOS = ?";
    try (Connection connection = Banco.connect();
         PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, userId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}



    public List<ProdutosCardapio> obterProdutos() {
        Banco.connect();
        List<ProdutosCardapio> produtos = new ArrayList<>();
        String sql = "SELECT NOME, PRECO, QUANTIDADE, IMAGEMPATH FROM produtos";
        if(connection != null)
        {
            
        
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:cafeteria.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String nome = rs.getString("NOME");
                String preco = rs.getString("PRECO");
                String quantidade = rs.getString("QUANTIDADE");
                String caminhoImagem = rs.getString("IMAGEMPATH");
                produtos.add(new ProdutosCardapio(nome,preco,quantidade,caminhoImagem));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally
        {
            Banco.close(connection);
        }
        
        }
        
        return produtos;
    }
 public DadosUsuario acharDadosUsuarioNomeeIdade(int id)
 {
     
       
       Banco.connect();
      if(connection != null)
         {
         try
         {
             String sql = "SELECT * FROM DADOS WHERE ID_DADOS = ?";
             PreparedStatement stmt = null;
             stmt = connection.prepareStatement(sql);
             stmt.setInt(1, id);
             ResultSet resultSet = stmt.executeQuery();
             
             while(resultSet.next())
             {
                
                nomeUsuario  = resultSet.getString("NOME");
                idadeUsuario  = resultSet.getString("IDADE");
               
             }
             
             connection.close();
         }
         catch(SQLException e)
                 {
                     e.printStackTrace();
                 }finally
               {
                Banco.close(connection);
               }
     
 }
   if(nomeUsuario != null && !nomeUsuario.trim().isEmpty() || idadeUsuario!= null && !idadeUsuario.trim().isEmpty())
   {
       DadosUsuario dados = new DadosUsuario(nomeUsuario,idadeUsuario);
    return dados;
   }
   else
   {
           DadosUsuario dados = new DadosUsuario();
           dados.setNome("");
           dados.setIdade("");
       return dados;
   }
}
}
