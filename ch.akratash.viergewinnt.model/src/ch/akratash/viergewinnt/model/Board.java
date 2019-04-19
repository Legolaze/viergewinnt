package ch.akratash.viergewinnt.model;

/**
 * Repräsentiert das Spielfeld eines 4 gewinnt Bretts
 * 
 */
public class Board {

	private static final int DEFAULT_COLUMNS = 7;
	private static final int DEFAULT_ROWS = 6;

	/**
	 * 2Dimensionales Array welches den Zustand der gesetzten Steine beinhaltet
	 */
	private Color[][] m_grid;
	private boolean m_gameOver;
	private Color m_activePlayer;
	private Color m_winner;

	/**
	 * Default Zustände des Boards GameOver Zustand auf false neues 2 Dimensionales
	 * Array 7Felder breit 6Felder tief Der Rote Spieler beginnt GewinnerZustand ist
	 * enum None zu Beginn des Spiels
	 * 
	 */
	public Board() {
		this(DEFAULT_COLUMNS, DEFAULT_ROWS);
	}

	public Board(int columns, int rows) {
		m_gameOver = false;
		m_grid = new Color[columns][rows];
		m_activePlayer = Color.RED;
		m_winner = Color.NONE;
		/**
		 * überschreiben der NULL Werte im Array auf den Zustand NONE
		 */
		for (int column = 0; column < m_grid.length; column++) {
			for (int row = 0; row < m_grid[column].length; row++) {
				m_grid[column][row] = Color.NONE;
			}
		}
	}

	/**
	 * Methode um den Spieler nach vollendetem Zug zu wechseln
	 */
	private void switchPlayer() {
		if (m_gameOver) {
			m_activePlayer = Color.NONE;
		} else if (m_activePlayer == Color.RED) {
			m_activePlayer = Color.YELLOW;
		} else if (m_activePlayer == Color.YELLOW) {
			m_activePlayer = Color.RED;
		}
	}

	/**
	 * TBD, ausführlich beschreiben
	 * 
	 * @param column Spalte in welcher der Stein gesetzt wird
	 * @return true falls valider Zug
	 */
	public boolean makeMove(int column) {
		boolean result = false;

		if (column >= 0 && column < m_grid.length) {

			int rowIndex = 0;
			while (rowIndex < m_grid[column].length && m_grid[column][rowIndex] != Color.NONE) {
				rowIndex++;
			}

			if (rowIndex < m_grid[column].length) {
				m_grid[column][rowIndex] = m_activePlayer;
				switchPlayer();
				result = true;
				checkGameOver(column, rowIndex);
			}
		}

		return result;
	}

	/**
	 * Gibt an ob das Spiel vorbei ist.
	 * 
	 * @return true if game is over
	 */
	public boolean isGameOver() {
		return m_gameOver;
	}

	private void checkGameOver(final int lastInsertedColumn, final int lastInsertedRow) {
		Color lastInsertedColor = m_grid[lastInsertedColumn][lastInsertedRow];

		if (!m_gameOver && (checkGameOverColumn(lastInsertedColumn, lastInsertedRow, lastInsertedColor) || checkGameOverRow(lastInsertedColumn, lastInsertedRow, lastInsertedColor)
				|| checkGameOverDiagonalBackslash(lastInsertedColumn, lastInsertedRow, lastInsertedColor)
				|| checkGameOverDiagonalForwardslash(lastInsertedColumn, lastInsertedRow, lastInsertedColor))) {

			m_gameOver = true;
			m_winner = lastInsertedColor;

		}
	}

	/**
	 * Methode zum ermitteln ob einer der Spieler Yellow/Red gewonnen hat.
	 * indem der Count hochzählt wenn das Array m_gridn über column und row == letzte Farbe
	 * ist mit der Abfrage am Schluss ob der Count die 4 erreicht um das Spiel zu
	 * gewinnen wenn dies der Fall ist wird boolean result=true und return result
	 * zurückgegeben
	 * 
	 * @param lastInsertedColumn column index of board of last inserted stone
	 * @param lastInsertedRow row index of board of last inserted stone
	 * @param lastInsertedColor color of last inserted stone
	 * @return result true if game is over, false otherwise
	 */
	private boolean checkGameOverColumn(final int lastInsertedColumn, final int lastInsertedRow, final Color lastInsertedColor) {
		boolean result = false;

		int count = 0;
		for (int row = lastInsertedRow; row >= 0; row--) {
			if (m_grid[lastInsertedColumn][row] == lastInsertedColor) {
				count++;
			}
		}
		if (count >= 4) {
			result = true;
		}
		return result;
	}

	private boolean checkGameOverRow(final int lastInsertedColumn, final int lastInsertedRow, final Color lastInsertedColor) {

		boolean result = false;
		int count = 0;
		for (int column = lastInsertedColumn; column >= 0; column--) {
			if (m_grid[column][lastInsertedRow] == lastInsertedColor) {
				count++;
			}
		}
		for (int column = lastInsertedColumn + 1; column < DEFAULT_COLUMNS; column++) {
			if (m_grid[column][lastInsertedRow] == lastInsertedColor) {
				count++;
			}
		}
		if (count >= 4) {
			result = true;
		}
		return result;
	}

	private boolean checkGameOverDiagonalBackslash(final int lastInsertedColumn, final int lastInsertedRow, final Color lastInsertedColor) {

		boolean result = false;
		int count = 0;
		for (int column = lastInsertedColumn, row = lastInsertedRow; column >= 0 && row < DEFAULT_ROWS; column--, row++) {
			if (m_grid[column][row] == lastInsertedColor) {
				count++;
			}
		}
		for (int column = lastInsertedColumn + 1, row = lastInsertedRow - 1; column < DEFAULT_COLUMNS && row >= 0; column++, row--) {
			if (m_grid[column][row] == lastInsertedColor) {
				count++;
			}
		}
		if (count >= 4) {
			result = true;
		}
		return result;
	}

	private boolean checkGameOverDiagonalForwardslash(final int lastInsertedColumn, final int lastInsertedRow, final Color lastInsertedColor) {
		boolean result = false;
		int count = 0;
		for (int column = lastInsertedColumn, row = lastInsertedRow; column >= 0 && row >= 0; column--, row--) {
			if (m_grid[column][row] == lastInsertedColor) {
				count++;
			}

		}
		for (int column = lastInsertedColumn + 1, row = lastInsertedRow + 1; column < DEFAULT_COLUMNS && row < DEFAULT_ROWS; column++, row++) {
			if (m_grid[column][row] == lastInsertedColor) {
				count++;
			}
		}
		if (count >= 4) {
			result = true;
		}
		return result;
	}

	public Color getWinner() {
		return m_winner;
	}
}
