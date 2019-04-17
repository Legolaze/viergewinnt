package ch.akratash.viergewinnt.model;

/**
 * 2Dimensionales Array mit 2 Instanzierungsvariabeln
 * und einer Abfrage ob das Spiel over ist
 */
public class Board {

	private Color[][] m_grid;
	private boolean m_gameOver;
	private Color m_activePlayer;
	private Color m_winner;

	/**
	 * Default Zustände des Boards
	 * GameOver Zustand auf false
	 * neues 2 Dimensionales Array 7Felder breit 6Felder tief
	 * Der Rote Spieler beginnt
	 * GewinnerZustand ist enum None zu Beginn des Spiels
	 * 
	 */
	public Board() {
		m_gameOver = false;
		m_grid = new Color[7][6];
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

	public boolean setStone(int column) {
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

	public boolean isGameOver() {
		return m_gameOver;
	}

	private void checkGameOver(int lastInsertedColumn, int lastInsertedRow) {
		Color lastInsertedColor = m_grid[lastInsertedColumn][lastInsertedRow];

		if (checkGameOverColumn(lastInsertedColumn, lastInsertedRow, lastInsertedColor)
				|| checkGameOverRow(lastInsertedColumn, lastInsertedRow, lastInsertedColor)
				|| checkGameOverDiagonalBackslah(lastInsertedColumn, lastInsertedRow, lastInsertedColor)
				|| checkGameOverDiagonalForwardslah(lastInsertedColumn, lastInsertedRow, lastInsertedColor)) {

			m_gameOver = true;
			m_winner = lastInsertedColor;

		}
	}

	/**
	 * Methode zum ermitteln ob einer der Spieler Yellow/Red gewonnen hat.
	 * indem der Count  hochzählt wenn das Array m_gridn über column und row == letzte Farbe ist
	 * mit der Abfrage am Schluss ob der Count die 4 erreicht um das Spiel zu gewinnen
	 * wenn dies der Fall  ist wird boolean result=true und return result zurückgegeben
	 * @param lastInsertedColumn
	 * @param lastInsertedRow
	 * @param lastInsertedColor
	 * @return result
	 */
	private boolean checkGameOverColumn(int lastInsertedColumn, int lastInsertedRow, Color lastInsertedColor) {
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

	private boolean checkGameOverRow(int lastInsertedColumn, int lastInsertedRow, Color lastInsertedColor) {

		boolean result = false;
		int count = 0;
		for (int column = lastInsertedColumn; column >= 0; column--)
			if (m_grid[column][lastInsertedRow] == lastInsertedColor) {
				count++;
			}
		if (count >= 4) {
			result = true;
		}
		return result;
	}

	private boolean checkGameOverDiagonalBackslah(int lastInsertedColumn, int lastInsertedRow,
			Color lastInsertedColor) {
		// TODO
		return false;
	}

	private boolean checkGameOverDiagonalForwardslah(int lastInsertedColumn, int lastInsertedRow,
			Color lastInsertedColor) {
		// TODO
		return false;
	}

	public Color getWinner() {
		return m_winner;
	}
}
