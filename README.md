# Golden Raspberry Awards

Este é um repositório teste para uma entrevista na git. O objetivo é construir uma API Restfull para listar os indicados e vencedores do premio Framboesa de Ouro na categoria pior filme.

A saída será um JSON mostrando o(s) produtor com o maior intervalo entre um premio e outro e o de menor intervalo:

```json
{
    "min": [
        {
            "producer": "Producer 1",
            "interval": 1,
            "previousWin": 2008,
            "followingWin": 2009
        },
        {
            "producer": "Producer 2",
            "interval": 1,
            "previousWin": 2018,
            "followingWin": 2019
        }
    ],
    "max": [
        {
            "producer": "Producer 1",
            "interval": 99,
            "previousWin": 1900,
            "followingWin": 1999
        },
        {
            "producer": "Producer 2",
            "interval": 99,
            "previousWin": 2000,
            "followingWin": 2099
        }
    ]
}
```

A entrada será um arquivo CSV que deve ser carregado ao iniciar a aplicação:

```csv
year;title;studios;producers;winner                                                   
1980;Can't Stop the Music;Associated Film Distribution;Allan Carr;yes
1980;Cruising;Lorimar Productions, United Artists;Jerry Weintraub;
1980;The Formula;MGM, United Artists;Steve Shagan;
```


## Executando o código

Execute os passo em ordem:

1. Baixe o código:
```shell
git clone git@github.com:sebas-sil/golden_raspberry_awards.git
```
2. execute o maven:
```shell
/mvnw clean spring-boot:run
```
3. teste a aplicação:
```shell
curl -v localhost:8080/films    
```

Como resultado terá a listagem dos filmes carregados no init

```shell
* Host localhost:8080 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
> GET /films HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.6.0
> Accept: */*
> 
< HTTP/1.1 200 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Wed, 14 Aug 2024 19:24:59 GMT
< 
* Leftovers after chunking: 5 bytes
* Connection #0 to host localhost left intact
[{"id":1,"year":1980,"title":"Can't Stop the Music","studios":"Associated Film Distribution","producers":"Allan Carr","winner":true},{"id":2,"year":1980,"title":"Cruising","studios":"Lorimar Productions, United Artists","producers":"Jerry Weintraub","winner":false},{"id":3,"year":1980,"title":"The Formula, MGM","studios":"United Artists","producers":"Steve Shagan","winner":false}]
```