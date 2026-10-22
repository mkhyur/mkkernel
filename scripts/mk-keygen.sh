#!/usr/bin/env bash
# mkkernel: gera sua chave de assinatura e imprime os valores que o kernel precisa.
# Uso: ./scripts/mk-keygen.sh
# Requer: JDK (keytool) e openssl.
set -euo pipefail
KS=mkkernel.jks; ALIAS=mkkernel
[ -f "$KS" ] || keytool -genkeypair -v -keystore "$KS" -alias "$ALIAS" -keyalg RSA -keysize 4096 -validity 36500 -dname "CN=mkkernel"
keytool -exportcert -keystore "$KS" -alias "$ALIAS" -rfc | openssl x509 -outform DER -out /tmp/mk.der
SIZE=$(stat -c %s /tmp/mk.der 2>/dev/null || stat -f %z /tmp/mk.der)
HASH=$(openssl dgst -sha256 /tmp/mk.der | awk '{print $NF}')
echo
echo "Compile o kernel/LKM com:"
echo "  KSU_NEXT_MANAGER_SIZE=$SIZE KSU_NEXT_MANAGER_HASH=$HASH"
echo "Assine o APK do manager com $KS (alias $ALIAS). NAO suba o .jks no GitHub."
