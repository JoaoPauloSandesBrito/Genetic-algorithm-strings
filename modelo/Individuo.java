/* ***************************************************************
* Autor............: JOAO PAULO SANDES BRITO
* Matricula........: 202110811
* Inicio...........: DESCONHECIDO
* Ultima alteracao.: 18/02/2023
* Nome.............: INDIVIDUO
* Funcao...........: CRIA UM MODELO DE INDIVIDUO
*************************************************************** */

package modelo;

public interface Individuo {
    /* ****************************************************************
     * Metodo: fitness
     * Funcao: Calcular nota individuo
     * Parametros: null
     * Retorno: double
     */
    public double fitness();

    /* ****************************************************************
     * Metodo: gerate_gamete
     * Funcao: Criar gameta a partir de par de cromossomos de individuo
     * Parametros: null
     * Retorno: DNA do gameta
     */
    public DNA gerate_gamete();

    /* ****************************************************************
     * Metodo: getA , getB
     * Funcao: Acessar cromossomo especifico de individuo
     * Parametros: null
     * Retorno: DNA
     */
    public DNA getA();
    public DNA getB();   
    
    /* ****************************************************************
     * Metodo: getRG
     * Funcao: Acessar numero de identificacao de individuo
     * Parametros: null
     * Retorno: int
     */
    public int getRG(); 
}
