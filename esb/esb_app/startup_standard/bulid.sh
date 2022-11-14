#!/usr/bin/env bash

VERSION="0.0.1"

ESB_BIN="${BASH_SOURCE-$0}"
ESB_BIN="$(dirname "${ESB_BIN}")"
ESB_BIN_DIR="$(cd "${ESB_BIN}"; pwd)"

echo $ESB_BIN_DIR

cd $ESB_BIN_DIR

DIST_BIN_DIR="esb-$VERSION"
mkdir -p $ESB_BIN_DIR/target/dist/$DIST_BIN_DIR

# ESB Provider 打包
mvn assembly:assembly -DskipTests

cp -rf $ESB_BIN_DIR/target/esb-bin/esb/* $ESB_BIN_DIR/target/dist/$DIST_BIN_DIR