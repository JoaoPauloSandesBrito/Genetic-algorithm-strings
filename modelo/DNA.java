/* ***************************************************************
* Autor............: JOAO PAULO SANDES BRITO
* Matricula........: 202110811
* Inicio...........: DESCONHECIDO
* Ultima alteracao.: 18/02/2023
* Nome.............: DNA
* Funcao...........: CRIA DNA A PARTID DE GENES
*************************************************************** */

package modelo;

import java.util.ArrayList;
import java.util.Random;

public class DNA {
    private ArrayList<Gene> Genes = new ArrayList<Gene>(); // DNA gene set
    Random rand = new Random();

    /*
     * ****************************************************************
     * Metodo: adicionarGene
     * Funcao: Adicionar gene rand no objeto DNA
     * Parametros: tamanho do gene a ser adicionado
     * Retorno: null
     */
    public void adicionarGene(int tamanho_Gene) {
        Gene novo = new Gene(tamanho_Gene);
        Genes.add(novo);
    }

    /*
     * ****************************************************************
     * Metodo: addGene
     * Funcao: Adicionar gene especifico no objeto DNA
     * Parametros: gene a ser adicionado
     * Retorno: null
     */
    public void addGene(Gene C) {
        Genes.add(C);
    }

    /*
     * ****************************************************************
     * Metodo: getSize
     * Funcao: Retornar numero de genes no DNA
     * Parametros: null
     * Retorno: Quantidade de genes(int)
     */
    public int getSize() {
        return Genes.size();
    }

    /*
     * ****************************************************************
     * Metodo: crossOver
     * Funcao: Mutar o gene e simular um crossOver
     * Parametros: probabilidade de mutacao
     * Retorno: null
     */
    public void crossOver(double prob) // Gene change by crossover
    {
        for (int j = 0; j < Genes.size(); j++) {
            for (int s = 0; s < Genes.get(j).getGene().length(); s++) {
                Double cross = rand.nextDouble();
                if (cross < prob) {
                    if (Genes.get(j).getGene().charAt(s) == 'A')
                        Genes.get(j).modifyGene(s, "T"); // adenina to timina
                    if (Genes.get(j).getGene().charAt(s) == 'T')
                        Genes.get(j).modifyGene(s, "A"); // timina to adenina
                    if (Genes.get(j).getGene().charAt(s) == 'C')
                        Genes.get(j).modifyGene(s, "G"); // citosina to guanina
                    if (Genes.get(j).getGene().charAt(s) == 'G')
                        Genes.get(j).modifyGene(s, "C"); // guanina to citosina
                }
            }
        }
    }

    /*
     * ****************************************************************
     * Metodo: getGeneDNA
     * Funcao: Retorna um gene especifico do dna
     * Parametros: localizacao do gene
     * Retorno: Gene object
     */
    public String getGeneDNA(int num) {
        try {
            return Genes.get(num).getGene();
        } catch (Exception e) {
            return "Gene not Found";
        }
    }

    /*
     * ****************************************************************
     * Metodo: getGenesDNA
     * Funcao: Retorna todos os genes do dna
     * Parametros: null
     * Retorno: String object of cromossomo
     */
    public String getGenesDNA() { // Returns all genes from DNA
        try {
            String cromossomo = new String();
            for (int n = 0; n < Genes.size(); n++) {
                cromossomo = cromossomo + Genes.get(n).getGene();
                if (n + 1 < Genes.size())
                    cromossomo = cromossomo + " ";
            }
            return cromossomo;
        } catch (Exception e) {
            return "Not Found";
        }
    }
}
