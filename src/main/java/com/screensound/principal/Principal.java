package com.screensound.principal;

import com.screensound.model.Artista;
import com.screensound.model.Musica;
import com.screensound.model.TipoArtista;
import com.screensound.repository.ArtistaRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final ArtistaRepository repository;
    Scanner sc = new Scanner(System.in);

    public Principal(ArtistaRepository repository) {
        this.repository = repository;
    }

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
                                        
                    Selecione a opção: 
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
                    System.out.println("Opção inválida!0");
            }
        }
    }

    private void cadastrarArtistas() {
        var cadastrarNovo = "S";

        while (cadastrarNovo.equalsIgnoreCase("s")) {
            System.out.print("Informe o nome do artista: ");
            var nome = sc.nextLine();
            System.out.print("Informe o tipo do artista (solo, dupla ou banda): ");
            var tipo = sc.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nome, tipoArtista);
            repository.save(artista);
            System.out.print("Cadastrar novo artista? (S/N) ");
            cadastrarNovo = sc.nextLine();
        }
    }

    private void cadastrarMusicas() {
        System.out.print("Cadastrar música de que artista? ");
        var nome = sc.nextLine();
        Optional<Artista> artista = repository.findByNomeContainingIgnoreCase(nome);
        if (artista.isPresent()) {
            System.out.print("Informe o título da música: ");
            var nomeMusica = sc.nextLine();
            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            repository.save(artista.get());
        } else {
            System.out.println("Artista não encontrado!");
        }

    }

    private void listarMusicas() {
        List<Artista> artistas = repository.findAll();
        artistas.forEach(a -> a.getMusicas().forEach(System.out::println));
    }

    private void dadosArtista() {
        System.out.print("Qual artista deseja pesquisar? ");
        var nomeArtista = sc.nextLine();

        String apiKey = "4558e93f1d4ebe23ba93200501be657b";
        String url = "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist="
                + nomeArtista.replace(" ", "%20")
                + "&api_key=" + apiKey + "&format-json";

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String jsonResponse = response.toString();
            int bioStart = jsonResponse.indexOf("<summary>")+9;
            int bioEnd = jsonResponse.indexOf("</summary>");
            if (bioStart >= 9 && bioEnd > bioStart) {
                String biografia = jsonResponse.substring(bioStart, bioEnd)
                        .replace("\\n", "\n")
                        .replace("\\", "");
                System.out.println("Descrição do Artista:\n" + limparDadosDoArtista (biografia));
            } else {
                System.out.println("Não foi possível encontrar informações sobre o artista.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar informações do artista: " + e.getMessage());
        }
    }

    private void buscarMusicaPorArtista() {
        System.out.print("Buscar músicas de que artista? ");
        var nome = sc.nextLine();
        List<Musica> musicas = repository.buscarMusicasPorArtista(nome);
        musicas.forEach(System.out::println);
    }

    public String limparDadosDoArtista (String dadosArtista) {
        var teste = dadosArtista.replaceAll("&quot", "")
                .replaceAll("&apos;", "")
                .replaceAll(";", "");
        return teste.substring(0,teste.indexOf("&lt"));
    }
}
