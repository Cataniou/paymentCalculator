# Lunch Split App

Bem-vindo ao Lunch Split App, uma aplicação web simples para calcular e dividir despesas de refeições entre pessoas. Esta aplicação foi desenvolvida usando Vue.js para o frontend e Java com Spring Boot para o backend.

### Instruções de Uso

1. **Selecione o Serviço de Pagamento:**
    - Escolha o serviço de pagamento na lista suspensa.

2. **Digite o Usuário para o Link de Pagamento:**
    - Se o serviço de pagamento selecionado não for 'NENHUM', insira o nome de usuário desejado para o link de pagamento.

3. **Adicione Pessoas:**
    - Insira o nome de cada pessoa e clique em "Adicionar Pessoa".

4. **Adicione Itens Consumidos por Pessoa:**
    - Para cada pessoa, insira os itens consumidos e seus valores.

5. **Adicione Taxas e Descontos:**
    - Adicione taxas e descontos, se necessário.

6. **Calcule o Total:**
    - Clique no botão "Calcular Total" para obter o valor total a ser pago e os detalhes individuais por pessoa.

7. **Resultados:**
    - Os resultados incluem o total de consumo, taxas, descontos, e o valor final a ser pago por cada pessoa.

8. **Pagar e Copiar Link de Pagamento:**
    - Se um serviço de pagamento estiver selecionado, os botões "Pagar" e "Copiar Link de Pagamento" estarão disponíveis para cada pessoa.

9. **Limpar Dados:**
    - Use o botão "Limpar Dados" para reiniciar a aplicação e começar um novo cálculo.

### Observações Importantes

- Certifique-se de inserir valores não negativos para itens, taxas e descontos.
- Os botões de "Pagar" e "Copiar Link de Pagamento" só serão exibidos se um serviço de pagamento válido for selecionado.

### Tecnologias Utilizadas

- Vue.js para o frontend.
- Java com Spring Boot para o backend.

### Como Iniciar o Projeto

1. Clone este repositório.
2. Abra o terminal e vá para o diretório do frontend (`src/main/frontend`).
3. Execute `npm install` para instalar as dependências.
4. Execute `npm run serve` para iniciar o servidor de desenvolvimento do frontend.
5. Em outro terminal, vá para o diretório do backend (`src/main/java/com/lunchsplit`).
6. Execute `./mvnw spring-boot:run` para iniciar o servidor backend.

A aplicação estará disponível em [http://localhost:8080](http://localhost:8080).

### Contribuições

Contribuições são bem-vindas! Se você encontrar problemas ou tiver sugestões de melhorias, sinta-se à vontade para abrir uma issue ou enviar um pull request.

Aproveite o Lunch Split App!