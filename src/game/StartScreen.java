package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StartScreen extends JFrame {

    private Font customFont;

    public StartScreen() {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font\\Inter-VariableFont_slnt,wght.ttf"))
                    .deriveFont(Font.BOLD | Font.ITALIC); // Aplica negrita y cursiva
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            // registra la fuente
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            ((Throwable) e).printStackTrace();
        }
        initUI();
    }

    private void initUI() {
        setLayout(null); // Desactiva el administrador de diseño

        // Crea un JPanel con una imagen de fondo
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("C://Users/adria//Desktop/Tetris//font//tetris.jpg"));
                    // Escala la imagen
                    Image scaledBackgroundImage = backgroundImage.getScaledInstance(1280, 800, Image.SCALE_SMOOTH);
                    g.drawImage(scaledBackgroundImage, -100, 0, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 450, 700);// Asegúrate de que el panel cubra toda la ventana

        JLabel titleLabel = new JLabel("Tetris");
        titleLabel.setForeground(Color.WHITE); // Establece el color del texto
        titleLabel.setFont(customFont.deriveFont(90f)); // Aplica el tamaño de la fuente
        titleLabel.setBounds(-30, 100, 500, 150); // Posiciona el título
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton startButton = new JButton("START GAME");
        startButton.setBounds(150, 300, 150, 50); // Posiciona el botón de inicio
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter your name:");
                if (name != null && !name.isEmpty()) {
                    // Muestra los comandos del juego
                    JOptionPane.showMessageDialog(null, "Comandos del juego:\n" +
                            "Tecla ARRIBA: Rotar a la derecha\n" +
                            "Tecla ABAJO: Rotar a la izquierda\n" +
                            "Tecla IZQUIERDA: Mover a la izquierda\n" +
                            "Tecla DERECHA: Mover a la derecha\n" +
                            "Tecla ESPACIO: Caída instantánea\n" +
                            "Tecla D: un espacio hacia abajo\n" +
                            "Tecla P: Pausar el juego");
                    new Tetris(name).setVisible(true);
                    dispose();
                }
            }
        });

        JButton exitButton = new JButton("RANKING");
        exitButton.setBounds(150, 400, 150, 50); // Posiciona el botón de salida
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RankingTable rankingTable = new RankingTable();
                rankingTable.setLocation(StartScreen.this.getLocation()); // Establece la ubicación de la nueva ventana
                rankingTable.setVisible(true);
                dispose();
            }
        });

        // Agrega los componentes al panel de fondo en lugar de a la ventana
        // directamente
        backgroundPanel.add(titleLabel);
        backgroundPanel.add(startButton);
        backgroundPanel.add(exitButton);

        // Agrega el panel de fondo a la ventana
        add(backgroundPanel);

        setTitle("Tetris Start Screen");
        setSize(450, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            StartScreen ex = new StartScreen();
            ex.setVisible(true);
        });
    }
}