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
        totalDiscounts: null,
        totalConsumption: null,
        peopleValues: []
    },
    methods: {
        addPerson() {
            // Remova espaços em branco no início e final do nome
            const cleanedName = this.newPerson.name.trim();

            // Verifique se já existe uma pessoa com o mesmo nome
            const personExists = this.persons.some(person => person.name.trim() === cleanedName);

            if (personExists) {
                alert('Já existe uma pessoa com esse nome. Por favor, escolha outro nome.');
                return; // Impede a adição se já existir uma pessoa com o mesmo nome
            }

            // Adicione a nova pessoa apenas se não existir uma pessoa com o mesmo nome
            this.persons.push({
                name: cleanedName,
                newItem: {
                    item: '',
                    itemPrice: 0
                },
                items: [] // Lista de itens consumidos pela pessoa
            });

            // Limpe os campos após adicionar a pessoa
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

            // Limpe os campos após adicionar o item
            person.newItem = {
                item: '',
                itemPrice: 0
            };
        },
        removeItem(person, itemIndex) {
            // Remova o item da lista de itens da pessoa
            person.items.splice(itemIndex, 1);
        },
        addTax() {
            this.taxes.push({
                value: 0,
                isPercentage: true // Porcentagem por padrão
            });
        },
        removeTax(index) {
            // Remova a taxa da lista de taxas
            this.taxes.splice(index, 1);
        },
        addDiscount() {
            this.discounts.push({
                value: 0,
                isPercentage: true // Porcentagem por padrão
            });
        },
        removeDiscount(index) {
            // Remova o desconto da lista de descontos
            this.discounts.splice(index, 1);
        },
        calculateTotal() {

            if (this.persons.length === 0 || this.persons.every(person => person.items.length === 0)) {
                alert('Adicione pelo menos uma pessoa com itens consumidos para calcular o total.');
                return;
            }

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

            fetch('http://localhost:8080/lunch/split', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(requestData),
            })
            .then(response => response.text())
            .then(data => {
                const responseData = JSON.parse(data);
                this.totalConsumption = responseData.totalConsumption;
                this.totalTaxes = responseData.totalTaxes;
                this.totalDiscounts = responseData.totalDiscounts;
                this.finalAmount = responseData.totalToPay;
                this.peopleValues = responseData.peopleValues;
            })
            .catch(error => {
                console.error('Erro ao processar o pedido:', error);
            });
        }
    }
});