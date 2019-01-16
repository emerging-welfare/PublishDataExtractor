<h1>Publish Data Extractor Using DCTFinder</h1>

[DCTFinder GitHub Page](https://github.com/xtannier/DCTFinder).

See documentation here: http://sourceforge.net/p/dctfinder/wiki/Home/

## Requirements
Java
Python 3.6.

DCTFinder requires java >= 7.

Wapiti (Conditional Random Fields)

You need to install CRF tool Wapiti http://wapiti.limsi.fr/.

## The following external JAR is required:
Apache Commons Lang http://commons.apache.org/proper/commons-lang/download_lang.cgi
Apache Commons CLI (needed for training only) http://commons.apache.org/proper/commons-cli/download_cli.cgi

## Installation 

Download wapiti, 

Extract it

Open terminal and write 

```

make install

```

Copy the installed path then pasted into the code /DTC/main.java Paths.get("<Past your path here >");


