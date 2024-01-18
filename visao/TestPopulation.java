package visao;

import controle.Populacao;
import modelo.IndividuoPi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class TestPopulation {
    public static void main(String[] args) {
        File arquivo = new File("testPopulation.txt"); // creates the file on disk with the name testPop.txt

        int pop_qtd = 10;
        int geracao = 0;

        pop_qtd = Integer.parseInt(JOptionPane.showInputDialog("Choose population size:"));

        Populacao Brazil = new Populacao(pop_qtd,10,10);
        Brazil.arrange();

        System.out.println("Geracao:" + geracao);
        System.out.println(Brazil.see_pop(0)); // Show initial population in order of fitness
        try {
            FileWriter fw = new FileWriter(arquivo, true); // Open the file to write
            fw.write("Generation:" + geracao + "\n");
            fw.write(Brazil.see_pop(0)); // add generation info to the file
            fw.close(); // close the file
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        while (true) {
            geracao = geracao + 1;

            for (int r = 0; r < ((pop_qtd - pop_qtd % 2) / 2); r++) { // Generate new individuals
                Brazil.addIndividuo(new IndividuoPi(Brazil.getIndividuo(r * 2).gerate_gamete(),
                        Brazil.getIndividuo(r * 2 + 1).gerate_gamete()));
            }

            Brazil.arrange();
            Brazil.maxPop(pop_qtd); // Kills the weakest individuals
            System.out.println("Geracao:" + geracao);
            System.out.println(Brazil.see_pop(0)); // Show population in order of fitness

            try {
                FileWriter fw = new FileWriter(arquivo, true); // Open the file to write
                fw.write("\nGeneration:" + geracao + "\n");
                fw.write(Brazil.see_pop(0)); // add generation info to the file
                fw.close(); // close the file
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (Brazil.maxFitness() >= 15)
                break;
        }
        System.out.println("END");

    }
}
