# InTheBox 📦

**InTheBox** é um aplicativo Android nativo, desenvolvido com Kotlin, voltado para o cadastro, visualização e gerenciamento de embalagens. O app oferece autenticação, carrinho de compras e histórico da última compra, com persistência local via Room Database.

## Funcionalidades

- **Onboarding e Autenticação**
  - Tela de introdução com redirecionamento automático
  - Registro e login de usuários persistidos localmente com Room

- **Catálogo de Produtos**
  - Visualização em grade de embalagens (nome, preço e dimensões)
  - Cada produto possui uma galeria/carrossel de imagens (ViewPager2)

- **Detalhe do Produto**
  - Exibição completa do produto
  - Botão "Adicionar ao Carrinho"

- **Carrinho**
  - Lista de itens adicionados com preço e dimensões
  - Botão para remover itens
  - Total calculado automaticamente

- **Última Compra**
  - Tela semelhante ao carrinho com total inferior fixo

- **Menu Lateral (Drawer)**
  - Acesso às telas: Produtos, Carrinho, Última Compra, Perfil e Logout

- **Perfil**
  - Exibição do nome e e-mail da sessão atual

## Tecnologias e Bibliotecas

| Tecnologia | Versão | Finalidade |
|------------|--------|------------|
| Android SDK | API 34 | Compatível com 98%+ dos dispositivos |
| Kotlin | 2.0.21 | Linguagem principal |
| AGP (Android Gradle Plugin) | 8.1.0 | Suporte ao Kotlin DSL |
| Room | Incluído via `androidx.room` | Banco de dados local |
| Glide | `com.github.bumptech.glide:glide` | Carregamento eficiente de imagens |
| ViewPager2 | Oficial AndroidX | Carrossel de imagens |
| Navigation Component | `androidx.navigation` | Navegação entre telas |
| ConstraintLayout | 2.2.1 | Layouts modernos e responsivos |

## Estrutura do Projeto

```
com.example.inthebox
├── data
│   ├── dao/
│   ├── entity/
│   ├── model/
│   └── AppDatabase.kt
├── ui
│   ├── adapter/
│   ├── product/
│   ├── profile/
│   ├── CartFragment.kt
│   ├── LastPurchaseFragment.kt
│   └── MainActivity.kt
└── res/
    ├── layout/
    ├── drawable/
    └── navigation/
```

## Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/inthebox.git
   ```

2. Abra com o **Android Studio Flamingo ou superior**

3. Sincronize os arquivos Gradle

4. Execute em um emulador ou dispositivo físico com Android 7.0+

