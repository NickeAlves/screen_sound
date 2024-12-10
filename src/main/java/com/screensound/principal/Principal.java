package com.screensound.principal;

import java.util.Scanner;

public class Principal {
    Scanner sc = new Scanner(System.in);

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 9) {
            var menu = """
                    *** Screen Sound ***
                    
                    1 - Cadastrar artistas
                    2 - Cadasrar músicas
                    3 - Listar músicas
                    4 - Buscar músicas por artistas
                    5 - Pesquisar dados sobre um artista
                    
                    9 - Sair
                    
                    """;
            System.out.print(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicaPorArtista();
                    break;
                case 5:
                    dadosArtista();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarArtistas() {
    }

    private void cadastrarMusicas() {
    }

    private void listarMusicas() {
    }

    private void buscarMusicaPorArtista() {
    }

    private void dadosArtista() {
    }
}
