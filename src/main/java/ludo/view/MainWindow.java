package ludo.view;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import ludo.controller.GameController;
import ludo.model.Color;

import javax.swing.*;

public class MainWindow extends javax.swing.JFrame {
    // MainWindow acumula funções, mas ok
    private GameController gameController = new GameController(this);

    // Pawn moving game attributes
    private int steps;
    private boolean timeToMove = false;
    private ArrayList<String> movablePawns;
    private String myColor;
    private boolean BATATA = false;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        // Set title
        setTitle("Ludo dos campeões");

        // TODO: REMOVE THIS
        this.freeDice();
        this.BATATA = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        // Carrega imagem
        File img = new File("img/ludo.png");
        boardPanel = new BoardView(img, this);
        diceLabel = new DiceView();
        rollButton = new javax.swing.JButton();
        historicoScrollPane = new javax.swing.JScrollPane();
        historicoPane = new javax.swing.JTextPane();
        historicoLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        conexMenu = new javax.swing.JMenu();
        serHostMenuItem = new javax.swing.JMenuItem();
        conectarMenuItem = new javax.swing.JMenuItem();
        ajudaMenu = new javax.swing.JMenu();
        sobreMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        boardPanel.setBackground(new java.awt.Color(255, 51, 204));
        boardPanel.setAlignmentX(0.0F);
        boardPanel.setAlignmentY(0.0F);
        boardPanel.setEnabled(false);
        boardPanel.setMinimumSize(new java.awt.Dimension(705, 705));
        boardPanel.setPreferredSize(new java.awt.Dimension(705, 705));
        boardPanel.setRequestFocusEnabled(false);
        boardPanel.setLayout(new java.awt.GridLayout(15, 15));

        rollButton.setText("Rolar dado");
        rollButton.setEnabled(false);
        rollButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rollButtonActionPerformed(evt);
            }
        });

        historicoScrollPane.setAutoscrolls(true);

        historicoPane.setEditable(false);
        historicoPane.setFocusable(false);
        historicoScrollPane.setViewportView(historicoPane);

        historicoLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        historicoLabel.setLabelFor(historicoPane);
        historicoLabel.setText("Histórico da partida");

        statusLabel.setText("-");

        conexMenu.setText("Conexão");

        serHostMenuItem.setText("Ser host");
        serHostMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serHostMenuItemActionPerformed(evt);
            }
        });
        conexMenu.add(serHostMenuItem);

        conectarMenuItem.setText("Conectar");
        conectarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectarMenuItemActionPerformed(evt);
            }
        });
        conexMenu.add(conectarMenuItem);

        menuBar.add(conexMenu);

        ajudaMenu.setText("Ajuda");

        sobreMenuItem.setText("Sobre");
        sobreMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sobreMenuItemActionPerformed(evt);
            }
        });
        ajudaMenu.add(sobreMenuItem);

        menuBar.add(ajudaMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(diceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(historicoLabel)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(rollButton, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                        .addComponent(historicoScrollPane)))))))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(diceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(rollButton)
                        .addGap(20, 20, 20)
                        .addComponent(historicoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(historicoScrollPane))
                    .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(statusLabel)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void invalidPawnWarning() {
        JOptionPane.showMessageDialog(this, "Selecione uma peça válida!", "Peça inválida", JOptionPane.ERROR_MESSAGE);
    }

    public void pawnSelected(Color squareColor, String type, int pos) {
        System.out.println(squareColor.toString() + type.toString() + pos);
        if(!this.timeToMove) return;
        // Check if the color of the square is the same as the player
        if(!Objects.equals(squareColor.toString(), this.myColor)) {
            this.invalidPawnWarning();
            return;
        };
        //System.out.println(squareColor.toString() + type.toString() + pos);
        // Compare the square code with the movable pawns locations
        String code = type + pos;
        if(type.equals("B")) code = type;
        // Check if pawn exists in movable pawns
        if(!this.movablePawns.contains(code)) {
            this.invalidPawnWarning();
            return;
        }
        // get pawn code index of
        int index = this.movablePawns.indexOf(code);
        System.out.println(index + " " + this.steps);
        if(this.BATATA) this.steps = 50;
        this.gameController.movePawn(index, this.steps);
        // TODO: time to move false
    }

    public void pawnSelected(String type, int pos) {
        System.out.println(type.toString() + pos);
        if(!this.timeToMove) return;
        String code = type + pos;
        if(!this.movablePawns.contains(code)) {
            this.invalidPawnWarning();
            return;
        }
        int index = this.movablePawns.indexOf(code);
        this.gameController.movePawn(index, this.steps);
    }

    public void handlePawnSelectionResponse() {
        EnumMap<Color, String[]> newState = this.gameController.getGameState().toMap();
        this.boardPanel.updateBoard(newState);
    }

    private void rollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollButtonActionPerformed
        this.steps = this.gameController.rollDice();
        //this.lockDice();
        // call setValue from DiceView
        DiceView diceView = (DiceView) diceLabel;
        diceView.setValue(this.steps);
        // update pane
        statusLabel.setText("Você rolou " + this.steps + "!");
        //historicoPane.setText(historicoPane.getText() + "\n" + this.gameController.getMyColor().toString().toUpperCase() + " rolou " + this.steps + "\n");

        // TODO: Remnove this
        //this.gameController.sendGameState();
        // Dice is rolled and updated. Get valid moves for the player.
        // TODO: cuidado com isso
        this.timeToMove = true;
        ArrayList<String> movablePawns = this.gameController.getMovablePawns(this.steps);
        this.movablePawns = movablePawns;
        System.out.println("Movable pawns: " + this.movablePawns.toString());
        this.myColor = this.gameController.getMyColor().toString();
        System.out.println("My color: " + this.myColor.toString());
        // TODO: if array length == 0, passa a vez
        // passa a vez = manda game state igual
    }//GEN-LAST:event_rollButtonActionPerformed

    private void serHostMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        try {//GEN-FIRST:event_serHostMenuItemActionPerformed
            String localIP = String.valueOf(InetAddress.getLocalHost().getHostAddress());
            String message = String.format("Seu IP é: %s", localIP);
            JOptionPane.showMessageDialog(this, message, "Seu IP", JOptionPane.INFORMATION_MESSAGE);
            // Disable all options
            this.conectarMenuItem.setEnabled(false);
            this.serHostMenuItem.setEnabled(false);
            this.gameController.createGame();
        }  catch (Exception ex) {
            System.out.println("Erro ao obter IP local");
        }
    }//GEN-LAST:event_serHostMenuItemActionPerformed

    private void sobreMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sobreMenuItemActionPerformed
        // Criar Dialog de informações
        // StringBuilder pro HTML não bugar
        StringBuilder sb = new StringBuilder();
        sb.append("<html><b>LUDO DOS CAMPEÕES</b><br>");
        sb.append("Idealizado e programado pelos seguintes cornos:<br><ul>");
        sb.append("<li>Daniel Henrique Serezane Pereira<br>");
        sb.append("<li>Guilherme Silva Batalhoti<br>");
        sb.append("<li>Gustavo Becelli do Nacimento<br>");
        sb.append("</ul>FCT/UNESP - 2023</html>");
        JOptionPane.showMessageDialog(this, sb.toString(), "Sobre", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_sobreMenuItemActionPerformed

    private void conectarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectarMenuItemActionPerformed
        String ip = JOptionPane.showInputDialog(this, "Digite o IP do host", "Conectar", JOptionPane.QUESTION_MESSAGE);
        // validate IP address
        if(ip == null || ip.isEmpty() || ip.isBlank() || !ip.matches("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}")) {
            JOptionPane.showMessageDialog(this, "IP inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        InetAddress address = null;
        try {
            address = InetAddress.getByName(ip);
            // try to connect to server
            boolean res = this.gameController.joinGame(address);
            if (!res) {
                JOptionPane.showMessageDialog(this, "Erro na conexão.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(this, "Conectado com sucesso.", "Conectado", JOptionPane.INFORMATION_MESSAGE);
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(this, "Erro na conexão.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        // connect to server
    }//GEN-LAST:event_conectarMenuItemActionPerformed

    public void startGame() {
        // Block host
        serHostMenuItem.setEnabled(false);
        conectarMenuItem.setEnabled(false);
        // Clear historicoPane
        historicoPane.setText("");
        // Seta o tabuleiro para as posições iniciais
        boardPanel.clearBoard();
    }

    public void freeDice() {
        rollButton.setEnabled(true);
    }

    public void lockDice() {
        rollButton.setEnabled(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ajudaMenu;
    private BoardView boardPanel;
    private javax.swing.JMenuItem conectarMenuItem;
    private javax.swing.JMenu conexMenu;
    private javax.swing.JLabel diceLabel;
    private javax.swing.JLabel historicoLabel;
    private javax.swing.JTextPane historicoPane;
    private javax.swing.JScrollPane historicoScrollPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton rollButton;
    private javax.swing.JMenuItem serHostMenuItem;
    private javax.swing.JMenuItem sobreMenuItem;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
}
