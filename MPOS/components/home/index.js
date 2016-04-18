'use strict';

app.home = kendo.observable({
    onShow: function() {
       
    },
    afterShow: function() {}
});

(function (parent) {
    var homeModel = kendo.observable({
        fields: {
            usuario: '',
            contrasena: '',
        },
        submit: function () {
            //console.log(buscarModel.fields.busdocumento+"-"+buscarModel.fields.busnumero);

        },
        sesion: function () {

        var success = function(message){
            console.log(message);
        };
        var error = function(error){
            console.log(error);
        }
        
        var requestCode = '454545';
        var email = 'agarcia@hotmail.com';
        var moneda = '604';
		var monto = '150.52';
     
		var args = [requestCode, email, moneda, monto];
        
        mpos.callmpos(args, success, error);

        }
    });

    parent.set('homeModel', homeModel);
})(app.home);
