#!/bin/bash

mkdir -p classes && javac -d classes src/**/*.java

cd classes && java Main