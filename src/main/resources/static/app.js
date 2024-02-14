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
        newPersonName: '',
        taxes: [],
        discounts: [],
        finalAmount: null,
        totalTaxes: null,
        totalDiscounts: null,
        totalConsumption: null,
        peopleValues: [],
        paymentServices: [],
        selectedPaymentService: null,
        userInput: '',
        personToRemove: null
    },
    methods: {
        addPerson() {
            const cleanedName = this.newPerson.name.trim();

            const personExists = this.persons.some(person => person.name.trim() === cleanedName);

            if (personExists) {
                alert('Já existe uma pessoa com esse nome. Por favor, escolha outro nome.');
                return;
            }

            if (cleanedName === '') {
                alert('Por favor, insira um nome válido para a pessoa.');
                return;
            }

            // Adicione a nova pessoa apenas se não existir uma pessoa com o mesmo nome
            this.persons.push({
                name: cleanedName,
                newItem: {
                    item: '',
                    itemPrice: 0
                },
                items: []
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
            person.items.splice(itemIndex, 1);
        },
        addTax() {
            this.taxes.push({
                value: 0,
                isPercentage: false
            });
        },
        removeTax(index) {
            this.taxes.splice(index, 1);
        },
        addDiscount() {
            this.discounts.push({
                value: 0,
                isPercentage: false
            });
        },
        removeDiscount(index) {
            this.discounts.splice(index, 1);
        },
        calculateTotal() {

            if (!this.selectedPaymentService) {
                alert('Selecione um serviço de pagamento antes de calcular o total.');
                return;
            }

            if (this.selectedPaymentService !== 'NENHUM' && !this.userInput) {
                alert('Por favor, preencha o campo de usuário.');
                return;
            }

            if (this.persons.length === 0 || this.persons.every(person => person.items.length === 0)) {
                alert('Adicione pelo menos uma pessoa com itens consumidos para calcular o total.');
                return;
            }

            const peopleData = this.persons.map(person => {
                return {
                    name: person.name,
                    items: person.items.map(item => {
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
                discounts: discountsData,
                paymentService: this.selectedPaymentService,
                userInput: this.userInput
            };

            console.log(requestData); // Printa no log o que foi mandado para o BackEnd
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
        },
        refreshPage() {
            location.reload();
        },
        copyPaymentLink(linkToPay) {
            const inputElement = document.createElement('input');
            inputElement.value = linkToPay;

            document.body.appendChild(inputElement);

            inputElement.select();
            inputElement.setSelectionRange(0, 99999); // Para dispositivos móveis

            document.execCommand('copy');

            document.body.removeChild(inputElement);

            alert('Link de pagamento copiado para a área de transferência!');
        },
        removePerson(index) {
            this.personToRemove = index;
            this.persons.splice(this.personToRemove, 1);

            // Limpando variavel de controle
            this.personToRemove = null;
        }
    },
    created() {
        // Ao criar a instância Vue, faça uma solicitação GET para obter os serviços de pagamento
        fetch('http://localhost:8080/paymentServices')
            .then(response => response.json())
            .then(data => {
                // Atualize o array paymentServices com os serviços obtidos do backend
                this.paymentServices = data;
            })
            .catch(error => {
                console.error('Erro ao obter os serviços de pagamento:', error);
            });
    }
});