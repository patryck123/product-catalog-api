# 🏷️ Product Catalog API

Catálogo de produtos com sistema de avaliações, reviews e filtros por categoria.

## 📋 Sobre o Projeto

API de catálogo focada na experiência do consumidor. Além de listar produtos com suas características, permite que clientes deixem avaliações com nota e texto. O sistema calcula a média de estrelas e lista os produtos mais bem avaliados.

## ✨ Funcionalidades

- ✅ Gerenciar catálogo de produtos
- ✅ Organizar por categorias
- ✅ Avaliações com nota (1-5) e comentário
- ✅ Cálculo automático de média de estrelas
- ✅ Listagem dos mais bem avaliados
- ✅ Filtrar por categoria
- ✅ Busca por nome
- ✅ Produtos em destaque

## 🔗 Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| GET/POST | `/api/catalog` | Listar / Cadastrar produto |
| GET | `/api/catalog/top-rated` | Mais bem avaliados |
| POST | `/api/catalog/{id}/reviews` | Adicionar avaliação |
| GET | `/api/catalog/{id}/reviews` | Ver avaliações |
| GET | `/api/catalog?category={id}` | Filtrar por categoria |

## 🛠️ Tecnologias

- Java 17 · Spring Boot 3.2 · PostgreSQL · Maven · Lombok
