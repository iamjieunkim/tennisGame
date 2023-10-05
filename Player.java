package Project1;
import java.io.IOException;
import java.util.Scanner;

//Game 세팅에 필요한 입력 관련 처리 클래스
public class Player extends GameElement{

   Scanner sc = new Scanner(System.in);
      
   public void createPlayer() {
      
      // 게임 방식 입력
      System.out.println("■".repeat(2)+"⭐🎾👟천하제일 테니스 대회🎾👟⭐"+"■".repeat(2));
      System.out.println("------------단/복식 선택------------");
      System.out.println("1. 단식게임");
      System.out.println("2. 복식게임");
      System.out.print("게임방식을 번호로 입력 : ");
      int soloDuo = sc.nextInt();
      int setNum;
      while (!(soloDuo == 1||soloDuo ==2)) {
         System.out.println("올바른 입력이 아닙니다.");
         System.out.print("다시 게임 방식을 입력하시오 (1.단식 2.복식) :  ");
         soloDuo = sc.nextInt();
         
         try {
            System.in.skip(System.in.available());
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      // 이름을 저장할 배열의 크기를 단식이면 2, 복식이면 4로 입력 방식에 따라 값을 할당하겠다.
      String [] name = new String [soloDuo * 2];

      // 단식을 선택한 경우 - 세트수 선택
      if(soloDuo == 1) {
         System.out.println("-------------세트 선택--------------");
         System.out.println("1. 5세트 게임");
         System.out.println("2. 3세트 게임");
         System.out.print("세트를 번호로 입력 : ");
         setNum = sc.nextInt();
         while (!(setNum == 1||setNum ==2)) {
            System.out.println("올바른 입력이 아닙니다.");
            System.out.print(" > 다시 세트 수를 입력하세요. ( 1번:(5세트), 2번:(3세트) ) ");
            setNum = sc.nextInt();
            try {
               System.in.skip(System.in.available());
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
      // 복식을 선택한 경우 - 세트수 선택
      else {
         System.out.println("-------------세트 선택--------------");
         System.out.println("1. 5세트 게임");
         System.out.println("2. 3세트 게임");
         System.out.print("세트를 번호로 입력 : ");
         setNum = sc.nextInt();
         while (!(setNum == 1||setNum == 2)) {
            System.out.println("올바른 입력이 아닙니다.");
            System.out.print(" > 다시 세트 수를 입력하세요. ( 1번:(5세트), 2번:(3세트) ) ");
            setNum = sc.nextInt();
            try {
               System.in.skip(System.in.available());
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
      
      if(setNum == 1) {
         setNum = 3;
      }
      else {
         setNum = 2;
      }
      
      for (int i = 0; i < name.length; i++) {
            System.out.printf("[%d번] 선수 이름을 입력하세요 :  ", i + 1);
            name[i] = sc.next();
            
            while (!checkKorean(name[i])) {
               System.out.printf("⚠️🚨한글이름으로 3글자 이하로 다시 작성해주세요🚨⚠️ : ");
               name[i] = sc.next();
            }  
         }

         this.setName(name);
         this.setSetNum(setNum);
         this.setSoloDuo(soloDuo);

   }

   private boolean checkKorean(String input) {
       return input.matches("^[가-힣]{1,3}$"); //1부터 3자리 한글자! 자음+모음
   }
   
}