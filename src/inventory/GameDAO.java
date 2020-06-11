package inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {
	private final String url;
	private final String username;
	private final String password;

	public GameDAO(String url, String username, String password) {
		super();

		this.url = url;
		this.username = username;
		this.password = password;
	}

	public Game getGame(int id) throws SQLException {
		final String sql = "SELECT * FROM games WHERE game_id = ?";

		Game game = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			String title = rs.getString("title");
			String developer = rs.getString("developer");
			int keys = rs.getInt("keys");
			int available = rs.getInt("available");

			game = new Game(id, title, developer, keys, available);
		}

		rs.close();
		pstmt.close();
		conn.close();

		return game;
	}

	public List<Game> getGames() throws SQLException {
		final String sql = "SELECT * FROM games ORDER BY game_id ASC";

		List<Game> games = new ArrayList<>();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("game_id");
			String title = rs.getString("title");
			String developer = rs.getString("developer");
			int keys = rs.getInt("keys");
			int available = rs.getInt("available");

			games.add(new Game(id, title, developer, keys, available));
		}

		rs.close();
		stmt.close();
		conn.close();

		return games;
	}

	public boolean insertGame(String title, String developer, int keys, int available) throws SQLException {
		final String sql = "INSERT INTO games (title, developer, keys, available) " + "VALUES (?, ?, ?, ?)";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, title);
		pstmt.setString(2, developer);
		pstmt.setInt(3, keys);
		pstmt.setInt(4, available);
		int affected = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return affected == 1;
	}

	public boolean updateGame(Game game) throws SQLException {
		final String sql = "UPDATE games SET title = ?, developer = ?, keys = ?, available = ? " + "WHERE game_id = ?";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, game.getTitle());
		pstmt.setString(2, game.getDeveloper());
		pstmt.setInt(3, game.getKeys());
		pstmt.setInt(4, game.getAvailable());
		pstmt.setInt(5, game.getId());
		int affected = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return affected == 1;
	}

	public boolean deleteGame(Game game) throws SQLException {
		final String sql = "DELETE FROM games WHERE game_id = ?";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, game.getId());
		int affected = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return affected == 1;
	}

	private Connection getConnection() throws SQLException {
		final String driver = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return DriverManager.getConnection(url, username, password);
	}
}