package tictactoe_package;
import java.util.*;


public class TicTacToe {
	static ArrayList<Integer> playerPositions = new ArrayList<>();
	static ArrayList<Integer> cpuPositions = new ArrayList<>();
	
	public static void printGameBoard(char[] [] gameBoard) {
		//to print out gameboard
				for(int i= 0; i < gameBoard.length; i++) {
					for(int j=0; j<gameBoard[i].length; j++) {
						System.out.print(gameBoard[i][j]);
					}
					System.out.println();
				}
		//Note we could have use for(char[] row: gameBoard)
		//.......................(for char c: row) instead
	}
	public static void placePiece(char[][] gameBoard, int pos, String user) {
		
		char player = ' ';
		if(user.equals("player")){
			player = 'X';
			playerPositions.add(pos);
		}
		else if(user.equals("CPU")) {
			player = '0';
			cpuPositions.add(pos);
		}
		switch(pos) {
			case 1:
				gameBoard[0][0] = player;
				break;
			case 2:
				gameBoard[0][2] = player;
				break;
			case 3:
				gameBoard[0][4] = player;
				break;
			case 4:
				gameBoard[2][0] = player;
				break;
			case 5:
				gameBoard[2][2] = player;
				break;
			case 6:
				gameBoard[2][4] = player;
				break;
			case 7:
				gameBoard[4][0] = player;
				break;
			case 8:
				gameBoard[4][2] = player;
				break;
			case 9:
				gameBoard[4][4] = player;
				break;
			default:
				break;	
		}
		
	}
	@SuppressWarnings("rawtypes")
	public static String checkWinner() {
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);
		
		List leftCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,8);
		List rightCol = Arrays.asList(3,6,9);
		
		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(3,5,7);
		
		List<List> winnings = new ArrayList<List>();
		winnings.add(topRow);
		winnings.add(midRow);
		winnings.add(botRow);
		winnings.add(leftCol);
		winnings.add(midCol);
		winnings.add(rightCol);
		winnings.add(cross1);
		winnings.add(cross2);
		
		for(List l : winnings) {
			if(playerPositions.containsAll(l)) {
				return "Congrats you won!";
			}
			else if(cpuPositions.containsAll(l)) {
				return "CPU wins. ";
			}
			else if(playerPositions.size() + cpuPositions.size() == 9){
				return "Tie game...Board is full";
			}
		}
		return "";
	}
	
	
	public static void main(String[] args) {
		
		char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, 
				{'-', '+', '-', '+', '-'}, 
				{' ', '|', ' ', '|', ' '}, 
				{'-', '+', '-', '+', '-'}, 
				{' ', '|', ' ', '|', ' '}};
		printGameBoard(gameBoard);
		
		while(true) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			
			System.out.print("Enter your placement (1-9): ");
			int playerPos = scan.nextInt();
			while(playerPositions.contains(playerPos)|| cpuPositions.contains(playerPos)
					|| playerPos > 9 || playerPos < 1) {
				System.out.print("Try again. 10Enter your placement (1-9): ");
			playerPos = scan.nextInt();
			}
			
			placePiece(gameBoard, playerPos, "player");
			
			String result = checkWinner();
			if(result.length() > 0) {
				printGameBoard(gameBoard);
				System.out.println(result);
				break;
			}
		
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) +1;
			while(cpuPositions.contains(cpuPos)|| playerPositions.contains(cpuPos)) {
				cpuPos = rand.nextInt(9) +1;
			}
			
			placePiece(gameBoard, cpuPos, "CPU");
			
			printGameBoard(gameBoard);
		
			result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
		}
	}
}

