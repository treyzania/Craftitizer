CC='gcc'
INSTALL_TO='.'
TEST_DIR='test'

crsplit: crsplit.o
	${CC} -o crsplit crsplit.o

crsplit.o:
	${CC} -c crsplit.c

install: crsplit
	cp crsplit ${INSTALL_TO}

clean:
	rm -f crsplit
	rm -f crsplit.o
	rm -rf ${TEST_DIR}

test: crsplit
	mkdir ${TEST_DIR}
	./crsplit ${TEST_DIR}
