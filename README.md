<p align="center"><img src="assets/mk/logo.png" width="220" alt="mkkernel"></p>

# mkkernel

> **Developer: MK** ([@mkhyur](https://github.com/mkhyur)) — mantenedor e desenvolvedor oficial do mkkernel.

mkkernel e um gerenciador de root para Android com tema Uchiha (azul-noite, Chidori e Sharingan),
em duas versoes: **normal** (`com.mkkernel.manager`) e **spoofed** (pacote aleatorio).
E um fork do [KernelSU-Next](https://github.com/KernelSU-Next/KernelSU-Next) (GPL-2.0); os creditos
dos autores originais e a licenca foram mantidos (veja `LICENSE`).

## Desenvolvedor
- **MK** — idealizacao, identidade visual, tema, build normal/spoofed e integracao com kernels customizados.

## Creditos
- KernelSU / KernelSU-Next e seus contribuidores (base do projeto, GPL-2.0).
- O personagem usado no logo pertence aos seus criadores (Masashi Kishimoto / Shueisha / Pierrot);
  a imagem e usada apenas como icone de fa, sem fins comerciais.
## Downloads
Veja a aba [Releases](https://github.com/mkhyur/mkkernel/releases): cada versao traz o APK
**normal** (`com.mkkernel.manager`) e o APK **spoofed** (pacote aleatorio), alem dos modulos de
kernel (`.ko`), zips AnyKernel3 e o `ksud`.

## Kernels suportados
- **GKI (modulo/LKM):** baixe o `.ko` da sua versao de kernel na pagina de Releases. Nao precisa
  recompilar o kernel.
- **NON-GKI / kernel customizado (integracao por codigo-fonte):** veja o guia completo em
  [`docs/NON_GKI.md`](docs/NON_GKI.md). Em resumo, rode `kernel/setup.sh` na arvore do seu kernel,
  habilite `CONFIG_KSU=y` e compile normalmente.

## Independencia
- Pacote do app: `com.mkkernel.manager` (JNI e manifest atualizados).
- Assinatura propria: rode `scripts/mk-keygen.sh`; ele gera a keystore e imprime
  `KSU_NEXT_MANAGER_SIZE` / `KSU_NEXT_MANAGER_HASH`. Passe esses valores ao compilar seu kernel
  customizado (Kconfig/Makefile do diretorio `kernel/`) para que ele reconheca SO o seu manager.

