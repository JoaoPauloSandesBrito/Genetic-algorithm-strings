/* ***************************************************************
* Autor............: JOAO PAULO SANDES BRITO
* Matricula........: 202110811
* Inicio...........: DESCONHECIDO
* Ultima alteracao.: 18/01/2024
* Nome.............: PRINCIPAL
* Funcao...........: CODIGO DE OTIMIZACAO
*************************************************************** */

import controle.Populacao;
import modelo.IndividuoPi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;

public class Principal extends Application {
    String textoPrincipal = "";

    File arquivo = new File("testPopulation.txt"); // creates the file on disk with the name testPop.txt
    int pop_qtd = 10;
    int genes_qtd = 20;
    int prob_mutacao = 1;

    int geracao = 0;

    @Override
    public void start(Stage primaryStage) {

        // Cria botoes do topo da pagina
        Button btnInicio = new Button("Inicio");
        Button btnCarregar = new Button("Carregar");
        Button btnSalvar = new Button("Salvar");
        HBox botoes = new HBox();
        botoes.getChildren().addAll(btnInicio, btnCarregar, btnSalvar);
        botoes.setPadding(new Insets(20, 10, 0, 10));
        botoes.setSpacing(10);
        HBox.setHgrow(btnInicio, Priority.ALWAYS);
        HBox.setHgrow(btnCarregar, Priority.ALWAYS);
        HBox.setHgrow(btnSalvar, Priority.ALWAYS);

        // Cria caixa de texto para alterar a quantidade de individuos
        Label lblParametroIndividuos = new Label("INDIVIDUOS : ");
        TextField txtParametroIndividuos = new TextField(String.valueOf(pop_qtd));
        HBox caixaParametroIndividuos = new HBox();
        caixaParametroIndividuos.setSpacing(10);
        caixaParametroIndividuos.setPadding(new Insets(20, 10, 0, 10));
        HBox.setHgrow(txtParametroIndividuos, Priority.ALWAYS);
        caixaParametroIndividuos.getChildren().addAll(lblParametroIndividuos, txtParametroIndividuos);
        txtParametroIndividuos.setOnKeyReleased(e -> {
            String texto = txtParametroIndividuos.getText();
            System.out.println("INDIVIDUOS digitado: " + texto);
            try {
                pop_qtd = Integer.parseInt(texto);
            } catch (Exception erro) {
                txtParametroIndividuos.setText(String.valueOf(pop_qtd));
            }

        });
        caixaParametroIndividuos.setAlignment(Pos.CENTER);

        // Cria caixa de texto para alterar a quantidade de genes de cada individuo
        Label lblParametroGenes = new Label("GENES : ");
        TextField txtParametroGenes = new TextField(String.valueOf(genes_qtd));
        HBox caixaParametroGenes = new HBox();
        caixaParametroGenes.setSpacing(10);
        caixaParametroGenes.setPadding(new Insets(20, 10, 0, 10));
        HBox.setHgrow(txtParametroGenes, Priority.ALWAYS);
        caixaParametroGenes.getChildren().addAll(lblParametroGenes, txtParametroGenes);
        txtParametroGenes.setOnKeyReleased(e -> {
            String texto = txtParametroGenes.getText();
            System.out.println("GENES digitado: " + texto);
            try {
                genes_qtd = Integer.parseInt(texto);
            } catch (Exception erro) {
                txtParametroGenes.setText(String.valueOf(genes_qtd));
            }

        });
        caixaParametroGenes.setAlignment(Pos.CENTER);

                // Cria caixa de texto para alterar a probabilidade de mutacao dos individuos
        Label lblParametroMutacao = new Label("PROBABILIDADE MUTACAO (0-100) : ");
        TextField txtParametroMutacao = new TextField(String.valueOf(prob_mutacao));
        HBox caixaParametroMutacao = new HBox();
        caixaParametroMutacao.setSpacing(10);
        caixaParametroMutacao.setPadding(new Insets(20, 10, 0, 10));
        HBox.setHgrow(txtParametroMutacao, Priority.ALWAYS);
        caixaParametroMutacao.getChildren().addAll(lblParametroMutacao, txtParametroMutacao);

        txtParametroMutacao.setOnKeyReleased(e -> {
            String texto = txtParametroMutacao.getText();
            System.out.println("MUTACAO digitada: " + texto);
            try {
                if ((Integer.parseInt(texto) > 100) || (Integer.parseInt(texto) < 0))
                    txtParametroMutacao.setText(String.valueOf(prob_mutacao));
                else
                    prob_mutacao = Integer.parseInt(texto);

            } catch (Exception erro) {
                txtParametroMutacao.setText(String.valueOf(prob_mutacao));
            }

        });
        caixaParametroMutacao.setAlignment(Pos.CENTER);

        //Cria caixa de texto ara mostrar os resultados
        HBox caixaResultados = new HBox();
        TextArea resultados = new TextArea(textoPrincipal);

        HBox.setHgrow(resultados, Priority.ALWAYS);
        resultados.setPrefRowCount(7);
        resultados.setWrapText(true);
        caixaResultados.getChildren().add(resultados);

        caixaResultados.setMargin(resultados, new Insets(20, 10, 0, 10));

        btnInicio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Populacao Brazil = new Populacao(pop_qtd, genes_qtd, prob_mutacao);
                Brazil.arrange();

                System.out.println("Geracao:" + geracao);
                System.out.println(Brazil.see_pop(0)); // Show initial population in order of fitness
                try {
                    FileWriter fw = new FileWriter(arquivo, true); // Open the file to write
                    fw.write("Generation:" + geracao + "\n");
                    textoPrincipal = textoPrincipal + "Generation:" + geracao + "\n";
                    textoPrincipal = textoPrincipal + Brazil.see_pop(0);
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
                        textoPrincipal = textoPrincipal + "Generation:" + geracao + "\n";
                        textoPrincipal = textoPrincipal + Brazil.see_pop(0);
                        fw.write("\nGeneration:" + geracao + "\n");
                        fw.write(Brazil.see_pop(0)); // add generation info to the file
                        fw.close(); // close the file
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    if (Brazil.maxFitness() >= 15)
                        break;
                }

                resultados.setText(textoPrincipal);
                System.out.println(textoPrincipal);

            }
        });

        VBox raiz = new VBox();
        raiz.getChildren().addAll(botoes, caixaParametroIndividuos, caixaParametroMutacao, caixaParametroGenes,
                caixaResultados);

        // CSS to center buttons
        botoes.setStyle("-fx-alignment: center;");

        Scene cena = new Scene(raiz, 600, 400);

        primaryStage.setTitle("Meu Programa");
        primaryStage.setScene(cena);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}