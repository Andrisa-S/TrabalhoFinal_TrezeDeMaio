-- 1. Livros publicados após 2010 relacionados à História Afro-Brasileira
SELECT
    L.id,
    L.Titulo,
    L.Subtitulo,
    L.AnoPublicacao,
    A.NomeAutor,
    L.Editora,
    L.Chamada
FROM Livro L
INNER JOIN Autor A ON L.AutorID = A.id
WHERE L.AnoPublicacao > 2010
    AND (L.Titulo LIKE '%negro%'
        OR L.Titulo LIKE '%afro%'
        OR L.Titulo LIKE '%racismo%'
        OR L.Titulo LIKE '%genocídio%'
        OR L.Subtitulo LIKE '%negro%'
        OR L.Subtitulo LIKE '%afro%'
        OR L.Subtitulo LIKE '%racismo%')
ORDER BY L.AnoPublicacao DESC;


-- 2. Documentos do acervo histórico doados por um doador específico
SELECT
    AH.id,
    AH.TipoItem,
    AH.Descricao,
    AH.AnoItem,
    AH.CaminhoItem,
    D.Nome AS NomeDoador,
    D.Contato
FROM Acervo_historico AH
INNER JOIN Doador D ON AH.DoadorID = D.id
WHERE D.Nome LIKE '%Associação Comunitária Treze de Maio%'
ORDER BY AH.AnoItem DESC;


-- 3. Autor com mais livros cadastrados na biblioteca
SELECT
    A.id,
    A.NomeAutor,
    A.Nacionalidade,
    COUNT(L.id) AS TotalLivros,
    GROUP_CONCAT(L.Titulo ORDER BY L.Titulo SEPARATOR ', ') AS Titulos
FROM Autor A
INNER JOIN Livro L ON A.id = L.AutorID
GROUP BY A.id, A.NomeAutor, A.Nacionalidade
ORDER BY COUNT(L.id) DESC
LIMIT 1;


-- 4. Itens do acervo histórico sem digitalização (sem anexo/caminho)
SELECT
    AH.id,
    AH.TipoItem,
    AH.Descricao,
    AH.AnoItem,
    D.Nome AS NomeDoador
FROM Acervo_historico AH
LEFT JOIN Doador D ON AH.DoadorID = D.id
WHERE AH.CaminhoItem IS NULL
    OR AH.CaminhoItem = ''
ORDER BY AH.AnoItem;


-- 5. Livros mais emprestados (ranking de popularidade)
SELECT
    L.id,
    L.Titulo,
    A.NomeAutor,
    L.AnoPublicacao,
    COUNT(E.id) AS TotalEmprestimos,
    SUM(CASE WHEN E.Datadevolucao IS NULL THEN 1 ELSE 0 END) AS EmprestimosAtivos
FROM Livro L
INNER JOIN Autor A ON L.AutorID = A.id
LEFT JOIN Emprestimo E ON L.id = E.LivroID
GROUP BY L.id, L.Titulo, A.NomeAutor, L.AnoPublicacao
HAVING COUNT(E.id) > 0
ORDER BY COUNT(E.id) DESC;


-- 6. Usuários com empréstimos em atraso (mais de 15 dias sem devolução)
SELECT
    U.id,
    U.Nome AS NomeUsuario,
    U.Email,
    U.Tipo,
    L.Titulo AS LivroEmprestado,
    E.Dataretirada,
    DATEDIFF(CURRENT_DATE(), E.Dataretirada) AS DiasEmprestado
FROM Usuario U
INNER JOIN Emprestimo E ON U.id = E.UsuarioID
INNER JOIN Livro L ON E.LivroID = L.id
WHERE E.Datadevolucao IS NULL
    AND DATEDIFF(CURRENT_DATE(), E.Dataretirada) > 15
ORDER BY DiasEmprestado DESC;


-- 7. Doadores mais ativos (com mais doações entre livros e acervo histórico)
SELECT
    D.id,
    D.Nome,
    D.Contato,
    COUNT(DISTINCT L.id) AS LivrosDoados,
    COUNT(DISTINCT AH.id) AS ItensAcervoDoados,
    COUNT(DISTINCT L.id) + COUNT(DISTINCT AH.id) AS TotalDoacoes
FROM Doador D
LEFT JOIN Livro L ON D.id = L.DoadorID
LEFT JOIN Acervo_historico AH ON D.id = AH.DoadorID
GROUP BY D.id, D.Nome, D.Contato
HAVING COUNT(DISTINCT L.id) + COUNT(DISTINCT AH.id) > 0
ORDER BY TotalDoacoes DESC;


-- 8. Livros de autores brasileiros por década de publicação
SELECT
  
    (L.AnoPublicacao DIV 10) * 10 AS Decada, 
    COUNT(L.id) AS TotalLivros,
  
    GROUP_CONCAT(DISTINCT A.NomeAutor ORDER BY A.NomeAutor SEPARATOR ', ') AS Autores
FROM Livro L
INNER JOIN Autor A ON L.AutorID = A.id
WHERE A.Nacionalidade = 'Brasileira'
    AND L.AnoPublicacao IS NOT NULL
GROUP BY Decada
ORDER BY Decada DESC;


-- 9. Tipos de itens mais comuns no acervo histórico por período
SELECT
    CASE
        WHEN AH.AnoItem < 1900 THEN 'Século XIX'
        WHEN AH.AnoItem BETWEEN 1900 AND 1949 THEN 'Primeira Metade Século XX'
        WHEN AH.AnoItem BETWEEN 1950 AND 1999 THEN 'Segunda Metade Século XX'
        ELSE 'Século XXI'
    END AS Periodo,
    AH.TipoItem,
    COUNT(*) AS Quantidade
FROM Acervo_historico AH
GROUP BY
    Periodo, 
    AH.TipoItem
ORDER BY Periodo, Quantidade DESC;


-- 10. Relatório: Livros nunca emprestados
SELECT
    L.id,
    L.Titulo,
    A.NomeAutor,
    L.AnoPublicacao,
    L.Editora,
    D.Nome AS Doador,
   
    'Nunca foi emprestado' AS StatusEmprestimo, 
    'Disponível' AS Disponibilidade 
FROM Livro L
INNER JOIN Autor A ON L.AutorID = A.id
LEFT JOIN Doador D ON L.DoadorID = D.id
WHERE NOT EXISTS (SELECT 1 FROM Emprestimo E WHERE E.LivroID = L.id)
ORDER BY L.AnoPublicacao DESC;
