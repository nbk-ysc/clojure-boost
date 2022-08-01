Sobre o Desafio

Criar um código em Clojure que simula o cartão de crédito de um cliente Nubank.

O que esse projeto faz:
Trabalha com estruturas de dados para representar e manipular dados das estruturas Cartão e Compra;
Realiza listagem de compras com critérios diferentes;
Agrega dados para relatório de gastos por categoria, e por mês;
Trabalha com biblioteca de datas nativa no Java;
Lê e processa arquivos CSV.
Massa de dados:
O arquivo Massa de dados contém dados para utilizar no desenvolvimento e avaliar os resultados esperados.

Bom projeto!


------------------------------------------------------------------------------------------------------------------------

Material para estudos

Para realizar o desafio dessa semana você precisa ter conhecimentos da linguagem de programação Clojure e do paradigma de programação funcional.

Preparamos um plano de estudos para você estudar e se preparar para o desafio:

Clojure: Introdução à programação funcional
Clojure: Coleções no dia a dia
Bons estudos!


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Setup Inicial

Acesse o projeto https://github.com/nbk-ysc/clojure-boost e crie um fork (uma "cópia" do projeto para o seu usuário do GitHub). Caso não tenha conta no GitHub, crie uma (as entregas de código serão por lá 😊).

Após o fork, faça o clone do projeto em sua máquina local.


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Compras Realizadas

Tarefa
Criar a função nova-compra, que retorne uma estrutura de dados que represente uma compra realizada em um determinado cartão.

Parâmetros:
data (String: yyyy-mm-dd)
valor (BigDecimal)
estabelecimento (String)
categoria (String)
cartao (Long)
Retorno:
Map com a seguinte estrutura:
{:data ...
  :valor ...
  :estabelecimento ...
  :categoria ...
  :cartao ...}


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Listar Compras

Tarefa
Crie as funções lista-compras que retorna uma coleção com todas as compras realizadas.

Parâmetros
A função não recebe parâmetros.

Retorno
Deve retornar um vetor de maps de compras.

Critérios de aceitação
O vetor deve ter os 19 maps de compras, com os dados da planilha Massa de dados.


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Calcular o total gasto em compras de um cartao

Tarefa
Criar a função total-gasto, que recebe um vetor de compras e retorna a soma dos valores gastos.

Parametros
compras (vetor com maps de compra)
Retorno
BigDecimal com a soma do valor das compras
Exemplo:
[{:valor: 100.00 ; demais chaves do mapa
  {:valor: 250.00 ; demais chaves do mapa
  {:valor: 400.00 ; demais chaves do mapa}]

TOTAL: R$ 750,00


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Buscar compras por mes

Tarefa
Criar uma função que, dado um mês e uma lista de compras, retorne um vetor das compras feitas somente naquele mês.

Parâmetros
mes (inteiro)
lista de compras (vetor ou list com maps de compras)
Retorno
vetor com maps de compra


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Calcular o total da fatura de um mes

Tarefa
Criar a função total-gasto-no-mes, que calcule a soma dos valores gastos num determinado cartão em um mês.

Para facilitar, considere que todas as compras sejam de um mesmo cartão.


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Agrupar gastos por categoria

Tarefa
Criar uma função que retorne os total gasto agrupados por categoria.

Parâmetros
compras (vetor com maps de compras)
Retorno
Map* com as categorias associadas ao valor gasto
Exemplo
[{:categoria  "Educação" :valor 700.00 ; demais chaves do mapa}
 {:categoria  "Saúde" :valor 1500.00 ; demais chaves do mapa}
 {:categoria  "Educação" :valor 50.00 ; demais chaves do mapa}
 {:categoria  "Alimentação" :valor 100.00 ; demais chaves do mapa}
 {:categoria  "Alimentação" :valor 89.90 ; demais chaves do mapa}]

Saída
{"Educação" 750.00
 "Saúde" 1500.00
 "Alimentação" 189.90}

OBSERVAÇÃO
A saída não precisa ser ordenada


------------------------------------------------------------------------------------------------------------------------
*Opcional*

Filtrar compras num intervalo de valores

Tarefa
Criar uma função que retorne as compras que estão dentro de um intervalo de valor máximo e valor mínimo.


------------------------------------------------------------------------------------------------------------------------
*Opcional* *Desafio*

Usar API Java Time para datas

Tarefa
Utilizar a API de datas do Java (Java Time) para representar as datas das compras, e da validade do cartão.

Adaptar também a função que filtra compras no mês.


------------------------------------------------------------------------------------------------------------------------
*Opcional* *Desafio*

Carregar dados de arquivo CSV

Adapte a função lista-compras para carregar os dados do arquivo compras.csv anexo nesta tarefa.
(esta na pasta utils do projeto)




