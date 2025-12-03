
CREATE TABLE Autor (
    id SERIAL PRIMARY KEY,
    NomeAutor VARCHAR(255) NOT NULL,
    Nacionalidade VARCHAR(100),
    DataNascimento DATE,
    DataFalecimento DATE,
    BiografiaResumida TEXT
);


CREATE TABLE Doador (
    id SERIAL PRIMARY KEY,
    Nome VARCHAR(255) NOT NULL,
    Contato VARCHAR(255),
    DataCadastro DATE DEFAULT CURRENT_DATE
);


CREATE  TABLE Usuario (
    id SERIAL PRIMARY KEY,
    Nome VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    Senha VARCHAR(255) NOT NULL,
    Tipo VARCHAR(50) -- 'Administrador', 'Pesquisador', 'Visitante'
);


CREATE  TABLE Livro (
    id SERIAL PRIMARY KEY,
    ISBN BIGINT, -- Usei BIGINT pois ISBN tem 13 digitos e estoura o INT comum
    Titulo VARCHAR(255) NOT NULL,
    Subtitulo VARCHAR(255),
    Edicao VARCHAR(50),
    LocalPublicacao VARCHAR(255),
    Editora VARCHAR(255),
    AnoPublicacao INT,
    NumPaginas INT,
    Chamada VARCHAR(100),
    AutorID INT NOT NULL,
    DoadorID INT,
    FOREIGN KEY (AutorID) REFERENCES Autor(id),
    FOREIGN KEY (DoadorID) REFERENCES Doador(id)
);


CREATE  TABLE Acervo_historico (
    id SERIAL PRIMARY KEY,
    TipoItem VARCHAR(100),
    Descricao VARCHAR(255),
    AnoItem INT,
    CaminhoItem VARCHAR(255),
    DoadorID INT,
    FOREIGN KEY (DoadorID) REFERENCES Doador(id)
);


CREATE  TABLE Emprestimo (
    id SERIAL PRIMARY KEY,
    Dataretirada DATE NOT NULL DEFAULT CURRENT_DATE,
    Datadevolucao DATE,
    UsuarioID INT NOT NULL,
    LivroID INT NOT NULL,
    FOREIGN KEY (UsuarioID) REFERENCES Usuario(id),
    FOREIGN KEY (LivroID) REFERENCES Livro(id)
);




INSERT INTO Autor (NomeAutor, Nacionalidade, DataNascimento, DataFalecimento, BiografiaResumida) VALUES
('Carolina Maria de Jesus', 'Brasileira', '1914-03-14', '1977-02-13', 'Escritora famosa por Quarto de Despejo.'),
('Machado de Assis', 'Brasileira', '1839-06-21', '1908-09-29', 'Fundador da Academia Brasileira de Letras.'),
('Conceição Evaristo', 'Brasileira', '1946-11-29', NULL, 'Criadora do conceito de escrevivência.'),
('Djamila Ribeiro', 'Brasileira', '1980-08-01', NULL, 'Filósofa e escritora contemporânea.'),
('Abdias do Nascimento', 'Brasileira', '1914-03-14', '2011-05-23', 'Ativista, escritor e político.'),
('Chimamanda Ngozi Adichie', 'Nigeriana', '1977-09-15', NULL, 'Escritora feminista nigeriana.');


INSERT INTO Doador (Nome, Contato, DataCadastro) VALUES
('Associação Comunitária Treze de Maio', 'contato@treze.org', '2023-01-10'),
('Maria da Silva', 'maria.silva@email.com', '2023-02-15'),
('João Batista', 'joao.batista@email.com', '2023-03-20'),
('Biblioteca Pública de Santa Maria', 'biblioteca@santamaria.rs.gov.br', '2023-05-05'),
('Família Oliveira', 'fam.oliveira@email.com', '2023-06-12');


INSERT INTO Usuario (Nome, Email, Senha, Tipo) VALUES
('Admin Museu', 'admin@museu13maio.com', 'senha123', 'Administrador'),
('Estudante UFN', 'aluno@ufn.edu.br', 'ufn2025', 'Pesquisador'),
('Visitante Geral', 'visitante@gmail.com', 'guest', 'Visitante');


INSERT INTO Livro (ISBN, Titulo, Subtitulo, Edicao, LocalPublicacao, Editora, AnoPublicacao, NumPaginas, Chamada, AutorID, DoadorID) VALUES
(9788530000, 'Quarto de Despejo', 'Diário de uma favelada', '10ª', 'São Paulo', 'Ática', 1960, 200, 'LIT-BRA-01', 1, 2),
(9788530001, 'Dom Casmurro', NULL, '1ª', 'Rio de Janeiro', 'Garnier', 1899, 256, 'CLS-BRA-02', 2, 4),
(9788530002, 'Olhos D’água', NULL, '1ª', 'Rio de Janeiro', 'Pallas', 2014, 116, 'CNT-BRA-03', 3, 1),
(9788530003, 'Pequeno Manual Antirracista', NULL, '1ª', 'São Paulo', 'Companhia das Letras', 2019, 136, 'SOC-BRA-04', 4, 2),
(9788530004, 'O Genocídio do Negro Brasileiro', 'Processo de um Racismo Mascarado', '3ª', 'Rio de Janeiro', 'Paz e Terra', 1978, 180, 'HIS-BRA-05', 5, 1),
(9788530005, 'Hibisco Roxo', NULL, '2ª', 'São Paulo', 'Companhia das Letras', 2011, 320, 'LIT-INT-06', 6, 3),
(9788530006, 'Memórias Póstumas de Brás Cubas', NULL, 'Edição Especial', 'Rio de Janeiro', 'Nova Fronteira', 2015, 300, 'CLS-BRA-07', 2, 4),
(9788530007, 'Ponciá Vicêncio', NULL, '1ª', 'Belo Horizonte', 'Mazza Edições', 2003, 140, 'LIT-BRA-08', 3, 5),
(9788530008, 'Quem tem medo do feminismo negro?', NULL, '1ª', 'São Paulo', 'Companhia das Letras', 2018, 120, 'SOC-BRA-09', 4, 2),
(9788530009, 'Casa de Alvenaria', 'Diário de uma ex-favelada', '1ª', 'São Paulo', 'Francisco Alves', 1961, 210, 'LIT-BRA-10', 1, 5);


INSERT INTO Acervo_historico (TipoItem, Descricao, AnoItem, CaminhoItem, DoadorID) VALUES
('Fotografia', 'Foto da fundação do clube social negro', 1950, '/img/foto_fundacao_1950.jpg', 1),
('Ata', 'Ata de reunião da diretoria de 1960', 1960, '/docs/ata_1960.pdf', 1),
('Objeto', 'Tambor cerimonial antigo', 1940, '/img/tambor_01.jpg', 5),
('Carta', 'Carta de alforria digitalizada', 1880, '/docs/carta_alforria_1880.pdf', 4),
('Jornal', 'Recorte do Jornal A Razão sobre evento', 1975, '/docs/jornal_arazao_1975.pdf', 2),
('Vestimenta', 'Traje típico de festividade religiosa', 1980, '/img/traje_festividade.jpg', 3),
('Relato Oral', 'Gravação de entrevista com Dona Maria', 2005, '/audio/relato_dona_maria.mp3', 2),
('Fotografia', 'Desfile de carnaval de rua em Santa Maria', 1985, '/img/carnaval_sm_1985.jpg', 4),
('Documento', 'Certidão de nascimento antiga', 1930, '/docs/certidao_1930.pdf', 5),
('Troféu', 'Troféu do campeonato de futebol do clube', 1990, '/img/trofeu_1990.jpg', 1);


INSERT INTO Emprestimo (Dataretirada, Datadevolucao, UsuarioID, LivroID) VALUES
('2023-11-01', '2023-11-15', 2, 1), 
('2023-11-05', '2023-11-20', 2, 4), 
('2023-11-10', NULL, 3, 2),         
('2023-11-12', NULL, 2, 5);     


select * from Usuario;