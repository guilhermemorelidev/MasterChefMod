# MasterChef Mod (Forge 1.7.10)

Este é um mod de culinária para Minecraft 1.7.10 criado originalmente por **Minetronic**, 

---

## ⚠️ Erro original

Ao iniciar um mundo, o jogo crashava com o seguinte erro:

java.lang.NullPointerException: Ticking memory connection
at cpw.mods.fml.common.network.internal.FMLProxyPacket.func_148833_a(FMLProxyPacket.java:101)

Esse erro acontecia por causa de uma falha de rede ao buscar a versão mais recente do mod através da classe `JavaGetUrl`. O sistema não tratava corretamente casos de erro de conexão, resultando em `null` durante o processamento de pacotes de rede.

---

## 🛠️ Processo de Correção

1. **Desofuscação com BON2**  
   O `.jar` original do mod MasterChef estava ofuscado (com nomes ilegíveis como `func_148833_a`). Para restaurar os nomes legíveis, utilizei o **[BON2 (tterrag1098)](https://github.com/tterrag1098/BON2/releases)**, uma ferramenta voltada para mods 1.7.10 com suporte ao MCP.

2. **Descompilação com CFR**  
   Após a desofuscação, utilizei o decompilador **CFR** para obter o código-fonte `.java` do mod.

3. **Reestruturação do Projeto**  
   - Organizei os pacotes e arquivos em um projeto ForgeGradle compatível.

4. **Correção de código**  
   - Corrigi erros de tipagem com `HashMap`.
   - Adicionei tratamento de exceções nas classes `JavaGetUrl` e `MessageChecker`.
   - Corrigi a lógica de `ItemChefGarb` para evitar `ConcurrentModificationException`.

---

## ✅ O que foi corrigido

### 🔧 Arquivo: `ItemChefGarb.java`

- Corrigida a tipagem incorreta dos mapas:
  ```java
  HashMap<Object, Object> → HashMap<String, ExtraSpeed>
Corrigido uso da variável extraFurnaceSpeed para aceitar corretamente mapas tipados.

Corrigida a lógica do purgeList() para evitar ConcurrentModificationException.

🌐 Arquivo: JavaGetUrl.java
Tratamento de exceções de rede adicionado.

Corrigido o acesso à InputStream.

Corrigida separação entre VERSION e EXTRA_INFO.

🛠️ Ambiente de Build
Este projeto utiliza:

ForgeGradle mantido por anatawa12, adaptado para compilar mods antigos (como 1.7.10) em setups modernos de Gradle.
https://github.com/anatawa12/ForgeGradle-example

Java 8 (recomendado: Oracle JDK 1.8.0_202)

Gradle 7.x+

📦 Como compilar
./gradlew build
Após o build, o .jar gerado estará em build/libs/.

📥 Como instalar
Instale o Forge 1.7.10 (recomendado: 10.13.4.1614)

Coloque o .jar em .minecraft/mods

Inicie o Minecraft com Forge

👥 Créditos
👨‍🍳 Minetronic — autor original do mod

🛠️ anatawa12 — ForgeGradle moderno usado para compilar

🔁 Correções e manutenção: Este fork foi corrigido com foco em manter compatibilidade do mod, que já não funfava mais.

🤝 Contribuindo
Sugestões, melhorias ou correções são sempre aceitas.

Você pode ajudar com:
Novas receitas ou itens
Melhorias gráficas ou de desempenho
Traduções e localização
Compatibilidade com outros mods

