package nextstep.ladder.view;

import java.io.PrintStream;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PrintStream out = System.out;
    public static final String GET_NAMES_MESSAGE = "참여할 사람 이름을 입력하세요.";
    public static final String GET_HEIGHT_MESSAGE = "최대 사다리 높이는 몇 개인가요?";

    public static String getNames() {
        out.println(GET_NAMES_MESSAGE);
        return scanner.nextLine();
    }

    public static int getHeight() {
        out.println(GET_HEIGHT_MESSAGE);
        return Integer.parseInt(scanner.nextLine());
    }
}