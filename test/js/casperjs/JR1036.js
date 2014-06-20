/*==============================================================================*/
/* Casper generated Wed Jun 18 2014 09:18:21 GMT-0400 (Eastern Daylight Time) */
/*==============================================================================*/

"use strict";
var host, uri;
var user = {
    id: '',
    password: '',
    tier: ''
};
var caspermod = require('casper');
var utils = require("utils");
var cdecurate = require("./common.js");

cdecurate.init(caspermod, utils, user);

casper.test.begin('JR1036 test', function(test) {
    var x = caspermod.selectXPath;

//    casper.wait(1000);
//    casper.echo("clicking on the menu ...");
    casper
        .then(function() {
            cdecurate.connect(caspermod, user);
        })
        .then(function() {
            this.capture('login.png', {
                top: 0,
                left: 0,
                width: casper.options.viewportSize.width,
                height: casper.options.viewportSize.height
            });
            //===click on the "Create" menu
            //this.click(x("/html/body/div[3]/table/tbody/tr[1]/td[2]/table/tbody/tr/td[2]"));  //===#deprecated - only for production as at 6/19/2014
        })
        .then(function() {
            //===click on the "Data Element Concept" menu item
            this.click(x('//*[@id="createMenu"]/dl[2]/dt[2]'));
        })
    ;

//    casper.echo("performing real test ...");
    casper.waitForSelector("form[name=newDECForm] input[name='rNameConv']",
        function success() {
            casper.captureSelector("v.png", "html");
            test.assertExists("form[name=newDECForm] input[name='rNameConv']", "Clicking system generated button of 'Select Short Name Naming Standard' radio button ...");
            this.click("form[name=newDECForm] input[name='rNameConv']");
        },
        function fail() {
            test.assertExists("form[name=newDECForm] input[name='rNameConv']");
        });
    casper.waitForSelector("form[name=newDECForm] input[type=button][value='Validate']",
        function success() {
            test.assertExists("form[name=newDECForm] input[type=button][value='Validate']", "Clicking validate button ...");
            this.click("form[name=newDECForm] input[type=button][value='Validate']");
        },
        function fail() {
            test.assertExists("form[name=newDECForm] input[type=button][value='Validate']");
        });
    casper.waitForSelector("form[name=validateDECForm] input[type=button][value='Back']",
        function success() {
            this.captureSelector("1036.png", "html");
            test.assertDoesntExist(
            {type: 'xpath', path: "//form/table[2]/tbody/tr[5]/td[2][contains(text(),'\(Generated by the System\)')]"},
            'Asserting the fix is good (element with text does not exist!)'
            );
        },
        function fail() {
            this.captureSelector("1036.png", "html");
        });
    casper.waitForSelector("form[name=validateDECForm] input[type=button][value='Back']",
        function success() {
            test.assertExists("form[name=validateDECForm] input[type=button][value='Back']", "Clicking back button ...");
            this.click("form[name=validateDECForm] input[type=button][value='Back']");
        },
        function fail() {
            test.assertExists("form[name=validateDECForm] input[type=button][value='Back']");
        });

    casper.run(function() {
        test.done();
        console.log('TESTED URL [' +this.getCurrentUrl()+']','info');
        this.captureSelector("done.png", "html");
//        this.exit();
    });
});