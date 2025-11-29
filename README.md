
# 📦 **PetFriends – Arquitetura de Microsserviços (Pedidos, Almoxarifado e Transporte)**  
### 🐶 Sistema distribuído usando Spring Boot, DDD, eventos de domínio e comunicação assíncrona com RabbitMQ

Este projeto implementa **três microsserviços totalmente independentes**, seguindo princípios de **DDD**, **Event-Driven Architecture**, **Spring Cloud Stream**, **RabbitMQ** e **consistência eventual**:

| Microsserviço | Responsabilidade |
|--------------|------------------|
| **petfriends-pedidos** | Gera pedidos, muda status e emite eventos. |
| **petfriends-almoxarifado** | Reserva e separa itens após pagamento (evento PedidoFechado). |
| **petfriends-transporte** | Cria remessa e inicia entrega (evento PedidoDespachado). |

---

# 📘 Sumário

1. Arquitetura Geral  
2. Requisitos  
3. Instalação e Execução  
4. Fluxo Completo da Aplicação  
5. Estrutura dos Microsserviços  
6. Testando com Postman / Curl  
7. RabbitMQ – Filas e Bindings  
8. DDD e Eventos de Domínio  
9. H2 Console  
10. Problemas Comuns e Soluções

---

# 🏗️ **Arquitetura Geral**

```
                ┌──────────────────────────┐
                │  petfriends-web (React)  │
                └────────────┬─────────────┘
                             REST
                               │
                               ▼
┌──────────────────┬───────────────────┬────────────────────┐
│ petfriends-pedidos │ petfriends-almoxarifado │ petfriends-transporte │
└───────────┬────────┴─────────────┬──────────┴─────────────┘
            │ Eventos (RabbitMQ)   │
            ▼                      ▼
     pedidos-fechados       pedidos-despachados
```

---

# ⚙️ **Requisitos**

- Java 21  
- Maven  
- Docker + Docker Compose  
- IntelliJ / VSCode  
- Curl  

---

# 🚀 **Instalação e Execução**

## 1. Subir o RabbitMQ

```bash
docker run -d --name rabbitmq \
  -p 5672:5672 \
  -p 15672:15672 \
  rabbitmq:3-management
```

UI do RabbitMQ: http://localhost:15672  
User: guest / Password: guest

---

## 2. Subir os microsserviços

### Pedidos

```bash
cd petfriends-pedidos
mvn spring-boot:run
```

### Almoxarifado

```bash
cd petfriends-almoxarifado
mvn spring-boot:run
```

### Transporte

```bash
cd petfriends-transporte
mvn spring-boot:run
```

---

# 🔄 **Fluxo Completo da Aplicação**

1. Cliente cria pedido (REST).  
2. Serviço de Pedidos salva o pedido e envia **PedidoFechadoEvent**.  
3. Almoxarifado consome evento e cria **ReservaEstoque**.  
4. Cliente despacha o pedido.  
5. Serviço de Pedidos envia **PedidoDespachadoEvent**.  
6. Transporte consome evento e cria **Entrega**.  

---

# 🧱 **Estrutura dos Microsserviços**

```
src/main/java/br/com/petfriends/<microservico>/
  ├── domain/
  ├── events/
  ├── infra/
  ├── service/
  ├── messaging/
  └── api/
```

---

# 🧪 **Testando com Postman / CURL**

## Criar Pedido

```bash
curl -X POST http://localhost:8080/pedidos \
  -H "Content-Type: application/json" \
  -d '{
        "clienteId": 10,
        "itens": [
          { "produtoId": 1, "quantidade": 2 },
          { "produtoId": 2, "quantidade": 1 }
        ]
      }'
```

### Ver reservas criadas

```bash
curl http://localhost:8082/reservas
```

---

## Despachar Pedido

```bash
curl -X POST http://localhost:8080/pedidos/1/despachar \
  -H "Content-Type: application/json" \
  -d '{
        "endereco": {
          "logradouro": "Rua A",
          "numero": "123",
          "complemento": "Ap 1",
          "bairro": "Centro",
          "cidade": "Quebec",
          "estado": "QC",
          "cep": "G1X0X0"
        },
        "valorFrete": 20.50
      }'
```

### Ver entregas criadas

```bash
curl http://localhost:8083/entregas
```

---

# 📡 **RabbitMQ – Filas**

| Evento | Produzido por | Consumido por | Fila |
|--------|---------------|---------------|------|
| PedidoFechadoEvent | Pedidos | Almoxarifado | pedidos-fechados |
| PedidoDespachadoEvent | Pedidos | Transporte | pedidos-despachados |

---

# 🧩 **DDD**

- Agregados:
  - Pedido
  - ReservaEstoque
  - Entrega

- Value Objects:
  - ItemPedido
  - ItemReservado
  - EnderecoEntrega

- Eventos:
  - PedidoFechadoEvent
  - PedidoDespachadoEvent

---

# 🗄️ H2 Console

| Serviço | URL |
|--------|-----|
| Pedidos | http://localhost:8080/h2-console |
| Almoxarifado | http://localhost:8082/h2-console |
| Transporte | http://localhost:8083/h2-console |

---

# 🛠️ Problemas Comuns

## Evento não consumido
- Verifique se o **binding** está correto
- Verifique se a função no application.yml bate com o nome do Bean

## RabbitMQ recusando conexão
- Container não está rodando  
- Porta 5672 ocupada  

## Entrega não aparece
- Pedido não foi despachado  
- Veja os logs do transporte  

---

# 🎓 **Conclusão**

Este README foi projetado para permitir que qualquer professor/testador:

- Inicie todos os serviços rapidamente  
- Teste o fluxo completo  
- Avalie DDD, eventos, mensageria e arquitetura  
- Use Postman, Curl e RabbitMQ com facilidade  

Se você precisar de:
- PDF  
- README com imagens  
- docker-compose automático  
- documentação técnica detalhada  

Basta pedir!
