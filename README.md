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
## Independencia (v0.2)
- Pacote do app: `com.mkkernel.manager` (JNI e manifest atualizados).
- Assinatura propria: rode `scripts/mk-keygen.sh`; ele gera a keystore e imprime
  `KSU_NEXT_MANAGER_SIZE` / `KSU_NEXT_MANAGER_HASH`. Passe esses valores ao compilar seu kernel
  customizado (Kconfig/Makefile do diretorio `kernel/`) para que ele reconheca SO o seu manager.
- Ainda nao compilado/testado: o build do APK precisa do Android SDK+NDK (ou do workflow em `.github/workflows`).

