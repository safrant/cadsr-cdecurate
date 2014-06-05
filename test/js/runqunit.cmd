echo Running unit tests in QUnits against headless browser ...
:call phantomjs runner-qun.js units-qun.html
:pause


echo Running unit tests in QUnit without a browser ...
:call qunit --cov -c ./helpers/mock-gf32723.js:./helpers/mock-gf7680.js -d vendors/curl.min-0.7.3.js -t units-qun.js

call qunit --cov -d vendors/curl.min-0.7.3.js -t units-qun.js ./helpers/mock-gf32723.js ./helpers/mock-gf7680.js