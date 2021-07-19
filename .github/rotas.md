# Rotas da Aplicação

Este arquivo especifica as rotas da aplicação, seus métodos e parâmetros.

## Bairros

### Criar um bairro

Exemplo de requisição:
```javascript
POST /district
{
  "prop_district": "Vila Olímpica",
  "value_district_m2": 12345.12
}
```

Exemplo de resposta:
```javascript
Status: 201
{
  "id": 5,
  "prop_district": "Vila Olimpica",
  "value_district_m2": 12345.12
}
```

### Retornar todos os bairros

Exemplo de requisição:
```javascript
GET /district
```

Exemplo de resposta (200):
```javascript
[
  {
    "id": 1,
    "prop_district": "Japiim 2",
    "value_district_m2": 2933.5
  },
  {
    "id": 2,
    "prop_district": "Vieralves",
    "value_district_m2": 5432.7
  },
  {
    "id": 3,
    "prop_district": "Ponta Negra",
    "value_district_m2": 7931.3
  },
  {
    "id": 4,
    "prop_district": "Cachoeirinha",
    "value_district_m2": 1730.9
  }
]
```

## Propriedades

### Criar uma propriedade

Exemplo de requisição:
```javascript
POST /property
{
  "prop_name": "Casa 1",
  "prop_district_id": 1,
  "rooms": [
      {
        "room_name": "Quarto",
        "room_width": 25.0,
        "room_length": 25.0
      },
      {
        "room_name": "Banheiro",
        "room_width": 15.0,
        "room_length": 15.0
      }
  ]
}
```

Exemplo de resposta:
```javascript
Status: 201
{
  "id": 1,
  "prop_name": "Casa 1",
  "district": {
    "id": 1,
    "prop_district": "Japiim 2",
    "value_district_m2": 2933.5
  },
  "rooms": [
    {
      "room_name": "Quarto",
      "room_width": 25.0,
      "room_length": 25.0
    },
    {
      "room_name": "Banheiro",
      "room_width": 15.0,
      "room_length": 15.0
    }
  ]
}
```

### Valor total de metros quadrados

Exemplo de requisição
```javascript
GET /property/1/totalSquareMeters
```

```javascript
{
  "id": 1,
  "prop_name": "Casa 1",
  "total_square_meters": 850.0
}
```


### Valor total da propriedade

Exemplo de requisição:
```javascript
GET /property/1/totalValue
```

Exemplo de resposta:
```javascript
{
  "id": 1,
  "prop_name": "Casa 1",
  "total_value": 2493475.00
}
```

### Maior cômodo

Exemplo de requisição:
```javascript
GET /property/1/biggestRoom
```

Exemplo de resposta:
```javascript
{
  "room_name": "Quarto",
  "square_meters": 625.0
}
```

### Quantidade de metros quadrados de cada cômodo

Exemplo de requisição:
```javascript
GET /property/1/rooms
```

Exemplo de resposta:
```javascript
[
  {
    "room_name": "Quarto",
    "square_meters": 625.0
  },
  {
    "room_name": "Banheiro",
    "square_meters": 225.0
  }
]
```