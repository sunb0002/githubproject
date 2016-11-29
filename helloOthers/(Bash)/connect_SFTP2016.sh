#!/bin/sh

echo "===Server List===
xxxx 1.1.1.1
yyyy 2.2.2.2
===Port usage====
SB 12345
(pls add)
=================
"

echo -n "madoka says, Enter your destination server IP: "
read IP

echo -n "kyouko says, Enter your Port Number: "
read port

ssh hahaha@1.1.1.1 -L "$port":"$IP":22