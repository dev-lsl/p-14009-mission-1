package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 ==");
        Scanner scanner = new Scanner(System.in);
        int id = 0;

        //클래스 리스트 생성
        List<WiseSayings> wiseSayingsList = new ArrayList<>();

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String wiseSay = scanner.nextLine().trim();

                System.out.print("작가 : ");
                String author = scanner.nextLine().trim();
                id++;

                //입력받은 명언 리스트에 저장
                wiseSayingsList.add(new WiseSayings(id, wiseSay, author));


                System.out.printf("%d번 명언이 등록되었습니다.\n", id);
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");

                //리스트에 저장된 명언들 다꺼내서 출력
                for (int i = 0; i <= wiseSayingsList.size() - 1; i++) {
                    // wiseSayings 클래스 변수 하나 만들고 거기다가 리스트에 있는 것들 계속 집어넣고 출력
                    WiseSayings w1 = wiseSayingsList.get(i);
                    System.out.printf("%d / %s / %s \n", w1.id, w1.wiseSay, w1.author);
                }
            } else if (cmd.startsWith("삭제?id=")) {
                //"삭제?id="의 다음 입력을 받아서 int로 변환.
                int idToDelete = Integer.parseInt(cmd.substring("삭제?id=".length()));

                boolean found = false;
                // 입력받은 삭제id와 리스트에 아이디를 비교해야함 -> 비교하기위한 리스트 모든 값 호출
                for (int i = 0; i < wiseSayingsList.size(); i++) {
                    int delNum = wiseSayingsList.get(i).id;
                    if (delNum == idToDelete) {
                        wiseSayingsList.remove(idToDelete - 1);
                        found = true;
                        System.out.printf("%d번 명언이 삭제되었습니다.\n", idToDelete);
                        break;
                    }
                }
                if (found == false) {
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n", idToDelete);
                }
            } else if (cmd.startsWith("수정?id=")) {
                //수정받을 id 받아서 int로 변환
                int idToModify = Integer.parseInt(cmd.substring("수정?id=".length()));

                boolean found = false;

                //리스트 호출하여 id랑 비교
                for (int i = 0; i < wiseSayingsList.size(); i++) {
                    int modifyNum = wiseSayingsList.get(i).id;
                    if (modifyNum == idToModify) {
                        System.out.printf("명언(기존) : %s\n", wiseSayingsList.get(idToModify - 1).wiseSay);
                        System.out.print("명언 : ");
                        String modifyWiseSay = scanner.nextLine().trim();

                        System.out.printf("작가(기존) : %s\n", wiseSayingsList.get(idToModify - 1).author);
                        System.out.print("명언 : ");
                        String modifyAuthor = scanner.nextLine().trim();

                        wiseSayingsList.remove(idToModify - 1);
                        wiseSayingsList.add(new WiseSayings(idToModify, modifyWiseSay, modifyAuthor));
                        found = true;
                        System.out.println("수정 완료");
                        break;
                    }
                }
                //입력받은 id가 없을경우
                if (found == false) {
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n", idToModify);
                }

            }
        }
    }
}
