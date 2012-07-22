(function() {
    var Battery = function() {
        return {
            get: function(property, successCallback, errorCallback) {
                PhoneGap.exec(successCallback, errorCallback, 'Battery', 'get', [ property ]);
            }
        }
    };

    PhoneGap.addConstructor(function() {
        // add plugin to window.plugins
        if (!window.plugins) window.plugins = {};
		window.plugins.battery = new Battery();

        // register plugin on native side
        if (navigator.app && navigator.app.addService)
			navigator.app.addService('Battery', 'com.phonegap.plugins.Battery');
    });
})();
