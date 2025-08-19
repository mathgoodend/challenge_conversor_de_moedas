import br.com.conversor.moedas.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HttpsHandler https = new HttpsHandler(System.getenv("API_KEY"));
        Conversor conversor = new Conversor(https);
        ApplicationMenu menu = new ApplicationMenu(conversor);
        Scanner input = new Scanner(System.in);

        menu.runApplication(input);
    }
}