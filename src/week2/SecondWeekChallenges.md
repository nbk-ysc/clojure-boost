Sobre o Desafio

Criar um código em Clojure que simula o cartão de crédito de um cliente Nubank.

O que esse projeto faz:
Trabalha com records para representar e manipular dados das estruturas Cartão e Compra;
Usa polimorfismo com uso de multi methods;
Manipula átomos para processamento concorrente.
Bom projeto!


------------------------------------------------------------------------------------------------------------------------

Material para estudos

Para realizar o desafio dessa semana você precisa ter conhecimentos da linguagem de programação Clojure e do paradigma de programação funcional.

Preparamos um plano de estudos para você estudar e se preparar para o desafio:

Clojure: Mutabilidade com átomos e refs
Clojure: Record, protocol e multi method
Bons estudos!


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Definir átomo como banco de dados em memória

Tarefa
Definir um átomo no símbolo repositorio-de-compras onde serão salvos os maps de compras.
O átomo deve ser inicializado com um vetor vazio [].


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Criar Record para Compra

Tarefa
Crie um record para representar uma Compra realizada em um determinado Cartão.

Atributos do record devem ser:
ID (Long ou nil)
Data (String: yyyy-mm-dd ou LocalDate)
Valor (BigDecimal)
Estabelecimento (String)
Categoria (String)
Cartão (Long)


A função nova-compra ainda faz sentido?


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Inserir Compra

Tarefa
Criar a função insere-compra. Ela vai atribuir um id a uma compra e armazená-la num vetor.

Parâmetros da função:
um record de uma compra sem id;
um vetor com as compras já cadastradas..

Retorno da função:
um vetor com a nova compra inserida nele.

Critérios de aceitação:
O ID da nova compra deve ser o valor máximo de ID da lista de compras mais 1;
Se a lista de compras estiver vazia, o ID deve ser o valor 1.


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Inserir compra no átomo

Tarefa
Criar a função insere-compra! para incluir uma nova compra no átomo de compras usando swap!.

Parâmetros da função:
um record de uma compra;
átomo repositorio-de-compras criado anteriormente .

Retorno da função:
"sem retorno"

Critérios de aceitação:
A função deve substituir o valor interno do átomo por meio de um swap!;
O valor do átomo deve ser atualizado com a função insere-compracriada anteriormente.


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Listar compras do átomo


Tarefa
Crie a função lista-compras!, que lista as compras de um átomo.

Parâmetros da função:
átomo repositorio-de-compras criado anteriormente.

Retorno da função:
"sem retorno".

Critérios de aceitação:
A função deve executar um println no deref em cima do átomo;


------------------------------------------------------------------------------------------------------------------------
*Opcional*

Excluir Compra

Tarefa
Crie a função exclui-compra, que exclui uma compra de determinado id de um vetor.

Parâmetros da função:
id da compra a ser excluída;
vetor de compras.
Retorno da função:
Novo vetor sem a compra excluída.
Critérios de aceitação:
Caso a compra não exista, retornar o vetor original recebido por parâmetro.


------------------------------------------------------------------------------------------------------------------------
*Opcional* 

Excluir compra do átomo

Tarefa
Criar a função exclui-compra! para uma compra de um átomo por meio de swap!.

Parâmetros da função:
id da compra a ser excluída;
átomo repositorio-de-compras criado anteriormente .

Retorno da função:
"sem retorno"

Critérios de aceitação:
A função deve substituir o valor interno do átomo por meio de um swap!;
O valor do átomo deve ser atualizado com a função exclui-compracriada anteriormente.


------------------------------------------------------------------------------------------------------------------------
*Opcional* *Desafio*

Validar cadastro de compra

Tarefa
Criar a função valida-compra para validar uma compra. Depois, altere a função insere-compra! definida anteriormente para validar a compra antes de salvá-la no átomo.

Parâmetros da função:
um record da nova compra.

Regras de aceitação:

Data da compra:
Se você estiver usando a data como String, então ela deve ter o formato yyyy-mm-dd;
Se você estiver usando API do Java Time, então ela deve ser uma data menor ou igual à data atual.

Valor:
deve ser um BigDecimal positivo.

Estabelecimento:
Deve ter pelo menos 2 caracteres.

Categoria:
Deve ser uma das opções: Alimentação, Automóvel, Casa, Educação, Lazer ou Saúde.




