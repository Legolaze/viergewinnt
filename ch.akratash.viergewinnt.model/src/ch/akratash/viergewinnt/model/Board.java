package ch.akratash.viergewinnt.model;

public class Board {

	private Color[][] m_grid;
	private boolean m_gameOver;
	private Color m_activePlayer;

	public Board() {
		m_gameOver = false;
		m_grid = new Color[7][6];
		m_activePlayer = Color.RED;

		for (Color[] column : m_grid) {
			for (Color field : column) {
				field = Color.NONE;
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
			while (m_grid[column][rowIndex] != Color.NONE) {
				rowIndex++;
			}

			if (rowIndex < m_grid[column].length) {
				m_grid[column][rowIndex] = m_activePlayer;
				switchPlayer();
				result = true;
			}
		}

		return result;
	}
}
