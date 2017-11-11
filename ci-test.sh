#!/usr/bin/env bash
mvn verify --projects lift-auth-it --also-make -Prun-it -Dse.andolf.environment=${1:-ci}
