package controle;

/* ***************************************************************
* Autor............: JOAO PAULO SANDES BRITO
* Matricula........: 202110811
* Inicio...........: DESCONHECIDO
* Ultima alteracao.: 18/02/2023
* Nome.............: POPULACAO
* Funcao...........: CONTROLE DOS INDIVIDUOS DA POPULACAO
*************************************************************** */

import java.util.ArrayList;
import java.util.Collections;

import modelo.Individuo;
import modelo.IndividuoPi;

public class Populacao {
    private ArrayList<Individuo> Individuos = new ArrayList<Individuo>();

    /*
     * ****************************************************************
     * Metodo: Populacao
     * Funcao: construtor (Iniciar populacao aleatoria)
     * Parametros: quantidade da populacao, tamanho dos genes dos individuos e probabilidade de mutacao
     * Retorno: null
     */
    public Populacao(int qtd, int tam_gene, int cross_prob) { 
        for (int i = 0; i < qtd; i++) {
            Individuo createIndividuo = new IndividuoPi(tam_gene, cross_prob);
            Individuos.add(createIndividuo);
        }
    }

    /*
     * ****************************************************************
     * Metodo: maxPop
     * Funcao: reduz o tamanho da populacao para valor pre definido
     * Parametros: quantidade maxima da populacao (int)
     * Retorno: null
     */
    public void maxPop(int qtd) {
        while (true) {
            if (Individuos.size() > qtd)
                Individuos.remove(qtd);
            else
                break;
        }
    }

        /*
     * ****************************************************************
     * Metodo: addIndividuo
     * Funcao: Adiciona individuo a poulacao
     * Parametros: Individuo a ser adicionado
     * Retorno: null
     */
    public void addIndividuo(Individuo ze) {
        Individuos.add(ze);
    }

     /*
     * ****************************************************************
     * Metodo: getIndividuo
     * Funcao: Busca um individuo da poulacao
     * Parametros: numero de localizacao no array
     * Retorno: Individuo
     */
    public Individuo getIndividuo(int num) { // Returns individual from population array
        return Individuos.get(num);
    }

    /*
     * ****************************************************************
     * Metodo: maxFitness
     * Funcao: Retornar nota do "melhor" individuo na pupulacao
     * Parametros: null
     * Retorno: double (nota)
     */
    public double maxFitness() { 
        return Individuos.get(0).fitness();
    }

     /*
     * ****************************************************************
     * Metodo: see_individuo
     * Funcao: Retornar informacao sobre individuo especifico
     * Parametros: posicao no array e o gene
     * Retorno: String (GENE)
     */
    public String see_individuo(int id, int gen) { 
        return "A: " + Individuos.get(id).getA().getGenesDNA() + "  B: " + Individuos.get(id).getB().getGenesDNA()
                + "  fitness: " + Individuos.get(id).fitness() + "  RG: " + Individuos.get(id).getRG();
    }

    /*
     * ****************************************************************
     * Metodo: see_pop
     * Funcao: Retornar informacao sobre todos os individuos da populacao
     * Parametros: gene
     * Retorno: String (genes da populacao)
     */
    public String see_pop(int gen) { // Returns information for all individuals in the population array
        String pop = new String("");

        for (int L = 0; L < Individuos.size(); L++) {
            pop = pop + "A: " + Individuos.get(L).getA().getGenesDNA() + "  B: "
                    + Individuos.get(L).getB().getGenesDNA() + "  fitness: " + Individuos.get(L).fitness() + "  RG: "
                    + Individuos.get(L).getRG() + "\n";
        }

        return pop;
    }

    /*
     * ****************************************************************
     * Metodo: size_pop
     * Funcao: Retornar tamanho da populacao
     * Parametros: null
     * Retorno: int (tamanho da populacao)
     */
    public int size_pop() {
        return Individuos.size();
    }

    /*
     * ****************************************************************
     * Metodo: arrange
     * Funcao: Organiza o array da populacao por nota dos individuos
     * Parametros: null
     * Retorno: null
     */
    public void arrange() { // Arranges the array in note order.
        while (true) {
            boolean ok = true;
            for (int l = 0; l < Individuos.size() - 1; l++) {
                if (Individuos.get(l).fitness() < Individuos.get(l + 1).fitness()) {
                    Collections.swap(Individuos, l, l + 1);
                    ok = false;
                }
            }
            if (ok)
                break;
        }
    }
}
