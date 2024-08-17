# Golden Raspberry Awards

Este é um repositório teste para uma entrevista na Outsera. O objetivo é construir uma API Restfull para listar os indicados e vencedores do premio Framboesa de Ouro na categoria pior filme.

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
./mvnw clean spring-boot:run -Dspring-boot.run.arguments="--dbfile_path=/downloads/movielist.csv"
```
Como resultado será visto os logs do spring na saída default. Caso algum erro no carregamento do arquivo, também será mostrado (como exemplo abaixo)
```shell
[INFO] Attaching agents: []

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.2)

2024-08-17T13:08:49.202-03:00 ERROR 33943 --- [golden raspberry awards] [           main] b.c.c.g.film.LoadDatabase                : Invalid Line at 3 with error: Unparseable number: invalid year
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
< Date: Fri, 16 Aug 2024 21:58:30 GMT
< 
{ [270 bytes data]
100   263    0   263    0     0   2146      0 --:--:-- --:--:-- --:--:--  2155
* Connection #0 to host localhost left intact
{
   "max" : [
      {
         "followingWin" : 2015,
         "interval" : 13,
         "previousWin" : 2002,
         "producer" : "Matthew Vaughn"
      },
      {
         "followingWin" : 1995,
         "interval" : 13,
         "previousWin" : 1982,
         "producer" : "Mitsuharu Ishii"
      }
   ],
   "min" : [
      {
         "followingWin" : 1991,
         "interval" : 1,
         "previousWin" : 1990,
         "producer" : "Joel Silver"
      }
   ]
}
```

## controle de tempo

| data | inicio | fim | qtd min | obs |
| ---- | ------ | --- | ------- | --- |
| 2024-08-14 | 15:20 | 16:40 | 80min | Esqueleto da aplicação com das classes principais e teste manual |
| 2024-08-15 | 16:00 | 17:40 | 100min | Carregar banco com arquivo CSV (job seria melhor que CommandLineRunner!) |
| 2024-08-16 | 17:00 | 18:00 | 60min | Normilize database |
| 2024-08-16 | 18:00 | 19:00 | 60min | Queries |
| 2024-08-16 | 20:00 | 20:45 | 45min | Testes |
| 2024-08-17 | 07:30 | 8:30 | 60min | Parametrização do arquivo de banco e testes de arquivo inválido |
| 2024-08-17 | 12:30 | 13:00 | 30min | Parametrização por linha de comando |

total: 7h 15min
