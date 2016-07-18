/*global cordova, module*/

module.exports = {
     callmpos: function (options, successCallback, errorCallback) {
            //console.log(options);
			options = options || {};

			var requestCode = options[0];
			var email = options[1];
			var moneda = options[2];
            var monto = options[3];
         	var comercio = options[4];

			var args = [requestCode, email, moneda, monto, comercio];

			cordova.exec(successCallback, errorCallback, "Mpos", "callmpos", args);
    },
     calldevicename: function (options, successCallback, errorCallback) {
            //console.log(options);
         	console.log("options");
         	var args = [''];
			cordova.exec(successCallback, errorCallback, "Mpos", "calldevicename", args);
    }
    
};