# 🎵 Screen Sound - Sistema de Gerenciamento Musical  

Um programa desenvolvido em **Java**, utilizando **Spring Boot**, **JPA**, **PostgreSQL** e integração com as APIs **Google Cloud Translation** e **Last.fm**, para gerenciar artistas e músicas, além de buscar e traduzir informações detalhadas sobre artistas.

---

## 📋 **Funcionalidades**  

1. **Cadastrar Artista:**  
   Adicione novos artistas ao banco de dados.

2. **Cadastrar Músicas:**  
   Insira músicas associadas a artistas já cadastrados.

3. **Listar Músicas Cadastradas:**  
   Visualize todas as músicas armazenadas no banco de dados.

4. **Buscar Músicas por Artista:**  
   Filtre músicas com base no nome do artista.

5. **Pesquisar Dados sobre um Artista:**  
   Consulte informações detalhadas sobre artistas utilizando a **Last.fm API**. As biografias recuperadas são traduzidas automaticamente para o português com a **Google Cloud Translation API**.

---

## 🛠 **Tecnologias Utilizadas**  

- **Java 17**: Linguagem principal.  
- **Spring Boot**: Framework para criação da aplicação.  
- **JPA**: Para mapeamento e persistência de dados.  
- **PostgreSQL**: Banco de dados relacional.  
- **Last.fm API**: Para consulta de informações sobre artistas.  
- **Google Cloud Translation API**: Para tradução automática de biografias.  
- **Maven**: Gerenciador de dependências e build.  

---

## ⚙️ **Configuração do Projeto**  

### Pré-requisitos:  
- **Java 17+**  
- **Maven**  
- **PostgreSQL**  
- **Credenciais para as APIs Last.fm e Google Cloud Translation**  

### Passos:  

1. **Clone o repositório:**  
   ```bash
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
   cd nome-do-repositorio


 2. **Configure o banco de dados:**

  • Crie um banco de dados PostgreSQL.
  • Atualize as configurações de acesso no arquivo application.properties:

    spring.datasource.url=jdbc:postgresql://localhost:5432/nome-do-banco
    spring.datasource.username=seu-usuario
    spring.datasource.password=sua-senha

3. **Configure as credenciais das APIs:**

  • Insira suas chaves de API nos locais apropriados.

    google.api.key=CHAVE_GOOGLE_CLOUD_TRANSLATION
    lastfm.api.key=CHAVE_LASTFM

4. **Execute o projeto:**
   
    mvn spring-boot:run
    Acesse a aplicação em: http://localhost:8080.


📄 **APIs Utilizadas**

  Last.fm API:
  Usada para buscar informações sobre artistas.

  Google Cloud Translation API:
  Utilizada para traduzir as biografias dos artistas para o português.



Todos os direitos reservados Nicolas Alves ©
