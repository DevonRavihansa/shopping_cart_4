app.factory('CartService', function($resource){
    return $resource('/rest/cart', {sku:'@sku', quantity:'quantity'}, {
        update: {method: 'PUT'}
    });
});

app.controller('cartController', function($scope, CartService, cart_data){
    var cart_default = {'items':[], 'itemCount':0, 'total':0.0};
    $scope.cart =  cart_data != null ? cart_data : cart_default;

    $scope.add = function(sku){
        CartService.save({sku:sku},{},function(response){
            $scope.cart = response;
        });
    };
    $scope.add = function(sku, quantity){
        CartService.save({sku:sku, quantity:quantity},{},function(response){
            $scope.cart = response;
        });
    };
    $scope.update = function(sku, quantity){
        CartService.update({sku:sku, quantity:quantity},{},function(response){
            $scope.cart = response;
        });
    };
    $scope.remove = function(sku){
        CartService.remove({sku:sku},{},function(response){
            $scope.cart = response;
        });
    };
});