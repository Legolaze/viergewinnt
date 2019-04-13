package ch.akratash.viergewinnt.model;

public class Board {

	private Color[][] m_grid;
	private boolean m_gameOver;
	private Color m_activePlayer;
	private Color m_winner;

	public Board() {
		m_gameOver = false;
		m_grid = new Color[7][6];
		m_activePlayer = Color.RED;
		m_winner = Color.NONE;

		for (int column = 0; column < m_grid.length; column++) {
			for (int row = 0; row < m_grid[column].length; row++) {
				m_grid[column][row] = Color.NONE;
			}
		}

	}

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
		// TODO [akratash] implement
		return false;
	}

	private boolean checkGameOverDiagonalBackslah(int lastInsertedColumn, int lastInsertedRow,
			Color lastInsertedColor) {
		// TODO [akratash] implement
		return false;
	}

	private boolean checkGameOverDiagonalForwardslah(int lastInsertedColumn, int lastInsertedRow,
			Color lastInsertedColor) {
		// TODO [akratash] implement
		return false;
	}

	public Color getWinner() {
		return m_winner;
	}
}
