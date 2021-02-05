#! /bin/bash

export database=mssql
export mode=polling
export EVENTUATEDATABASE=mssql
export QUARKUS_PROFILE=mssql

./_build-and-test-all.sh $*
