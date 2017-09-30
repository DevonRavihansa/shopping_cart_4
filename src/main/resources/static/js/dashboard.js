app.factory('ProductService', function($resource){
    return $resource('/rest/product/:sku', {}, {
        update: {method: 'PUT'}
    });
});
app.config(['$routeProvider',function($routeProvider){
    $routeProvider
        .when('/products',{
            templateUrl: "views/products",
            controller: ['$scope','async', function($scope, async){
                $scope.products = async;
            }],
            resolve: {
                async:['ProductService', function(ProductService){
                    return ProductService.query();
                }]
            }
        })
        .when('/products/create',{
            templateUrl: "views/products/create",
            controller: ['$scope','async', function($scope, async){
                $scope.product = async;
            }],
            resolve: {
                async:['ProductService', function(ProductService){
                    return ProductService.query();
                }]
            }
        })
        .when('/products/:sku',{
            templateUrl: "views/products/detail",
            controller: ['$scope','async', function($scope, async){
                $scope.product = async;
            }],
            resolve: {
                async:['ProductService', '$route', function(ProductService, $route){
                    return ProductService.get({sku:$route.current.params.sku});
                }]
            }
        })
        .otherwise({
            template: "<h1>Default View</h1><p>{{msg}}</p>",
            controller: "defaultCtrl"
        });
}]);

app.controller('defaultCtrl', function($scope){
    $scope.msg = "Default Msg";
});