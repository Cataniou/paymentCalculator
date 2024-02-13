// app.js
new Vue({
    el: '#app',
    data: {
        persons: [],
        newPerson: {
            name: '',
            newItem: {
                item: '',
                itemPrice: 0
            }
        },
        taxes: [],
        discounts: [],
        finalAmount: null,
        totalTaxes: null,
        totalDiscounts: null
    },
    methods: {
        addPerson() {
            // Remove espaços em branco no início e final do nome
            const cleanedName = this.newPerson.name.trim();

            // Verifica se já existe uma pessoa com o mesmo nome
            const personExists = this.persons.some(person => person.name.trim() === cleanedName);

            if (personExists) {
                alert('Já existe uma pessoa com esse nome. Por favor, escolha outro nome.');
                return; // Impede a adição se já existir uma pessoa com o mesmo nome
            }

            // Adiciona a nova pessoa apenas se não existir uma pessoa com o mesmo nome
            this.persons.push({
                name: cleanedName,
                newItem: {
                    item: '',
                    itemPrice: 0
                },
                items: [] // Lista de itens consumidos pela pessoa
            });

            // Limpa os campos após adicionar a pessoa
            this.newPerson = {
                name: '',
                newItem: {
                    item: '',
                    itemPrice: 0
                }
            };
        },
        addItem(person) {
            person.items.push({
                item: person.newItem.item,
                itemPrice: parseFloat(person.newItem.itemPrice)
            });

            // Limpa os campos após adicionar o item
            person.newItem = {
                item: '',
                itemPrice: 0
            };
        },
        removeItem(person, itemIndex) {
            // Remove o item da lista de itens da pessoa
            person.items.splice(itemIndex, 1);
        },
        addTax() {
            this.taxes.push({
                value: 0,
                isPercentage: true // Porcentagem por padrão
            });
        },
        removeTax(index) {
            // Remove a taxa da lista de taxas
            this.taxes.splice(index, 1);
        },
        addDiscount() {
            this.discounts.push({
                value: 0,
                isPercentage: true // Porcentagem por padrão
            });
        },
        removeDiscount(index) {
            // Remove o desconto da lista de descontos
            this.discounts.splice(index, 1);
        },
        calculateTotal() {
            const peopleData = this.persons.map(person => {
                // Para cada pessoa, cria um objeto com o formato desejado
                return {
                    name: person.name,
                    items: person.items.map(item => {
                        // Para cada item consumido, cria um objeto no formato desejado
                        return {
                            name: item.item,
                            value: item.itemPrice
                        };
                    })
                };
            });

            const taxesData = this.taxes.map(tax => {
                return {
                    isPercentage: tax.isPercentage,
                    value: parseFloat(tax.value)
                };
            });

            const discountsData = this.discounts.map(discount => {
                return {
                    isPercentage: discount.isPercentage,
                    value: parseFloat(discount.value)
                };
            });

            const requestData = {
                people: peopleData,
                taxes: taxesData,
                discounts: discountsData
            };

            // Agora você pode enviar 'requestData' para o backend
            console.log(JSON.stringify(requestData));

            // var requestData = "{\"People\":[{\"name\":\"Joao\",\"Itens\":[{\"Hamburguer\":16},{\"Batata\":20}]},{\"name\":\"Carlos\",\"Itens\":[{\"Porção\":30},{\"Refrigerante\":5}]},{\"name\":\"Lucas\",\"Itens\":[{\"Salada\":28},{\"Suco\":9}]}],\"Taxes\":[{\"Percentage\":true,\"Value\":10},{\"Percentage\":false,\"Value\":2.99}],\"Discounts\":[{\"Percentage\":true,\"Value\":2},{\"Percentage\":false,\"Value\":1.98}]}"
            //
            // requestData = requestData.replace('\\', '');
            // requestData = requestData.replace('\\', '');
            fetch('http://localhost:8080/lunch/split', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(requestData),
            })
                .then(response => response.text())
                .then(data => {
                })
                .catch(error => {
                    console.error('Erro ao processar o pedido:', error);
                });
            // Você deve enviar 'requestData' para o seu backend usando uma requisição HTTP, como axios ou fetch.

            // Lógica para calcular o total de consumo, considerando taxas extras e descontos
            const consumption = this.totalConsumption;

            // Calcula o total de taxas cumulativas
            this.totalTaxes = this.taxes.reduce((total, tax) => {
                if (tax.isPercentage) {
                    return total + (consumption * (tax.value / 100));
                } else {
                    return total + parseFloat(tax.value);
                }
            }, 0);

            // Calcula o total de descontos cumulativos
            this.totalDiscounts = this.discounts.reduce((total, discount) => {
                if (discount.isPercentage) {
                    return total + (consumption * (discount.value / 100));
                } else {
                    return total + parseFloat(discount.value);
                }
            }, 0);

            // Calcula o total com taxas e descontos
            this.finalAmount = consumption + this.totalTaxes - this.totalDiscounts;

            // Lógica para gerar o link do Picpay
            this.picpayLink = 'https://www.picpay.com/';
        }
    },
    computed: {
        totalConsumption() {
            // Calcula o total de consumo
            return this.persons.reduce((total, person) => {
                const itemsTotal = person.items.reduce((itemTotal, item) => itemTotal + item.itemPrice, 0);
                return total + itemsTotal;
            }, 0);
        }
    }
});