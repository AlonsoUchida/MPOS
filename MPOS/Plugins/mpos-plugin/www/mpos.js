/*global cordova, module*/

module.exports = {
     callmpos: function (options, successCallback, errorCallback) {
            console.log(options);
			options = options || {};

			var requestCode = options[0];
			var email = options[1];
			var moneda = options[2];
            var monto = options[3];

			var args = [requestCode, email, moneda, monto];

			cordova.exec(successCallback, errorCallback, "Mpos", "callmpos", args);
    }
    
};