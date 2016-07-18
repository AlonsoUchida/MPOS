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
            var title = "Paratemer retrieved: "
            alert(title + message["req"]);
            alert(title + message["resp"]);
            alert(title + message["msg"]);
            alert(title + message["ref"]);
            alert(title + message["apr"]);
            alert(title + message["voucher"]);
        };
        var error = function(error){
            console.log(error);
        }
        
        var requestCode = '6535655';
        var email = 'agarcia@hotmail.com';
        var moneda = '604';
		var monto = '150.52';
        var comercio = '580';
     
		var args = [requestCode, email, moneda, monto, comercio];
      
       },
          sesion2: function () {
                
      mpos.callmpos(args, success, error);

            
        var success = function(message){
            var title = "Paratemer retrieved: "
            alert(title + message["devicename"]);
        };
        var error = function(error){
            console.log(error);
        }
        var args = [''];
        mpos.calldevicename(args, success, error);
              
          }
    });

    parent.set('homeModel', homeModel);
})(app.home);
