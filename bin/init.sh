#!/bin/bash
set -ex

WORKSPACE=$1
COMMIT_URL=$2

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
CODE_DIR="$(dirname ${SCRIPT_DIR})"

DEST_HELLOWORLD_DIR=$WORKSPACE/smartsight-helloworld-sl
DEST_SIMULATOR_DIR=$WORKSPACE/smartsight-simulator-sl
mkdir -p $DEST_HELLOWORLD_DIR
mkdir -p $DEST_SIMULATOR_DIR
rm -rf $DEST_HELLOWORLD_DIR/* $DEST_SIMULATOR_DIR/*

# compile project
cd $CODE_DIR
mvn clean install -Dmaven.test.skip=true

# create helloworld-sl
rm -rf fake/helloworld/*.tar.gz
AGENT_PACKAGE=$(basename agent/target/*.tar.gz)
cp -rf agent/target/$AGENT_PACKAGE fake/helloworld
cd fake/helloworld
tar czvf smartsight-helloworld.tar.gz $AGENT_PACKAGE hello
cp -rf smartsight-helloworld.tar.gz Dockerfile $DEST_HELLOWORLD_DIR
echo $COMMIT_URL > $DEST_HELLOWORLD_DIR/change.list

# create simulator-sl
cd $CODE_DIR/fake/simu
rm -rf ./*.tar.gz
tar czvf smartsight-simulator.tar.gz *.py
cp -rf smartsight-simulator.tar.gz Dockerfile $DEST_SIMULATOR_DIR
echo $COMMIT_URL > $DEST_SIMULATOR_DIR/change.list

