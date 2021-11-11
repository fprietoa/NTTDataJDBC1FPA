package com.everis.fpaFatima_jdbc_taller1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * Clase principal
 * 
 * @author fprietoa
 *
 */
public class NTTDataJDBCMain {

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Conexión y ejecución de consulta a BD (MySQL)
		stablishMDBConnection();
	}

	private static void stablishMDBConnection() {

		try {

			// Se establece el driver de conexión a BBDD
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Apertura de conexión con BBDD (URL / Usuario / Contraseña)
			final Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nttdata_javajdbc?user=root&password=rootroot");

			// Realización de la consulta.
			final Statement sentence = dbConnection.createStatement();
			final String query = "SELECT jugadores.name AS playerName, equipo.name AS teamName, jugadores.first_rol AS rol1, jugadores.second_rol AS rol2, birth_date AS birthD FROM nttdata_mysql_soccer_player AS jugadores JOIN nttdata_mysql_soccer_team AS equipo WHERE jugadores.id_soccer_team = equipo.id_soccer_team;";
			final ResultSet queryResult = sentence.executeQuery(query);

			// Iteración de resultados.
			StringBuilder playerInfo = new StringBuilder();
			while (queryResult.next()) {

				playerInfo.append("Nombre: ");
				playerInfo.append(queryResult.getString("playerName"));

				playerInfo.append(" | Equipo: ");
				playerInfo.append(queryResult.getString("teamName"));

				playerInfo.append(" | Demarcación: ");
				playerInfo.append(queryResult.getString("rol1"));

				playerInfo.append(" | Demarcación alternativa: ");
				playerInfo.append(queryResult.getString("rol2"));

				playerInfo.append(" | Fecha nacimiento: ");
				playerInfo.append(queryResult.getDate("birthD"));

				playerInfo.append("\n");

			}

			System.out.println(playerInfo.toString());

			// Cierre de conexión con BBDD.

		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("Error al registrar el driver de MySQL: " + ex);
		}

	}
}
