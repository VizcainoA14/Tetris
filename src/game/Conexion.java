package game;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Conexion {
    private static final String user = "tetris_user";
    private static final String password = "zU0tao7Tc3H66wOdlBZQ2ofwG67ctZzx"; //C:\Users\Andres\Downloads Cambiar por la contraseña real
    String url = "jdbc:postgresql://dpg-coauhri1hbls73fudilg-a.oregon-postgres.render.com:5432/tetris?sslmode=require";


    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver"); 
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa!"); 
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conn;
    }

    public Object[][] showAllDataFromTable(String tableName) {
        List<Object[]> tempList = new ArrayList<>();
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT Game.ID_Game, Player.Name AS PlayerName, Game.Points, Game.Time FROM Game JOIN Player ON Game.ID_Player = Player.ID_Player ORDER BY Game.Points DESC, Game.Time ASC;");

            while (rs.next()) {
                String playerName = rs.getString("PlayerName");
                String gamePoints = rs.getString("Points");
                String gameTime = rs.getString("Time");
                tempList.add(new Object[] { playerName, gamePoints, gameTime });
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener los datos de la tabla: " + e.getMessage());
        }

        Object[][] results = new Object[tempList.size()][];
        for (int i = 0; i < tempList.size(); i++) {
            results[i] = tempList.get(i);
        }
        return results;
    }
    
    public void insertgame(String name, int points, double time) {
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Player (Name) VALUES ('" + name + "') ON CONFLICT DO NOTHING;");
            stmt.executeUpdate("INSERT INTO Game (ID_Player, Points, Time) VALUES ((SELECT ID_Player FROM Player WHERE Name = '" + name + "'), " + points + ", " + time + ");");
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar datos en la tabla: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        Conexion conn = new Conexion();
        conn.showAllDataFromTable("Player");
        
    }
}
