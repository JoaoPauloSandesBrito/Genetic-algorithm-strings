/* ***************************************************************
* Autor............: JOAO PAULO SANDES BRITO
* Matricula........: 202110811
* Inicio...........: DESCONHECIDO
* Ultima alteracao.: 18/02/2023
* Nome.............: GENE
* Funcao...........: CRIA SEQUENCIA DE GENE
*************************************************************** */

package modelo;

import java.util.Random;

public class Gene {
    Random rand = new Random();
    private String sequence = new String(); // Nitrogen base sequence

    /* ****************************************************************
     * Metodo: Gene
     * Funcao: construtor
     * Parametros: pode ser iniciado tanto com uma sequencia quanto por um tamanho
     * Retorno: objeto
     */
    public Gene(String seq) { // Starts a gene with predefined sequence
        this.sequence = seq;
    }

    public Gene(int size) { // Starts a gene with random sequence
        for (int i = 0; i < size; i++) {
            double choice = rand.nextDouble();
            if ((choice >= 0) && (choice < 0.25))
                this.sequence = this.sequence + "A"; // Adenina
            if ((choice >= 0.25) && (choice < 0.5))
                this.sequence = this.sequence + "C"; // Citosina
            if ((choice >= 0.5) && (choice < 0.75))
                this.sequence = this.sequence + "G"; // Guanina
            if ((choice >= 0.75) && (choice <= 1))
                this.sequence = this.sequence + "T"; // Timina
        }
    }

    /* ****************************************************************
     * Metodo: getGene
     * Funcao: Retorna a sequencia de bases nitrogenadas
     * Parametros: null
     * Retorno: String
     */
    public String getGene() { 
        return this.sequence;
    }

    /* ****************************************************************
     * Metodo: modifyGene
     * Funcao: Altera uma base nitrogenada especifica
     * Parametros: posicao a ser alterada e o valor
     * Retorno: null
     */
    public void modifyGene(int position, String replacement) { 
        sequence = sequence.substring(0, position) + replacement + sequence.substring(position + 1);
    }
}