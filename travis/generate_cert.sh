#!/bin/bash
source /etc/profile
echo -e "$KEY_PASS\n$KEY_PASS\n$KEY_PASS\n$KEY_PASS" | keytool -genkey -alias sslcert -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650 -name "CN=Mahallem, OU=mah, O=mah, L=mah, ST=mah,C=TR"