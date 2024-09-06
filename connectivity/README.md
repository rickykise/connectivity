# fassto_connectivity

## Project 구조
![Project 구조](image/connectivity-v1-architecture.png)

# API 호출 관계

## ERP to Solochain

![ERP to Solochain](image/fassto_connectivity-Erp To Solochain.drawio.png)

### API 
* ERP <--> Connectivity: /api/solochain/v1/{api_name}
* Connectivity <--> Solochain: /api/v1/{api_name}


## Solochain to ERP (or Carrier)

### API
Connectivity: /api/solochain/v1/{api_name}

![Solochain to ERP (or Carrier)](image/fassto_connectivity-Solochain To Erp.drawio.png)

# Accept-Language
* Korean: ko
* English: en
