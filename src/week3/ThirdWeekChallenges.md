Sobre o Desafio

Criar um código em Clojure que simula o cartão de crédito de um cliente Nubank.

O que esse projeto faz:
Trabalha a validação de entradas e saídas de funções por meio de schemas;
Cria schemas de validações próprios;
Ensina a criar testes automatizados em Clojure.
Bom projeto!


------------------------------------------------------------------------------------------------------------------------

Material para estudos

Para realizar o desafio dessa semana você precisa ter conhecimentos da linguagem de programação Clojure e do paradigma de programação funcional.

Preparamos um plano de estudos para você estudar e se preparar para o desafio:

Clojure: Schemas
Clojure: explorando testes
Bons estudos!


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Definir Schema para Compra

Tarefa
Definir o símbolo CompraSchema que estabelece o schema do que é considerada uma compra válida.

Regras de aceitação:
Data da compra:

Se você estiver usando a data como String, então ela deve ter o formato yyyy-mm-dd;
Se você estiver usando API do Java Time, então ela deve ser uma data menor ou igual à data atual.

Valor: deve ser um BigDecimal positivo.
Estabelecimento: Deve ter pelo menos 2 caracteres.
Categoria: Deve ser uma das opções: Alimentação, Automóvel, Casa, Educação, Lazer ou Saúde.
Cartão: inteiro entre 0 e 1 0000 0000 0000 0000.


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Adaptar função nova-compra

Tarefa
Adapte a função nova-compra para garantir que ela retorne um valor compatível com o schema CompraSchema


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Testar função total-gasto

Tarefa
Criar um teste automatizado para aferir se a função total-gasto funciona corretamente.

A rranjar:
Crie um vetor com 3 compras de valores 100, 200 e 300 respectivamente
Os demais atributos podem ter quaisquer valores válidos.
A gir:
chame a função total-gasto criada na Semana 1 passando o vetor criado anteriormente.
A ssegurar:
garanta que o valor retornado seja igual a 600.


------------------------------------------------------------------------------------------------------------------------
*Essencial*

Testar função que agrupa gastos por categoria

Tarefa
Criar um teste automatizado para aferir se a função que agrupa gastos por categoria funciona corretamente.

Arranjar:
Crie um vetor com algumas compras de categorias diferentes.
Agir:
chame a função que agrupa gastos por categoria criada na Semana 1 passando o vetor criado anteriormente.
Assegurar:
garanta que o valor retornado esteja agrupado corretamente por categoria e os valores correspondentes de cada uma.


------------------------------------------------------------------------------------------------------------------------
*Opcional*

Teste de compra válida


Tarefa
Criar um teste automatizado para aferir o esquema aceita uma compra válida.

Arranjar:
Crie uma compra com os seguintes valores:
:data: 2022-05-09 (String ou LocalDate, de acordo com seu sschema);
:valor: 100M;
:estabelecimento: "Amazon"
:categoria: "Casa"
:cartao: 1111222233334444
Agir:
chame a função validate da biblioteca Prismatic.
Assegurar:
garanta que o valor retornado seja igual ao passado para a função validate.


------------------------------------------------------------------------------------------------------------------------
*Opcional*

Testar cenários inválidos para schema de Compra

Tarefa
Criar um teste automatizado para os cenários inválidos, definidos no checklist abaixo, para o schema de compra.

não foram explorados todos os cenários negativos possíveis.
Regras de aceitação:
Criar um testing para cada cenário;
Chamar a função validate e garantir que a exceção esperada é lançada.

Cenários:
Compra com data inválida (se String, formato inválido; se LocalDate, valor nil)
Valor negativo
Estabelecimento com String vazia
Categoria inválida (inexistente)
Cartão com número inválido


------------------------------------------------------------------------------------------------------------------------
*Opcional* 

Testar outros cenários

Discussão:
Os testes criados anteriormente são suficientes? Você pensa em algum outro cenário de outro ponto do sistema que precise ser testado?

Crie outros testes automatizados para cenários que julgar importantes. Essa tarefa é livre. Deixe aflorar o especialista em teste que há em você! 😊


------------------------------------------------------------------------------------------------------------------------





