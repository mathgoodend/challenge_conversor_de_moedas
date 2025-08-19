package br.com.conversor.moedas;

import java.math.BigDecimal;
import java.util.Scanner;

public class ApplicationMenu {
    private final String[] currencies = {"BRL", "USD", "EUR", "ARS", "COP", "CLP", "CNY", "JPY", "CAD", "THB"};
    private final String header = """
            ===============================================================
                Seja bem-vindo ao conversor de moedas.
                Selecione opcao desejada e digite o valor para converter.
            -~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-
            """;
    private final String items = """
            [01]  Dolar Americano 	(USD) 	--> 	Real Brasileiro (BRL)
            [02]  Euro              (EUR) 	--> 	Real Brasileiro (BRL)
            [03]  Peso Argentino 	(ARS) 	--> 	Real Brasileiro (BRL)
            [04]  Peso Colombiano 	(COP) 	--> 	Real Brasileiro (BRL)
            [05]  Peso Chileno 	    (CLP) 	--> 	Real Brasileiro (BRL)
            [06]  Yuan Chines 	    (CNY) 	--> 	Real Brasileiro (BRL)
            [07]  Iene Japones 	    (JPY) 	--> 	Real Brasileiro (BRL)
            [08]  Dolar Canadense 	(CAD) 	--> 	Real Brasileiro (BRL)
            [09]  Bhat Tailandes 	(THB) 	--> 	Real Brasileiro (BRL)
            [10]  Libra Esterlina 	(GBP) 	--> 	Real Brasileiro (BRL)
            [00]  Sair do app
            ===============================================================
            """;
    private Conversor conversor;

    public ApplicationMenu(Conversor conversor) {
        this.conversor = conversor;
    }

    private void render() {
        System.out.println(this.header);
        System.out.println(this.items);
    }

    private int setOption(String option, Scanner input) {
        try {
            int numOption = Integer.parseInt(option);

            if (numOption > 0 && numOption <= this.items.length()) {
                this.executeOption(numOption, input);
            } else {
                this.goodbye();
            }

            return numOption;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private void goodbye() {
        System.out.println("Obrigado pro usar o aplicativo, ate logo..");
    }

    private void executeOption(int option, Scanner input) {
        System.out.println("Informe o valor que deseja converter: ");
        String value = input.nextLine()
                .replaceAll("[^0-9.,]", "")
                .replace(",", ".");
        BigDecimal[] res = conversor.convertCurrency(this.currencies[option], value);
        String response = "Conversao: %s (%s) totalizam %.2f (%s) convertidos a %.2f (%s)\n"
                .formatted(
                        value,
                        this.currencies[option],
                        res[0],
                        this.currencies[0],
                        res[1],
                        this.currencies[0]);
        System.out.println(response);
    }

    public void runApplication(Scanner input) {
        int exit = 1;
        while (exit != 0) {
            this.render();
            exit = setOption(input.nextLine().replaceAll("\\D|\\s", ""), input);
        }
    }
}
