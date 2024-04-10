package game;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RankingTable extends JFrame {

    public RankingTable() {
        Conexion conn = new Conexion();
        

        // Datos para la tabla
        Object[][] data = conn.showAllDataFromTable("Player");

        JButton backButton = new JButton("Regresar");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartScreen startScreen = new StartScreen();
                startScreen.setLocation(RankingTable.this.getLocation());
                startScreen.setVisible(true);
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(backButton);

        // Agrega el JPanel al JFrame antes del panel existente
        getContentPane().add(buttonPanel, BorderLayout.NORTH);

        // Nombres de las columnas
        String[] columnNames = {"Nombre", "Puntos", "Tiempo"};

        // Crea una tabla con los datos y nombres de las columnas
        JTable table = new JTable(data, columnNames);

        // Asegúrate de que la tabla no sea editable
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(tableModel);

        // Establece el tamaño preferido de la tabla
        table.setPreferredScrollableViewportSize(new Dimension(350, 500));

        // Agrega la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Crea un JPanel con FlowLayout y agrega el JScrollPane a este
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(scrollPane);

        // Configura el layout del JFrame
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Agrega un Box.Filler para mover la tabla un poco más abajo
        add(new Box.Filler(new Dimension(0, 50), new Dimension(0, 50), new Dimension(0, 50)));

        // Agrega el JPanel al JFrame
        add(panel);

        // Configura el JFrame
        setTitle("Ranking de Partidas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 700); // Establece el tamaño de la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        new RankingTable();
    }
}