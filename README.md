# mkkernel

Fork do [KernelSU-Next](https://github.com/KernelSU-Next/KernelSU-Next) (GPL-2.0) com identidade propria.
Os creditos dos autores originais e a licenca GPL-2.0 foram mantidos (veja LICENSE).

Status: app renomeado para "mkkernel". Pacote interno (com.rifsxd.ksunext) mantido por enquanto,
pois o JNI e a verificacao de assinatura do manager no kernel dependem dele.

## Independencia (v0.2)
- Pacote do app: `com.mkkernel.manager` (JNI e manifest atualizados).
- Assinatura propria: rode `scripts/mk-keygen.sh`; ele gera a keystore e imprime
  `KSU_NEXT_MANAGER_SIZE` / `KSU_NEXT_MANAGER_HASH`. Passe esses valores ao compilar seu kernel
  customizado (Kconfig/Makefile do diretorio `kernel/`) para que ele reconheca SO o seu manager.
- Ainda nao compilado/testado: o build do APK precisa do Android SDK+NDK (ou do workflow em `.github/workflows`).
