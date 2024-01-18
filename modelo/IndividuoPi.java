/* ***************************************************************
* Autor............: JOAO PAULO SANDES BRITO
* Matricula........: 202110811
* Inicio...........: DESCONHECIDO
* Ultima alteracao.: 18/02/2023
* Nome.............: INDIVIDUOPI
* Funcao...........: CRIA UM INDIVIDUO PARA TESTES
*************************************************************** */

package modelo;

import java.util.Random;

public class IndividuoPi implements Individuo {
    DNA A;
    DNA B;
    static int RG = 0;
    int myRG = 0;
    int crossOver_prob = 10;

    Random rand = new Random();

    public IndividuoPi(DNA a, DNA b) { // Start an individual with heredity
        this.A = a;
        this.B = b;
        RG = RG + 1;
        myRG = RG;
    }

    public IndividuoPi(int tam_gene, int cross_prob) { // Starts an individual with spontaneous generation
        this.A = new DNA();
        this.B = new DNA();

        this.crossOver_prob = cross_prob;

        this.A.adicionarGene(tam_gene);
        this.B.adicionarGene(tam_gene);
        RG = RG + 1;
        myRG = RG;
    }

    public double fitness() { // Classify the individual with a grade
        int fit = 0;
        for (int n = 0; n < this.A.getGenesDNA().length(); n++) {
            if (this.A.getGenesDNA().charAt(n) == 'A')
                fit = fit + 1;
        }
        for (int n = 0; n < this.B.getGenesDNA().length(); n++) {
            if (this.B.getGenesDNA().charAt(n) == 'A')
                fit = fit + 1;
        }
        return fit;
    }

    public int getRG() { // Returns the creation order of the individual in the program
        return myRG;
    }

    public DNA getA() { // Returns DNA received by a parent
        return this.A;
    }

    public DNA getB() { // Returns DNA received by a parent
        return this.B;
    }

    public DNA gerate_gamete() { // Generates a gamete by randomly choosing DNA from the parents
        DNA X = new DNA();

        if (this.A.getSize() == this.B.getSize()) {
            for (int u = 0; u < this.A.getSize(); u++) {
                String GeneA = this.A.getGeneDNA(u);
                String GeneB = this.B.getGeneDNA(u);

                String Gameta = new String("");
                for (int c = 0; c < GeneB.length(); c++) {
                    if (rand.nextDouble() < 0.5)
                        Gameta = Gameta + GeneA.charAt(c);
                    else
                        Gameta = Gameta + GeneB.charAt(c);
                }
                Gene x = new Gene(Gameta);
                X.addGene(x);
            }
            X.crossOver(crossOver_prob/100);
        }
        return X;
    }
}