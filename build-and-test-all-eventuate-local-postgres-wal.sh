#! /bin/bash

export database=postgres
export mode=wal
export EVENTUATEDATABASE=postgresql
export QUARKUS_PROFILE=postgresql

./_build-and-test-all.sh $*
