# 🏛️ Sistema de Gerenciamento de Acervos - Museu Treze de Maio

<div align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.1-green?style=for-the-badge&logo=spring" alt="Spring Boot 3.1">
  <img src="https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql" alt="MySQL 8.0">
  <img src="https://img.shields.io/badge/Thymeleaf-3.1-white?style=for-the-badge&logo=thymeleaf" alt="Thymeleaf">
  <img src="https://img.shields.io/badge/Maven-3.9-red?style=for-the-badge&logo=apachemaven" alt="Maven">
</div>

## 📋 Sobre o Projeto

Sistema web desenvolvido para o **Museu Treze de Maio** de Santa Maria, com o objetivo de digitalizar, catalogar e gerenciar o vasto patrimônio histórico e cultural afro-brasileiro da instituição.

O sistema permite o gerenciamento de dois acervos distintos:
- **📚 Acervo Histórico**: Jornais, atas, fotografias, cartas, relatos orais, objetos
- **📖 Biblioteca Comunitária**: Livros, revistas e jornais

## ✨ Funcionalidades

### 👥 Múltiplos Perfis de Usuário
- **Administradores do Museu**: Catalogação, edição e gerenciamento completo
- **Funcionários**: Operações básicas de cadastro e consulta
- **Público Geral**: Consultas básicas e descoberta do acervo

### 📊 Gerenciamento de Acervos
- ✅ **CRUD completo** para Acervo Histórico e Biblioteca
- ✅ **Busca avançada** com múltiplos filtros
- ✅ **Upload de arquivos** digitais (fotos, documentos)
- ✅ **Controle de digitalização** dos itens
- ✅ **Associação com doadores** e autores

### 🔐 Sistema de Segurança
- ✅ **Criação de contas** com diferentes níveis de acesso
- ✅ **Autenticação segura** com Spring Security
- ✅ **Autorização por perfil** (ADMIN, FUNCIONARIO, VISITANTE)
- ✅ **Sessões protegidas** e controle de acesso

### 📈 Relatórios e Consultas
- ✅ **Consultas complexas** SQL com JOIN, GROUP BY, subconsultas
- ✅ **Relatórios estatísticos** do acervo
- ✅ **Exportação de dados** básica
- ✅ **Dashboard administrativo**

## 🏗️ Arquitetura

### Tecnologias Utilizadas
- **Backend**: Java 17, Spring Boot 3.1, Spring Data JPA, Spring Security
- **Frontend**: Thymeleaf, Bootstrap 5, JavaScript
- **Banco de Dados**: MySQL 8.0 (compatível com SQL Server)
- **Build Tool**: Maven 3.9+
- **Controle de Versão**: Git/GitHub

### Estrutura do Projeto
```
src/main/java/org/example/trabalhofinal_trezedemaio/
├── controller/           # Controladores MVC
│   ├── LivroController.java
│   ├── AcervoHistoricoController.java
│   ├── UsuarioController.java
│   └── AutenticacaoController.java
├── model/               # Entidades JPA
│   ├── Livro.java
│   ├── AcervoHistorico.java
│   ├── Usuario.java
│   ├── Autor.java
│   └── Doador.java
├── repository/          # Interfaces de acesso a dados
│   ├── LivroRepository.java
│   ├── AcervoHistoricoRepository.java
│   ├── UsuarioRepository.java
│   └── ...
├── service/             # Lógica de negócio
│   ├── AcervoService.java
│   ├── AutenticacaoService.java
│   └── UsuarioService.java
├── security/            # Configurações de segurança
│   ├── SecurityConfig.java
│   ├── UserDetailsServiceImpl.java
│   └── WebMvcConfig.java
└── config/              # Configurações adicionais
    └── AppConfig.java

src/main/resources/
├── templates/           # Views Thymeleaf
│   ├── livro/
│   ├── acervo-historico/
│   ├── usuario/
│   └── fragments/
├── static/              # Assets estáticos
│   ├── css/
│   ├── js/
│   └── images/
└── application.properties

sql/                     # Scripts SQL
├── schema.sql          # Estrutura do banco
├── data.sql            # Dados iniciais
└── consultas.sql       # Consultas complexas

docs/                    # Documentação
├── diagramas/          # DER, casos de uso
├── requisitos/         # Documentação de requisitos
└── relatorios/         # Relatórios do projeto
```

## 🚀 Como Executar o Projeto

### Pré-requisitos
- Java 17 ou superior
- MySQL 8.0 ou superior
- Maven 3.9+
- Git

### Passo a Passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/Andrisa-S/TrabalhoFinal_TrezeDeMaio.git
   cd TrabalhoFinal_TrezeDeMaio
   ```

2. **Configure o banco de dados**
   ```sql
   CREATE DATABASE museu_trezedemaio;
   CREATE USER 'museu_user'@'localhost' IDENTIFIED BY 'senha123';
   GRANT ALL PRIVILEGES ON museu_trezedemaio.* TO 'museu_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

3. **Configure o arquivo application.properties**
   ```properties
   # src/main/resources/application.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/museu_trezedemaio
   spring.datasource.username=museu_user
   spring.datasource.password=senha123
   
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

4. **Compile e execute o projeto**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. **Acesse a aplicação**
   - URL: http://localhost:8080
   - Usuário admin: `admin@museu.org` / `senha123`
   - Usuário funcionário: `funcionario@museu.org` / `senha123`
   - Usuário visitante: `visitante@email.com` / `senha123`

## 📊 Scripts SQL

### Estrutura do Banco (DDL)
```sql
-- Tabela de usuários
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    nome VARCHAR(100),
    telefone VARCHAR(20),
    perfil ENUM('ADMINISTRADOR', 'FUNCIONARIO', 'VISITANTE') DEFAULT 'VISITANTE',
    data_cadastro DATE DEFAULT CURRENT_DATE,
    ativo BOOLEAN DEFAULT TRUE
);

-- Tabela de acervo histórico
CREATE TABLE acervo_historico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_item VARCHAR(50) NOT NULL,
    descricao TEXT NOT NULL,
    ano_item INT,
    caminho_item VARCHAR(500),
    doador_id BIGINT,
    FOREIGN KEY (doador_id) REFERENCES doador(id) ON DELETE SET NULL
);

-- Tabela de livros
CREATE TABLE livro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    isbn VARCHAR(20),
    editora VARCHAR(100),
    ano_publicacao INT,
    quantidade INT DEFAULT 1
);
```

### Dados Iniciais (DML)
```sql
-- Usuários de teste
INSERT INTO usuario (email, senha, nome, perfil) VALUES
('admin@museu.org', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBoSLZjQjY2v6a', 'Administrador Principal', 'ADMINISTRADOR'),
('funcionario@museu.org', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBoSLZjQjY2v6a', 'Funcionário do Acervo', 'FUNCIONARIO'),
('pesquisador@email.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBoSLZjQjY2v6a', 'Pesquisador Externo', 'VISITANTE');

-- Acervo histórico de exemplo
INSERT INTO acervo_historico (tipo_item, descricao, ano_item, doador_id) VALUES
('Fotografia', 'Fotografia da primeira sede do museu', 1985, 1),
('Carta', 'Carta manuscrita do fundador do museu', 1978, 2),
('Jornal', 'Edição especial sobre cultura afro-brasileira', 1992, 3);
```

### Consultas Complexas (Exemplos)
```sql
-- 1. Livros publicados após 2010 sobre História Afro-Brasileira
SELECT l.titulo, l.ano_publicacao, a.nome AS autor
FROM livro l
JOIN livro_autor la ON l.id = la.livro_id
JOIN autor a ON la.autor_id = a.id
JOIN livro_assunto las ON l.id = las.livro_id
JOIN assunto ass ON las.assunto_id = ass.id
WHERE l.ano_publicacao > 2010
  AND ass.nome LIKE '%História Afro-Brasileira%';

-- 2. Itens do acervo histórico não digitalizados
SELECT ah.tipo_item, ah.descricao, d.nome AS doador
FROM acervo_historico ah
LEFT JOIN doador d ON ah.doador_id = d.id
WHERE ah.caminho_item IS NULL OR ah.caminho_item = '';

-- 3. Top 5 autores com mais livros
SELECT a.nome, COUNT(la.livro_id) AS total_livros
FROM autor a
JOIN livro_autor la ON a.id = la.autor_id
GROUP BY a.id
ORDER BY total_livros DESC
LIMIT 5;
```

## 👥 Contribuição

### Equipe de Desenvolvimento
- **Integrante 1**: Modelagem de banco de dados e entidades JPA
- **Integrante 2**: Sistema de autenticação e controle de acesso
- **Integrante 3**: Desenvolvimento das views e interface
- **Integrante 4**: Consultas SQL e relatórios

### Padrões de Código
- Nomenclatura em português para domínio
- Comentários Javadoc em classes e métodos públicos
- Seguir convenções do Spring Boot
- Commits semânticos (feat:, fix:, docs:, etc.)

## 📝 Licença

Este projeto foi desenvolvido para fins acadêmicos como parte da disciplina de Implementação de Banco de Dados e Laboratório de Desenvolvimento de Software.

© 2024 Equipe Treze de Maio - Universidade Federal de Santa Maria

## 🔗 Links Úteis

- **Repositório GitHub**: https://github.com/Andrisa-S/TrabalhoFinal_TrezeDeMaio
- **Documentação Spring Boot**: https://spring.io/projects/spring-boot
- **Documentação Thymeleaf**: https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html
- **MySQL Documentation**: https://dev.mysql.com/doc/

## 🆘 Suporte

Para reportar bugs ou solicitar novas funcionalidades:
1. Verifique os [Issues](https://github.com/Andrisa-S/TrabalhoFinal_TrezeDeMaio/issues) existentes
2. Crie um novo issue com:
   - Descrição detalhada do problema
   - Passos para reproduzir
   - Screenshots (se aplicável)
   - Ambiente (SO, versão do Java, etc.)

---

<div align="center">
  <p><em>Desenvolvido com ❤️ para preservar a história e cultura afro-brasileira</em></p>
  <p>Museu Treze de Maio - Santa Maria/RS</p>
</div>

## 🎯 Próximas Melhorias

- [ ] API REST para integração com outros sistemas
- [ ] Sistema de backup automático do banco
- [ ] Relatórios estatísticos avançados
- [ ] Sistema de reserva online para livros
- [ ] Galeria virtual 3D para objetos do acervo
- [ ] Integração com redes sociais
- [ ] Versão mobile do sistema

## 📊 Métricas do Projeto

- ✅ **100%** Funcionalidades básicas implementadas
- ✅ **15+** Entidades do modelo de dados
- ✅ **20+** Consultas SQL complexas
- ✅ **10+** Telas do sistema
- ✅ **3** Níveis de acesso implementados
- ✅ **100+** Linhas de documentação

---

*Última atualização: Dezembro 2024*
