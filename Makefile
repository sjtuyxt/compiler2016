all:
	$(MAKE) -C src all
	$(MAKE) -C src/ir all
	if [ ! -d bin ]; then mkdir bin; fi
	if [ ! -d bin/ir ]; then mkdir bin/ir; fi
	cp src/*.s bin/
	cp src/*.class bin/
	cp src/ir/*.class bin/ir/
	$(MAKE) -C src clean
	$(MAKE) -C src/ir clean
clean:
	$(MAKE) -C src clean
	$(MAKE) -C src/ir clean
	-rm -rf bin
