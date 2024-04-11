#!/bin/sh
geth init --datadir /root/drm-ethereum genesis.json

rm -f /root/drm-ethereum/geth/nodekey

geth "$@"