package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<WiseSayings> wiseSayingsList = new ArrayList<>();
    static int id = 0;

    public static void main(String[] args) {

        System.out.println("== 명언 ==");


        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            String baseCmd = cmd.contains("?") ? (cmd.split("\\?")[0]) : (cmd);

            switch (baseCmd) {
                case "종료":
                    return;
                case "등록":
                    handleRegister();
                    break;
                case "목록":
                    handleList();
                    break;
                case "삭제":
                    handlesDelete(cmd);
                    break;
                case "수정":
                    handleModify(cmd);
                    break;
            }

        }
    }


    //등록 메소드
    public static void handleRegister() {
        System.out.print("명언 : ");
        String wiseSay = scanner.nextLine().trim();

        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();
        id++;

        //입력받은 명언 리스트에 저장
        wiseSayingsList.add(new WiseSayings(id, wiseSay, author));


        System.out.printf("%d번 명언이 등록되었습니다.\n", id);

    }

    //목록 메소드
    public static void handleList() {
        System.out.println("번호 / 작가 / 명언");

        //리스트에 저장된 명언들 다꺼내서 출력
        for (int i = 0; i <= wiseSayingsList.size() - 1; i++) {
            // wiseSayings 클래스 변수 하나 만들고 거기다가 리스트에 있는 것들 계속 집어넣고 출력
            WiseSayings w1 = wiseSayingsList.get(i);
            System.out.printf("%d / %s / %s \n", w1.id, w1.wiseSay, w1.author);
        }

    }

    //삭제 메소드
    public static void handlesDelete(String cmd) {
        int idToDelete = Integer.parseInt(cmd.substring("삭제?id=".length()));

        boolean found = false;
        // 입력받은 삭제id와 리스트에 아이디를 비교해야함 -> 비교하기위한 리스트 모든 값 호출
        for (int i = 0; i < wiseSayingsList.size(); i++) {

            if (wiseSayingsList.get(i).id == idToDelete) {
                wiseSayingsList.remove(i);
                found = true;
                System.out.printf("%d번 명언이 삭제되었습니다.\n", idToDelete);
                break;
            }
        }
        if (found == false) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", idToDelete);

        }


    }

    //수정 메소드
    public static void handleModify(String cmd) {
        int idTomod = Integer.parseInt(cmd.substring(6));
        boolean foundMod = false;

        for (int i = 0; i < wiseSayingsList.size(); i++) {
            if (idTomod == wiseSayingsList.get(i).id) {
                System.out.printf("명언(기존) : %s\n", wiseSayingsList.get(i).wiseSay);
                System.out.print("명언 : ");
                String modifyWise = scanner.nextLine().trim();
                System.out.printf("작가(기존) : %s\n", wiseSayingsList.get(i).author);
                System.out.print("작가 : ");
                String modifyAuthor = scanner.nextLine().trim();

                wiseSayingsList.get(i).wiseSay = modifyWise;
                wiseSayingsList.get(i).author = modifyAuthor;
                System.out.println("수정 완료");

                foundMod = true;
                break;

            }
        }
        if (foundMod == false) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", idTomod);
        }

    }

}
