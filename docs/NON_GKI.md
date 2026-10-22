# Root em kernels NON-GKI (mkkernel)

O mkkernel suporta dois metodos de integracao com o kernel. Escolha pelo seu aparelho:

## 1. GKI (modulo de kernel / LKM) — Android 12+ com GKI 2.0
A maioria dos celulares recentes (kernel 5.10+ no formato GKI) usa um **modulo carregavel**
(`*_kernelsu.ko`), sem precisar recompilar o kernel inteiro. Baixe o `.ko` certo para a
versao do seu kernel na pagina de Releases e instale pelo app (aba Instalar > Selecionar e Corrigir,
ou via modulo LKM direto se seu boot/init_boot ja suportar).

## 2. NON-GKI / kernels antigos ou customizados (integracao por codigo-fonte)
Aparelhos com kernel **non-GKI** (a maioria pre-Android 12, ou ROMs/kernels customizados que nao
seguem o padrao GKI) **nao suportam LKM dinamico**. Para esses, o KernelSU precisa ser **colado no
codigo-fonte do kernel antes de compilar**. E para isso que serve `kernel/setup.sh`.

### Passo a passo
1. Tenha o codigo-fonte do kernel do seu aparelho (do fabricante ou de uma ROM custom) e um
   toolchain de build funcionando (clang/gcc, como o fabricante recomenda).
2. Na raiz do codigo-fonte do kernel, rode:
   ```bash
   curl -LSs "https://raw.githubusercontent.com/mkhyur/mkkernel/main/kernel/setup.sh" | bash -s v1.0.0
   ```
   Isso clona o mkkernel, cria um symlink `drivers/kernelsu -> mkkernel/kernel`, e edita o
   `Makefile`/`Kconfig` dos drivers para incluir o KSU no build.
3. Habilite `CONFIG_KSU=y` no seu `defconfig` (e, se seu kernel usar, `CONFIG_KSU_SUSFS=y` para o
   SuSFS incluso).
4. Antes de compilar, gere sua chave e exporte os valores que o kernel usa para reconhecer SO o seu
   manager (veja `scripts/mk-keygen.sh` no repositorio do manager):
   ```bash
   export KSU_NEXT_MANAGER_SIZE=<tamanho>
   export KSU_NEXT_MANAGER_HASH=<sha256>
   ```
   Esses dois valores viram `EXPECTED_MANAGER_SIZE`/`EXPECTED_MANAGER_HASH` em
   `kernel/manager/apk_sign.c`, usados para validar a assinatura do APK em tempo de boot.
5. Compile o kernel normalmente (`make`, `build.sh` do seu dispositivo, etc.) e flashe a imagem
   resultante (`boot.img`/`Image`) do jeito que voce ja usa para esse aparelho.
6. Instale o APK mkkernel (normal ou spoofed, baixado da pagina de Releases) e confirme na tela
   inicial que o root foi reconhecido.

### Reverter
```bash
curl -LSs "https://raw.githubusercontent.com/mkhyur/mkkernel/main/kernel/setup.sh" | bash -s --cleanup
```

### Observacoes
- Este metodo exige que **voce mesmo compile o kernel** do seu aparelho; o mkkernel nao fornece um
  kernel binario pronto para non-GKI (cada aparelho tem drivers/arvore diferentes).
- A versao normal e a spoofed do APK funcionam igual com o mesmo kernel: o reconhecimento e feito
  pela assinatura (hash), nao pelo nome do pacote.
