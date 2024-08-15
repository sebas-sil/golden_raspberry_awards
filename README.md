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
curl -v localhost:8080/films | json_pp
```

Como resultado terá a listagem dos filmes carregados no init

```shell
* Host localhost:8080 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
> GET /films HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.7.1
> Accept: */*
> 
* Request completely sent off
< HTTP/1.1 200 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Thu, 15 Aug 2024 19:27:35 GMT
< 
{ [390 bytes data]
100   383    0   383    0     0  59518      0 --:--:-- --:--:-- --:--:-- 63833
* Connection #0 to host localhost left intact
[
   {
      "id" : 1,
      "producers" : "Allan Carr",
      "studios" : "Associated Film Distribution",
      "title" : "Can't Stop the Music",
      "winner" : true,
      "year" : 1980
   },
   {
      "id" : 2,
      "producers" : "Jerry Weintraub",
      "studios" : "Lorimar Productions, United Artists",
      "title" : "Cruising",
      "winner" : false,
      "year" : 1980
   },
   {
      "id" : 3,
      "producers" : "Steve Shagan",
      "studios" : "United Artists",
      "title" : "The Formula, MGM",
      "winner" : false,
      "year" : 1980
   }
]
```

## controle de tempo

| data | inicio | fim | qtd min | obs |
| ---- | ------ | --- | ------- | --- |
| 2024-08-14 | 15:20 | 16:40 | 80min | Esqueleto da aplicação com das classes principais e teste manual |
| 2024-08-15 | 16:00 | 17:40 | 100min | Carregar banco com arquivo CSV |
