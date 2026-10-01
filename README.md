# 🎣 FishLog

O **FishLog** é uma plataforma backend em **Java (Spring Boot)** concebida como um diário de bordo digital e ecossistema inteligente para pescadores desportivos. O projeto tem como objetivo registar capturas, analisar dados meteorológicos e otimizar as jornadas de pesca através da análise de padrões ambientais.

---

## 🚀 Visão Geral e Funcionalidades

A aplicação está estruturada para evoluir por fases, cobrindo os seguintes recursos:

* **Gestão de Capturas & Histórico:** Registo detalhado de exemplares capturados (espécie, peso, comprimento, localização e equipamento utilizado).
* **Integração Meteorológica:** Captura automática de dados de clima, pressão atmosférica e temperatura via API externa no momento do registo.
* **Catálogo de Espécies:** Consulta de detalhes biológicos, regulamentação (tamanhos mínimos) e épocas de defeso.
* **Motor de Recomendações (BaitPicker):** Algoritmo para sugestão de iscas com base no clima atual e na espécie-alvo.
* **Segurança e Controlo de Acesso:** Autenticação de utilizadores com gestão de acessos privados e públicos.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework Principal:** Spring Boot 3
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Base de Dados:** PostgreSQL (Produção) / H2 (Desenvolvimento)
* **Segurança:** Spring Security + JWT
* **Outras Ferramentas:** Lombok, Bean Validation, RestTemplate/WebClient, Docker

---

## 🗺️ Roteiro de Desenvolvimento (Roadmap)

- [ ] **Fase 1: Módulo Base (MVP)**
  - [ ] Mapeamento das entidades principais (`Peixe`, `Captura`, `Utilizador`).
  - [ ] Criação dos endpoints REST para CRUDs básicos.
- [ ] **Fase 2: Segurança & Boas Práticas**
  - [ ] Implementação de autenticação com Spring Security e JWT.
  - [ ] Padronização de respostas com DTOs e tratamento global de exceções.
- [ ] **Fase 3: Integrações Externas**
  - [ ] Consumo de API externa de meteorologia (OpenWeatherMap).
  - [ ] Configuração de tarefas agendadas (`@Scheduled`) e envio de notificações por e-mail.
- [ ] **Fase 4: Inteligência & Regras de Negócio**
  - [ ] Implementação de padrões de projeto (Strategy) para recomendação de iscas.
  - [ ] Elaboração de relatórios estatísticos de capturas.
- [ ] **Fase 5: DevOps & Documentação**
  - [ ] Conteinerização da aplicação com Docker e Docker Compose.
  - [ ] Documentação interativa da API com Swagger / OpenAPI
