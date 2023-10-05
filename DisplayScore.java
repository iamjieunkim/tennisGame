package Project1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

public class DisplayScore {
	

	//LocalTime elapsedTime = LocalTime.now(); 		// Play Time 계산을 위한 변수, 테니스 게임을 시작하는 순간의 시간
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); // 시간 출력 형식
	
	String [][] scoreBoard = createScoreBoard();	// 계수기, 클래스 전반적으로 쓰이기 때문에 전역변수로 선언
	String logFileNames;							// Log 파일 이름을 지정하기 위한 변수
	int temp;										// 세트가 종료될 때 다음 세트 기록을 위해 계수기의 주소를 증가시키기 위한 변수
	
	// 입력받은 이름을 Log 파일 제목에 반영하기 전 가공
	public void getNames(String[] name) {
		logFileNames = Arrays.toString(name);
	}
	
	// 계수기 생성 메소드
	private String[][] createScoreBoard() {
		
		scoreBoard = new String [3][8];		
		String [] menu = {"Team","Point","Set 1","Set 2","Set 3","Set 4","Set 5","Match"};
		// 배열의 0번째 행 - 카테고리로 초기화
		for (int i = 0; i < menu.length; i++) {
			scoreBoard[0][i]=menu[i];
		}
		// 이름 입력되는 공간을 제외한 나머지 부분을 0으로 초기화
		for (int i = 1; i < scoreBoard.length; i++) {
  			for (int j = 1; j < scoreBoard[i].length; j++) {
  				scoreBoard[i][j]=Integer.toString(0);
			}
		}
		
		// 계수기 생성 완료 -> 전역변수 scoreboard로 반환
		return scoreBoard;
	}
	
	// 계수기에 선수 이름 반영하는 메소드
	public void setScoreBoardName(String[] names) {
		
		switch (names.length) {
		case 2: // 단식 경기
			scoreBoard[1][0] = names[0];
			scoreBoard[2][0] = names[1];
			break;

		case 4: // 복식 경기
			scoreBoard[1][0] = names[0].charAt(0) + "&" + names[1].charAt(0);
			scoreBoard[2][0] = names[2].charAt(0) + "&" + names[3].charAt(0);
			break;
		}
	}
	
	// 포인트를 계수기에 반영 및 출력하는 메소드
	public void printScore(String score1, String score2) {

		scoreBoard[1][1] = score1;
		scoreBoard[2][1] = score2;
		dispScoreBoard();
	}

	// 게임스코어를 계수기에 반영 및 출력하는 메소드
	public void printGame(int a, String gameScore) {
		
		int i = temp;
		
		
		//큰 보드에서 세트 칸에 점수를 부여, 점수를 주면서 다음칸으로 넘어가는것
		if( a == 0) scoreBoard[1][2 + i] = gameScore;
		else scoreBoard[2][2 + i] = gameScore;
		
		// 양 측의 점수 차이 계(두선수의 점수 차)
		//int sbd = Math.abs(Integer.parseInt(scoreBoard[1][2+i]) - Integer.parseInt(scoreBoard[2][2 + i]));
		int minus = Math.abs(Integer.parseInt(scoreBoard[1][2+i]) - Integer.parseInt(scoreBoard[2][2 + i]));
		
		// 2게임차 이상이면서 먼저 6게임을 딴 경우 또는 타이브레이크 상황에서 7게임을 먼저 딴 경우
		// 현재 주소에는 더이상 기록하지 않고 다음 세트를 기록하는 주소로 이동하기 위해 temp를 증가시킴
		for (int j = 1; j < 3; j++) {
			//if(scoreBoard[j][2 + i].contains("6") && sbd >= 2) temp++;
			if(scoreBoard[j][2 + i].contains("6") && minus >= 2) temp++; //이겼을때 세트 다음 세트로 넘어감
			else if(scoreBoard[j][2 + i].contains("7")) temp++; //이겼을때 다음세트로 넘어감
		}
		
		//printBoard();
		dispScoreBoard();
	}

	// 세트스코어를 계수기에 반영 및 출력하는 메소드
	public void printSet(int a, String setScore) {
		
		if( a == 0) scoreBoard[1][7] = setScore; //선수 1의 Match 값
		else scoreBoard[2][7] = setScore; ////선수 2의 Match 값
		//printBoard();
		dispScoreBoard();
		printWinnerName();
	}
	
	

	// Log 파일 생성 메소드
	public void creattxtfile(String[][] scoreBoard, String logFileNames) {
		
		LocalDate todayLog = LocalDate.now();   // Log 파일 제목에 현재 날짜를 반영하기 위한 객체 생성
		Date dayLog = new Date();				// Log가 기록될 때 현재 시간을 기록하기 위한 객체 생성
	
	    String fileName = System.getProperty("user.home") + "/" + todayLog + logFileNames + " GameLog.txt";
	    
		
		try {
			FileWriter fw = new FileWriter(fileName, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.printf("Current Time: \n" + sdf.format(dayLog));
			pw.println();
			pw.println("-".repeat(90));
			for (int i = 0; i < scoreBoard.length; i++) {
				for (String line : scoreBoard[i]) {
					pw.print(line+"\t");
				}
				pw.println();
				pw.println("-".repeat(90));
			}
			pw.println("\r\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	// 계수기 출력 메소드
	//public void printBoard() {
	public void dispScoreBoard() {

		Date today = new Date();				 // 현재 날짜를 반영하기 위한 객체 생성
		LocalTime todayBoard = LocalTime.now();  // 게임 시작 시의 시간 elapsedTime과의 차이를 계산하여 Play Time을 반영
		
		System.out.printf("테니스 게임 일자: " + sdf.format(today));
		System.out.println();
		System.out.println("■".repeat(21) + "⭐🎾👟천하제일 테니스 대회🎾👟⭐" + "■".repeat(21));
		System.out.println("=".repeat(74));

		for (int i = 0; i < scoreBoard.length; i++) {
			
			for (int j = 0; j < scoreBoard[i].length; j++) {
				System.out.printf("\t%s", scoreBoard[i][j]);
			}
			System.out.println();
			System.out.println("=".repeat(74));
			
		}
		System.out.println("\n");
		
		// 콘솔 또는 실행 파일에 출력하면서 Log파일에 기록
		creattxtfile(scoreBoard, logFileNames);
	}
	private void printWinnerName() {
		//if (Integer.parseInt(scoreBoard[1][7]) > Integer.parseInt(scoreBoard[2][7])) {
		if (Integer.parseInt(scoreBoard[1][7]) > Integer.parseInt(scoreBoard[2][7])) {
			System.out.printf("\t\t\t    🥇 최종 승자 🥇 : %s\n", scoreBoard[1][0]);
		} else if (Integer.parseInt(scoreBoard[1][7]) < Integer.parseInt(scoreBoard[2][7])) {
			System.out.printf("\t\t\t    🥇 최종 승자 🥇 : %s\n", scoreBoard[2][0]);
		}
		
	}
	
}
